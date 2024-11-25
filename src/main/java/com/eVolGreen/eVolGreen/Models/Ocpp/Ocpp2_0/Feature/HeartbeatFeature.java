package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.HeartbeatResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.HeartbeatRequest;

/**
 * Característica que representa el mensaje de latido (Heartbeat) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al punto de carga enviar un latido periódico al servidor (CSMS)
 * para indicar que está activo y operativo. Además, proporciona la hora actual al punto de carga.
 * </p>
 */
public class HeartbeatFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para manejar mensajes de latido.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public HeartbeatFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link HeartbeatRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return HeartbeatRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link HeartbeatResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return HeartbeatResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "Heartbeat".
     */
    @Override
    public String getAction() {
        return "Heartbeat";
    }
}
