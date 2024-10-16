package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

/**
 * Eventos relacionados con el transmisor WebSocket para eVolGreen OCPP.
 */
public interface RadioEvents {

    /**
     * Indica que la conexión ha sido establecida exitosamente.
     */
    void connected();

    /**
     * Mensaje entrante desde el nodo.
     *
     * @param message objeto mensaje recibido.
     */
    void receivedMessage(Object message);

    /**
     * Indica que se ha desconectado del nodo.
     */
    void disconnected();
}
