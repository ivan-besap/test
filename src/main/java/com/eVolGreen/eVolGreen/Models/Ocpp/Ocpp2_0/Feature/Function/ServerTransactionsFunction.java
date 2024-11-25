package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.FunctionFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.GetTransactionStatusFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.TransactionEventFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.GetTransactionStatusRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.TransactionEventRequest;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que gestiona solicitudes y crea respuestas para el bloque funcional de transacciones.
 * Implementa la interfaz {@link Function} y proporciona funcionalidades específicas para
 * manejar solicitudes relacionadas con eventos de transacciones y el estado de las mismas.
 */
public class ServerTransactionsFunction implements Function {

    private final ServerTransactionsEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor que inicializa la función de transacciones del servidor.
     *
     * @param eventHandler Instancia del manejador de eventos {@link ServerTransactionsEventHandler}
     *                     para gestionar solicitudes relacionadas con transacciones.
     */
    public ServerTransactionsFunction(ServerTransactionsEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new GetTransactionStatusFeature(null));
        features.add(new TransactionEventFeature(this));
    }

    /**
     * Obtiene la lista de características (features) soportadas por esta función.
     *
     * @return Un arreglo de instancias {@link FunctionFeature} que representan las características
     *         soportadas.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja solicitudes entrantes relacionadas con transacciones.
     *
     * @param sessionIndex Identificador único de la sesión en la que se recibió la solicitud.
     * @param request Instancia de {@link Request} que contiene los detalles de la solicitud.
     * @return Una instancia de {@link Confirmation} como respuesta a la solicitud, o {@code null}
     *         si la solicitud no es soportada.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof TransactionEventRequest) {
            return eventHandler.handleTransactionEventRequest(
                    sessionIndex, (TransactionEventRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud de estado de transacción {@link GetTransactionStatusRequest}.
     *
     * @return Una instancia de {@link GetTransactionStatusRequest} para solicitar el estado
     *         de una transacción específica.
     */
    public GetTransactionStatusRequest createGetTransactionStatusRequest() {
        return new GetTransactionStatusRequest();
    }
}
