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
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.RemoteTrigger.Confirmations.TriggerMessageConfirmation;
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

    private static final int MAX_KEYS_LIMIT = 20;

    private static final Logger logger = LoggerFactory.getLogger(OcppController.class);

    private final Map<Long, MeterValuesRequest> latestMeterValuesByConnector = new ConcurrentHashMap<>();

    /** JL **/
    @PostMapping("/iniciar-carga-remota")
    public ResponseEntity<String> iniciarCargaRemotaEnSimulador(
            @RequestParam String chargePointId,
            @RequestBody RemoteStartTransactionRequest request) {
        try {
            if (request == null || request.getIdTag() == null || request.getIdTag().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El campo 'idTag' es requerido.");
            }

            // Obtener el UUID de la sesión asociada al chargePointId
            UUID sessionUUID = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionUUID == null) {
                return ResponseEntity.badRequest().body("ChargePointId no encontrado o no está conectado.");
            }

            // Obtener la WebSocketSession asociada
            WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionUUID.toString());
            if (webSocketSession == null || !webSocketSession.isOpen()) {
                return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
            }

            // Obtener la sesión OCPP
            Session session = webSocketHandler.getSessionById(sessionUUID.toString());
            if (session == null) {
                return ResponseEntity.badRequest().body("Sesión OCPP no encontrada.");
            }

            String messageId = UUID.randomUUID().toString();

            // Llamar al handler
            webSocketHandler.handleRemoteStartTransaction(session, webSocketSession, request, messageId);

            return ResponseEntity.ok("Solicitud de RemoteStartTransaction enviada con éxito para idTag: " + request.getIdTag());
        } catch (Exception e) {
            logger.error("Error al enviar RemoteStartTransaction", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
        }
    }

    /** JL **/
    @PostMapping("/detener-carga-remota")
    public ResponseEntity<?> detenerCargaRemotaEnSimulador(@RequestParam String chargePointId,
                                                           @RequestBody RemoteStopTransactionRequest request) {
        try {
            // 1. Validar parámetros
            if (request == null || request.getTransactionId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El campo 'transactionId' en el request es requerido.");
            }

            logger.info("Solicitud para detener carga remota recibida. CargadorId: {}, TransactionId: {}", chargePointId, request.getTransactionId());

            // 2. Obtener sesiones activas
            UUID sessionUUID = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionUUID == null) {
                return ResponseEntity.badRequest().body("ChargePointId no encontrado o no está conectado.");
            }

            // Obtener la WebSocketSession asociada
            WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionUUID.toString());
            if (webSocketSession == null || !webSocketSession.isOpen()) {
                return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
            }

            // Obtener la sesión OCPP
            Session session = webSocketHandler.getSessionById(sessionUUID.toString());
            if (session == null) {
                return ResponseEntity.badRequest().body("Sesión OCPP no encontrada.");
            }

            String messageId = UUID.randomUUID().toString();


            // 4. Llamar al handler para enviar el RemoteStopTransaction
            webSocketHandler.handleRemoteStopTransaction(session, webSocketSession, request, messageId);

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
     * @return La confirmación GetConfigurationConfirmation recibida del cargador.
     */
    @PostMapping("/getConfiguration")
    public ResponseEntity<?> getConfiguration(
            @RequestParam String chargePointId,
            @RequestBody(required = false) List<String> keys) {
        try {
            // Validación adicional de parámetros
            if (keys != null && keys.size() > MAX_KEYS_LIMIT) {
                return ResponseEntity.badRequest().body("Número de claves excede el límite permitido.");
            }

            // Obtener el UUID de la sesión asociada al chargePointId
            UUID sessionUUID = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionUUID == null) {
                return ResponseEntity.badRequest().body("ChargePointId no encontrado o no está conectado.");
            }

            // Obtener la WebSocketSession asociada
            WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionUUID.toString());
            if (webSocketSession == null || !webSocketSession.isOpen()) {
                return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
            }

            // Obtener la sesión OCPP
            Session session = webSocketHandler.getSessionById(sessionUUID.toString());
            if (session == null) {
                return ResponseEntity.badRequest().body("Sesión OCPP no encontrada.");
            }

            // Enviar la solicitud y obtener el CompletableFuture
            CompletableFuture<GetConfigurationConfirmation> future = webSocketHandler.sendGetConfigurationRequestAsync(session, webSocketSession, keys);

            // Esperar la confirmación con un timeout de 10 segundos
            GetConfigurationConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.debug("GetConfigurationConfirmation recibido para messageId: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("Timeout esperando GetConfigurationConfirmation.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restaurar el estado de interrupción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("El hilo fue interrumpido.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @PostMapping("/trigger-status")
    public ResponseEntity<?> requestStatus(@RequestParam String chargePointId,
                                           @RequestBody TriggerMessageRequest request) {
        UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
        if (sessionId == null) {
            logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
        }

        Session session = WebSocketHandler.sessionStore.get(sessionId);
        WebSocketSession webSocketSession = WebSocketHandler.webSocketSessionStorage.get(sessionId);

        if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
            logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La sesión para chargePointId " + chargePointId + " no está activa.");
        }

        try {
            // Asegurar que el request tenga el requestedMessage correcto
            request.setRequestedMessage(TriggerMessageRequestType.StatusNotification);

            // Enviar la solicitud de TriggerMessage y obtener el CompletableFuture
            CompletableFuture<TriggerMessageConfirmation> future =
                    webSocketHandler.handleStatusNotificationTrigger(session, webSocketSession, request);

            // Esperar la confirmación con un timeout (por ejemplo 10 seg)
            TriggerMessageConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.debug("TriggerMessageConfirmation recibido: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("Timeout esperando TriggerMessageConfirmation.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("El hilo fue interrumpido.");
        } catch (Exception e) {
            logger.error("Error procesando la solicitud TriggerMessage (StatusNotification): {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @PostMapping("/trigger-heartbeat")
    public ResponseEntity<?> triggerHeartbeat(@RequestParam String chargePointId,
                                              @RequestBody TriggerMessageRequest request) {
        // Obtener el UUID de la sesión asociada al chargePointId
        UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
        if (sessionId == null) {
            logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
        }

        Session session = WebSocketHandler.sessionStore.get(sessionId);
        WebSocketSession webSocketSession = WebSocketHandler.webSocketSessionStorage.get(sessionId);

        // Verificar que la sesión y la conexión WebSocket estén activas
        if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
            logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La sesión para chargePointId " + chargePointId + " no está activa.");
        }

        try {
            // Asegurar que el request tenga el requestedMessage correcto
            request.setRequestedMessage(TriggerMessageRequestType.Heartbeat);

            // Enviar la solicitud TriggerMessage de manera asíncrona y obtener un CompletableFuture
            CompletableFuture<TriggerMessageConfirmation> future =
                    webSocketHandler.sendTriggerMessageRequestAsync(session, webSocketSession, request);

            // Esperar la confirmación con un timeout (por ejemplo, 10 segundos)
            TriggerMessageConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.debug("TriggerMessageConfirmation recibido: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                    .body("Timeout esperando TriggerMessageConfirmation.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("El hilo fue interrumpido.");
        } catch (Exception e) {
            logger.error("Error procesando la solicitud TriggerMessage (Heartbeat): {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @PostMapping("/trigger-diagnosticsStatusNotification")
    public ResponseEntity<?> triggerDiagnosticsStatusNotification(@RequestParam String chargePointId,
                                                                  @RequestBody TriggerMessageRequest request) {
        try {
            // Verificar que el punto de carga (chargePointId) esté conectado
            UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionId == null) {
                logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
            }

            // Obtener la sesión OCPP y la sesión WebSocket
            Session session = WebSocketHandler.sessionStore.get(sessionId);
            WebSocketSession webSocketSession = WebSocketHandler.webSocketSessionStorage.get(sessionId);

            if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
                logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("La sesión para chargePointId " + chargePointId + " no está activa.");
            }

            // Asegurar que el request tenga el requestedMessage correcto
            request.setRequestedMessage(TriggerMessageRequestType.DiagnosticsStatusNotification);

            // Enviar la solicitud TriggerMessage de manera asíncrona y obtener el Future de la confirmación
            CompletableFuture<TriggerMessageConfirmation> future = webSocketHandler.handleDiagnosticsStatusNotificationTrigger(session, webSocketSession, request);

            // Esperar la confirmación con timeout de 10 segundos
            TriggerMessageConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            // Responder con la confirmación recibida
            return ResponseEntity.ok(confirmation);

        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                    .body("Timeout esperando TriggerMessageConfirmation.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("El hilo fue interrumpido.");
        } catch (IOException e) {
            logger.error("Error al enviar TriggerMessageRequest", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error enviando solicitud");
        } catch (Exception e) {
            logger.error("Error al procesar la solicitud de TriggerMessage (DiagnosticsStatusNotification): {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @PostMapping("/trigger-bootNotification")
    public ResponseEntity<?> triggerBootNotification(@RequestParam String chargePointId,
                                                     @RequestBody TriggerMessageRequest request) {
        UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
        if (sessionId == null) {
            logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
        }

        Session session = WebSocketHandler.sessionStore.get(sessionId);
        WebSocketSession webSocketSession = WebSocketHandler.webSocketSessionStorage.get(sessionId);

        if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
            logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La sesión para chargePointId " + chargePointId + " no está activa.");
        }

        try {
            // Asegurar que el request tenga el requestedMessage correcto
            request.setRequestedMessage(TriggerMessageRequestType.BootNotification);

            CompletableFuture<TriggerMessageConfirmation> future = webSocketHandler.handleBootNotificationTrigger(session, webSocketSession, request);

            // Esperar la confirmación con un timeout de 10 segundos
            TriggerMessageConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.debug("TriggerMessageConfirmation recibido: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("Timeout esperando TriggerMessageConfirmation.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("El hilo fue interrumpido.");
        } catch (Exception e) {
            logger.error("Error al procesar la solicitud de TriggerMessage (BootNotification): {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @PostMapping("/trigger-firmwareStatusNotification")
    public ResponseEntity<?> triggerFirmwareStatusNotification(@RequestParam String chargePointId,
                                                               @RequestBody TriggerMessageRequest request) {
        // Obtener el UUID de la sesión asociada al chargePointId
        UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
        if (sessionId == null) {
            logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
        }

        Session session = WebSocketHandler.sessionStore.get(sessionId);
        WebSocketSession webSocketSession = WebSocketHandler.webSocketSessionStorage.get(sessionId);

        // Verificar que la sesión y la conexión WebSocket estén activas
        if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
            logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La sesión para chargePointId " + chargePointId + " no está activa.");
        }

        try {
            // Asegurar que el request tenga el requestedMessage correcto antes de enviar
            request.setRequestedMessage(TriggerMessageRequestType.FirmwareStatusNotification);

            // Enviar la solicitud TriggerMessage de manera asíncrona y obtener un CompletableFuture
            CompletableFuture<TriggerMessageConfirmation> future =
                    webSocketHandler.sendTriggerMessageRequestAsync(session, webSocketSession, request);

            // Esperar la confirmación con un timeout de 10 segundos
            TriggerMessageConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.debug("TriggerMessageConfirmation recibido: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                    .body("Timeout esperando TriggerMessageConfirmation.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("El hilo fue interrumpido.");
        } catch (Exception e) {
            logger.error("Error procesando la solicitud TriggerMessage (FirmwareStatusNotification): {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @PostMapping("/trigger-meterValues")
    public ResponseEntity<?> triggerMeterValues(@RequestParam String chargePointId,
                                                @RequestBody TriggerMessageRequest request) {
        // Obtener el UUID de la sesión asociada al chargePointId
        UUID sessionId = webSocketHandler.getSessionIdByChargePointId(chargePointId);
        if (sessionId == null) {
            logger.error("No se encontró una sesión activa para chargePointId: {}", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
        }

        Session session = WebSocketHandler.sessionStore.get(sessionId);
        WebSocketSession webSocketSession = WebSocketHandler.webSocketSessionStorage.get(sessionId);

        // Verificar que la sesión y la conexión WebSocket estén activas
        if (session == null || webSocketSession == null || !webSocketSession.isOpen()) {
            logger.error("La sesión para chargePointId {} no está activa.", chargePointId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("La sesión para chargePointId " + chargePointId + " no está activa.");
        }

        try {
            // Asegurar que el request tenga el requestedMessage correcto
            request.setRequestedMessage(TriggerMessageRequestType.MeterValues);

            // Enviar la solicitud TriggerMessage de manera asíncrona y obtener un CompletableFuture
            CompletableFuture<TriggerMessageConfirmation> future =
                    webSocketHandler.sendTriggerMessageRequestAsync(session, webSocketSession, request);

            // Esperar la confirmación con un timeout de 10 segundos
            TriggerMessageConfirmation confirmation = future.get(10, TimeUnit.SECONDS);

            logger.debug("TriggerMessageConfirmation recibido: {}", confirmation);
            return ResponseEntity.ok(confirmation);

        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                    .body("Timeout esperando TriggerMessageConfirmation.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("El hilo fue interrumpido.");
        } catch (Exception e) {
            logger.error("Error procesando la solicitud TriggerMessage (MeterValues): {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @PostMapping("/unlock-connector")
    public ResponseEntity<?> unlockConnector(@RequestParam String chargePointId,
                                             @RequestBody UnlockConnectorRequest request) {
        try {
            // Validar el campo connectorId del request
            if (!request.validate()) {
                return ResponseEntity.badRequest().body("UnlockConnectorRequest inválido: connectorId debe ser mayor que 0.");
            }

            // Obtener el UUID de la sesión asociada al chargePointId
            UUID sessionUUID = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionUUID == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró una sesión activa para chargePointId: " + chargePointId);
            }

            // Obtener la WebSocketSession asociada
            WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionUUID.toString());
            if (webSocketSession == null || !webSocketSession.isOpen()) {
                return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
            }

            // Obtener la sesión OCPP
            Session session = webSocketHandler.getSessionById(sessionUUID.toString());
            if (session == null) {
                return ResponseEntity.badRequest().body("Sesión OCPP no encontrada.");
            }

            // Generar un messageId único para esta solicitud
            String messageId = UUID.randomUUID().toString();

            // Enviar la solicitud UnlockConnector
            CompletableFuture<UnlockConnectorConfirmation> future = webSocketHandler.sendUnlockConnectorRequestAsync(session,webSocketSession,request);

            // Esperar la confirmación con un timeout de 10 segundos
            Confirmation confirmation = future.get(10, TimeUnit.SECONDS);

            // Verificar que la confirmación sea del tipo UnlockConnectorConfirmation
            if (confirmation instanceof UnlockConnectorConfirmation) {
                UnlockConnectorConfirmation unlockConfirmation = (UnlockConnectorConfirmation) confirmation;
                logger.info("UnlockConnectorConfirmation recibida: {}", unlockConfirmation);

                // Puedes procesar aquí el estado de la confirmación si lo deseas
                return ResponseEntity.ok(unlockConfirmation);
            } else {
                logger.warn("Tipo de confirmación inesperado: {}", confirmation);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error: Confirmación inesperada para UnlockConnector.");
            }

        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("Timeout esperando UnlockConnectorConfirmation.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("El hilo fue interrumpido.");
        } catch (Exception e) {
            logger.error("Error procesando la solicitud de UnlockConnector: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al procesar la solicitud de UnlockConnector.");
        }
    }

    /** JL **/
    @PostMapping("/reset-cargador")
    public ResponseEntity<String> resetCargador(@RequestBody ResetRequest resetRequest, @RequestParam String chargePointId) {
        try {
            // Validar parámetros
            if (resetRequest == null || resetRequest.getType() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El campo 'type' es requerido (Hard o Soft).");
            }


            logger.info("Reseteando Cargador con id: {}. Tipo de reset: {}", chargePointId, resetRequest.getType());

            // Obtener sesiones activas
            UUID sessionUUID = webSocketHandler.getSessionIdByChargePointId(chargePointId);
            if (sessionUUID == null) {
                return ResponseEntity.badRequest().body("ChargePointId no encontrado o no está conectado.");
            }

            // Obtener la WebSocketSession asociada
            WebSocketSession webSocketSession = webSocketHandler.getWebSocketSessionById(sessionUUID.toString());
            if (webSocketSession == null || !webSocketSession.isOpen()) {
                return ResponseEntity.badRequest().body("WebSocketSession no está disponible o cerrada.");
            }

            // Obtener la sesión OCPP
            Session session = webSocketHandler.getSessionById(sessionUUID.toString());
            if (session == null) {
                return ResponseEntity.badRequest().body("Sesión OCPP no encontrada.");
            }

            String messageId = UUID.randomUUID().toString();

            // Llamar al handler
            webSocketHandler.handleReset(session, webSocketSession, resetRequest, messageId);

            return ResponseEntity.ok("Solicitud de Reset enviada con éxito. Tipo: " + resetRequest.getType());

        } catch (Exception e) {
            logger.error("Error al enviar la solicitud de Reset", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
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

}
