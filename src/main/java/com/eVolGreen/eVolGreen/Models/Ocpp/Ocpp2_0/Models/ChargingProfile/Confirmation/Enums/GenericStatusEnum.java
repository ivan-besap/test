package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.Enums;

/**
 * Enum que especifica si el CSMS puede procesar la solicitud.
 *
 * <p>El estado indica si la solicitud fue aceptada o rechazada.
 */
public enum GenericStatusEnum {
    /**
     * Indica que el CSMS pudo procesar exitosamente la solicitud.
     */
    Accepted,

    /**
     * Indica que el CSMS no pudo procesar la solicitud.
     */
    Rejected
}
