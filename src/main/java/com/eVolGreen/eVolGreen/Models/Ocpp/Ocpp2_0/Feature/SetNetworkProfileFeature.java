package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.SetNetworkProfileResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.SetNetworkProfileRequest;

/**
 * Funcionalidad: Configurar el perfil de red.
 *
 * <p>Esta clase implementa la funcionalidad OCPP que permite al sistema central configurar los
 * parámetros de red de la estación de carga, como configuración IP, DNS y otros ajustes relacionados con la conectividad.
 */
public class SetNetworkProfileFeature extends FunctionFeature {

    /**
     * Constructor de la clase SetNetworkProfileFeature.
     *
     * @param ownerFunction La función propietaria que contiene esta característica.
     */
    public SetNetworkProfileFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link SetNetworkProfileRequest} que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return SetNetworkProfileRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link SetNetworkProfileResponse} que representa la respuesta.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SetNetworkProfileResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica.
     *
     * @return Una cadena que contiene el nombre de la acción ("SetNetworkProfile").
     */
    @Override
    public String getAction() {
        return "SetNetworkProfile";
    }
}
