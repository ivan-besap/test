package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Confirmation.Enums;

/**
 * Enumera los posibles estados de éxito o fallo al realizar una reserva con el mensaje "ReserveNow".
 *
 * <p>Estos estados permiten al sistema determinar el resultado de la operación solicitada para
 * reservar un punto de carga.
 */
public enum ReserveNowStatusEnum {
    /** Indica que la reserva fue aceptada exitosamente. */
    Accepted,

    /** Indica que la reserva falló debido a un error interno o de hardware en el punto de carga. */
    Faulted,

    /** Indica que el punto de carga ya está ocupado y no puede ser reservado. */
    Occupied,

    /** Indica que la solicitud de reserva fue rechazada, por ejemplo, debido a políticas o restricciones. */
    Rejected,

    /** Indica que el punto de carga no está disponible para ser reservado. */
    Unavailable
}
