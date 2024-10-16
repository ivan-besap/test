package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;

/**
 * Validador de cadenas que aplica una serie de reglas de validación sobre el valor de la cadena proporcionada.
 *
 * <p>Este validador recibe un conjunto de reglas de validación y las aplica a la cadena proporcionada.
 * Si alguna regla no se cumple, lanza una excepción {@link PropertyConstraintException}.</p>
 */
public class StringValidator extends Validator<String> {

    private final IValidationRule[] rules;

    /**
     * Constructor que acepta una lista de reglas de validación.
     *
     * @param rules un array de reglas de validación a aplicar sobre la cadena
     */
    public StringValidator(IValidationRule[] rules) {
        this.rules = rules;
    }

    /**
     * Aplica todas las reglas de validación a la cadena proporcionada.
     *
     * @param value la cadena a validar
     * @throws PropertyConstraintException si alguna de las reglas no se cumple
     */
    @Override
    public void validate(String value) throws PropertyConstraintException {
        for (IValidationRule rule : rules) {
            rule.validate(value);
        }
    }
}
