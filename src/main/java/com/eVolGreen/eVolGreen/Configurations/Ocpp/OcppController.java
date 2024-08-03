package com.eVolGreen.eVolGreen.Configurations.Ocpp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OcppController {

    private final OcppHandler ocppHandler;

    @Autowired
    public OcppController(OcppHandler ocppHandler) {
        this.ocppHandler = ocppHandler;
    }

    @GetMapping("/sendBootNotification")
    public ResponseEntity<String> sendBootNotification() {
        ocppHandler.sendBootNotification();
        return ResponseEntity.ok("BootNotification sent");
    }
}
