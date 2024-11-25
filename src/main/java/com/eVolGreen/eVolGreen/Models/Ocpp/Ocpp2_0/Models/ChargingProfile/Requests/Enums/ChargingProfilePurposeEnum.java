package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Enums;

/**
 * Propósito del perfil de carga.
 *
 * Define el propósito del perfil de carga que describe un horario de entrega de energía
 * o corriente en una estación de carga.
 */
public enum ChargingProfilePurposeEnum {

    /**
     * Restricciones externas de la estación de carga.
     * Define un perfil para restringir la energía de acuerdo con limitaciones externas.
     */
    ChargingStationExternalConstraints,

    /**
     * Perfil máximo de la estación de carga.
     * Representa el nivel máximo de energía o corriente que la estación puede suministrar.
     */
    ChargingStationMaxProfile,

    /**
     * Perfil predeterminado para la transacción.
     * Define un perfil predeterminado a utilizar para transacciones de carga individuales.
     */
    TxDefaultProfile,

    /**
     * Perfil específico de la transacción.
     * Define un perfil asignado específicamente a una transacción de carga en curso.
     */
    TxProfile
}
