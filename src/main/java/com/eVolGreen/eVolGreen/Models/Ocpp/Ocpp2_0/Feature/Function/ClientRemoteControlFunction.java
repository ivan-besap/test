package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.UnlockConnectorRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.RemoteTrigger.Request.TriggerMessageRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.RequestStartTransactionRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.RequestStopTransactionRequest;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que proporciona creadores de solicitudes del cliente y manejadores de solicitudes
 * para el bloque funcional de control remoto en OCPP.
 *
 * Este bloque incluye operaciones para iniciar y detener transacciones remotamente,
 * activar mensajes específicos y desbloquear conectores.
 */
public class ClientRemoteControlFunction implements Function {

    private final ClientRemoteControlEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor para inicializar el bloque funcional de control remoto con un manejador de eventos.
     *
     * @param eventHandler Instancia de {@link ClientRemoteControlEventHandler} para manejar eventos.
     */
    public ClientRemoteControlFunction(ClientRemoteControlEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new RequestStartTransactionFeature(this));
        features.add(new RequestStopTransactionFeature(this));
        features.add(new TriggerMessageFeature(this));
        features.add(new UnlockConnectorFeature(this));
    }

    /**
     * Devuelve la lista de características funcionales soportadas por este bloque funcional.
     *
     * @return Un arreglo de {@link FunctionFeature} que contiene las características soportadas.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja una solicitud entrante basada en su tipo.
     *
     * @param sessionIndex Identificador único de la sesión en curso.
     * @param request Objeto {@link Request} que representa la solicitud entrante.
     * @return Un objeto {@link Confirmation} que representa la respuesta generada, o {@code null} si
     *         no se reconoce el tipo de solicitud.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof RequestStartTransactionRequest) {
            return eventHandler.handleRequestStartTransactionRequest(
                    (RequestStartTransactionRequest) request);
        } else if (request instanceof RequestStopTransactionRequest) {
            return eventHandler.handleRequestStopTransactionRequest(
                    (RequestStopTransactionRequest) request);
        } else if (request instanceof TriggerMessageRequest) {
            return eventHandler.handleTriggerMessageRequest((TriggerMessageRequest) request);
        } else if (request instanceof UnlockConnectorRequest) {
            return eventHandler.handleUnlockConnectorRequest((UnlockConnectorRequest) request);
        }
        return null;
    }
}
