package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Confirmation.CancelReservationResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Requests.CancelReservationRequest;

/**
 * Característica que representa el mensaje de cancelación de reserva (CancelReservation) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al servidor (CSMS) cancelar una reserva previamente realizada en un punto de carga.
 * </p>
 */
public class CancelReservationFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad de cancelación de reserva.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public CancelReservationFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link CancelReservationRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return CancelReservationRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link CancelReservationResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return CancelReservationResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "CancelReservation".
     */
    @Override
    public String getAction() {
        return "CancelReservation";
    }
}

