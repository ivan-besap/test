package com.eVolGreen.eVolGreen.Models.Ocpp.Controller;

import com.eVolGreen.eVolGreen.Configurations.MQ.WebSocketHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Session;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums.ChargingProfileKindType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums.ChargingProfilePurposeType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums.ResetType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.ChargingProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.ChargingSchedule;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.MeterValue;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.IniciarCargaRemotaRequest;
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
import java.util.concurrent.ConcurrentHashMap;

import static org.apache.activemq.plugin.ForcePersistencyModeBroker.log;


@RestController
@RequestMapping("/api/ocpp")
public class OcppController {

    @Autowired
    private WebSocketHandler webSocketHandler;

    @Autowired
    private UtilService utilService;

    private static final Logger logger = LoggerFactory.getLogger(OcppController.class);

    private final Map<Long, MeterValuesRequest> latestMeterValuesByConnector = new ConcurrentHashMap<>();

    /**
     * Endpoint para iniciar una transacción de carga remota en un punto de carga específico.
     *
     * Este método permite al servidor enviar una solicitud de inicio de transacción a un punto de carga
     * remoto, proporcionando el ID de la sesión, el ID del conector y la etiqueta de identificación (idTag).
     *
     * @param sessionId  El identificador único (UUID) de la sesión WebSocket.
     * @param connectorId  El ID del conector que se utilizará en la transacción.
     * @param idTag  La etiqueta de identificación del usuario autorizando la transacción.
     * @return Un ResponseEntity con un mensaje de éxito o de error, según el resultado.
     */
    @PostMapping("/start-transaction")
    public ResponseEntity<String> startTransaction(
            @RequestParam String sessionId,
            @RequestParam String connectorId,
            @RequestParam String idTag,
            @RequestParam(required = false) Integer chargingProfileId,
            @RequestParam(required = false) Integer stackLevel,
            @RequestParam(required = false) ChargingProfilePurposeType chargingProfilePurpose,
            @RequestParam(required = false) ChargingProfileKindType chargingProfileKind,
            @RequestParam(required = false) ZonedDateTime validFrom,
            @RequestParam(required = false) ZonedDateTime validTo) {
        try {
            // Validar que el connectorId e idTag no sean nulos
            if (connectorId == null || idTag == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El ID de conector y el idTag son requeridos.");
            }

            // Obtener la sesión OCPP
            Session ocppSession = getSessionOrThrow(sessionId);
            WebSocketSession webSocketSession = getWebSocketSessionOrThrow(sessionId);

            // Crear la solicitud RemoteStartTransactionRequest
            RemoteStartTransactionRequest remoteStartRequest = new RemoteStartTransactionRequest(idTag);
            remoteStartRequest.setConnectorId(Integer.parseInt(connectorId));

            // Crear el ChargingProfile si los datos están presentes
            if (chargingProfileId != null && stackLevel != null && chargingProfilePurpose != null && chargingProfileKind != null) {
                ChargingProfile chargingProfile = new ChargingProfile(chargingProfileId, stackLevel, chargingProfilePurpose, chargingProfileKind, new ChargingSchedule());
                chargingProfile.setValidFrom(validFrom);
                chargingProfile.setValidTo(validTo);
                remoteStartRequest.setChargingProfile(chargingProfile);
            }

            // Generar un ID de mensaje único
            String messageId = UUID.randomUUID().toString();

            // Enviar la solicitud a través del WebSocketHandler
            webSocketHandler.handleRemoteStartTransaction(ocppSession, webSocketSession, remoteStartRequest, messageId);

            return ResponseEntity.ok("Transacción remota iniciada con éxito.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en la solicitud: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al iniciar la transacción remota.");
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
    public void iniciarCargaRemotaEnSimulador2(@RequestParam String session,
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
        webSocketHandler.handleRemoteStartTransaction2(ocppSession, webSocketSession, request, messageId);

        // Verificar si la respuesta es exitosa
//        if (!response.getStatusCode().is2xxSuccessful()) {
//            throw new Exception("Error al iniciar la carga remota en el simulador: " + response.getBody());
//        }

        log.info("Carga remota en simulador iniciada con éxito para connectorId: {} y idTag: {}", request.getConnectorId(), request.getIdTag());
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
    public void detenerCargaRemotaEnSimulador(@RequestParam String session,
                                              @RequestBody RemoteStopTransactionRequest request) throws Exception {
//        String url = "http://localhost:8081/api/charge-point/remote-start";

//        UUID sessionId = session.getSessionId();
        Session ocppSession = getSessionOrThrow(session);
        WebSocketSession webSocketSession = getWebSocketSessionOrThrow(session);


        // Configurar los encabezados de la solicitud si son necesarios
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Crear la solicitud HTTP con el payload y los encabezados
        HttpEntity<RemoteStopTransactionRequest> requestEntity = new HttpEntity<>(request, headers);

        String messageId = UUID.randomUUID().toString();

        // Enviar la solicitud POST al simulador
        webSocketHandler.handleRemoteStopTransaction(ocppSession, webSocketSession, request, messageId);

        // Verificar si la respuesta es exitosa
//        if (!response.getStatusCode().is2xxSuccessful()) {
//            throw new Exception("Error al iniciar la carga remota en el simulador: " + response.getBody());
//        }

//        log.info("Carga remota en simulador iniciada con éxito para connectorId: {} y idTag: {}", request.getConnectorId(), request.getIdTag());
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
    public ResponseEntity<String> resetCargador(@RequestParam ResetType resetType, @RequestParam String ocppid) {
        try {
            log.info("Reseteando Cargador: {} en el simulador. Tipo de reset: {}",ocppid,resetType);

            // Llamar al servicio para comunicar al simulador
            utilService.resetCargadorSimulador(resetType, ocppid);

            return ResponseEntity.ok("Cargador Reseteado con éxito");
        } catch (Exception e) {
            log.error("Error Reseteando el Cargador: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Reseteando el cargador");
        }
    }


}
