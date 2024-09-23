package com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile;

import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Reservation.CancelReservationConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Reservation.CancelReservationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Reservation.ReserveNowConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Reservation.ReserveNowRequest;

/**
 * La interfaz {@code ClientReservationEventHandler} define los métodos necesarios para manejar
 * solicitudes relacionadas con las reservas de estaciones de carga. Esto incluye solicitudes para
 * realizar una reserva o cancelar una reserva existente.
 */
public interface ClientReservationEventHandler {

    /**
     * Maneja una solicitud {@link ReserveNowRequest} y devuelve una confirmación {@link ReserveNowConfirmation}.
     * Este método gestiona la lógica necesaria para procesar la solicitud de reserva de una estación de carga.
     *
     * @param request la solicitud entrante de {@link ReserveNowRequest} para gestionar.
     * @return la confirmación {@link ReserveNowConfirmation} como respuesta.
     */
    ReserveNowConfirmation handleReserveNowRequest(ReserveNowRequest request);

    /**
     * Maneja una solicitud {@link CancelReservationRequest} y devuelve una confirmación {@link CancelReservationConfirmation}.
     * Este método gestiona la lógica necesaria para procesar la cancelación de una reserva existente.
     *
     * @param request la solicitud entrante de {@link CancelReservationRequest} para gestionar.
     * @return la confirmación {@link CancelReservationConfirmation} como respuesta.
     */
    CancelReservationConfirmation handleCancelReservationRequest(CancelReservationRequest request);
}
