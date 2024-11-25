package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.CancelReservationFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.FunctionFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.ReservationStatusUpdateFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.ReserveNowFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Requests.CancelReservationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Requests.Enums.ReservationUpdateStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Requests.ReservationStatusUpdateRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Requests.ReserveNowRequest;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Implementación del bloque funcional de reserva para un cliente OCPP.
 * <p>
 * Esta clase proporciona los métodos necesarios para crear solicitudes de reserva y gestionar las
 * respuestas relacionadas. Incluye características como cancelar reservas, actualizar el estado de
 * las reservas y realizar nuevas reservas.
 */
public class ClientReservationFunction implements Function {

    private final ClientReservationEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor que inicializa la clase con un manejador de eventos.
     *
     * @param eventHandler Manejador de eventos para gestionar las solicitudes relacionadas con
     *                     reservas.
     */
    public ClientReservationFunction(ClientReservationEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new CancelReservationFeature(this));
        features.add(new ReservationStatusUpdateFeature(null));
        features.add(new ReserveNowFeature(this));
    }

    /**
     * Devuelve la lista de características soportadas por este bloque funcional.
     *
     * @return Un arreglo de {@link FunctionFeature} que contiene todas las características
     * soportadas.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja una solicitud entrante y devuelve una confirmación.
     *
     * @param sessionIndex Identificador de la sesión actual.
     * @param request      Solicitud que se debe manejar.
     * @return Confirmación generada a partir de la solicitud, o {@code null} si la solicitud no
     * puede ser manejada.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof CancelReservationRequest) {
            return eventHandler.handleCancelReservationRequest((CancelReservationRequest) request);
        } else if (request instanceof ReserveNowRequest) {
            return eventHandler.handleReserveNowRequest((ReserveNowRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud de actualización del estado de una reserva.
     *
     * @param reservationId           Identificador único de la reserva que se desea actualizar.
     * @param reservationUpdateStatus Nuevo estado de la reserva.
     * @return Una instancia de {@link ReservationStatusUpdateRequest} con los valores proporcionados.
     */
    public ReservationStatusUpdateRequest createReservationStatusUpdateRequest(
            Integer reservationId, ReservationUpdateStatusEnum reservationUpdateStatus) {
        return new ReservationStatusUpdateRequest(reservationId, reservationUpdateStatus);
    }
}
