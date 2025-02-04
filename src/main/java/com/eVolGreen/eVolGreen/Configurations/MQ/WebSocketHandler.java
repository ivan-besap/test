package com.eVolGreen.eVolGreen.Configurations.MQ;

import com.eVolGreen.eVolGreen.Models.Account.Car.DeviceIdentifier;
import com.eVolGreen.eVolGreen.Models.Account.Transaction.TransactionInfo;
import com.eVolGreen.eVolGreen.Models.ChargingStation.Connector.ConnectorStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.CargasOcpp;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.UnsupportedFeatureException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.ConfirmationCompletedHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.SessionInformation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Queue;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Handler.DefaultClientCoreEventHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Handler.ServerCoreEventHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.ClientRemoteTriggerProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.ServerCoreProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.ServerRemoteTriggerProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.JSONServer;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.Enums.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.Utils.IdTagInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums.ChargePointStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums.SampledValueMeasurand;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.MeterValue;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.SampledValue;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Confirmations.DiagnosticsStatusNotificationConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Confirmations.FirmwareStatusNotificationConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Confirmations.GetDiagnosticsConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Confirmations.UpdateFirmwareConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Request.DiagnosticsStatusNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Request.FirmwareStatusNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Request.GetDiagnosticsRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Request.UpdateFirmwareRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.LocalAuthList.Confirmations.GetLocalListVersionConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.LocalAuthList.Confirmations.SendLocalListConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.LocalAuthList.Request.GetLocalListVersionRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.LocalAuthList.Request.SendLocalListRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.RemoteTrigger.Confirmations.Enums.TriggerMessageStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.RemoteTrigger.Confirmations.TriggerMessageConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.RemoteTrigger.Request.Enums.TriggerMessageRequestType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.RemoteTrigger.Request.TriggerMessageRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Reservation.Confirmations.CancelReservationConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Reservation.Confirmations.ReserveNowConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Reservation.Request.CancelReservationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Reservation.Request.ReserveNowRequest;
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
import com.eVolGreen.eVolGreen.Repositories.CargasOcppRepository;
import com.eVolGreen.eVolGreen.Repositories.ConnectorRepository;
import com.eVolGreen.eVolGreen.Repositories.DeviceIdentifierRepository;
import com.eVolGreen.eVolGreen.Repositories.TransactionInfoRepository;
import com.eVolGreen.eVolGreen.Services.AccountService.UtilService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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


import java.io.EOFException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Stream;

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
    public ObjectMapper objectMapper;
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

    private static final Map<String, String> authorizedIdTags = new ConcurrentHashMap<>();

    @Autowired
    private CargasOcppRepository cargasOcppRepository;

    @Autowired
    private ConnectorRepository connectorRepository;

    @Autowired
    private DeviceIdentifierRepository deviceIdentifierRepository;
    private final Map<String, TransactionInfo> activeTransactions = new ConcurrentHashMap<>();
    private final Map<Integer, CargasOcpp> cargasOcppMap = new ConcurrentHashMap<>();
    @Autowired
    private TransactionInfoRepository transactionInfoRepository;





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
                            WebSocketMetricsConfig webSocketMetricsConfig, DeviceIdentifierRepository deviceIdentifierRepository,
                            CargasOcppRepository cargasOcppRepository, TransactionInfoRepository  transactionInfoRepository, ConnectorRepository connectorRepository) {

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

        this.cargasOcppRepository = cargasOcppRepository;

        this.deviceIdentifierRepository = deviceIdentifierRepository;

        this.transactionInfoRepository = transactionInfoRepository;

        this.connectorRepository = connectorRepository;
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

    public void registerSession(UUID sessionId, Session session) {
        sessionStore.put(sessionId, session);
        logger.info("Sesión registrada con ID: {}", sessionId);
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
            registerSession(sessionUUID,session);

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
     * Maneja mensajes de texto entrantes y los procesa según el tipo de mensaje OCPP.
     *
     * @param webSocketSession La sesión WebSocket activa.
     * @param message          El mensaje de texto recibido.
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

            // Llama a `processIncomingMessage` con la instancia de `Session` y `WebSocketSession`
            processIncomingMessage(session, webSocketSession, payload);

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
    private void processIncomingMessage(Session session, WebSocketSession webSocketSession, String payload) throws IOException {
        if (payload == null || payload.isEmpty()) {
            logger.warn("Mensaje vacío recibido.");
            sendError(session, webSocketSession, null, "Mensaje vacío.");
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

                // Manejar la acción
                handleAction(session, webSocketSession, action, requestPayload, messageId);
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

    /**
     * Procesa la acción específica recibida en el mensaje Call.
     *
     * @param session          La instancia de la sesión OCPP.
     * @param webSocketSession La sesión WebSocket.
     * @param action           La acción específica a manejar.
     * @param requestPayload   El contenido de la solicitud.
     * @param messageId        El ID del mensaje para rastrear la solicitud.
     * @throws IOException Si ocurre un error al procesar o enviar la respuesta.
     */
    private void handleAction(Session session, WebSocketSession webSocketSession, String action, Object requestPayload, String messageId) throws IOException {
        switch (action) {
            case "Authorize":
                handleAuthorize(session, webSocketSession, requestPayload, messageId);
                break;
            case "BootNotification":
                handleBootNotification(session, webSocketSession, requestPayload, messageId);
                break;
            case "Heartbeat":
                handleHeartbeat(session, webSocketSession, requestPayload, messageId);
                break;
            case "MeterValues":
                handleMeterValues(session, webSocketSession, requestPayload, messageId);
                break;
            case "StartTransaction":
                handleStartTransaction(session, webSocketSession, requestPayload, messageId);
                break;
            case "StopTransaction":
                handleStopTransaction(session, webSocketSession, requestPayload, messageId);
                break;
            case "StatusNotification":
                handleStatusNotification(session, webSocketSession, requestPayload, messageId);
                break;
            case "DataTransfer":
                handleDataTransfer(session, webSocketSession, requestPayload, messageId);
                break;
            case "Available":
                handleAvailable(session, webSocketSession, requestPayload, messageId);
                break;
            case "Preparing":
                handlePreparing(session, webSocketSession, requestPayload, messageId);
                break;
            case "Charging":
                handleCharging(session, webSocketSession, requestPayload, messageId);
                break;
            case "TriggerMessage":
                handleTriggerMessage(session, webSocketSession, requestPayload, messageId);
                break;
            case "ChangeAvailability":
                handleChangeAvailability(session, webSocketSession, requestPayload, messageId);
                break;
            case "GetConfiguration":
                handleGetConfiguration(session, webSocketSession, requestPayload, messageId);
                break;
            case "RemoteStartTransaction":
                handleRemoteStartTransaction(session, webSocketSession, requestPayload, messageId);
                break;
            case "RemoteStopTransaction":
                handleRemoteStopTransaction(session, webSocketSession, requestPayload, messageId);
                break;
            case "ClearCache":
                handleClearCache(session, webSocketSession, requestPayload, messageId);
                break;
            case "ChangeConfiguration":
                handleChangeConfiguration(session, webSocketSession, requestPayload, messageId);
                break;
            case "ClearChargingProfile":
                handleClearChargingProfile(session, webSocketSession, requestPayload, messageId);
                break;
            case "GetCompositeSchedule":
                handleGetCompositeSchedule(session, webSocketSession, requestPayload, messageId);
                break;
            case "SetChargingProfile":
                handleSetChargingProfile(session, webSocketSession, requestPayload, messageId);
                break;
            case "UnlockConnector":
                handleUnlockConnector(session, webSocketSession, requestPayload, messageId);
                break;
            case "Reset":
                handleReset(session, webSocketSession, requestPayload, messageId);
                break;
            case "SecurityEventNotification":
                handleSecurityEventNotification(session, webSocketSession, requestPayload, messageId);
                break;
            case "DiagnosticsStatusNotification":
                handleDiagnosticsStatusNotification(session, webSocketSession, requestPayload, messageId);
                break;
            case "FirmwareStatusNotification":
                handleFirmwareStatusNotification(session, webSocketSession, requestPayload, messageId);
                break;
            default:
                logger.warn("Acción no soportada_: {}", action);
                sendError(session, webSocketSession, messageId, "Acción no soportada: " + action);
                break;
        }
    }


    /**
     * Maneja la solicitud de TriggerMessage y delega el procesamiento según el tipo de mensaje solicitado.
     *
     * @param session           La instancia de la sesión OCPP.
     * @param webSocketSession  La sesión WebSocket asociada.
     * @param requestPayload    El contenido de la solicitud TriggerMessage.
     * @param messageId         El ID único del mensaje.
     * @throws IOException Si ocurre un error durante el procesamiento.
     */
    private void handleTriggerMessage(Session session, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            // Convierte el payload en una instancia de TriggerMessageRequest
            TriggerMessageRequest triggerMessageRequest = objectMapper.convertValue(requestPayload, TriggerMessageRequest.class);
            logger.info("TriggerMessageRequest recibido: {}", triggerMessageRequest);

            // Determinar el tipo de mensaje a disparar
            TriggerMessageRequestType requestedMessage = triggerMessageRequest.getRequestedMessage();

            // Dependiendo del tipo de mensaje, llamar al método correspondiente
            switch (requestedMessage) {
                case Heartbeat:
                    handleHeartbeatTrigger(session, webSocketSession, triggerMessageRequest);
                    break;
                case StatusNotification:
                    handleStatusNotificationTrigger(session, webSocketSession, triggerMessageRequest);
                    break;
                case DiagnosticsStatusNotification:
                    handleDiagnosticsStatusNotificationTrigger(session, webSocketSession, triggerMessageRequest);
                    break;
                case FirmwareStatusNotification:
                    handleFirmwareStatusNotificationTrigger(session, webSocketSession, triggerMessageRequest);
                    break;
                case MeterValues:
                    handleMeterValuesTrigger(session, webSocketSession, triggerMessageRequest);
                    break;
                case BootNotification:
                    handleBootNotificationTrigger(session, webSocketSession, triggerMessageRequest);
                    break;
                default:
                    logger.warn("Tipo de TriggerMessage no soportado: {}", requestedMessage);
                    sendError(session, webSocketSession, messageId, "Tipo de TriggerMessage no soportado: " + requestedMessage);
                    break;
            }

            // Crear y enviar la confirmación de TriggerMessage
            TriggerMessageConfirmation confirmation = new TriggerMessageConfirmation();
            confirmation.setStatus(TriggerMessageStatus.Accepted);
            sendResponse(session, webSocketSession, messageId, "TriggerMessage", confirmation);
            logger.info("TriggerMessage manejado exitosamente para la sesión: {}", session.getSessionId());

        } catch (Exception e) {
            logger.error("Error procesando TriggerMessage", e);
            sendError(session, webSocketSession, messageId, "Error en TriggerMessage: " + e.getMessage());
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

    /**
     * Maneja mensajes entrantes en la sesión WebSocket.
     *
     * @param session La sesión WebSocket activa.
     * @param message El mensaje recibido.
     * @throws Exception Si ocurre un error al procesar el mensaje.
     */
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
        // Configura el ObjectMapper para ignorar campos nulos
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // Serializa el objeto confirmation ignorando campos nulos
        String response = objectMapper.writeValueAsString(new Object[]{3, messageId, confirmation});

        // Envía la respuesta a través del WebSocket
        webSocketSession.sendMessage(new TextMessage(response));

        // Log de la respuesta enviada
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

        // Formato del mensaje de error según OCPP
        String errorResponse = objectMapper.writeValueAsString(new Object[]{4, messageId, "InternalError", errorMessage, null});

        // Enviar mensaje de error a través de la sesión
        if (webSocketSession != null && webSocketSession.isOpen()) {
            webSocketSession.sendMessage(new TextMessage(errorResponse));
            logger.debug("Mensaje de error enviado: {}", errorMessage);
        } else {
            logger.warn("La sesión WebSocket está cerrada, no se puede enviar el mensaje de error.");
        }
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
            String idTag = authorizeRequest.getIdTag();
            System.out.println("idTag: " + idTag);

            if (idTag == null || idTag.trim().isEmpty()) {
                idTag = "default";
            }

            // Enviar mensaje de log a Amazon MQ
            jsonServer.sendMessageToMQ("Authorize request received for idTag: " + idTag);

            DeviceIdentifier deviceIdentifier = deviceIdentifierRepository.findByRFID(idTag);
            System.out.println("deviceIdentifier: " + deviceIdentifier);


            // Configurar la respuesta de confirmación con la lógica de negocio
            IdTagInfo idTagInfo = new IdTagInfo();

            if (deviceIdentifier == null) {
                // Si no existe el idTag, marcar como Invalid
                idTagInfo.setStatus(AuthorizationStatus.Invalid);
            } else {
                // Verificar si es usable
                if (!deviceIdentifier.getUsable()) {
                    idTagInfo.setStatus(AuthorizationStatus.Blocked);
                } else if (deviceIdentifier.getFechaExpiracion().isBefore(LocalDate.now())) {
                    // Verificar si la fecha de expiración ha pasado
                    idTagInfo.setStatus(AuthorizationStatus.Expired);
                } else {
                    // Si todo es válido, aceptamos
                    idTagInfo.setStatus(AuthorizationStatus.Accepted);
                    authorizedIdTags.put(session.getChargePointId(), idTag);
                    logger.info("idTag autorizada asociada al chargePointId: {}", session.getChargePointId());
                }
            }

            // Crear y configurar el objeto de confirmación
            AuthorizeConfirmation confirmation = new AuthorizeConfirmation();
            confirmation.setIdTagInfo(idTagInfo);
            // Enviar la respuesta a través de la sesión OCPP
            sendResponse(session, webSocketSession, messageId, "Authorize", confirmation);
            logger.info("Authorize completado exitosamente para la sesión: {}", session.getSessionId());
            logger.info("Respuesta del Servidor para Authorize : {} con RFID : {}", confirmation, idTag);

        } catch (NullPointerException e) {
            logger.error("Error procesando Authorize para la sesión: {}", session.getSessionId(), e);
            sendError(session,webSocketSession,messageId, "Internal server error");
        } catch (Exception e) {
            // Manejo de cualquier error que ocurra al procesar la solicitud
            logger.error("Error procesando Authorize para la sesión: {}", session.getSessionId(), e);
            sendError(session,webSocketSession,messageId, "Internal server error");
        }
    }
    public String getAuthorizedIdTag(String chargePointId) {
        return authorizedIdTags.get(chargePointId);
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

    public CompletableFuture<TriggerMessageConfirmation> handleHeartbeatTrigger(Session session, WebSocketSession webSocketSession, TriggerMessageRequest request) throws IOException {
        return sendTriggerMessageRequestAsync(session, webSocketSession, request);
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

            // Obtener la transacción activa desde el mapa o la base de datos
            TransactionInfo transactionInfo = activeTransactions.values().stream()
                    .filter(t -> t.getTransactionId() == meterValuesRequest.getTransactionId())
                    .findFirst()
                    .orElseGet(() -> transactionInfoRepository.findByTransactionIdAndActive(meterValuesRequest.getTransactionId())
                            .orElse(null));

            // Obtener CargasOcpp activa desde el mapa o la base de datos
            CargasOcpp cargasOcpp = cargasOcppMap.values().stream()
                    .filter(c -> c.getActivo() == cargasOcppMap.get(session.getChargePointId()).getActivo())
                    .findFirst()
                    .orElseGet(() -> cargasOcppRepository.findByChargePointIdAndActive(session.getChargePointId())
                            .orElse(null));

            if (transactionInfo == null) {
                logger.warn("No se encontró una transacción activa para TransactionId: {}", meterValuesRequest.getTransactionId());
                return;
            }

            if (transactionInfo != null) {
                // Actualizar el último MeterValue en la transacción
                List<MeterValue> meterValues = List.of(meterValuesRequest.getMeterValue());
                if (meterValues != null && !meterValues.isEmpty()) {
                    MeterValue lastMeterValue = meterValues.get(meterValues.size() - 1);
                    SampledValue[] sampledValues = lastMeterValue.getSampledValue();
                    transactionInfo.setTransactionData(sampledValues);
                    transactionInfoRepository.save(transactionInfo);
                    logger.info("Último MeterValue actualizado para TransactionId: {}",
                            transactionInfo.getTransactionId());

                    // Extraer la energía suministrada por transactionID y cargador
                    logger.info("+++++++++SampledValues: {}", Arrays.toString(sampledValues));
                    Arrays.stream(sampledValues)
                            .filter(sampledValue -> sampledValue.getMeasurand() == SampledValueMeasurand.Energy_Active_Import_Register)
                            .forEach(sampledValue -> {
                                try {
                                    Integer energySupplied = Integer.parseInt(sampledValue.getValue());
                                    transactionInfo.setEnergyConsumed((energySupplied - transactionInfo.getMeterStart()));
                                    transactionInfoRepository.save(transactionInfo);
                                    logger.info("++++++++++Energía suministrada actualizada para TransactionId {}: {}",
                                            transactionInfo.getTransactionId(), transactionInfo.getEnergyConsumed());
                                } catch (NumberFormatException e) {
                                    logger.warn("Valor inválido para Energy.Active.Import.Register: {}", sampledValue.getValue());
                                }
                            });
                }
            } else {
                logger.warn("No se encontró una transacción activa para ChargePoint: {}",
                        meterValuesRequest.getTransactionId());
            }

            // Serializar a JSON para enviar a Amazon MQ y loggear
            String meterValuesJson = objectMapper.writeValueAsString(meterValuesRequest);
            logger.info("Meter values recibidos: {}", meterValuesJson);
            jsonServer.sendMessageToMQ("Meter values recibidos: " + meterValuesJson);

            cargasOcppRepository.save(cargasOcpp);

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

            // Validar idTag (RFID)
            String idTag = startTransactionRequest.getIdTag();
            DeviceIdentifier usuario = deviceIdentifierRepository.findByRFID(idTag);
            if (usuario == null) {
                logger.warn("RFID no autorizado: {}", idTag);

                IdTagInfo idTagInfo = new IdTagInfo();
                idTagInfo.setStatus(AuthorizationStatus.Invalid);

                StartTransactionConfirmation confirmation = new StartTransactionConfirmation();
                confirmation.setIdTagInfo(idTagInfo);

                sendResponse(session, webSocketSession, messageId, "StartTransaction", confirmation);
                return;
            }
            logger.info("RFID autorizado: {}", idTag);

            int transactionId = Math.abs(UUID.randomUUID().hashCode());

            // Crear y guardar TransactionInfo con MeterStart
            TransactionInfo transactionInfo = new TransactionInfo();
            transactionInfo.setChargePointId(session.getChargePointId());
            transactionInfo.setConnectorId(startTransactionRequest.getConnectorId());
            transactionInfo.setTransactionId(transactionId);
            transactionInfo.setStartTime(startTransactionRequest.getTimestamp());
            transactionInfo.setMeterStart(startTransactionRequest.getMeterStart());
            transactionInfo.setEmpresa(usuario.getEmpresa());
            transactionInfo.setEnergyConsumed(0);
            transactionInfo.setActive(true);
            logger.info("Valor MeterStart inicio: {}", transactionInfo.getMeterStart());
            transactionInfoRepository.save(transactionInfo);

            activeTransactions.put(session.getChargePointId(), transactionInfo);

            CargasOcpp cargasOcpp = new CargasOcpp();
            cargasOcpp.setOcppId(session.getChargePointId());
            cargasOcpp.setNumeroConector(startTransactionRequest.getConnectorId());
            cargasOcpp.setTransaccionId(transactionId);
            cargasOcpp.setFechaCreacion(startTransactionRequest.getTimestamp().toLocalDateTime());
            cargasOcpp.setActivo(true);
            cargasOcppRepository.save(cargasOcpp);

            cargasOcppMap.put(transactionId, cargasOcpp);

            // Enviar el mensaje a Amazon MQ
            jsonServer.sendMessageToMQ("Start Transaction request received for Connector ID: " + startTransactionRequest.getConnectorId());
            logger.info("Mensaje enviado a Amazon MQ para StartTransaction: Connector ID {}", startTransactionRequest.getConnectorId());

            // Configurar la respuesta
            IdTagInfo idTagInfo = new IdTagInfo();
            idTagInfo.setStatus(AuthorizationStatus.Accepted);

            StartTransactionConfirmation confirmation = new StartTransactionConfirmation();
            confirmation.setIdTagInfo(idTagInfo);
            confirmation.setTransactionId(transactionId);

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

            // Obtener la transacción activa vinculada al transactionId
            TransactionInfo transactionInfo = activeTransactions.values().stream()
                    .filter(t -> t.getTransactionId() == stopTransactionRequest.getTransactionId())
                    .findFirst()
                    .orElse(null);

            logger.info("Transaction Data : {}",stopTransactionRequest.getTransactionData());

            if (transactionInfo != null) {
                // Calcular la energía suministrada
                Integer meterStart = transactionInfo.getMeterStart();
                Integer meterStop = stopTransactionRequest.getMeterStop();
                Integer energySupplied = meterStop - meterStart;

                // Actualizar los valores finales de la transacción
                transactionInfo.setMeterStop(stopTransactionRequest.getMeterStop());
                transactionInfo.setEndTime(stopTransactionRequest.getTimestamp());
                transactionInfo.setEnergyConsumed(energySupplied);
                transactionInfo.setActive(false);
                transactionInfoRepository.save(transactionInfo);

                LocalDateTime stopTime = LocalDateTime.now();

                logger.info("Transaction ended for ChargePoint: {}, TransactionId: {}, Stop Time: {}, MeterStop: {}",
                        transactionInfo.getChargePointId(), transactionInfo.getTransactionId(), stopTime,stopTransactionRequest.getMeterStop());

                // Remover la transacción de las activas
                activeTransactions.remove(transactionInfo.getChargePointId());
            } else {
                logger.warn("StopTransaction received for unknown transactionId: {}", stopTransactionRequest.getTransactionId());
            }

            // Obtener MeterValue del request
            MeterValue[] meterValues = stopTransactionRequest.getTransactionData();

            if (meterValues == null || meterValues.length == 0) {
                logger.warn("MeterValues está vacío o es nulo para TransactionId: {}", stopTransactionRequest.getTransactionId());
            } else {

                // Procesar MeterValues y SampledValues
                SampledValue[] sampledValues = Arrays.stream(meterValues)
                        .flatMap(meterValue -> meterValue.getSampledValue() != null
                                ? Arrays.stream(meterValue.getSampledValue())
                                : Stream.empty()) // Si sampledValue es null, evitar el error
                        .toArray(SampledValue[]::new); // Convertir el Stream a un arreglo de SampledValue

                // Log de los valores procesados
                if (sampledValues.length > 0) {
                    logger.info("Sampled Value: {}", sampledValues[0].getValue());
                    logger.info("Sampled: {}", Arrays.toString(sampledValues));
                }

                logger.info("Sampled Value: {}", sampledValues[0].getValue());
                logger.info("Sampled : {}", sampledValues);

                transactionInfo.setTransactionData(sampledValues);
                transactionInfoRepository.save(transactionInfo);
            }

            // Enviar el mensaje a Amazon MQ
            jsonServer.sendMessageToMQ("Stop Transaction request received: " + stopTransactionJson);

            // Configuración de los valores de respuesta
            IdTagInfo idTagInfo = new IdTagInfo();
            idTagInfo.setStatus(AuthorizationStatus.Accepted);
            idTagInfo.setExpiryDate(ZonedDateTime.now().plusDays(30));

            CargasOcpp cargasOcpp = cargasOcppMap.get(stopTransactionRequest.getTransactionId());
            if (cargasOcpp != null) {
                cargasOcpp.setActivo(false);  // Desactivar la carga
                cargasOcppRepository.save(cargasOcpp);  // Guardar el cambio en la base de datos
                logger.info("Estado de CargasOcpp actualizado a inactivo para el transactionId: {}", stopTransactionRequest.getTransactionId());
            } else {
                logger.warn("CargasOcpp no encontrado para el transactionId: {}", stopTransactionRequest.getTransactionId());
            }

            cargasOcppMap.remove(stopTransactionRequest.getTransactionId());


            
            // Crear la confirmación de StopTransaction y asignar los detalles
            StopTransactionConfirmation confirmation = new StopTransactionConfirmation();
            confirmation.setIdTagInfo(idTagInfo);

            // Enviar la respuesta al cliente
            sendResponse(session, webSocketSession, messageId, "StopTransaction", confirmation);
            logger.info("StopTransaction completado exitosamente para la sesión: {}", session.getSessionId());

        } catch (IllegalArgumentException e) {
            logger.error("Error: Payload inválido. Error: {}", e.getMessage());
            sendError(session, webSocketSession, messageId, "Invalid payload");
        } catch (Exception e) {
            logger.error("Error procesando StopTransaction. Error: {}", e.getMessage(), e);
            sendError(session, webSocketSession, messageId, "Internal server error");
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

            String ocppId = session.getChargePointId();
            Integer connectorId = statusNotificationRequest.getConnectorId();
            String status = statusNotificationRequest.getStatus().toString();
            logger.info("status obtenido status={}",status);

            // **Aquí se llama al método del service en lugar de usar directamente la implementación**
            ChargePointStatus chargePointStatus;
            try {
                chargePointStatus = ChargePointStatus.valueOf(status);
                logger.info("chargePointStatus asociado chargePointStatus={}",chargePointStatus);
            } catch (IllegalArgumentException e) {
                logger.error("Estado no reconocido: {}. No se puede mapear a ConnectorStatus.", status);
                return;
            }

            // 2️⃣ Mapear ChargePointStatus a ConnectorStatus
            ConnectorStatus newStatus = mapChargePointStatusToConnectorStatus(chargePointStatus);

            if (newStatus == null) {
                logger.error("Error: El estado mapeado es NULL. No se puede actualizar el conector. OCPP_ID={}, Connector_ID={}", ocppId, connectorId);
            } else {
                int updatedRows = connectorRepository.updateConnectorStatus(ocppId, connectorId, newStatus);

                if (updatedRows > 0) {
                    logger.info("Estado del conector actualizado correctamente en la BD: OCPP_ID={}, Connector_ID={}, Status={}", ocppId, connectorId, newStatus);
                } else {
                    logger.warn("No se encontró ningún conector con OCPP_ID={} y Connector_ID={}. No se realizó actualización.", ocppId, connectorId);
                }
            }

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
     * @throws IOException Si ocurre un error al enviar la solicitud o procesar la respuesta.
     */
    public CompletableFuture<TriggerMessageConfirmation> handleStatusNotificationTrigger(Session session, WebSocketSession webSocketSession, TriggerMessageRequest request)  throws IOException {
        return sendTriggerMessageRequestAsync(session, webSocketSession, request);
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

    public void handleReset(Session session, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            // Convertir el payload a ResetRequest
            ResetRequest resetRequest = objectMapper.convertValue(requestPayload, ResetRequest.class);

            // Validar el tipo de reinicio (Hard o Soft)
            if (resetRequest.getType() == null) {
                logger.error("El campo 'type' en ResetRequest es requerido.");
                sendError(session, webSocketSession, messageId, "El campo 'type' es requerido (Hard o Soft).");
                return;
            }
            logger.info("Solicitud de Reset recibida. Tipo: {}", resetRequest.getType());

            ResetConfirmation confirmation = new ResetConfirmation();
            confirmation.setStatus(ResetStatus.Accepted);

            // Enviar la respuesta al cliente
            sendResponse(session, webSocketSession, messageId, "Reset", confirmation);
            logger.info("Reset manejado exitosamente para la sesión: {}", session.getSessionId());

        } catch (IllegalArgumentException e) {
            // Error en la conversión del payload
            logger.error("Error en los argumentos de ResetRequest: {}", e.getMessage());
            sendError(session, webSocketSession, messageId, "Parámetros inválidos en ResetRequest: " + e.getMessage());
        } catch (Exception e) {
            // Error general inesperado
            logger.error("Error al procesar la solicitud de Reset", e);
            sendError(session, webSocketSession, messageId, "Error inesperado en Reset: " + e.getMessage());
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
     * @throws IOException si ocurre un error al procesar o enviar la respuesta.  JL
     */
    /** JL **/
    public void handleRemoteStartTransaction(Session session, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            logger.debug("Procesando RemoteStartTransaction recibido con messageId: {}", messageId);

            // Validar que el payload no sea nulo
            if (requestPayload == null) {
                throw new IllegalArgumentException("El payload de la solicitud no puede ser nulo.");
            }

            // Deserializar el payload en un objeto RemoteStartTransactionRequest
            RemoteStartTransactionRequest remoteStartRequest = objectMapper.convertValue(requestPayload, RemoteStartTransactionRequest.class);
            logger.debug("Payload deserializado: {}", remoteStartRequest);

            RemoteStartTransactionConfirmation confirmation = new RemoteStartTransactionConfirmation();
            confirmation.setStatus(RemoteStartStopStatus.Accepted);

            sendResponse(session, webSocketSession, messageId, "RemoteStartTransaction", confirmation);
            logger.info("RemoteStartTransaction manejado exitosamente para la sesión: {}", session.getSessionId());

            logger.info("RemoteStartTransaction enviado con éxito para idTag: {}, connectorId: {}", remoteStartRequest.getIdTag(), remoteStartRequest.getConnectorId());
        } catch (IllegalArgumentException e) {
            logger.error("Error en los argumentos de RemoteStartTransaction: {}", e.getMessage());
            sendError(session, webSocketSession, messageId, "Error en RemoteStartTransaction: " + e.getMessage());
        } catch (Exception e) {
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
    /** JL **/
    public void handleRemoteStopTransaction(Session ocppSession, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            // 1. Convertir el requestPayload en un RemoteStopTransactionRequest
            RemoteStopTransactionRequest remoteStopTransactionRequest = objectMapper.convertValue(requestPayload, RemoteStopTransactionRequest.class);

            // 2. Validar que transactionId no sea null
            Integer transactionId = remoteStopTransactionRequest.getTransactionId();
            if (transactionId == null) {
                logger.error("El campo transactionId es null en la solicitud RemoteStopTransaction.");
                sendError(ocppSession, webSocketSession, messageId, "transactionId es requerido.");
                return;
            }
            logger.info("Solicitud de RemoteStopTransaction recibida para transactionId: {}", transactionId);

            // 3. Crear el mensaje final para el cargador (sin incluir campos adicionales como "payload")
            Map<String, Object> ocppMessage = new HashMap<>();
            ocppMessage.put("transactionId", transactionId);

            // 4. Serializar el mensaje en JSON y enviarlo a través del WebSocket
            String jsonMessage = objectMapper.writeValueAsString(ocppMessage);
            logger.info("Enviando mensaje RemoteStopTransaction al cargador: {}", jsonMessage);

            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(new TextMessage(jsonMessage));
                logger.info("Mensaje RemoteStopTransaction enviado al cargador para transactionId: {}", transactionId);
            } else {
                logger.error("La sesión WebSocket no está activa para el chargePointId: {}", ocppSession.getChargePointId());
                sendError(ocppSession, webSocketSession, messageId, "WebSocketSession no está activa.");
            }

        } catch (IllegalArgumentException e) {
            // Manejo de errores específicos de conversión
            logger.error("Error en los argumentos de RemoteStopTransaction: {}", e.getMessage());
            sendError(ocppSession, webSocketSession, messageId, "Parámetros inválidos: " + e.getMessage());
        } catch (Exception e) {
            // Manejo general de errores
            logger.error("Error procesando RemoteStopTransaction", e);
            sendError(ocppSession, webSocketSession, messageId, "Error en RemoteStopTransaction: " + e.getMessage());
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
            logger.info("ChangeConfigurationRequest recibido: {}", changeConfigurationRequest);

            // Log de la solicitud para referencia
            ChangeConfigurationConfirmation confirmation = new ChangeConfigurationConfirmation();
            confirmation.setStatus(ConfigurationStatus.Accepted);

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

    public CompletableFuture<TriggerMessageConfirmation> handleBootNotificationTrigger(Session session, WebSocketSession webSocketSession, TriggerMessageRequest request) throws IOException {
        return sendTriggerMessageRequestAsync(session, webSocketSession, request);
    }


    public CompletableFuture<TriggerMessageConfirmation> handleDiagnosticsStatusNotificationTrigger(Session session, WebSocketSession webSocketSession, TriggerMessageRequest request)  throws IOException {
        return sendTriggerMessageRequestAsync(session, webSocketSession, request);
    }

    public CompletableFuture<TriggerMessageConfirmation> handleFirmwareStatusNotificationTrigger(Session session, WebSocketSession webSocketSession, TriggerMessageRequest request) throws IOException {
        return sendTriggerMessageRequestAsync(session, webSocketSession, request);

    }

    public CompletableFuture<TriggerMessageConfirmation> handleMeterValuesTrigger(Session session, WebSocketSession webSocketSession, TriggerMessageRequest request) throws IOException {
        return sendTriggerMessageRequestAsync(session, webSocketSession, request);
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
     * Envía una solicitud GetConfigurationRequest de manera asíncrona y retorna un CompletableFuture.
     *
     * @param session          La instancia de la sesión OCPP.
     * @param webSocketSession La sesión WebSocket asociada.
     * @param keys             Lista de claves de configuración a obtener.
     * @return CompletableFuture que se completará con la confirmación.
     * @throws IOException Si ocurre un error al enviar la solicitud.
     */
    public CompletableFuture<GetConfigurationConfirmation> sendGetConfigurationRequestAsync(Session session, WebSocketSession webSocketSession, List<String> keys) throws IOException {
        // Generar un messageId único para esta solicitud
        String messageId = UUID.randomUUID().toString();
        logger.debug("Enviando GetConfigurationRequest con messageId: {}", messageId);

        // Crear un CompletableFuture para manejar la confirmación
        CompletableFuture<GetConfigurationConfirmation> future = new CompletableFuture<>();

        // Registrar la promesa pendiente en el mapa de la sesión
        session.registerPendingPromise(messageId, "GetConfiguration", (CompletableFuture) future);
        logger.debug("Stored CompletableFuture for messageId: {}", messageId);

        // Construir la solicitud GetConfigurationRequest
        GetConfigurationRequest getConfigRequest = new GetConfigurationRequest();

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

        // Serializar la solicitud a JSON con la estructura correcta del envelope OCPP
        String requestJson = objectMapper.writeValueAsString(new Object[]{2, messageId, "GetConfiguration", getConfigRequest});
        logger.info("Enviando GetConfigurationRequest: {}", requestJson);

        // Enviar la solicitud a través de la sesión OCPP
        session.sendTextMessage(requestJson, webSocketSession);
        logger.debug("GetConfigurationRequest enviado para messageId: {}", messageId);

        return future;
    }

    public CompletableFuture<TriggerMessageConfirmation> sendTriggerMessageRequestAsync(Session session, WebSocketSession webSocketSession, TriggerMessageRequest request) throws IOException {
        String messageId = UUID.randomUUID().toString();
        logger.debug("Enviando TriggerMessageRequest con messageId: {}", messageId);

        // Crear el CompletableFuture para manejar la confirmación
        CompletableFuture<TriggerMessageConfirmation> future = new CompletableFuture<>();

        // Registrar la promesa pendiente en el mapa de la sesión
        session.registerPendingPromise(messageId, "TriggerMessage", (CompletableFuture) future);

        // Serializar la solicitud
        String requestJson = objectMapper.writeValueAsString(new Object[]{2, messageId, "TriggerMessage", request});
        logger.info("Enviando TriggerMessageRequest: {}", requestJson);

        // Enviar la solicitud a través de la sesión OCPP
        session.sendTextMessage(requestJson, webSocketSession);
        logger.debug("TriggerMessageRequest enviado para messageId: {}", messageId);

        logger.info(String.valueOf(future));
        return future;

    }

    public CompletableFuture<UnlockConnectorConfirmation> sendUnlockConnectorRequestAsync(Session session, WebSocketSession webSocketSession, UnlockConnectorRequest request) throws IOException {
        // Generar un messageId único para esta solicitud
        String messageId = UUID.randomUUID().toString();
        logger.debug("Enviando UnlockConnectorRequest con messageId: {}", messageId);

        // Crear un CompletableFuture para manejar la confirmación
        CompletableFuture<UnlockConnectorConfirmation> future = new CompletableFuture<>();

        // Registrar la promesa pendiente en el mapa de la sesión
        session.registerPendingPromise(messageId, "UnlockConnector", (CompletableFuture) future);
        logger.debug("Stored CompletableFuture for messageId: {}", messageId);

        // Validar la solicitud antes de enviarla
        if (!request.validate()) {
            logger.error("UnlockConnectorRequest inválido: connectorId debe ser mayor que 0.");
            sendError(session, webSocketSession, messageId, "UnlockConnectorRequest inválido: connectorId debe ser mayor que 0.");
            future.completeExceptionally(new IllegalArgumentException("UnlockConnectorRequest inválido"));
            return future;
        }

        // Serializar la solicitud a JSON con la estructura OCPP correcta
        String requestJson = objectMapper.writeValueAsString(new Object[]{2, messageId, "UnlockConnector", request});
        logger.info("Enviando UnlockConnectorRequest: {}", requestJson);

        // Enviar la solicitud a través de la sesión OCPP
        session.sendTextMessage(requestJson, webSocketSession);
        logger.debug("UnlockConnectorRequest enviado para messageId: {}", messageId);

        return future;
    }

    public CompletableFuture<RemoteStartTransactionConfirmation> sendRemoteStartTransactionRequestAsync(Session session, WebSocketSession webSocketSession, RemoteStartTransactionRequest request) throws IOException {
        //Generar un messageId unico para esta solicitud
        String messageId = UUID.randomUUID().toString();
        logger.debug("Enviando RemoteStartTransactionRequest con messageId: {}", messageId);

        // Crear el CompletableFuture para manejar la confirmación
        CompletableFuture<RemoteStartTransactionConfirmation> future = new CompletableFuture<>();

        // Registrar la promesa pendiente en el mapa de la sesión
        session.registerPendingPromise(messageId, "RemoteStartTransaction", (CompletableFuture) future);
        logger.debug("Completar promesa para messageId: {}",messageId);

        if (!request.validate()) {
            logger.error("RemoteStartTransactionRequest inválido: connectorId debe ser mayor que 0.");
            sendError(session, webSocketSession, messageId, "RemoteStartTransactionRequest inválido: connectorId debe ser mayor que 0.");
            future.completeExceptionally(new IllegalArgumentException("RemoteStartTransactionRequest inválido"));
            return future;
        }

        // Configurar ObjectMapper para ignorar valores nulos
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // Serializar la solicitud
        String requestJson = objectMapper.writeValueAsString(new Object[]{2, messageId, "RemoteStartTransaction", request});
        logger.info("Enviando RemoteStartTransactionRequest: {}", requestJson);

        // Enviar la solicitud a través de la sesión OCPP
        session.sendTextMessage(requestJson, webSocketSession);
        logger.debug("RemoteStartTransactionRequest enviado para messageId: {}", messageId);

        return future;
    }

    public CompletableFuture<RemoteStopTransactionConfirmation> sendRemoteStopTransactionRequestAsync(Session session, WebSocketSession webSocketSession, RemoteStopTransactionRequest request) throws IOException {
        //Generar un messageId unico para esta solicitud
        String messageId = UUID.randomUUID().toString();
        logger.debug("Enviando RemoteStopTransactionRequest con messageId: {}", messageId);

        // Crear el CompletableFuture para manejar la confirmación
        CompletableFuture<RemoteStopTransactionConfirmation> future = new CompletableFuture<>();

        // Registrar la promesa pendiente en el mapa de la sesión
        session.registerPendingPromise(messageId, "RemoteStopTransaction", (CompletableFuture) future);
        logger.debug("Completar promesa para messageId: {}", messageId);

        if (!request.validate()) {
            logger.error("RemoteStopTransactionRequest inválido: connectorId debe ser mayor que 0.");
            sendError(session, webSocketSession, messageId, "RemoteStopTransactionRequest inválido: connectorId debe ser mayor que 0.");
            future.completeExceptionally(new IllegalArgumentException("RemoteStopTransactionRequest inválido"));
            return future;
        }

        // Serializar la solicitud
        String requestJson = objectMapper.writeValueAsString(new Object[]{2, messageId, "RemoteStopTransaction", request});
        logger.info("Enviando RemoteStopTransactionRequest: {}", requestJson);

        // Enviar la solicitud a través de la sesión OCPP
        session.sendTextMessage(requestJson, webSocketSession);
        logger.debug("RemoteStopTransactionRequest enviado para messageId: {}", messageId);

        return future;
    }

    public CompletableFuture<ChangeConfigurationConfirmation> sendChangeConfigurationRequestAsync(Session session, WebSocketSession webSocketSession, ChangeConfigurationRequest request) throws IOException {
        //Generar un messageId unico para esta solicitud
        String messageId = UUID.randomUUID().toString();
        logger.debug("Enviando ChangeConfigurationRequest con messageId: {}", messageId);

        // Crear el CompletableFuture para manejar la confirmación
        CompletableFuture<ChangeConfigurationConfirmation> future = new CompletableFuture<>();

        // Registrar la promesa pendiente en el mapa de la sesión
        session.registerPendingPromise(messageId, "ChangeConfiguration", (CompletableFuture) future);
        logger.debug("Completar promesa para messageId: {}", messageId);

        if (!request.validate()) {
            logger.error("ChangeConfigurationRequest inválido: key y value son requeridos.");
            sendError(session, webSocketSession, messageId, "ChangeConfigurationRequest inválido: key y value son requeridos.");
            future.completeExceptionally(new IllegalArgumentException("ChangeConfigurationRequest inválido"));
            return future;
        }

        // Serializar la solicitud
        String requestJson = objectMapper.writeValueAsString(new Object[]{2, messageId, "ChangeConfiguration", request});
        logger.info("Enviando ChangeConfigurationRequest: {}", requestJson);

        // Enviar la solicitud a través de la sesión OCPP
        session.sendTextMessage(requestJson, webSocketSession);
        logger.debug("ChangeConfigurationRequest enviado para messageId: {}", messageId);

        return future;
    }

    /**
     * Envía de manera asíncrona la solicitud GetDiagnostics al cargador y retorna un CompletableFuture
     * que se completará cuando llegue la confirmación (GetDiagnosticsConfirmation).
     *
     * @param session           La instancia de la sesión OCPP.
     * @param webSocketSession  La sesión WebSocket asociada.
     * @param request           El objeto GetDiagnosticsRequest con la información requerida.
     * @return                  Un CompletableFuture que se completará con la confirmación GetDiagnosticsConfirmation.
     * @throws IOException      Si ocurre un error al enviar el mensaje.
     */
    public CompletableFuture<GetDiagnosticsConfirmation> sendGetDiagnosticsRequestAsync(
            Session session,
            WebSocketSession webSocketSession,
            GetDiagnosticsRequest request
    ) throws IOException {

        // Generar un messageId único para esta solicitud
        String messageId = UUID.randomUUID().toString();
        logger.debug("Enviando GetDiagnosticsRequest con messageId: {}", messageId);

        // Crear un CompletableFuture para manejar la confirmación
        CompletableFuture<GetDiagnosticsConfirmation> future = new CompletableFuture<>();

        // Registrar la promesa pendiente en el mapa de la sesión
        session.registerPendingPromise(messageId, "GetDiagnostics", (CompletableFuture) future);

        // Validar la solicitud antes de enviarla
        if (!request.validate()) {
            logger.error("GetDiagnosticsRequest inválido: el campo 'location' es obligatorio.");
            sendError(session, webSocketSession, messageId, "GetDiagnosticsRequest inválido: 'location' es obligatorio.");
            future.completeExceptionally(new IllegalArgumentException("GetDiagnosticsRequest inválido"));
            return future;
        }

        // Serializar la solicitud a JSON con la estructura OCPP (array con [2, messageId, action, payload])
        String requestJson = objectMapper.writeValueAsString(
                new Object[]{2, messageId, "GetDiagnostics", request}
        );
        logger.info("Enviando GetDiagnosticsRequest: {}", requestJson);

        // 6. Enviar la solicitud a través de la sesión OCPP
        session.sendTextMessage(requestJson, webSocketSession);
        logger.debug("GetDiagnosticsRequest enviado para messageId: {}", messageId);

        return future;
    }

    public CompletableFuture<ResetConfirmation> sendResetRequestAsync(Session session, WebSocketSession webSocketSession, ResetRequest resetRequest) throws IOException {
        // Generar un messageId único para esta solicitud
        String messageId = UUID.randomUUID().toString();
        logger.debug("Enviando ResetRequest con messageId: {}", messageId);

        // Crear un CompletableFuture para manejar la confirmación
        CompletableFuture<ResetConfirmation> future = new CompletableFuture<>();

        // Registrar la promesa pendiente en el mapa de la sesión
        session.registerPendingPromise(messageId, "Reset", (CompletableFuture) future);
        logger.debug("Stored CompletableFuture for messageId: {}", messageId);

        // Validar la solicitud antes de enviarla
        if (!resetRequest.validate()) {
            logger.error("ResetRequest inválido: type es requerido.");
            sendError(session, webSocketSession, messageId, "ResetRequest inválido: type es requerido.");
            future.completeExceptionally(new IllegalArgumentException("ResetRequest inválido"));
            return future;
        }

        // Serializar la solicitud a JSON con la estructura OCPP correcta
        String requestJson = objectMapper.writeValueAsString(new Object[]{2, messageId, "Reset", resetRequest});
        logger.info("Enviando ResetRequest: {}", requestJson);

        // Enviar la solicitud a través de la sesión OCPP
        session.sendTextMessage(requestJson, webSocketSession);
        logger.debug("ResetRequest enviado para messageId: {}", messageId);

        return future;
    }

    public CompletableFuture<ClearCacheConfirmation> sendClearCacheRequestAsync(Session session, WebSocketSession webSocketSession, ClearCacheRequest request) throws IOException {

        // Generar un messageId único para esta solicitud
        String messageId = UUID.randomUUID().toString();
        logger.info("ClearCacheRequest con messageId: {}", messageId);

        // Crear un CompletableFuture para esperar la confirmación
        CompletableFuture<ClearCacheConfirmation> future = new CompletableFuture<>();

        // Registrar la promesa pendiente en el mapa de la sesión
        session.registerPendingPromise(messageId, "ClearCache", (CompletableFuture) future);

        // Serializar el payload a JSON
        String requestJson = objectMapper.writeValueAsString(new Object[]{2, messageId, "ClearCache", request});

        //Enviar la solcitud a traves de la session OCPP
        session.sendTextMessage(requestJson, webSocketSession);
        logger.info("ClearCacheRequest enviado para la sesión: {}", session.getSessionId());

        return future;
    }

    public CompletableFuture<GetLocalListVersionConfirmation> sendGetLocalListVersionRequestAsync(Session session, WebSocketSession webSocketSession, GetLocalListVersionRequest request) throws IOException {

        //Generar un messageId unico para esta solicitud
        String messageId = UUID.randomUUID().toString();
        logger.info("Solicitud GetLocalListVersion enviada: {}", messageId);

        // Crear el CompletableFuture para manejar la confirmación
        CompletableFuture<GetLocalListVersionConfirmation> future = new CompletableFuture<>();

        // Registrar la promesa pendiente en el mapa de la sesión
        session.registerPendingPromise(messageId, "GetLocalListVersion", (CompletableFuture) future);
        logger.info("Promise registrada para messageId: {}", messageId);

        // Serializar la solicitud
        String requestJson = objectMapper.writeValueAsString(new Object[]{2, messageId, "GetLocalListVersion", request});
        logger.info("Solicitud serializada: {}", requestJson);

        // Enviar la solicitud a través de la sesión OCPP
        session.sendTextMessage(requestJson, webSocketSession);
        logger.debug("GetLocalVersionRequest enviado para messageId: {}", messageId);


        return future;
    }

    public CompletableFuture<ChangeAvailabilityConfirmation> sendChangeAvailabilityRequestAsync(Session session, WebSocketSession webSocketSession, ChangeAvailabilityRequest request) throws IOException {

        //Generar un messageId unico para esta solicitud
        String messageId = UUID.randomUUID().toString();
        logger.debug("Enviando ChangeAvailabilityRequest con messageId: {}", messageId);

        //Crear un CompletableFuture para esperar la confirmación
        CompletableFuture<ChangeAvailabilityConfirmation> future = new CompletableFuture<>();

        //Registrar el CompletableFuture para esperar la confirmación
        session.registerPendingPromise(messageId, "ChangeAvailability", (CompletableFuture) future);

        //Serializar el payload a JSON
        String requestJson = objectMapper.writeValueAsString(new Object[]{2, messageId, "ChangeAvailability", request});

        //Enviar la solicitud a traves de la sesion OCPP
        session.sendTextMessage(requestJson,webSocketSession);
        logger.debug("ChangeAvailabilityRequest enviado con messageId: {}", messageId);

        return future;
    }

    public CompletableFuture<UpdateFirmwareConfirmation> sendUpdateFirmwareRequestAsync(Session session, WebSocketSession webSocketSession, UpdateFirmwareRequest request) throws IOException {
        // Generar un messageId único para esta solicitud
        String messageId = UUID.randomUUID().toString();
        logger.debug("Enviando UpdateFirmwareRequest con messageId: {}", messageId);

        // Crear un CompletableFuture para manejar la confirmación
        CompletableFuture<UpdateFirmwareConfirmation> future = new CompletableFuture<>();

        // Registrar la promesa pendiente en el mapa de la sesión
        session.registerPendingPromise(messageId, "UpdateFirmware", (CompletableFuture) future);
        logger.debug("Stored CompletableFuture for messageId: {}", messageId);

        // Validar la solicitud antes de enviarla
        if (!request.validate()) {
            logger.error("UpdateFirmwareRequest inválido: location y retrieveDate son requeridos.");
            future.completeExceptionally(new IllegalArgumentException("UpdateFirmwareRequest inválido"));
            return future;
        }

        // Serializar la solicitud a JSON con la estructura OCPP correcta
        String requestJson = objectMapper.writeValueAsString(new Object[]{2, messageId, "UpdateFirmware", request});
        logger.info("Enviando UpdateFirmwareRequest: {}", requestJson);

        // Enviar la solicitud a través de la sesión OCPP
        session.sendTextMessage(requestJson, webSocketSession);
        logger.debug("UpdateFirmwareRequest enviado para messageId: {}", messageId);

        return future;
    }

    public CompletableFuture<DataTransferConfirmation> sendDataTransferRequestAsync(Session session, WebSocketSession webSocketSession, DataTransferRequest request) throws IOException {

        // Generar un messageId único para esta solicitud
        String messageId = UUID.randomUUID().toString();

        // Crear un CompletableFuture para esperar la confirmación
        CompletableFuture<DataTransferConfirmation> future = new CompletableFuture<>();

        //Registrar el CompletableFuture para esperar la confirmación
        session.registerPendingPromise(messageId, "DataTransfer", (CompletableFuture) future);

        //Serializar el payload a JSON
        String requestJson = objectMapper.writeValueAsString(new Object[]{2,messageId,"DataTransfer",request});

        //Enviar la solicitud a traves de la sesion OCPP
        session.sendTextMessage(requestJson,webSocketSession);
        logger.info("DataTransferRequest enviado para la sesión: {}", session.getSessionId());

        return future;
    }

    public CompletableFuture<SendLocalListConfirmation> sendSendLocalListRequestAsync(Session session, WebSocketSession webSocketSession, SendLocalListRequest request) throws IOException {
        // Generar un messageId único para esta solicitud
        String messageId = UUID.randomUUID().toString();
        logger.debug("Enviando SendLocalListRequest con messageId: {}", messageId);

        // Crear un CompletableFuture para manejar la confirmación
        CompletableFuture<SendLocalListConfirmation> future = new CompletableFuture<>();

        // Registrar la promesa pendiente en el mapa de la sesión
        session.registerPendingPromise(messageId, "SendLocalList", (CompletableFuture) future);
        logger.debug("Stored CompletableFuture for messageId: {}", messageId);

        // Validar la solicitud antes de enviarla
        if (!request.validate()) {
            logger.error("SendLocalListRequest inválido: listVersion y UpdateType son requeridos.");
            sendError(session, webSocketSession, messageId, "SendLocalListRequest inválido: listVersion y UpdateType son requeridos.");
            future.completeExceptionally(new IllegalArgumentException("SendLocalListRequest inválido"));
            return future;
        }

        // Serializar la solicitud a JSON con la estructura OCPP correcta
        String requestJson = objectMapper.writeValueAsString(new Object[]{2, messageId, "SendLocalList", request});
        logger.info("Enviando SendLocalListRequest: {}", requestJson);

        // Enviar la solicitud a través de la sesión OCPP
        session.sendTextMessage(requestJson, webSocketSession);
        logger.debug("SendLocalListRequest enviado para messageId: {}", messageId);

        return future;
    }

    public CompletableFuture<GetCompositeScheduleConfirmation> sendGetCompositeScheduleRequestAsync(Session session, WebSocketSession webSocketSession, GetCompositeScheduleRequest request) throws IOException {
        // Generar un messageId único para esta solicitud
        String messageId = UUID.randomUUID().toString();
        logger.debug("Enviando GetCompositeScheduleRequest con messageId: {}", messageId);

        // Crear un CompletableFuture para manejar la confirmación
        CompletableFuture<GetCompositeScheduleConfirmation> future = new CompletableFuture<>();

        // Registrar la promesa pendiente en el mapa de la sesión
        session.registerPendingPromise(messageId, "GetCompositeSchedule", (CompletableFuture) future);
        logger.debug("Stored CompletableFuture for messageId: {}", messageId);

        // Validar la solicitud antes de enviarla
        if (!request.validate()) {
            logger.error("GetCompositeScheduleRequest inválido: connectorId y duration son requeridos.");
            sendError(session, webSocketSession, messageId, "GetCompositeScheduleRequest inválido: connectorId y duration son requeridos.");
            future.completeExceptionally(new IllegalArgumentException("GetCompositeScheduleRequest inválido"));
            return future;
        }

        // Serializar la solicitud a JSON con la estructura OCPP correcta
        String requestJson = objectMapper.writeValueAsString(new Object[]{2, messageId, "GetCompositeSchedule", request});
        logger.info("Enviando GetCompositeScheduleRequest: {}", requestJson);

        // Enviar la solicitud a través de la sesión OCPP
        session.sendTextMessage(requestJson, webSocketSession);
        logger.debug("GetCompositeScheduleRequest enviado para messageId: {}", messageId);

        return future;
    }

    public CompletableFuture<ClearChargingProfileConfirmation> sendClearChargingProfileRequestAsync(Session session, WebSocketSession webSocketSession, ClearChargingProfileRequest request) throws IOException {
        // Generar un messageId único para esta solicitud
        String messageId = UUID.randomUUID().toString();
        logger.debug("Enviando ClearChargingProfileRequest con messageId: {}", messageId);

        // Crear un CompletableFuture para manejar la confirmación
        CompletableFuture<ClearChargingProfileConfirmation> future = new CompletableFuture<>();

        // Registrar la promesa pendiente en el mapa de la sesión
        session.registerPendingPromise(messageId, "ClearChargingProfile", (CompletableFuture) future);
        logger.debug("Stored CompletableFuture for messageId: {}", messageId);

        // Validar la solicitud antes de enviarla
        if (!request.validate()) {
            logger.error("ClearChargingProfileRequest inválido: connectorId y chargingProfilePurpose son requeridos.");
            future.completeExceptionally(new IllegalArgumentException("ClearChargingProfileRequest inválido"));
            return future;
        }

        // Serializar la solicitud a JSON con la estructura OCPP correcta
        String requestJson = objectMapper.writeValueAsString(new Object[]{2, messageId, "ClearChargingProfile", request});
        logger.info("Enviando ClearChargingProfileRequest: {}", requestJson);

        // Enviar la solicitud a través de la sesión OCPP
        session.sendTextMessage(requestJson, webSocketSession);
        logger.debug("ClearChargingProfileRequest enviado para messageId: {}", messageId);

        return future;
    }

    public CompletableFuture<SetChargingProfileConfirmation> sendSetChargingProfileRequestAsync(Session session, WebSocketSession webSocketSession, SetChargingProfileRequest request) throws IOException {
        // Generar un messageId único para esta solicitud
        String messageId = UUID.randomUUID().toString();
        logger.debug("Enviando SetChargingProfileRequest con messageId: {}", messageId);

        // Crear un CompletableFuture para manejar la confirmación
        CompletableFuture<SetChargingProfileConfirmation> future = new CompletableFuture<>();

        // Registrar la promesa pendiente en el mapa de la sesión
        session.registerPendingPromise(messageId, "SetChargingProfile", (CompletableFuture) future);
        logger.debug("Stored CompletableFuture for messageId: {}", messageId);

        // Validar la solicitud antes de enviarla
        if (!request.validate()) {
            logger.error("SetChargingProfileRequest inválido: connectorId, chargingProfile y stackLevel son requeridos.");
            future.completeExceptionally(new IllegalArgumentException("SetChargingProfileRequest inválido"));
            return future;
        }

        // Serializar la solicitud a JSON con la estructura OCPP correcta
        String requestJson = objectMapper.writeValueAsString(new Object[]{2,messageId,"SetChargingProfile",request});
        logger.debug("SetChargingProfileRequest serializado a JSON: {}", requestJson);

        // Enviar la solicitud a través de la sesión OCPP
        session.sendTextMessage(requestJson, webSocketSession);
        logger.debug("SetChargingProfileRequest enviado a través de la sesión: {}", session.getSessionId());

        return future;

    }

    public CompletableFuture<CancelReservationConfirmation> sendCancelReservationRequestAsync(Session session, WebSocketSession webSocketSession, CancelReservationRequest request) throws IOException {

        // Generar un messageId único para esta solicitud
        String messageId = UUID.randomUUID().toString();
        logger.debug("Enviando CancelReservationRequest con messageId: {}", messageId);

        // Crear un CompletableFuture para esperar la confirmación
        CompletableFuture<CancelReservationConfirmation> future = new CompletableFuture<>();

        // Registrar la promesa pendiente en el mapa de la sesión
        session.registerPendingPromise(messageId, "CancelReservation", (CompletableFuture) future);
        logger.debug("Registrando promesa pendiente para messageId: {}", messageId);

        // Validar la solicitud antes de enviarla
        if (!request.validate()) {
            future.completeExceptionally(new IllegalArgumentException("CancelReservationRequest inválido."));
            return future;
        }

        // Serializar la solicitud a JSON con la estructura OCPP correcta
        String requestJson = objectMapper.writeValueAsString(new Object[] {2, messageId, "CancelReservation" , request});
        logger.debug("CancelReservationRequest serializado a JSON: {}", requestJson);

        // Enviar la solicitud a través de la sesión OCPP
        session.sendTextMessage(requestJson, webSocketSession);
        logger.debug("CancelReservationRequest enviado a través de la sesión OCPP.");

        return future;
    }

    public CompletableFuture<ReserveNowConfirmation> sendReserveNowRequestAsync(Session session, WebSocketSession webSocketSession, ReserveNowRequest request) throws IOException {
        // Generar un messageId único para esta solicitud
        String messageId = UUID.randomUUID().toString();
        logger.debug("Enviando ReserveNowRequest con messageId: {}", messageId);

        // Crear un CompletableFuture para manejar la confirmación
        CompletableFuture<ReserveNowConfirmation> future = new CompletableFuture<>();

        // Registrar la promesa pendiente en el mapa de la sesión
        session.registerPendingPromise(messageId, "ReserveNow", (CompletableFuture) future);
        logger.debug("Stored CompletableFuture for messageId: {}", messageId);

        // Validar la solicitud antes de enviarla
        if (!request.validate()) {
            logger.error("ReserveNowRequest inválido: connectorId, expiryDate y idTag son requeridos.");
            future.completeExceptionally(new IllegalArgumentException("ReserveNowRequest inválido"));
            return future;
        }

        // Serializar la solicitud a JSON con la estructura OCPP correcta
        String requestJson = objectMapper.writeValueAsString(new Object[]{2, messageId, "ReserveNow", request});
        logger.info("Enviando ReserveNowRequest: {}", requestJson);

        // Enviar la solicitud a través de la sesión OCPP
        session.sendTextMessage(requestJson, webSocketSession);
        logger.debug("ReserveNowRequest enviado para messageId: {}", messageId);

        return future;
    }

    /**
     * Maneja la recepción de CallResult y completa el CompletableFuture correspondiente.
     *
     * @param session       La instancia de la sesión OCPP.
     * @param messageId     El ID del mensaje.
     * @param responsePayload El contenido de la respuesta.
     */
    private void handleCallResult(Session session, String messageId, Object responsePayload) {
        logger.debug("Recibiendo CallResult para messageId: {}", messageId);

        // Obtener la acción asociada al messageId
        String action = session.getPendingAction(messageId);
        if (action == null) {
            logger.warn("No se encontró una acción para el messageId: {}", messageId);
            return;
        }

        CompletableFuture<Confirmation> future = session.getPendingPromise(messageId);
        if (future == null) {
            logger.warn("No se encontró un CompletableFuture para messageId: {}", messageId);
            return;
        }

        try {
            Confirmation confirmation;
            switch (action) {
                case "GetConfiguration":
                    confirmation = objectMapper.convertValue(responsePayload, GetConfigurationConfirmation.class);
                    break;
                case "TriggerMessage":
                    confirmation = objectMapper.convertValue(responsePayload, TriggerMessageConfirmation.class);
                    break;
                case "UnlockConnector":
                    confirmation = objectMapper.convertValue(responsePayload, UnlockConnectorConfirmation.class);
                    break;
                case "RemoteStartTransaction":
                    confirmation = objectMapper.convertValue(responsePayload, RemoteStartTransactionConfirmation.class);
                    break;
                case "RemoteStopTransaction":
                    confirmation = objectMapper.convertValue(responsePayload, RemoteStopTransactionConfirmation.class);
                    break;
                case "FirmwareStatusNotification":
                    confirmation = objectMapper.convertValue(responsePayload, FirmwareStatusNotificationConfirmation.class);
                    break;
                case "ChangeConfiguration":
                    confirmation = objectMapper.convertValue(responsePayload, ChangeConfigurationConfirmation.class);
                    break;
                case "GetDiagnostics":
                    confirmation = objectMapper.convertValue(responsePayload, GetDiagnosticsConfirmation.class);
                    break;
                case "Reset":
                    confirmation = objectMapper.convertValue(responsePayload, ResetConfirmation.class);
                    break;
                case "ClearCache":
                    confirmation = objectMapper.convertValue(responsePayload, ClearCacheConfirmation.class);
                    break;
                case "GetLocalListVersion":
                    confirmation = objectMapper.convertValue(responsePayload, GetLocalListVersionConfirmation.class);
                    break;
                case "ChangeAvailability":
                    confirmation = objectMapper.convertValue(responsePayload, ChangeAvailabilityConfirmation.class);
                    break;
                case "UpdateFirmware":
                    confirmation = objectMapper.convertValue(responsePayload, UpdateFirmwareConfirmation.class);
                    break;
                case "DataTransfer":
                    confirmation = objectMapper.convertValue(responsePayload, DataTransferConfirmation.class);
                    break;
                case "SendLocalList":
                    confirmation = objectMapper.convertValue(responsePayload, SendLocalListConfirmation.class);
                    break;
                case "GetCompositeSchedule":
                    confirmation = objectMapper.convertValue(responsePayload, GetCompositeScheduleConfirmation.class);
                    break;
                case "ClearChargingProfile":
                    confirmation = objectMapper.convertValue(responsePayload, ClearChargingProfileConfirmation.class);
                    break;
                case "SetChargingProfile":
                    confirmation = objectMapper.convertValue(responsePayload, SetChargingProfileConfirmation.class);
                    break;
                case "CancelReservation":
                    confirmation = objectMapper.convertValue(responsePayload, CancelReservationConfirmation.class);
                    break;
                case "ReserveNow":
                    confirmation = objectMapper.convertValue(responsePayload, ReserveNowConfirmation.class);
                    break;
                default:
                    logger.warn("Acción no soportada o no mapeada: {}", action);
                    future.completeExceptionally(new IllegalStateException("Acción no soportada: " + action));
                    return;
            }

            // Validar la confirmación
            if (confirmation == null || !confirmation.validate()) {
                throw new IllegalArgumentException("La confirmación para la acción " + action + " es inválida.");
            }

            // Completar el futuro con la confirmación
            future.complete(confirmation);
            logger.info("Confirmación {} completada para messageId: {}", confirmation.getClass().getSimpleName(), messageId);

        } catch (Exception e) {
            logger.error("Error al procesar la confirmación para messageId: {}, acción: {}", messageId, action, e);
            future.completeExceptionally(e);
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

    private void handleDiagnosticsStatusNotification(Session session, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {
        try {
            // Deserializar el payload a DiagnosticsStatusNotificationRequest
            DiagnosticsStatusNotificationRequest diagnosticsRequest = objectMapper.convertValue(requestPayload, DiagnosticsStatusNotificationRequest.class);
            logger.info("DiagnosticsStatusNotificationRequest recibido: {}", diagnosticsRequest);

            // Validar la solicitud si es necesario
            if (!diagnosticsRequest.validate()) {
                throw new IllegalArgumentException("DiagnosticsStatusNotificationRequest inválido.");
            }

            // Crear la confirmación de la respuesta
            DiagnosticsStatusNotificationConfirmation confirmation = new DiagnosticsStatusNotificationConfirmation();
            logger.info("DiagnosticsStatusNotificationConfirmation creada: {}", confirmation);

            // Enviar la confirmación al cliente
            sendResponse(session, webSocketSession, messageId, "DiagnosticsStatusNotification", confirmation);
            logger.info("DiagnosticsStatusNotification completado exitosamente para la sesión: {}", session.getSessionId());

        } catch (IllegalArgumentException e) {
            // Manejar errores de validación
            logger.error("Error en los argumentos de DiagnosticsStatusNotification: {}", e.getMessage());
            sendError(session, webSocketSession, messageId, "Error en DiagnosticsStatusNotification: " + e.getMessage());
        } catch (Exception e) {
            // Manejar cualquier otro error inesperado
            logger.error("Error procesando DiagnosticsStatusNotification para la sesión: {}",
                    session != null ? session.getSessionId() : "Sesión no disponible", e);
            sendError(session, webSocketSession, messageId, "Error en DiagnosticsStatusNotification: " + e.getMessage());
        }
    }

    private void handleFirmwareStatusNotification(Session session, WebSocketSession webSocketSession, Object requestPayload, String messageId) throws IOException {

        try {
            // Deserializar el payload a FirmawareNotificationRequest
            FirmwareStatusNotificationRequest firmwareRequest = objectMapper.convertValue(requestPayload, FirmwareStatusNotificationRequest.class);
            logger.info("FirmwareStatusNotificationRequest recibido: {}", firmwareRequest);

            // Validar la solicitud
            if (!firmwareRequest.validate()) {
                throw new IllegalArgumentException("DiagnosticsStatusNotificationRequest inválido.");
            }

            // Crear la confirmación de la respuesta
            FirmwareStatusNotificationConfirmation confirmation = new FirmwareStatusNotificationConfirmation();
            logger.info("FirmwareStatusNotificationConfirmation creada: {}", confirmation);

            // Enviar la confirmación al cliente
            sendResponse(session, webSocketSession, messageId, "FirmwareStatusNotification", confirmation);
            logger.info("FirmwareStatusNotification completado exitosamente para la sesión: {}", session.getSessionId());

        } catch (IllegalArgumentException e) {
            // Manejar errores de validación
            logger.error("Error en los argumentos de FirmawareNotificationRequest: {}", e.getMessage());
            sendError(session, webSocketSession, messageId, "Error en FirmawareNotificationRequest: " + e.getMessage());
        } catch (Exception e) {
            // Manejar cualquier otro error inesperado
            logger.error("Error procesando FirmawareNotificationRequest para la sesión: {}",
                    session != null ? session.getSessionId() : "Sesión no disponible", e);
            sendError(session, webSocketSession, messageId, "Error en FirmawareNotificationRequest: " + e.getMessage());
        }
    }

    private ConnectorStatus mapChargePointStatusToConnectorStatus(ChargePointStatus status) {
        return switch (status) {
            case Charging -> ConnectorStatus.OCCUPIED;
            case Available -> ConnectorStatus.CONNECTED;
            case Unavailable -> ConnectorStatus.DISCONNECTED;
            case SuspendedEV -> ConnectorStatus.SUSPENDED;
            case Finishing -> ConnectorStatus.FINISHING;
            default -> ConnectorStatus.DISCONNECTED;
        };
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
