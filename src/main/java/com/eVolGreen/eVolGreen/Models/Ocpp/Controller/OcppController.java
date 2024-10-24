package com.eVolGreen.eVolGreen.Models.Ocpp.Controller;

import com.eVolGreen.eVolGreen.Configurations.MQ.WebSocketHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Session;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.Enums.ChargingProfileKindType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.Enums.ChargingProfilePurposeType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.HeartbeatRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.RemoteStartTransactionRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.StartTransactionRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.Utils.ChargingProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.Utils.ChargingSchedule;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.Utils.MeterValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;



@RestController
@RequestMapping("/api/ocpp")
public class OcppController {

    @Autowired
    private WebSocketHandler webSocketHandler;

    private static final Logger logger = LoggerFactory.getLogger(OcppController.class);

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

}
