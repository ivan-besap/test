package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Confirmation.TransactionEventResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.TransactionEventRequest;

import java.util.UUID;

/**
 * Interfaz de manejo de eventos para el bloque funcional de transacciones (Transactions).
 * Esta interfaz define los métodos para manejar eventos relacionados con transacciones,
 * como el inicio, actualización y finalización de una transacción en el sistema de gestión central (CSMS).
 */
public interface ServerTransactionsEventHandler {

    /**
     * Maneja una solicitud de evento de transacción {@link TransactionEventRequest}.
     *
     * @param sessionIndex Identificador único de la sesión en la que se recibió la solicitud.
     *                     Este ID está asociado a la conexión activa entre la estación de carga y el CSMS.
     * @param request La solicitud {@link TransactionEventRequest} que contiene los detalles del evento de transacción.
     * @return Una respuesta {@link TransactionEventResponse} que confirma el manejo del evento de transacción.
     */
    TransactionEventResponse handleTransactionEventRequest(
            UUID sessionIndex, TransactionEventRequest request);
}
