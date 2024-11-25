package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.SetVariablesResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.SetVariablesRequest;

/**
 * Funcionalidad: Configuración de Variables.
 *
 * <p>La clase `SetVariablesFeature` implementa la funcionalidad del protocolo OCPP 2.0.1 que
 * permite al sistema central modificar los valores de las variables configurables de las
 * estaciones de carga. Esto incluye parámetros como límites de carga, configuraciones de red,
 * y otros ajustes de operación.
 */
public class SetVariablesFeature extends FunctionFeature {

    /**
     * Constructor de la clase SetVariablesFeature.
     *
     * @param ownerFunction La función propietaria que contiene esta característica.
     */
    public SetVariablesFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link SetVariablesRequest} que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return SetVariablesRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link SetVariablesResponse} que representa la respuesta.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SetVariablesResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica.
     *
     * @return Una cadena que contiene el nombre de la acción ("SetVariables").
     */
    @Override
    public String getAction() {
        return "SetVariables";
    }
}

