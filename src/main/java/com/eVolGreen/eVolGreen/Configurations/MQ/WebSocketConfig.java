package com.eVolGreen.eVolGreen.Configurations.MQ;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Queue;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.ServerCoreProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.JSONServer;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.*;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.io.IOException;
import java.util.*;

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
    private final Map<UUID, Session> activeSessions = new HashMap<>();

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

    private WebSocketHandler createWebSocketHandler() {
        return new WebSocketHandler(sessionFactory, communicator, jsonServer, coreProfile, amazonMQCommunicator) {
            @Override
            public void afterConnectionEstablished(WebSocketSession session) throws IOException {
                String acceptedProtocol = (String) session.getAttributes().get("subProtocol");
                logger.debug("Subprotocolo aceptado en la sesión (después de handshake): {}", acceptedProtocol);

                if ("ocpp1.6".equals(acceptedProtocol)) {
                    UUID sessionId = UUID.randomUUID();
                    Session ocppSession = new Session(communicator, queue, fulfiller, featureRepository);
                    activeSessions.put(sessionId, ocppSession);
                    amazonMQCommunicator.addSession(sessionId, session);
                    logger.info("Sesión WebSocket registrada exitosamente con ID: {} y subprotocolo: {}", sessionId, acceptedProtocol);
                } else {
                    logger.warn("Subprotocolo no soportado o no definido: {}. Cerrando la sesión: {}", acceptedProtocol == null ? "Ninguno" : acceptedProtocol, session.getId());
                    session.close(CloseStatus.PROTOCOL_ERROR);
                    logger.info("Sesión cerrada debido a subprotocolo no soportado.");
                }
            }

            /**
             * Maneja los mensajes entrantes de WebSocket, principalmente provenientes del simulador, y los procesa según los tipos de mensajes OCPP.
             * <p>
             * Este método verifica si el mensaje recibido es de tipo {@link TextMessage}. En caso afirmativo, registra el mensaje,
             * lo analiza como un array JSON y luego extrae y procesa la acción en función del protocolo OCPP.
             * </p>
             *
             * @param session la sesión WebSocket a través de la cual se recibió el mensaje
             * @param message el mensaje entrante de WebSocket a procesar
             * @throws Exception si ocurre un error durante el procesamiento del mensaje
             */
            @Override
            public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
                logger.debug("Mensaje recibido en la sesión {}: {}", session.getId(), message.getPayload());

                // Verificar si el mensaje es de tipo TextMessage
                if (message instanceof TextMessage) {
                    String payload = ((TextMessage) message).getPayload();

                    // Log para ver el contenido exacto del mensaje Heartbeat recibido
                    logger.info("Mensaje Heartbeat recibido del simulador: {}", payload);

                    // Intenta parsear el mensaje como un array JSON primero
                    JsonNode messageJson = objectMapper.readTree(payload);

                    // Verificar si es un array
                    if (messageJson.isArray()) {
                        JsonNode messageTypeNode = messageJson.get(0); // Tipo de mensaje
                        JsonNode messageIdNode = messageJson.get(1); // ID del mensaje
                        JsonNode actionNode = messageJson.get(2); // Acción
                        JsonNode requestPayload = messageJson.get(3); // Payload

                        if (messageTypeNode == null || messageIdNode == null || actionNode == null) {
                            logger.warn("Formato de mensaje OCPP inválido. Payload: {}", payload);
                            sendError(session, "unknown", "Invalid OCPP message format: 'messageType', 'messageId' or 'action' missing");
                            return;
                        }

                        // Extraer tipo de mensaje y ID, y continuar con el procesamiento
                        String messageType = messageTypeNode.asText();
                        String messageId = messageIdNode.asText();
                        String action = actionNode.asText(); // Acción (como "Heartbeat", "StatusNotification", etc.)

                        switch (action) {
                            case "Heartbeat":
                                handleHeartbeat(session, requestPayload, messageId);
                                break;
                            case "StatusNotification":
                                handleStatusNotification(session, requestPayload, messageId);
                                break;
                            case "Authorize":
                                handleAuthorize(session, requestPayload, messageId);
                                break;
                            case "StartTransaction":
                                handleStartTransaction(session, requestPayload, messageId);
                                break;
                            case "MeterValues":
                                handleMeterValues(session, requestPayload, messageId);
                                break;
                            case "StopTransaction":
                                handleStopTransaction(session, requestPayload, messageId);
                                break;
                            case "BootNotification":
                                handleBootNotification(session, requestPayload, messageId);
                                break;
                            case "RemoteStartTransaction":
                                handleRemoteStartTransaction(session, requestPayload, messageId);
                                break;
                            case "RemoteStopTransaction":
                                handleRemoteStopTransaction(session, requestPayload, messageId);
                                break;
                            case "StatusNotificationPreparing":
                                handlePreparing(session, requestPayload, messageId);
                                break;
                            case "StatusNotificationCharging":
                                handleCharging(session, requestPayload, messageId);
                                break;
                            case "ChangeAvailability":
                                handleChangeAvailability(session, requestPayload, messageId);
                                break;
                            case "GetConfiguration":
                                handleGetConfiguration(session, requestPayload, messageId);
                                break;
                            default:
                                logger.warn("Tipo de mensaje OCPP desconocido recibido: {}", action);
                                sendError(session, messageId, "Unknown OCPP message action: " + action);
                        }
                    } else {
                        logger.error("Formato de mensaje no soportado. Se esperaba un array JSON.");
                        sendError(session, "unknown", "Unsupported message format: expected JSON array.");
                    }
                } else {
                    logger.error("Tipo de mensaje no soportado: {}", message.getClass().getName());
                    sendError(session, "unknown", "Unsupported message type: " + message.getClass().getName());
                }
            }


            @Override
            public void handleTransportError(WebSocketSession session, Throwable exception) {
                logger.error("Error en la sesión {}: {}", session.getId(), exception.getMessage(), exception);
                try {
                    session.close(CloseStatus.SERVER_ERROR);
                    logger.info("Sesión cerrada debido a error de transporte.");
                } catch (IOException e) {
                    logger.error("Error al cerrar la sesión después de un error de transporte: {}", e.getMessage());
                }
            }

            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
                logger.info("Conexión WebSocket cerrada: ID {}, Status: {}", session.getId(), closeStatus);
                // Remover la sesión del mapa al cerrar
                activeSessions.remove(UUID.fromString(session.getId()));
            }

            @Override
            public boolean supportsPartialMessages() {
                return false;
            }
        };
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(createWebSocketHandler(), "/ocpp/{cid}")
                .setAllowedOriginPatterns("*")
                .setHandshakeHandler(new DefaultHandshakeHandler() {
                    protected String determineSubProtocol(List<String> requestedProtocols, WebSocketHandler wsHandler) {
                        logger.info("Determinando subprotocolo. Protocolos solicitados: " + requestedProtocols);
                        return requestedProtocols.contains("ocpp1.6") ? "ocpp1.6" : null;
                    }
                })
                .addInterceptors(new HandshakeInterceptor() {
                    @Override
                    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, org.springframework.web.socket.WebSocketHandler wsHandler, Map<String, Object> attributes) {
                        String protocol = request.getHeaders().getFirst("Sec-WebSocket-Protocol");
                        ProtocolVersion expectedProtocol = featureRepository.getProtocolVersion();
                        String expectedProtocolName = expectedProtocol != null ? expectedProtocol.getSubProtocolName() : null;

                        logger.info("Subprotocolo esperado: '{}', Subprotocolo recibido: '{}'", expectedProtocolName, protocol);
                        if (protocol != null && protocol.equals(expectedProtocolName)) {
                            response.getHeaders().add("Sec-WebSocket-Protocol", protocol);
                            attributes.put("subProtocol", protocol);  // Persistir el subprotocolo en los atributos de la sesión
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
                });
    }
}
