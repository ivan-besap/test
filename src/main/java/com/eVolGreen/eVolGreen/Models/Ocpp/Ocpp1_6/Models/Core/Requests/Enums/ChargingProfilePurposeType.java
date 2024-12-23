package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.ChargingProfile;

/**
 * Tipos de propósito para un perfil de carga.
 * Usado para determinar el propósito de un {@link ChargingProfile}.
 */
public enum ChargingProfilePurposeType {

    /**
     * Perfil máximo de la estación de carga, establece los límites máximos permitidos por la estación.
     */
    ChargePointMaxProfile,

    /**
     * Perfil por defecto para transacciones, aplicable cuando no se especifica uno.
     */
    TxDefaultProfile,

    /**
     * Perfil de carga específico para una transacción en particular.
     */
    TxProfile
}
