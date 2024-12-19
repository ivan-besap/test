package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Requests.Enums;

/**
 * Enum que representa los estados actualizados de una reserva.
 *
 * <p>Estos estados indican si una reserva ha expirado o ha sido eliminada.
 */
public enum ReservationUpdateStatusEnum {
    /**
     * Indica que la reserva ha expirado.
     *
     * <p>La reserva ya no es válida porque se ha superado su tiempo límite.
     */
    Expired,

    /**
     * Indica que la reserva ha sido eliminada.
     *
     * <p>La reserva ya no está disponible porque ha sido cancelada o removida de forma manual.
     */
    Removed
}
