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
        ocppHandler.sendBootNotification();
        return ResponseEntity.ok("BootNotification sent");
    }

    @GetMapping("/sendHeartbeat")
    public ResponseEntity<String> sendHeartbeat() {
        ocppHandler.sendHeartbeat();
        return ResponseEntity.ok("Heartbeat sent");
    }

    @GetMapping("/send")
    public String sendMessage() {
        jmsTemplate.convertAndSend("testQueue", "Hello from ActiveMQ!");
        return "Message sent!";
    }
}
