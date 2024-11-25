package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Confirmation.Enums;

/**
 * CancelReservationStatusEnum
 *
 * <p>Representa el estado del resultado de una solicitud de cancelación de reserva realizada por el CSMS
 * (Central System Management Service) en el protocolo OCPP 2.0.1.
 */
public enum CancelReservationStatusEnum {
    /** Indica que la cancelación de la reserva fue aceptada exitosamente por el CSMS. */
    Accepted,

    /** Indica que la cancelación de la reserva fue rechazada por el CSMS. */
    Rejected
}
