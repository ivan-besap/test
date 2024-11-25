package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.ClearedChargingLimitResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.ClearedChargingLimitRequest;

/**
 * Implementación de la característica "ClearedChargingLimit" dentro del protocolo OCPP 2.0.1.
 * <p>
 * Esta característica permite a un servidor enviar una notificación indicando que un límite
 * de carga previamente configurado ha sido eliminado.
 * </p>
 */
public class ClearedChargingLimitFeature extends FunctionFeature {

    /**
     * Constructor de la clase que asocia esta característica con su función propietaria.
     *
     * @param ownerFunction La función que contiene esta característica.
     */
    public ClearedChargingLimitFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene la clase del tipo de solicitud asociada a esta característica.
     *
     * @return La clase {@link ClearedChargingLimitRequest}, que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return ClearedChargingLimitRequest.class;
    }

    /**
     * Obtiene la clase del tipo de confirmación asociada a esta característica.
     *
     * @return La clase {@link ClearedChargingLimitResponse}, que representa la respuesta.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ClearedChargingLimitResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica.
     *
     * @return Una cadena que representa el nombre de la acción: "ClearedChargingLimit".
     */
    @Override
    public String getAction() {
        return "ClearedChargingLimit";
    }
}
