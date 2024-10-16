package com.eVolGreen.eVolGreen.Configurations.MQ;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.CommunicatorEvents;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Message;
import jakarta.jms.JMSException;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentMap;

/**
 * Implementación de {@link MessageListener} para gestionar mensajes entrantes de Amazon MQ.
 * Procesa tanto solicitudes nuevas como respuestas a solicitudes previas,
 * y maneja errores si se detecta un código de error en el mensaje.
 */
public class MQMessageListener implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(MQMessageListener.class);

    private final CommunicatorEvents events;
    private final ConcurrentMap<String, Message> pendingMessages;

    /**
     * Constructor para inicializar los eventos de comunicación y el mapa de mensajes pendientes.
     *
     * @param events           Interface para manejar eventos de comunicación.
     * @param pendingMessages  Mapa concurrente de mensajes pendientes de respuesta.
     */
    public MQMessageListener(CommunicatorEvents events, ConcurrentMap<String, Message> pendingMessages) {
        this.events = events;
        this.pendingMessages = pendingMessages;
    }

    /**
     * Método que se ejecuta al recibir un mensaje de Amazon MQ.
     *
     * @param message El mensaje entrante de tipo {@link TextMessage}.
     */
    @Override
    public void onMessage(jakarta.jms.Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String payload = textMessage.getText();
                String uniqueId = textMessage.getJMSCorrelationID();
                String action = textMessage.getStringProperty("action");
                String errorCode = textMessage.getStringProperty("errorCode");

                if (errorCode != null) {
                    // Mensaje de error recibido
                    events.onError(uniqueId, errorCode, payload, null);
                    logger.debug("Mensaje de error procesado: {} - Código de error: {}", uniqueId, errorCode);
                } else if (pendingMessages.containsKey(uniqueId)) {
                    // Respuesta a una solicitud enviada
                    events.onCallResult(uniqueId, action, payload);
                    pendingMessages.remove(uniqueId);
                    logger.debug("Respuesta a solicitud procesada: {}", uniqueId);
                } else {
                    // Nueva solicitud entrante
                    events.onCall(uniqueId, action, payload);
                    logger.debug("Nueva solicitud procesada: {}", uniqueId);
                }
            } else {
                logger.warn("Mensaje recibido no es de tipo TextMessage: {}", message.getClass().getName());
            }
        } catch (JMSException e) {
            logger.error("Error al procesar el mensaje entrante", e);
        }
    }
}
