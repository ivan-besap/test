package com.eVolGreen.eVolGreen.Configurations.MQ;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Controlador para manejar mensajes entrantes de WebSocket en el endpoint /ocpp.
 */
/**
 * Controlador WebSocket para manejar las conexiones y mensajes del cliente.
 * Escucha en el endpoint configurado y responde a los eventos de mensaje.
 */
@Component
public class WebSocketController extends TextWebSocketHandler {

    /**
     * Maneja la recepción de mensajes desde el cliente.
     *
     * @param session la sesión WebSocket actual
     * @param message el mensaje recibido desde el cliente
     * @throws Exception si ocurre algún error durante el manejo del mensaje
     */
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Lógica para procesar el mensaje recibido
        session.sendMessage(new TextMessage("Mensaje recibido: " + message.getPayload()));
    }

    /**
     * Maneja la apertura de una nueva conexión WebSocket.
     *
     * @param session la sesión WebSocket que se acaba de abrir
     * @throws Exception si ocurre un error al abrir la conexión
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Conexión WebSocket establecida: " + session.getId());
    }

    public boolean checkConnection(WebSocketSession session) {
        return session.isOpen();
    }


}

