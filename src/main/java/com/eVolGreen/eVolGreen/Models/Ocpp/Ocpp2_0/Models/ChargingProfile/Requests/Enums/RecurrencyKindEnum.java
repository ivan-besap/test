package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Enums;

/**
 * Tipo de recurrencia para el perfil de carga.
 *
 * <p>Define el punto de inicio de la recurrencia en un perfil de carga. Este tipo se utiliza
 * para especificar patrones de repetición del perfil de carga.
 */
public enum RecurrencyKindEnum {
    /**
     * Recurrencia diaria.
     *
     * <p>El perfil de carga se repite diariamente, comenzando desde el punto de inicio especificado.
     * Es útil para configurar un horario constante para cada día.
     */
    Daily,

    /**
     * Recurrencia semanal.
     *
     * <p>El perfil de carga se repite semanalmente, comenzando desde el punto de inicio especificado.
     * Permite definir horarios que se repiten en un día específico de la semana.
     */
    Weekly
}

