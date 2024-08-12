package com.eVolGreen.eVolGreen.Configurations;

import org.springframework.web.socket.*;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private WebSocketSession session;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // La conexión WebSocket se ha establecido
        this.session = session;
        System.out.println("WebSocket connection established: " + session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Received message: " + message.getPayload());
        session.sendMessage(new TextMessage("Message received"));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // Manejar errores de transporte
        System.out.println("WebSocket transport error: " + exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // La conexión WebSocket se ha cerrado
        System.out.println("Connection closed: " + session + ", Status: " + status);
        this.session = null;
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void sendMessage(String messageString) {
        if (this.session != null && this.session.isOpen()) {
            try {
                this.session.sendMessage(new TextMessage(messageString));
                System.out.println("Mensaje enviado a través de WebSocket: " + messageString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
