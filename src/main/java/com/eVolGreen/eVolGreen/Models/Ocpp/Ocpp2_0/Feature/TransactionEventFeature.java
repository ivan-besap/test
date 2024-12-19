package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Confirmation.TransactionEventResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.TransactionEventRequest;

/**
 * Funcionalidad: Evento de Transacción.
 *
 * <p>La clase `TransactionEventFeature` implementa la funcionalidad del protocolo OCPP 2.0.1
 * que permite a las estaciones de carga notificar al sistema central sobre eventos relacionados
 * con las transacciones, como su inicio, actualización o finalización.
 */
public class TransactionEventFeature extends FunctionFeature {

    /**
     * Constructor de la clase TransactionEventFeature.
     *
     * @param ownerFunction La función propietaria que contiene esta característica.
     */
    public TransactionEventFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link TransactionEventRequest} que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return TransactionEventRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link TransactionEventResponse} que representa la respuesta.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return TransactionEventResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica.
     *
     * @return Una cadena que contiene el nombre de la acción ("TransactionEvent").
     */
    @Override
    public String getAction() {
        return "TransactionEvent";
    }
}
