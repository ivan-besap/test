package com.eVolGreen.eVolGreen.Configurations.Ocpp;

import com.eVolGreen.eVolGreen.Configurations.Ocpp.Models.*;
import com.eVolGreen.eVolGreen.Configurations.WebSocketHandler;


import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class OcppHandler {

    private final WebSocketHandler webSocketHandler;
    private boolean riggedToFail;

    public OcppHandler(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    public void sendBootNotification() {
        BootNotificationRequest request = new BootNotificationRequest();
        request.setChargePointModel("Model S");
        request.setChargePointVendor("Tesla");

        // Aquí envías el mensaje usando WebSocket
        sendMessage(request);
    }

    public void handleAuthorizeRequest(AuthorizeRequest request) {
        AuthorizeConfirmation confirmation = new AuthorizeConfirmation();
        // Configura la respuesta según sea necesario
        confirmation.setIdTagInfo(new IdTagInfo(AuthorizationStatus.Accepted, ZonedDateTime.now().plusDays(1)));
        sendMessage(confirmation);
    }

    public void handleHeartbeatRequest(HeartbeatRequest request) {
        HeartbeatConfirmation confirmation = new HeartbeatConfirmation();
        confirmation.setCurrentTime(ZonedDateTime.now());
        sendMessage(confirmation);
    }

    private void sendMessage(Object message) {
        String messageString = message.toString(); // Asegúrate de que toString() devuelve una representación válida para enviar
        webSocketHandler.sendMessage(messageString);
    }

    // Método para simular fallos en la respuesta, como en DummyHandlers
    private <T> T failurePoint(T response) {
        if (riggedToFail) {
            riggedToFail = false;
            throw new RuntimeException("Simulated failure");
        }
        return response;
    }

    public void setRiggedToFail(boolean riggedToFail) {
        this.riggedToFail = riggedToFail;
    }

    public boolean isRiggedToFail() {
        return riggedToFail;
    }

    public void sendHeartbeat() {
        HeartbeatRequest request = new HeartbeatRequest();
        sendMessage(failurePoint(request));

    }
}
