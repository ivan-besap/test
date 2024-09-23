package com.eVolGreen.eVolGreen.Models.Ocpp;

import com.eVolGreen.eVolGreen.Configurations.MQ.WebSocketHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

/**
 * Clase encargada de gestionar las solicitudes y respuestas del protocolo OCPP (Open Charge Point Protocol),
 * enviando mensajes a través de WebSocket y Amazon MQ.
 * <p>
 * Esta clase actúa como un middleware entre las estaciones de carga y el sistema central,
 * gestionando diferentes solicitudes como BootNotification, Heartbeat, autorización de transacciones, etc.
 *
 * <b>Mensajes enviados:</b>
 * <ul>
 *     <li>WebSocket: Los mensajes de solicitud y confirmación se envían a través de WebSocket.</li>
 *     <li>Amazon MQ: Los mismos mensajes son enviados a la cola "ocpp_queue".</li>
 * </ul>
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     OcppHandler handler = new OcppHandler(webSocketHandler);
 *     handler.sendBootNotification();
 *     handler.handleAuthorizeRequest(new AuthorizeRequest("TAG123"));
 * </pre>
 */
@Component
public class OcppHandler {

    @Autowired
    private JmsTemplate jmsTemplate;

    private final WebSocketHandler webSocketHandler;

    /**
     * Constructor que inyecta el manejador WebSocket para gestionar las conexiones y mensajes.
     *
     * @param webSocketHandler El manejador WebSocket usado para enviar mensajes.
     */
    @Autowired
    public OcppHandler(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    /**
     * Envía una solicitud BootNotification al sistema central a través de WebSocket y Amazon MQ.
     * Este mensaje informa sobre el estado y configuración de la estación de carga.
     */
    public void sendBootNotification() {
        BootNotificationRequest request = new BootNotificationRequest();
        request.setChargePointModel("Model S");
        request.setChargePointVendor("Tesla");

        sendToWebSocketAndQueue(request, "BootNotification");
    }

    /**
     * Envía una solicitud Heartbeat, que notifica al sistema central que la estación de carga sigue activa.
     */
    public void sendHeartbeat() {
        HeartbeatRequest request = new HeartbeatRequest();
        sendToWebSocketAndQueue(request, "Heartbeat");
    }

    /**
     * Maneja una solicitud de autorización (AuthorizeRequest) enviada por una estación de carga.
     * Este mensaje autoriza un idTag para iniciar una transacción.
     *
     * @param request La solicitud de autorización con el idTag.
     */
    public void handleAuthorizeRequest(AuthorizeRequest request) {
        AuthorizeConfirmation confirmation = new AuthorizeConfirmation();
        confirmation.setIdTagInfo(new IdTagInfo(AuthorizationStatus.Accepted, ZonedDateTime.now().plusDays(1)));
        sendMessage(confirmation);
    }

    /**
     * Maneja una solicitud de inicio de transacción (StartTransactionRequest) y envía una confirmación.
     *
     * @param request La solicitud de inicio de transacción.
     */
    public void handleStartTransaction(StartTransactionRequest request) {
        StartTransactionConfirmation confirmation = new StartTransactionConfirmation();
        confirmation.setTransactionId(12345); // Ejemplo: ID de transacción
        confirmation.setIdTagInfo(new IdTagInfo(AuthorizationStatus.Accepted));
        sendMessage(confirmation);
    }

    /**
     * Maneja una solicitud de finalización de transacción (StopTransactionRequest) y envía una confirmación.
     *
     * @param request La solicitud para finalizar la transacción.
     */
    public void handleStopTransaction(StopTransactionRequest request) {
        StopTransactionConfirmation confirmation = new StopTransactionConfirmation();
        confirmation.setIdTagInfo(new IdTagInfo(AuthorizationStatus.Accepted));
        sendMessage(confirmation);
    }

    /**
     * Maneja solicitudes de valores de medidor (MeterValuesRequest), típicamente enviadas durante una transacción.
     *
     * @param request La solicitud con los valores del medidor.
     */
    public void handleMeterValues(MeterValuesRequest request) {
        sendMessage("MeterValues confirmation");
    }

    /**
     * Maneja solicitudes de notificación de estado (StatusNotificationRequest).
     * Este mensaje informa sobre el estado del conector en la estación de carga.
     *
     * @param request La solicitud de notificación de estado.
     */
    public void handleStatusNotification(StatusNotificationRequest request) {
        sendMessage("StatusNotification confirmation");
    }

    /**
     * Maneja solicitudes de transferencia de datos (DataTransferRequest), permitiendo la transferencia de información personalizada.
     *
     * @param request La solicitud de transferencia de datos.
     */
    public void handleDataTransfer(DataTransferRequest request) {
        DataTransferConfirmation confirmation = new DataTransferConfirmation();
        confirmation.setStatus(DataTransferStatus.Accepted);
        sendMessage(confirmation);
    }

    /**
     * Envía un mensaje de confirmación a través de WebSocket y Amazon MQ.
     *
     * @param message El mensaje de confirmación a enviar.
     */
    private void sendMessage(Object message) {
        sendToWebSocketAndQueue(message, "OcppMessage");
    }

    /**
     * Envía un mensaje tanto a WebSocket como a Amazon MQ, asegurando la entrega en ambos canales.
     *
     * @param message     El contenido del mensaje.
     * @param messageType El tipo de mensaje que se está enviando (ej: BootNotification, Heartbeat, etc.).
     */
    private void sendToWebSocketAndQueue(Object message, String messageType) {
        String messageString = message.toString();
        webSocketHandler.sendMessageToAll(messageString);
        jmsTemplate.convertAndSend("ocpp_queue", messageString);
        System.out.println(messageType + " enviado a través de WebSocket y MQ: " + messageString);
    }
}
