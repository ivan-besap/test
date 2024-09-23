package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests;

/**
 * Enum que define los posibles tipos de disponibilidad para un conector en el sistema OCPP.
 * Estos valores son utilizados en la solicitud para cambiar la disponibilidad de un conector.
 *
 * @see ChangeAvailabilityRequest
 */
public enum AvailabilityType {

    /**
     * Indica que el conector debe volverse inoperativo.
     */
    Inoperative,

    /**
     * Indica que el conector debe volverse operativo.
     */
    Operative
}
