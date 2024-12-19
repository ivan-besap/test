package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Enums;

/**
 * Fuente que ha establecido este límite de carga.
 *
 * <p>Representa las posibles fuentes que pueden instalar un perfil de carga o
 * establecer un límite de carga en el sistema.</p>
 */
public enum ChargingLimitSourceEnum {
    /**
     * Energy Management System (EMS).
     *
     * Fuente que representa un sistema de gestión de energía.
     */
    EMS,

    /**
     * Otro.
     *
     * Fuente no especificada o distinta a las predefinidas.
     */
    Other,

    /**
     * System Operator (SO).
     *
     * Fuente que representa un operador del sistema.
     */
    SO,

    /**
     * Charging Station Operator (CSO).
     *
     * Fuente que representa al operador de la estación de carga.
     */
    CSO
}
