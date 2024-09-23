package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Reservation;

/**
 * Enum que define los posibles resultados de una solicitud de reserva en el sistema OCPP.
 * Estos estados son utilizados en la respuesta de la solicitud de reserva.
 */
public enum ReservationStatus {

    /**
     * Indica que la reserva fue aceptada correctamente.
     */
    Accepted,

    /**
     * Indica que ocurrió una falla en el sistema al intentar procesar la reserva.
     */
    Faulted,

    /**
     * Indica que el conector o el punto de carga ya está ocupado y no puede ser reservado.
     */
    Occupied,

    /**
     * Indica que la reserva fue rechazada.
     */
    Rejected,

    /**
     * Indica que el conector o punto de carga no está disponible para ser reservado.
     */
    Unavailable
}
