package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.CancelReservationFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.FunctionFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.ReservationStatusUpdateFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.ReserveNowFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Requests.CancelReservationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Requests.ReservationStatusUpdateRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Requests.ReserveNowRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Utils.IdToken;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que gestiona la creación de solicitudes y controladores para el bloque funcional de
 * Reservas (Reservation) en el servidor.
 */
public class ServerReservationFunction implements Function {

    private final ServerReservationEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor para inicializar el controlador de eventos y las características de la función.
     *
     * @param eventHandler Manejador de eventos para las solicitudes relacionadas con reservas.
     */
    public ServerReservationFunction(ServerReservationEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new CancelReservationFeature(null));
        features.add(new ReservationStatusUpdateFeature(this));
        features.add(new ReserveNowFeature(null));
    }

    /**
     * Obtiene la lista de características disponibles en esta función.
     *
     * @return Un arreglo de características soportadas por esta función.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja las solicitudes entrantes relacionadas con reservas.
     *
     * @param sessionIndex Identificador de la sesión donde se recibió la solicitud.
     * @param request Solicitud específica para procesar.
     * @return Confirmación generada tras procesar la solicitud, o {@code null} si no se soporta.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof ReservationStatusUpdateRequest) {
            return eventHandler.handleReservationStatusUpdateRequest(
                    sessionIndex, (ReservationStatusUpdateRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud de {@link CancelReservationRequest} con los campos requeridos.
     *
     * @param reservationId Identificador de la reserva que se desea cancelar.
     * @return Una instancia de {@link CancelReservationRequest}.
     */
    public CancelReservationRequest createCancelReservationRequest(Integer reservationId) {
        return new CancelReservationRequest(reservationId);
    }

    /**
     * Crea una solicitud de {@link ReserveNowRequest} con los campos requeridos.
     *
     * @param id Identificador de la reserva.
     * @param expiryDateTime Fecha y hora en que expira la reserva.
     * @param idToken Token de identificación utilizado para la autorización.
     * @return Una instancia de {@link ReserveNowRequest}.
     */
    public ReserveNowRequest createReserveNowRequest(
            Integer id, ZonedDateTime expiryDateTime, IdToken idToken) {
        return new ReserveNowRequest(id, expiryDateTime, idToken);
    }
}
