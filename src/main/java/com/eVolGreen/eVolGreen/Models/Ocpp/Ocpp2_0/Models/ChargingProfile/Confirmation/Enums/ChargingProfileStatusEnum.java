package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.Enums;

/**
 * Indica si la estación de carga pudo procesar exitosamente el mensaje.
 * No garantiza que el programa de carga se siga exactamente, ya que pueden existir
 * otras restricciones que la estación de carga deba considerar.
 */
public enum ChargingProfileStatusEnum {
    /** La solicitud fue aceptada exitosamente. */
    Accepted,

    /** La solicitud fue rechazada. */
    Rejected
}
