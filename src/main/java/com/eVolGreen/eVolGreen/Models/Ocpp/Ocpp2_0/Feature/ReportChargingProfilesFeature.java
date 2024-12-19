package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.ReportChargingProfilesResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.ReportChargingProfilesRequest;

/**
 * Característica: Informar Perfiles de Carga.
 *
 * <p>Esta clase implementa la funcionalidad para manejar la acción de informar perfiles de carga
 * de acuerdo con el protocolo OCPP 2.0.1.
 */
public class ReportChargingProfilesFeature extends FunctionFeature {

    /**
     * Constructor de la característica ReportChargingProfilesFeature.
     *
     * @param ownerFunction La función propietaria que contiene esta característica.
     */
    public ReportChargingProfilesFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link ReportChargingProfilesRequest}, que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return ReportChargingProfilesRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link ReportChargingProfilesResponse}, que representa la respuesta de
     * confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ReportChargingProfilesResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica en el protocolo OCPP.
     *
     * @return Una cadena que indica la acción ("ReportChargingProfiles").
     */
    @Override
    public String getAction() {
        return "ReportChargingProfiles";
    }
}
