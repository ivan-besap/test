package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities;

/**
 * Interface para gestionar las conexiones entrantes en un servidor.
 * <p>
 * Implementa la interfaz {@link Radio}, lo que permite al servidor manejar conexiones de red
 * y recibir mensajes desde el lado del cliente.
 * </p>
 */
public interface Receiver extends Radio {

    /**
     * Acepta una solicitud de conexión entrante.
     * <p>
     * Este método permite al servidor recibir y procesar eventos relacionados con la conexión,
     * como conexiones abiertas, cerradas o errores en la transmisión de datos.
     * </p>
     *
     * @param events instancia de {@link RadioEvents} para manejar los eventos relacionados con la conexión.
     */
    void accept(RadioEvents events);
}
