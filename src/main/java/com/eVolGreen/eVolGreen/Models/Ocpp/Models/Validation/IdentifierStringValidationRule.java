package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Validation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation.IValidationRule;

import java.util.regex.Pattern;

/**
 * Clase de validación para verificar que una cadena sea un identificador válido.
 * Se asegura que la cadena sólo contenga caracteres permitidos definidos por el patrón: letras, números,
 * y los caracteres especiales: '*', '-', '_', '=', ':', '+', '|', '@', '.'.
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     {@code
 *     IValidationRule rule = new IdentifierStringValidationRule();
 *     rule.validate("validIdentifier123");
 *     }
 * </pre>
 */
public class IdentifierStringValidationRule implements IValidationRule {

    private static final String ERROR_MESSAGE = "Caracter(es) no permitido(s) en la cadena de identificador.";
    private static final String PATTERN = "([a-zA-Z0-9]|\\*|\\-|\\_|\\=|\\:|\\+|\\||\\@|\\.)+";

    /**
     * Valida que la cadena proporcionada siga el formato de un identificador válido.
     * Si la cadena contiene caracteres no permitidos, lanza una excepción.
     *
     * @param value La cadena que se desea validar.
     * @throws PropertyConstraintException si la cadena contiene caracteres no permitidos.
     */
    @Override
    public void validate(String value) throws PropertyConstraintException {
        if (!match(value)) {
            throw new PropertyConstraintException(value, ERROR_MESSAGE);
        }
    }

    /**
     * Verifica si la cadena cumple con el patrón de un identificador válido.
     *
     * @param value La cadena que se desea validar.
     * @return {@code true} si la cadena coincide con el patrón, {@code false} en caso contrario.
     */
    private boolean match(String value) {
        Pattern pattern = Pattern.compile(PATTERN);
        return pattern.matcher(value).matches();
    }
}
