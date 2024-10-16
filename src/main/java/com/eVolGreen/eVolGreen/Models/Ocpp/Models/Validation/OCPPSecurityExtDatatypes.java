package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Validation;


import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation.IValidationRule;

/**
 * Clase que define las reglas de validación para varios tipos de datos en OCPP.
 * Estas reglas se aplican para asegurar que los datos enviados y recibidos cumplan con las restricciones de longitud y formato.
 *
 * <p>Esta clase incluye validaciones para cadenas con longitudes máximas, así como validaciones específicas para identificadores.</p>
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     {@code
 *     IValidationRule rule = OCPPSecurityExtDatatypes.string50();
 *     boolean isValid = rule.validate("Cadena de prueba");
 *     }
 * </pre>
 */
public class OCPPSecurityExtDatatypes {

    /**
     * Regla de validación para cadenas con una longitud máxima de 50 caracteres.
     *
     * @return {@link IValidationRule} que valida si la cadena tiene un máximo de 50 caracteres.
     */
    public static IValidationRule string50() {
        return new StringMaxLengthValidationRule(50);
    }

    /**
     * Regla de validación para cadenas con una longitud máxima de 20 caracteres.
     *
     * @return {@link IValidationRule} que valida si la cadena tiene un máximo de 20 caracteres.
     */
    public static IValidationRule string20() {
        return new StringMaxLengthValidationRule(20);
    }

    /**
     * Regla de validación para cadenas con una longitud máxima de 40 caracteres.
     *
     * @return {@link IValidationRule} que valida si la cadena tiene un máximo de 40 caracteres.
     */
    public static IValidationRule string40() {
        return new StringMaxLengthValidationRule(40);
    }

    /**
     * Regla de validación para cadenas con una longitud máxima de 512 caracteres.
     *
     * @return {@link IValidationRule} que valida si la cadena tiene un máximo de 512 caracteres.
     */
    public static IValidationRule string512() {
        return new StringMaxLengthValidationRule(512);
    }

    /**
     * Regla de validación para cadenas con una longitud máxima de 1000 caracteres.
     *
     * @return {@link IValidationRule} que valida si la cadena tiene un máximo de 1000 caracteres.
     */
    public static IValidationRule string1000() {
        return new StringMaxLengthValidationRule(1000);
    }

    /**
     * Regla de validación para identificar cadenas válidas como identificadores.
     * Esta validación es más estricta, asegurando que la cadena siga un formato específico de identificadores.
     *
     * @return {@link IValidationRule} que valida si la cadena cumple con las reglas de un identificador válido.
     */
    public static IValidationRule identifierString() {
        return (IValidationRule) new IdentifierStringValidationRule();
    }
}
