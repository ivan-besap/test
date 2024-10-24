package com.eVolGreen.eVolGreen.Configurations.MQ;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.ServerCoreProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.JSONServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);

    private final AmazonMQCommunicator amazonMQCommunicator;
    private final ISessionFactory sessionFactory;
    private final Communicator communicator;
    private final JSONServer jsonServer;
    private final ServerCoreProfile coreProfile;
    private final IFeatureRepository featureRepository;
    private final Queue queue;
    private final PromiseFulfiller fulfiller;

    @Autowired
    public WebSocketConfig(AmazonMQCommunicator amazonMQCommunicator,
                           ISessionFactory sessionFactory,
                           Communicator communicator,
                           JSONServer jsonServer,
                           ServerCoreProfile coreProfile,
                           IFeatureRepository featureRepository,
                           Queue queue,
                           PromiseFulfiller fulfiller) {
        this.amazonMQCommunicator = amazonMQCommunicator;
        this.sessionFactory = sessionFactory;
        this.communicator = communicator;
        this.jsonServer = jsonServer;
        this.coreProfile = coreProfile;
        this.featureRepository = featureRepository;
        this.queue = queue;
        this.fulfiller = fulfiller;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(createWebSocketHandler(), "/ocpp/{cid}")
                .setAllowedOriginPatterns("*")
                .setHandshakeHandler(createHandshakeHandler())
                .addInterceptors(createHandshakeInterceptor());
    }

    private WebSocketHandler createWebSocketHandler() {
        return new WebSocketHandler(sessionFactory, communicator, jsonServer, coreProfile, amazonMQCommunicator);
    }

    private DefaultHandshakeHandler createHandshakeHandler() {
        return new DefaultHandshakeHandler() {
            protected String determineSubProtocol(List<String> requestedProtocols, WebSocketHandler wsHandler) {
                logger.info("Determinando subprotocolo. Protocolos solicitados: {}", (Throwable) requestedProtocols);
                return requestedProtocols.contains("ocpp1.6") ? "ocpp1.6" : null;
            }
        };
    }

    private HandshakeInterceptor createHandshakeInterceptor() {
        return new HandshakeInterceptor() {
            @Override
            public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, org.springframework.web.socket.WebSocketHandler wsHandler, Map<String, Object> attributes) {
                String protocol = request.getHeaders().getFirst("Sec-WebSocket-Protocol");
                ProtocolVersion expectedProtocol = featureRepository.getProtocolVersion();
                String expectedProtocolName = expectedProtocol != null ? expectedProtocol.getSubProtocolName() : null;

                logger.info("Subprotocolo esperado: '{}', Subprotocolo recibido: '{}'", expectedProtocolName, protocol);
                if (protocol != null && protocol.equals(expectedProtocolName)) {
                    response.getHeaders().add("Sec-WebSocket-Protocol", protocol);
                    attributes.put("subProtocol", protocol);
                    logger.info("Subprotocolo validado exitosamente.");
                    return true;
                } else {
                    logger.warn("Subprotocolo no soportado o no definido: {}", protocol);
                    return false;
                }
            }

            @Override
            public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, org.springframework.web.socket.WebSocketHandler wsHandler, Exception exception) {
                // Log adicional si es necesario
            }
        };
    }
}