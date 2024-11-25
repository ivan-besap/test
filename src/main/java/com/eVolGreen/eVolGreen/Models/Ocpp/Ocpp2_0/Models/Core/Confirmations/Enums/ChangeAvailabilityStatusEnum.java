package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Enums;

/**
 * Indica si la estación de carga puede realizar el cambio de disponibilidad solicitado.
 */
public enum ChangeAvailabilityStatusEnum {
    /**
     * El cambio de disponibilidad ha sido aceptado y se realizará.
     */
    Accepted,

    /**
     * El cambio de disponibilidad ha sido rechazado y no se realizará.
     */
    Rejected,

    /**
     * El cambio de disponibilidad está programado para ser realizado más tarde.
     */
    Scheduled
}
