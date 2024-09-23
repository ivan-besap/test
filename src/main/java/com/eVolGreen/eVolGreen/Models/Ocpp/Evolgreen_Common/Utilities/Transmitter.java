package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities;

/**
 * Esta interfaz define un transmisor que maneja conexiones por parte de un cliente.
 * Extiende la interfaz {@link Radio}, lo que significa que los transmisores
 * también deben poder manejar el envío y recepción de mensajes.
 */
public interface Transmitter extends Radio {

    /**
     * Establece una conexión con un nodo específico.
     * Este método debe gestionar el proceso de conexión a una dirección URI específica,
     * y registrar un manejador de eventos para gestionar eventos relacionados con la conexión.
     *
     * @param uri la URL y el puerto del nodo al cual se desea conectar.
     * @param events los eventos relacionados con la conexión que serán manejados por {@link RadioEvents}.
     */
    void connect(String uri, RadioEvents events);
}
