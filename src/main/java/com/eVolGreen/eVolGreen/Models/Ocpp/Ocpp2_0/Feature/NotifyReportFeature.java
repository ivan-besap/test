package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.NotifyReportResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.NotifyReportRequest;

/**
 * Característica que representa el mensaje para notificar un informe (NotifyReport) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite que el punto de carga envíe al servidor (CSMS) informes con datos relevantes,
 * como configuraciones, estado del sistema o métricas de rendimiento, en respuesta a solicitudes anteriores
 * o como parte de un monitoreo periódico.
 * </p>
 */
public class NotifyReportFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para manejar notificaciones de informes.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public NotifyReportFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link NotifyReportRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return NotifyReportRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link NotifyReportResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return NotifyReportResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "NotifyReport".
     */
    @Override
    public String getAction() {
        return "NotifyReport";
    }
}
