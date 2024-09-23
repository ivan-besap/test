package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions;

/**
 * Excepción que se devuelve a una solicitud saliente si se informa de un error desde el otro extremo.
 * <p>
 * Esta clase encapsula los detalles del error, incluyendo el código de error, la descripción
 * y el payload asociado.
 */
public class CallErrorException extends Exception {

    private String errorCode;
    private String errorDescription;
    private Object payload;

    /**
     * Constructor de la excepción.
     *
     * @param errorCode        código de error enviado desde el otro extremo.
     * @param errorDescription descripción del error.
     * @param payload          el payload crudo enviado desde el otro extremo.
     */
    public CallErrorException(String errorCode, String errorDescription, Object payload) {
        super(
                "errorCode='"
                        + errorCode
                        + '\''
                        + ", errorDescription='"
                        + errorDescription
                        + '\''
                        + ", payload="
                        + payload);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.payload = payload;
    }

    /**
     * Obtiene el código de error enviado por la otra parte.
     *
     * @return el código de error.
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Establece el código de error.
     *
     * @param errorCode el código de error a establecer.
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Obtiene la descripción del error.
     *
     * @return la descripción del error.
     */
    public String getErrorDescription() {
        return errorDescription;
    }

    /**
     * Establece la descripción del error.
     *
     * @param errorDescription la descripción del error a establecer.
     */
    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    /**
     * Obtiene el payload crudo enviado desde el otro extremo.
     *
     * @return el payload crudo.
     */
    public Object getPayload() {
        return payload;
    }

    /**
     * Establece el payload crudo.
     *
     * @param payload el payload crudo a establecer.
     */
    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
