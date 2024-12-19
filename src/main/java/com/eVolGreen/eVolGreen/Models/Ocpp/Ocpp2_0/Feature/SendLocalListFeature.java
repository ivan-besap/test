package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.SendLocalListResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.SendLocalListRequest;

/**
 * Funcionalidad: Envío de lista local.
 *
 * <p>Esta clase representa la funcionalidad OCPP para manejar la actualización o el envío de la
 * lista local de autorizaciones a una estación de carga en el protocolo OCPP 2.0.1.
 */
public class SendLocalListFeature extends FunctionFeature {

    /**
     * Constructor de la clase SendLocalListFeature.
     *
     * @param ownerFunction La función que contiene esta característica.
     */
    public SendLocalListFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link SendLocalListRequest} que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return SendLocalListRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link SendLocalListResponse} que representa la respuesta.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SendLocalListResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica.
     *
     * @return Una cadena que contiene el nombre de la acción ("SendLocalList").
     */
    @Override
    public String getAction() {
        return "SendLocalList";
    }
}
