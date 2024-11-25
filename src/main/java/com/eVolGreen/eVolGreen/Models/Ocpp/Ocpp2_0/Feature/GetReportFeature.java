package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Confirmation.GetReportResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.GetReportRequest;

/**
 * Característica que representa el mensaje para obtener un informe (GetReport) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al servidor (CSMS) solicitar un informe detallado al punto de carga,
 * que incluye información sobre su configuración, capacidades y estado operativo.
 * </p>
 */
public class GetReportFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para obtener un informe.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public GetReportFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link GetReportRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return GetReportRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link GetReportResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetReportResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "GetReport".
     */
    @Override
    public String getAction() {
        return "GetReport";
    }
}
