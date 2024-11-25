package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.JSONClient;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.BootNotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientEventsImpl implements ClientEvents {

    private static final Logger log = LoggerFactory.getLogger(ClientEventsImpl.class);

    private final JSONClient jsonClient;

    @Autowired
    public ClientEventsImpl(JSONClient jsonClient) {
        this.jsonClient = jsonClient;
    }

    @Override
    public void connectionOpened() {
        log.info("Conexión establecida con éxito al CMS.");
        BootNotificationRequest request = new BootNotificationRequest("ChargePointVendor", "ChargePointModel");
        try {
            jsonClient.send(request).whenComplete((confirmation, throwable) -> {
                if (throwable != null) {
                    log.error("Error al enviar BootNotification", throwable);
                } else {
                    log.info("BootNotification enviado con éxito: {}", confirmation);
                }
            });
        } catch (Exception e) {
            log.error("Excepción al enviar BootNotification", e);
        }
    }

    @Override
    public void connectionClosed() {
        log.info("Conexión cerrada");
    }

    @Override
    public void connected() {
        log.info("Conectado");
    }

    @Override
    public void disconnected() {
        log.info("Desconectado");
    }

    @Override
    public void handleError(String error, String description) {
        log.error("Error en la conexión al CMS: {} - {}", error, description);
    }
}
