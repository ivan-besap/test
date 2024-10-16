package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

/**
 * Interface para manejar eventos de conexión en el cliente eVolGreen.
 */
public interface ClientEvents {

    /**
     * Método invocado cuando la conexión es abierta exitosamente.
     */
    void connectionOpened();

    /**
     * Método invocado cuando la conexión es cerrada.
     */
    void connectionClosed();

    void connected();

    void disconnected();

    void handleError(String error, String description);
}
