package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Enums;

/**
 * Enum que define los posibles resultados de la operación de configuración
 * de un perfil de red en una estación de carga.
 */
public enum SetNetworkProfileStatusEnum {
    /**
     * La operación se completó exitosamente.
     */
    Accepted,

    /**
     * La operación fue rechazada por la estación de carga.
     */
    Rejected,

    /**
     * La operación no se pudo completar debido a un error interno o externo.
     */
    Failed
}
