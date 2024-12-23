package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Reservation.Confirmations.CancelReservationConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Reservation.Request.CancelReservationRequest;


/**
 * La clase {@code CancelReservationFeature} gestiona la funcionalidad de cancelar una reserva
 * en el protocolo OCPP. Permite que una estación de carga o backend cancele una reserva previamente
 * hecha por un usuario.
 * <p>
 * Este feature es fundamental para las operaciones de gestión de reservas, ya que permite
 * liberar un cargador que estaba reservado pero que ya no se utilizará, optimizando el uso de las estaciones.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     CancelReservationFeature cancelReservationFeature = new CancelReservationFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = cancelReservationFeature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = cancelReservationFeature.getConfirmationType();}
 *     String action = cancelReservationFeature.getAction();
 * </pre>
 */
public class CancelReservationFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de cancelación de reserva con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public CancelReservationFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para la cancelación de reserva.
     *
     * @return la clase {@link CancelReservationRequest} correspondiente al tipo de solicitud de cancelación.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return CancelReservationRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para la cancelación de reserva.
     *
     * @return la clase {@link CancelReservationConfirmation} correspondiente al tipo de confirmación de cancelación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return CancelReservationConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de cancelación de reserva.
     * En este caso, la acción es "CancelReservation".
     *
     * @return una cadena de texto con el nombre de la acción: "CancelReservation".
     */
    @Override
    public String getAction() {
        return "CancelReservation";
    }
}
