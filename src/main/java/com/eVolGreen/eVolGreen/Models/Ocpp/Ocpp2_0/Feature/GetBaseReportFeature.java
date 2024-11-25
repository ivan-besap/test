package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Confirmation.GetBaseReportResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.GetBaseReportRequest;

/**
 * Característica que representa el mensaje para obtener un informe base (GetBaseReport) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al servidor (CSMS) solicitar un informe base del punto de carga, que contiene
 * información específica sobre la configuración, las capacidades o el estado del dispositivo.
 * </p>
 */
public class GetBaseReportFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para obtener un informe base.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public GetBaseReportFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link GetBaseReportRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return GetBaseReportRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link GetBaseReportResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetBaseReportResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "GetBaseReport".
     */
    @Override
    public String getAction() {
        return "GetBaseReport";
    }
}
