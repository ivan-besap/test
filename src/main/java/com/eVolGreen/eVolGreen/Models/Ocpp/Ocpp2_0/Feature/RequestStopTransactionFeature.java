package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Confirmation.RequestStopTransactionResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.RequestStopTransactionRequest;

/**
 * Característica: Solicitar Detención de Transacción.
 *
 * <p>Esta clase implementa la funcionalidad para manejar la acción de solicitud de detención de una
 * transacción según el protocolo OCPP 2.0.1.
 */
public class RequestStopTransactionFeature extends FunctionFeature {

    /**
     * Constructor de la característica RequestStopTransactionFeature.
     *
     * @param ownerFunction La función propietaria que contiene esta característica.
     */
    public RequestStopTransactionFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link RequestStopTransactionRequest}, que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return RequestStopTransactionRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link RequestStopTransactionResponse}, que representa la respuesta de
     * confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return RequestStopTransactionResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica en el protocolo OCPP.
     *
     * @return Una cadena que indica la acción ("RequestStopTransaction").
     */
    @Override
    public String getAction() {
        return "RequestStopTransaction";
    }
}
