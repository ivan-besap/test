package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.SessionInformation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Maneja la lógica básica del servidor:
 * Mantiene una lista de características soportadas.
 * Realiza un seguimiento de las solicitudes salientes.
 * Llama de vuelta cuando se recibe una confirmación.
 */
public class Server {

    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public static final int INITIAL_SESSIONS_NUMBER = 1000;

    private final Map<UUID, ISession> sessions;
    private final Listener listener;
    private final IPromiseRepository promiseRepository;

    /**
     * Constructor. Maneja las inyecciones requeridas.
     *
     * @param listener El listener inyectado.
     * @param promiseRepository El repositorio de promesas inyectado.
     */
    public Server(
            Listener listener,
            IPromiseRepository promiseRepository) {
        this.listener = listener;
        this.promiseRepository = promiseRepository;
        this.sessions = new ConcurrentHashMap<>(INITIAL_SESSIONS_NUMBER);
    }

    /**
     * Inicia la escucha de clientes.
     *
     * @param hostname URL o IP del servidor como String.
     * @param port El número de puerto del servidor.
     * @param serverEvents Manejador de devolución de llamada para eventos específicos del servidor.
     */
    public void open(String hostname, int port, ServerEvents serverEvents) {

        listener.open(
                hostname,
                port,
                new ListenerEvents() {

                    @Override
                    public void authenticateSession(
                            SessionInformation information, String username, String password)
                            throws AuthenticationException {
                        serverEvents.authenticateSession(information, username, password);
                    }

                    @Override
                    public void newSession(ISession session, SessionInformation information) {
                        session.accept(
                                new SessionEvents() {
                                    @Override
                                    public void handleConfirmation(String uniqueId, Confirmation confirmation) {

                                        Optional<CompletableFuture<Confirmation>> promiseOptional =
                                                promiseRepository.getPromise(uniqueId);
                                        if (promiseOptional.isPresent()) {
                                            promiseOptional.get().complete(confirmation);
                                            promiseRepository.removePromise(uniqueId);
                                        } else {
                                            logger.debug("Promise not found for confirmation {}", confirmation);
                                        }
                                    }

                                    @Override
                                    public Confirmation handleRequest(Request request)
                                            throws UnsupportedFeatureException {
                                        Optional<Feature> featureOptional = session.getFeatureRepository().findFeature(request);
                                        if (featureOptional.isPresent()) {
                                            Optional<UUID> sessionIdOptional = getSessionID(session);
                                            if (sessionIdOptional.isPresent()) {
                                                return featureOptional
                                                        .get()
                                                        .handleRequest(sessionIdOptional.get(), request);
                                            } else {
                                                logger.error(
                                                        "Unable to handle request ({}), the active session was not found for {}.",
                                                        request, session.getSessionId());
                                                throw new IllegalStateException("Active session not found");
                                            }
                                        } else {
                                            throw new UnsupportedFeatureException();
                                        }
                                    }

                                    @Override
                                    public boolean asyncCompleteRequest(String uniqueId, Confirmation confirmation) throws UnsupportedFeatureException, OccurenceConstraintException {
                                        return session.completePendingPromise(uniqueId, confirmation);
                                    }

                                    @Override
                                    public void handleError(
                                            String uniqueId, String errorCode, String errorDescription, Object payload) {
                                        Optional<CompletableFuture<Confirmation>> promiseOptional =
                                                promiseRepository.getPromise(uniqueId);
                                        if (promiseOptional.isPresent()) {
                                            promiseOptional
                                                    .get()
                                                    .completeExceptionally(
                                                            new CallErrorException(errorCode, errorDescription, payload));
                                            promiseRepository.removePromise(uniqueId);
                                        } else {
                                            logger.debug("Promise not found for error {}", errorDescription);
                                        }
                                    }

                                    @Override
                                    public void handleConnectionClosed() {
                                        Optional<UUID> sessionIdOptional = getSessionID(session);
                                        if (sessionIdOptional.isPresent()) {
                                            serverEvents.lostSession(sessionIdOptional.get());
                                            sessions.remove(sessionIdOptional.get());
                                        } else {
                                            logger.warn("Active session not found for {}", session.getSessionId());
                                        }
                                    }

                                    @Override
                                    public void handleConnectionOpened() {}
                                });

                        sessions.put(session.getSessionId(), session);

                        Optional<UUID> sessionIdOptional = getSessionID(session);
                        if (sessionIdOptional.isPresent()) {
                            serverEvents.newSession(sessionIdOptional.get(), information);
                            logger.debug("Session created: {}", session.getSessionId());
                        } else {
                            throw new IllegalStateException("Failed to create a session");
                        }
                    }
                });
    }

    /**
     * Obtiene el ID de sesión para una sesión dada.
     *
     * @param session La sesión para la que se busca el ID.
     * @return Un Optional conteniendo el UUID de la sesión si existe, o un Optional vacío si no.
     */
    private Optional<UUID> getSessionID(ISession session) {
        if (!sessions.containsKey(session.getSessionId())) {
            return Optional.empty();
        }

        return Optional.of(session.getSessionId());
    }

    /**
     * Cierra todas las conexiones y detiene la escucha de clientes.
     */
    public void close() {
        listener.close();
    }

    /**
     * Envía un mensaje a un cliente.
     *
     * @param sessionIndex Índice de sesión del cliente.
     * @param request Solicitud para el cliente.
     * @return Manejador de devolución de llamada para cuando el cliente responde.
     * @throws UnsupportedFeatureException Si la característica no está entre la lista de características soportadas.
     * @throws OccurenceConstraintException Si la solicitud no es válida.
     * @throws NotConnectedException Si la sesión no está conectada.
     */
    public CompletableFuture<Confirmation> send(UUID sessionIndex, Request request)
            throws UnsupportedFeatureException, OccurenceConstraintException, NotConnectedException {
        ISession session = sessions.get(sessionIndex);

        if (session == null) {
            logger.warn("Session not found by index: {}", sessionIndex);

            // No se encontró sesión significa que el cliente se desconectó y la solicitud debe cancelarse
            throw new NotConnectedException();
        }

        Optional<Feature> featureOptional = session.getFeatureRepository().findFeature(request);
        if (!featureOptional.isPresent()) {
            throw new UnsupportedFeatureException();
        }

        if (!request.validate()) {
            throw new OccurenceConstraintException();
        }

        String id = session.storeRequest(request);
        CompletableFuture<Confirmation> promise = promiseRepository.createPromise(id);
        session.sendRequest(featureOptional.get().getAction(), request, id);
        return promise;
    }

    /**
     * Indica la finalización de una solicitud pendiente.
     *
     * @param sessionIndex Índice de sesión del cliente.
     * @param uniqueId El id único utilizado para la {@link Request} original.
     * @param confirmation La {@link Confirmation} para la {@link Request} original.
     * @return Un booleano que indica si se encontró la solicitud pendiente.
     * @throws NotConnectedException Si no se encuentra la sesión con el sessionIndex pasado.
     * @throws UnsupportedFeatureException Si la característica no está soportada.
     * @throws OccurenceConstraintException Si la confirmación no es válida.
     */
    public boolean asyncCompleteRequest(UUID sessionIndex, String uniqueId, Confirmation confirmation) throws NotConnectedException, UnsupportedFeatureException, OccurenceConstraintException {
        ISession session = sessions.get(sessionIndex);

        if (session == null) {
            logger.warn("Session not found by index: {}", sessionIndex);

            // No se encontró sesión significa que el cliente se desconectó y la solicitud debe cancelarse
            throw new NotConnectedException();
        }

        return session.completePendingPromise(uniqueId, confirmation);
    }

    /**
     * Verifica si una sesión está abierta.
     *
     * @param sessionIndex Índice de sesión del cliente.
     * @return true si la sesión está abierta, false en caso contrario.
     */
    public boolean isSessionOpen(UUID sessionIndex) {
        return sessions.containsKey(sessionIndex);
    }

    /**
     * Cierra la conexión con un cliente.
     *
     * @param sessionIndex Índice de sesión del cliente.
     */
    public void closeSession(UUID sessionIndex) {
        ISession session = sessions.get(sessionIndex);
        if (session != null) {
            session.close();
        }
    }
}