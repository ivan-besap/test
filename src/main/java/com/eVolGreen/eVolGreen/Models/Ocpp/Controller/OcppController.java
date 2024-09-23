package com.eVolGreen.eVolGreen.Models.Ocpp.Controller;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.*;


import com.eVolGreen.eVolGreen.Models.Ocpp.OcppClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * Controlador REST para manejar solicitudes relacionadas con el protocolo OCPP (Open Charge Point Protocol).
 * Este controlador permite la interacción con el servidor OCPP mediante un cliente JSON.
 *
 * <p>Valida automáticamente las solicitudes entrantes y gestiona excepciones si los datos no cumplen con las reglas de validación.</p>
 *
 * <p><b>Ejemplos de Uso:</b></p>
 * <pre>
 *     curl -X POST http://localhost:8080/api/ocpp/authorize -d '{ "idTag": "B4C3" }' -H "Content-Type: application/json"
 *     curl -X GET http://localhost:8080/api/ocpp/sendHeartbeat
 * </pre>
 */
@RestController
@RequestMapping("/api/ocpp")
public class OcppController {

    private final OcppClientService ocppClientService;

    @Autowired
    public OcppController(OcppClientService ocppClientService) {
        this.ocppClientService = ocppClientService;
    }

    /**
     * Conecta el cliente OCPP al servidor especificado.
     *
     * <p>Permite al cliente conectarse a un servidor OCPP a través de la URL proporcionada. Debe realizarse antes de enviar cualquier solicitud.</p>
     *
     * @param url La URL del servidor OCPP.
     * @return Respuesta con mensaje de éxito o error.
     *
     * <p><b>Ejemplo de Uso:</b></p>
     * <pre>
     *     curl -X POST http://localhost:8080/api/ocpp/connect?url=wss://server-ocpp.com
     * </pre>
     */
    @PostMapping("/connect")
    public ResponseEntity<String> connectToServer(@RequestParam String url) {
        try {
            ocppClientService.connectToOcppServer(url);
            return ResponseEntity.ok("Conectado al servidor OCPP: " + url);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al conectar: " + e.getMessage());
        }
    }

    /**
     * Envía una solicitud de BootNotification al servidor OCPP.
     *
     * <p>Informa sobre el estado y la configuración de la estación de carga.</p>
     *
     * @return Respuesta con la confirmación de BootNotification.
     *
     * <p><b>Ejemplo de Uso:</b></p>
     * <pre>
     *     curl -X GET http://localhost:8080/api/ocpp/bootNotification
     * </pre>
     */
    @GetMapping("/bootNotification")
    public ResponseEntity<Confirmation> sendBootNotification() {
        try {
            Confirmation confirmation = ocppClientService.sendBootNotification();
            return ResponseEntity.ok(confirmation);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     * Envía una solicitud de autorización al servidor OCPP.
     *
     * <p>Autoriza el uso del idTag proporcionado.</p>
     *
     * @param request La solicitud de autorización enviada por la estación de carga.
     * @return Respuesta con la confirmación de autorización.
     *
     * <p><b>Ejemplo de Uso:</b></p>
     * <pre>
     *     curl -X POST http://localhost:8080/api/ocpp/authorize -d '{ "idTag": "ABC123" }' -H "Content-Type: application/json"
     * </pre>
     */
    @PostMapping("/authorize")
    public ResponseEntity<Confirmation> authorize(@Valid @RequestBody AuthorizeRequest request) {
        try {
            Confirmation confirmation = ocppClientService.sendAuthorizeRequest(request);
            return ResponseEntity.ok(confirmation);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     * Envía una solicitud de Heartbeat al servidor OCPP.
     *
     * <p>Notifica al sistema central que la estación de carga sigue activa.</p>
     *
     * @return Respuesta con la confirmación de Heartbeat.
     *
     * <p><b>Ejemplo de Uso:</b></p>
     * <pre>
     *     curl -X GET http://localhost:8080/api/ocpp/heartbeat
     * </pre>
     */
    @GetMapping("/heartbeat")
    public ResponseEntity<Confirmation> sendHeartbeat() {
        try {
            Confirmation confirmation = ocppClientService.sendHeartbeat();
            return ResponseEntity.ok(confirmation);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     * Envía una solicitud de inicio de transacción al servidor OCPP.
     *
     * <p>Inicia una transacción con el sistema central.</p>
     *
     * @param request La solicitud para iniciar la transacción.
     * @return Respuesta con la confirmación de la transacción.
     *
     * <p><b>Ejemplo de Uso:</b></p>
     * <pre>
     *     curl -X POST http://localhost:8080/api/ocpp/startTransaction -d '{ "connectorId": 1, "idTag": "ABC123" }' -H "Content-Type: application/json"
     * </pre>
     */
    @PostMapping("/startTransaction")
    public ResponseEntity<Confirmation> startTransaction(@RequestBody StartTransactionRequest request) {
        try {
            Confirmation confirmation = ocppClientService.sendStartTransaction(request);
            return ResponseEntity.ok(confirmation);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     * Envía una solicitud para detener una transacción al servidor OCPP.
     *
     * <p>Finaliza la transacción con el sistema central.</p>
     *
     * @param request La solicitud para detener la transacción.
     * @return Respuesta con la confirmación de StopTransaction.
     *
     * <p><b>Ejemplo de Uso:</b></p>
     * <pre>
     *     curl -X POST http://localhost:8080/api/ocpp/stopTransaction -d '{ "transactionId": 12345 }' -H "Content-Type: application/json"
     * </pre>
     */
    @PostMapping("/stopTransaction")
    public ResponseEntity<Confirmation> stopTransaction(@RequestBody StopTransactionRequest request) {
        try {
            Confirmation confirmation = ocppClientService.sendStopTransaction(request);
            return ResponseEntity.ok(confirmation);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     * Envía una solicitud MeterValues al servidor OCPP.
     *
     * <p>Envía los valores medidos por el medidor de energía al sistema central.</p>
     *
     * @param request La solicitud de valores del medidor.
     * @return Respuesta con la confirmación de MeterValues.
     *
     * <p><b>Ejemplo de Uso:</b></p>
     * <pre>
     *     curl -X POST http://localhost:8080/api/ocpp/meterValues -d '{ "connectorId": 1, "transactionId": 12345, "values": [...] }' -H "Content-Type: application/json"
     * </pre>
     */
    @PostMapping("/meterValues")
    public ResponseEntity<Confirmation> sendMeterValues(@RequestBody MeterValuesRequest request) {
        try {
            Confirmation confirmation = ocppClientService.sendMeterValues(request);
            return ResponseEntity.ok(confirmation);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     * Envía una solicitud DataTransfer al servidor OCPP.
     *
     * <p>Solicita transferir datos arbitrarios al sistema central.</p>
     *
     * @param request La solicitud de transferencia de datos.
     * @return Respuesta con la confirmación de DataTransfer.
     *
     * <p><b>Ejemplo de Uso:</b></p>
     * <pre>
     *     curl -X POST http://localhost:8080/api/ocpp/dataTransfer -d '{ "vendorId": "vendorX", "messageId": "message1", "data": "sampleData" }' -H "Content-Type: application/json"
     * </pre>
     */
    @PostMapping("/dataTransfer")
    public ResponseEntity<Confirmation> dataTransfer(@RequestBody DataTransferRequest request) {
        try {
            Confirmation confirmation = ocppClientService.sendDataTransfer(request);
            return ResponseEntity.ok(confirmation);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
