package com.eVolGreen.eVolGreen.Configurations.MQ;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.UnsupportedFeatureException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.ConfirmationCompletedHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.SessionInformation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Queue;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.TimeoutHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.TimeoutTimer;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Handler.DefaultClientCoreEventHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Handler.ServerCoreEventHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.ClientRemoteTriggerProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.ServerCoreProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.ServerRemoteTriggerProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.JSONServer;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.Enums.AuthorizationStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.Enums.DataTransferStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.Enums.RegistrationStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.Enums.RemoteStartStopStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.Utils.IdTagInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.Utils.KeyValueType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums.ChargePointStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.RemoteTrigger.Confirmations.TriggerMessageConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.RemoteTrigger.Request.Enums.TriggerMessageRequestType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.RemoteTrigger.Request.TriggerMessageRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.SecurityEventNotificationConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.SecurityEventNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Confirmations.ClearChargingProfileConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Confirmations.Enums.ChargingProfileStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Confirmations.Enums.ClearChargingProfileStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Confirmations.Enums.GetCompositeScheduleStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Confirmations.GetCompositeScheduleConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Confirmations.SetChargingProfileConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Request.ClearChargingProfileRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Request.GetCompositeScheduleRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Request.SetChargingProfileRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.TimeoutSessionDecorator;
import com.eVolGreen.eVolGreen.Services.AccountService.UtilService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;


import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.*;

/**
 * {@code WebSocketHandler} maneja las conexiones WebSocket, gestionando el ciclo de vida de las sesiones
 * y redireccionando solicitudes como BootNotification y Authorize al {@link JSONServer}.
 */
@AllArgsConstructor
@Component
public class WebSocketHandler extends AbstractWebSocketHandler {

    private static final long MAX_PING_INTERVAL_MS = 30000; // 30 segundos
    private final UtilService utilService;

    private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);

    public static final Map<UUID, Session> sessionStore = new ConcurrentHashMap<>();
    public static final Map<UUID, WebSocketSession> webSocketSessionStorage = new ConcurrentHashMap<>();

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
    private ConfirmationCompletedHandler completedHandler;


    private final WebSocketMetricsConfig webSocketMetricsConfig;
    private static final Map<String, UUID> chargePointIdToSessionIdMap = new ConcurrentHashMap<>();
    private final Map<String, Long> sessionLastPingTime = new ConcurrentHashMap<>();
    private final Map<String, CompletableFuture<GetConfigurationConfirmation>> getConfigFutures = new ConcurrentHashMap<>();



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
//     * @param amazonMQCommunicator    Comunicador para integración con Amazon MQ.
     */
    @Autowired
    public WebSocketHandler(UtilService utilService, ISessionFactory sessionFactory, Communicator communicator,
                            JSONServer jsonServer, ServerCoreProfile coreProfile,
                            Queue queue, PromiseFulfiller fulfiller, IFeatureRepository featureRepository,
                            WebSocketMetricsConfig webSocketMetricsConfig) {

        this.sessionFactory = sessionFactory;
        this.utilService = utilService;
        this.communicator = communicator;
        this.jsonServer = jsonServer;
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

        // Añadir el perfil al JSONServer
        jsonServer.addFeatureProfile(coreProfile);

        // Configuración de otros perfiles de servidor si es necesario
        this.serverRemoteTriggerProfile = new ServerRemoteTriggerProfile();
        jsonServer.addFeatureProfile(serverRemoteTriggerProfile);

        // Registro del evento de perfil de cliente en el manejador de eventos principal
        this.clientCoreEventHandler = new DefaultClientCoreEventHandler();

        this.webSocketMetricsConfig = webSocketMetricsConfig;
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
        // Obtener el subprotocolo desde los atributos de la sesión
        String acceptedProtocol = (String) webSocketSession.getAttributes().get("subProtocol");
        logger.debug("Subprotocolo aceptado en la sesión (después de handshake): {}", acceptedProtocol);

        // Verificar si el subprotocolo es el esperado
        if (!"ocpp1.6".equals(acceptedProtocol)) {
            handleUnsupportedProtocol(webSocketSession, acceptedProtocol);
            return;
        }

        try {
            // Obtener el chargePointId desde los atributos de la sesión
            String chargePointId = (String) webSocketSession.getAttributes().get("chargePointId");
            if (chargePointId == null) {
                logger.error("Error: chargePointId no encontrado en los atributos de la sesión.");
                webSocketSession.close(CloseStatus.BAD_DATA);
                return;
            }

            // Utilizar el ID proporcionado por la sesión WebSocket en lugar de generar uno nuevo
            String sessionId = webSocketSession.getId();
            UUID sessionUUID = UUID.fromString(sessionId);

            // Crear e inicializar la sesión OCPP con el ID de la sesión WebSocket
            Session session = initializeSession(sessionUUID, webSocketSession);
            session.setChargePointId(chargePointId);  // Asignar el chargePointId a la sesión

            // Vincular el sessionUUID con la WebSocketSession
            webSocketSession.getAttributes().put("sessionId", sessionUUID);

            // Almacenar la sesión en sessionStore
            sessionStore.put(sessionUUID, session);

            // Almacenar la sesión WebSocket en webSocketSessionStorage
            webSocketSessionStorage.put(sessionUUID, webSocketSession);

            webSocketSession.getAttributes().put("chargePointId", chargePointId);

            // Vincular el sessionUUID con el chargePointId
            chargePointIdToSessionIdMap.put(chargePointId, sessionUUID);

            // Incrementar la métrica de conexiones activas
            webSocketMetricsConfig.incrementActiveConnections();

            // Configurar el intervalo de Heartbeat (por ejemplo, 30 segundos)
            String  Key = "HeartbeatInterval";
            String Value = "30";
            ChangeConfigurationRequest changeConfigRequest = new ChangeConfigurationRequest(Key, Value);
            session.sendRequest("ChangeConfiguration", changeConfigRequest, UUID.randomUUID().toString());

            logger.info("Configurado HeartbeatInterval a 30 segundos para la sesión: {}", sessionUUID);

            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate(() -> {
                try {
                    verifyActiveSessions();
                } catch (Exception e) {
                    logger.error("Error verificando sesiones activas", e);
                }
            }, 0, 30, TimeUnit.SECONDS);


//            // Registrar en Amazon MQ
//            amazonMQCommunicator.addSession(sessionUUID, webSocketSession);

//            logger.info("Sesión WebSocket registrada exitosamente con ID: {} y chargePointId: {}", sessionUUID, chargePointId);

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
        if (payload == null || payload.isEmpty()) {
            logger.warn("Mensaje vacío recibido.");
            return;
        }

        // Intentar parsear el payload como un array JSON.
        Object[] array;
        try {
            array = objectMapper.readValue(payload, Object[].class);
        } catch (Exception e) {
            logger.warn("Error al parsear el payload: {}", payload, e);
            sendError(session, webSocketSession, null, "Mensaje no reconocido.");
            return;
        }

        if (array.length < 2) {
            logger.warn("Mensaje con estructura incorrecta: {}", payload);
            sendError(session, webSocketSession, null, "Mensaje con estructura incorrecta.");
            return;
        }

        // Obtener el tipo de mensaje
        Integer messageType = (Integer) array[0];
        String messageId = (String) array[1];

        switch (messageType) {
            case 2: // Call
                if (array.length < 4) {
                    logger.warn("Mensaje Call con estructura incorrecta: {}", payload);
                    sendError(session, webSocketSession, messageId, "Mensaje Call con estructura incorrecta.");
                    return;
                }
                String action = (String) array[2];
                Object requestPayload = array[3];
                logger.debug("Acción recibida: {} con payload: {}", action, requestPayload != null ? requestPayload.toString() : "null");

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
                    case "GetConfiguration" -> handleGetConfigurationRequest(session, webSocketSession, requestPayload, messageId);
                    case "RemoteStartTransaction" -> handleRemoteStartTransaction(session, webSocketSession, requestPayload, messageId);
                    case "RemoteStopTransaction" -> handleRemoteStopTransaction(session, webSocketSession, requestPayload, messageId);
                    case "ClearCache" -> handleClearCache(session, webSocketSession, requestPayload, messageId);
                    case "ChangeConfiguration" -> handleChangeConfiguration(session, webSocketSession, requestPayload, messageId);
                    case "ClearChargingProfile" -> handleClearChargingProfile(session, webSocketSession, requestPayload, messageId);
                    case "GetCompositeSchedule" -> handleGetCompositeSchedule(session, webSocketSession, requestPayload, messageId);
                    case "SetChargingProfile" -> handleSetChargingProfile(session, webSocketSession, requestPayload, messageId);
                    case "UnlockConnector" -> handleUnlockConnector(session, webSocketSession, requestPayload, messageId);
                    case "Reset" -> handleReset(session, webSocketSession, requestPayload, messageId);
                    case "SecurityEventNotification" -> handleSecurityEventNotification(session, webSocketSession, requestPayload, messageId);
                    case "Ping" -> handlePingMessage(webSocketSession, new PingMessage((ByteBuffer) array[3]));

                    default -> {
                        logger.warn("Acción no soportada: {}", action);
                        sendError(session, webSocketSession, messageId, "Acción no soportada: " + action);
                    }
                }
                break;

            case 3: // CallResult
                if (array.length < 3) {
                    logger.warn("Mensaje CallResult con estructura incorrecta: {}", payload);
                    sendError(session, webSocketSession, messageId, "Mensaje CallResult con estructura incorrecta.");
                    return;
                }
                Object responsePayload = array[2];
                logger.debug("CallResult recibido para messageId: {} con payload: {}", messageId, responsePayload != null ? responsePayload.toString() : "null");
                handleCallResult(session, messageId, responsePayload);
                break;

            case 4: // CallError
                if (array.length < 5) {
                    logger.warn("Mensaje CallError con estructura incorrecta: {}", payload);
                    sendError(session, webSocketSession, messageId, "Mensaje CallError con estructura incorrecta.");
                    return;
                }
                String errorCode = (String) array[2];
                String errorDescription = (String) array[3];
                Object errorDetails = array[4];
                logger.error("CallError recibido para messageId: {} - Código: {}, Descripción: {}, Detalles: {}", messageId, errorCode, errorDescription, errorDetails);
                handleCallError(session, messageId, errorCode, errorDescription, errorDetails);
                break;

            default:
                logger.warn("Tipo de mensaje desconocido: {}", messageType);
                sendError(session, webSocketSession, messageId, "Tipo de mensaje desconocido: " + messageType);
                break;
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
            UUID sessionId = (UUID) webSocketSession.getAttributes().get("sessionId");
            if (sessionId != null) {
                sessionStore.remove(sessionId);
                webSocketSessionStorage.remove(sessionId);
            }

            // Eliminar el mapeo chargePointId -> sessionId
            String chargePointId = (String) webSocketSession.getAttributes().get("chargePointId");
            if (chargePointId != null) {
                chargePointIdToSessionIdMap.remove(chargePointId);
            }

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
        webSocketSessionStorage.remove(sessionId);

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

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if (message instanceof PingMessage pingMessage) {
            logger.debug("Ping recibido desde la sesión: {}", session.getId());
            handlePingMessage(session, pingMessage);
        } else if (message instanceof PongMessage pongMessage) {
            logger.debug("Pong recibido desde la sesión: {}", session.getId());
            handlePongMessage(session, pongMessage);
        } else if (message instanceof TextMessage textMessage) {
            handleTextMessage(session, textMessage);
        } else {
            logger.warn("Tipo de mensaje no reconocido: {}", message.getClass().getSimpleName());
        }
    }


    private void attemptReconnection(WebSocketSession webSocketSession) {
        try {
            if (reconnectAttempts < MAX_RECONNECT_ATTEMPTS) {
                reconnectAttempts++;
                logger.warn("Intento de reconexión {}/{} para la sesión: {}", reconnectAttempts, MAX_RECONNECT_ATTEMPTS, webSocketSession.getId());

                // Cierra la sesión actual de manera controlada
                closeSessionWithError(webSocketSession);

                // Obtiene la sesión OCPP asociada para intentar la reconexión
                UUID sessionUUID = (UUID) webSocketSession.getAttributes().get("sessionId");
                Session ocppSession = sessionStore.get(sessionUUID);

                if (ocppSession != null) {
                    logger.info("Iniciando intento de reconexión para la sesión OCPP con ID: {}", sessionUUID);
                    reconnect(webSocketSession, ocppSession);
                } else {
                    logger.warn("Sesión OCPP no encontrada para el ID: {}. Eliminando la sesión de la lista activa.", webSocketSession.getId());
                    sessions.remove(webSocketSession);
                }
            } else {
                logger.error("Se alcanzó el máximo de intentos de reconexión para la sesión: {}. Cerrando sesión definitivamente.", webSocketSession.getId());
                closeSessionPermanently(webSocketSession);
            }
        } catch (Exception e) {
            logger.error("Error inesperado durante la reconexión de la sesión: {}", webSocketSession.getId(), e);
        }
    }

//5
    /**
     * Reconecta una sesión WebSocket a la estación de carga asociada.
     *
     * @param webSocketSession La sesión WebSocket a reconectar.
     * @param ocppSession      La sesión OCPP asociada.
     */
    private void reconnect(WebSocketSession webSocketSession, Session ocppSession) {
        try {
            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

            scheduler.schedule(() -> {
                try {
                    logger.info("Intentando reconectar la sesión WebSocket: {}", webSocketSession.getId());

                    if (!webSocketSession.isOpen()) {
                        ocppSession.open(webSocketSession.getUri().toString(), createSessionEvents(ocppSession));
                    }

                    logger.info("Reconexión exitosa para la sesión: {}", webSocketSession.getId());
                    reconnectAttempts = 0; // Reinicia el contador de intentos después de una reconexión exitosa
                    scheduler.shutdown();

                } catch (Exception e) {
                    logger.error("Error durante el intento de reconexión para la sesión: {}", webSocketSession.getId(), e);
                }
            }, RECONNECT_INTERVAL_SECONDS, TimeUnit.SECONDS);

        } catch (Exception e) {
            logger.error("Error al programar la reconexión de la sesión: {}", webSocketSession.getId(), e);
        }
    }

    /**
     * Cierra una sesión de manera controlada después de superar el máximo de intentos de reconexión.
     *
     * @param webSocketSession La sesión WebSocket a cerrar.
     */
    private void closeSessionPermanently(WebSocketSession webSocketSession) {
        try {
            UUID sessionUUID = (UUID) webSocketSession.getAttributes().get("sessionId");
            sessionStore.remove(sessionUUID);
            webSocketSessionStorage.remove(sessionUUID);
            sessions.remove(webSocketSession);

            logger.warn("Sesión cerrada y eliminada permanentemente: {}", webSocketSession.getId());
        } catch (Exception e) {
            logger.error("Error al cerrar permanentemente la sesión: {}", webSocketSession.getId(), e);
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
    public void sendResponse(Session session, WebSocketSession webSocketSession, String messageId, String action, Object confirmation) throws IOException {
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
                if (confirmation instanceof RemoteStartTransactionConfirmation) {
                    // Aquí llega la confirmación de RemoteStartTransaction
                    RemoteStartTransactionConfirmation conf = (RemoteStartTransactionConfirmation) confirmation;
                    logger.info("Recibida confirmación para RemoteStartTransaction con uniqueId: {}", uniqueId);
                    // Procesar la confirmación según tu lógica.
                }

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
    public void sendError(Session session, WebSocketSession webSocketSession, String messageId, String errorMessage) throws IOException {
        logger.error("Error: {}", errorMessage);

        // Formato del mensaje de error
        String errorResponse = objectMapper.writeValueAsString(new Object[]{4, messageId, "InternalError", errorMessage, null});

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
            confirmation.setInterval(30);

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

            String chargePointId = session.getChargePointId();  // Obtener el chargePointId de la sesión
            jsonServer.sendMessageToMQ("Heartbeat received for Charger: " + chargePointId);  // Usa el ID dinámico
            logger.info("Cargador {} conectado con éxito para la sesión: {}", chargePointId, session.getSessionId());

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

//11.1

    public void handleHeartbeatTrigger(Session session, WebSocketSession webSocketSession, String messageId, TriggerMessageRequest request) throws IOException {
        try {
            logger.info("Enviando solicitud de TriggerMessage al cargador para la sesión: {}", session.getSessionId());

            TriggerMessageRequest triggerMessageRequest = objectMapper.convertValue(request, TriggerMessageRequest.class);
            logger.debug("Payload TriggerMessageRequest: {}", triggerMessageRequest);

            TriggerMessageRequest triggerRequest = new TriggerMessageRequest();
            triggerRequest.setRequestedMessage(TriggerMessageRequestType.Heartbeat);
            // Puedes especificar el conectorId si aplica (normalmente no para Heartbeat)

            // Envías este request usando:
            session.sendRequest("TriggerMessage", triggerRequest, messageId);
            session.sendTextMessage(String.valueOf(triggerRequest),webSocketSession);


            logger.info("Solicitud de TriggerMessage enviada exitosamente para la sesión: {}", session.getSessionId());
        } catch (Exception e) {
            // Manejar errores de envío o procesamiento
            logger.error("Error al enviar la solicitud de TriggerMessage", e);
            sendError(session, webSocketSession, messageId, "Error en TriggerMessageRequest: " + e.getMessage());
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

            utilService.updateMeterValuesFront(meterValuesJson);

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

//15.1 Pedir StatusNotification a Estacion de carga
    /**
     * Envía una solicitud a la estación de carga para obtener su estado actual.
     *
     * @param session La instancia de la sesión OCPP.
     * @param webSocketSession La sesión WebSocket asociada.
     * @param messageId El identificador único del mensaje.
     * @throws IOException Si ocurre un error al enviar la solicitud o procesar la respuesta.
     */
    public void handleStatusNotificationTrigger(Session session, WebSocketSession webSocketSession, String messageId, Request request) throws IOException {
        try {
            logger.info("Enviando solicitud de TriggerMessage al cargador para la sesión: {}", session.getSessionId());

            TriggerMessageRequest triggerMessageRequest1 = objectMapper.convertValue(request, TriggerMessageRequest.class);
            logger.debug("Serialized TriggerMessageRequest payload: {}", triggerMessageRequest1);

            // Enviar la solicitud a la estación de carga
            session.sendRequest("TriggerMessage", triggerMessageRequest1, messageId);

            logger.info("Solicitud de TriggerMessage enviada exitosamente para la sesión: {}", session.getSessionId());

        } catch (Exception e) {
            // Manejar errores de envío o procesamiento
            logger.error("Error al enviar la solicitud de TriggerMessage", e);
            sendError(session, webSocketSession, messageId, "Error en TriggerMessageRequest: " + e.getMessage());
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
            DataTransferStatus dataTransferStatus = DataTransferStatus.Accepted;

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

            logger.info("Solicitud de GetConfiguration recibida para la clave: {}", Arrays.toString(getConfigurationRequest.getKey()));

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

    void handleUnlockConnector(Session ocppSession, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            // Convierte el payload a una instancia de UnlockConnectorRequest
            UnlockConnectorRequest unlockConnectorRequest = objectMapper.convertValue(requestPayload, UnlockConnectorRequest.class);

            logger.info("Solicitud de UnlockConnector recibida para el conector: {}", unlockConnectorRequest.getConnectorId());

            // Llama al manejador de eventos del cliente para procesar la solicitud
            UnlockConnectorConfirmation confirmation = clientCoreEventHandler.handleUnlockConnectorRequest(unlockConnectorRequest);

            // Envía la respuesta al cliente
            sendResponse(ocppSession, webSocketSession, messageId, "UnlockConnector", confirmation);
            logger.info("UnlockConnector manejado exitosamente para el conectorId: {}", unlockConnectorRequest.getConnectorId());
            logger.info("UnlockConnector manejado exitosamente para la sesión: {}", ocppSession.getSessionId());
            logger.info("Estado de conector: {} ", confirmation.getStatus());
        } catch (Exception e) {
            // Maneja cualquier excepción ocurrida durante el procesamiento
            logger.error("Error processing UnlockConnector", e);
            sendError(ocppSession, webSocketSession, messageId, "Error en UnlockConnector: " + e.getMessage());
        }
    }

    void handleReset(Session ocppSession, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            // Convertir el payload en una instancia de ResetRequest
            ResetRequest resetRequest = objectMapper.convertValue(requestPayload, ResetRequest.class);

            logger.info("Solicitud de Reset recibida con tipo: {}", resetRequest.getType());

            // Llamar al manejador de eventos del cliente para procesar la solicitud
            ResetConfirmation confirmation = clientCoreEventHandler.handleResetRequest(resetRequest);

            // Enviar la respuesta al cliente
            sendResponse(ocppSession, webSocketSession, messageId, "Reset", confirmation);
            logger.info("Reset manejado exitosamente para la sesión: {}", ocppSession.getSessionId());

        } catch (Exception e) {
            // Manejar cualquier excepción ocurrida durante el procesamiento
            logger.error("Error al procesar la solicitud de Reset", e);
            sendError(ocppSession, webSocketSession, messageId, "Error en Reset: " + e.getMessage());
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

            // Deserializa el payload en un objeto RemoteStartTransactionRequest
            RemoteStartTransactionRequest remoteStartRequest = objectMapper.convertValue(requestPayload, RemoteStartTransactionRequest.class);
            logger.debug("Payload deserializado: {}", remoteStartRequest);

            // Verificar si la sesión es válida
            if (session == null) {
                throw new IllegalStateException("Sesión OCPP no encontrada para el ID proporcionado.");
            }

            // Enviar el mensaje de RemoteStartTransaction a Amazon MQ para logging
            logger.info("Mensaje enviado a Estacion de Carga para RemoteStartTransaction con idTag: {}", remoteStartRequest.getIdTag());

            // Enviar la solicitud de inicio de transacción remota
            session.sendRequest("RemoteStartTransaction", remoteStartRequest, messageId);

            // Log de éxito
            logger.info("RemoteStartTransactionRequest enviado exitosamente para la sesión: {} ", session.getSessionId());

        } catch (IllegalArgumentException e) {
            // Manejo de errores con argumentos inválidos
            logger.error("Error en los argumentos de RemoteStartTransaction: {}", e.getMessage());
            sendError(session, webSocketSession, messageId, "Error en RemoteStartTransaction: " + e.getMessage());
        } catch (Exception e) {
            // Manejo de errores generales
            logger.error("Error procesando RemoteStartTransaction para la sesión: {}", session != null ? session.getSessionId() : "Sesión no disponible", e);
            sendError(session, webSocketSession, messageId, "Error en RemoteStartTransaction: " + e.getMessage());
        }
    }

    public void handleRemoteStartTransaction2(Session session, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            logger.debug("Procesando RemoteStartTransaction con messageId: {}", messageId);

            // Validar que el payload no sea nulo
            if (requestPayload == null) {
                throw new IllegalArgumentException("El payload de la solicitud no puede ser nulo.");
            }

            // Deserializar el payload en un objeto RemoteStartTransactionRequest
            RemoteStartTransactionRequest remoteStartRequest = objectMapper.convertValue(requestPayload, RemoteStartTransactionRequest.class);
            logger.debug("Payload deserializado: {}", remoteStartRequest);

            // Verificar si la sesión es válida
            if (session == null) {
                throw new IllegalStateException("Sesión OCPP no encontrada para el ID proporcionado.");
            }

            // Confirmar que la sesión WebSocket está abierta
            if (!webSocketSession.isOpen()) {
                throw new IllegalStateException("La sesión WebSocket no está abierta.");
            }

            // Log de envío
            logger.info("Enviando RemoteStartTransactionRequest al simulador...");
            logger.info("Contenido del mensaje enviado: {}", objectMapper.writeValueAsString(remoteStartRequest));

            // Enviar el mensaje al simulador
            session.sendRequest("RemoteStartTransaction", remoteStartRequest, messageId);

            // Log de éxito
            logger.info("RemoteStartTransactionRequest enviado con éxito para idTag: {}, connectorId: {}", remoteStartRequest.getIdTag(), remoteStartRequest.getConnectorId());
            logger.info("Esperando confirmación del simulador...");

        } catch (IllegalArgumentException e) {
            // Manejo de errores con argumentos inválidos
            logger.error("Error en los argumentos de RemoteStartTransaction: {}", e.getMessage());
            sendError(session, webSocketSession, messageId, "Error en RemoteStartTransaction: " + e.getMessage());
        } catch (Exception e) {
            // Manejo de errores generales
            logger.error("Error procesando RemoteStartTransaction para la sesión: {}", session != null ? session.getSessionId() : "Sesión no disponible", e);
            sendError(session, webSocketSession, messageId, "Error en RemoteStartTransaction: " + e.getMessage());
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
    public void handleRemoteStopTransaction(Session ocppSession, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            // Deserializa el payload en un objeto RemoteStopTransactionRequest
            RemoteStopTransactionRequest remoteStopTransactionRequest = objectMapper.convertValue(requestPayload, RemoteStopTransactionRequest.class);

            Integer transactionId = remoteStopTransactionRequest.getTransactionId();
            logger.info("Solicitud de RemoteStopTransaction recibida para transactionId: {}", transactionId);

            // Lógica específica para detener la transacción remota
            boolean stopSuccessful = true; // Simula el éxito de la parada de la transacción; ajusta según tu lógica

            // Crea la confirmación de la respuesta
            RemoteStopTransactionConfirmation confirmation = new RemoteStopTransactionConfirmation();

            if (stopSuccessful) {
                confirmation.setStatus(RemoteStartStopStatus.Accepted); // Establece el estado como aceptado
            } else {
                confirmation.setStatus(RemoteStartStopStatus.Rejected); // O un estado diferente si falla
            }

            // Envía la respuesta de confirmación al cliente
            sendResponse(ocppSession, webSocketSession, messageId, "RemoteStopTransaction", confirmation);
            logger.info("RemoteStopTransaction completado exitosamente para la sesión: {}", ocppSession.getSessionId());

        } catch (Exception e) {
            // Manejo de errores en el proceso y envío de mensaje de error
            logger.error("Error processing RemoteStopTransaction", e);
            sendError(ocppSession, webSocketSession, messageId, "Error in RemoteStopTransaction: " + e.getMessage());
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

            // Log de la solicitud para referencia
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

//27
    /**
     * Maneja la solicitud ClearChargingProfile, permitiendo limpiar perfiles de carga configurados previamente.
     *
     * @param session           La instancia de Session que gestiona la lógica de OCPP.
     * @param webSocketSession  La sesión WebSocket asociada.
     * @param requestPayload    El contenido de la solicitud en formato JSON.
     * @param messageId         El identificador único del mensaje para rastrear la solicitud.
     * @throws IOException Si ocurre un error al procesar o enviar la respuesta.
     */
    void handleClearChargingProfile(Session session, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            logger.debug("Procesando ClearChargingProfileRequest con messageId: {}", messageId);

            // Validar que el payload no sea nulo
            if (requestPayload == null) {
                throw new IllegalArgumentException("El payload de la solicitud no puede ser nulo.");
            }

            // Deserializar el payload a ClearChargingProfileRequest
            ClearChargingProfileRequest clearRequest = objectMapper.convertValue(requestPayload, ClearChargingProfileRequest.class);
            logger.info("ClearChargingProfileRequest recibido: {}", clearRequest);

            // Verificar si la sesión es válida
            if (session == null) {
                throw new IllegalStateException("Sesión OCPP no encontrada para el ID proporcionado.");
            }

            // Enviar la solicitud ClearChargingProfile
            session.sendRequest("ClearChargingProfile", clearRequest, messageId);

            // Configurar la respuesta de confirmación
            ClearChargingProfileConfirmation confirmation = new ClearChargingProfileConfirmation();
            confirmation.setStatus(ClearChargingProfileStatus.Accepted);
            confirmation.setCompletedHandler(completedHandler);

            // Log para verificar los perfiles de carga después de limpiar
            logger.info("Perfiles de carga después de seteado: {}", confirmation.getCompletedHandler());

            // Enviar la respuesta al cliente
            sendResponse(session, webSocketSession, messageId, "ClearChargingProfile", confirmation);
            logger.info("ClearChargingProfile manejado exitosamente para la sesión: {}", session.getSessionId());

        } catch (Exception e) {
            // Manejo de errores en el procesamiento
            logger.error("Error en ClearChargingProfile para la sesión {}: {}", session.getSessionId(), e.getMessage(), e);
            sendError(session, webSocketSession, messageId, "Error en ClearChargingProfile: " + e.getMessage());
        }
    }

//28
    /**
     * Maneja la solicitud GetCompositeSchedule, utilizada para obtener el horario compuesto
     * de carga para un conector específico, en función de la configuración de perfiles de carga.
     *
     * @param session           La instancia de Session que gestiona la lógica de OCPP.
     * @param webSocketSession  La sesión WebSocket asociada.
     * @param requestPayload    El contenido de la solicitud en formato JSON.
     * @param messageId         El identificador único del mensaje para rastrear la solicitud.
     * @throws IOException Si ocurre un error al procesar o enviar la respuesta.
     */
    void handleGetCompositeSchedule(Session session, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            logger.debug("Procesando GetCompositeScheduleRequest con messageId: {}", messageId);

            // Validar que el payload no sea nulo
            if (requestPayload == null) {
                throw new IllegalArgumentException("El payload de la solicitud no puede ser nulo.");
            }

            // Deserializar el payload a GetCompositeScheduleRequest
            GetCompositeScheduleRequest getScheduleRequest = objectMapper.convertValue(requestPayload, GetCompositeScheduleRequest.class);
            logger.info("GetCompositeScheduleRequest recibido: {}", getScheduleRequest);

            // Verificar si la sesión es válida
            if (session == null) {
                throw new IllegalStateException("Sesión OCPP no encontrada para el ID proporcionado.");
            }

            // Enviar la solicitud GetCompositeScheduleRequest
            session.sendRequest("GetCompositeSchedule", getScheduleRequest, messageId);

            // Configurar la respuesta de confirmación
            GetCompositeScheduleConfirmation confirmation = new GetCompositeScheduleConfirmation();
            confirmation.setStatus(GetCompositeScheduleStatus.Accepted);
            confirmation.setCompletedHandler(completedHandler);

            // Enviar la respuesta al cliente
            sendResponse(session, webSocketSession, messageId, "GetCompositeSchedule", confirmation);
            logger.info("GetCompositeSchedule manejado exitosamente para la sesión: {}", session.getSessionId());

        } catch (Exception e) {
            logger.error("Error en GetCompositeSchedule", e);
            sendError(session, webSocketSession, messageId, "Error en GetCompositeSchedule: " + e.getMessage());
        }
    }

//29
    /**
     * Maneja la solicitud SetChargingProfile, utilizada para establecer un perfil de carga
     * en un conector específico en función de la configuración del perfil.
     *
     * @param session           La instancia de Session que gestiona la lógica de OCPP.
     * @param webSocketSession  La sesión WebSocket asociada.
     * @param requestPayload    El contenido de la solicitud en formato JSON.
     * @param messageId         El identificador único del mensaje para rastrear la solicitud.
     * @throws IOException Si ocurre un error al procesar o enviar la respuesta.
     */
    void handleSetChargingProfile(Session session, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            logger.debug("Procesando SetChargingProfileRequest con messageId: {}", messageId);

            // Validar que el payload no sea nulo
            if (requestPayload == null) {
                throw new IllegalArgumentException("El payload de la solicitud no puede ser nulo.");
            }

            // Deserializar el payload a SetChargingProfileRequest
            SetChargingProfileRequest setProfileRequest = objectMapper.convertValue(requestPayload, SetChargingProfileRequest.class);
            logger.info("SetChargingProfileRequest recibido: {}", setProfileRequest);

            // Verificar si la sesión es válida
            if (session == null) {
                throw new IllegalStateException("Sesión OCPP no encontrada para el ID proporcionado.");
            }

            // Enviar la solicitud SetChargingProfile al punto de carga
            session.sendRequest("SetChargingProfile", setProfileRequest, messageId);

            // Configurar la respuesta de confirmación
            SetChargingProfileConfirmation confirmation = new SetChargingProfileConfirmation();
            confirmation.setStatus(ChargingProfileStatus.Accepted);

            // Enviar la respuesta al cliente
            sendResponse(session, webSocketSession, messageId, "SetChargingProfile", confirmation);
            logger.info("SetChargingProfile manejado exitosamente para la sesión: {}", session.getSessionId());

        } catch (IllegalArgumentException e) {
            logger.error("Error de argumento en SetChargingProfile: {}", e.getMessage());
            sendError(session, webSocketSession, messageId, "Error en SetChargingProfile: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error en SetChargingProfile", e);
            sendError(session, webSocketSession, messageId, "Error en SetChargingProfile: " + e.getMessage());
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

    public UUID getSessionIdByChargePointId(String chargePointId) {
        return chargePointIdToSessionIdMap.get(chargePointId);
    }

    public void handleBootNotificationTrigger(Session session, WebSocketSession webSocketSession, String messageId, TriggerMessageRequest request) throws IOException {
        try {
            logger.info("Enviando solicitud de TriggerMessage al cargador para la sesión: {}", session.getSessionId());

            TriggerMessageRequest triggerMessageRequest = objectMapper.convertValue(request, TriggerMessageRequest.class);
            logger.debug("Payload TriggerMessageRequest: {}", triggerMessageRequest);

            // Enviar la solicitud a la estación de carga
            session.sendRequest("TriggerMessage", triggerMessageRequest, messageId);

            logger.info("Solicitud de TriggerMessage enviada exitosamente para la sesión: {}", session.getSessionId());
        } catch (Exception e) {
            // Manejar errores de envío o procesamiento
            logger.error("Error al enviar la solicitud de TriggerMessage", e);
            sendError(session, webSocketSession, messageId, "Error en TriggerMessageRequest: " + e.getMessage());
        }
    }

    public void handleDiagnosticsStatusNotificationTrigger(Session session, WebSocketSession webSocketSession, String messageId, TriggerMessageRequest request) throws IOException {
        try {
            logger.info("Enviando solicitud de TriggerMessage al cargador para la sesión: {}", session.getSessionId());

            TriggerMessageRequest triggerMessageRequest = objectMapper.convertValue(request, TriggerMessageRequest.class);
            logger.debug("Payload TriggerMessageRequest: {}", triggerMessageRequest);

            // Enviar la solicitud a la estación de carga
            session.sendRequest("TriggerMessage", triggerMessageRequest, messageId);

            logger.info("Solicitud de TriggerMessage enviada exitosamente para la sesión: {}", session.getSessionId());
        } catch (Exception e) {
            // Manejar errores de envío o procesamiento
            logger.error("Error al enviar la solicitud de TriggerMessage", e);
            sendError(session, webSocketSession, messageId, "Error en TriggerMessageRequest: " + e.getMessage());
        }
    }

    public void handleFirmwareStatusNotificationTrigger(Session session, WebSocketSession webSocketSession, String messageId, TriggerMessageRequest request) throws IOException {
        try {
            logger.info("Enviando solicitud de TriggerMessage al cargador para la sesión: {}", session.getSessionId());

            TriggerMessageRequest triggerMessageRequest = objectMapper.convertValue(request, TriggerMessageRequest.class);
            logger.debug("Payload TriggerMessageRequest: {}", triggerMessageRequest);

            // Enviar la solicitud a la estación de carga
            session.sendRequest("TriggerMessage", triggerMessageRequest, messageId);

            logger.info("Solicitud de TriggerMessage enviada exitosamente para la sesión: {}", session.getSessionId());
        } catch (Exception e) {
            // Manejar errores de envío o procesamiento
            logger.error("Error al enviar la solicitud de TriggerMessage", e);
            sendError(session, webSocketSession, messageId, "Error en TriggerMessageRequest: " + e.getMessage());
        }
    }

    public void handleMeterValuesTrigger(Session session, WebSocketSession webSocketSession, String messageId, TriggerMessageRequest request) throws IOException {
        try {
            logger.info("Enviando solicitud de TriggerMessage al cargador para la sesión: {}", session.getSessionId());

            TriggerMessageRequest triggerMessageRequest = objectMapper.convertValue(request, TriggerMessageRequest.class);
            logger.debug("Payload TriggerMessageRequest: {}", triggerMessageRequest);

            // Enviar la solicitud a la estación de carga
            session.sendRequest("TriggerMessage", triggerMessageRequest, messageId);

            logger.info("Solicitud de TriggerMessage enviada exitosamente para la sesión: {}", session.getSessionId());
        } catch (Exception e) {
            // Manejar errores de envío o procesamiento
            logger.error("Error al enviar la solicitud de TriggerMessage", e);
            sendError(session, webSocketSession, messageId, "Error en TriggerMessageRequest: " + e.getMessage());
        }
    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        logger.debug("Pong recibido en la sesión: " + session.getId());
        // Aquí puedes agregar lógica adicional, por ejemplo,
        // actualizar un timestamp de último pong recibido, etc.
    }

    private void handlePingMessage(WebSocketSession session, PingMessage pingMessage) {
        try {
            logger.debug("Ping recibido. Respondiendo con Pong para la sesión: {}", session.getId());
            session.sendMessage(new PongMessage(pingMessage.getPayload()));
            sessionLastPingTime.put(session.getId(), System.currentTimeMillis());
        } catch (IOException e) {
            logger.error("Error al enviar PongMessage a la sesión: {}", session.getId(), e);
            attemptReconnection(session);
        }
    }


    // Método para verificar sesiones activas
    @Scheduled(fixedRate = 30000) // Se ejecuta cada 30 segundos
    private void verifyActiveSessions() {
        long currentTime = System.currentTimeMillis();
        sessionLastPingTime.forEach((sessionId, lastPingTime) -> {
            if (currentTime - lastPingTime > MAX_PING_INTERVAL_MS) { // Por ejemplo, 30 segundos
                logger.warn("La sesión {} no ha enviado un Ping en los últimos {} ms. Cerrando sesión.", sessionId, MAX_PING_INTERVAL_MS);
                WebSocketSession webSocketSession = getWebSocketSessionById(sessionId);
                if (webSocketSession != null && webSocketSession.isOpen()) {
                    try {
                        webSocketSession.close(CloseStatus.GOING_AWAY);
                        logger.info("Sesión {} cerrada debido a inactividad.", sessionId);
                    } catch (IOException e) {
                        logger.error("Error al cerrar la sesión inactiva: {}", sessionId, e);
                    }
                }
            }
        });
    }

    /**
     * Maneja la solicitud de notificación de eventos de seguridad y envía la confirmación correspondiente al cliente.
     *
     * <p>Esta solicitud es enviada por la estación de carga para notificar un evento de seguridad.</p>
     *
     * @param session           La instancia de la sesión OCPP.
     * @param webSocketSession  La sesión WebSocket activa.
     * @param requestPayload    El contenido de la solicitud SecurityEventNotification.
     * @param messageId         El ID del mensaje que se está procesando.
     * @throws IOException si ocurre un error al procesar o enviar la respuesta.
     */
    public void handleSecurityEventNotification(Session session, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            // Deserializar el payload a SecurityEventNotificationRequest
            SecurityEventNotificationRequest securityEventRequest = objectMapper.convertValue(requestPayload, SecurityEventNotificationRequest.class);

            // Validar la solicitud si es necesario
            if (!securityEventRequest.validate()) {
                throw new IllegalArgumentException("SecurityEventNotificationRequest inválido.");
            }

            // Log detallado de la solicitud recibida
            String requestJson = objectMapper.writeValueAsString(securityEventRequest);
            logger.info("SecurityEventNotificationRequest recibido: {}", requestJson);

            // Procesar la solicitud según la lógica de negocio
            // Por ejemplo, enviar a Amazon MQ
            jsonServer.sendMessageToMQ("SecurityEventNotification recibido: " + securityEventRequest.toString());

            // Crear la confirmación de la respuesta
            SecurityEventNotificationConfirmation confirmation = new SecurityEventNotificationConfirmation();

            // Enviar la confirmación al cliente
            sendResponse(session, webSocketSession, messageId, "SecurityEventNotification", confirmation);
            logger.info("SecurityEventNotification completado exitosamente para la sesión: {}", session.getSessionId());

        } catch (IllegalArgumentException e) {
            // Manejar errores de validación
            logger.error("Error en los argumentos de SecurityEventNotification: {}", e.getMessage());
            sendError(session, webSocketSession, messageId, "Error en SecurityEventNotification: " + e.getMessage());
        } catch (Exception e) {
            // Manejar cualquier otro error inesperado
            logger.error("Error procesando SecurityEventNotification para la sesión: {}", session != null ? session.getSessionId() : "Sesión no disponible", e);
            sendError(session, webSocketSession, messageId, "Error en SecurityEventNotification: " + e.getMessage());
        }
    }

    /**
     * Envía una solicitud GetConfigurationRequest al cargador y retorna un CompletableFuture que se completará con la confirmación.
     *
     * @param session           La instancia de la sesión OCPP.
     * @param webSocketSession  La sesión WebSocket asociada.
     * @param keys              Lista de claves de configuración que se desean obtener. Si es nula o vacía, se obtendrán todas las configuraciones.
     * @return CompletableFuture que se completará con la GetConfigurationConfirmation.
     * @throws IOException Si ocurre un error al enviar la solicitud.
     */
    public CompletableFuture<GetConfigurationConfirmation> sendGetConfigurationRequestAsync(Session session, WebSocketSession webSocketSession, List<String> keys,String messageId) throws IOException {
        // Crear un CompletableFuture para manejar la confirmación
        CompletableFuture<GetConfigurationConfirmation> future = new CompletableFuture<>();
        getConfigFutures.put(messageId, future);
        logger.debug("Stored CompletableFuture for messageId: {}", messageId);

        // Construir la solicitud GetConfigurationRequest
        GetConfigurationRequest getConfigRequest = new GetConfigurationRequest();
        getConfigRequest.setOcppMessageId(messageId);

        if (keys != null && !keys.isEmpty()) {
            getConfigRequest.setKey(keys.toArray(new String[0]));
        } else {
            getConfigRequest.setKey(new String[0]);
        }

        // Validar la solicitud antes de enviarla
        if (!getConfigRequest.validate()) {
            logger.error("GetConfigurationRequest inválido. Claves exceden el límite permitido.");
            sendError(session, webSocketSession, messageId, "GetConfigurationRequest inválido: Claves exceden el límite permitido.");
            future.completeExceptionally(new IllegalArgumentException("GetConfigurationRequest inválido"));
            return future;
        }

        // Serializar la solicitud a JSON
        String requestJson = objectMapper.writeValueAsString(new Object[]{2, messageId, "GetConfiguration", getConfigRequest});
        logger.info("Enviando GetConfigurationRequest: {}", requestJson);

        // Enviar la solicitud a través de la sesión OCPP
        session.sendTextMessage(requestJson, webSocketSession);

        return future;
    }

    private void handleGetConfigurationRequest(Session session, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        List<String> keys = null;
        if (requestPayload != null) {
            GetConfigurationRequest getConfigRequest = objectMapper.convertValue(requestPayload, GetConfigurationRequest.class);
            keys = Arrays.asList(getConfigRequest.getKey());
        }
        CompletableFuture<GetConfigurationConfirmation> future = sendGetConfigurationRequestAsync(session, webSocketSession, keys, messageId);
        future.thenAccept(confirmation -> {
            // Procesa la confirmación si es necesario
            if (confirmation.getConfigurationKey() != null) {
                for (KeyValueType kv : confirmation.getConfigurationKey()) {
                    logger.info("Configuración obtenida - Clave: {}, Valor: {}", kv.getKey(), kv.getValue());
                    // Por ejemplo, almacena la configuración en una base de datos o en memoria
                }
            }
            if (confirmation.getUnknownKey() != null) {
                for (String unknownKey : confirmation.getUnknownKey()) {
                    logger.warn("Clave de configuración desconocida: {}", unknownKey);
                    // Implementa la lógica para manejar claves desconocidas, por ejemplo, notificar al administrador
                }
            }
        }).exceptionally(ex -> {
            logger.error("Error en GetConfiguration: {}", ex.getMessage());
            return null;
        });
    }




    private void handleCallResult(Session session, String messageId, Object responsePayload) {
        logger.debug("Recibiendo CallResult para messageId: {}", messageId);
        CompletableFuture<GetConfigurationConfirmation> future = getConfigFutures.remove(messageId);
        if (future != null) {
            logger.debug("Found CompletableFuture for messageId: {}", messageId);
            try {
                // Deserializar el payload a GetConfigurationConfirmation
                GetConfigurationConfirmation confirmation = objectMapper.convertValue(responsePayload, GetConfigurationConfirmation.class);

                // Validar la confirmación
                if (!confirmation.validate()) {
                    throw new IllegalArgumentException("GetConfigurationConfirmation inválido.");
                }

                // Completar el CompletableFuture con la confirmación
                future.complete(confirmation);
                logger.info("GetConfigurationConfirmation completado para messageId: {}", messageId);

                // Procesar la configuración obtenida
                if (confirmation.getConfigurationKey() != null) {
                    for (KeyValueType kv : confirmation.getConfigurationKey()) {
                        logger.info("Configuración obtenida - Clave: {}, Valor: {}", kv.getKey(), kv.getValue());
                        // Implementa la lógica para almacenar o utilizar las configuraciones obtenidas
                    }
                }

                if (confirmation.getUnknownKey() != null) {
                    for (String unknownKey : confirmation.getUnknownKey()) {
                        logger.warn("Clave de configuración desconocida: {}", unknownKey);
                        // Implementa la lógica para manejar claves desconocidas si es necesario
                    }
                }

            } catch (Exception e) {
                logger.error("Error al procesar GetConfigurationConfirmation para messageId: {}", messageId, e);
                future.completeExceptionally(e);
            }
        } else {
            logger.warn("No se encontró un CompletableFuture para messageId: {}", messageId);
        }
    }


    private void handleCallError(Session session, String messageId, String errorCode, String errorDescription, Object errorDetails) {
        CompletableFuture<GetConfigurationConfirmation> future = getConfigFutures.remove(messageId);
        if (future != null) {
            Exception e = new RuntimeException("CallError - Código: " + errorCode + ", Descripción: " + errorDescription);
            logger.error("CallError recibido para messageId: {} - Código: {}, Descripción: {}", messageId, errorCode, errorDescription);
            future.completeExceptionally(e);
        } else {
            logger.warn("No se encontró un CompletableFuture para CallError messageId: {}", messageId);
        }
    }




//    private void sendMeterValuesToClient(WebSocketSession webSocketSession, String meterValuesJson) throws IOException {
//        if (webSocketSession.isOpen()) {
//            webSocketSession.sendMessage(new TextMessage(meterValuesJson));
//            logger.debug("MeterValues enviados al cliente: {}", meterValuesJson);
//        } else {
//            logger.warn("La sesión WebSocket está cerrada, no se pueden enviar MeterValues.");
//        }
//    }

}
