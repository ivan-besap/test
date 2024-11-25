package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.NotifyEventResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.NotifyEventRequest;

/**
 * Característica que representa el mensaje para notificar eventos (NotifyEvent) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite que el punto de carga informe al servidor (CSMS) sobre eventos que han ocurrido.
 * Estos eventos pueden incluir errores, cambios de estado o cualquier otra notificación relevante para
 * el monitoreo y la gestión del punto de carga.
 * </p>
 */
public class NotifyEventFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para manejar notificaciones de eventos.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public NotifyEventFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link NotifyEventRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return NotifyEventRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link NotifyEventResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return NotifyEventResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "NotifyEvent".
     */
    @Override
    public String getAction() {
        return "NotifyEvent";
    }
}
