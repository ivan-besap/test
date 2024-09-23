package com.eVolGreen.eVolGreen.Models.Ocpp;

import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.BootNotificationRequest;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * Clase encargada de inicializar el cliente OCPP y gestionar la conexión WebSocket con el servidor.
 * Esta clase actúa como el punto de entrada para enviar solicitudes OCPP como BootNotification.
 */
@Component
public class OcppClientApp {

    /**
     * Método principal para ejecutar la aplicación OCPP.
     * Establece la conexión con el servidor OCPP y envía una solicitud BootNotification.
     *
     * @param args Argumentos de línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        try {
            // URI del servidor WebSocket OCPP
            URI serverUri = new URI("ws://localhost:8080/ocpp");

            // Inicializa el cliente OCPP
            OcppClient client = new OcppClient(serverUri);

            // Establece la conexión con el servidor WebSocket
            client.connectBlocking();

            // Crea y envía una solicitud BootNotification al servidor
            BootNotificationRequest bootNotificationRequest = new BootNotificationRequest();
            bootNotificationRequest.setChargePointModel("Model S");
            bootNotificationRequest.setChargePointVendor("Tesla");

            // Envía la solicitud como un mensaje al servidor WebSocket
            client.send(bootNotificationRequest.toString());

            System.out.println("BootNotificationRequest enviado al servidor.");

            // Mantener la conexión abierta para seguir recibiendo mensajes
            // (Este puede cerrarse de forma manual según la lógica del sistema)

        } catch (Exception e) {
            System.err.println("Error al intentar conectar con el servidor OCPP: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
