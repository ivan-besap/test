package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Confirmation.ReservationStatusUpdateResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Requests.ReservationStatusUpdateRequest;

/**
 * Funcionalidad: Actualización del estado de una reserva.
 *
 * <p>Esta clase representa la funcionalidad OCPP para manejar la acción de actualización
 * del estado de una reserva en el protocolo OCPP 2.0.1.
 */
public class ReservationStatusUpdateFeature extends FunctionFeature {

    /**
     * Constructor de la clase ReservationStatusUpdateFeature.
     *
     * @param ownerFunction La función que contiene esta característica.
     */
    public ReservationStatusUpdateFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link ReservationStatusUpdateRequest} que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return ReservationStatusUpdateRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link ReservationStatusUpdateResponse} que representa la respuesta.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ReservationStatusUpdateResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica.
     *
     * @return Una cadena que contiene el nombre de la acción ("ReservationStatusUpdate").
     */
    @Override
    public String getAction() {
        return "ReservationStatusUpdate";
    }
}
