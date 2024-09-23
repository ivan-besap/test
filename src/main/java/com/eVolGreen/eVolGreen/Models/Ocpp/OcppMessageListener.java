package com.eVolGreen.eVolGreen.Models.Ocpp;

import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Clase que implementa un listener para recibir y procesar mensajes desde Amazon MQ (ActiveMQ).
 * Este listener escucha la cola configurada y procesa los mensajes recibidos.
 */
@Component
public class OcppMessageListener implements MessageListener {

    /**
     * Este método es llamado automáticamente cuando un mensaje llega a la cola configurada.
     * Procesa mensajes de tipo {@link TextMessage}.
     *
     * @param message El mensaje recibido de la cola.
     */
    @Override
    @JmsListener(destination = "ocpp_queue")
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String text = ((TextMessage) message).getText();
                System.out.println("Mensaje recibido desde Amazon MQ: " + text);
                // Aquí puedes procesar el mensaje recibido según la lógica de negocio.
            } catch (Exception e) {
                System.err.println("Error al procesar el mensaje de Amazon MQ: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.err.println("El mensaje recibido no es de tipo TextMessage.");
        }
    }

    /**
     * Procesa el mensaje OCPP recibido. Este método puede ser extendido para manejar
     * diferentes tipos de mensajes OCPP.
     *
     * @param message El contenido del mensaje recibido.
     */
    private void processOcppMessage(String message) {
        // Lógica para procesar los mensajes OCPP
        System.out.println("Procesando mensaje OCPP: " + message);
        // Dependiendo del tipo de mensaje, se puede invocar al handler correspondiente
    }
}
