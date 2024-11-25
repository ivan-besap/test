package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.GetMonitoringReportResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.GetMonitoringReportRequest;

/**
 * Característica que representa el mensaje para obtener un informe de monitoreo (GetMonitoringReport) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al servidor (CSMS) solicitar un informe de monitoreo que contiene información
 * sobre eventos y variables configuradas en el punto de carga.
 * </p>
 */
public class GetMonitoringReportFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para obtener un informe de monitoreo.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public GetMonitoringReportFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link GetMonitoringReportRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return GetMonitoringReportRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link GetMonitoringReportResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetMonitoringReportResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "GetMonitoringReport".
     */
    @Override
    public String getAction() {
        return "GetMonitoringReport";
    }
}

