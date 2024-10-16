package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation;

import java.util.ArrayList;

/**
 * Clase para construir validadores con múltiples reglas y configuraciones.
 *
 * <p>La clase `ValidatorBuilder` permite crear validadores con una lista de reglas personalizadas y
 * configurar si el campo es obligatorio o no. Ofrece una API fluida para añadir reglas de validación
 * y especificar si la validación es obligatoria.</p>
 */
public class ValidatorBuilder {

    private boolean required = false;
    private ArrayList<IValidationRule> rules;

    /**
     * Constructor que inicializa un nuevo `ValidatorBuilder` con una lista vacía de reglas de validación.
     */
    public ValidatorBuilder() {
        rules = new ArrayList<>();
    }

    /**
     * Añade una nueva regla de validación a la lista de reglas.
     *
     * @param rule la regla de validación a añadir
     * @return el propio `ValidatorBuilder` para encadenar llamadas
     */
    public ValidatorBuilder addRule(IValidationRule rule) {
        rules.add(rule);
        return this;
    }

    /**
     * Establece si la validación será obligatoria.
     *
     * @param isRequired indica si el campo es obligatorio o no
     * @return el propio `ValidatorBuilder` para encadenar llamadas
     */
    public ValidatorBuilder setRequired(boolean isRequired) {
        required = isRequired;
        return this;
    }

    /**
     * Construye y devuelve un validador basado en las reglas y configuraciones proporcionadas.
     *
     * <p>Si la validación es obligatoria, se envolverá en un `RequiredDecorator`. Si no es obligatoria,
     * se envolverá en un `OptionalDecorator`.</p>
     *
     * @return el validador construido
     */
    public Validator build() {
        Validator validator = new StringValidator(rules.toArray(new IValidationRule[0]));

        if (required) {
            validator = new RequiredDecorator(validator);
        } else {
            validator = new OptionalDecorator(validator);
        }

        return validator;
    }
}
