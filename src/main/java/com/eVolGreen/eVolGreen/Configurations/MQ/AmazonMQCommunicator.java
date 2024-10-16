package com.eVolGreen.eVolGreen.Configurations.MQ;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.mq.AmazonMQ;
import com.amazonaws.services.mq.AmazonMQClientBuilder;
import com.amazonaws.services.mq.model.ListBrokersRequest;
import com.amazonaws.services.mq.model.ListBrokersResult;
import com.amazonaws.services.mq.model.BrokerSummary;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Communicator;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.CommunicatorEvents;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.UnsupportedFeatureException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Message;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.SessionInformation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.ServerEvents;
import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.PingMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Gestiona la conexión y comunicación con Amazon MQ, enviando y recibiendo mensajes en una cola específica,
 * y manejando sesiones WebSocket activas. Implementa ServerEvents para comunicar eventos de sesión de vuelta a Amazon MQ.
 *
 * Esta clase extiende {@link Communicator} e integra Amazon MQ con el protocolo OCPP mediante la interfaz
 * {@link ServerEvents} para eventos de sesión y manejo de errores.
 */
@Component
public class AmazonMQCommunicator extends Communicator {

    private static final Logger logger = LoggerFactory.getLogger(AmazonMQCommunicator.class);
    private final ServerEvents serverEvents;
    private final AmazonMQ amazonMQClient;
    private final String brokerUrl;
    private final String brokerUser;
    private final String brokerPassword;
    private String brokerId;
    private final Map<UUID, WebSocketSession> activeSessions = new ConcurrentHashMap<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     * Constructor que inicializa el cliente de Amazon MQ y configura los parámetros de conexión.
     *
     * @param awsConfig      configuración de credenciales de AWS.
     * @param brokerUrl      URL del broker de Amazon MQ.
     * @param brokerUser     nombre de usuario para la conexión al broker.
     * @param brokerPassword contraseña para la conexión al broker.
     * @param serverEvents   instancia de ServerEvents para manejar eventos de sesión.
     */
    public AmazonMQCommunicator(AwsConfig awsConfig, String brokerUrl, String brokerUser,
                                String brokerPassword, ServerEvents serverEvents) {
        this.amazonMQClient = AmazonMQClientBuilder.standard()
                .withRegion("us-east-2")
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(awsConfig.getAccessKey(), awsConfig.getSecretKey())))
                .build();
        this.brokerUrl = brokerUrl;
        this.brokerUser = brokerUser;
        this.brokerPassword = brokerPassword;
        this.serverEvents = serverEvents;
    }

    /**
     * Recupera el ID del broker de Amazon MQ.
     *
     * @return El ID del primer broker disponible, o {@code null} si no se encuentra ninguno.
     */
    private String retrieveBrokerId() {
        ListBrokersRequest request = new ListBrokersRequest();
        try {
            ListBrokersResult result = amazonMQClient.listBrokers(request);
            if (!result.getBrokerSummaries().isEmpty()) {
                BrokerSummary brokerSummary = result.getBrokerSummaries().get(0);
                String brokerId = brokerSummary.getBrokerId();
                logger.info("Broker ID recuperado: {}", brokerId);
                return brokerId;
            } else {
                logger.warn("No se encontraron brokers en la cuenta de Amazon MQ.");
                return null;
            }
        } catch (Exception e) {
            logger.error("Error al recuperar el brokerId: ", e);
            return null;
        }
    }

    /**
     * Envía un mensaje a una cola específica en Amazon MQ.
     *
     * @param message   El contenido del mensaje a enviar.
     * @param queueName El nombre de la cola de destino.
     * @return {@code true} si el mensaje fue enviado con éxito, {@code false} de lo contrario.
     */
    public boolean sendMessageToQueue(String message, String queueName) {
        if (brokerId == null && !initializeBrokerId()) {
            logger.error("No se pudo enviar el mensaje: no se encontró brokerId.");
            return false;
        }
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
            connectionFactory.setUserName(brokerUser);
            connectionFactory.setPassword(brokerPassword);

            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(queueName);

            MessageProducer producer = session.createProducer(destination);
            TextMessage textMessage = session.createTextMessage(message);
            producer.send(textMessage);

            producer.close();
            session.close();
            connection.close();

            logger.info("Mensaje enviado exitosamente a la cola: {}", queueName);
            return true;
        } catch (Exception e) {
            logger.error("Error al enviar mensaje a la cola {}: ", queueName, e);
            return false;
        }
    }

    /**
     * Inicializa brokerId si aún no está configurado.
     *
     * @return {@code true} si brokerId se inicializó con éxito, {@code false} de lo contrario.
     */
    private boolean initializeBrokerId() {
        this.brokerId = retrieveBrokerId();
        return this.brokerId != null;
    }

    /**
     * Cierra la conexión con el cliente de Amazon MQ.
     */
    public void closeConnection() {
        logger.info("Conexión con Amazon MQ cerrada exitosamente");
    }

    /**
     * Recupera el ID del broker configurado para Amazon MQ.
     *
     * @return El {@code brokerId} actual, o {@code null} si no se pudo recuperar.
     */
    public String getBrokerId() {
        if (this.brokerId == null) {
            this.brokerId = retrieveBrokerId();
        }
        return this.brokerId;
    }

    @Override
    public void connect(String uri, CommunicatorEvents events) {
        logger.info("Conectando a URI: {}", uri);
    }

    @Override
    public <T> T unpackPayload(Object payload, Class<T> type) throws Exception {
        if (type.isInstance(payload)) {
            return type.cast(payload);
        } else {
            throw new IllegalArgumentException("Tipo de payload no coincide para: " + type.getName());
        }
    }

    @Override
    public Object packPayload(Object payload) {
        return payload.toString();
    }

    @Override
    protected Object makeCallResult(String uniqueId, String action, Object payload) {
        return "Resultado:" + uniqueId + ":" + action + ":" + payload;
    }

    @Override
    protected Object makeCall(String uniqueId, String action, Object payload) {
        return action + ":" + payload;
    }

    @Override
    protected Object makeCallError(String uniqueId, String action, String errorCode, String errorDescription) {
        return "Error:" + uniqueId + ":" + errorCode + ":" + errorDescription;
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    protected Message parse(Object json) {
        return null; // Implementación pendiente
    }

    /**
     * Añade una sesión WebSocket activa al mapa de sesiones.
     *
     * @param sessionId ID único de la sesión.
     * @param session   Sesión WebSocket.
     */
    public void addSession(UUID sessionId, WebSocketSession session) {
        activeSessions.put(sessionId, session);
    }

    /**
     * Elimina una sesión WebSocket activa del mapa de sesiones.
     *
     * @param sessionId ID único de la sesión a eliminar.
     */
    public void removeSession(UUID sessionId) {
        activeSessions.remove(sessionId);
    }

    /**
     * Envía un mensaje de ping a todas las sesiones WebSocket activas. Programado para ejecutarse cada 30 segundos.
     */
    @Scheduled(fixedRate = 30000)
    public void sendPingToClients() {
        activeSessions.values().forEach(session -> {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new PingMessage());
                } catch (IOException e) {
                    logger.error("Error al enviar ping a la sesión: {}", session.getId(), e);
                    removeSession(UUID.fromString(session.getId()));
                }
            }
        });
    }

    // Delegación de eventos de sesión a Amazon MQ
    public void newSession(UUID sessionIndex, SessionInformation information) {
        logger.info("Nueva sesión iniciada: {}", sessionIndex);
        sendSessionEvent("Nueva sesión iniciada: " + sessionIndex);
        serverEvents.newSession(sessionIndex, information);
    }

    public void lostSession(UUID sessionIndex) {
        logger.warn("Sesión perdida: {}", sessionIndex);
        sendSessionEvent("Sesión perdida: " + sessionIndex);
        serverEvents.lostSession(sessionIndex);
    }

    public void handleError(String uniqueId, String errorCode, String errorDescription, Object payload) {
        logger.error("Error en la solicitud: {} - {}", errorCode, errorDescription);
        sendErrorEvent("Error en la solicitud: " + errorCode + " - " + errorDescription);
        serverEvents.handleError(uniqueId, errorCode, errorDescription, payload);
    }

    public void handleConfirmation(String uniqueId, Confirmation confirmation) {
        logger.info("Confirmación recibida: {}", confirmation);
        sendConfirmationEvent("Confirmación recibida: " + confirmation.toString());
        serverEvents.handleConfirmation(uniqueId, confirmation);
    }

    public Confirmation handleRequest(Request request) throws UnsupportedFeatureException {
        logger.debug("Solicitud recibida: {}", request);
        return serverEvents.handleRequest(request);
    }

    // Métodos de ayuda para enviar eventos a las colas de Amazon MQ
    void sendSessionEvent(String message) {
        sendMessageToQueue(message, "sessionEventsQueue");
    }

    void sendErrorEvent(String message) {
        sendMessageToQueue(message, "errorEventsQueue");
    }

    void sendConfirmationEvent(String message) {
        sendMessageToQueue(message, "confirmationEventsQueue");
    }
}
