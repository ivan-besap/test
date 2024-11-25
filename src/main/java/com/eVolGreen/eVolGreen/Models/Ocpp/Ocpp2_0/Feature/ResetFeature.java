package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.ResetResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.ResetRequest;

/**
 * Funcionalidad: Reiniciar el punto de carga.
 *
 * <p>Esta clase representa la funcionalidad OCPP para manejar la acción de reiniciar un punto de
 * carga en el protocolo OCPP 2.0.1.
 */
public class ResetFeature extends FunctionFeature {

    /**
     * Constructor de la clase ResetFeature.
     *
     * @param ownerFunction La función que contiene esta característica.
     */
    public ResetFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link ResetRequest} que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return ResetRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link ResetResponse} que representa la respuesta.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ResetResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica.
     *
     * @return Una cadena que contiene el nombre de la acción ("Reset").
     */
    @Override
    public String getAction() {
        return "Reset";
    }
}
