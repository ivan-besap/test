package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

/**
 * La clase {@code CallResultMessage} extiende {@link Message} y representa
 * una respuesta exitosa de una llamada OCPP (Open Charge Point Protocol) desde el servidor o la estación de carga.
 * <p>
 * Esta clase es parte del flujo de comunicación del protocolo OCPP, encapsulando el resultado de una
 * solicitud previamente enviada en una llamada {@link CallMessage}.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     CallResultMessage callResult = new CallResultMessage();
 *     // Procesar el resultado de la llamada según sea necesario
 * </pre>
 */
public class CallResultMessage extends Message {

    /**
     * Constructor por defecto para crear una instancia de {@code CallResultMessage}.
     * Este objeto encapsula el resultado de una llamada exitosa en el protocolo OCPP.
     */
    public CallResultMessage() {
        super();
    }


}
