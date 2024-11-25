package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Confirmation.GetLogResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.GetLogRequest;

/**
 * Característica que representa el mensaje para obtener registros de eventos (GetLog) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al servidor (CSMS) solicitar registros de eventos específicos
 * almacenados en el punto de carga, lo que facilita el análisis de errores y la supervisión de actividades.
 * </p>
 */
public class GetLogFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para obtener registros de eventos.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public GetLogFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link GetLogRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return GetLogRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link GetLogResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetLogResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "GetLog".
     */
    @Override
    public String getAction() {
        return "GetLog";
    }
}
