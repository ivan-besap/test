package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models;

/**
 * La clase {@code CallErrorMessage} representa un mensaje de error en el contexto
 * de las llamadas de protocolo OCPP. Captura detalles específicos como el código de error,
 * la descripción y una carga útil cruda que puede proporcionar información adicional sobre
 * el error ocurrido.
 * <p>
 * Esta clase es útil para gestionar situaciones de error en la comunicación entre estaciones
 * de carga y el servidor central.
 * </p>
 */
public class CallErrorMessage extends Message {

    // Código del error OCPP
    private String errorCode;

    // Descripción detallada del error
    private String errorDescription;

    // Carga útil cruda relacionada con el error
    private String rawPayload;

    /**
     * Devuelve el código de error que identifica la naturaleza del problema.
     *
     * @return el código de error como una cadena.
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Establece el código de error que identifica el problema. Se recomienda seguir
     * los códigos de error estándar del protocolo OCPP.
     *
     * @param errorCode el código de error a establecer.
     * @throws IllegalArgumentException si el código de error es nulo o vacío.
     */
    public void setErrorCode(String errorCode) {
        if (errorCode == null || errorCode.isEmpty()) {
            throw new IllegalArgumentException("El código de error no puede ser nulo o vacío");
        }
        this.errorCode = errorCode;
    }

    /**
     * Devuelve una descripción detallada del error.
     *
     * @return la descripción del error.
     */
    public String getErrorDescription() {
        return errorDescription;
    }

    /**
     * Establece una descripción más detallada del error ocurrido. Esta descripción puede
     * proporcionar información útil para identificar y corregir el problema.
     *
     * @param errorDescription la descripción del error a establecer.
     * @throws IllegalArgumentException si la descripción del error es nula o vacía.
     */
    public void setErrorDescription(String errorDescription) {
        if (errorDescription == null || errorDescription.isEmpty()) {
            throw new IllegalArgumentException("La descripción del error no puede ser nula o vacía");
        }
        this.errorDescription = errorDescription;
    }

    /**
     * Devuelve la carga útil cruda asociada al error. Esta carga puede incluir información adicional
     * que no está estructurada o en formato sin procesar.
     *
     * @return la carga útil cruda en formato de cadena.
     */
    public String getRawPayload() {
        return rawPayload;
    }

    /**
     * Establece la carga útil cruda relacionada con el error. Puede ser útil para depuración o análisis.
     *
     * @param rawPayload la carga útil cruda a establecer.
     */
    public void setRawPayload(String rawPayload) {
        this.rawPayload = rawPayload;
    }
}
