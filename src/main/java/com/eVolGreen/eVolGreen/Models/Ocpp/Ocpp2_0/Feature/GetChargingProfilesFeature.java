package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.GetChargingProfilesResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.GetChargingProfilesRequest;

/**
 * Característica que representa el mensaje para obtener perfiles de carga (GetChargingProfiles) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al servidor (CSMS) solicitar información sobre uno o más perfiles de carga
 * configurados en un punto de carga para gestionar y optimizar el suministro de energía.
 * </p>
 */
public class GetChargingProfilesFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para obtener perfiles de carga.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public GetChargingProfilesFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link GetChargingProfilesRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return GetChargingProfilesRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link GetChargingProfilesResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetChargingProfilesResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "GetChargingProfiles".
     */
    @Override
    public String getAction() {
        return "GetChargingProfiles";
    }
}

