package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation;

/**
 * Decorador opcional para la validación de cadenas.
 *
 * <p>Esta clase permite que la validación de un valor de cadena sea opcional, es decir,
 * si el valor es nulo, no se realiza la validación. En caso contrario, se delega la validación
 * al validador proporcionado.</p>
 */
public class OptionalDecorator extends Validator<String> {

    private final Validator<String> validator;

    /**
     * Constructor que inicializa el decorador opcional con un validador existente.
     *
     * @param validator el validador a utilizar si el valor no es nulo
     */
    public OptionalDecorator(Validator<String> validator) {
        this.validator = validator;
    }

    /**
     * Valida un valor de cadena, si no es nulo. Si el valor es nulo, se omite la validación.
     *
     * @param value el valor de cadena que se validará
     */
    @Override
    public void validate(String value) {
        if (value == null) return;

        this.validator.validate(value);
    }
}
