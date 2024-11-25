package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Enums;

/**
 * Tipo de perfil de carga.
 *
 * <p>Define el tipo de horario utilizado en el perfil de carga. Esto especifica cómo se estructuran
 * y aplican los horarios para regular la carga.
 */
public enum ChargingProfileKindEnum {
    /**
     * Perfil absoluto.
     *
     * <p>El horario se define en función de un tiempo absoluto (por ejemplo, el 01/01/2024 a las 12:00 PM).
     * Especifica un punto en el tiempo exacto para la aplicación del perfil.
     */
    Absolute,

    /**
     * Perfil recurrente.
     *
     * <p>El horario se repite en intervalos definidos (por ejemplo, todos los días a las 10:00 AM).
     * Es útil para establecer patrones regulares de carga.
     */
    Recurring,

    /**
     * Perfil relativo.
     *
     * <p>El horario se define en relación con un evento (por ejemplo, una transacción de carga específica).
     * El tiempo se calcula en función de la duración o del inicio de la transacción.
     */
    Relative
}
