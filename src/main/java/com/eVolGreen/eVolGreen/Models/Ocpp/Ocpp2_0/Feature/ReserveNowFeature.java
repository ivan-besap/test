package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Confirmation.ReserveNowResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Requests.ReserveNowRequest;

/**
 * Funcionalidad: Reservar un punto de carga.
 *
 * <p>Esta clase representa la funcionalidad OCPP para manejar la acción de reservar un punto de
 * carga en el protocolo OCPP 2.0.1.
 */
public class ReserveNowFeature extends FunctionFeature {

    /**
     * Constructor de la clase ReserveNowFeature.
     *
     * @param ownerFunction La función que contiene esta característica.
     */
    public ReserveNowFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link ReserveNowRequest} que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return ReserveNowRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link ReserveNowResponse} que representa la respuesta.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ReserveNowResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica.
     *
     * @return Una cadena que contiene el nombre de la acción ("ReserveNow").
     */
    @Override
    public String getAction() {
        return "ReserveNow";
    }
}
