package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

/**
 * La clase {@code CallMessage} extiende {@link Message} y representa
 * una llamada OCPP (Open Charge Point Protocol) entre el servidor central y las estaciones de carga.
 * <p>
 * Esta clase es fundamental en la comunicación de solicitudes del protocolo OCPP.
 * Sirve como un contenedor base que puede ser extendido o detallado para diferentes tipos de llamadas.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     CallMessage callMessage = new CallMessage();
 *     // Enviar la llamada utilizando el mecanismo adecuado de comunicación
 * </pre>
 */
public class CallMessage extends Message {

    /**
     * Constructor por defecto para crear una instancia de {@code CallMessage}.
     * Esta clase puede ser extendida para definir diferentes tipos de llamadas dentro del protocolo OCPP.
     */
    public CallMessage() {
        super();
    }

    // Otros métodos específicos pueden ser agregados según la implementación OCPP requerida
}
