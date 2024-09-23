package com.eVolGreen.eVolGreen.Models.Ocpp;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.NotConnectedException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.OccurenceConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.UnsupportedFeatureException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.ServerCoreProfile;

import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.protocols.IProtocol;
import org.java_websocket.protocols.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

/**
 * Implementación del servidor JSON para gestionar WebSockets y conexiones WSS en OCPP 1.6.
 * Este servidor permite la interacción entre el sistema central y los puntos de carga mediante perfiles de características.
 */
public class JSONServer implements IServerAPI {

    private static final Logger logger = LoggerFactory.getLogger(JSONServer.class);

    public final Draft draftOcppOnly;
    private final WebSocketListener listener;
    private final Server server;
    private final FeatureRepository featureRepository;
    private JSONConfiguration jsonConfiguration;

    /**
     * Constructor que crea un servidor WebSocket compatible con OCPP 1.6.
     *
     * @param coreProfile   Perfil principal del servidor, requerido como mínimo.
     * @param configuration Configuración de red para el servidor JSON.
     */
    public JSONServer(ServerCoreProfile coreProfile, JSONConfiguration configuration) {
        featureRepository = new FeatureRepository();
        SessionFactory sessionFactory = new SessionFactory(featureRepository);

        ArrayList<IProtocol> protocols = new ArrayList<>();
        protocols.add(new Protocol("ocpp1.6"));
        protocols.add(new Protocol(""));

        draftOcppOnly = new Draft_6455(Collections.emptyList(), protocols);

        if (configuration.getParameter(JSONConfiguration.HTTP_HEALTH_CHECK_ENABLED, true)) {
            logger.info("JSONServer 1.6 con HttpHealthCheckDraft");
            this.listener = new WebSocketListener(sessionFactory, configuration, draftOcppOnly, new Draft_HttpHealthCheck());
        } else {
            this.listener = new WebSocketListener(sessionFactory, configuration, draftOcppOnly);
        }

        server = new Server(this.listener, new PromiseRepository());
        featureRepository.addFeatureProfile(coreProfile);
    }

    /**
     * Constructor que crea un servidor WebSocket simple utilizando la configuración por defecto.
     *
     * @param coreProfile Perfil principal del servidor.
     */
    public JSONServer(ServerCoreProfile coreProfile) {
        this(coreProfile, JSONConfiguration.get());
    }

    /**
     * Constructor que crea un servidor compatible con WSS.
     *
     * @param coreProfile       Perfil principal del servidor.
     * @param wssFactoryBuilder Creador de fábricas para aceptar conexiones WSS.
     * @param configuration     Configuración de red para el servidor JSON.
     */
    public JSONServer(ServerCoreProfile coreProfile, WssFactoryBuilder wssFactoryBuilder, JSONConfiguration configuration) {
        this(coreProfile, configuration);
        enableWSS(wssFactoryBuilder);
    }

    /**
     * Activa la capacidad de aceptar conexiones WSS.
     *
     * @param sslContext Contexto SSL para habilitar WSS.
     * @throws IOException Si el servidor ya está conectado o hay un problema con la configuración SSL.
     */
    public void enableWSS(SSLContext sslContext) throws IOException {
        WssFactoryBuilder builder = BaseWssFactoryBuilder.builder().sslContext(sslContext);
        enableWSS(builder);
    }

    /**
     * Activa la capacidad de aceptar conexiones WSS utilizando un {@link WssFactoryBuilder}.
     *
     * @param wssFactoryBuilder Creador de fábricas para WebSocket con soporte WSS.
     * @return Instancia de {@link JSONServer}.
     */
    public JSONServer enableWSS(WssFactoryBuilder wssFactoryBuilder) {
        wssFactoryBuilder.verify();
        listener.enableWSS(wssFactoryBuilder);
        return this;
    }

    @Override
    public void addFeatureProfile(Profile profile) {
        featureRepository.addFeatureProfile(profile);
    }

    @Override
    public boolean isSessionOpen(UUID session) {
        return server.isSessionOpen(session);
    }

    @Override
    public void closeSession(UUID session) {
        server.closeSession(session);
    }

    @Override
    public void open(String host, int port, ServerEvents serverEvents) {
        logger.info("Repositorio de características: {}", featureRepository);
        server.open(host, port, serverEvents);
    }

    @Override
    public void close() {
        server.close();
    }

    @Override
    public boolean isClosed() {
        return listener.isClosed();
    }

    @Override
    public CompletionStage<Confirmation> send(UUID session, Request request)
            throws OccurenceConstraintException, UnsupportedFeatureException, NotConnectedException {
        return server.send(session, request);
    }

    @Override
    public boolean asyncCompleteRequest(UUID sessionIndex, String uniqueId, Confirmation confirmation)
            throws NotConnectedException, UnsupportedFeatureException, OccurenceConstraintException {
        return server.asyncCompleteRequest(sessionIndex, uniqueId, confirmation);
    }
}
