package com.eVolGreen.eVolGreen.Configurations.MQ;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.util.List;

@Configuration
public class HandshakeConfig {

    @Bean
    public HandshakeHandler handshakeHandler() {
        return new DefaultHandshakeHandler() {
            protected String determineSubProtocol(List<String> requestedProtocols, WebSocketHandler wsHandler) {
                String expectedProtocol = "OCPP1_6"; // Subprotocolo esperado
                if (requestedProtocols.contains(expectedProtocol)) {
                    return expectedProtocol;
                }
                return null; // Devolver null si el subprotocolo no coincide
            }
        };
    }
}
