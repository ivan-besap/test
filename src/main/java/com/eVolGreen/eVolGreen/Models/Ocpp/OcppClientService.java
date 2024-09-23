package com.eVolGreen.eVolGreen.Models.Ocpp;

import com.eVolGreen.eVolGreen.Configurations.MQ.WebSocketHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.OccurenceConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.UnsupportedFeatureException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.ClientEvents;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.ClientCoreProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Servicio que maneja la comunicación entre el cliente OCPP y el servidor OCPP,
 * incluyendo el reenvío de mensajes a través de WebSocket y Amazon MQ.
 */
@Service
public class OcppClientService {

    private final JSONClient jsonClient;
    private final WebSocketHandler webSocketHandler;
    private final JmsTemplate jmsTemplate;

    @Autowired
    public OcppClientService(ClientCoreProfile coreProfile, WebSocketHandler webSocketHandler, JmsTemplate jmsTemplate) {
        this.jsonClient = new JSONClient(coreProfile);
        this.webSocketHandler = webSocketHandler;
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Conecta el cliente OCPP al servidor especificado por la URL.
     *
     * @param url URL del servidor OCPP.
     */
    public void connectToOcppServer(String url) {
        jsonClient.connect(url, new ClientEvents() {
            @Override
            public void connectionOpened() {
                logAndSendMessage("Conexión abierta con el servidor OCPP.");
            }

            @Override
            public void connectionClosed() {
                logAndSendMessage("Conexión cerrada con el servidor OCPP.");
            }

            @Override
            public void connected() {
                logAndSendMessage("Conectado al servidor OCPP.");
            }

            @Override
            public void disconnected() {
                logAndSendMessage("Desconectado del servidor OCPP.");
            }
        });
    }

    /**
     * Envía una solicitud BootNotification al servidor OCPP y reenvía el mensaje por WebSocket y MQ.
     */
    public Confirmation sendBootNotification() throws OccurenceConstraintException, UnsupportedFeatureException {
        BootNotificationRequest request = new BootNotificationRequest();
        return sendRequestToServerAndLog(request, "BootNotification enviado al servidor OCPP");
    }

    /**
     * Envía una solicitud de autorización al servidor OCPP y reenvía el mensaje.
     */
    public Confirmation sendAuthorizeRequest(AuthorizeRequest request) throws OccurenceConstraintException, UnsupportedFeatureException {
        return sendRequestToServerAndLog(request, "AuthorizeRequest enviado al servidor OCPP");
    }

    /**
     * Envía una solicitud Heartbeat al servidor OCPP y reenvía el mensaje.
     */
    public Confirmation sendHeartbeat() throws OccurenceConstraintException, UnsupportedFeatureException {
        HeartbeatRequest request = new HeartbeatRequest();
        return sendRequestToServerAndLog(request, "Heartbeat enviado al servidor OCPP");
    }

    /**
     * Envía una solicitud StartTransaction al servidor OCPP y reenvía el mensaje.
     */
    public Confirmation sendStartTransaction(StartTransactionRequest request) throws OccurenceConstraintException, UnsupportedFeatureException {
        return sendRequestToServerAndLog(request, "StartTransaction enviado al servidor OCPP");
    }

    /**
     * Envía una solicitud StopTransaction al servidor OCPP y reenvía el mensaje.
     */
    public Confirmation sendStopTransaction(StopTransactionRequest request) throws OccurenceConstraintException, UnsupportedFeatureException {
        return sendRequestToServerAndLog(request, "StopTransaction enviado al servidor OCPP");
    }

    /**
     * Envía una solicitud MeterValues al servidor OCPP y reenvía el mensaje.
     */
    public Confirmation sendMeterValues(MeterValuesRequest request) throws OccurenceConstraintException, UnsupportedFeatureException {
        return sendRequestToServerAndLog(request, "MeterValues enviado al servidor OCPP");
    }

    /**
     * Envía una solicitud DataTransfer al servidor OCPP y reenvía el mensaje.
     */
    public Confirmation sendDataTransfer(DataTransferRequest request) throws OccurenceConstraintException, UnsupportedFeatureException {
        return sendRequestToServerAndLog(request, "DataTransfer enviado al servidor OCPP");
    }

    /**
     * Método genérico para enviar solicitudes al servidor OCPP y reenviar el mensaje.
     */
    private Confirmation sendRequestToServerAndLog(Request request, String logMessage) throws OccurenceConstraintException, UnsupportedFeatureException {
        Confirmation confirmation = jsonClient.send(request).toCompletableFuture().join();
        logAndSendMessage(logMessage + ": " + confirmation);
        return confirmation;
    }

    /**
     * Método para registrar y reenviar mensajes tanto a WebSocket como a Amazon MQ.
     */
    private void logAndSendMessage(String message) {
        System.out.println(message); // Log en la consola
        webSocketHandler.sendMessageToAll(message); // Reenviar a WebSocket
        jmsTemplate.convertAndSend("ocpp_queue", message); // Reenviar a Amazon MQ
    }
}
