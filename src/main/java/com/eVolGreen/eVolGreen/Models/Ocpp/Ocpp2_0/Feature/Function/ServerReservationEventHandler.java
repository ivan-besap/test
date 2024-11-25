package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Confirmation.ReservationStatusUpdateResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Requests.ReservationStatusUpdateRequest;

import java.util.UUID;

/**
 * Interfaz de manejo de eventos para el bloque funcional de Reservas (Reservation) en el servidor.
 * <p>
 * Esta interfaz define los métodos de devolución de llamada para manejar solicitudes relacionadas
 * con actualizaciones del estado de reservas.
 */
public interface ServerReservationEventHandler {

    /**
     * Maneja una solicitud de {@link ReservationStatusUpdateRequest} y devuelve una respuesta
     * {@link ReservationStatusUpdateResponse}.
     *
     * @param sessionIndex Identificador de la sesión en la que se recibió la solicitud.
     * @param request Solicitud entrante de {@link ReservationStatusUpdateRequest} a manejar.
     * @return Respuesta saliente de {@link ReservationStatusUpdateResponse}.
     */
    ReservationStatusUpdateResponse handleReservationStatusUpdateRequest(
            UUID sessionIndex, ReservationStatusUpdateRequest request);
}

