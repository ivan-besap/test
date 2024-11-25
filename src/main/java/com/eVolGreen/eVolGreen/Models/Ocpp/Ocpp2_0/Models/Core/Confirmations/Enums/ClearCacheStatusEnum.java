package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Enums;

/**
 * ClearCacheStatusEnum
 *
 * Enumera los posibles estados de la respuesta a una solicitud para limpiar la memoria caché en una estación de carga.
 */
public enum ClearCacheStatusEnum {
    /**
     * La solicitud de limpiar la memoria caché fue aceptada y ejecutada por la estación de carga.
     */
    Accepted,

    /**
     * La solicitud de limpiar la memoria caché fue rechazada por la estación de carga.
     */
    Rejected
}
