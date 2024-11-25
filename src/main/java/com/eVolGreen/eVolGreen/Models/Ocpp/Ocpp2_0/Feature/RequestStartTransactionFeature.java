package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Confirmation.RequestStartTransactionResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.RequestStartTransactionRequest;

/**
 * Característica: Solicitar Inicio de Transacción.
 *
 * <p>Esta clase implementa la funcionalidad para manejar la acción de solicitud de inicio de una
 * transacción según el protocolo OCPP 2.0.1.
 */
public class RequestStartTransactionFeature extends FunctionFeature {

    /**
     * Constructor de la característica RequestStartTransactionFeature.
     *
     * @param ownerFunction La función propietaria que contiene esta característica.
     */
    public RequestStartTransactionFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link RequestStartTransactionRequest}, que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return RequestStartTransactionRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link RequestStartTransactionResponse}, que representa la respuesta de
     * confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return RequestStartTransactionResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica en el protocolo OCPP.
     *
     * @return Una cadena que indica la acción ("RequestStartTransaction").
     */
    @Override
    public String getAction() {
        return "RequestStartTransaction";
    }
}
