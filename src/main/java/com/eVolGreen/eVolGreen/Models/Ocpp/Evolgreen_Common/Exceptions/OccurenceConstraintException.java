package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions;

/**
 * Excepción lanzada cuando se intenta enviar una solicitud que no es válida debido a restricciones de ocurrencia.
 * <p>
 * Esta excepción se utiliza para indicar que una solicitud ha fallado porque no cumple con ciertas restricciones
 * definidas por el protocolo OCPP, como el número o tipo de mensajes permitidos en una secuencia específica.
 * </p>
 */
public class OccurenceConstraintException extends Exception {

    /**
     * Constructor por defecto para la excepción OccurenceConstraintException.
     * <p>
     * Crea una nueva instancia de la excepción sin un mensaje de error detallado.
     * </p>
     */
    public OccurenceConstraintException() {
        super();
    }

    /**
     * Constructor que acepta un mensaje personalizado para describir el error.
     * <p>
     * Permite especificar un mensaje que describa con más detalle la causa de la excepción.
     * </p>
     *
     * @param message El mensaje detallado que describe la causa de la excepción.
     */
    public OccurenceConstraintException(String message) {
        super(message);
    }
}
