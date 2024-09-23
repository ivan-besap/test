package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions;

/**
 * Excepción que se lanza cuando se intenta enviar una solicitud, pero no se ha establecido una conexión.
 * <p>
 * Esta excepción se utiliza para indicar que el sistema no está conectado a un servidor o a otro dispositivo
 * remoto, por lo que la operación solicitada no puede realizarse.
 * </p>
 */
public class NotConnectedException extends Exception {

    /**
     * Constructor por defecto para la excepción NotConnectedException.
     * <p>
     * Crea una nueva instancia de la excepción sin un mensaje de error detallado.
     * </p>
     */
    public NotConnectedException() {
        super();
    }

    /**
     * Constructor que acepta un mensaje personalizado para describir el error.
     * <p>
     * Permite especificar un mensaje que describa con más detalle el motivo de la excepción.
     * </p>
     *
     * @param message El mensaje detallado que describe la causa de la excepción.
     */
    public NotConnectedException(String message) {
        super(message);
    }
}
