package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.GetVariablesResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.GetVariablesRequest;

/**
 * Característica que representa el mensaje para obtener variables (GetVariables) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al servidor (CSMS) solicitar valores de variables específicas configuradas
 * o soportadas por un punto de carga, facilitando el monitoreo y control del dispositivo.
 * </p>
 */
public class GetVariablesFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para obtener variables.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public GetVariablesFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link GetVariablesRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return GetVariablesRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link GetVariablesResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetVariablesResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "GetVariables".
     */
    @Override
    public String getAction() {
        return "GetVariables";
    }
}
