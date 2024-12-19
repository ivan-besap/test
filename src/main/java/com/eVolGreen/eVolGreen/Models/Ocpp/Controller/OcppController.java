package com.eVolGreen.eVolGreen.Models.Ocpp.Controller;

import com.eVolGreen.eVolGreen.Configurations.MQ.WebSocketHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Session;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.Enums.RemoteStartStopStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.Enums.ResetStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.Enums.UnlockStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums.ChargingProfileKindType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums.ChargingProfilePurposeType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums.ResetType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.ChargingProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.ChargingSchedule;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.MeterValue;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.IniciarCargaRemotaRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.RemoteTrigger.Request.Enums.TriggerMessageRequestType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.RemoteTrigger.Request.TriggerMessageRequest;
import com.eVolGreen.eVolGreen.Services.AccountService.UtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.eVolGreen.eVolGreen.Configurations.MQ.WebSocketHandler.sessionStore;

import static com.eVolGreen.eVolGreen.Configurations.MQ.WebSocketHandler.webSocketSessionStorage;
import static org.apache.activemq.plugin.ForcePersistencyModeBroker.log;


@RestController
@RequestMapping("/api")
public class OcppController {

    @Autowired
    private WebSocketHandler webSocketHandler;

    @Autowired
    private UtilService utilService;

    private static final Logger logger = LoggerFactory.getLogger(OcppController.class);

    private final Map<Long, MeterValuesRequest> latestMeterValuesByConnector = new ConcurrentHashMap<>();

    /**
     * Endpoint para iniciar una transacción de carga remota en un punto de carga específico.
     * <p>
     * Este método permite al servidor enviar una solicitud de inicio de transacción a un punto de carga
     * remoto, proporcionando el ID de la sesión, el ID del conector y la etiqueta de identificación (idTag).
     *
     * @return Un ResponseEntity con un mensaje de éxito o de error, según el resultado.
     */
    @PostMapping("/remote-start")
    public ResponseEntity<?> remoteStartTransaction(@RequestBody Map<String, Object> payload) {
        logger.info("Solicitud recibida: {}", payload);

        try {
            // Extraer los parámetros
            String chargePointId = (String) payload.get("chargePointId");
            Map<String, Object> requestPayload = (Map<String, Object>) payload.get("requestPayload");

            // Validar parámetros obligatorios
            if (chargePointId == null || requestPayload == null) {
                logger.error("Faltan parámetros obligatorios: chargePointId o requestPayload");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Faltan parámetros obligatorios: chargePointId o requestPayload.");
            }

            // Validar idTag y connectorId
            String idTag = (String) requestPayload.get("idTag");
            Integer connectorId = (Integer) requestPayload.get("connectorId");

            if (idTag == null || connectorId == null) {
                logger.error("Faltan campos requeridos en requestPayload: idTag o connectorId");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Faltan campos requeridos en requestPayload: idTag o connectorId.");
            }

            logger.info("Datos extraídos: chargePointId={}, idTag={}, connectorId={}", chargePointId, idTag, connectorId);

            // Buscar la sesión activa
            UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionId == null) {
                logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
            }

            Session session = sessionStore.get(sessionId);
            WebSocketSession webSocketSession = webSocketSessionStorage.get(sessionId);

            if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
                logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("La sesión para chargePointId " + chargePointId + " no está activa.");
            }

            // Crear el identificador único para el mensaje
            String messageId = UUID.randomUUID().toString();

            // Crear la solicitud de RemoteStartTransaction
            RemoteStartTransactionRequest request = new RemoteStartTransactionRequest();
            request.setIdTag(idTag);
            request.setConnectorId(connectorId);

            // Enviar la solicitud y obtener el CompletableFuture
            CompletableFuture<Confirmation> futureResponse = session.sendRemoteStartTransaction("RemoteStartTransaction", request, messageId);

            // Manejar la respuesta asincrónicamente
            futureResponse.whenComplete((confirmation, error) -> {
                if (error != null) {
                    logger.error("Error procesando la confirmación para RemoteStartTransaction con messageId {}: {}", messageId, error.getMessage(), error);
                    try {
                        webSocketHandler.sendError(session, webSocketSession, messageId, "Error en RemoteStartTransaction: " + error.getMessage());
                    } catch (IOException ioException) {
                        logger.error("Error enviando error al cliente: {}", ioException.getMessage(), ioException);
                    }
                } else {
                    try {
                        if (confirmation instanceof RemoteStartTransactionConfirmation) {
                            RemoteStartTransactionConfirmation remoteStartConfirmation = (RemoteStartTransactionConfirmation) confirmation;
                            if (remoteStartConfirmation.getStatus() == RemoteStartStopStatus.Accepted) {
                                logger.info("RemoteStartTransaction aceptado para chargePointId: {}", chargePointId);
                            } else {
                                logger.warn("RemoteStartTransaction rechazado para chargePointId: {}", chargePointId);
                            }

                            // Enviar la respuesta al cliente WebSocket
                            webSocketHandler.sendResponse(session, webSocketSession, messageId, "RemoteStartTransaction", remoteStartConfirmation);
                        } else {
                            logger.warn("Tipo de confirmación inesperado: {}", confirmation);
                            webSocketHandler.sendError(session, webSocketSession, messageId, "Error: Confirmación inesperada para RemoteStartTransaction.");
                        }
                    } catch (IOException e) {
                        logger.error("Error enviando respuesta al cliente: {}", e.getMessage(), e);
                    }
                }
            });

            logger.info("RemoteStartTransaction enviado: chargePointId={}, idTag={}, connectorId={}", chargePointId, idTag, connectorId);
            return ResponseEntity.ok("RemoteStartTransaction enviado con éxito para chargePointId: " + chargePointId);

        } catch (IllegalArgumentException e) {
            logger.error("Error en los argumentos de RemoteStartTransaction: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error en los argumentos de RemoteStartTransaction: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error procesando la solicitud de RemoteStartTransaction: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al procesar la solicitud de RemoteStartTransaction.");
        }
    }


    @PostMapping("/remote-stop")
    public ResponseEntity<?> remoteStopTransaction(@RequestBody Map<String, Object> payload) {
        logger.info("Solicitud recibida: {}", payload);

        try {
            // Extraer los parámetros
            String chargePointId = (String) payload.get("chargePointId");
            Map<String, Object> requestPayload = (Map<String, Object>) payload.get("requestPayload");

            // Validar parámetros obligatorios
            if (chargePointId == null || requestPayload == null) {
                logger.error("Faltan parámetros obligatorios: chargePointId o requestPayload");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Faltan parámetros obligatorios: chargePointId o requestPayload.");
            }

            // Validar transactionId
            Integer transactionId = (Integer) requestPayload.get("transactionId");
            if (transactionId == null) {
                logger.error("Falta el campo requerido en requestPayload: transactionId");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Falta el campo requerido en requestPayload: transactionId.");
            }

            logger.info("Datos extraídos: chargePointId={}, transactionId={}", chargePointId, transactionId);

            // Buscar la sesión activa
            UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionId == null) {
                logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
            }

            Session session = sessionStore.get(sessionId);
            WebSocketSession webSocketSession = webSocketSessionStorage.get(sessionId);

            if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
                logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("La sesión para chargePointId " + chargePointId + " no está activa.");
            }

            // Crear el identificador único para el mensaje
            String messageId = UUID.randomUUID().toString();

            // Crear la solicitud de RemoteStopTransaction
            RemoteStopTransactionRequest request = new RemoteStopTransactionRequest();
            request.setTransactionId(transactionId);

            // Enviar la solicitud y obtener el CompletableFuture
            CompletableFuture<Confirmation> futureResponse = session.sendRemoteStartTransaction("RemoteStopTransaction", request, messageId);

            // Manejar la respuesta asincrónicamente
            futureResponse.whenComplete((confirmation, error) -> {
                if (error != null) {
                    logger.error("Error procesando la confirmación para RemoteStopTransaction con messageId {}: {}", messageId, error.getMessage(), error);
                    try {
                        webSocketHandler.sendError(session, webSocketSession, messageId, "Error en RemoteStopTransaction: " + error.getMessage());
                    } catch (IOException ioException) {
                        logger.error("Error enviando error al cliente: {}", ioException.getMessage(), ioException);
                    }
                } else {
                    try {
                        if (confirmation instanceof RemoteStopTransactionConfirmation) {
                            RemoteStopTransactionConfirmation remoteStopConfirmation = (RemoteStopTransactionConfirmation) confirmation;
                            if (remoteStopConfirmation.getStatus() == RemoteStartStopStatus.Accepted) {
                                logger.info("RemoteStopTransaction aceptado para chargePointId: {}", chargePointId);
                            } else {
                                logger.warn("RemoteStopTransaction rechazado para chargePointId: {}", chargePointId);
                            }

                            // Enviar la respuesta al cliente WebSocket
                            webSocketHandler.sendResponse(session, webSocketSession, messageId, "RemoteStopTransaction", remoteStopConfirmation);
                        } else {
                            logger.warn("Tipo de confirmación inesperado: {}", confirmation);
                            webSocketHandler.sendError(session, webSocketSession, messageId, "Error: Confirmación inesperada para RemoteStopTransaction.");
                        }
                    } catch (IOException e) {
                        logger.error("Error enviando respuesta al cliente: {}", e.getMessage(), e);
                    }
                }
            });

            logger.info("RemoteStopTransaction enviado: chargePointId={}, transactionId={}", chargePointId, transactionId);
            return ResponseEntity.ok("RemoteStopTransaction enviado con éxito para chargePointId: " + chargePointId);

        } catch (IllegalArgumentException e) {
            logger.error("Error en los argumentos de RemoteStopTransaction: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error en los argumentos de RemoteStopTransaction: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error procesando la solicitud de RemoteStopTransaction: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al procesar la solicitud de RemoteStopTransaction.");
        }
    }



    // Método que maneja la lógica de inicio de la transacción
    private void sendStartTransaction(Session ocppSession, WebSocketSession webSocketSession, String connectorId, String idTag) {
        try {
            if (ocppSession == null) {
                throw new IllegalArgumentException("La sesión OCPP no puede ser nula.");
            }
            if (webSocketSession == null) {
                throw new IllegalArgumentException("La sesión WebSocket no puede ser nula.");
            }
            if (idTag == null || idTag.isEmpty()) {
                throw new IllegalArgumentException("El idTag no puede ser nulo o vacío.");
            }

            // Aquí reutilizas la lógica de enviar la solicitud con el método sendRequest de la sesión
            logger.debug("Iniciando transacción con connectorId: " + connectorId + " y idTag: " + idTag);
            StartTransactionRequest request = new StartTransactionRequest(Integer.parseInt(connectorId), idTag, 0, ZonedDateTime.now());
            ocppSession.sendRequest("RemoteStartTransaction", request, UUID.randomUUID().toString());

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Formato de ID del conector no válido.");
        } catch (Exception e) {
            throw new IllegalStateException("Error al enviar la solicitud de transacción remota: " + e.getMessage(), e);
        }
    }


    private Session getSessionOrThrow(String sessionId) {
        Session ocppSession = webSocketHandler.getSessionById(sessionId);
        if (ocppSession == null) {
            throw new IllegalArgumentException("No se encontró la sesión OCPP para ID: " + sessionId);
        }
        return ocppSession;
    }

    private WebSocketSession getWebSocketSessionOrThrow(String sessionId) {
        WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionId);
        if (webSocketSession == null) {
            throw new IllegalArgumentException("No se encontró la sesión WebSocket para ID: " + sessionId);
        }
        return webSocketSession;
    }


    // 2 HearBeat
    @PostMapping("/heartbeat")
    public ResponseEntity<String> sendHeartbeat(@RequestParam String sessionId) {
        try {
            Session ocppSession = getSessionOrThrow(sessionId);
            WebSocketSession webSocketSession = getWebSocketSessionOrThrow(sessionId);

            // Enviar Heartbeat y procesar respuesta
            sendHeartbeatRequest(ocppSession, webSocketSession);

            return ResponseEntity.ok("Heartbeat enviado.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error enviando Heartbeat.");
        }
    }

    private void sendHeartbeatRequest(Session ocppSession, WebSocketSession webSocketSession) throws IOException {
        HeartbeatRequest request = new HeartbeatRequest();
        webSocketHandler.handleHeartbeat(ocppSession, webSocketSession, request, UUID.randomUUID().toString());
    }

    // 3 MeterValue
    @GetMapping("/meter-values")
    public ResponseEntity<List<MeterValue>> getMeterValues(
            @RequestParam String sessionId,
            @RequestParam String transactionId) {
        try {

            // Recuperar la sesión OCPP y la sesión WebSocket
            Session ocppSession = getSessionOrThrow(sessionId);
            WebSocketSession webSocketSession = getWebSocketSessionOrThrow(sessionId);

            // Recuperar las métricas de carga para la sesión y transacción dadas
            List<MeterValue> meterValues = retrieveMeterValues(ocppSession, transactionId);

            if (meterValues.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(meterValues);
            }

            return ResponseEntity.ok(meterValues);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método que recupera las métricas de carga (meter values) para una sesión y transacción dadas
    private List<MeterValue> retrieveMeterValues(Session ocppSession, String transactionId) {

        List<MeterValue> meterValues = new ArrayList<>();

        // Ejemplo básico de datos simulados
        MeterValue sampleMeterValue = new MeterValue();
        meterValues.add(sampleMeterValue);

        return meterValues;
    }


// 4

    @GetMapping("/get-session-by-idTag")
    public ResponseEntity<String> getSessionByIdTag(@RequestParam String idTag) {
        try {
            // Buscar la sesión correspondiente al idTag
            String sessionId = findSessionIdByIdTag(idTag);

            if (sessionId == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró una sesión para el idTag proporcionado.");
            }

            return ResponseEntity.ok("ID de sesión encontrado: " + sessionId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error buscando la sesión: " + e.getMessage());
        }
    }

    // Método que busca el sessionId basado en el idTag
    private String findSessionIdByIdTag(String idTag) {

        Map<String, String> idTagToSessionMap = getIdTagToSessionMap();  // Debes implementar este método

        return idTagToSessionMap.get(idTag);
    }

    // Método ejemplo para obtener el mapa de idTags a sessionIds
    private Map<String, String> getIdTagToSessionMap() {
        // Aquí deberías devolver el mapa que contiene la relación idTag -> sessionId
        // Este es solo un ejemplo, deberías adaptarlo a tu implementación.
        Map<String, String> idTagToSessionMap = new HashMap<>();
        idTagToSessionMap.put("ABCDE", "07eded6d-3a71-d110-13e0-89dd0e168e8f");
        // Agregar más asociaciones según sea necesario
        return idTagToSessionMap;
    }

    @PostMapping("/enviar-heartbeat")
    public ResponseEntity<String> enviarHeartbeat(@RequestParam String ocppid, @RequestParam String vendorId, @RequestParam boolean enableAutomatic) {
        try {
            log.info("Recibido en el backend: Enviar Heartbeat para ocppid -> {}, enableAutomatic -> {}", ocppid, enableAutomatic);

            // Llamar al método que comunica al simulador
            utilService.enviarHeartbeatASimulador(ocppid, enableAutomatic, vendorId);

            return ResponseEntity.ok("Heartbeat enviado correctamente al simulador");
        } catch (Exception e) {
            log.error("Error enviando Heartbeat al simulador: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al enviar Heartbeat al simulador");
        }
    }

    @PostMapping("/iniciar-carga")
    public ResponseEntity<String> iniciarCarga(
            @RequestParam int connectorId) {
        try {
            log.info("Iniciando carga para el conector: {}", connectorId);

            // Llamar al servicio para comunicar al simulador
            utilService.iniciarCargaEnSimulador(connectorId);

            return ResponseEntity.ok("Carga iniciada con éxito");
        } catch (Exception e) {
            log.error("Error iniciando la carga: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error iniciando la carga");
        }
    }

    @PostMapping("/iniciar-carga-remota")
    public void iniciarCargaRemotaEnSimulador(@RequestParam String session,
                                              @RequestBody RemoteStartTransactionRequest request) throws Exception {
//        String url = "http://localhost:8081/api/charge-point/remote-start";

//        UUID sessionId = session.getSessionId();
        Session ocppSession = getSessionOrThrow(session);
        WebSocketSession webSocketSession = getWebSocketSessionOrThrow(session);


        // Configurar los encabezados de la solicitud si son necesarios
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Crear la solicitud HTTP con el payload y los encabezados
        HttpEntity<RemoteStartTransactionRequest> requestEntity = new HttpEntity<>(request, headers);

        String messageId = UUID.randomUUID().toString();

        // Enviar la solicitud POST al simulador
        webSocketHandler.handleRemoteStartTransaction(ocppSession, webSocketSession, request, messageId);

        // Verificar si la respuesta es exitosa
//        if (!response.getStatusCode().is2xxSuccessful()) {
//            throw new Exception("Error al iniciar la carga remota en el simulador: " + response.getBody());
//        }

        log.info("Carga remota en simulador iniciada con éxito para connectorId: {} y idTag: {}", request.getConnectorId(), request.getIdTag());
    }

    @PostMapping("/iniciar-carga-remota2")
    public ResponseEntity<String> iniciarCargaRemotaEnSimulador2(
            @RequestParam String session,
            @RequestBody RemoteStartTransactionRequest request) {
        try {
            if (request == null || request.getIdTag() == null || request.getIdTag().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El campo 'idTag' es requerido.");
            }

            if (session == null || session.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El parámetro 'session' es requerido.");
            }

            logger.info("Iniciando RemoteStartTransaction para sesión: {} y idTag: {}", session, request.getIdTag());

            Session ocppSession = getSessionOrThrow(session);
            WebSocketSession webSocketSession = getWebSocketSessionOrThrow(session);

            String messageId = UUID.randomUUID().toString();

            // Llamar al handler
            webSocketHandler.handleRemoteStartTransaction2(ocppSession, webSocketSession, request, messageId);

            return ResponseEntity.ok("Solicitud de RemoteStartTransaction enviada con éxito para idTag: " + request.getIdTag());
        } catch (Exception e) {
            logger.error("Error al enviar RemoteStartTransaction", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
        }
    }


//    @PostMapping("/iniciar-carga-remota")
//    public ResponseEntity<String> iniciarCargaRemota(@RequestBody IniciarCargaRemotaRequest request) {
//        try {
//            log.info("Iniciando carga remota para el conector: {} con idTag: {}", request.getConnectorId(), request.getIdTag());
//
//            // Llamar al servicio para comunicar con el simulador
//            utilService.iniciarCargaRemotaEnSimulador(request.getConnectorId(), request.getIdTag(), request.getChargingProfile());
//
//            return ResponseEntity.ok("Carga remota iniciada con éxito");
//        } catch (Exception e) {
//            log.error("Error iniciando la carga remota: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error iniciando la carga remota");
//        }
//    }


//    @PostMapping("/detener-carga-remota")
//    public ResponseEntity<String> detenerCargaRemota(@RequestBody RemoteStopTransactionRequest request) {
//        try {
//            log.info("Deteniendo carga remota para transactionId: {}", request.getTransactionId());
//
//            // Llamar al servicio para comunicar con el simulador
//            utilService.detenerCargaRemotaEnSimulador(request.getTransactionId());
//
//            return ResponseEntity.ok("Carga remota detenida con éxito");
//        } catch (Exception e) {
//            log.error("Error deteniendo la carga remota: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deteniendo la carga remota");
//        }
//    }

    @PostMapping("/detener-carga-remota")
    public ResponseEntity<?> detenerCargaRemotaEnSimulador(@RequestParam String session,
                                                           @RequestBody RemoteStopTransactionRequest request) {
        try {
            // 1. Validar parámetros
            if (session == null || session.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El parámetro 'session' es requerido.");
            }

            if (request == null || request.getTransactionId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El campo 'transactionId' en el request es requerido.");
            }

            logger.info("Solicitud para detener carga remota recibida. Session: {}, TransactionId: {}", session, request.getTransactionId());

            // 2. Obtener sesiones activas
            Session ocppSession = getSessionOrThrow(session);
            WebSocketSession webSocketSession = getWebSocketSessionOrThrow(session);

            // 3. Generar un messageId único
            String messageId = UUID.randomUUID().toString();

            // 4. Llamar al handler para enviar el RemoteStopTransaction
            webSocketHandler.handleRemoteStopTransaction2(ocppSession, webSocketSession, request, messageId);

            logger.info("Solicitud RemoteStopTransaction enviada exitosamente. Session: {}, MessageId: {}", session, messageId);

            // 5. Retornar respuesta de éxito
            return ResponseEntity.ok("Solicitud de detener carga remota enviada correctamente. MessageId: " + messageId);

        } catch (IllegalArgumentException e) {
            logger.error("Error en los parámetros de la solicitud: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parámetro inválido: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error al procesar detener carga remota", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
        }
    }

    @PostMapping("/getConfiguration")
    public ResponseEntity<String> getConfiguration(@RequestParam String sessionId, @RequestBody GetConfigurationRequest requestPayload) {
        try {
            // Obtener la sesión WebSocket y la OCPP Session a partir del sessionId
            UUID sessionUUID = UUID.fromString(sessionId);
            WebSocketSession webSocketSession = WebSocketHandler.webSocketSessionStorage.get(sessionUUID);
            Session ocppSession = WebSocketHandler.sessionStore.get(sessionUUID);

            if (webSocketSession == null || ocppSession == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró una sesión activa para el sessionId: " + sessionId);
            }

            if (!webSocketSession.isOpen()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La sesión WebSocket no está activa para el sessionId: " + sessionId);
            }

            // Crear un mensajeId único para el mensaje
            String messageId = UUID.randomUUID().toString();

            // Llamar al WebSocketHandler para manejar la solicitud
            webSocketHandler.handleGetConfigurationRequest2(ocppSession, webSocketSession, requestPayload, messageId);

            return ResponseEntity.ok("Solicitud GetConfiguration enviada exitosamente al cargador.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en el sessionId: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error procesando GetConfiguration: " + e.getMessage());
        }
    }



    @GetMapping("/obtener-ultimo-meter-value-json")
    public ResponseEntity<String> getLastMeterValuesJson() {
        String meterValuesJson = utilService.getMeterValuesFront();
        if (meterValuesJson != null) {
            return ResponseEntity.ok(meterValuesJson);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping("/detener-carga")
    public ResponseEntity<String> detenerCarga() {
        try {
            log.info("Deteniendo carga en el simulador");

            // Llamar al servicio para comunicar al simulador
            utilService.detenerCargaEnSimulador();

            return ResponseEntity.ok("Carga detenida con éxito");
        } catch (Exception e) {
            log.error("Error deteniendo la carga: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deteniendo la carga");
        }
    }

    @PostMapping("/desbloquear-conector")
    public ResponseEntity<String> desbloquearConector(@RequestParam int connectorId) {
        try {
            log.info("Desbloqueando Conector en el simulador");

            // Llamar al servicio para comunicar al simulador
            utilService.desbloquearConectorSimulador(connectorId);

            return ResponseEntity.ok("Conector Desbloqueado con éxito");
        } catch (Exception e) {
            log.error("Error Desbloqueando el conector: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Desbloqueando el conector");
        }
    }

    @PostMapping("/reset-cargador")
    public ResponseEntity<String> resetCargador(@RequestBody ResetRequest resetRequest, @RequestParam String session) {
        try {
            // Validar parámetros
            if (resetRequest == null || resetRequest.getType() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El campo 'type' es requerido (Hard o Soft).");
            }

            if (session == null || session.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El parámetro 'session' es requerido.");
            }

            logger.info("Reseteando Cargador con session: {}. Tipo de reset: {}", session, resetRequest.getType());

            // Obtener sesiones activas
            Session ocppSession = getSessionOrThrow(session);
            WebSocketSession webSocketSession = getWebSocketSessionOrThrow(session);

            // Generar messageId único
            String messageId = UUID.randomUUID().toString();

            // Llamar al handler
            webSocketHandler.handleReset2(ocppSession, webSocketSession, resetRequest, messageId);

            return ResponseEntity.ok("Solicitud de Reset enviada con éxito. Tipo: " + resetRequest.getType());

        } catch (Exception e) {
            logger.error("Error al enviar la solicitud de Reset", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
        }
    }




    // Método que maneja la lógica de inicio de la transacción de carga remota

    @PostMapping("/change-configuration")
    public ResponseEntity<?> changeConfiguration(@RequestBody Map<String, Object> payload) {
        logger.info("Solicitud recibida: {}", payload);

        try {
            // Extraer los parámetros
            String chargePointId = (String) payload.get("chargePointId");
            String key = (String) payload.get("key");
            String value = (String) payload.get("value");

            // Validar parámetros obligatorios
            if (chargePointId == null || key == null || value == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Faltan parámetros obligatorios: chargePointId, key o value.");
            }

            // Obtener la sesión
            UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionId == null) {
                logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
            }

            Session session = sessionStore.get(sessionId);
            WebSocketSession webSocketSession = webSocketSessionStorage.get(sessionId);

            if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
                logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("La sesión para chargePointId " + chargePointId + " no está activa.");
            }

            // Crear la solicitud ChangeConfigurationRequest
            ChangeConfigurationRequest request = new ChangeConfigurationRequest();
            request.setKey(key);
            request.setValue(value);

            String messageId = UUID.randomUUID().toString();

            // Enviar la solicitud a la Estación de Carga
            CompletableFuture<Confirmation> futureResponse = session.sendRemoteStartTransaction("ChangeConfiguration", request, messageId);

            // Manejar la respuesta asincrónicamente
            futureResponse.whenComplete((confirmation, error) -> {
                if (error != null) {
                    logger.error("Error al recibir la confirmación para ChangeConfiguration: {}", error.getMessage(), error);
                    try {
                        webSocketHandler.sendError(session, webSocketSession, messageId, "Error en ChangeConfiguration: " + error.getMessage());
                    } catch (IOException ioException) {
                        logger.error("Error enviando error al cliente: {}", ioException.getMessage(), ioException);
                    }
                } else {
                    try {
                        if (confirmation instanceof ChangeConfigurationConfirmation) {
                            ChangeConfigurationConfirmation changeConfigConfirmation = (ChangeConfigurationConfirmation) confirmation;
                            logger.info("ChangeConfigurationConfirmation recibida: {}", changeConfigConfirmation);

                            // Procesar la respuesta según sea necesario
                            // Por ejemplo, verificar si el cambio fue aceptado o rechazado

                            // Opcionalmente, enviar la respuesta al cliente que inició la solicitud
                        } else {
                            logger.warn("Tipo de confirmación inesperado: {}", confirmation);
                            webSocketHandler.sendError(session, webSocketSession, messageId, "Error: Confirmación inesperada para ChangeConfiguration.");
                        }
                    } catch (IOException e) {
                        logger.error("Error enviando respuesta al cliente: {}", e.getMessage(), e);
                    }
                }
            });

            logger.info("ChangeConfigurationRequest enviado a chargePointId: {}", chargePointId);
            return ResponseEntity.ok("ChangeConfigurationRequest enviado con éxito a chargePointId: " + chargePointId);

        } catch (Exception e) {
            logger.error("Error procesando la solicitud de ChangeConfiguration: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al procesar la solicitud de ChangeConfiguration.");
        }
    }

    /**
     * Endpoint para enviar una solicitud GetConfigurationRequest a un cargador específico.
     *
     * @param chargePointId El ID del punto de carga al que se enviará la solicitud.
     * @param keys          (Opcional) Lista de claves de configuración que se desean obtener.
     * @return La confirmación GetConfigurationConfirmation recibida del cargador.
     */
//    @PostMapping("/getConfiguration")
//    public ResponseEntity<?> getConfiguration(
//            @RequestParam String chargePointId,
//            @RequestBody(required = false) List<String> keys) {
//        try {
//            // Obtener el UUID de la sesión asociada al chargePointId
//            UUID sessionUUID = webSocketHandler.getSessionIdByChargePointId(chargePointId);
//            if (sessionUUID == null) {
//                return ResponseEntity.badRequest().body("ChargePointId no encontrado o no está conectado.");
//            }
//
//            // Obtener la WebSocketSession asociada
//            WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionUUID.toString());
//            if (webSocketSession == null || !webSocketSession.isOpen()) {
//                return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
//            }
//
//            // Obtener la sesión OCPP
//            Session session = webSocketHandler.getSessionById(sessionUUID.toString());
//            if (session == null) {
//                return ResponseEntity.badRequest().body("Sesión OCPP no encontrada.");
//            }
//
//            // Generar un messageId único para esta solicitud
//            String messageId = UUID.randomUUID().toString();
//
//            // Enviar la solicitud GetConfigurationRequest de manera asíncrona con el messageId
//            CompletableFuture<GetConfigurationConfirmation> future = webSocketHandler.sendGetConfigurationRequestAsync(session, webSocketSession, keys, messageId);
//
//            // Esperar la confirmación con un timeout
//            GetConfigurationConfirmation confirmation = future.get(10, TimeUnit.SECONDS); // Ajusta el timeout según tus necesidades
//
//            return ResponseEntity.ok(confirmation);
//
//        } catch (TimeoutException e) {
//            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("Timeout esperando GetConfigurationConfirmation.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
//        }
//    }

    @PostMapping("/trigger-status")
    public ResponseEntity<String> requestStatus(@RequestParam String chargePointId,
                                                @RequestBody TriggerMessageRequest request) {

        UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
        if (sessionId == null) {
            logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
        }

        Session session = sessionStore.get(sessionId);
        WebSocketSession webSocketSession = webSocketSessionStorage.get(sessionId);

        if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
            logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La sesión para chargePointId " + chargePointId + " no está activa.");
        }

        try {
            String messageId = UUID.randomUUID().toString();

            webSocketHandler.handleStatusNotificationTrigger(session, webSocketSession, messageId, request);
            return ResponseEntity.ok("Solicitud de StatusNotification enviada exitosamente");
        } catch (IOException e) {
            logger.error("Error al enviar StatusNotificationRequest", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error enviando solicitud");
        }
    }

    @GetMapping("/triggerHeartbeat")
    public ResponseEntity<String> triggerHeartbeat(@RequestParam String chargePointId) {
        try {
            // Obtener el sessionId a partir del chargePointId
            UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionId == null) {
                return ResponseEntity.badRequest().body("No se encontró una sesión para el chargePointId: " + chargePointId);
            }

            // Obtener la sesión OCPP asociada
            Session session = WebSocketHandler.sessionStore.get(sessionId);
            if (session == null) {
                return ResponseEntity.badRequest().body("Sesión no encontrada para el sessionId: " + sessionId);
            }

            // Obtener la sesión WebSocket asociada
            WebSocketSession webSocketSession = WebSocketHandler.webSocketSessionStorage.get(sessionId);
            if (webSocketSession == null || !webSocketSession.isOpen()) {
                return ResponseEntity.badRequest().body("La sesión WebSocket no está abierta o no se encontró.");
            }

            // Crear el TriggerMessageRequest
            TriggerMessageRequest request = new TriggerMessageRequest();
            request.setRequestedMessage(TriggerMessageRequestType.Heartbeat);

            // Generar un messageId
            String messageId = UUID.randomUUID().toString();

            // Llamar a handleHeartbeatTrigger
            webSocketHandler.handleHeartbeatTrigger(session, webSocketSession, messageId, request);

            return ResponseEntity.ok("Se envió el TriggerMessage (Heartbeat) correctamente.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error enviando TriggerMessage: " + e.getMessage());
        }
    }

    @PostMapping("/trigger-diagnosticsStatusNotification")
    public ResponseEntity<String> triggerDiagnosticsStatusNotification(@RequestParam String chargePointId,
                                                 @RequestBody TriggerMessageRequest request) {

        UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
        if (sessionId == null) {
            logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
        }

        Session session = sessionStore.get(sessionId);
        WebSocketSession webSocketSession = webSocketSessionStorage.get(sessionId);

        if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
            logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La sesión para chargePointId " + chargePointId + " no está activa.");
        }

        try {
            String messageId = UUID.randomUUID().toString();

            webSocketHandler.handleDiagnosticsStatusNotificationTrigger(session, webSocketSession, messageId, request);
            return ResponseEntity.ok("Solicitud de " + request.getRequestedMessage() + " enviada exitosamente");
        } catch (IOException e) {
            logger.error("Error al enviar TriggerMessageRequest", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error enviando solicitud");
        }
    }

    @PostMapping("/trigger-bootNotification")
    public ResponseEntity<String> triggerBootNotification(@RequestParam String chargePointId,
                                                          @RequestBody TriggerMessageRequest request) {

        UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
        if (sessionId == null) {
            logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
        }

        Session session = sessionStore.get(sessionId);
        WebSocketSession webSocketSession = webSocketSessionStorage.get(sessionId);

        if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
            logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La sesión para chargePointId " + chargePointId + " no está activa.");
        }

        try {
            String messageId = UUID.randomUUID().toString();

            webSocketHandler.handleBootNotificationTrigger(session, webSocketSession, messageId, request);
            return ResponseEntity.ok("Solicitud de " + request.getRequestedMessage() + " enviada exitosamente");
        } catch (IOException e) {
            logger.error("Error al enviar TriggerMessageRequest", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error enviando solicitud");
        }
    }

    @PostMapping("/trigger-firmwareStatusNotification")
    public ResponseEntity<String> triggerFirmwareStatusNotification(@RequestParam String chargePointId,
                                                          @RequestBody TriggerMessageRequest request) {

        UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
        if (sessionId == null) {
            logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
        }

        Session session = sessionStore.get(sessionId);
        WebSocketSession webSocketSession = webSocketSessionStorage.get(sessionId);

        if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
            logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La sesión para chargePointId " + chargePointId + " no está activa.");
        }

        try {
            String messageId = UUID.randomUUID().toString();

            webSocketHandler.handleFirmwareStatusNotificationTrigger(session, webSocketSession, messageId, request);
            return ResponseEntity.ok("Solicitud de " + request.getRequestedMessage() + " enviada exitosamente");
        } catch (IOException e) {
            logger.error("Error al enviar TriggerMessageRequest", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error enviando solicitud");
        }
    }

    @PostMapping("/trigger-meterValues")
    public ResponseEntity<String> triggerMeterValues(@RequestParam String chargePointId,
                                                                    @RequestBody TriggerMessageRequest request) {

        UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
        if (sessionId == null) {
            logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
        }

        Session session = sessionStore.get(sessionId);
        WebSocketSession webSocketSession = webSocketSessionStorage.get(sessionId);

        if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
            logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La sesión para chargePointId " + chargePointId + " no está activa.");
        }

        try {
            String messageId = UUID.randomUUID().toString();

            webSocketHandler.handleMeterValuesTrigger(session, webSocketSession, messageId, request);
            return ResponseEntity.ok("Solicitud de " + request.getRequestedMessage() + " enviada exitosamente");
        } catch (IOException e) {
            logger.error("Error al enviar TriggerMessageRequest", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error enviando solicitud");
        }
    }

    @PostMapping("/unlock-connector")
    public ResponseEntity<?> unlockConnector(@RequestBody Map<String, Object> payload) {
        logger.info("Solicitud recibida: {}", payload);

        try {
            // Extraer los parámetros
            String chargePointId = (String) payload.get("chargePointId");
            Integer connectorId = (Integer) payload.get("connectorId");

            // Validar parámetros obligatorios
            if (chargePointId == null || connectorId == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Faltan parámetros obligatorios: chargePointId o connectorId.");
            }

            // Obtener la sesión
            UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionId == null) {
                logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
            }

            Session session = sessionStore.get(sessionId);
            WebSocketSession webSocketSession = webSocketSessionStorage.get(sessionId);

            if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
                logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("La sesión para chargePointId " + chargePointId + " no está activa.");
            }

            // Crear la solicitud UnlockConnectorRequest
            UnlockConnectorRequest request = new UnlockConnectorRequest();
            request.setConnectorId(connectorId);

            String messageId = UUID.randomUUID().toString();

            // Enviar la solicitud a la Estación de Carga
            CompletableFuture<Confirmation> futureResponse = session.sendRemoteStartTransaction("UnlockConnector", request, messageId);

            // Manejar la respuesta asincrónicamente
            futureResponse.whenComplete((confirmation, error) -> {
                if (error != null) {
                    logger.error("Error al recibir la confirmación para UnlockConnector: {}", error.getMessage(), error);
                    try {
                        webSocketHandler.sendError(session, webSocketSession, messageId, "Error en UnlockConnector: " + error.getMessage());
                    } catch (IOException ioException) {
                        logger.error("Error enviando error al cliente: {}", ioException.getMessage(), ioException);
                    }
                } else {
                    try {
                        if (confirmation instanceof UnlockConnectorConfirmation) {
                            UnlockConnectorConfirmation unlockConnectorConfirmation = (UnlockConnectorConfirmation) confirmation;
                            logger.info("UnlockConnectorConfirmation recibida: {}", unlockConnectorConfirmation);

                            // Procesar la respuesta según sea necesario
                            // Verificar el estado de la confirmación
                            if (unlockConnectorConfirmation.getStatus() == UnlockStatus.Unlocked) {
                                logger.info("Conector {} desbloqueado exitosamente para chargePointId: {}", connectorId, chargePointId);
                            } else {
                                logger.warn("No se pudo desbloquear el conector {} para chargePointId: {}. Estado: {}", connectorId, chargePointId, unlockConnectorConfirmation.getStatus());
                            }

                            // Opcionalmente, enviar la respuesta al cliente que inició la solicitud
                        } else {
                            logger.warn("Tipo de confirmación inesperado: {}", confirmation);
                            webSocketHandler.sendError(session, webSocketSession, messageId, "Error: Confirmación inesperada para UnlockConnector.");
                        }
                    } catch (IOException e) {
                        logger.error("Error enviando respuesta al cliente: {}", e.getMessage(), e);
                    }
                }
            });

            logger.info("UnlockConnectorRequest enviado a chargePointId: {} para connectorId: {}", chargePointId, connectorId);
            return ResponseEntity.ok("UnlockConnectorRequest enviado con éxito a chargePointId: " + chargePointId);

        } catch (Exception e) {
            logger.error("Error procesando la solicitud de UnlockConnector: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al procesar la solicitud de UnlockConnector.");
        }
    }

    @PostMapping("/unlock-connector2")
    public ResponseEntity<String> unlockConnector(@RequestParam String session, @RequestBody UnlockConnectorRequest request) {
        try {
            // Validar parámetros
            if (request == null || request.getConnectorId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El campo 'connectorId' es requerido.");
            }

            if (session == null || session.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El parámetro 'session' es requerido.");
            }

            logger.info("Procesando UnlockConnector para sesión: {} y connectorId: {}", session, request.getConnectorId());

            // Obtener las sesiones activas
            Session ocppSession = getSessionOrThrow(session);
            WebSocketSession webSocketSession = getWebSocketSessionOrThrow(session);

            // Generar messageId único
            String messageId = UUID.randomUUID().toString();

            // Llamar al handler
            webSocketHandler.handleUnlockConnector2(ocppSession, webSocketSession, request, messageId);

            return ResponseEntity.ok("Solicitud de UnlockConnector enviada con éxito para connectorId: " + request.getConnectorId());
        } catch (Exception e) {
            logger.error("Error al enviar UnlockConnector", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
        }
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetChargePoint(@RequestBody Map<String, Object> payload) {
        logger.info("Solicitud de Reset recibida: {}", payload);

        try {
            // Extraer los parámetros
            String chargePointId = (String) payload.get("chargePointId");
            String typeStr = (String) payload.get("type"); // 'Soft' o 'Hard'

            // Validar parámetros obligatorios
            if (chargePointId == null || typeStr == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Faltan parámetros obligatorios: chargePointId o type.");
            }

            // Validar el tipo de reset
            ResetType type;
            try {
                type = ResetType.valueOf(typeStr);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Valor inválido para 'type'. Debe ser 'Soft' o 'Hard'.");
            }

            // Obtener la sesión
            UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionId == null) {
                logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
            }

            Session session = sessionStore.get(sessionId);
            WebSocketSession webSocketSession = webSocketSessionStorage.get(sessionId);

            if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
                logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("La sesión para chargePointId " + chargePointId + " no está activa.");
            }

            // Crear la solicitud ResetRequest
            ResetRequest request = new ResetRequest();
            request.setType(type);

            String messageId = UUID.randomUUID().toString();

            // Enviar la solicitud a la Estación de Carga
            CompletableFuture<Confirmation> futureResponse = session.sendRemoteStartTransaction("Reset", request, messageId);

            // Manejar la respuesta asincrónicamente
            futureResponse.whenComplete((confirmation, error) -> {
                if (error != null) {
                    logger.error("Error al recibir la confirmación para Reset: {}", error.getMessage(), error);
                    try {
                        webSocketHandler.sendError(session, webSocketSession, messageId, "Error en Reset: " + error.getMessage());
                    } catch (IOException ioException) {
                        logger.error("Error enviando error al cliente: {}", ioException.getMessage(), ioException);
                    }
                } else {
                    try {
                        if (confirmation instanceof ResetConfirmation) {
                            ResetConfirmation resetConfirmation = (ResetConfirmation) confirmation;
                            logger.info("ResetConfirmation recibida: {}", resetConfirmation);

                            // Verificar el estado de la confirmación
                            if (resetConfirmation.getStatus() == ResetStatus.Accepted) {
                                logger.info("Reset {} aceptado para chargePointId: {}", typeStr, chargePointId);
                            } else {
                                logger.warn("Reset {} rechazado para chargePointId: {}. Estado: {}", typeStr, chargePointId, resetConfirmation.getStatus());
                            }

                            // Opcionalmente, enviar la respuesta al cliente que inició la solicitud
                        } else {
                            logger.warn("Tipo de confirmación inesperado: {}", confirmation);
                            webSocketHandler.sendError(session, webSocketSession, messageId, "Error: Confirmación inesperada para Reset.");
                        }
                    } catch (IOException e) {
                        logger.error("Error enviando respuesta al cliente: {}", e.getMessage(), e);
                    }
                }
            });

            logger.info("ResetRequest enviado a chargePointId: {} con tipo: {}", chargePointId, typeStr);
            return ResponseEntity.ok("ResetRequest enviado con éxito a chargePointId: " + chargePointId);

        } catch (Exception e) {
            logger.error("Error procesando la solicitud de Reset: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al procesar la solicitud de Reset.");
        }
    }


}
