package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Confirmation.GetDisplayMessagesResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.GetDisplayMessagesRequest;

/**
 * Característica que representa el mensaje para obtener mensajes de pantalla (GetDisplayMessages) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al servidor (CSMS) solicitar una lista de los mensajes configurados en la pantalla
 * de un punto de carga, proporcionando una forma de gestionar y supervisar los mensajes mostrados.
 * </p>
 */
public class GetDisplayMessagesFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para obtener mensajes de pantalla.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public GetDisplayMessagesFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link GetDisplayMessagesRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return GetDisplayMessagesRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link GetDisplayMessagesResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetDisplayMessagesResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "GetDisplayMessages".
     */
    @Override
    public String getAction() {
        return "GetDisplayMessages";
    }
}
