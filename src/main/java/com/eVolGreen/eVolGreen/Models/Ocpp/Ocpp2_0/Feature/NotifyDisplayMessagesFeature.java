package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Confirmation.NotifyDisplayMessagesResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.NotifyDisplayMessagesRequest;

/**
 * Característica que representa el mensaje para notificar mensajes de pantalla
 * (NotifyDisplayMessages) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al punto de carga informar al servidor (CSMS) sobre los mensajes
 * que se están mostrando en su pantalla, proporcionando información relevante sobre los
 * mensajes configurados o en cola.
 * </p>
 */
public class NotifyDisplayMessagesFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para manejar notificaciones de mensajes de pantalla.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public NotifyDisplayMessagesFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link NotifyDisplayMessagesRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return NotifyDisplayMessagesRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link NotifyDisplayMessagesResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return NotifyDisplayMessagesResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "NotifyDisplayMessages".
     */
    @Override
    public String getAction() {
        return "NotifyDisplayMessages";
    }
}

