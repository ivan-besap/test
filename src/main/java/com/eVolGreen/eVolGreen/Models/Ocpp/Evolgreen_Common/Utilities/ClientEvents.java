package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities;

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
}
