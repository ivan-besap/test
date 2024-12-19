package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.MeterValuesResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.MeterValuesRequest;

/**
 * Clase que representa la funcionalidad para manejar solicitudes y respuestas del mensaje "MeterValues".
 *
 * Este mensaje se utiliza para enviar datos del medidor desde la estación de carga al sistema central (CSMS),
 * proporcionando lecturas como consumo de energía, voltajes, corrientes, entre otros.
 */
public class MeterValuesFeature extends FunctionFeature {

    /**
     * Constructor de la clase `MeterValuesFeature`.
     *
     * @param ownerFunction La función a la que pertenece esta característica.
     */
    public MeterValuesFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de solicitud asociada con esta funcionalidad.
     *
     * @return La clase que representa el tipo de solicitud: {@link MeterValuesRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return MeterValuesRequest.class;
    }

    /**
     * Obtiene el tipo de confirmación esperada como respuesta a esta funcionalidad.
     *
     * @return La clase que representa el tipo de confirmación: {@link MeterValuesResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return MeterValuesResponse.class;
    }

    /**
     * Obtiene la acción que identifica esta funcionalidad en el protocolo OCPP.
     *
     * @return Una cadena con el nombre de la acción: "MeterValues".
     */
    @Override
    public String getAction() {
        return "MeterValues";
    }
}
