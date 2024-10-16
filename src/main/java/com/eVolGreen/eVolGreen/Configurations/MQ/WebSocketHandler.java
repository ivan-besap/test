package com.eVolGreen.eVolGreen.Configurations.MQ;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.NotConnectedException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.OccurenceConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.UnsupportedFeatureException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.SessionInformation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Handler.DefaultClientCoreEventHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Handler.ServerCoreEventHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.ClientRemoteTriggerProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.ServerCoreProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.ServerRemoteTriggerProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.JSONServer;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Enums.AuthorizationStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Enums.DataTransferStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Enums.RegistrationStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Utils.IdTagInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.Enums.ChargePointStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.RemoteTrigger.Request.TriggerMessageRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


import java.io.EOFException;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * {@code WebSocketHandler} maneja las conexiones WebSocket, gestionando el ciclo de vida de las sesiones
 * y redireccionando solicitudes como BootNotification y Authorize al {@link JSONServer}.
 */
@AllArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);
    private static final Map<UUID, ISession> sessionStore = new ConcurrentHashMap<>();
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    private final ISessionFactory sessionFactory;
    private final Communicator communicator;
    public final ObjectMapper objectMapper;
    private final JSONServer jsonServer;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private AuthorizationStatus status;
    private ClientRemoteTriggerProfile clientRemoteTriggerProfile;
    private int reconnectAttempts = 0;
    private static final int MAX_RECONNECT_ATTEMPTS = 3;
    private ServerRemoteTriggerProfile serverRemoteTriggerProfile;
    private DefaultClientCoreEventHandler clientCoreEventHandler;
    private AmazonMQCommunicator amazonMQCommunicator;


    /**
     * Maneja las conexiones WebSocket, gestionando el ciclo de vida de las sesiones y
     * redirigiendo solicitudes específicas de OCPP al {@link JSONServer} y al manejador de eventos de Amazon MQ.
     * <p>
     * Este constructor inicializa y configura los perfiles y eventos necesarios, incluyendo el perfil principal del
     * servidor y los eventos para la integración con Amazon MQ.
     * </p>
     *
     * @param sessionFactory          Fábrica de sesiones para crear instancias de {@link ISession}.
     * @param communicator            Comunicador para transmisión de mensajes.
     * @param jsonServer              Servidor JSON que maneja las solicitudes de OCPP.
     * @param coreProfile             Perfil principal de OCPP para el servidor.
     * @param amazonMQCommunicator    Comunicador para integración con Amazon MQ.
     */
    public WebSocketHandler(ISessionFactory sessionFactory, Communicator communicator,
                            JSONServer jsonServer, ServerCoreProfile coreProfile,
                            AmazonMQCommunicator amazonMQCommunicator) {

        this.sessionFactory = sessionFactory;
        this.communicator = communicator;
        this.jsonServer = jsonServer;
        this.amazonMQCommunicator = amazonMQCommunicator;
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Configurar eventos de sesión como ServerCoreEventHandler
        ServerCoreEventHandler serverEvents = new AmazonMQServerEvents(amazonMQCommunicator);
        coreProfile.setEventHandler(serverEvents);

        // Añadir el perfil al JSONServer
        jsonServer.addFeatureProfile(coreProfile);

        // Configuración de otros perfiles de servidor si es necesario
        this.serverRemoteTriggerProfile = new ServerRemoteTriggerProfile();
        jsonServer.addFeatureProfile(serverRemoteTriggerProfile);

        // Registro del evento de perfil de cliente en el manejador de eventos principal
        this.clientCoreEventHandler = new DefaultClientCoreEventHandler();
    }






    /**
     * Al establecer una conexión WebSocket, crea y registra una sesión.
     *
     * @param session la sesión WebSocket recién establecida.
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        // Obtiene el subprotocolo aceptado del cliente
        String acceptedProtocol = session.getAcceptedProtocol();
        logger.debug("Subprotocolo solicitado por el cliente (sec-websocket-protocol): {}", acceptedProtocol);

        // Verifica si el subprotocolo es compatible o está definido en el enum ProtocolVersion
        if (acceptedProtocol != null && ProtocolVersion.fromSubProtocolName(acceptedProtocol) != null) {
            UUID sessionId = UUID.randomUUID();

            // Registra la sesión con el subprotocolo aceptado
            amazonMQCommunicator.addSession(sessionId, session);
            logger.info("Sesión WebSocket registrada con subprotocolo {}: {}", acceptedProtocol, sessionId);

        } else {
            // Si el subprotocolo es nulo o no está soportado, cierra la sesión y registra el evento
            String protocolMessage = acceptedProtocol == null ? "Ninguno" : acceptedProtocol;
            logger.warn("Subprotocolo no soportado o no definido: {}. Cerrando la sesión: {}", protocolMessage, session.getId());

            try {
                session.close(CloseStatus.PROTOCOL_ERROR);
            } catch (IOException e) {
                logger.error("Error al cerrar la sesión debido a un subprotocolo no soportado: {}", e.getMessage());
            }
        }
    }


    /**
     * Procesa mensajes entrantes de texto y los maneja según el tipo de acción especificada.
     *
     * @param session la sesión WebSocket.
     * @param message el mensaje recibido.
     * @throws Exception si ocurre un error al procesar el mensaje.
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        logger.debug("Payload recibido: {}", payload);

        // Validar el formato básico del mensaje
        if (!payload.contains("[")) {
            logger.warn("Formato de mensaje no válido recibido: {}", payload);
            sendError(session, null, "Formato no válido: el mensaje debe estar en formato JSON");
            return;
        }

        try {
            Object[] array = objectMapper.readValue(payload, Object[].class);
            String messageId = (String) array[1];
            String action = (String) array[2];
            Object requestPayload = array[3];

            logger.debug("Acción recibida: {} con payload: {}", action, requestPayload.toString());

            switch (action) {
                case "Authorize":
                    handleAuthorize(session, requestPayload, messageId);
                    break;
                case "BootNotification":
                    handleBootNotification(session, requestPayload, messageId);
                    break;
                case "Heartbeat":
                    handleHeartbeat(session, requestPayload, messageId);
                    break;
                case "MeterValues":
                    handleMeterValues(session, requestPayload, messageId);
                    break;
                case "StartTransaction":
                    handleStartTransaction(session, requestPayload, messageId);
                    break;
                case "StopTransaction":
                    handleStopTransaction(session, requestPayload, messageId);
                    break;
                case "StatusNotification":
                    handleStatusNotification(session, requestPayload, messageId);
                    break;
                case "DataTransfer":
                    handleDataTransfer(session, requestPayload, messageId);
                    break;
                case "Available":
                    handleAvailable(session, requestPayload, messageId);
                    break;
                case "Preparing":
                    handlePreparing(session, requestPayload, messageId);
                    break;
                case "Charging":
                    handleCharging(session, requestPayload, messageId);
                    break;
                case "TriggerMessage":
                    handleServerTriggerMessage(session, requestPayload, messageId);
                    break;
                case "ChangeAvailability":
                    handleChangeAvailability(session, requestPayload, messageId);
                    break;
                case "GetConfiguration":
                    handleGetConfiguration(session, requestPayload, messageId);
                    break;
                case "RemoteStartTransaction":
                    handleRemoteStartTransaction(session, requestPayload, messageId);
                    break;
                case "RemoteStopTransaction":
                    handleRemoteStopTransaction(session, requestPayload, messageId);
                    break;
                case "ClearCache":
                    handleClearCache(session, requestPayload, messageId);
                    break;
                case "ChangeConfiguration":
                    handleChangeConfiguration(session, requestPayload, messageId);
                    break;
                default:
                    sendError(session, messageId, "Acción no soportada: " + action);
                    break;
            }

        } catch (JsonProcessingException e) {
            logger.error("Error al analizar el mensaje JSON", e);
            sendError(session, null, "Formato JSON inválido: " + e.getMessage());
        } catch (ClassCastException e) {
            logger.error("Formato de mensaje inesperado", e);
            sendError(session, null, "Formato de mensaje inesperado");
        } catch (Exception e) {
            logger.error("Error al manejar el mensaje", e);
            sendError(session, null, "Error al manejar el mensaje: " + e.getMessage());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        amazonMQCommunicator.removeSession(UUID.fromString(session.getId()));
        logger.info("Sesión WebSocket cerrada: {}", session.getId());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        if (exception instanceof EOFException) {
            logger.warn("EOFException detectada en la sesión: {}", session.getId(), exception);
            if (reconnectAttempts < MAX_RECONNECT_ATTEMPTS) {
                reconnectAttempts++;
                logger.warn("Intento de reconexión {} de {}", reconnectAttempts, MAX_RECONNECT_ATTEMPTS);
                if (session.isOpen()) {
                    try {
                        session.close(CloseStatus.SERVER_ERROR);
                    } catch (IOException e) {
                        logger.error("Error al cerrar la sesión después de EOFException: {}", session.getId(), e);
                    }
                }
                // Implement reconnection logic here
                reconnect(session);
            } else {
                logger.warn("Se alcanzó el máximo de intentos de reconexión. Cerrando sesión: {}", session.getId());
                try {
                    session.close(CloseStatus.SERVER_ERROR);
                } catch (IOException e) {
                    logger.error("Error al cerrar la sesión después de alcanzar el máximo de intentos de reconexión: {}", session.getId(), e);
                }
                sessions.remove(session);
            }
        } else {
            logger.error("Error de transporte en la sesión {}: {}", session.getId(), exception.getMessage());
            try {
                session.close(CloseStatus.SERVER_ERROR);
            } catch (IOException e) {
                logger.error("Error al cerrar la sesión después de un error de transporte: {}", session.getId(), e);
            }
            sessions.remove(session);
        }
    }

    private void reconnect(WebSocketSession session) {
        // Implement your reconnection logic here
        // For example, you can schedule a task to attempt reconnection after a delay
        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            try {
                // Attempt to reconnect
                // You may need to create a new WebSocketSession and re-establish the connection
                // This is a placeholder for your actual reconnection logic
                logger.info("Intentando reconectar la sesión: {}", session.getId());
                // Your reconnection code here
            } catch (Exception e) {
                logger.error("Error al intentar reconectar la sesión: {}", session.getId(), e);
            }
        }, 5, TimeUnit.SECONDS); // Adjust the delay as needed
    }

    /**
     * Handles incoming TriggerMessage requests from the server side by utilizing ServerRemoteTriggerProfile.
     *
     * @param session the WebSocketSession.
     * @param requestPayload the request payload.
     * @param messageId the unique message ID.
     * @throws IOException if an error occurs during processing.
     */
    void handleServerTriggerMessage(WebSocketSession session, Object requestPayload, String messageId) throws IOException {
        try {
            TriggerMessageRequest triggerMessageRequest = objectMapper.convertValue(requestPayload, TriggerMessageRequest.class);
            Confirmation confirmation = serverRemoteTriggerProfile.handleRequest(UUID.fromString(session.getId()), triggerMessageRequest);
            sendResponse(session, messageId, "ServerTriggerMessage", confirmation);
            logger.info("Server-triggered message handled successfully for session: {}", session.getId());
        } catch (Exception e) {
            logger.error("Error processing ServerTriggerMessage", e);
            sendError(session, messageId, "Error in ServerTriggerMessage: " + e.getMessage());
        }
    }

    void handleAvailable(WebSocketSession session, Object requestPayload, String messageId) throws IOException {
        // Conversión del payload
        StatusNotificationRequest statusRequest = objectMapper.convertValue(requestPayload, StatusNotificationRequest.class);
        statusRequest.setStatus(ChargePointStatus.Available);

        jsonServer.sendMessageToMQ("Connector is now Available");
        StatusNotificationConfirmation confirmation = new StatusNotificationConfirmation();

        sendResponse(session, messageId, "StatusNotification", confirmation);
        logger.info("Connector set to Available for session: {}", session.getId());
    }

    void handlePreparing(WebSocketSession session, Object requestPayload, String messageId) throws IOException {
        StatusNotificationRequest statusRequest = objectMapper.convertValue(requestPayload, StatusNotificationRequest.class);
        statusRequest.setStatus(ChargePointStatus.Preparing);

        jsonServer.sendMessageToMQ("Connector is Preparing to start a session");
        StatusNotificationConfirmation confirmation = new StatusNotificationConfirmation();

        sendResponse(session, messageId, "StatusNotification", confirmation);
        logger.info("Connector preparing for session: {}", session.getId());
    }

    void handleCharging(WebSocketSession session, Object requestPayload, String messageId) throws IOException {
        StatusNotificationRequest statusRequest = objectMapper.convertValue(requestPayload, StatusNotificationRequest.class);
        statusRequest.setStatus(ChargePointStatus.Charging);

        jsonServer.sendMessageToMQ("Connector is now Charging");
        StatusNotificationConfirmation confirmation = new StatusNotificationConfirmation();

        sendResponse(session, messageId, "StatusNotification", confirmation);
        logger.info("Charging session started for session: {}", session.getId());
    }


    /**
     * Delegate ChangeAvailability request handling to DefaultClientCoreEventHandler.
     */
    void handleChangeAvailability(WebSocketSession session, Object requestPayload, String messageId) throws IOException {
        try {
            ChangeAvailabilityRequest changeAvailabilityRequest = objectMapper.convertValue(requestPayload, ChangeAvailabilityRequest.class);
            Confirmation confirmation = clientCoreEventHandler.handleChangeAvailabilityRequest(changeAvailabilityRequest);
            sendResponse(session, messageId, "ChangeAvailability", confirmation);
        } catch (Exception e) {
            logger.error("Error processing ChangeAvailability", e);
            sendError(session, messageId, "Error in ChangeAvailability: " + e.getMessage());
        }
    }

    /**
     * Delegate GetConfiguration request handling to DefaultClientCoreEventHandler.
     */
    void handleGetConfiguration(WebSocketSession session, Object requestPayload, String messageId) throws IOException {
        try {
            GetConfigurationRequest getConfigurationRequest = objectMapper.convertValue(requestPayload, GetConfigurationRequest.class);
            Confirmation confirmation = clientCoreEventHandler.handleGetConfigurationRequest(getConfigurationRequest);
            sendResponse(session, messageId, "GetConfiguration", confirmation);
        } catch (Exception e) {
            logger.error("Error processing GetConfiguration", e);
            sendError(session, messageId, "Error in GetConfiguration: " + e.getMessage());
        }
    }



    /**
     * Envía un mensaje de error al cliente.
     *
     * @param session      la sesión WebSocket.
     * @param messageId    el ID del mensaje.
     * @param errorMessage el mensaje de error.
     * @throws IOException si ocurre un error al enviar el mensaje.
     */
    void sendError(WebSocketSession session, String messageId, String errorMessage) throws IOException {
        logger.error("Error: {}", errorMessage);
        String errorResponse = objectMapper.writeValueAsString(new Object[]{4, messageId, errorMessage});
        session.sendMessage(new TextMessage(errorResponse));
        logger.debug("Mensaje de error enviado: {}", errorMessage);
    }


    /**
     * Maneja las solicitudes de autorización y envía la confirmación al cliente.
     *
     * @param session        la sesión WebSocket.
     * @param requestPayload el contenido de la solicitud.
     * @param messageId      el ID del mensaje.
     * @throws IOException si ocurre un error al procesar o enviar la respuesta.
     */
    void handleAuthorize(WebSocketSession session, Object requestPayload, String messageId) throws IOException {
        try {
            // Convierte el payload en una instancia de AuthorizeRequest
            AuthorizeRequest authorizeRequest = objectMapper.convertValue(requestPayload, AuthorizeRequest.class);

            // Envía el mensaje a Amazon MQ
            jsonServer.sendMessageToMQ("Authorize request received for idTag: " + authorizeRequest.getIdTag());

            // Configura los valores de la respuesta
            IdTagInfo idTagInfo = new IdTagInfo();
            idTagInfo.setStatus(AuthorizationStatus.Accepted); // Ajusta el estado según las reglas de negocio
            idTagInfo.setExpiryDate(ZonedDateTime.now().plusDays(30)); // Establece una fecha de expiración si es necesario

            // Crea el objeto de confirmación e inicializa los campos
            AuthorizeConfirmation confirmation = new AuthorizeConfirmation();
            confirmation.setIdTagInfo(idTagInfo); // Asigna el IdTagInfo a la confirmación

            // Envía la respuesta directamente al cliente
            sendResponse(session, messageId, "Authorize", confirmation);
            logger.info("Authorize completado exitosamente para la sesión: {}", session.getId());

        } catch (Exception e) {
            // Cualquier otro error general en el procesamiento
            logger.error("Error procesando Authorize para la sesión: {}", session.getId(), e);
            sendError(session, messageId, "Internal server error");
        }
    }


    /**
     * Maneja la solicitud de notificación de inicio y envía la confirmación al cliente.
     *
     * @param session        La sesión WebSocket.
     * @param requestPayload El contenido de la solicitud.
     * @param messageId      El ID del mensaje.
     * @throws IOException                   Si ocurre un error al procesar o enviar la respuesta.
     * @throws NotConnectedException         Si la sesión no está disponible o conectada.
     * @throws UnsupportedFeatureException   Si una característica no está soportada.
     * @throws OccurenceConstraintException  Si ocurre una violación de restricciones.
     */
    void handleBootNotification(WebSocketSession session, Object requestPayload, String messageId) throws IOException {
        try {
            // Configuración de ObjectMapper
            ObjectMapper mapper = new ObjectMapper();
            JavaTimeModule module = new JavaTimeModule();
            module.addDeserializer(ZonedDateTime.class, new CustomZonedDateTimeDeserializer());
            mapper.registerModule(module);
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            // Deserialización del payload
            BootNotificationRequest bootNotificationRequest = mapper.convertValue(requestPayload, BootNotificationRequest.class);

            // Log para visualizar el contenido de la solicitud
            String bootNotificationJson = mapper.writeValueAsString(bootNotificationRequest);
            logger.info("BootNotificationRequest recibido: {}", bootNotificationJson);

            // Enviar el mensaje a Amazon MQ
            jsonServer.sendMessageToMQ("Boot Notification request received: " + bootNotificationJson);

            // Configuración de respuesta
            BootNotificationConfirmation confirmation = new BootNotificationConfirmation();
            confirmation.setStatus(RegistrationStatus.Accepted);
            confirmation.setCurrentTime(ZonedDateTime.now(ZoneOffset.UTC));
            confirmation.setInterval(300);

            // Enviar la respuesta al cliente
            sendResponse(session, messageId, "BootNotification", confirmation);
            logger.info("BootNotification completado exitosamente para la sesión: {}", session.getId());

        } catch (IllegalArgumentException e) {
            logger.error("Error: ID de sesión no es válido como UUID. ID: {}", session.getId(), e);
            sendError(session, messageId, "Invalid session ID format");
        } catch (Exception e) {
            logger.error("Error procesando BootNotification para la sesión: {}", session.getId(), e);
            sendError(session, messageId, "Internal server error");
        }
    }


    /**
     * Maneja la solicitud de latido (heartbeat) y envía la confirmación al cliente.
     *
     * @param session        la sesión WebSocket.
     * @param requestPayload el contenido de la solicitud.
     * @param messageId      el ID del mensaje.
     * @throws IOException si ocurre un error al procesar o enviar la respuesta.
     */
    void handleHeartbeat(WebSocketSession session, Object requestPayload, String messageId) throws IOException {
        try {
            HeartbeatRequest heartbeatRequest = objectMapper.convertValue(requestPayload, HeartbeatRequest.class);

            // Log detalle del payload convertido a JSON
            String heartbeatJson = objectMapper.writeValueAsString(heartbeatRequest);
            logger.info("Heartbeat recibido: {}", heartbeatJson);

            // Enviar mensaje a Amazon MQ
            jsonServer.sendMessageToMQ("Heartbeat received");
            logger.debug("Heartbeat enviado a Amazon MQ.");

            // Configurar respuesta de confirmación
            HeartbeatConfirmation confirmation = new HeartbeatConfirmation();
            confirmation.setCurrentTime(ZonedDateTime.now(ZoneOffset.UTC));

            // Enviar respuesta al cliente
            sendResponse(session, messageId, "Heartbeat", confirmation);
            logger.info("Heartbeat completado exitosamente para la sesión: {}", session.getId());

        } catch (IllegalArgumentException e) {
            logger.error("Error: ID de sesión no es válido como UUID. ID: {}", session.getId(), e);
            sendError(session, messageId, "Invalid session ID format");
        } catch (Exception e) {
            logger.error("Error procesando Heartbeat para la sesión: {}", session.getId(), e);
            sendError(session, messageId, "Internal server error");
        }
    }



    /**
     * Maneja la solicitud de valores del medidor y envía la confirmación al cliente.
     *
     * @param session        la sesión WebSocket.
     * @param requestPayload el contenido de la solicitud.
     * @param messageId      el ID del mensaje.
     * @throws IOException si ocurre un error al procesar o enviar la respuesta.
     */
    void handleMeterValues(WebSocketSession session, Object requestPayload, String messageId) throws IOException {
        try {
            // Configuración del ObjectMapper con deserializador personalizado
            ObjectMapper mapper = new ObjectMapper();
            JavaTimeModule module = new JavaTimeModule();
            module.addDeserializer(ZonedDateTime.class, new CustomZonedDateTimeDeserializer());
            mapper.registerModule(module);
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            // Deserialización del payload
            MeterValuesRequest meterValuesRequest = mapper.convertValue(requestPayload, MeterValuesRequest.class);

            // Serializar a JSON para enviar a Amazon MQ y visualizar en logs
            String meterValuesJson = mapper.writeValueAsString(meterValuesRequest);
            logger.info("Meter values recibidos: {}", meterValuesJson);
            jsonServer.sendMessageToMQ("Meter values recibidos: " + meterValuesJson);

            // Crear y enviar la confirmación al cliente
            MeterValuesConfirmation confirmation = new MeterValuesConfirmation();
            sendResponse(session, messageId, "MeterValues", confirmation);
            logger.info("MeterValues completado exitosamente para la sesión: {}", session.getId());

        } catch (IllegalArgumentException e) {
            logger.error("Error: ID de sesión no es válido como UUID. ID: {}", session.getId(), e);
            sendError(session, messageId, "Invalid session ID format");
        } catch (Exception e) {
            logger.error("Error procesando MeterValues para la sesión: {}", session.getId(), e);
            sendError(session, messageId, "Internal server error");
        }
    }




    /**
     * Maneja la solicitud de inicio de transacción y envía la confirmación al cliente.
     *
     * @param session        la sesión WebSocket.
     * @param requestPayload el contenido de la solicitud.
     * @param messageId      el ID del mensaje.
     * @throws IOException si ocurre un error al procesar o enviar la respuesta.
     */
    void handleStartTransaction(WebSocketSession session, Object requestPayload, String messageId) throws IOException {
        try {
            logger.debug("Procesando StartTransaction con messageId: {}", messageId);

            // Configuración del ObjectMapper y deserialización
            ObjectMapper mapper = new ObjectMapper();
            JavaTimeModule module = new JavaTimeModule();
            module.addDeserializer(ZonedDateTime.class, new CustomZonedDateTimeDeserializer());
            mapper.registerModule(module);
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            // Registro del payload recibido
            logger.info("Mensaje recibido del cargador: {}", requestPayload);

            // Deserialización del payload
            StartTransactionRequest startTransactionRequest = mapper.readValue(requestPayload.toString(), StartTransactionRequest.class);
            logger.debug("Payload deserializado: {}", startTransactionRequest);

            // Enviar el mensaje a Amazon MQ
            jsonServer.sendMessageToMQ("Start Transaction request received for Connector ID: " + startTransactionRequest.getConnectorId());
            logger.info("Mensaje enviado a Amazon MQ para StartTransaction: Connector ID {}", startTransactionRequest.getConnectorId());

            // Configurar la respuesta
            IdTagInfo idTagInfo = new IdTagInfo();
            idTagInfo.setStatus(AuthorizationStatus.Accepted);
            idTagInfo.setExpiryDate(ZonedDateTime.now().plusDays(30));
            StartTransactionConfirmation confirmation = new StartTransactionConfirmation();
            confirmation.setIdTagInfo(idTagInfo);
            confirmation.setTransactionId(new Random().nextInt(99999));

            // Enviar la respuesta al cliente
            sendResponse(session, messageId, "StartTransaction", confirmation);
            logger.info("Respuesta enviada al cliente para el mensaje ID {}: {}", messageId, confirmation);
            logger.info("StartTransaction completado exitosamente para la sesión: {}", session.getId());

        } catch (IllegalArgumentException e) {
            logger.error("Error: ID de sesión no es válido como UUID. ID: {}", session.getId(), e);
            sendError(session, messageId, "Invalid session ID format");
        } catch (Exception e) {
            logger.error("Error procesando StartTransaction para la sesión: {}", session.getId(), e);
            sendError(session, messageId, "Internal server error");
        }
    }



    /**
     * Maneja la solicitud de finalización de transacción y envía la confirmación al cliente.
     *
     * @param session        la sesión WebSocket.
     * @param requestPayload el contenido de la solicitud.
     * @param messageId      el ID del mensaje.
     * @throws IOException si ocurre un error al procesar o enviar la respuesta.
     */
    void handleStopTransaction(WebSocketSession session, Object requestPayload, String messageId) throws IOException {
        try {
            // Configuración del ObjectMapper con deserializador personalizado
            ObjectMapper mapper = new ObjectMapper();
            JavaTimeModule module = new JavaTimeModule();
            module.addDeserializer(ZonedDateTime.class, new CustomZonedDateTimeDeserializer());
            mapper.registerModule(module);
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            // Deserializar el payload en una instancia de StopTransactionRequest
            StopTransactionRequest stopTransactionRequest = mapper.convertValue(requestPayload, StopTransactionRequest.class);

            // Serializar y loggear el contenido del request
            String stopTransactionJson = mapper.writeValueAsString(stopTransactionRequest);
            logger.info("StopTransaction request recibido: {}", stopTransactionJson);

            // Enviar el mensaje a Amazon MQ
            jsonServer.sendMessageToMQ("Stop Transaction request received: " + stopTransactionJson);

            // Configuración de los valores de respuesta
            IdTagInfo idTagInfo = new IdTagInfo();
            idTagInfo.setStatus(AuthorizationStatus.Accepted);
            idTagInfo.setExpiryDate(ZonedDateTime.now().plusDays(30));

            // Crear y enviar la respuesta al cliente
            StopTransactionConfirmation confirmation = new StopTransactionConfirmation();
            confirmation.setIdTagInfo(idTagInfo);
            sendResponse(session, messageId, "StopTransaction", confirmation);
            logger.info("StopTransaction completado exitosamente para la sesión: {}", session.getId());

        } catch (IllegalArgumentException e) {
            logger.error("Error: ID de sesión no es válido como UUID. ID: {}", session.getId(), e);
            sendError(session, messageId, "Invalid session ID format");
        } catch (Exception e) {
            logger.error("Error procesando StopTransaction para la sesión: {}", session.getId(), e);
            sendError(session, messageId, "Internal server error");
        }
    }


    /**
     * Maneja la solicitud de notificación de estado y envía la confirmación al cliente.
     * <p>
     * Este método convierte el `requestPayload` en un objeto `StatusNotificationRequest` y luego
     * envía un mensaje a Amazon MQ con los detalles del estado. Después, envía una confirmación
     * al cliente a través de la sesión WebSocket.
     * </p>
     *
     * @param session        la sesión WebSocket.
     * @param requestPayload el contenido de la solicitud.
     * @param messageId      el ID del mensaje.
     * @throws IOException si ocurre un error al procesar o enviar la respuesta.
     */
    void handleStatusNotification(WebSocketSession session, Object requestPayload, String messageId) throws IOException {
        try {
            logger.debug("Procesando StatusNotification con messageId: {}", messageId);

            // Configuración del ObjectMapper
            ObjectMapper mapper = new ObjectMapper();
            JavaTimeModule module = new JavaTimeModule();
            module.addDeserializer(ZonedDateTime.class, new CustomZonedDateTimeDeserializer());
            mapper.registerModule(module);
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            // Registro del payload recibido
            logger.info("Mensaje recibido del cargador: {}", requestPayload.toString());

            // Deserialización del payload
            StatusNotificationRequest statusNotificationRequest = mapper.readValue(requestPayload.toString(), StatusNotificationRequest.class);
            logger.debug("Payload deserializado: {}", statusNotificationRequest);

            // Envío de mensaje a Amazon MQ
            String messageToMQ = "Status Notification received: " + statusNotificationRequest;
            jsonServer.sendMessageToMQ(messageToMQ);
            logger.info("Mensaje enviado a Amazon MQ: {}", messageToMQ);

            // Creación de la confirmación
            StatusNotificationConfirmation confirmation = new StatusNotificationConfirmation();

            // Envío de la respuesta al cliente
            sendResponse(session, messageId, "StatusNotification", confirmation);
            logger.info("Respuesta enviada al cliente para el mensaje ID {}: {}", messageId, confirmation);
            logger.info("Respuesta enviada para StatusNotification: {}", messageId);
            logger.info("StatusNotification completado exitosamente para la sesión: {}", session.getId());

        } catch (IllegalArgumentException e) {
            // Manejo de error para ID de sesión inválido
            logger.error("Error: ID de sesión no es válido como UUID. ID: {}", session.getId(), e);
            sendError(session, messageId, "Invalid session ID format");
        } catch (Exception e) {
            // Manejo de otros errores
            logger.error("Error procesando StatusNotification para la sesión: {}", session.getId(), e);
            sendError(session, messageId, "Internal server error");
        }
    }






    /**
     * Maneja la solicitud de transferencia de datos y envía la confirmación al cliente.
     *
     * @param session        la sesión WebSocket.
     * @param requestPayload el contenido de la solicitud.
     * @param messageId      el ID del mensaje.
     * @throws IOException si ocurre un error al procesar o enviar la respuesta.
     */
    void handleDataTransfer(WebSocketSession session, Object requestPayload, String messageId) throws IOException {
        try {
            // Convertir el payload en una instancia de DataTransferRequest
            DataTransferRequest dataTransferRequest = objectMapper.convertValue(requestPayload, DataTransferRequest.class);

            // Validar que los campos requeridos estén presentes
            if (dataTransferRequest.getVendorId() == null || dataTransferRequest.getMessageId() == null) {
                logger.error("Campos requeridos faltantes en DataTransferRequest");
                sendError(session, messageId, "Missing required fields in DataTransferRequest");
                return;
            }

            // Enviar el mensaje a Amazon MQ con los detalles de DataTransfer
            jsonServer.sendMessageToMQ("Data Transfer request received: " + dataTransferRequest.toString());

            // Determinar el status de respuesta basado en lógica de negocio (aquí lo configuramos manualmente)
            DataTransferStatus dataTransferStatus = DataTransferStatus.Accepted;
            if ("Vendor123".equals(dataTransferRequest.getVendorId())) {
                dataTransferStatus = DataTransferStatus.Accepted;
            } else {
                dataTransferStatus = DataTransferStatus.Rejected;
            }

            // Crear el objeto de confirmación con los campos configurados
            DataTransferConfirmation confirmation = new DataTransferConfirmation();
            confirmation.setStatus(dataTransferStatus);
            confirmation.setData("Datos procesados correctamente para: " + dataTransferRequest.getMessageId());

            // Enviar la respuesta directamente al cliente
            sendResponse(session, messageId, "DataTransfer", confirmation);
            logger.info("DataTransfer completado exitosamente para la sesión: {}", session.getId());

        } catch (IllegalArgumentException e) {
            // Manejo de error si el ID de la sesión no es válido
            logger.error("Error: ID de sesión no es válido como UUID. ID: {}", session.getId(), e);
            sendError(session, messageId, "Invalid session ID format");
        } catch (Exception e) {
            // Cualquier otro error general en el procesamiento
            logger.error("Error procesando DataTransfer para la sesión: {}", session.getId(), e);
            sendError(session, messageId, "Internal server error");
        }
    }

    /**
     * Procesa la solicitud de cambio de configuración, proporcionando detalles específicos.
     *
     * <p>Ejemplo de llamada:
     * <pre>
     *   {
     *      "messageId": "12345",
     *      "action": "ChangeConfiguration",
     *      "payload": {
     *          "key": "MaxChargingProfile",
     *          "value": "10"
     *      }
     *   }
     * </pre>
     *
     * @param session      la sesión WebSocket activa.
     * @param requestPayload el payload de la solicitud ChangeConfiguration.
     * @param messageId    el ID del mensaje que se está procesando.
     * @throws IOException en caso de error al enviar la respuesta.
     */
    void handleChangeConfiguration(WebSocketSession session, Object requestPayload, String messageId) throws IOException {
        // Lógica de procesamiento específica para ChangeConfiguration
        sendResponse(session, messageId, "ChangeConfiguration", new ChangeConfigurationConfirmation());
        logger.info("ChangeConfiguration completado exitosamente para la sesión: {}", session.getId());
    }

    /**
     * Maneja la solicitud de ClearCache, con el objetivo de limpiar la caché del punto de carga.
     *
     * <p>Ejemplo de llamada:
     * <pre>
     *   {
     *      "messageId": "67890",
     *      "action": "ClearCache"
     *   }
     * </pre>
     *
     * @param session      la sesión WebSocket activa.
     * @param requestPayload el payload de la solicitud ClearCache.
     * @param messageId    el ID del mensaje que se está procesando.
     * @throws IOException en caso de error al enviar la respuesta.
     */
    void handleClearCache(WebSocketSession session, Object requestPayload, String messageId) throws IOException {
        // Lógica de procesamiento específica para ClearCache
        sendResponse(session, messageId, "ClearCache", new ClearCacheConfirmation());
        logger.info("ClearCache completado exitosamente para la sesión: {}", session.getId());
    }

    /**
     * Procesa la solicitud RemoteStopTransaction, deteniendo una transacción de carga remota.
     *
     * <p>Ejemplo de llamada:
     * <pre>
     *   {
     *      "messageId": "56789",
     *      "action": "RemoteStopTransaction",
     *      "payload": {
     *          "transactionId": 789
     *      }
     *   }
     * </pre>
     *
     * @param session      la sesión WebSocket activa.
     * @param requestPayload el payload de la solicitud RemoteStopTransaction.
     * @param messageId    el ID del mensaje que se está procesando.
     * @throws IOException en caso de error al enviar la respuesta.
     */
    void handleRemoteStopTransaction(WebSocketSession session, Object requestPayload, String messageId) throws IOException {
        // Lógica de procesamiento específica para RemoteStopTransaction
        sendResponse(session, messageId, "RemoteStopTransaction", new RemoteStopTransactionConfirmation());
        logger.info("RemoteStopTransaction completado exitosamente para la sesión: {}", session.getId());
    }

    /**
     * Procesa la solicitud RemoteStartTransaction, comenzando una transacción de carga remota.
     *
     * <p>Ejemplo de llamada:
     * <pre>
     *   {
     *      "messageId": "45678",
     *      "action": "RemoteStartTransaction",
     *      "payload": {
     *          "connectorId": 1,
     *          "idTag": "ABC123"
     *      }
     *   }
     * </pre>
     *
     * @param session      la sesión WebSocket activa.
     * @param requestPayload el payload de la solicitud RemoteStartTransaction.
     * @param messageId    el ID del mensaje que se está procesando.
     * @throws IOException en caso de error al enviar la respuesta.
     */
    void handleRemoteStartTransaction(WebSocketSession session, Object requestPayload, String messageId) throws IOException {
        // Lógica de procesamiento específica para RemoteStartTransaction
        sendResponse(session, messageId, "RemoteStartTransaction", new RemoteStartTransactionConfirmation());
        logger.info("RemoteStartTransaction completado exitosamente para la sesión: {}", session.getId());
    }


    /**
     * Enviar respuestas en un formato uniforme para todos los manejadores.
     *
     * @param session      la sesión WebSocket.
     * @param messageId    el ID del mensaje.
     * @param action       la acción específica procesada.
     * @param confirmation la confirmación a enviar al cliente.
     * @throws IOException si ocurre un error al enviar el mensaje.
     */
    private void sendResponse(WebSocketSession session, String messageId, String action, Object confirmation) throws IOException {
        String response = objectMapper.writeValueAsString(new Object[]{3, messageId, confirmation});
        session.sendMessage(new TextMessage(response));
        logger.info("Respuesta enviada para {}: {}", action, messageId);
    }

    /**
     * Crea una instancia de eventos de sesión para manejar el ciclo de vida de la sesión.
     *
     * @param sessionUUID el UUID de la sesión.
     * @return los eventos de sesión configurados.
     */
    private SessionEvents createSessionEvents(UUID sessionUUID) {
        return new SessionEvents() {
            @Override
            public void handleConfirmation(String uniqueId, Confirmation confirmation) {
                logger.info("Confirmación recibida: {}", confirmation);
            }

            @Override
            public Confirmation handleRequest(Request request) throws UnsupportedFeatureException {
                logger.debug("Solicitud recibida: {}", request);
                return null;
            }


            @Override
            public boolean asyncCompleteRequest(String uniqueId, Confirmation confirmation) {
                return false;
            }

            @Override
            public void handleError(String uniqueId, String errorCode, String errorDescription, Object payload) {
                logger.error("Error en la solicitud: {} - {}", errorCode, errorDescription);
            }

            @Override
            public void handleConnectionOpened() {
                logger.info("Conexión WebSocket abierta: {}", sessionUUID);
            }

            @Override
            public void handleConnectionClosed() {
                logger.info("Conexión WebSocket cerrada: {}", sessionUUID);
                sessionStore.remove(sessionUUID);
            }

            @Override
            public void onError(String ocppMessageId, String internalError, String errorProcessingRequest, Request request) {
                logger.error("Error interno en la solicitud OCPP: {}", ocppMessageId);
            }

            @Override
            public void newSession(UUID sessionIndex, SessionInformation information) {
                logger.info("Nueva sesión iniciada: {}", sessionIndex);
            }

            @Override
            public void lostSession(UUID sessionIndex) {
                logger.warn("Sesión perdida: {}", sessionIndex);
                sessionStore.remove(sessionIndex);
            }
        };
    }
}
