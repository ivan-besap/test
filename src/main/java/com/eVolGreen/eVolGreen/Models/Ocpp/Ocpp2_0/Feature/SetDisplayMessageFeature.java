package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Confirmation.SetDisplayMessageResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.SetDisplayMessageRequest;

/**
 * Funcionalidad: Configurar mensajes en pantalla.
 *
 * <p>Esta clase representa la funcionalidad OCPP para establecer mensajes que se mostrarán
 * en la pantalla de una estación de carga, usando el protocolo OCPP 2.0.1.
 */
public class SetDisplayMessageFeature extends FunctionFeature {

    /**
     * Constructor de la clase SetDisplayMessageFeature.
     *
     * @param ownerFunction La función propietaria que contiene esta característica.
     */
    public SetDisplayMessageFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link SetDisplayMessageRequest} que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return SetDisplayMessageRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link SetDisplayMessageResponse} que representa la respuesta.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SetDisplayMessageResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica.
     *
     * @return Una cadena que contiene el nombre de la acción ("SetDisplayMessage").
     */
    @Override
    public String getAction() {
        return "SetDisplayMessage";
    }
}
