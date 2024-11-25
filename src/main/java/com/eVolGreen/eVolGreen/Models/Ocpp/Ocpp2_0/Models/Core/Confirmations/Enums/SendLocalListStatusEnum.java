package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Enums;

/**
 * Indica el resultado de la actualización de la lista local de autorizaciones en la estación de carga.
 *
 * <p>Define los posibles estados que puede devolver la estación de carga en respuesta a una solicitud
 * para actualizar la lista local de autorizaciones.
 */
public enum SendLocalListStatusEnum {

    /**
     * La estación de carga ha recibido y aplicado correctamente la actualización de la lista local.
     */
    Accepted,

    /**
     * La estación de carga no pudo aplicar la actualización de la lista local.
     */
    Failed,

    /**
     * La versión proporcionada no coincide con la esperada por la estación de carga.
     */
    VersionMismatch
}
