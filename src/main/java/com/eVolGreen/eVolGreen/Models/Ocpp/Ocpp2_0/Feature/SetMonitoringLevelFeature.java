package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.SetMonitoringLevelResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.SetMonitoringLevelRequest;

/**
 * Funcionalidad: Establecer el nivel de monitoreo.
 *
 * <p>Esta clase representa la funcionalidad OCPP para configurar el nivel de detalle
 * del monitoreo de eventos y variables en la estación de carga.
 */
public class SetMonitoringLevelFeature extends FunctionFeature {

    /**
     * Constructor de la clase SetMonitoringLevelFeature.
     *
     * @param ownerFunction La función propietaria que contiene esta característica.
     */
    public SetMonitoringLevelFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link SetMonitoringLevelRequest} que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return SetMonitoringLevelRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link SetMonitoringLevelResponse} que representa la respuesta.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SetMonitoringLevelResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica.
     *
     * @return Una cadena que contiene el nombre de la acción ("SetMonitoringLevel").
     */
    @Override
    public String getAction() {
        return "SetMonitoringLevel";
    }
}
