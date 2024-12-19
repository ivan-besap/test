package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Confirmation.CancelReservationResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Confirmation.ReserveNowResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Requests.CancelReservationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Requests.ReserveNowRequest;

/**
 * Interfaz para manejar eventos del cliente relacionados con el bloque funcional de reservas.
 * <p>
 * Este bloque funcional permite gestionar reservas para estaciones de carga,
 * incluyendo la cancelación y la creación de nuevas reservas.
 */
public interface ClientReservationEventHandler {

    /**
     * Maneja una solicitud de cancelación de reserva y genera una respuesta correspondiente.
     *
     * @param request Objeto {@link CancelReservationRequest} que contiene los detalles de la solicitud de cancelación.
     * @return Objeto {@link CancelReservationResponse} con la respuesta procesada.
     */
    CancelReservationResponse handleCancelReservationRequest(CancelReservationRequest request);

    /**
     * Maneja una solicitud para realizar una nueva reserva y genera una respuesta correspondiente.
     *
     * @param request Objeto {@link ReserveNowRequest} que contiene los detalles de la solicitud de reserva.
     * @return Objeto {@link ReserveNowResponse} con la respuesta procesada.
     */
    ReserveNowResponse handleReserveNowRequest(ReserveNowRequest request);
}
