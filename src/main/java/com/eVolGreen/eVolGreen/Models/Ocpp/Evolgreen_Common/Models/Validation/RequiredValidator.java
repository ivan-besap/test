package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;

/**
 * Validador utilizado para asegurar que un campo obligatorio no sea nulo.
 *
 * <p>La clase {@code RequiredValidator} valida que el valor proporcionado no sea {@code null}.
 * Si el valor es nulo, se lanza una excepción {@link PropertyConstraintException} indicando
 * que el campo es requerido.</p>
 *
 * <pre>
 * Ejemplo de uso:
 *
 * RequiredValidator validator = new RequiredValidator();
 * try {
 *     validator.validate(someValue); // Lanza excepción si someValue es null.
 * } catch (PropertyConstraintException ex) {
 *     System.out.println("El campo es obligatorio y no puede ser null.");
 * }
 * </pre>
 */
public class RequiredValidator extends Validator<Object> {

    // Mensaje de error que se lanza cuando el valor es nulo
    private final String ERROR_MESSAGE = "Field is required and must not be Null.";

    /**
     * Valida que el valor proporcionado no sea {@code null}.
     *
     * @param value el valor a validar.
     * @throws PropertyConstraintException si el valor es {@code null}.
     */
    @Override
    public void validate(Object value) throws PropertyConstraintException {
        if (value == null) {
            // Lanza una excepción con el mensaje de error cuando el valor es null
            throw new PropertyConstraintException(ERROR_MESSAGE, null);
        }
    }
}
