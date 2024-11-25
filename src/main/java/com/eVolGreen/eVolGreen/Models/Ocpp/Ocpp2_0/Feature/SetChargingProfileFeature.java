package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.SetChargingProfileResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.SetChargingProfileRequest;

/**
 * Funcionalidad: Establecer el perfil de carga.
 *
 * <p>Esta clase representa la funcionalidad OCPP para gestionar la configuración o actualización
 * de los perfiles de carga en una estación de carga usando el protocolo OCPP 2.0.1.
 */
public class SetChargingProfileFeature extends FunctionFeature {

    /**
     * Constructor de la clase SetChargingProfileFeature.
     *
     * @param ownerFunction La función que contiene esta característica.
     */
    public SetChargingProfileFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link SetChargingProfileRequest} que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return SetChargingProfileRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link SetChargingProfileResponse} que representa la respuesta.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SetChargingProfileResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica.
     *
     * @return Una cadena que contiene el nombre de la acción ("SetChargingProfile").
     */
    @Override
    public String getAction() {
        return "SetChargingProfile";
    }
}

