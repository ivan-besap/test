package com.eVolGreen.eVolGreen.Configurations.MQ;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.*;
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
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Enums.RemoteStartStopStatus;
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
import java.util.concurrent.*;

/**
 * {@code WebSocketHandler} maneja las conexiones WebSocket, gestionando el ciclo de vida de las sesiones
 * y redireccionando solicitudes como BootNotification y Authorize al {@link JSONServer}.
 */
@AllArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

    private static final Map<UUID, Session> sessionStore = new ConcurrentHashMap<>();
    private static final Map<UUID, WebSocketSession> webSocketSessionStorage = new ConcurrentHashMap<>();

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
    private long RECONNECT_INTERVAL_SECONDS;
    private Queue queue;
    private PromiseFulfiller fulfiller;
    private IFeatureRepository featureRepository;


    /**
     * Maneja las conexiones WebSocket, gestionando el ciclo de vida de las sesiones y
     * redirigiendo solicitudes específicas de OCPP al {@link JSONServer} y al manejador de eventos de Amazon MQ.
     * <p>
     * Este constructor inicializa y configura los perfiles y eventos necesarios, incluyendo el perfil principal del
     * servidor y los eventos para la integración con Amazon MQ.
     * </p>
     *
     * @param sessionFactory       Fábrica de sesiones para crear instancias de {@link ISession}.
     * @param communicator         Comunicador para transmisión de mensajes.
     * @param jsonServer           Servidor JSON que maneja las solicitudes de OCPP.
     * @param coreProfile          Perfil principal de OCPP para el servidor.
     * @param amazonMQCommunicator Comunicador para integración con Amazon MQ.
     */
    public WebSocketHandler(ISessionFactory sessionFactory, Communicator communicator,
                            JSONServer jsonServer, ServerCoreProfile coreProfile,
                            AmazonMQCommunicator amazonMQCommunicator) {

        this.sessionFactory = sessionFactory;
        this.communicator = communicator;
        this.jsonServer = jsonServer;
        this.amazonMQCommunicator = amazonMQCommunicator;
        this.queue = queue;
        this.fulfiller = fulfiller;
        this.featureRepository = featureRepository;
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Configurar eventos de sesión como ServerCoreEventHandler
        ServerCoreEventHandler serverEvents = new AmazonMQServerEvents(amazonMQCommunicator);
        coreProfile.setEventHandler(serverEvents);

        logger.debug("Dependencias inicializadas: sessionFactory={}, communicator={}, amazonMQCommunicator={}, coreProfile={}",
                sessionFactory, communicator, amazonMQCommunicator, coreProfile);

        if (amazonMQCommunicator == null || !amazonMQCommunicator.isConnected()) {
            throw new IllegalStateException("El radio no está inicializado correctamente.");
        }
        if (sessionFactory == null || communicator == null || coreProfile == null) {
            throw new IllegalStateException("Dependencias críticas no inicializadas.");
        }





        // Añadir el perfil al JSONServer
        jsonServer.addFeatureProfile(coreProfile);

        // Configuración de otros perfiles de servidor si es necesario
        this.serverRemoteTriggerProfile = new ServerRemoteTriggerProfile();
        jsonServer.addFeatureProfile(serverRemoteTriggerProfile);

        // Registro del evento de perfil de cliente en el manejador de eventos principal
        this.clientCoreEventHandler = new DefaultClientCoreEventHandler();
    }

//1
    /**
     * Maneja el establecimiento de una nueva conexión WebSocket. Valida el subprotocolo aceptado por el cliente
     * y, si es compatible, crea una nueva sesión OCPP, la registra en Amazon MQ, y la almacena en sessionStore.
     * Si el protocolo no es soportado, la sesión se cierra con un error.
     *
     * @param webSocketSession La sesión WebSocket recién establecida.
     * @throws IOException Si ocurre un error al cerrar una sesión con protocolo no soportado.
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws IOException {
        String acceptedProtocol = (String) webSocketSession.getAttributes().get("subProtocol");
        logger.debug("Subprotocolo aceptado en la sesión (después de handshake): {}", acceptedProtocol);

        if (!"ocpp1.6".equals(acceptedProtocol)) {
            handleUnsupportedProtocol(webSocketSession, acceptedProtocol);
            return;
        }

        try {
            // Utiliza directamente el ID proporcionado por WebSocketSession
            String sessionId = webSocketSession.getId();
            UUID sessionUUID = UUID.nameUUIDFromBytes(sessionId.getBytes());

            // Crea e inicializa la sesión OCPP con el mismo ID que WebSocketSession
            Session session = initializeSession(sessionUUID, webSocketSession);

            // Vincula el sessionUUID con la WebSocketSession
            webSocketSession.getAttributes().put("sessionId", sessionUUID);

            // Almacena la sesión en sessionStore y WebSocketSession en webSocketSessionStorage
            sessionStore.put(sessionUUID, session);
            webSocketSessionStorage.put(sessionUUID, webSocketSession);

            amazonMQCommunicator.addSession(sessionUUID, webSocketSession);
            logger.info("Sesión WebSocket registrada exitosamente con ID: {}", sessionUUID);

        } catch (Exception e) {
            logger.error("Error al establecer la conexión WebSocket: {}", e.getMessage(), e);
            webSocketSession.close(CloseStatus.SERVER_ERROR);
        }
    }




    /**
     * Inicializa una nueva instancia de una sesión OCPP asociada a una sesión WebSocket.
     * <p>
     * Este método crea una sesión OCPP con un UUID proporcionado y la vincula a la sesión WebSocket
     * correspondiente. La sesión OCPP es registrada y configurada con los eventos necesarios
     * para su manejo. Además, la sesión es almacenada y registrada en el sistema de mensajería Amazon MQ.
     * </p>
     *
     * @param sessionUUID     El identificador único (UUID) de la sesión que será inicializada.
     * @param webSocketSession La sesión WebSocket asociada que se utilizará para la comunicación.
     * @return La instancia de {@link ISession} que representa la sesión OCPP inicializada.
     * @throws IllegalStateException Si la sesión creada no es una instancia válida de {@link Session}.
     */
    private Session initializeSession(UUID sessionUUID, WebSocketSession webSocketSession) {
        // Crear sesión como ISession
        ISession iSession = sessionFactory.createSession(sessionUUID, communicator, queue, fulfiller, featureRepository);

        // Hacer casting explícito si es necesario
        if (iSession instanceof Session) {
            Session session = (Session) iSession;

            // Registra la sesión con sus eventos
            session.registerSession(createSessionEvents(session));

            logger.info("Sesión OCPP inicializada con ID: {}", sessionUUID);

            return session;
        } else {
            logger.error("Error: La instancia creada no es de tipo 'Session'.");
            throw new IllegalStateException("El tipo de sesión no es compatible.");
        }
    }



    /**
     * Verifica si el protocolo proporcionado es soportado por el servidor.
     *
     * @param acceptedProtocol El subprotocolo aceptado por el cliente durante la conexión.
     * @return true si el protocolo es soportado; de lo contrario, false.
     */
    private boolean isProtocolSupported(String acceptedProtocol) {
        return acceptedProtocol != null && ProtocolVersion.fromSubProtocolName(acceptedProtocol) != null;
    }

    /**
     * Maneja el caso en el que el subprotocolo aceptado por el cliente no es soportado.
     * Se registra un mensaje de advertencia y se cierra la sesión con un estado de error.
     *
     * @param webSocketSession La sesión WebSocket que se debe cerrar.
     * @param acceptedProtocol El subprotocolo que el cliente intentó utilizar.
     * @throws IOException Si ocurre un error al cerrar la sesión.
     */
    private void handleUnsupportedProtocol(WebSocketSession webSocketSession, String acceptedProtocol) throws IOException {
        String protocolMessage = acceptedProtocol == null ? "Ninguno" : acceptedProtocol;
        logger.warn("Subprotocolo no soportado o no definido: {}. Cerrando la sesión: {}", protocolMessage, webSocketSession.getId());

        try {
            webSocketSession.close(CloseStatus.PROTOCOL_ERROR);
        } catch (IOException e) {
            logger.error("Error al cerrar la sesión debido a un subprotocolo no soportado: {}", e.getMessage());
        }
    }

//2
    /**
     * Procesa mensajes de texto entrantes y los maneja según el tipo de acción especificada.
     *
     * @param webSocketSession La sesión WebSocket.
     * @param message          El mensaje recibido.
     * @throws Exception Si ocurre un error al procesar el mensaje.
     */
    @Override
    protected void handleTextMessage(WebSocketSession webSocketSession, TextMessage message) throws Exception {
        String payload = message.getPayload();
        logger.debug("Payload recibido: {}", payload);

        if (!isValidJsonFormat(payload)) {
            sendError(null, webSocketSession, null, "Formato no válido: el mensaje debe estar en formato JSON");
            return;
        }

        try {
            // Obtener el sessionId desde los atributos de la WebSocketSession
            UUID sessionId = (UUID) webSocketSession.getAttributes().get("sessionId");
            Session session = sessionStore.get(sessionId);

            // Verifica si la sesión es nula y maneja el error en consecuencia
            if (session == null) {
                logger.error("No se encontró la sesión OCPP para el ID: {}", sessionId);
                sendError(null, webSocketSession, null, "Sesión no encontrada");
                return;
            }

            // Llama a `handleAction` con la instancia de `Session` y `webSocketSession`
            handleAction(session, webSocketSession, payload);

        } catch (JsonProcessingException e) {
            logger.error("Error al analizar el mensaje JSON", e);
            sendError(null, webSocketSession, null, "Formato JSON inválido: " + e.getMessage());
        } catch (ClassCastException e) {
            logger.error("Formato de mensaje inesperado", e);
            sendError(null, webSocketSession, null, "Formato de mensaje inesperado");
        } catch (Exception e) {
            logger.error("Error al manejar el mensaje", e);
            sendError(null, webSocketSession, null, "Error al manejar el mensaje: " + e.getMessage());
        }
    }


    /**
     * Verifica si el mensaje tiene un formato JSON básico válido.
     *
     * @param payload El contenido del mensaje.
     * @return true si el formato es válido; de lo contrario, false.
     */
    private boolean isValidJsonFormat(String payload) {
        if (!payload.contains("[")) {
            logger.warn("Formato de mensaje no válido recibido: {}", payload);
            return false;
        }
        return true;
    }

    /**
     * Procesa el contenido del mensaje y ejecuta la acción correspondiente.
     *
     * @param session          La instancia de la sesión OCPP.
     * @param webSocketSession La sesión WebSocket.
     * @param payload          El contenido del mensaje en formato JSON.
     * @throws IOException Si ocurre un error de procesamiento JSON.
     */
    private void handleAction(Session session, WebSocketSession webSocketSession, String payload) throws IOException {
        Object[] array = objectMapper.readValue(payload, Object[].class);
        String messageId = (String) array[1];
        String action = (String) array[2];
        Object requestPayload = array[3];

        logger.debug("Acción recibida: {} con payload: {}", action, requestPayload.toString());

        switch (action) {
            case "Authorize" -> handleAuthorize(session, webSocketSession, requestPayload, messageId);
            case "BootNotification" -> handleBootNotification(session, webSocketSession, requestPayload, messageId);
            case "Heartbeat" -> handleHeartbeat(session, webSocketSession, requestPayload, messageId);
            case "MeterValues" -> handleMeterValues(session, webSocketSession, requestPayload, messageId);
            case "StartTransaction" -> handleStartTransaction(session, webSocketSession, requestPayload, messageId);
            case "StopTransaction" -> handleStopTransaction(session, webSocketSession, requestPayload, messageId);
            case "StatusNotification" -> handleStatusNotification(session, webSocketSession, requestPayload, messageId);
            case "DataTransfer" -> handleDataTransfer(session, webSocketSession, requestPayload, messageId);
            case "Available" -> handleAvailable(session, webSocketSession, requestPayload, messageId);
            case "Preparing" -> handlePreparing(session, webSocketSession, requestPayload, messageId);
            case "Charging" -> handleCharging(session, webSocketSession, requestPayload, messageId);
            case "TriggerMessage" -> handleServerTriggerMessage(session, webSocketSession, requestPayload, messageId);
            case "ChangeAvailability" -> handleChangeAvailability(session, webSocketSession, requestPayload, messageId);
            case "GetConfiguration" -> handleGetConfiguration(session, webSocketSession, requestPayload, messageId);
            case "RemoteStartTransaction" -> handleRemoteStartTransaction(session, webSocketSession, requestPayload, messageId);
            case "RemoteStopTransaction" -> handleRemoteStopTransaction(session, webSocketSession, requestPayload, messageId);
            case "ClearCache" -> handleClearCache(session, webSocketSession, requestPayload, messageId);
            case "ChangeConfiguration" -> handleChangeConfiguration(session, webSocketSession, requestPayload, messageId);
            default -> sendError(session, webSocketSession, messageId, "Acción no soportada: " + action);
        }
    }

//3
    /**
     * Maneja el cierre de la conexión WebSocket, eliminando la sesión del almacén y de Amazon MQ.
     *
     * @param webSocketSession La sesión WebSocket que se ha cerrado.
     * @param status           El estado de cierre de la sesión.
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus status) {
        try {
            UUID sessionId = UUID.fromString(webSocketSession.getId());
            removeSession(sessionId);
            logger.info("Sesión cerrada y eliminada: {}", sessionId);
        } catch (IllegalArgumentException e) {
            logger.error("Error: ID de sesión no es un UUID válido: {}", webSocketSession.getId(), e);
        }
    }

    /**
     * Elimina la sesión del almacén y de Amazon MQ.
     *
     * @param sessionId El identificador único de la sesión.
     */
    private void removeSession(UUID sessionId) {
        // Eliminar la sesión de los almacenes de datos
        Session ocppSession = sessionStore.remove(sessionId);

        // Asegura que se remueva también de AmazonMQ
        if (ocppSession != null) {
            amazonMQCommunicator.removeSession(sessionId);
            logger.info("Sesión con ID {} removida de AmazonMQ y de sessionStore.", sessionId);
        } else {
            logger.warn("No se encontró la sesión para remover: {}", sessionId);
        }
    }

//4
    /**
     * Maneja errores de transporte en la sesión WebSocket, incluyendo reconexiones en caso de EOFException.
     *
     * @param webSocketSession La sesión WebSocket en la que ocurrió el error.
     * @param exception        La excepción lanzada durante el transporte.
     */
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable exception) {
        if (exception instanceof EOFException) {
            logger.warn("EOFException detectada en la sesión: {}", webSocketSession.getId(), exception);
            attemptReconnection(webSocketSession);
        } else {
            logger.error("Error de transporte en la sesión {}: {}", webSocketSession.getId(), exception.getMessage());
            closeSessionWithError(webSocketSession);
        }
    }

    /**
     * Intenta reconectar la sesión si no se ha alcanzado el máximo de intentos permitidos.
     *
     * @param webSocketSession La sesión WebSocket que necesita reconexión.
     */
    private void attemptReconnection(WebSocketSession webSocketSession) {
        if (reconnectAttempts < MAX_RECONNECT_ATTEMPTS) {
            reconnectAttempts++;
            logger.warn("Intento de reconexión {} de {}", reconnectAttempts, MAX_RECONNECT_ATTEMPTS);
            closeSessionWithError(webSocketSession);

            // Obtiene la sesión OCPP asociada para reutilizar en la reconexión
            Session ocppSession = sessionStore.get(UUID.fromString(webSocketSession.getId()));
            if (ocppSession != null) {
                reconnect(webSocketSession, ocppSession);
            } else {
                logger.warn("No se encontró la sesión OCPP asociada para reconectar: {}", webSocketSession.getId());
            }
        } else {
            logger.warn("Se alcanzó el máximo de intentos de reconexión. Cerrando sesión: {}", webSocketSession.getId());
            closeSessionWithError(webSocketSession);
            sessions.remove(webSocketSession);
        }
    }

    /**
     * Cierra la sesión con un estado de error, registrando la excepción.
     *
     * @param webSocketSession La sesión WebSocket que se cerrará.
     */
    private void closeSessionWithError(WebSocketSession webSocketSession) {
        try {
            if (webSocketSession.isOpen()) {
                webSocketSession.close(CloseStatus.SERVER_ERROR);
            }
        } catch (IOException e) {
            logger.error("Error al cerrar la sesión debido a un error de transporte: {}", webSocketSession.getId(), e);
        }
    }


//5
    /**
     * Programa una serie de reintentos de reconexión para la sesión WebSocket especificada.
     * Si se alcanzan los intentos máximos sin éxito, se crea una nueva sesión OCPP y se registra.
     *
     * @param webSocketSession La sesión WebSocket que se intentará reconectar.
     * @param ocppSession      La instancia de la sesión OCPP asociada.
     */
    private void reconnect(WebSocketSession webSocketSession, Session ocppSession) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(() -> {
            if (reconnectAttempts < MAX_RECONNECT_ATTEMPTS) {
                attemptReconnectionLogic(webSocketSession, ocppSession);
            } else {
                // Si se alcanzan los intentos máximos, crear una nueva sesión OCPP
                createAndRegisterNewSession(webSocketSession);
                scheduler.shutdown();
            }
        }, 0, RECONNECT_INTERVAL_SECONDS, TimeUnit.SECONDS);
    }

    /**
     * Intenta restablecer la conexión de una sesión WebSocket existente.
     * Si la sesión está abierta, la cierra de manera controlada para luego intentar la reconexión.
     * Este método incrementa los intentos de reconexión y verifica el estado de la sesión.
     *
     * @param webSocketSession La sesión WebSocket que se intentará reconectar.
     * @param ocppSession      La instancia de la sesión OCPP asociada.
     */
    private void attemptReconnectionLogic(WebSocketSession webSocketSession, Session ocppSession) {
        try {
            if (webSocketSession.isOpen()) {
                webSocketSession.close(CloseStatus.SESSION_NOT_RELIABLE);
            }
            logger.info("Intentando reconectar la sesión: {}", webSocketSession.getId());

            // Intentar reconexión reutilizando la misma instancia de Session
            ocppSession.open(webSocketSession.getUri().toString(), createSessionEvents(ocppSession));
            reconnectAttempts++;
        } catch (Exception e) {
            logger.error("Error al intentar reconectar la sesión: {}", webSocketSession.getId(), e);
        }
    }

    /**
     * Crea una nueva sesión OCPP y la registra en el sistema, reemplazando la sesión actual.
     * Este método es llamado cuando se alcanzan los intentos máximos de reconexión sin éxito.
     *
     * @param webSocketSession La sesión WebSocket original que no pudo ser reconectada.
     */
    private void createAndRegisterNewSession(WebSocketSession webSocketSession) {
        try {
            UUID newSessionId = UUID.randomUUID();

            // Crear y configurar una nueva sesión OCPP
            Session newSession = initializeSession(newSessionId, webSocketSession);
            sessionStore.put(newSessionId, newSession);

            // Registra la nueva sesión en AmazonMQ
            amazonMQCommunicator.addSession(newSessionId, webSocketSession);
            logger.info("Nueva sesión creada y registrada después de intentar reconectar: {}", newSessionId);

            // Reinicia el contador de reintentos
            reconnectAttempts = 0;
        } catch (Exception e) {
            logger.error("Error al crear y registrar una nueva sesión después de los intentos de reconexión fallidos", e);
        }
    }

//6
    /**
     * Enviar respuestas en un formato uniforme para todos los manejadores utilizando la instancia de Session.
     *
     * @param session      La sesión de OCPP que envía el mensaje.
     * @param messageId    El ID del mensaje.
     * @param action       La acción específica procesada.
     * @param confirmation La confirmación a enviar al cliente.
     * @throws IOException Si ocurre un error al enviar el mensaje.
     */
    private void sendResponse(Session session, WebSocketSession webSocketSession, String messageId, String action, Object confirmation) throws IOException {
        String response = objectMapper.writeValueAsString(new Object[]{3, messageId, confirmation});

        // Envía la respuesta a través de la sesión establecida
        session.sendTextMessage(response, webSocketSession);

        logger.info("Respuesta '{}' enviada para la sesión {}: messageId {}", action, session.getSessionId(), messageId);
    }

//7
    /**
     * Crea una instancia de `SessionEvents` para gestionar el ciclo de vida de la sesión.
     *
     * @param session La instancia de sesión que será gestionada.
     * @return Un objeto `SessionEvents` configurado para la sesión.
     */
    private SessionEvents createSessionEvents(Session session) {
        return new SessionEvents() {

            /**
             * Maneja la confirmación de una solicitud.
             *
             * @param uniqueId     El identificador único de la solicitud.
             * @param confirmation La confirmación recibida.
             */
            @Override
            public void handleConfirmation(String uniqueId, Confirmation confirmation) {
                logger.info("Confirmación recibida para la solicitud {}: {}", uniqueId, confirmation);
            }

            /**
             * Procesa una solicitud entrante y genera la confirmación correspondiente.
             *
             * @param request La solicitud recibida.
             * @return La confirmación generada, o `null` si no se soporta la solicitud.
             * @throws UnsupportedFeatureException Si la solicitud no es compatible.
             */
            @Override
            public Confirmation handleRequest(Request request) throws UnsupportedFeatureException {
                logger.debug("Solicitud recibida: {}", request);
                // Aquí puedes implementar la lógica para generar una respuesta según el tipo de solicitud.
                return null;  // Devuelve una respuesta si es necesario
            }

            /**
             * Completa asincrónicamente una solicitud pendiente.
             *
             * @param uniqueId     El identificador de la solicitud pendiente.
             * @param confirmation La confirmación que completa la solicitud.
             * @return `true` si se completa exitosamente; `false` de lo contrario.
             */
            @Override
            public boolean asyncCompleteRequest(String uniqueId, Confirmation confirmation) {
                // Aquí puedes incluir la lógica para completar una solicitud de manera asincrónica.
                return false;
            }

            /**
             * Gestiona errores que ocurren durante el procesamiento de solicitudes.
             *
             * @param uniqueId          El identificador único de la solicitud que provocó el error.
             * @param errorCode         El código de error.
             * @param errorDescription  La descripción del error.
             * @param payload           Datos adicionales sobre el error.
             */
            @Override
            public void handleError(String uniqueId, String errorCode, String errorDescription, Object payload) {
                logger.error("Error en la solicitud {}: {} - {}", uniqueId, errorCode, errorDescription);
            }

            /**
             * Maneja la apertura de la conexión de la sesión.
             * Registra la sesión en el `sessionStore`.
             */
            @Override
            public void handleConnectionOpened() {
                sessionStore.put(session.getSessionId(), session);
                logger.info("Conexión WebSocket abierta y sesión registrada: {}", session.getSessionId());
            }

            /**
             * Maneja el cierre de la conexión de la sesión.
             * Elimina la sesión del `sessionStore`.
             */
            @Override
            public void handleConnectionClosed() {
                logger.info("Conexión WebSocket cerrada para la sesión: {}", session.getSessionId());
                sessionStore.remove(session.getSessionId());
            }

            /**
             * Maneja errores internos de la sesión.
             *
             * @param ocppMessageId El identificador del mensaje OCPP que provocó el error.
             * @param internalError La descripción del error interno.
             * @param errorProcessingRequest La descripción del error al procesar la solicitud.
             * @param request La solicitud que provocó el error.
             */
            @Override
            public void onError(String ocppMessageId, String internalError, String errorProcessingRequest, Request request) {
                logger.error("Error interno en la solicitud OCPP {}: {}", ocppMessageId, internalError);
            }

            /**
             * Maneja la creación de una nueva sesión.
             *
             * @param sessionIndex  El identificador de la nueva sesión.
             * @param information   Información adicional de la sesión.
             */
            @Override
            public void newSession(UUID sessionIndex, SessionInformation information) {
                logger.info("Nueva sesión iniciada: {}", sessionIndex);
            }

            /**
             * Maneja la pérdida de una sesión.
             *
             * @param sessionIndex El identificador de la sesión perdida.
             */
            @Override
            public void lostSession(UUID sessionIndex) {
                logger.warn("Sesión perdida: {}", sessionIndex);
                sessionStore.remove(sessionIndex);
            }
        };
    }

//8
    /**
     * Envía un mensaje de error al cliente utilizando la instancia de Session.
     *
     * @param webSocketSession La sesión WebSocket.
     * @param messageId        El identificador del mensaje.
     * @param errorMessage     El mensaje de error a enviar.
     * @throws IOException Si ocurre un error al enviar el mensaje de error.
     */
    void sendError(Session session,WebSocketSession webSocketSession, String messageId, String errorMessage) throws IOException {
        logger.error("Error: {}", errorMessage);

        // Formato del mensaje de error
        String errorResponse = objectMapper.writeValueAsString(new Object[]{4, messageId, errorMessage});

        // Enviar mensaje de error a través de la sesión
        session.sendTextMessage(errorResponse, webSocketSession);
        logger.debug("Mensaje de error enviado: {}", errorMessage);
    }

//9
    /**
     * Procesa solicitudes de autorización, genera la confirmación y la envía al cliente.
     *
     * @param session        La instancia de sesión de OCPP.
     * @param webSocketSession La sesión WebSocket correspondiente a la instancia de sesión OCPP.
     * @param requestPayload El contenido de la solicitud de autorización.
     * @param messageId      El identificador del mensaje para rastrear la solicitud.
     * @throws IOException Si ocurre un error al procesar o enviar la respuesta.
     */
    void handleAuthorize(Session session, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            // Convierte el payload en una instancia de AuthorizeRequest
            AuthorizeRequest authorizeRequest = objectMapper.convertValue(requestPayload, AuthorizeRequest.class);

            // Enviar mensaje de log a Amazon MQ
            jsonServer.sendMessageToMQ("Authorize request received for idTag: " + authorizeRequest.getIdTag());

            // Configurar la respuesta de confirmación con la lógica de negocio
            IdTagInfo idTagInfo = new IdTagInfo();
            idTagInfo.setStatus(AuthorizationStatus.Accepted);  // Estado aceptado por defecto
            idTagInfo.setExpiryDate(ZonedDateTime.now().plusDays(30)); // Expira en 30 días

            // Crear y configurar el objeto de confirmación
            AuthorizeConfirmation confirmation = new AuthorizeConfirmation();
            confirmation.setIdTagInfo(idTagInfo);

            // Enviar la respuesta a través de la sesión OCPP
            sendResponse(session, webSocketSession, messageId, "Authorize", confirmation);
            logger.info("Authorize completado exitosamente para la sesión: {}", session.getSessionId());

        } catch (Exception e) {
            // Manejo de cualquier error que ocurra al procesar la solicitud
            logger.error("Error procesando Authorize para la sesión: {}", session.getSessionId(), e);
            sendError(session,webSocketSession,messageId, "Internal server error");
        }
    }

//10
    /**
     * Maneja la solicitud de notificación de inicio y envía la confirmación al cliente a través de la instancia de Session.
     *
     * @param session           La instancia de Session para gestionar la lógica de OCPP.
     * @param webSocketSession  La sesión WebSocket asociada.
     * @param requestPayload    El contenido de la solicitud.
     * @param messageId         El ID del mensaje.
     * @throws IOException                    Si ocurre un error al procesar o enviar la respuesta.
     * @throws IllegalArgumentException       Si el formato de la sesión es inválido.
     */
    void handleBootNotification(Session session, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            // Deserializar el payload a BootNotificationRequest
            BootNotificationRequest bootNotificationRequest = objectMapper.convertValue(requestPayload, BootNotificationRequest.class);

            // Log para visualizar el contenido de la solicitud
            String bootNotificationJson = objectMapper.writeValueAsString(bootNotificationRequest);
            logger.info("BootNotificationRequest recibido: {}", bootNotificationJson);

            // Enviar el mensaje a Amazon MQ
            jsonServer.sendMessageToMQ("Boot Notification request received: " + bootNotificationJson);

            // Configuración de la respuesta
            BootNotificationConfirmation confirmation = new BootNotificationConfirmation();
            confirmation.setStatus(RegistrationStatus.Accepted);
            confirmation.setCurrentTime(ZonedDateTime.now(ZoneOffset.UTC));
            confirmation.setInterval(300);

            // Enviar la respuesta al cliente
            sendResponse(session, webSocketSession, messageId, "BootNotification", confirmation);
            logger.info("BootNotification completado exitosamente para la sesión: {}", session.getSessionId());

        } catch (IllegalArgumentException e) {
            logger.error("Error: ID de sesión no es válido como UUID. ID: {}", session.getSessionId(), e);
            sendError(session,webSocketSession, messageId, "Invalid session ID format");
        } catch (Exception e) {
            logger.error("Error procesando BootNotification para la sesión: {}", session.getSessionId(), e);
            sendError(session,webSocketSession, messageId, "Internal server error");
        }
    }

//11
    /**
     * Maneja la solicitud de latido (heartbeat) y envía la confirmación al cliente utilizando la instancia de Session.
     *
     * @param session           La instancia de Session que gestiona la lógica de OCPP.
     * @param webSocketSession  La sesión WebSocket asociada.
     * @param requestPayload    El contenido de la solicitud.
     * @param messageId         El ID del mensaje.
     * @throws IOException Si ocurre un error al procesar o enviar la respuesta.
     */
    public void handleHeartbeat(Session session, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            // Deserialización del payload a HeartbeatRequest
            HeartbeatRequest heartbeatRequest = objectMapper.convertValue(requestPayload, HeartbeatRequest.class);

            // Log detallado del payload recibido
            String heartbeatJson = objectMapper.writeValueAsString(heartbeatRequest);
            logger.info("Heartbeat recibido en la sesión {}: {}", session.getSessionId(), heartbeatJson);

            // Enviar mensaje a Amazon MQ
            jsonServer.sendMessageToMQ("Heartbeat received");
            logger.debug("Heartbeat enviado a Amazon MQ para la sesión: {}", session.getSessionId());

            // Configuración de la respuesta de confirmación
            HeartbeatConfirmation confirmation = new HeartbeatConfirmation();
            confirmation.setCurrentTime(ZonedDateTime.now(ZoneOffset.UTC));

            // Enviar respuesta al cliente
            sendResponse(session, webSocketSession, messageId, "Heartbeat", confirmation);
            logger.info("Heartbeat completado exitosamente para la sesión: {}", session.getSessionId());

        } catch (IllegalArgumentException e) {
            logger.error("Error: ID de sesión no es válido como UUID. ID: {}", session.getSessionId(), e);
            sendError(session,webSocketSession, messageId, "Invalid session ID format");
        } catch (Exception e) {
            logger.error("Error procesando Heartbeat para la sesión: {}", session.getSessionId(), e);
            sendError(session,webSocketSession, messageId, "Internal server error");
        }
    }

//12
    /**
     * Maneja la solicitud de valores del medidor y envía la confirmación al cliente.
     *
     * @param session           La instancia de Session que gestiona la lógica de OCPP.
     * @param webSocketSession  La sesión WebSocket asociada.
     * @param requestPayload    El contenido de la solicitud.
     * @param messageId         El ID del mensaje.
     * @throws IOException si ocurre un error al procesar o enviar la respuesta.
     */
    public void handleMeterValues(Session session, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            logger.debug("Procesando MeterValues con messageId: {}", messageId);

            // Deserializar el payload a MeterValuesRequest
            MeterValuesRequest meterValuesRequest = objectMapper.convertValue(requestPayload, MeterValuesRequest.class);
            logger.debug("Payload deserializado: {}", meterValuesRequest);

            // Validar que el payload es correcto
            if (!meterValuesRequest.validate()) {
                throw new IllegalArgumentException("MeterValuesRequest inválido");
            }

            // Serializar a JSON para enviar a Amazon MQ y loggear
            String meterValuesJson = objectMapper.writeValueAsString(meterValuesRequest);
            logger.info("Meter values recibidos: {}", meterValuesJson);
            jsonServer.sendMessageToMQ("Meter values recibidos: " + meterValuesJson);

            // Crear y enviar la confirmación de MeterValues
            MeterValuesConfirmation confirmation = new MeterValuesConfirmation();
            sendResponse(session, webSocketSession, messageId, "MeterValues", confirmation);
            logger.info("MeterValues completado exitosamente para la sesión: {}", session.getSessionId());

        } catch (IllegalArgumentException e) {
            logger.error("Error: ID de sesión no es válido o payload inválido. ID: {}, Error: {}", session.getSessionId(), e.getMessage());
            sendError(session, webSocketSession, messageId, "Invalid session ID or payload");
        } catch (Exception e) {
            logger.error("Error procesando MeterValues para la sesión: {}, Error: {}", session.getSessionId(), e.getMessage(), e);
            sendError(session, webSocketSession, messageId, "Internal server error");
        }
    }

//13
    /**
     * Maneja la solicitud de inicio de transacción y envía la confirmación al cliente.
     *
     * @param session           La instancia de Session que gestiona la lógica de OCPP.
     * @param webSocketSession  La sesión WebSocket asociada.
     * @param requestPayload    El contenido de la solicitud.
     * @param messageId         El ID del mensaje.
     * @throws IOException si ocurre un error al procesar o enviar la respuesta.
     */
    void handleStartTransaction(Session session, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            logger.debug("Procesando StartTransaction con messageId: {}", messageId);

            // Deserializar el payload a StartTransactionRequest
            StartTransactionRequest startTransactionRequest = objectMapper.convertValue(requestPayload, StartTransactionRequest.class);
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
            sendResponse(session, webSocketSession, messageId, "StartTransaction", confirmation);
            logger.info("Respuesta enviada para StartTransaction con ID de mensaje {}: {}", messageId, confirmation);
            logger.info("StartTransaction completado exitosamente para la sesión: {}", session.getSessionId());

        } catch (IllegalArgumentException e) {
            logger.error("Error: ID de sesión no es válido como UUID. ID: {}", session.getSessionId(), e);
            sendError(session,webSocketSession, messageId, "Invalid session ID format");
        } catch (Exception e) {
            logger.error("Error procesando StartTransaction para la sesión: {}", session.getSessionId(), e);
            sendError(session,webSocketSession, messageId, "Internal server error");
        }
    }

//14
    /**
     * Maneja la solicitud de finalización de transacción y envía la confirmación al cliente.
     *
     * @param session           La instancia de Session que gestiona la lógica de OCPP.
     * @param webSocketSession  La sesión WebSocket asociada.
     * @param requestPayload    El contenido de la solicitud.
     * @param messageId         El ID del mensaje.
     * @throws IOException si ocurre un error al procesar o enviar la respuesta.
     */
    void handleStopTransaction(Session session, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            // Deserializar el payload a StopTransactionRequest
            StopTransactionRequest stopTransactionRequest = objectMapper.convertValue(requestPayload, StopTransactionRequest.class);

            // Log de la solicitud para referencia
            String stopTransactionJson = objectMapper.writeValueAsString(stopTransactionRequest);
            logger.info("StopTransaction request recibido: {}", stopTransactionJson);

            // Enviar el mensaje a Amazon MQ
            jsonServer.sendMessageToMQ("Stop Transaction request received: " + stopTransactionJson);

            // Configuración de los valores de respuesta
            IdTagInfo idTagInfo = new IdTagInfo();
            idTagInfo.setStatus(AuthorizationStatus.Accepted);
            idTagInfo.setExpiryDate(ZonedDateTime.now().plusDays(30));

            // Crear la confirmación de StopTransaction y asignar los detalles
            StopTransactionConfirmation confirmation = new StopTransactionConfirmation();
            confirmation.setIdTagInfo(idTagInfo);

            // Enviar la respuesta al cliente
            sendResponse(session, webSocketSession, messageId, "StopTransaction", confirmation);
            logger.info("StopTransaction completado exitosamente para la sesión: {}", session.getSessionId());

        } catch (IllegalArgumentException e) {
            logger.error("Error: ID de sesión no es válido como UUID. ID: {}", session.getSessionId(), e);
            sendError(session,webSocketSession, messageId, "Invalid session ID format");
        } catch (Exception e) {
            logger.error("Error procesando StopTransaction para la sesión: {}", session.getSessionId(), e);
            sendError(session,webSocketSession, messageId, "Internal server error");
        }
    }

//15
    /**
     * Maneja las notificaciones de estado (StatusNotification) enviadas desde un punto de carga.
     *
     * Este método procesa la solicitud de notificación de estado, la deserializa, y luego
     * envía el mensaje a un servidor de mensajes (Amazon MQ). Finalmente, envía una respuesta
     * de confirmación al cliente.
     *
     * @param session La sesión OCPP asociada a la conexión WebSocket.
     * @param webSocketSession La sesión WebSocket actual.
     * @param requestPayload El payload de la solicitud de StatusNotification.
     * @param messageId El ID único del mensaje OCPP.
     * @throws IOException Si ocurre un error durante el envío del mensaje o el procesamiento de la sesión.
     */
    void handleStatusNotification(Session session, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            logger.debug("Procesando StatusNotification para la sesión {} con messageId: {}", session.getSessionId(), messageId);

            // Deserialización del payload en StatusNotificationRequest
            StatusNotificationRequest statusNotificationRequest = objectMapper.convertValue(requestPayload, StatusNotificationRequest.class);
            logger.debug("Payload de StatusNotification deserializado: {}", statusNotificationRequest);

            // Enviar el mensaje a Amazon MQ
            String messageToMQ = "Status Notification received: " + statusNotificationRequest;
            jsonServer.sendMessageToMQ(messageToMQ);
            logger.info("Mensaje de StatusNotification enviado a Amazon MQ: {}", messageToMQ);

            // Crear la confirmación de StatusNotification
            StatusNotificationConfirmation confirmation = new StatusNotificationConfirmation();

            // Enviar la respuesta de confirmación al cliente
            sendResponse(session, webSocketSession, messageId, "StatusNotification", confirmation);
            logger.info("StatusNotification completado exitosamente para la sesión: {}", session.getSessionId());

        } catch (IllegalArgumentException e) {
            // Manejar errores de formato de ID
            logger.error("Error: ID de sesión no válido en StatusNotification. ID: {}", session.getSessionId(), e);
            sendError(session, webSocketSession, messageId, "Invalid session ID format");
        } catch (Exception e) {
            // Manejar cualquier otro error inesperado
            logger.error("Error procesando StatusNotification para la sesión: {}", session.getSessionId(), e);
            sendError(session, webSocketSession, messageId, "Internal server error");
        }
    }

//16
    /**
     * Maneja la solicitud de transferencia de datos y envía la confirmación al cliente.
     *
     * @param session          La instancia de sesión OCPP.
     * @param webSocketSession La sesión WebSocket asociada.
     * @param requestPayload   El contenido de la solicitud.
     * @param messageId        El ID del mensaje.
     * @throws IOException si ocurre un error al procesar o enviar la respuesta.
     */
    void handleDataTransfer(Session session, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            // Convertir el payload en una instancia de DataTransferRequest
            DataTransferRequest dataTransferRequest = objectMapper.convertValue(requestPayload, DataTransferRequest.class);

            // Validar que los campos requeridos estén presentes
            if (dataTransferRequest.getVendorId() == null || dataTransferRequest.getMessageId() == null) {
                logger.error("Campos requeridos faltantes en DataTransferRequest");
                sendError(session,webSocketSession, messageId, "Missing required fields in DataTransferRequest");
                return;
            }

            // Enviar el mensaje a Amazon MQ con los detalles de DataTransfer
            jsonServer.sendMessageToMQ("Data Transfer request received: " + dataTransferRequest.toString());

            // Determinar el estado de la respuesta en función de la lógica de negocio
            DataTransferStatus dataTransferStatus = "Vendor123".equals(dataTransferRequest.getVendorId())
                    ? DataTransferStatus.Accepted
                    : DataTransferStatus.Rejected;

            // Crear el objeto de confirmación con los campos configurados
            DataTransferConfirmation confirmation = new DataTransferConfirmation();
            confirmation.setStatus(dataTransferStatus);
            confirmation.setData("Datos procesados correctamente para: " + dataTransferRequest.getMessageId());

            // Enviar la respuesta al cliente
            sendResponse(session, webSocketSession, messageId, "DataTransfer", confirmation);
            logger.info("DataTransfer completado exitosamente para la sesión: {}", session.getSessionId());

        } catch (IllegalArgumentException e) {
            logger.error("Error: ID de sesión no es válido como UUID. ID: {}", session.getSessionId(), e);
            sendError(session,webSocketSession, messageId, "Invalid session ID format");
        } catch (Exception e) {
            logger.error("Error procesando DataTransfer para la sesión: {}", session.getSessionId(), e);
            sendError(session,webSocketSession, messageId, "Internal server error");
        }
    }

//17
    /**
     * Maneja la solicitud de disponibilidad del conector y envía una confirmación al cliente.
     *
     * @param session          La instancia de sesión OCPP.
     * @param webSocketSession La sesión WebSocket asociada.
     * @param requestPayload   El contenido de la solicitud.
     * @param messageId        El ID del mensaje.
     * @throws IOException si ocurre un error al procesar o enviar la respuesta.
     */
    void handleAvailable(Session session, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        // Conversión del payload
        StatusNotificationRequest statusRequest = objectMapper.convertValue(requestPayload, StatusNotificationRequest.class);
        statusRequest.setStatus(ChargePointStatus.Available);

        // Enviar mensaje de disponibilidad a Amazon MQ
        jsonServer.sendMessageToMQ("Connector is now Available");

        // Crear la confirmación de estado y enviar la respuesta
        StatusNotificationConfirmation confirmation = new StatusNotificationConfirmation();
        sendResponse(session, webSocketSession, messageId, "StatusNotification", confirmation);

        logger.info("Conector configurado como Disponible para la sesión: {}", session.getSessionId());
    }

//18
    /**
     * Maneja la solicitud de preparación del conector y envía una confirmación al cliente.
     *
     * @param session          La instancia de sesión OCPP.
     * @param webSocketSession La sesión WebSocket asociada.
     * @param requestPayload   El contenido de la solicitud.
     * @param messageId        El ID del mensaje.
     * @throws IOException si ocurre un error al procesar o enviar la respuesta.
     */
    void handlePreparing(Session session, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        // Conversión del payload
        StatusNotificationRequest statusRequest = objectMapper.convertValue(requestPayload, StatusNotificationRequest.class);
        statusRequest.setStatus(ChargePointStatus.Preparing);

        // Enviar mensaje de preparación a Amazon MQ
        jsonServer.sendMessageToMQ("Connector is Preparing to start a session");

        // Crear la confirmación de estado y enviar la respuesta
        StatusNotificationConfirmation confirmation = new StatusNotificationConfirmation();
        sendResponse(session, webSocketSession, messageId, "StatusNotification", confirmation);

        logger.info("Conector en estado de Preparación para la sesión: {}", session.getSessionId());
    }

//19
    /**
     * Maneja la solicitud de inicio de carga y envía la confirmación al cliente.
     *
     * @param ocppSession      La instancia de sesión OCPP.
     * @param webSocketSession La sesión WebSocket asociada.
     * @param requestPayload   El contenido de la solicitud.
     * @param messageId        El ID del mensaje.
     * @throws IOException si ocurre un error al procesar o enviar la respuesta.
     */
    void handleCharging(Session ocppSession, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        // Conversión del payload
        StatusNotificationRequest statusRequest = objectMapper.convertValue(requestPayload, StatusNotificationRequest.class);
        statusRequest.setStatus(ChargePointStatus.Charging);

        // Enviar mensaje de carga a Amazon MQ
        jsonServer.sendMessageToMQ("Connector is now Charging");

        // Crear la confirmación de estado y enviar la respuesta
        StatusNotificationConfirmation confirmation = new StatusNotificationConfirmation();
        sendResponse(ocppSession, webSocketSession, messageId, "StatusNotification", confirmation);

        logger.info("Sesión de carga iniciada para la sesión OCPP: {}", ocppSession.getSessionId());
    }

//20
    /**
     * Maneja las solicitudes entrantes de TriggerMessage del lado del servidor utilizando ServerRemoteTriggerProfile.
     *
     * @param ocppSession      La instancia de la sesión OCPP.
     * @param webSocketSession La sesión WebSocket asociada.
     * @param requestPayload   El contenido de la solicitud.
     * @param messageId        El ID único del mensaje.
     * @throws IOException si ocurre un error durante el procesamiento.
     */
    void handleServerTriggerMessage(Session ocppSession, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            // Convierte el payload en una instancia de TriggerMessageRequest
            TriggerMessageRequest triggerMessageRequest = objectMapper.convertValue(requestPayload, TriggerMessageRequest.class);

            // Maneja la solicitud con el ServerRemoteTriggerProfile
            Confirmation confirmation = serverRemoteTriggerProfile.handleRequest(ocppSession.getSessionId(), triggerMessageRequest);

            // Envía la respuesta al cliente
            sendResponse(ocppSession, webSocketSession, messageId, "ServerTriggerMessage", confirmation);
            logger.info("Server-triggered message handled successfully for session OCPP: {}", ocppSession.getSessionId());
        } catch (Exception e) {
            logger.error("Error processing ServerTriggerMessage", e);
            sendError(ocppSession,webSocketSession, messageId, "Error in ServerTriggerMessage: " + e.getMessage());
        }
    }

//21
    /**
     * Maneja la solicitud ChangeAvailability y delega la gestión al DefaultClientCoreEventHandler.
     *
     * @param ocppSession      La instancia de la sesión OCPP.
     * @param webSocketSession La sesión WebSocket asociada.
     * @param requestPayload   El contenido de la solicitud.
     * @param messageId        El ID del mensaje.
     * @throws IOException si ocurre un error al procesar o enviar la respuesta.
     */
    void handleChangeAvailability(Session ocppSession, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            // Convierte el payload en una instancia de ChangeAvailabilityRequest
            ChangeAvailabilityRequest changeAvailabilityRequest = objectMapper.convertValue(requestPayload, ChangeAvailabilityRequest.class);

            // Delegación de la solicitud al manejador de eventos del cliente
            Confirmation confirmation = clientCoreEventHandler.handleChangeAvailabilityRequest(changeAvailabilityRequest);

            // Envía la respuesta al cliente
            sendResponse(ocppSession, webSocketSession, messageId, "ChangeAvailability", confirmation);
            logger.info("ChangeAvailability manejado exitosamente para la sesión: {}", ocppSession.getSessionId());
        } catch (Exception e) {
            // Maneja cualquier error que ocurra durante el procesamiento
            logger.error("Error processing ChangeAvailability", e);
            sendError(ocppSession,webSocketSession, messageId, "Error in ChangeAvailability: " + e.getMessage());
        }
    }

//22
    /**
     * Maneja la solicitud GetConfiguration y delega el procesamiento al DefaultClientCoreEventHandler.
     *
     * @param ocppSession      La instancia de la sesión OCPP.
     * @param webSocketSession La sesión WebSocket asociada.
     * @param requestPayload   El contenido de la solicitud.
     * @param messageId        El ID del mensaje.
     * @throws IOException si ocurre un error al procesar o enviar la respuesta.
     */
    void handleGetConfiguration(Session ocppSession, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            // Convierte el payload a una instancia de GetConfigurationRequest
            GetConfigurationRequest getConfigurationRequest = objectMapper.convertValue(requestPayload, GetConfigurationRequest.class);

            // Llama al manejador de eventos del cliente para procesar la solicitud
            Confirmation confirmation = clientCoreEventHandler.handleGetConfigurationRequest(getConfigurationRequest);

            // Envía la respuesta al cliente
            sendResponse(ocppSession, webSocketSession, messageId, "GetConfiguration", confirmation);
            logger.info("GetConfiguration manejado exitosamente para la sesión: {}", ocppSession.getSessionId());
        } catch (Exception e) {
            // Maneja cualquier excepción ocurrida durante el procesamiento
            logger.error("Error processing GetConfiguration", e);
            sendError(ocppSession,webSocketSession, messageId, "Error in GetConfiguration: " + e.getMessage());
        }
    }

//23
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
     * @param ocppSession     La instancia de la sesión OCPP.
     * @param webSocketSession La sesión WebSocket activa.
     * @param requestPayload  El contenido de la solicitud RemoteStartTransaction.
     * @param messageId       El ID del mensaje que se está procesando.
     * @throws IOException en caso de error al enviar la respuesta.
     */
    /**
     * Maneja la solicitud de inicio de transacción remota y envía la confirmación al cliente.
     *
     * @param session           La instancia de Session que gestiona la lógica de OCPP.
     * @param webSocketSession  La sesión WebSocket asociada.
     * @param requestPayload    El contenido de la solicitud.
     * @param messageId         El ID del mensaje.
     * @throws IOException si ocurre un error al procesar o enviar la respuesta.
     */
    public void handleRemoteStartTransaction(Session session, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            logger.debug("Procesando RemoteStartTransaction con messageId: {}", messageId);

            // Validar que el payload no sea nulo
            if (requestPayload == null) {
                throw new IllegalArgumentException("El payload de la solicitud no puede ser nulo.");
            }

            // Deserializar el payload en RemoteStartTransactionRequest
            RemoteStartTransactionRequest remoteStartRequest = objectMapper.convertValue(requestPayload, RemoteStartTransactionRequest.class);
            logger.debug("Payload deserializado: {}", objectMapper.writeValueAsString(remoteStartRequest));

            // Validar la solicitud antes de proceder
            if (!remoteStartRequest.validate()) {
                throw new IllegalArgumentException("La solicitud de RemoteStartTransaction no es válida.");
            }

            // Validar el perfil de carga si está presente
            if (remoteStartRequest.getChargingProfile() != null && !remoteStartRequest.getChargingProfile().validate()) {
                throw new IllegalArgumentException("El perfil de carga no es válido.");
            }

            // Verificar si la sesión es válida y la conexión WebSocket está abierta
            if (session == null || !webSocketSession.isOpen()) {
                throw new IllegalStateException("Sesión OCPP no encontrada o WebSocket cerrado para el ID proporcionado.");
            }

            // Verificar si AmazonMQCommunicator está conectado, de lo contrario reconectar
            if (amazonMQCommunicator == null || !amazonMQCommunicator.isConnected()) {
                logger.warn("Reconectando AmazonMQCommunicator...");
                if (!amazonMQCommunicator.isConnected()) {
                    throw new IllegalStateException("El radio no está inicializado correctamente después de intentar reconectar.");
                }
            }

            // Enviar el mensaje de RemoteStartTransaction a Amazon MQ para logging
            jsonServer.sendMessageToMQ("RemoteStartTransaction request received for idTag: " + remoteStartRequest.getIdTag());
            logger.info("Mensaje enviado a Amazon MQ para RemoteStartTransaction con idTag: {}", remoteStartRequest.getIdTag());

            // Enviar la solicitud de inicio de transacción remota a la estación de carga
            session.sendRequest("RemoteStartTransaction", remoteStartRequest, messageId);

            // Simular una confirmación de transacción
            RemoteStartTransactionConfirmation confirmation = new RemoteStartTransactionConfirmation();
            confirmation.setStatus(RemoteStartStopStatus.Accepted); // Aceptar la transacción

            // Enviar la respuesta de confirmación de la transacción al cliente
            sendResponse(session, webSocketSession, messageId, "RemoteStartTransaction", confirmation);

            logger.info("RemoteStartTransaction completado exitosamente para la sesión: {}", session.getSessionId());

        } catch (IllegalArgumentException e) {
            logger.error("Error en los argumentos de RemoteStartTransaction: {}", e.getMessage());
            sendError(session, webSocketSession, messageId, "NotSupported, La solicitud contiene argumentos inválidos: " + e.getMessage());
        } catch (IllegalStateException e) {
            logger.error("Error de estado en RemoteStartTransaction: {}", e.getMessage());
            sendError(session, webSocketSession, messageId, "InternalError, Error de estado: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error procesando RemoteStartTransaction para la sesión: {}", session != null ? session.getSessionId() : "Sesión no disponible", e);
            sendError(session, webSocketSession, messageId, "InternalError, Error procesando RemoteStartTransaction: " + e.getMessage());
        }
    }




//24
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
     * @param ocppSession      La instancia de la sesión OCPP.
     * @param webSocketSession La sesión WebSocket activa.
     * @param requestPayload   El contenido de la solicitud RemoteStopTransaction.
     * @param messageId        El ID del mensaje que se está procesando.
     * @throws IOException en caso de error al enviar la respuesta.
     */
    void handleRemoteStopTransaction(Session ocppSession, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            // Deserializa el payload en un objeto RemoteStopTransactionRequest
            RemoteStopTransactionRequest remoteStopTransactionRequest = objectMapper.convertValue(requestPayload, RemoteStopTransactionRequest.class);

            // Procesa la lógica específica para detener la transacción remota aquí, si es necesario

            // Crea la confirmación de la respuesta
            RemoteStopTransactionConfirmation confirmation = new RemoteStopTransactionConfirmation();

            // Envía la respuesta de confirmación al cliente
            sendResponse(ocppSession, webSocketSession, messageId, "RemoteStopTransaction", confirmation);
            logger.info("RemoteStopTransaction completado exitosamente para la sesión: {}", ocppSession.getSessionId());

        } catch (Exception e) {
            // Manejo de errores en el proceso y envío de mensaje de error
            logger.error("Error processing RemoteStopTransaction", e);
            sendError(ocppSession,webSocketSession, messageId, "Error in RemoteStopTransaction: " + e.getMessage());
        }
    }
//25
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
     * @param ocppSession      La instancia de la sesión OCPP.
     * @param webSocketSession La sesión WebSocket activa.
     * @param requestPayload   El contenido de la solicitud ClearCache.
     * @param messageId        El ID del mensaje que se está procesando.
     * @throws IOException en caso de error al enviar la respuesta.
     */
    void handleClearCache(Session ocppSession, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            // Deserializa el requestPayload en un objeto ClearCacheRequest
            ClearCacheRequest clearCacheRequest = objectMapper.convertValue(requestPayload, ClearCacheRequest.class);

            // Procesamiento de la solicitud ClearCache (se puede aplicar lógica adicional si es necesario)
            jsonServer.sendMessageToMQ("ClearCache request received: " + clearCacheRequest);

            // Crear y enviar la confirmación al cliente
            ClearCacheConfirmation confirmation = new ClearCacheConfirmation();
            sendResponse(ocppSession, webSocketSession, messageId, "ClearCache", confirmation);
            logger.info("ClearCache completado exitosamente para la sesión: {}", ocppSession.getSessionId());

        } catch (Exception e) {
            // Manejo de excepciones y envío de mensaje de error
            logger.error("Error processing ClearCache", e);
            sendError(ocppSession,webSocketSession, messageId, "Error in ClearCache: " + e.getMessage());
        }
    }

//26
    /**
     * Procesa la solicitud ChangeConfiguration y envía la confirmación correspondiente al cliente.
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
     * @param ocppSession      La instancia de la sesión OCPP.
     * @param webSocketSession La sesión WebSocket activa.
     * @param requestPayload   El contenido de la solicitud ChangeConfiguration.
     * @param messageId        El ID del mensaje que se está procesando.
     * @throws IOException en caso de error al enviar la respuesta.
     */
    void handleChangeConfiguration(Session ocppSession, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            // Conversión del payload a ChangeConfigurationRequest
            ChangeConfigurationRequest changeConfigurationRequest = objectMapper.convertValue(requestPayload, ChangeConfigurationRequest.class);

            // Lógica de procesamiento específica para ChangeConfiguration
            // (Aquí se realizarían las validaciones o acciones necesarias con changeConfigurationRequest)

            ChangeConfigurationConfirmation confirmation = new ChangeConfigurationConfirmation();

            // Envía la respuesta de confirmación al cliente
            sendResponse(ocppSession, webSocketSession, messageId, "ChangeConfiguration", confirmation);
            logger.info("ChangeConfiguration completado exitosamente para la sesión: {}", ocppSession.getSessionId());

        } catch (Exception e) {
            // Manejo de excepciones y envío de mensaje de error
            logger.error("Error processing ChangeConfiguration", e);
            sendError(ocppSession,webSocketSession, messageId, "Error in ChangeConfiguration: " + e.getMessage());
        }
    }

    public Session getSessionById(String sessionId) {
        try {
            UUID uuid = UUID.fromString(sessionId);
            return sessionStore.get(uuid);
        } catch (IllegalArgumentException e) {
            logger.error("Formato de UUID inválido para el ID de sesión: {}", sessionId, e);
            return null;
        }
    }

    public WebSocketSession getWebSocketSessionById(String sessionId) {
        try {
            UUID uuid = UUID.fromString(sessionId);
            return webSocketSessionStorage.get(uuid);
        } catch (IllegalArgumentException e) {
            logger.error("Formato de UUID inválido para el ID de sesión: {}", sessionId, e);
            return null;
        }
    }

}
