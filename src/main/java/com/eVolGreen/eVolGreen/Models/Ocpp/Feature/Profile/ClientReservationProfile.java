package com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.CancelReservationFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Feature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Handler.ClientReservationEventHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.ProfileFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.ReserveNowFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Reservation.Request.CancelReservationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Reservation.Request.ReserveNowRequest;

import java.util.HashSet;
import java.util.UUID;

/**
 * La clase {@code ClientReservationProfile} implementa la lógica para gestionar las reservas en una estación de carga.
 * Esta clase utiliza un manejador de eventos para manejar las solicitudes entrantes relacionadas con reservas,
 * como cancelar o crear una nueva reserva.
 *
 * <p>
 * Las características principales de este perfil incluyen la capacidad de:
 * <ul>
 *     <li>Manejar solicitudes para reservar un conector en una estación de carga.</li>
 *     <li>Manejar solicitudes para cancelar una reserva previamente hecha.</li>
 * </ul>
 *
 *
 * <p>Ejemplo de uso:</p>
 * <pre>
 *     ClientReservationEventHandler handler = new MyReservationHandler();
 *     ClientReservationProfile reservationProfile = new ClientReservationProfile(handler);
 *     ProfileFeature[] features = reservationProfile.getFeatureList();
 * </pre>
 */
public class ClientReservationProfile implements Profile {

    private HashSet<Feature> features;
    private ClientReservationEventHandler eventHandler;

    /**
     * Constructor que inicializa el perfil de reservas del cliente con un manejador de eventos específico.
     *
     * @param eventHandler el manejador de eventos que gestionará las solicitudes relacionadas con reservas.
     */
    public ClientReservationProfile(ClientReservationEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new HashSet<>();
        features.add(new CancelReservationFeature(this));
        features.add(new ReserveNowFeature(this));
    }

    /**
     * Devuelve la lista de características (features) que soporta este perfil de reservas.
     *
     * @return un arreglo de {@link ProfileFeature} que representa las características de este perfil.
     */
    @Override
    public ProfileFeature[] getFeatureList() {
        return features.toArray(new ProfileFeature[0]);
    }

    /**
     * Maneja las solicitudes entrantes relacionadas con reservas, como cancelar o crear una reserva.
     *
     * @param sessionIndex el identificador de la sesión.
     * @param request      la solicitud entrante que debe ser manejada.
     * @return una instancia de {@link Confirmation} como respuesta a la solicitud.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        Confirmation result = null;

        if (request instanceof CancelReservationRequest) {
            result = eventHandler.handleCancelReservationRequest((CancelReservationRequest) request);
        } else if (request instanceof ReserveNowRequest) {
            result = eventHandler.handleReserveNowRequest((ReserveNowRequest) request);
        }

        return result;
    }
}
