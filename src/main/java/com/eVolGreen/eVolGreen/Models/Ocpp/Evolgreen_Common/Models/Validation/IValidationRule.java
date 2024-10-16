package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;

/**
 * Interfaz que define una regla de validación.
 *
 * <p>Las clases que implementan esta interfaz deben proporcionar la lógica para validar
 * un valor de cadena basado en ciertos criterios. Si el valor no cumple con los requisitos
 * de validación, se debe lanzar una excepción de tipo {@link PropertyConstraintException}.</p>
 */
public interface IValidationRule {

    /**
     * Valida un valor de cadena en función de una regla de validación específica.
     *
     * @param value el valor de cadena que debe validarse
     * @throws PropertyConstraintException si el valor no cumple con los requisitos de validación
     */
    void validate(String value) throws PropertyConstraintException;
}
