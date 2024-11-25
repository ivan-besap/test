package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Confirmation.GetTransactionStatusResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.GetTransactionStatusRequest;

/**
 * Interfaz para el manejo de eventos del bloque funcional "Transactions" en OCPP 2.0.1.
 * <p>
 * Esta interfaz define un método de callback para gestionar solicitudes relacionadas
 * con el estado de las transacciones en una estación de carga.
 * </p>
 */
public interface ClientTransactionsEventHandler {

    /**
     * Maneja una solicitud {@link GetTransactionStatusRequest} y devuelve una respuesta
     * {@link GetTransactionStatusResponse}.
     *
     * @param request La solicitud entrante de estado de transacción.
     * @return La respuesta saliente con el estado de la transacción.
     */
    GetTransactionStatusResponse handleGetTransactionStatusRequest(GetTransactionStatusRequest request);
}
