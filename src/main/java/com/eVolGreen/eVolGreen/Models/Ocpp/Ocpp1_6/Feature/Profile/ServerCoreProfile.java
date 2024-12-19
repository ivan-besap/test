package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Handler.ServerCoreEventHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums.AvailabilityType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Enums.ResetType;

import java.util.HashSet;
import java.util.UUID;

/**
 * La clase {@code ServerCoreProfile} gestiona la implementación principal del servidor OCPP,
 * manejando las solicitudes enviadas desde los puntos de carga y devolviendo las confirmaciones
 * correspondientes. Este perfil incluye una serie de funcionalidades clave para la operación y
 * mantenimiento de la infraestructura de carga.
 */
public class ServerCoreProfile implements Profile {

    private ServerCoreEventHandler handler;
    private HashSet<Feature> features;

    /**
     * Constructor que inicializa el perfil con un manejador de eventos.
     *
     * @param handler el manejador de eventos para procesar las solicitudes entrantes.
     */
    public ServerCoreProfile(ServerCoreEventHandler handler) {
        this.handler = handler;
        features = new HashSet<>();
        initializeFeatures();
    }

    /**
     * Método para establecer el manejador de eventos.
     *
     * @param handler el manejador de eventos para procesar solicitudes entrantes.
     */
    public void setEventHandler(ServerCoreEventHandler handler) {
        this.handler = handler;
    }

    /**
     * Inicializa las características soportadas por el perfil.
     */
    private void initializeFeatures() {
        features.add(new AuthorizeFeature(this));
        features.add(new BootNotificationFeature(this));
        features.add(new ChangeAvailabilityFeature(this));
        features.add(new ChangeConfigurationFeature(this));
        features.add(new ClearCacheFeature(this));
        features.add(new DataTransferFeature(this));
        features.add(new GetConfigurationFeature(this));
        features.add(new HeartbeatFeature(this));
        features.add(new MeterValuesFeature(this));
        features.add(new RemoteStartTransactionFeature(this));
        features.add(new RemoteStopTransactionFeature(this));
        features.add(new ResetFeature(this));
        features.add(new StartTransactionFeature(this));
        features.add(new StatusNotificationFeature(this));
        features.add(new StopTransactionFeature(this));
        features.add(new UnlockConnectorFeature(this));
    }

    /**
     * Devuelve la lista de características (features) soportadas por el perfil.
     *
     * @return un arreglo de características implementadas por el perfil.
     */
    @Override
    public ProfileFeature[] getFeatureList() {
        return features.toArray(new ProfileFeature[0]);
    }

    /**
     * Maneja una solicitud OCPP y devuelve la confirmación correspondiente.
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request      la solicitud OCPP que necesita ser procesada.
     * @return una confirmación que responde a la solicitud recibida.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        Confirmation result = null;

        if (request instanceof AuthorizeRequest) {
            result = handler.handleAuthorizeRequest(sessionIndex, (AuthorizeRequest) request);
        } else if (request instanceof BootNotificationRequest) {
            result = handler.handleBootNotificationRequest(sessionIndex, (BootNotificationRequest) request);
        } else if (request instanceof DataTransferRequest) {
            result = handler.handleDataTransferRequest(sessionIndex, (DataTransferRequest) request);
        } else if (request instanceof HeartbeatRequest) {
            result = handler.handleHeartbeatRequest(sessionIndex, (HeartbeatRequest) request);
        } else if (request instanceof MeterValuesRequest) {
            result = handler.handleMeterValuesRequest(sessionIndex, (MeterValuesRequest) request);
        } else if (request instanceof StartTransactionRequest) {
            result = handler.handleStartTransactionRequest(sessionIndex, (StartTransactionRequest) request);
        } else if (request instanceof StatusNotificationRequest) {
            result = handler.handleStatusNotificationRequest(sessionIndex, (StatusNotificationRequest) request);
        } else if (request instanceof StopTransactionRequest) {
            result = handler.handleStopTransactionRequest(sessionIndex, (StopTransactionRequest) request);
        }

        return result;
    }

    // Métodos para crear solicitudes específicas con valores requeridos

    public ChangeAvailabilityRequest createChangeAvailabilityRequest(AvailabilityType type, int connectorId) {
        return new ChangeAvailabilityRequest(connectorId, type);
    }

    public ChangeConfigurationRequest createChangeConfigurationRequest(String key, String value) {
        return new ChangeConfigurationRequest(key, value);
    }

    public ClearCacheRequest createClearCacheRequest() {
        return new ClearCacheRequest();
    }

    public DataTransferRequest createDataTransferRequest(String vendorId) {
        return new DataTransferRequest(vendorId);
    }

    public GetConfigurationRequest createGetConfigurationRequest() {
        return new GetConfigurationRequest();
    }

    public RemoteStartTransactionRequest createRemoteStartTransactionRequest(String idTag) {
        return new RemoteStartTransactionRequest(idTag);
    }

    public RemoteStopTransactionRequest createRemoteStopTransactionRequest(Integer transactionId) {
        return new RemoteStopTransactionRequest(transactionId);
    }

    public ResetRequest createResetRequest(ResetType type) {
        return new ResetRequest(type);
    }

    public UnlockConnectorRequest createUnlockConnectorRequest(int connectorId) {
        return new UnlockConnectorRequest(connectorId);
    }
}
