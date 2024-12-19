package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.NotifyMonitoringReportResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.NotifyMonitoringReportRequest;

/**
 * Característica que representa el mensaje para notificar informes de monitoreo
 * (NotifyMonitoringReport) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite que el punto de carga informe al servidor (CSMS) sobre un
 * informe de monitoreo que incluye datos relacionados con condiciones específicas
 * que están siendo supervisadas, como valores de medición, alertas o anomalías.
 * </p>
 */
public class NotifyMonitoringReportFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para manejar notificaciones de informes de monitoreo.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public NotifyMonitoringReportFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link NotifyMonitoringReportRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return NotifyMonitoringReportRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link NotifyMonitoringReportResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return NotifyMonitoringReportResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "NotifyMonitoringReport".
     */
    @Override
    public String getAction() {
        return "NotifyMonitoringReport";
    }
}
