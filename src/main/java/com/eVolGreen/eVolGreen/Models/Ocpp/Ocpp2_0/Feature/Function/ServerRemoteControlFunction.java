package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.UnlockConnectorRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.RemoteTrigger.Request.Enums.MessageTriggerEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.RemoteTrigger.Request.TriggerMessageRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.RequestStartTransactionRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.RequestStopTransactionRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Utils.IdToken;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que gestiona las solicitudes y respuestas del bloque funcional de control remoto
 * (RemoteControl) en el servidor.
 * <p>
 * Permite crear solicitudes específicas y manejar solicitudes relacionadas con control remoto en
 * OCPP 2.0.1.
 */
public class ServerRemoteControlFunction implements Function {

    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor que inicializa las características del bloque de control remoto.
     */
    public ServerRemoteControlFunction() {
        this.features = new ArrayList<>();
        this.features.add(new RequestStartTransactionFeature(null));
        this.features.add(new RequestStopTransactionFeature(null));
        this.features.add(new TriggerMessageFeature(null));
        this.features.add(new UnlockConnectorFeature(null));
    }

    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        // Actualmente, este método no maneja solicitudes directamente.
        return null;
    }

    /**
     * Crea una solicitud de {@link RequestStartTransactionRequest} con los campos requeridos.
     *
     * @param idToken       Identificador insensible a mayúsculas utilizado para la autorización.
     * @param remoteStartId Identificador proporcionado por el servidor para este inicio remoto.
     * @return Una instancia de {@link RequestStartTransactionRequest}.
     */
    public RequestStartTransactionRequest createRequestStartTransactionRequest(
            IdToken idToken, Integer remoteStartId) {
        return new RequestStartTransactionRequest(idToken, remoteStartId);
    }

    /**
     * Crea una solicitud de {@link RequestStopTransactionRequest} con los campos requeridos.
     *
     * @param transactionId Identificador de la transacción que se solicita detener.
     * @return Una instancia de {@link RequestStopTransactionRequest}.
     */
    public RequestStopTransactionRequest createRequestStopTransactionRequest(String transactionId) {
        return new RequestStopTransactionRequest(transactionId);
    }

    /**
     * Crea una solicitud de {@link TriggerMessageRequest} con los campos requeridos.
     *
     * @param requestedMessage Tipo de mensaje que debe ser activado.
     * @return Una instancia de {@link TriggerMessageRequest}.
     */
    public TriggerMessageRequest createTriggerMessageRequest(MessageTriggerEnum requestedMessage) {
        return new TriggerMessageRequest(requestedMessage);
    }

    /**
     * Crea una solicitud de {@link UnlockConnectorRequest} con los campos requeridos.
     *
     * @param evseId      Identificador del EVSE que contiene el conector.
     * @param connectorId Identificador del conector que necesita ser desbloqueado.
     * @return Una instancia de {@link UnlockConnectorRequest}.
     */
    public UnlockConnectorRequest createUnlockConnectorRequest(Integer evseId, Integer connectorId) {
        return new UnlockConnectorRequest(evseId, connectorId);
    }
}
