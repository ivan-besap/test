package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.RemoteTrigger.Confirmation.TriggerMessageResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.RemoteTrigger.Request.TriggerMessageRequest;

/**
 * Funcionalidad: Disparar Mensaje.
 *
 * <p>La clase `TriggerMessageFeature` implementa la funcionalidad del protocolo OCPP 2.0.1 que
 * permite al sistema central solicitar a una estación de carga que envíe un mensaje específico,
 * como un evento de estado, valores de medición, o información de diagnóstico.
 */
public class TriggerMessageFeature extends FunctionFeature {

    /**
     * Constructor de la clase TriggerMessageFeature.
     *
     * @param ownerFunction La función propietaria que contiene esta característica.
     */
    public TriggerMessageFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link TriggerMessageRequest} que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return TriggerMessageRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link TriggerMessageResponse} que representa la respuesta.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return TriggerMessageResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica.
     *
     * @return Una cadena que contiene el nombre de la acción ("TriggerMessage").
     */
    @Override
    public String getAction() {
        return "TriggerMessage";
    }
}
