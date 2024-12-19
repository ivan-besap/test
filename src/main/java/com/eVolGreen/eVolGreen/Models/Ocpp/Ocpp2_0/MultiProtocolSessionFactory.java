package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0;


import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.*;

import java.util.UUID;

/**
 * Factoría para crear sesiones de cliente y servidor compatibles con múltiples versiones del
 * protocolo OCPP.
 */
public class MultiProtocolSessionFactory implements ISessionFactory {

    private final MultiProtocolFeatureRepository multiProtocolFeatureRepository;

    private UUID sessionId;

    /**
     * Constructor de la factoría.
     *
     * @param multiProtocolFeatureRepository Repositorio de características que gestiona
     *     implementaciones para múltiples versiones del protocolo.
     */
    public MultiProtocolSessionFactory(
            MultiProtocolFeatureRepository multiProtocolFeatureRepository) {
        this.multiProtocolFeatureRepository = multiProtocolFeatureRepository;
    }

    /**
     * Crea una sesión para el cliente.
     *
     * <p>La sesión utilizará el repositorio de características del {@link MultiProtocolFeatureRepository}
     * seleccionado utilizando {@link MultiProtocolFeatureRepository#selectProtocolVersion(ProtocolVersion)}.
     * Esto ocurre después de que la versión del protocolo negociada sea conocida (tras el handshake
     * del WebSocket).
     *
     * @param communicator El {@link Communicator} utilizado para la sesión del cliente.
     * @return La sesión del cliente como instancia de {@link ISession}.
     */
    @Override
    public ISession createSession(Communicator communicator) {
        AsyncPromiseFulfillerDecorator promiseFulfiller =
                new AsyncPromiseFulfillerDecorator(new SimplePromiseFulfiller());
        return new Session(sessionId ,communicator, new Queue(), promiseFulfiller, multiProtocolFeatureRepository);
    }

    @Override
    public ISession createSession(UUID sessionId, Communicator communicator, Queue queue, PromiseFulfiller fulfiller, IFeatureRepository featureRepository) {
        return null;
    }

    /**
     * Crea una sesión para el servidor.
     *
     * <p>La sesión utilizará el repositorio de características asociado a la versión del protocolo
     * proporcionada.
     *
     * @param communicator El {@link Communicator} utilizado para la sesión del servidor.
     * @param protocolVersion La versión del protocolo {@link ProtocolVersion} que será utilizada por
     *     la sesión del servidor.
     * @return La sesión del servidor como instancia de {@link ISession}.
     */
    public ISession createSession(Communicator communicator, ProtocolVersion protocolVersion) {
        IFeatureRepository featureRepository =
                multiProtocolFeatureRepository.getFeatureRepository(protocolVersion);
        AsyncPromiseFulfillerDecorator promiseFulfiller =
                new AsyncPromiseFulfillerDecorator(new SimplePromiseFulfiller());
        return new Session(sessionId,communicator, new Queue(), promiseFulfiller, featureRepository);
    }
}

