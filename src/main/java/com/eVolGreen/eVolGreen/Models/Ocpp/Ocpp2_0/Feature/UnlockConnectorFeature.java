package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.UnlockConnectorResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.UnlockConnectorRequest;

/**
 * Funcionalidad: Desbloquear conector.
 *
 * <p>La clase `UnlockConnectorFeature` implementa la funcionalidad del protocolo OCPP 2.0.1 que
 * permite al sistema central solicitar a una estación de carga que desbloquee un conector específico.
 */
public class UnlockConnectorFeature extends FunctionFeature {

    /**
     * Constructor de la clase UnlockConnectorFeature.
     *
     * @param ownerFunction La función propietaria que contiene esta característica.
     */
    public UnlockConnectorFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link UnlockConnectorRequest} que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return UnlockConnectorRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link UnlockConnectorResponse} que representa la respuesta.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return UnlockConnectorResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica.
     *
     * @return Una cadena que contiene el nombre de la acción ("UnlockConnector").
     */
    @Override
    public String getAction() {
        return "UnlockConnector";
    }
}
