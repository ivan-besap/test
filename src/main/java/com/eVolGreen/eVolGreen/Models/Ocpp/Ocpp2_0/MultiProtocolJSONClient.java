package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletionStage;
import javax.net.ssl.SSLContext;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.OccurenceConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.UnsupportedFeatureException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.JSONCommunicator;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.JSONConfiguration;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.WSS.BaseWssSocketBuilder;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.WSS.WssSocketBuilder;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.protocols.IProtocol;
import org.java_websocket.protocols.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;

/**
 * Implementación de un cliente JSON WebSocket compatible con múltiples versiones del protocolo OCPP.
 * Permite soporte para protocolos OCPP 1.x y OCPP 2.x, con capacidades de configuración avanzada
 * y compatibilidad con conexiones seguras WSS.
 */
public class MultiProtocolJSONClient implements IMultiProtocolClientAPI {

    private static final Logger logger = LoggerFactory.getLogger(MultiProtocolJSONClient.class);

    private final String identity;
    private final MultiProtocolFeatureRepository featureRepository;
    private final MultiProtocolWebSocketTransmitter transmitter;
    private final Client client;

    /**
     * Constructor principal para inicializar el cliente con múltiples versiones del protocolo.
     *
     * @param protocolVersions lista de versiones del protocolo soportadas.
     */
    public MultiProtocolJSONClient(List<ProtocolVersion> protocolVersions) {
        this(protocolVersions, null);
    }

    /**
     * Constructor para inicializar el cliente con una identidad opcional.
     *
     * @param protocolVersions lista de versiones del protocolo soportadas.
     * @param identity identidad del cliente, que será añadida al final de la URL.
     */
    public MultiProtocolJSONClient(List<ProtocolVersion> protocolVersions, String identity) {
        this(protocolVersions, identity, JSONConfiguration.get());
    }

    /**
     * Constructor avanzado con configuración de red.
     *
     * @param protocolVersions lista de versiones del protocolo soportadas.
     * @param identity identidad del cliente, que será añadida al final de la URL.
     * @param configuration configuración de red para el cliente JSON.
     */
    public MultiProtocolJSONClient(
            List<ProtocolVersion> protocolVersions, String identity, JSONConfiguration configuration) {
        this.identity = identity;
        featureRepository = new MultiProtocolFeatureRepository(protocolVersions);

        // Configuración de protocolos específicos según las versiones.
        List<IProtocol> inputProtocols = new ArrayList<>(protocolVersions.size());
        for (ProtocolVersion protocolVersion : protocolVersions) {
            inputProtocols.add(new Protocol(protocolVersion.getSubProtocolName()));
        }

        Draft draft = new Draft_6455(Collections.emptyList(), inputProtocols);
        transmitter = new MultiProtocolWebSocketTransmitter(featureRepository, configuration, draft);
        JSONCommunicator communicator = new JSONCommunicator(transmitter, false) {
            @Override
            public void receivedMessage(UUID sessionId, Object message) {

            }
        };
        ISessionFactory sessionFactory = new MultiProtocolSessionFactory(featureRepository);
        ISession session = sessionFactory.createSession(communicator);
        client = new Client(session, new PromiseRepository());
    }

    /**
     * Constructor con soporte para conexiones seguras (WSS).
     *
     * @param protocolVersions lista de versiones del protocolo soportadas.
     * @param identity identidad del cliente, que será añadida al final de la URL.
     * @param wssSocketBuilder builder para crear conexiones WSS.
     */
    public MultiProtocolJSONClient(
            List<ProtocolVersion> protocolVersions, String identity, WssSocketBuilder wssSocketBuilder) {
        this(protocolVersions, identity, wssSocketBuilder, JSONConfiguration.get());
    }

    /**
     * Constructor avanzado con soporte para WSS y configuración de red.
     *
     * @param protocolVersions lista de versiones del protocolo soportadas.
     * @param identity identidad del cliente, que será añadida al final de la URL.
     * @param wssSocketBuilder builder para crear conexiones WSS.
     * @param configuration configuración de red para el cliente JSON.
     */
    public MultiProtocolJSONClient(
            List<ProtocolVersion> protocolVersions,
            String identity,
            WssSocketBuilder wssSocketBuilder,
            JSONConfiguration configuration) {
        this(protocolVersions, identity, configuration);
        enableWSS(wssSocketBuilder);
    }

    /**
     * Habilita las conexiones seguras WSS utilizando un contexto SSL.
     *
     * @param sslContext el contexto SSL que proporciona la fábrica de sockets.
     * @throws IOException si ocurre un error al configurar el builder de WSS.
     */
    public void enableWSS(SSLContext sslContext) throws IOException {
        WssSocketBuilder wssSocketBuilder =
                BaseWssSocketBuilder.builder().sslSocketFactory(sslContext.getSocketFactory());
        enableWSS(wssSocketBuilder);
    }

    /**
     * Habilita las conexiones WSS utilizando un {@link WssSocketBuilder}.
     *
     * @param wssSocketBuilder builder para inicializar la conexión segura WSS.
     * @return la instancia actual del cliente.
     * @throws IllegalStateException si el cliente ya está conectado o el builder no está inicializado correctamente.
     */
    public MultiProtocolJSONClient enableWSS(WssSocketBuilder wssSocketBuilder) {
        wssSocketBuilder.verify();
        transmitter.enableWSS(wssSocketBuilder);
        return this;
    }

    /**
     * Permite reconfigurar ajustes de red mientras el cliente está conectado.
     * Por ejemplo, se puede cambiar el intervalo de pings sin desconectar.
     */
    public void reconfigure() {
        transmitter.configure();
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
    public void connect(String url, ClientEvents clientEvents) throws InterruptedException {
        logger.debug("Feature repository: {}", featureRepository);

        String identityUrl = (identity != null) ? String.format("%s/%s", url, identity) : url;
        client.connect(identityUrl, clientEvents);
    }

    @Override
    @Nullable
    public ProtocolVersion getProtocolVersion() {
        return featureRepository.getProtocolVersion();
    }

    @Override
    public CompletionStage<Confirmation> send(Request request)
            throws OccurenceConstraintException, UnsupportedFeatureException {
        return client.send(request);
    }

    @Override
    public boolean asyncCompleteRequest(String uniqueId, Confirmation confirmation)
            throws UnsupportedFeatureException, OccurenceConstraintException {
        return client.asyncCompleteRequest(uniqueId, confirmation);
    }

    @Override
    public void disconnect() {
        client.disconnect();
    }

    @Override
    public boolean isClosed() {
        return transmitter.isClosed();
    }

    @Override
    public UUID getSessionId() {
        return client.getSessionId();
    }
}
