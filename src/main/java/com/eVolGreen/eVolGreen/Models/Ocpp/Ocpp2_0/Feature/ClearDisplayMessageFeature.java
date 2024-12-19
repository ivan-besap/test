package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Confirmation.ClearDisplayMessageResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests.ClearDisplayMessageRequest;

/**
 * Característica que representa el mensaje para limpiar mensajes en pantalla (ClearDisplayMessage) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al servidor (CSMS) eliminar uno o más mensajes previamente configurados
 * para ser mostrados en la pantalla de un punto de carga.
 * </p>
 */
public class ClearDisplayMessageFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para limpiar mensajes en pantalla.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public ClearDisplayMessageFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link ClearDisplayMessageRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return ClearDisplayMessageRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link ClearDisplayMessageResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ClearDisplayMessageResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "ClearDisplayMessage".
     */
    @Override
    public String getAction() {
        return "ClearDisplayMessage";
    }
}
