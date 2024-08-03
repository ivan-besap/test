package com.eVolGreen.eVolGreen.Configurations.Ocpp;

import com.eVolGreen.eVolGreen.Configurations.Ocpp.Models.BootNotificationRequest;

import java.net.URI;

public class OcppClientApp {
    public static void main(String[] args) {
        try {
            OcppClient client = new OcppClient(new URI("ws://localhost:8080/ocpp"));
            client.connectBlocking();

            // Enviar un BootNotificationRequest
            BootNotificationRequest bootNotificationRequest = new BootNotificationRequest();
            bootNotificationRequest.setChargePointModel("Model S");
            bootNotificationRequest.setChargePointVendor("Tesla");
            client.send(bootNotificationRequest.toString());

            // Implementar otras funcionalidades según sea necesario

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
