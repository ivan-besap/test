package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;

/**
 * Decorador que impone que un valor debe ser obligatorio para validarse correctamente.
 *
 * <p>Este decorador añade una validación obligatoria a cualquier validador existente.
 * Si el valor es nulo o vacío, lanzará una excepción de restricción de propiedad.
 * En caso contrario, delegará la validación al validador proporcionado.</p>
 */
public class RequiredDecorator extends Validator<Object> {

    private final Validator<Object> requiredValidator = new RequiredValidator();
    private final Validator<Object> decoratee;

    /**
     * Constructor que inicializa el decorador obligatorio con un validador existente.
     *
     * @param validator el validador a decorar con la validación de obligatoriedad
     */
    public RequiredDecorator(Validator<Object> validator) {
        this.decoratee = validator;
    }

    /**
     * Valida un valor asegurándose de que no sea nulo o vacío,
     * luego delega la validación al validador decorado.
     *
     * @param value el valor a validar
     * @throws PropertyConstraintException si el valor no cumple con las restricciones
     */
    @Override
    public void validate(Object value) throws PropertyConstraintException {
        requiredValidator.validate(value); // Validación obligatoria
        decoratee.validate(value);         // Delegar validación adicional
    }
}
