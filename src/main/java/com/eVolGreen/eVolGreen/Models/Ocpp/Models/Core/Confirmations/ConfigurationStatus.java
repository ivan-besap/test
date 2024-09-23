package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations;

/**
 * Enum que define los posibles estados de una solicitud de cambio de configuración en OCPP.
 */
public enum ConfigurationStatus {

    /**
     * Indica que el cambio de configuración fue aceptado.
     */
    Accepted,

    /**
     * Indica que el cambio de configuración fue rechazado.
     */
    Rejected,

    /**
     * Indica que el cambio de configuración requiere un reinicio del punto de carga.
     */
    RebootRequired,

    /**
     * Indica que la configuración solicitada no es compatible con el punto de carga.
     */
    NotSupported
}
