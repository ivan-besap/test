package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.FunctionFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.GetTransactionStatusFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.TransactionEventFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Enums.TransactionEventEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Enums.TriggerReasonEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.GetTransactionStatusRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.TransactionEventRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Utils.Transaction;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que gestiona la creación de solicitudes y la recepción de respuestas para el bloque funcional
 * de "Transactions" en OCPP 2.0.1.
 */
public class ClientTransactionsFunction implements Function {

    private final ClientTransactionsEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor para inicializar el bloque funcional de transacciones con su manejador de eventos.
     *
     * @param eventHandler La implementación de {@link ClientTransactionsEventHandler} que manejará
     *                     los eventos relacionados con transacciones.
     */
    public ClientTransactionsFunction(ClientTransactionsEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new GetTransactionStatusFeature(this));
        features.add(new TransactionEventFeature(null));
    }

    /**
     * Obtiene la lista de características disponibles en este bloque funcional.
     *
     * @return Un arreglo de las características soportadas.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja una solicitud entrante y devuelve la confirmación correspondiente.
     *
     * @param sessionIndex El identificador único de la sesión.
     * @param request      La solicitud entrante que será procesada.
     * @return La confirmación correspondiente a la solicitud, o {@code null} si no se reconoce.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof GetTransactionStatusRequest) {
            return eventHandler.handleGetTransactionStatusRequest((GetTransactionStatusRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud {@link TransactionEventRequest} con todos los campos requeridos.
     *
     * @param eventType        El tipo de evento de transacción. Los valores posibles son:
     *                         "Started", "Updated" o "Ended".
     * @param timestamp        La fecha y hora en que ocurrió el evento.
     * @param triggerReason    La razón por la que el cargador envía este mensaje al CSMS.
     * @param seqNo            Número de secuencia incremental para rastrear la orden de los eventos.
     * @param transactionInfo  Información de la transacción asociada al evento.
     * @return Una instancia de {@link TransactionEventRequest} configurada con los valores proporcionados.
     */
    public TransactionEventRequest createTransactionEventRequest(
            TransactionEventEnum eventType,
            ZonedDateTime timestamp,
            TriggerReasonEnum triggerReason,
            Integer seqNo,
            Transaction transactionInfo
    ) {
        return new TransactionEventRequest(eventType, timestamp, triggerReason, seqNo, transactionInfo);
    }
}
