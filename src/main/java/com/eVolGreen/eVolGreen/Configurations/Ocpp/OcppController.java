package com.eVolGreen.eVolGreen.Configurations.Ocpp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OcppController {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private OcppHandler ocppHandler;

    @Autowired
    public OcppController(OcppHandler ocppHandler) {
        this.ocppHandler = ocppHandler;
    }

    @GetMapping("/sendBootNotification")
    public ResponseEntity<String> sendBootNotification() {
        try {
            ocppHandler.sendBootNotification();
            System.out.println("BootNotification enviado exitosamente");
            return ResponseEntity.ok("BootNotification enviado");
        } catch (Exception e) {
            System.err.println("Error al enviar BootNotification: " + e.getMessage());
            return ResponseEntity.status(500).body("Error al enviar BootNotification");
        }
    }

    @GetMapping("/sendHeartbeat")
    public ResponseEntity<String> sendHeartbeat() {
        try {
            ocppHandler.sendHeartbeat();
            System.out.println("Heartbeat enviado exitosamente");
            return ResponseEntity.ok("Heartbeat enviado");
        } catch (Exception e) {
            System.err.println("Error al enviar Heartbeat: " + e.getMessage());
            return ResponseEntity.status(500).body("Error al enviar Heartbeat");
        }
    }

    @GetMapping("/send")
    public String sendMessage() {
        try {
            jmsTemplate.convertAndSend("testQueue", "¡Hola desde ActiveMQ!");
            System.out.println("Mensaje enviado a testQueue exitosamente");
            return "¡Mensaje enviado!";
        } catch (Exception e) {
            System.err.println("Error al enviar mensaje a testQueue: " + e.getMessage());
            return "Error al enviar mensaje";
        }
    }
}
