package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletionStage;
import javax.net.ssl.SSLContext;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.NotConnectedException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.OccurenceConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.UnsupportedFeatureException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.Draft_HttpHealthCheck;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.JSONConfiguration;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.WSS.BaseWssFactoryBuilder;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.WSS.WssFactoryBuilder;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.PromiseRepository;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.ProtocolVersion;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Server;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.ServerEvents;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.protocols.IProtocol;
import org.java_websocket.protocols.Protocol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementación de un servidor JSON WebSocket que soporta múltiples versiones del protocolo OCPP.
 * Es compatible con conexiones seguras (WSS) y puede manejar protocolos OCPP 1.x y OCPP 2.x.
 */
public class MultiProtocolJSONServer implements IMultiProtocolServerAPI {

    private static final Logger logger = LoggerFactory.getLogger(MultiProtocolJSONServer.class);

    private final MultiProtocolWebSocketListener listener;
    private final Server server;
    private final MultiProtocolFeatureRepository featureRepository;

    /**
     * Constructor para un servidor WebSocket JSON, listo para WS.
     *
     * @param protocolVersions Lista de versiones del protocolo que el servidor aceptará.
     * @param configuration Configuración de red para el servidor JSON.
     */
    public MultiProtocolJSONServer(
            List<ProtocolVersion> protocolVersions, JSONConfiguration configuration) {
        featureRepository = new MultiProtocolFeatureRepository(protocolVersions);
        MultiProtocolSessionFactory sessionFactory = new MultiProtocolSessionFactory(featureRepository);

        List<IProtocol> protocols = new ArrayList<>(protocolVersions.size());
        for (ProtocolVersion protocolVersion : protocolVersions) {
            protocols.add(new Protocol(protocolVersion.getSubProtocolName()));
        }
        Draft draft = new Draft_6455(Collections.emptyList(), protocols);

        if (configuration.getParameter(JSONConfiguration.HTTP_HEALTH_CHECK_ENABLED, true)) {
            logger.info("Inicializando JSONServer con HttpHealthCheckDraft");
            listener =
                    new MultiProtocolWebSocketListener(
                            sessionFactory, configuration, draft, new Draft_HttpHealthCheck());
        } else {
            listener = new MultiProtocolWebSocketListener(sessionFactory, configuration, draft);
        }
        server = new Server(listener, new PromiseRepository());
    }

    /**
     * Constructor para un servidor WebSocket JSON, listo para WS.
     *
     * @param protocolVersions Lista de versiones del protocolo que el servidor aceptará.
     */
    public MultiProtocolJSONServer(List<ProtocolVersion> protocolVersions) {
        this(protocolVersions, JSONConfiguration.get());
    }

    /**
     * Constructor para un servidor WebSocket JSON, listo para WSS.
     *
     * @param protocolVersions Lista de versiones del protocolo que el servidor aceptará.
     * @param wssFactoryBuilder Builder para crear soporte para WSS.
     * @param configuration Configuración de red para el servidor JSON.
     */
    public MultiProtocolJSONServer(
            List<ProtocolVersion> protocolVersions,
            WssFactoryBuilder wssFactoryBuilder,
            JSONConfiguration configuration) {
        this(protocolVersions, configuration);
        enableWSS(wssFactoryBuilder);
    }

    /**
     * Constructor para un servidor WebSocket JSON, listo para WSS.
     *
     * @param protocolVersions Lista de versiones del protocolo que el servidor aceptará.
     * @param wssFactoryBuilder Builder para crear soporte para WSS.
     */
    public MultiProtocolJSONServer(
            List<ProtocolVersion> protocolVersions, WssFactoryBuilder wssFactoryBuilder) {
        this(protocolVersions, wssFactoryBuilder, JSONConfiguration.get());
    }

    /**
     * Habilita conexiones seguras WSS utilizando un contexto SSL.
     *
     * @param sslContext Contexto SSL para configurar la fábrica de sockets.
     * @throws IOException Si ocurre un error al inicializar la fábrica de WSS.
     */
    public void enableWSS(SSLContext sslContext) throws IOException {
        WssFactoryBuilder builder = BaseWssFactoryBuilder.builder().sslContext(sslContext);
        enableWSS(builder);
    }

    /**
     * Habilita el servidor para aceptar conexiones WSS.
     *
     * @param wssFactoryBuilder Builder para crear la fábrica de WebSocketServer.
     * @return Instancia del servidor actual.
     * @throws IllegalStateException Si el servidor ya está conectado o el builder no está inicializado correctamente.
     */
    public MultiProtocolJSONServer enableWSS(WssFactoryBuilder wssFactoryBuilder) {
        wssFactoryBuilder.verify();
        listener.enableWSS(wssFactoryBuilder);
        return this;
    }

    @Override
    public void addFeatureProfile(Profile profile) {
        addFeatureProfile(ProtocolVersion.OCPP1_6, profile);
    }

    @Override
    public void addFeatureProfile(ProtocolVersion protocolVersion, Profile profile) {
        featureRepository.addFeatureProfile(protocolVersion, profile);
    }

    @Override
    public void addFunction(ProtocolVersion protocolVersion, Function function) {
        featureRepository.addFeatureFunction(protocolVersion, function);
    }

    @Override
    public int getPort() {
        return listener.getPort();
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
        logger.info("Inicializando servidor con el repositorio de características: {}", featureRepository);
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
