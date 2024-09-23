package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions;

import java.io.Serializable;

/**
 * Excepción personalizada que indica un problema relacionado con la autenticación.
 * <p>
 * Esta excepción incluye un código de error específico que proporciona más
 * detalles sobre la naturaleza del error de autenticación.
 */
public class AuthenticationException extends Exception implements Serializable {

    private static final long serialVersionUID = -2323276402779073385L;

    private final int errorcode;

    /**
     * Constructor de la excepción que acepta un código de error.
     *
     * @param errorcode el código de error específico relacionado con la autenticación.
     */
    public AuthenticationException(int errorcode) {
        this.errorcode = errorcode;
    }

    /**
     * Constructor que acepta un código de error y un mensaje descriptivo.
     *
     * @param errorcode el código de error específico.
     * @param message el mensaje descriptivo del error.
     */
    public AuthenticationException(int errorcode, String message) {
        super(message);
        this.errorcode = errorcode;
    }

    /**
     * Constructor que acepta un código de error y una excepción que causó este error.
     *
     * @param errorcode el código de error específico.
     * @param cause la causa subyacente de esta excepción.
     */
    public AuthenticationException(int errorcode, Throwable cause) {
        super(cause);
        this.errorcode = errorcode;
    }

    /**
     * Constructor que acepta un código de error, un mensaje y una causa subyacente.
     *
     * @param errorcode el código de error específico.
     * @param message el mensaje descriptivo del error.
     * @param cause la causa subyacente de esta excepción.
     */
    public AuthenticationException(int errorcode, String message, Throwable cause) {
        super(message, cause);
        this.errorcode = errorcode;
    }

    /**
     * Obtiene el código de error asociado con la excepción de autenticación.
     *
     * @return el código de error.
     */
    public int getErrorCode() {
        return this.errorcode;
    }
}
