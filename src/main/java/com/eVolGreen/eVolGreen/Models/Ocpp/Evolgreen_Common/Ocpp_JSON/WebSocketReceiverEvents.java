package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON;

/**
 * Interfaz que define los eventos para manejar las conexiones WebSocket en la aplicación eVolGreen.
 * Estos eventos permiten gestionar el estado de la conexión y enviar mensajes a través de WebSocket.
 */
public interface WebSocketReceiverEvents {

    /**
     * Verifica si la conexión está cerrada.
     *
     * @return true si la conexión está cerrada (ya sea que nunca estuvo conectada o fue desconectada), false en caso contrario.
     */
    boolean isClosed();

    /**
     * Cierra la conexión WebSocket.
     */
    void close();

    /**
     * Envía un mensaje a través del WebSocket.
     *
     * @param message El mensaje que se enviará.
     */
    void relay(String message);
}
