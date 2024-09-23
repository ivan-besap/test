package com.eVolGreen.eVolGreen.Models.Ocpp;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.CompletionStage;
import javax.net.ssl.SSLContext;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.OccurenceConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.UnsupportedFeatureException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.ClientCoreProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.protocols.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementación del cliente para OCA OCPP versión 1.6 JSON Web Socket.
 */
public class JSONClient implements IClientAPI {

    private static final Logger logger = LoggerFactory.getLogger(JSONClient.class);

    public final Draft draftOcppOnly;
    private final WebSocketTransmitter transmitter;
    private final FeatureRepository featureRepository;
    private final Client client;
    private final String identity;

    /**
     * Constructor que utiliza el perfil core del cliente.
     *
     * @param coreProfile Implementación del perfil de características core.
     */
    public JSONClient(ClientCoreProfile coreProfile) {
        this(coreProfile, null);
    }

    /**
     * Constructor que crea la raíz compuesta de la aplicación para un cliente JSON.
     * El perfil de características core es requerido como mínimo.
     *
     * @param coreProfile Implementación del perfil de características core.
     * @param identity Si se establece, se añadirá la identidad a la URL.
     * @param configuration Configuración de red para un cliente JSON.
     */
    public JSONClient(
            ClientCoreProfile coreProfile, String identity, JSONConfiguration configuration) {
        this.identity = identity;
        draftOcppOnly =
                new Draft_6455(Collections.emptyList(), Collections.singletonList(new Protocol("ocpp1.6")));
        transmitter = new WebSocketTransmitter(configuration, draftOcppOnly);
        JSONCommunicator communicator = new JSONCommunicator(transmitter);
        featureRepository = new FeatureRepository();
        ISession session = new SessionFactory(featureRepository).createSession(communicator);
        client = new Client(session, new PromiseRepository());
        featureRepository.addFeatureProfile(coreProfile);
    }

    /**
     * Constructor que crea la raíz compuesta de la aplicación para un cliente JSON.
     * El perfil de características core es requerido como mínimo.
     *
     * @param coreProfile Implementación del perfil de características core.
     * @param identity Si se establece, se añadirá la identidad a la URL.
     */
    public JSONClient(ClientCoreProfile coreProfile, String identity) {
        this(coreProfile, identity, JSONConfiguration.get());
    }

    /**
     * Constructor que crea la raíz compuesta de la aplicación para un cliente JSON.
     * El perfil de características core es requerido como mínimo.
     *
     * @param coreProfile Implementación del perfil de características core.
     * @param identity Si se establece, se añadirá la identidad a la URL.
     * @param wssSocketBuilder Para construir {@link java.net.Socket} para soportar wss://.
     * @param configuration Configuración de red para un cliente JSON.
     */
    public JSONClient(
            ClientCoreProfile coreProfile,
            String identity,
            WssSocketBuilder wssSocketBuilder,
            JSONConfiguration configuration) {
        this(coreProfile, identity, configuration);
        enableWSS(wssSocketBuilder);
    }

    /**
     * Constructor que crea la raíz compuesta de la aplicación para un cliente JSON.
     * El perfil de características core es requerido como mínimo.
     *
     * @param coreProfile Implementación del perfil de características core.
     * @param identity Si se establece, se añadirá la identidad a la URL.
     * @param wssSocketBuilder Para construir {@link java.net.Socket} para soportar wss://.
     */
    public JSONClient(
            ClientCoreProfile coreProfile, String identity, WssSocketBuilder wssSocketBuilder) {
        this(coreProfile, identity, wssSocketBuilder, JSONConfiguration.get());
    }

    /**
     * Habilita la conexión WSS al punto final.
     * Este método es para asegurar la compatibilidad hacia atrás de la API expuesta.
     *
     * @param sslContext El contexto SSL a utilizar.
     * @throws IOException Si ocurre un error de E/S.
     */
    public void enableWSS(SSLContext sslContext) throws IOException {
        WssSocketBuilder wssSocketBuilder =
                BaseWssSocketBuilder.builder().sslSocketFactory(sslContext.getSocketFactory());
        enableWSS(wssSocketBuilder);
    }

    /**
     * Habilita la conexión WSS al punto final. El {@code wssSocketBuilder} debe ser inicializado
     * en ese paso (ya que los parámetros requeridos establecidos pueden variar dependiendo de la implementación,
     * se utiliza {@link WssSocketBuilder#verify()} para asegurar la inicialización).
     *
     * @param wssSocketBuilder Constructor para proporcionar socket SSL.
     * @throws IllegalStateException Si el cliente ya está conectado.
     * @throws IllegalStateException Si {@code wssSocketBuilder} no está inicializado correctamente.
     */
    public JSONClient enableWSS(WssSocketBuilder wssSocketBuilder) {
        wssSocketBuilder.verify();
        transmitter.enableWSS(wssSocketBuilder);
        return this;
    }

    /**
     * Aplica cambios de JSONConfiguration cuando ya está conectado.
     * Específicamente, el intervalo de ping de WebSocket puede cambiarse sin reconectar llamando a este método.
     */
    public void reconfigure() {
        transmitter.configure();
    }

    /**
     * Añade un perfil de características al repositorio de características.
     *
     * @param profile El perfil de características a añadir.
     */
    @Override
    public void addFeatureProfile(Profile profile) {
        featureRepository.addFeatureProfile(profile);
    }

    /**
     * Conecta al cliente a la URL especificada.
     *
     * @param url La URL a la que conectarse.
     * @param clientEvents Los eventos del cliente a manejar.
     */
    @Override
    public void connect(String url, ClientEvents clientEvents) {
        logger.debug("Feature repository: {}", featureRepository);

        String identityUrl = (identity != null) ? String.format("%s/%s", url, identity) : url;
        client.connect(identityUrl, clientEvents);
    }

    /**
     * Envía una solicitud al servidor.
     *
     * @param request La solicitud a enviar.
     * @return Una etapa de finalización que se completará con la confirmación.
     * @throws OccurenceConstraintException Si se viola una restricción de ocurrencia.
     * @throws UnsupportedFeatureException Si la característica no está soportada.
     */
    @Override
    public CompletionStage<Confirmation> send(Request request)
            throws OccurenceConstraintException, UnsupportedFeatureException {
        return client.send(request);
    }

    /**
     * Completa de forma asíncrona una solicitud pendiente.
     *
     * @param uniqueId El ID único de la solicitud.
     * @param confirmation La confirmación para completar la solicitud.
     * @return true si la solicitud pendiente se completó con éxito, false en caso contrario.
     * @throws UnsupportedFeatureException Si la característica no está soportada.
     * @throws OccurenceConstraintException Si se viola una restricción de ocurrencia.
     */
    @Override
    public boolean asyncCompleteRequest(String uniqueId, Confirmation confirmation) throws UnsupportedFeatureException, OccurenceConstraintException {
        return client.asyncCompleteRequest(uniqueId, confirmation);
    }

    /**
     * Desconecta al cliente del servidor.
     */
    @Override
    public void disconnect() {
        client.disconnect();
    }

    /**
     * Verifica si la conexión está cerrada.
     *
     * @return true si la conexión está cerrada, false en caso contrario.
     */
    @Override
    public boolean isClosed() {
        return transmitter.isClosed();
    }

    /**
     * Obtiene el ID de la sesión actual.
     *
     * @return El UUID de la sesión actual.
     */
    @Override
    public UUID getSessionId() {
        return client.getSessionId();
    }
}