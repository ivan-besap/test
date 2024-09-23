package com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Feature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.ProfileFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Reservation.CancelReservationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Reservation.ReserveNowRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.ReserveNowFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.CancelReservationFeature;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.UUID;

/**
 * Perfil de gestión de reservas en el servidor, proporcionando funcionalidades
 * para reservar y cancelar reservas en estaciones de carga.
 */
public class ServerReservationProfile implements Profile {

    private HashSet<Feature> features;

    /**
     * Constructor que inicializa el perfil de gestión de reservas en el servidor,
     * añadiendo las características de reservar y cancelar reservas.
     */
    public ServerReservationProfile() {
        features = new HashSet<>();
        features.add(new ReserveNowFeature(null));
        features.add(new CancelReservationFeature(null));
    }

    /**
     * Devuelve una lista de las características soportadas por este perfil.
     *
     * @return un array de {@link ProfileFeature} con las características soportadas.
     */
    @Override
    public ProfileFeature[] getFeatureList() {
        return features.toArray(new ProfileFeature[0]);
    }

    /**
     * Maneja las solicitudes recibidas y devuelve la confirmación correspondiente.
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request la solicitud recibida.
     * @return una confirmación de tipo {@link Confirmation}.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return null; // Implementación futura para manejar solicitudes de reserva
    }

    /**
     * Crea una solicitud de cancelación de reserva con los valores requeridos.
     *
     * @param reservationId Integer, id de la reserva a cancelar.
     * @return una instancia de {@link CancelReservationRequest}.
     */
    CancelReservationRequest createCancelReservationRequest(Integer reservationId) {
        return new CancelReservationRequest(reservationId);
    }

    /**
     * Crea una solicitud de reserva con los valores requeridos.
     *
     * @param connectorId Integer, identificador del conector de destino.
     * @param expiryDate ZonedDateTime, fecha y hora de fin de la reserva.
     * @param idTag String, identificador del usuario que realiza la reserva.
     * @param reservationId Integer, id de la reserva.
     * @param parentIdTag String, identificador del padre, opcional.
     * @return una instancia de {@link ReserveNowRequest}.
     */
    ReserveNowRequest createReserveNowRequest(
            Integer connectorId,
            ZonedDateTime expiryDate,
            String idTag,
            Integer reservationId,
            String parentIdTag) {
        ReserveNowRequest request =
                new ReserveNowRequest(connectorId, expiryDate, idTag, reservationId);
        request.setParentIdTag(parentIdTag);

        return request;
    }
}
