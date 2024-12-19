package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.ClearChargingProfileResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.ClearChargingProfileRequest;

/**
 * Característica que representa el mensaje para limpiar perfiles de carga (ClearChargingProfile) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al servidor (CSMS) eliminar uno o más perfiles de carga configurados en un punto
 * de carga, según los criterios especificados en la solicitud.
 * </p>
 */
public class ClearChargingProfileFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para limpiar perfiles de carga.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public ClearChargingProfileFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link ClearChargingProfileRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return ClearChargingProfileRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link ClearChargingProfileResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ClearChargingProfileResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "ClearChargingProfile".
     */
    @Override
    public String getAction() {
        return "ClearChargingProfile";
    }
}
