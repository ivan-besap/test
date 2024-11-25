package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.ClearVariableMonitoringResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.ClearVariableMonitoringRequest;

/**
 * Característica que representa el mensaje para limpiar la monitorización de variables (ClearVariableMonitoring) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al servidor (CSMS) deshabilitar la monitorización de una o más variables configuradas
 * previamente en un punto de carga.
 * </p>
 */
public class ClearVariableMonitoringFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para limpiar la monitorización de variables.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public ClearVariableMonitoringFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link ClearVariableMonitoringRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return ClearVariableMonitoringRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link ClearVariableMonitoringResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ClearVariableMonitoringResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "ClearVariableMonitoring".
     */
    @Override
    public String getAction() {
        return "ClearVariableMonitoring";
    }
}
