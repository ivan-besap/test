package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.CallErrorException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.OccurenceConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.UnsupportedFeatureException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.SessionInformation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Clase principal que gestiona la lógica básica del cliente OCPP en nuestro CMS eVolGreen.
 * <p>
 * Almacena y gestiona las características soportadas, rastrea solicitudes salientes
 * y responde a las confirmaciones recibidas desde el servidor.
 * </p>
 */
public class Client {

    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    private final ISession session;
    private final IPromiseRepository promiseRepository;

    /**
     * Constructor para la inyección de dependencias necesarias.
     *
     * @param session           El objeto de sesión inyectado que gestiona la conexión con el servidor
     * @param featureRepository
     * @param promiseRepository El repositorio que gestiona las promesas y las respuestas
     */
    public Client(ISession session, FeatureRepository featureRepository, IPromiseRepository promiseRepository) {
        this.session = session;
        this.promiseRepository = promiseRepository;
    }

    /**
     * Establece la conexión con el servidor.
     *
     * @param uri La URL del servidor OCPP
     * @param events Eventos del cliente, como conexión o desconexión
     */
    public void connect(String uri, ClientEvents events) throws InterruptedException {
        session.open(
                uri,
                new SessionEvents() {

                    @Override
                    public void handleConfirmation(String uniqueId, Confirmation confirmation) {
                        Optional<CompletableFuture<Confirmation>> promiseOptional =
                                promiseRepository.getPromise(uniqueId);
                        if (promiseOptional.isPresent()) {
                            promiseOptional.get().complete(confirmation);
                            promiseRepository.removePromise(uniqueId);
                        } else {
                            logger.debug("No se encontró promesa para la confirmación {}", confirmation);
                        }
                    }

                    @Override
                    public Confirmation handleRequest(Request request) throws UnsupportedFeatureException {
                        Optional<Feature> featureOptional = session.getFeatureRepository().findFeature(request.getAction());
                        if (featureOptional.isPresent()) {
                            return featureOptional.get().handleRequest(getSessionId(), request);
                        } else {
                            throw new UnsupportedFeatureException();
                        }
                    }

                    @Override
                    public boolean asyncCompleteRequest(String uniqueId, Confirmation confirmation) throws UnsupportedFeatureException, OccurenceConstraintException {
                        return session.completePendingPromise(uniqueId, confirmation);
                    }

                    @Override
                    public void handleError(String uniqueId, String errorCode, String errorDescription, Object payload) {
                        Optional<CompletableFuture<Confirmation>> promiseOptional =
                                promiseRepository.getPromise(uniqueId);
                        if (promiseOptional.isPresent()) {
                            promiseOptional.get().completeExceptionally(
                                    new CallErrorException(errorCode, errorDescription, payload)
                            );
                            promiseRepository.removePromise(uniqueId);
                        } else {
                            logger.debug("No se encontró promesa para el error {}", errorDescription);
                        }
                    }

                    @Override
                    public void onError(String uniqueId, String errorCode, String errorDescription, Request request) {
                        handleError(uniqueId, errorCode, errorDescription, request);
                    }

                    @Override
                    public void newSession(UUID sessionIndex, SessionInformation information) {


                    }

                    @Override
                    public void lostSession(UUID sessionIndex) {

                    }

                    @Override
                    public void handleConnectionClosed() {
                        if (events != null) events.connectionClosed();
                    }

                    @Override
                    public void handleConnectionOpened() {
                        if (events != null) events.connectionOpened();
                    }
                }
        );
    }

    /** Desconecta del servidor */
    public void disconnect() {
        try {
            session.close();
        } catch (Exception ex) {
            logger.info("Error al cerrar la sesión", ex);
        }
    }

    /**
     * Envía una solicitud {@link Request} al servidor.
     * Solo se pueden enviar solicitudes que sean compatibles con el cliente.
     *
     * @param request Solicitud saliente
     * @return Un objeto futuro que será completado con la confirmación recibida
     * @throws UnsupportedFeatureException Si se intenta enviar una solicitud de una característica no soportada
     * @throws OccurenceConstraintException Si la solicitud no es válida
     */
    public CompletableFuture<Confirmation> send(Request request) throws UnsupportedFeatureException, OccurenceConstraintException {
        Optional<Feature> featureOptional = session.getFeatureRepository().findFeature(request.getAction());
        if (!featureOptional.isPresent()) {
            logger.error("No se puede enviar la solicitud: característica no soportada. Payload: {}", request);
            throw new UnsupportedFeatureException();
        }

        if (!request.validate()) {
            logger.error("No se puede enviar la solicitud: no validada. Payload: {}", request);
            throw new OccurenceConstraintException();
        }

        String id = session.storeRequest(request);
        CompletableFuture<Confirmation> promise = promiseRepository.createPromise(id);

        session.sendRequest(featureOptional.get().getAction(), request, id);
        return promise;
    }

    /**
     * Obtiene el identificador de sesión actual.
     *
     * @return El UUID de la sesión
     */
    public UUID getSessionId() {
        return this.session.getSessionId();
    }

    /**
     * Completa de manera asincrónica una solicitud pendiente.
     *
     * @param uniqueId El ID único de la solicitud
     * @param confirmation La confirmación recibida para esa solicitud
     * @return true si se completa correctamente
     * @throws UnsupportedFeatureException Si se trata de completar una solicitud para una característica no soportada
     * @throws OccurenceConstraintException Si la confirmación no es válida
     */
    public boolean asyncCompleteRequest(String uniqueId, Confirmation confirmation) throws UnsupportedFeatureException, OccurenceConstraintException {
        return session.completePendingPromise(uniqueId, confirmation);
    }
}
