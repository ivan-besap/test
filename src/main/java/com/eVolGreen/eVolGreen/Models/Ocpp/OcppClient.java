package com.eVolGreen.eVolGreen.Models.Ocpp;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * Clase que extiende WebSocketClient y se encarga de gestionar la conexión WebSocket
 * con el servidor OCPP, así como el envío y recepción de mensajes.
 */
@Component
public class OcppClient extends WebSocketClient {

    /**
     * Constructor que inicializa la conexión WebSocket con el servidor.
     *
     * @param serverUri URI del servidor OCPP.
     */
    public OcppClient(URI serverUri) {
        super(serverUri);
    }

    /**
     * Método llamado al establecer la conexión con el servidor.
     *
     * @param handshakedata Información del handshake recibido del servidor.
     */
    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Conectado al servidor OCPP.");
    }

    /**
     * Método llamado cuando se recibe un mensaje del servidor WebSocket.
     *
     * @param message Mensaje recibido del servidor.
     */
    @Override
    public void onMessage(String message) {
        System.out.println("Mensaje recibido del servidor: " + message);
        // Aquí se puede agregar lógica para procesar los mensajes recibidos
    }

    /**
     * Método llamado cuando se cierra la conexión con el servidor.
     *
     * @param code Código de cierre de la conexión.
     * @param reason Razón por la que se cerró la conexión.
     * @param remote true si la conexión fue cerrada de forma remota, false si fue cerrada localmente.
     */
    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Desconectado del servidor OCPP. Código: " + code + ", Razón: " + reason);
    }

    /**
     * Método llamado cuando ocurre un error durante la conexión WebSocket.
     *
     * @param ex Excepción que describe el error.
     */
    @Override
    public void onError(Exception ex) {
        System.err.println("Error en la conexión WebSocket: " + ex.getMessage());
        ex.printStackTrace();
    }
}
