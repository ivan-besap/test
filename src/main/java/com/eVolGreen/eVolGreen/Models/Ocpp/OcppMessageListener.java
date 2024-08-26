package com.eVolGreen.eVolGreen.Models.Ocpp;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class OcppMessageListener implements MessageListener {

    @Override
//    @JmsListener(destination = "ocpp_queue")
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String text = ((TextMessage) message).getText();
                System.out.println("Received message from Amazon MQ: " + text);
                // Procesar el mensaje aquí
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
