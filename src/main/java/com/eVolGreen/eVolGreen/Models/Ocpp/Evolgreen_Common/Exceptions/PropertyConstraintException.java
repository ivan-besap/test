package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions;

/**
 * Excepción utilizada cuando falla la validación de un campo o propiedad.
 * <p>
 * Esta excepción se lanza cuando un valor de campo no cumple con las restricciones impuestas por una validación.
 * Proporciona detalles sobre el valor actual del campo y el motivo del fallo en la validación.
 * </p>
 */
public class PropertyConstraintException extends IllegalArgumentException {

    private static final String EXCEPTION_MESSAGE_TEMPLATE =
            "Validation failed: [%s]. Current Value: [%s]";

    /**
     * Constructor que crea una excepción con el valor de campo actual y el mensaje de error.
     * <p>
     * Permite capturar el valor que falló en la validación y un mensaje detallado que describe el problema.
     * </p>
     *
     * @param currentFieldValue El valor actual del campo que no pasó la validación.
     * @param errorMessage      Un mensaje detallado que describe el fallo en la validación.
     */
    public PropertyConstraintException(Object currentFieldValue, String errorMessage) {
        super(createValidationMessage(currentFieldValue, errorMessage));
    }

    /**
     * Crea un mensaje de validación utilizando una plantilla predefinida.
     * <p>
     * Formatea el mensaje de error y el valor de campo en una estructura legible para proporcionar más claridad
     * sobre el fallo de la validación.
     * </p>
     *
     * @param fieldValue  El valor actual del campo.
     * @param errorMessage El mensaje de error que describe la restricción fallida.
     * @return Un mensaje formateado que contiene el valor del campo y la descripción del error.
     */
    private static String createValidationMessage(Object fieldValue, String errorMessage) {
        return String.format(EXCEPTION_MESSAGE_TEMPLATE, errorMessage, fieldValue);
    }
}
