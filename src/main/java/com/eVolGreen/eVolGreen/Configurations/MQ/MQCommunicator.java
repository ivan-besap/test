package com.eVolGreen.eVolGreen.Configurations.MQ;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Communicator;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.CommunicatorEvents;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.NotConnectedException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.CallErrorMessage;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Message;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Session;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Implementación de Communicator utilizando Amazon MQ para manejar la comunicación OCPP.
 * Gestiona la conexión, envío y recepción de mensajes a través de colas JMS.
 */
public class MQCommunicator extends Communicator {
    private static final Logger logger = LoggerFactory.getLogger(MQCommunicator.class);

    private Connection connection;
    private jakarta.jms.Session jmsSession;
    private MessageProducer producer;
    private MessageConsumer consumer;
    private final String brokerUrl;
    private final String username;
    private final String password;
    private final String requestQueueName;
    private final String responseQueueName;
    private CommunicatorEvents events;
    private final ConcurrentMap<String, Message> pendingMessages;
    private final ObjectMapper objectMapper;
    private final Session protocolSession;
    private boolean isConnectionActive = false; // Estado manual de la conexión

    /**
     * Constructor de MQCommunicator.
     *
     * @param brokerUrl         URL del broker de Amazon MQ.
     * @param username          Nombre de usuario para la conexión.
     * @param password          Contraseña para la conexión.
     * @param requestQueueName  Nombre de la cola de solicitudes.
     * @param responseQueueName Nombre de la cola de respuestas.
     * @param protocolSession   Sesión OCPP para gestionar mensajes.
     */
    public MQCommunicator(String brokerUrl, String username, String password,
                          String requestQueueName, String responseQueueName, Session protocolSession) {
        this.brokerUrl = brokerUrl;
        this.username = username;
        this.password = password;
        this.requestQueueName = requestQueueName;
        this.responseQueueName = responseQueueName;
        this.pendingMessages = new ConcurrentHashMap<>();
        this.objectMapper = new ObjectMapper();
        this.protocolSession = protocolSession;
    }

    /**
     * Establece la conexión con Amazon MQ y configura el productor y consumidor de mensajes.
     *
     * @param uri    URI de conexión (no utilizado en esta implementación).
     * @param events Implementación de CommunicatorEvents para manejar eventos.
     */
    @Override
    public void connect(String uri, CommunicatorEvents events) {
        this.events = events;
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(username, password, brokerUrl);
            this.connection = factory.createConnection();
            this.connection.start();

            // Crea la sesión de JMS
            this.jmsSession = connection.createSession(false, jakarta.jms.Session.AUTO_ACKNOWLEDGE);

            // Crea las colas de solicitud y respuesta
            Queue requestQueue = jmsSession.createQueue(requestQueueName);
            Queue responseQueue = jmsSession.createQueue(responseQueueName);

            // Configura el productor y consumidor
            this.producer = jmsSession.createProducer(requestQueue);
            this.producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            this.consumer = jmsSession.createConsumer(responseQueue);
            this.consumer.setMessageListener(new MQMessageListener(events, pendingMessages));

            events.onConnected();
            logger.info("Conexión establecida con Amazon MQ");
        } catch (JMSException e) {
            logger.error("Error al conectar con Amazon MQ", e);
            events.onDisconnected();
        }
    }

    @Override
    public <T> T unpackPayload(Object payload, Class<T> type) throws Exception {
        return null;
    }

    @Override
    public Object packPayload(Object payload) {
        return null;
    }

    @Override
    protected Object makeCallResult(String uniqueId, String action, Object payload) {
        return null;
    }

    @Override
    protected Object makeCall(String uniqueId, String action, Object payload) {
        return null;
    }

    @Override
    protected Object makeCallError(String uniqueId, String action, String errorCode, String errorDescription) {
        return null;
    }

    /**
     * Envía una solicitud (Call) al punto de carga.
     *
     * @param uniqueId Identificador único de la solicitud.
     * @param action   Acción OCPP a ejecutar.
     * @param request  Objeto Request que contiene la solicitud.
     */
    @Override
    public void sendCall(String uniqueId, String action, Request request) {
        try {
            Message message = new Message();
            message.setId(uniqueId);
            message.setAction(action);
            message.setPayload(request);

            String serializedPayload = objectMapper.writeValueAsString(message.getPayload());
            TextMessage jmsMessage = jmsSession.createTextMessage(serializedPayload);
            jmsMessage.setJMSCorrelationID(uniqueId);
            jmsMessage.setStringProperty("action", action);

            producer.send(jmsMessage);
            pendingMessages.put(uniqueId, message);
            logger.debug("Solicitud enviada: {}", uniqueId);
        } catch (JMSException | JsonProcessingException e) {
            logger.error("Error al enviar la solicitud", e);
            events.onError(uniqueId, "NotConnected", "No se pudo enviar la solicitud debido a problemas de conexión", request);
        }
    }

    /**
     * Envía una confirmación (CallResult) en respuesta a una solicitud recibida.
     *
     * @param uniqueId     Identificador único de la solicitud original.
     * @param action       Acción OCPP correspondiente.
     * @param confirmation Objeto Confirmation que contiene la respuesta.
     */
    @Override
    public void sendCallResult(String uniqueId, String action, Confirmation confirmation) {
        try {
            String serializedPayload = objectMapper.writeValueAsString(confirmation);
            TextMessage jmsMessage = jmsSession.createTextMessage(serializedPayload);
            jmsMessage.setJMSCorrelationID(uniqueId);
            jmsMessage.setStringProperty("action", action);

            producer.send(jmsMessage);
            logger.debug("Confirmación enviada: {}", uniqueId);
        } catch (JMSException | JsonProcessingException e) {
            logger.error("Error al enviar la confirmación", e);
            events.onError(uniqueId, "NotConnected", "No se pudo enviar la confirmación debido a problemas de conexión", confirmation);
        }
    }

    /**
     * Envía un mensaje de error (CallError) en respuesta a una solicitud recibida.
     *
     * @param uniqueId         Identificador único de la solicitud original.
     * @param action           Acción OCPP correspondiente.
     * @param errorCode        Código de error.
     * @param errorDescription Descripción del error.
     */
    @Override
    public void sendCallError(String uniqueId, String action, String errorCode, String errorDescription) {
        try {
            CallErrorMessage errorMessage = new CallErrorMessage();
            errorMessage.setId(uniqueId);
            errorMessage.setAction(action);
            errorMessage.setErrorCode(errorCode);
            errorMessage.setErrorDescription(errorDescription);

            String serializedPayload = objectMapper.writeValueAsString(errorMessage);
            TextMessage jmsMessage = jmsSession.createTextMessage(serializedPayload);
            jmsMessage.setJMSCorrelationID(uniqueId);
            jmsMessage.setStringProperty("action", action);
            jmsMessage.setStringProperty("errorCode", errorCode);

            producer.send(jmsMessage);
            logger.debug("Mensaje de error enviado: {}", uniqueId);
        } catch (JMSException | JsonProcessingException e) {
            logger.error("Error al enviar el mensaje de error", e);
        }
    }

    /**
     * Verifica si la conexión con Amazon MQ está cerrada.
     *
     * @return true si la conexión está cerrada, false en caso contrario.
     */
    @Override
    public boolean isClosed() {

       return !isConnectionActive || connection == null || jmsSession == null;

    }

    @Override
    public Message parse(Object message) {
        return null;
    }

    /**
     * Cierra la conexión con Amazon MQ y libera los recursos asociados.
     */
    @Override
    public void disconnect() {
        try {
            if (consumer != null) consumer.close();
            if (producer != null) producer.close();
            if (jmsSession != null) jmsSession.close();
            if (connection != null) connection.close();
            events.onDisconnected();
            logger.info("Conexión con Amazon MQ cerrada");
        } catch (JMSException e) {
            logger.error("Error al desconectar de Amazon MQ", e);
        }
    }
}
