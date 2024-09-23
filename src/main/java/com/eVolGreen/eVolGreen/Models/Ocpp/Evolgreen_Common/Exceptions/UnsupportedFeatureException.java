package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions;

/**
 * Excepción utilizada para indicar que una característica no está soportada.
 * Esta clase extiende la clase {@link Exception} para proporcionar un mecanismo
 * de manejo de errores específico cuando una característica solicitada no es
 * compatible con la implementación actual.
 */
public class UnsupportedFeatureException extends Exception {

    private static final long serialVersionUID = 9189571272082918907L;

    /**
     * Constructor por defecto. Crea una instancia de {@link UnsupportedFeatureException}
     * sin un mensaje detallado.
     */
    public UnsupportedFeatureException() {
        super();
    }

    /**
     * Constructor que acepta un mensaje detallado. Este mensaje puede ser utilizado
     * para proporcionar una descripción más específica del error cuando una
     * característica no está soportada.
     *
     * @param message El mensaje detallado que describe la razón de la excepción.
     */
    public UnsupportedFeatureException(String message) {
        super(message);
    }
}
