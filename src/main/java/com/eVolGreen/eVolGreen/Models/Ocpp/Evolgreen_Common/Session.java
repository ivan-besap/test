package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.OccurenceConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.UnsupportedFeatureException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Feature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import static com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.ProtocolVersion.OCPP1_6;

/**
 * La clase Session maneja la comunicación de mensajes OCPP entre el cliente y el servidor,
 * almacenando solicitudes, confirmaciones y gestionando la interacción con el protocolo.
 * Cada sesión tiene un identificador único y se conecta o desconecta de un servidor utilizando un {@link Communicator}.
 */
public class Session implements ISession {

    private static final Logger logger = LoggerFactory.getLogger(Session.class);
    private UUID sessionId = UUID.randomUUID();
    private final Communicator communicator;
    private final Queue queue;
    private final RequestDispatcher dispatcher;
    private final IFeatureRepository featureRepository;
    private SessionEvents events;
    private final Map<String, AbstractMap.SimpleImmutableEntry<String, CompletableFuture<Confirmation>>> pendingPromises = new ConcurrentHashMap<>();

    private WebSocketSession webSocketSession;
    private String webSocketSessionId; // Atributo que almacena el ID de WebSocketSession

    private String chargePointId;

    /**
     * Constructor que maneja la inyección de dependencias necesarias.
     *
     * @param communicator el comunicador para enviar y recibir mensajes.
     * @param queue         la cola para almacenar y restaurar solicitudes.
     * @param fulfiller     objeto para gestionar promesas.
     * @param featureRepository el repositorio de características soportadas.
     */
    public Session(UUID sessionId,Communicator communicator, Queue queue, PromiseFulfiller fulfiller, IFeatureRepository featureRepository) {
        this.sessionId = sessionId;
        this.communicator = communicator;
        this.queue = queue;
        this.dispatcher = new RequestDispatcher(fulfiller);
        this.featureRepository = featureRepository;
    }

    /**
     * Obtiene el repositorio de características utilizado en esta sesión.
     *
     * @return el repositorio de características de la sesión.
     */
    @Override
    public IFeatureRepository getFeatureRepository() {
        return featureRepository;
    }

    /**
     * Obtiene el identificador único de la sesión.
     *
     * @return el UUID único de la sesión.
     */
    public UUID getSessionId() {
        return sessionId;
    }

    /**
     * Envía una solicitud al servidor.
     *
     * @param action  el nombre de la acción a realizar.
     * @param payload el objeto de solicitud a enviar.
     * @param uuid    el identificador único de la solicitud.
     */
    public void sendRequest(String action, Request payload, String uuid) {
        ensureRadioConnected();  // Verifica y conecta el radio si está desconectado
        communicator.sendCall(uuid, action, payload);
    }

    private void ensureRadioConnected() {
        // Verifica si communicator.radio es null y, si es así, inicialízalo
        if (communicator.radio == null) {
            communicator.radio = new DefaultRadioImplementation();
            logger.info("Radio inicializado con DefaultRadioImplementation.");
        }

        // Conecta el radio si está cerrado
        if (communicator.radio.isClosed() && communicator.radio instanceof DefaultRadioImplementation) {
            ((DefaultRadioImplementation) communicator.radio).connect();
            logger.info("Radio reconectado automáticamente.");
        }
    }

    /**
     * Almacena una solicitud y devuelve un identificador único.
     *
     * @param payload el objeto de solicitud a almacenar.
     * @return el identificador único para la solicitud almacenada.
     */
    public String storeRequest(Request payload) {
        return queue.store(payload);
    }

    /**
     * Envía una confirmación a una solicitud recibida.
     *
     * @param uniqueId     el identificador único de la solicitud a confirmar.
     * @param action       la acción asociada a la confirmación.
     * @param confirmation el objeto de confirmación.
     */
    public void sendConfirmation(String uniqueId, String action, Confirmation confirmation) {
        communicator.sendCallResult(uniqueId, action, confirmation);
    }

    private Optional<Class<? extends Confirmation>> getConfirmationType(String uniqueId)
            throws UnsupportedFeatureException {
        Optional<Request> requestOptional = queue.restoreRequest(uniqueId);

        if (requestOptional.isPresent()) {
            Optional<Feature> featureOptional = featureRepository.findFeature(requestOptional.get());
            if (featureOptional.isPresent()) {
                return Optional.of(featureOptional.get().getConfirmationType());
            } else {
                logger.debug("Feature for request with id: {} not found in session: {}", uniqueId, this);
                throw new UnsupportedFeatureException(
                        "Error with getting confirmation type by request id = " + uniqueId);
            }
        } else {
            logger.debug("Request with id: {} not found in session: {}", uniqueId, this);
        }

        return Optional.empty();
    }

    /**
     * Conecta a una URI específica proporcionando un manejador de eventos de conexión.
     *
     * @param uri          la URL del sistema remoto.
     * @param eventHandler el manejador de eventos de conexión.
     */
    public void open(String uri, SessionEvents eventHandler) {
        this.events = eventHandler;
        dispatcher.setEventHandler(eventHandler);
        communicator.connect(uri, new CommunicatorEventHandler());
    }

    /** Cierra la conexión. */
    public void close() {
        communicator.disconnect();
    }

    public void accept(SessionEvents eventHandler) {
        this.events = eventHandler;
        dispatcher.setEventHandler(eventHandler);
        communicator.accept(new CommunicatorEventHandler());
    }

    public void sendTextMessage(String message, WebSocketSession webSocketSession) throws IOException {
        if (webSocketSession.isOpen()) {
            webSocketSession.sendMessage(new TextMessage(message));
        } else {
            throw new IOException("La sesión WebSocket no está abierta.");
        }
    }

    public WebSocketSession getWebSocketSession() {
        return webSocketSession;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    public void setWebSocketSessionId(String webSocketSessionId) {
        this.webSocketSessionId = webSocketSessionId;
    }

    public String getWebSocketSessionId() {
        return this.webSocketSessionId;
    }


    private class CommunicatorEventHandler implements CommunicatorEvents {
        private static final String OCCURRENCE_CONSTRAINT_VIOLATION =
                "Payload for Action is syntactically correct but at least one of the fields violates occurrence constraints";
        private static final String INTERNAL_ERROR =
                "An internal error occurred and the receiver was not able to process the requested Action successfully";
        private static final String UNABLE_TO_PROCESS = "Unable to process action";

        @Override
        public void onCallResult(String id, String action, Object payload) {
            try {
                Optional<Class<? extends Confirmation>> confirmationTypeOptional = getConfirmationType(id);

                if (confirmationTypeOptional.isPresent()) {
                    Confirmation confirmation =
                            communicator.unpackPayload(payload, confirmationTypeOptional.get());
                    if (confirmation.validate()) {
                        events.handleConfirmation(id, confirmation);
                    } else {
                        communicator.sendCallError(id, action, isLegacyRPC() ? "OccurenceConstraintViolation" :
                                "OccurrenceConstraintViolation", OCCURRENCE_CONSTRAINT_VIOLATION);
                    }
                } else {
                    logger.warn(INTERNAL_ERROR);
                    communicator.sendCallError(id, action, "InternalError", INTERNAL_ERROR);
                }
            } catch (PropertyConstraintException ex) {
                logger.warn(ex.getMessage(), ex);
                communicator.sendCallError(id, action, "TypeConstraintViolation", ex.getMessage());
            } catch (UnsupportedFeatureException ex) {
                logger.warn(INTERNAL_ERROR, ex);
                communicator.sendCallError(id, action, "InternalError", INTERNAL_ERROR);
            } catch (Exception ex) {
                logger.warn(UNABLE_TO_PROCESS, ex);
                communicator.sendCallError(id, action, isLegacyRPC() ? "FormationViolation" :
                        "FormatViolation", UNABLE_TO_PROCESS);
            }
        }

        @Override
        public synchronized void onCall(String id, String action, Object payload) {
            Optional<Feature> featureOptional = featureRepository.findFeature(action);
            if (!featureOptional.isPresent()) {
                communicator.sendCallError(
                        id, action, "NotImplemented", "Requested Action is not known by receiver");
            } else {
                try {
                    Request request =
                            communicator.unpackPayload(payload, featureOptional.get().getRequestType());
                    request.setOcppMessageId(id);
                    if (request.validate()) {
                        CompletableFuture<Confirmation> promise = new CompletableFuture<>();
                        promise.whenComplete(new ConfirmationHandler(id, action, communicator));
                        addPendingPromise(id, action, promise);
                        dispatcher.handleRequest(promise, request);
                    } else {
                        communicator.sendCallError(id, action, isLegacyRPC() ? "OccurenceConstraintViolation" :
                                "OccurrenceConstraintViolation", OCCURRENCE_CONSTRAINT_VIOLATION);
                    }
                } catch (PropertyConstraintException ex) {
                    logger.warn(ex.getMessage(), ex);
                    communicator.sendCallError(id, action, "TypeConstraintViolation", ex.getMessage());
                } catch (Exception ex) {
                    logger.warn(UNABLE_TO_PROCESS, ex);
                    communicator.sendCallError(id, action, isLegacyRPC() ? "FormationViolation" :
                            "FormatViolation", UNABLE_TO_PROCESS);
                }
            }
        }

        @Override
        public void onError(String id, String errorCode, String errorDescription, Object payload) {
            events.handleError(id, errorCode, errorDescription, payload);
        }

        @Override
        public void onDisconnected() {
            events.handleConnectionClosed();
        }

        @Override
        public void onConnected() {
            events.handleConnectionOpened();
        }

        private boolean isLegacyRPC() {
            ProtocolVersion protocolVersion = featureRepository.getProtocolVersion();
            return protocolVersion == null || protocolVersion.equals(OCPP1_6);
        }
    }

    private void addPendingPromise(String id, String action, CompletableFuture<Confirmation> promise) {
        synchronized (pendingPromises) {
            pendingPromises.put(id, new AbstractMap.SimpleImmutableEntry<>(action, promise));
        }
    }

    @Override
    public boolean completePendingPromise(String id, Confirmation confirmation)
            throws UnsupportedFeatureException, OccurenceConstraintException {
        AbstractMap.SimpleImmutableEntry<String, CompletableFuture<Confirmation>> promiseAction = null;
        synchronized (pendingPromises) {
            promiseAction = pendingPromises.get(id);
            if (promiseAction == null) return false;
            pendingPromises.remove(id);
        }

        Optional<Feature> featureOptional = featureRepository.findFeature(promiseAction.getKey());
        if (featureOptional.isPresent()) {
            if (!featureOptional.get().getConfirmationType().isInstance(confirmation)) {
                throw new OccurenceConstraintException();
            }
        } else {
            throw new UnsupportedFeatureException("Característica no encontrada para id: " + id);
        }

        promiseAction.getValue().complete(confirmation);
        return true;
    }

    @Override
    public void registerSession(SessionEvents eventHandler) {
        this.events = eventHandler;
        dispatcher.setEventHandler(eventHandler);
        communicator.accept(new CommunicatorEventHandler());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return MoreObjects.equals(sessionId, session.sessionId);
    }

    @Override
    public int hashCode() {
        return MoreObjects.hash(sessionId);
    }

    public void setChargePointId(String chargePointId) {
        this.chargePointId = chargePointId;
    }

    // Método getter para chargePointId
    public String getChargePointId() {
        return chargePointId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("sessionId", sessionId)
                .add("communicator", communicator)
                .add("queue", queue)
                .add("dispatcher", dispatcher)
                .add("featureRepository", featureRepository)
                .add("events", events)
                .toString();
    }
}
