package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Validation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation.IValidationRule;

/**
 * Clase de validación que verifica si una cadena excede un límite de longitud.
 * Lanza una excepción si la longitud de la cadena supera el máximo permitido.
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     {@code
 *     IValidationRule rule = new StringMaxLengthValidationRule(50);
 *     rule.validate("Cadena de prueba");
 *     }
 * </pre>
 */
public class StringMaxLengthValidationRule implements IValidationRule {

    private static final String ERROR_MESSAGE = "Excedido el límite de %s caracteres";
    private final int maxLength;

    /**
     * Constructor que inicializa la regla con la longitud máxima permitida.
     *
     * @param maxLength Longitud máxima que se permite en la cadena.
     */
    public StringMaxLengthValidationRule(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * Valida que la cadena no exceda la longitud máxima permitida.
     *
     * @param value La cadena que se desea validar.
     * @throws PropertyConstraintException si la longitud de la cadena excede el máximo permitido.
     */
    @Override
    public void validate(String value) throws PropertyConstraintException {
        if (value.length() > maxLength) {
            throw new PropertyConstraintException(
                    value.length(), String.format(ERROR_MESSAGE, maxLength));
        }
    }
}
