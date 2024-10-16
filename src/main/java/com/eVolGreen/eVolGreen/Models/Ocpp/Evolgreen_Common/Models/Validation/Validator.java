package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;

/**
 * Clase abstracta para la validación de tipos genéricos.
 *
 * <p>Esta clase define la estructura básica para validadores que aplican reglas de validación sobre un tipo específico.
 * Las subclases deben implementar el método {@link #validate(Object)} para definir la lógica de validación.</p>
 *
 * @param <T> el tipo de dato que será validado
 */
public abstract class Validator<T> {

    /**
     * Valida de manera segura el valor proporcionado, manejando cualquier excepción que ocurra.
     *
     * <p>Este método intenta validar el valor, capturando cualquier excepción que se produzca
     * y devolviendo {@code false} si la validación falla. Si la validación tiene éxito, devuelve {@code true}.</p>
     *
     * @param value el valor a validar
     * @return {@code true} si la validación es exitosa, {@code false} en caso de error
     */
    public boolean safeValidate(T value) {
        boolean returnValue = true;
        try {
            this.validate(value);
        } catch (Exception ex) {
            returnValue = false;
        }
        return returnValue;
    }

    /**
     * Valida el valor proporcionado.
     *
     * <p>Este método debe ser implementado por las subclases para definir la lógica de validación.</p>
     *
     * @param value el valor a validar
     * @throws PropertyConstraintException si el valor no cumple con las reglas de validación
     */
    public abstract void validate(T value) throws PropertyConstraintException;
}
