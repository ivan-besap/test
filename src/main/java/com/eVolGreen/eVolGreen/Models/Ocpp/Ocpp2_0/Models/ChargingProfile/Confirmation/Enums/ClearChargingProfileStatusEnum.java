package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.Enums;

/**
 * Estado de la ejecución de la solicitud de borrado del perfil de carga.
 *
 * Indica si la estación de carga pudo ejecutar la solicitud de borrado.
 */
public enum ClearChargingProfileStatusEnum {

    /**
     * La solicitud fue aceptada y ejecutada con éxito.
     */
    Accepted,

    /**
     * La solicitud no pudo ser ejecutada porque el perfil de carga es desconocido.
     */
    Unknown
}
