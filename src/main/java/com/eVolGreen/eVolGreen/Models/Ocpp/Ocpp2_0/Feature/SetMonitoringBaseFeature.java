package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.SetMonitoringBaseResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.SetMonitoringBaseRequest;

/**
 * Funcionalidad: Establecer la base de monitoreo.
 *
 * <p>Esta clase representa la funcionalidad OCPP para configurar la base de monitoreo
 * utilizada por la estación de carga para observar variables específicas.
 */
public class SetMonitoringBaseFeature extends FunctionFeature {

    /**
     * Constructor de la clase SetMonitoringBaseFeature.
     *
     * @param ownerFunction La función propietaria que contiene esta característica.
     */
    public SetMonitoringBaseFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link SetMonitoringBaseRequest} que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return SetMonitoringBaseRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link SetMonitoringBaseResponse} que representa la respuesta.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SetMonitoringBaseResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica.
     *
     * @return Una cadena que contiene el nombre de la acción ("SetMonitoringBase").
     */
    @Override
    public String getAction() {
        return "SetMonitoringBase";
    }
}
