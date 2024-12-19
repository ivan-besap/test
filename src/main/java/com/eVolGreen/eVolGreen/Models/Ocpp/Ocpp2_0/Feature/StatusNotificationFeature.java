package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.StatusNotificationResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.StatusNotificationRequest;

/**
 * Funcionalidad: Notificación de Estado.
 *
 * <p>La clase `StatusNotificationFeature` implementa la funcionalidad del protocolo OCPP 2.0.1
 * que permite a las estaciones de carga informar al sistema central sobre cambios en su estado
 * operativo o en el estado de un conector específico.
 */
public class StatusNotificationFeature extends FunctionFeature {

    /**
     * Constructor de la clase StatusNotificationFeature.
     *
     * @param ownerFunction La función propietaria que contiene esta característica.
     */
    public StatusNotificationFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link StatusNotificationRequest} que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return StatusNotificationRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link StatusNotificationResponse} que representa la respuesta.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return StatusNotificationResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica.
     *
     * @return Una cadena que contiene el nombre de la acción ("StatusNotification").
     */
    @Override
    public String getAction() {
        return "StatusNotification";
    }
}
