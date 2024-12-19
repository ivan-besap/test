package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Confirmation.GetTransactionStatusResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.GetTransactionStatusRequest;

/**
 * Característica que representa el mensaje para obtener el estado de una transacción (GetTransactionStatus) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al servidor (CSMS) consultar el estado de una transacción en curso
 * en un punto de carga, facilitando el monitoreo y la gestión de la sesión de carga.
 * </p>
 */
public class GetTransactionStatusFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para obtener el estado de una transacción.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public GetTransactionStatusFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link GetTransactionStatusRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return GetTransactionStatusRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link GetTransactionStatusResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetTransactionStatusResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "GetTransactionStatus".
     */
    @Override
    public String getAction() {
        return "GetTransactionStatus";
    }
}
