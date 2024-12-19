package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.SecurityEventNotificationResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.SecurityEventNotificationRequest;

/**
 * Funcionalidad: Notificación de evento de seguridad.
 *
 * <p>Esta clase representa la funcionalidad OCPP para manejar notificaciones de eventos de
 * seguridad en el protocolo OCPP 2.0.1.
 */
public class SecurityEventNotificationFeature extends FunctionFeature {

    /**
     * Constructor de la clase SecurityEventNotificationFeature.
     *
     * @param ownerFunction La función que contiene esta característica.
     */
    public SecurityEventNotificationFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link SecurityEventNotificationRequest} que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return SecurityEventNotificationRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link SecurityEventNotificationResponse} que representa la respuesta.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SecurityEventNotificationResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica.
     *
     * @return Una cadena que contiene el nombre de la acción ("SecurityEventNotification").
     */
    @Override
    public String getAction() {
        return "SecurityEventNotification";
    }
}
