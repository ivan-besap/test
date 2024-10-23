package com.eVolGreen.eVolGreen.Models.Ocpp;

import com.eVolGreen.eVolGreen.Configurations.MQ.AmazonMQCommunicator;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.NotConnectedException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.OccurenceConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.UnsupportedFeatureException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.Draft_HttpHealthCheck;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.JSONConfiguration;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.WSS.BaseWssFactoryBuilder;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.WSS.WssFactoryBuilder;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.WebSocketListener;
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
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

/**
 * {@code JSONServer} implementa el servidor WebSocket para OCPP 1.6, soportando WebSocket y WSS para comunicación segura.
 * Integra Amazon MQ para entornos distribuidos, gestionando la interacción entre el sistema central y puntos de carga
 * mediante perfiles de características.
 */
public class JSONServer implements IServerAPI {

    private static final Logger logger = LoggerFactory.getLogger(JSONServer.class);

    private final Draft draftOcppOnly;
    private final WebSocketListener listener;
    private final Server server;
    private final FeatureRepository featureRepository;
//    private final AmazonMQCommunicator amazonMQCommunicator;
//    private final String brokerId;
    private final Map<UUID, ISession> sessionStore;

    /**
     * Constructor del servidor WebSocket JSONServer para OCPP 1.6.
     *
     * @param coreProfile          Perfil principal del servidor que define los mensajes y operaciones soportadas.
     * @param configuration        Configuración de red para el servidor JSON.
//     * @param amazonMQCommunicator Comunicador de Amazon MQ para entornos distribuidos.
//     * @param brokerId             Identificador del broker de Amazon MQ.
     * @param sessionStore         Almacenamiento compartido de sesiones para gestionar conexiones activas.
     */
    public JSONServer(ServerCoreProfile coreProfile, JSONConfiguration configuration,
//                      AmazonMQCommunicator amazonMQCommunicator, String brokerId,
                      Map<UUID, ISession> sessionStore) {

        this.featureRepository = new FeatureRepository();
//        this.amazonMQCommunicator = amazonMQCommunicator;
//        this.brokerId = brokerId;
        this.sessionStore = sessionStore;

        this.draftOcppOnly = initializeDraft();
        this.listener = initializeListener(configuration);

        this.server = new Server(this.listener, new PromiseRepository());
        featureRepository.addFeatureProfile(coreProfile);
    }

    /**
     * Inicializa el Draft para OCPP 1.6 con protocolos definidos.
     *
     * @return Un objeto {@link Draft} configurado para OCPP.
     */
    private Draft initializeDraft() {
        ArrayList<IProtocol> protocols = new ArrayList<>();
        protocols.add(new Protocol("ocpp1.6"));
        protocols.add(new Protocol(""));

        return new Draft_6455(Collections.emptyList(), protocols);
    }

    /**
     * Inicializa el WebSocketListener con soporte para HealthCheck si está habilitado en la configuración.
     *
     * @param configuration La configuración del servidor JSON.
     * @return Un objeto {@link WebSocketListener} configurado.
     */
    private WebSocketListener initializeListener(JSONConfiguration configuration) {
        SessionFactory sessionFactory = new SessionFactory(featureRepository);

        if (configuration.getParameter(JSONConfiguration.HTTP_HEALTH_CHECK_ENABLED, true)) {
            logger.info("Initializing JSONServer 1.6 with HttpHealthCheckDraft");
            return new WebSocketListener(sessionFactory, configuration, draftOcppOnly, new Draft_HttpHealthCheck());
        }
        return new WebSocketListener(sessionFactory, configuration, draftOcppOnly);
    }

    /**
     * Activa la capacidad de WSS con el contexto SSL proporcionado.
     *
     * @param sslContext Contexto SSL para WSS.
     * @throws IOException Si el servidor ya está conectado o hay un problema con la configuración SSL.
     */
    public void enableWSS(SSLContext sslContext) throws IOException {
        WssFactoryBuilder builder = BaseWssFactoryBuilder.builder().sslContext(sslContext);
        enableWSS(builder);
    }

    /**
     * Activa WSS utilizando un {@link WssFactoryBuilder}.
     *
     * @param wssFactoryBuilder Constructor de fábrica para WebSocket con soporte de WSS.
     * @return La instancia de {@link JSONServer} para encadenamiento.
     */
    public JSONServer enableWSS(WssFactoryBuilder wssFactoryBuilder) {
        wssFactoryBuilder.verify();
        listener.enableWSS(wssFactoryBuilder);
        return this;
    }

    /**
     * Envía un mensaje a Amazon MQ utilizando el comunicador.
     *
     * @param messageContent El contenido del mensaje a enviar.
     */
    public void sendMessageToMQ(String messageContent) {
//        amazonMQCommunicator.sendMessageToQueue(brokerId, messageContent);
        logger.info("Mensaje enviado a Amazon MQ: {}", messageContent);
    }

    /**
     * Agrega un perfil de características al repositorio.
     *
     * @param profile El perfil de características a agregar.
     */
    @Override
    public void addFeatureProfile(Profile profile) {
        featureRepository.addFeatureProfile(profile);
    }

    /**
     * Verifica si una sesión está abierta para el UUID especificado.
     *
     * @param sessionUUID El UUID de la sesión.
     * @return {@code true} si la sesión está abierta, {@code false} en caso contrario.
     */
    @Override
    public boolean isSessionOpen(UUID sessionUUID) {
        return sessionStore.containsKey(sessionUUID);
    }

    /**
     * Cierra una sesión para el UUID especificado.
     *
     * @param sessionUUID El UUID de la sesión.
     */
    @Override
    public void closeSession(UUID sessionUUID) {
        ISession session = sessionStore.remove(sessionUUID);
        if (session != null) {
            session.close();
            logger.info("Sesión cerrada: {}", sessionUUID);
        } else {
            logger.warn("Intento de cerrar una sesión no encontrada: {}", sessionUUID);
        }
    }

    /**
     * Abre el servidor en una dirección y puerto especificados con eventos de gestión.
     *
     * @param host         Dirección del host.
     * @param port         Número de puerto.
     * @param serverEvents Eventos de gestión de la conexión del servidor.
     */
    @Override
    public void open(String host, int port, ServerEvents serverEvents) {
        logger.info("Repositorio de características: {}", featureRepository);
        server.open(host, port, serverEvents);
    }

    /**
     * Cierra el servidor.
     */
    @Override
    public void close() {
        server.close();
    }

    /**
     * Verifica si el servidor está cerrado.
     *
     * @return {@code true} si el servidor está cerrado, de lo contrario, {@code false}.
     */
    @Override
    public boolean isClosed() {
        return listener.isClosed();
    }

    /**
     * Envía una solicitud y devuelve un {@link CompletionStage} de la confirmación.
     *
     * @param session  El UUID de la sesión.
     * @param request  El objeto de solicitud a enviar.
     * @return Un {@link CompletionStage} para la respuesta de confirmación.
     * @throws OccurenceConstraintException Si hay una violación de restricciones.
     * @throws UnsupportedFeatureException  Si la característica no es compatible.
     * @throws NotConnectedException        Si la sesión no está conectada.
     */
    @Override
    public CompletionStage<Confirmation> send(UUID session, Request request)
            throws OccurenceConstraintException, UnsupportedFeatureException, NotConnectedException {
        return server.send(session, request);
    }

    /**
     * Completa una solicitud asincrónica.
     *
     * @param sessionIndex El índice de la sesión UUID.
     * @param uniqueId     El identificador único para la solicitud.
     * @param confirmation La confirmación para completar la solicitud.
     * @return {@code true} si la solicitud se completó correctamente, {@code false} en caso contrario.
     * @throws NotConnectedException        Si la sesión no está conectada.
     * @throws UnsupportedFeatureException  Si la característica no es compatible.
     * @throws OccurenceConstraintException Si hay una violación de restricciones.
     */
    @Override
    public boolean asyncCompleteRequest(UUID sessionIndex, String uniqueId, Confirmation confirmation)
            throws NotConnectedException, UnsupportedFeatureException, OccurenceConstraintException {
        return server.asyncCompleteRequest(sessionIndex, uniqueId, confirmation);
    }

    /**
     * Registra una nueva sesión en el almacenamiento utilizando su ID.
     *
     * @param sessionId El UUID de la sesión.
     * @param session   La sesión a registrar.
     */
    public void registerSession(UUID sessionId, ISession session) {
        sessionStore.put(sessionId, session);
        logger.info("Sesión registrada con ID: {}", sessionId);
    }

    /**
     * Obtiene una sesión del almacenamiento utilizando su ID.
     *
     * @param sessionId El UUID de la sesión.
     * @return La sesión correspondiente o {@code null} si no se encuentra.
     */
    public ISession getSession(UUID sessionId) {
        return sessionStore.get(sessionId);
    }
}
