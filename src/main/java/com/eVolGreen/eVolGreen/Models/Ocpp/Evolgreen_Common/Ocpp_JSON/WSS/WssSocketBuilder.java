package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Ocpp_JSON.WSS;

import java.io.IOException;
import java.net.Socket;
import java.net.URI;

/**
 * Interfaz para construir un socket SSL compatible con el esquema WSS en eVolGreen OCPP.
 */
public interface WssSocketBuilder {

    /**
     * Establece la URI para identificar el endpoint de la conexión.
     *
     * @param uri URI que identifica el endpoint de la conexión.
     * @return instancia de {@link WssSocketBuilder}
     */
    WssSocketBuilder uri(URI uri);

    /**
     * Construye un socket SSL compatible con el esquema WSS.
     *
     * @return socket SSL
     * @throws IOException si ocurre un error al crear el socket.
     */
    Socket build() throws IOException;

    /**
     * Verifica si todos los parámetros requeridos durante la creación del cliente están establecidos.
     * Este método permite que el cliente falle rápidamente si faltan parámetros requeridos, sin exponer
     * detalles de implementación.
     *
     * @throws IllegalStateException si la verificación falla.
     */
    void verify();
}
