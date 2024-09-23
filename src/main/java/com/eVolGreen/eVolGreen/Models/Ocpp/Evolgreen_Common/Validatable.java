package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

/**
 * Interfaz utilizada para agregar una opción de validación a los modelos dentro del sistema eVolGreen.
 * <p>
 * Esta interfaz define el método necesario para validar que los campos y modelos subyacentes
 * dentro de una entidad o clase se ajustan a las especificaciones del sistema OCPP (Open Charge Point Protocol).
 * </p>
 */
public interface Validatable {

    /**
     * Método que valida si el modelo cumple con las especificaciones requeridas.
     * <p>
     * Se debe verificar que los campos obligatorios estén correctamente configurados
     * y que cualquier modelo subyacente también sea válido.
     * </p>
     *
     * @return true si el modelo es válido, false en caso contrario.
     */
    boolean validate();
}
