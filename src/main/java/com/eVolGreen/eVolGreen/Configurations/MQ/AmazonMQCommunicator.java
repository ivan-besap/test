package com.eVolGreen.eVolGreen.Configurations.MQ;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.AuthenticationException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.SessionInformation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.JSONCommunicator;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Radio;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.ServerEvents;
import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

import java.util.UUID;

/**
 * `AmazonMQCommunicator` es una entidad que gestiona la comunicación segura con Amazon MQ para enviar y recibir mensajes OCPP.
 * Extiende `JSONCommunicator` y utiliza Amazon MQ como intermediario, asegurando que los mensajes se entreguen correctamente
 * entre el sistema central y las estaciones de carga.
 *
 * Este comunicador maneja la configuración de Amazon MQ, la conexión, envío y recepción de mensajes,
 * y está diseñado para integrarse con WebSocket para reenviar los mensajes recibidos a través del sistema.
 */
public class AmazonMQCommunicator extends JSONCommunicator implements ServerEvents {

    private static final Logger logger = LoggerFactory.getLogger(AmazonMQCommunicator.class);
    private static final String WIRE_LEVEL_ENDPOINT = "ssl://b-e5acc38c-2617-4ce2-9ac0-009e5e858a2c-1.mq.us-west-2.amazonaws.com:61617";
    private static final String QUEUE_NAME = "ocppQueue";
    private final String username = "eVolGreen";
    private final String password = "eVolGreen123";
    private Connection connection;
    private Session jmsSession;
    private MessageProducer producer;
    private MessageConsumer consumer;

    /**
     * Constructor para inicializar AmazonMQCommunicator con una instancia de `Radio` para transmisión de mensajes.
     * Configura e inicia la conexión a Amazon MQ y prepara los componentes de mensajería.
     *
     * @param radio instancia de `Radio` para transmisión de mensajes.
     */
    public AmazonMQCommunicator(Radio radio) {
        super(radio);
        initializeConnection();
    }

    @Override
    public void receivedMessage(UUID sessionId, Object message) {

    }

    /**
     * Inicializa la conexión a Amazon MQ y configura el productor y consumidor de mensajes.
     * Intenta establecer una conexión segura con el endpoint y crea una sesión para el envío y recepción de mensajes.
     */
    private void initializeConnection() {
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(WIRE_LEVEL_ENDPOINT);
            connectionFactory.setUserName(username);
            connectionFactory.setPassword(password);
            connection = connectionFactory.createConnection();
            connection.start();
            jmsSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Crear productor y consumidor para la cola de Amazon MQ
            Destination destination = jmsSession.createQueue(QUEUE_NAME);
            producer = jmsSession.createProducer(destination);
            consumer = jmsSession.createConsumer(destination);
            consumer.setMessageListener(this::processMessage);
            logger.info("Conexión con Amazon MQ establecida y listener configurado.");
        } catch (JMSException e) {
            logger.error("Error al inicializar la conexión con Amazon MQ", e);
        }
    }

    /**
     * Método de callback para procesar mensajes entrantes desde Amazon MQ.
     * Este método se invoca automáticamente cada vez que se recibe un mensaje en la cola configurada.
     *
     * @param message El mensaje recibido desde Amazon MQ, que debe ser de tipo `TextMessage`.
     */
    private void processMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String text = ((TextMessage) message).getText();
                logger.info("Mensaje recibido de Amazon MQ: {}", text);
                // Enviar el mensaje a través de WebSocketHandler o manejarlo según lógica del sistema
            }
        } catch (JMSException e) {
            logger.error("Error al procesar el mensaje de Amazon MQ", e);
        }
    }

    /**
     * Envía un mensaje a la cola de Amazon MQ. El mensaje se envía como `TextMessage`.
     *
     * @param message El contenido del mensaje a enviar.
     */
    public void sendMessage(String message) {
        try {
            TextMessage textMessage = jmsSession.createTextMessage(message);
            producer.send(textMessage);
            logger.info("Mensaje enviado a Amazon MQ: {}", message);
        } catch (JMSException e) {
            logger.error("Error al enviar mensaje a Amazon MQ", e);
        }
    }

    /**
     * Cierra la conexión y libera los recursos asociados a Amazon MQ.
     * Este método debe llamarse al finalizar el uso de `AmazonMQCommunicator` para evitar fugas de recursos.
     */
    public void closeConnection() {
        try {
            if (producer != null) producer.close();
            if (consumer != null) consumer.close();
            if (jmsSession != null) jmsSession.close();
            if (connection != null) connection.close();
            logger.info("Conexión con Amazon MQ cerrada.");
        } catch (JMSException e) {
            logger.error("Error al cerrar la conexión con Amazon MQ", e);
        }
    }

    public void addSession(UUID newSessionId, WebSocketSession webSocketSession) {

    }

    public void removeSession(UUID sessionId) {

    }

    @Override
    public void authenticateSession(SessionInformation information, String username, String password) throws AuthenticationException {

    }

    @Override
    public void newSession(UUID sessionIndex, SessionInformation information) {

    }

    @Override
    public void lostSession(UUID sessionIndex) {

    }

    @Override
    public void handleError(String uniqueId, String errorCode, String errorDescription, Object payload) {

    }

    @Override
    public void handleConfirmation(String uniqueId, Confirmation confirmation) {

    }

    @Override
    public Confirmation handleRequest(Request request) {
        return null;
    }
}
