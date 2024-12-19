package com.eVolGreen.eVolGreen.Configurations.MQ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.*;

import java.lang.reflect.Type;

public class CustomStompSessionHandler extends StompSessionHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(CustomStompSessionHandler.class);

    private final String clientId;
    private final SessionManager sessionManager;
    private final String wssEndpoint;
    private final int heartbeatInterval;

    public CustomStompSessionHandler(String clientId, SessionManager sessionManager, String wssEndpoint, int heartbeatInterval) {
        this.clientId = clientId;
        this.sessionManager = sessionManager;
        this.wssEndpoint = wssEndpoint;
        this.heartbeatInterval = heartbeatInterval;
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        sessionManager.addSession(clientId, session);
        logger.info("Conectado a Amazon MQ con el cliente: {}", clientId);

        // Suscribirse al destino deseado
        session.subscribe("/topic/Test", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return String.class; // O el tipo de tu mensaje
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                // Maneja el mensaje recibido
                logger.info("Mensaje recibido en {}: {}", clientId, payload);
            }
        });

        // Opcionalmente, puedes enviar un mensaje inicial
        // session.send("/app/yourDestination", tuMensaje);
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        logger.info("Frame recibido: Headers: {}, Payload: {}", headers, payload);
    }

    @Override
    public void handleException(StompSession session, StompCommand command,
                                StompHeaders headers, byte[] payload, Throwable exception) {
        logger.error("Error en la sesión {}: {}", session.getSessionId(), exception.getMessage(), exception);
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        logger.error("Error de transporte en la sesión {}: {}", session.getSessionId(), exception.getMessage(), exception);
        sessionManager.removeSession(clientId);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return String.class; // O el tipo de tu mensaje
    }
}
