package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

//package com.eVolGreen.eVolGreen.Communication;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.NotConnectedException;

/**
 * Interfaz que contiene métodos comunes para la comunicación del servidor y cliente en eVolGreen.
 */
public interface Radio {

    /**
     * Desconecta un nodo.
     */
    void disconnect();

    /**
     * Envía un mensaje a un nodo.
     *
     * @param message mensaje a enviar.
     * @throws NotConnectedException si el mensaje no puede ser enviado debido a una falta de conexión.
     */
    void send(Object message) throws NotConnectedException;

    /**
     * Verifica si la conexión está cerrada o no se ha abierto.
     *
     * @return true si la conexión está cerrada o no se ha iniciado.
     */
    boolean isClosed();
}
