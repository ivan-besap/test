package com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * El perfil Core contiene las características principales de OCPP v. 1.6
 * <p>
 * Contiene métodos para crear solicitudes salientes desde el cliente.
 */
@Component
public class ClientCoreProfile implements Profile {

    private final ArrayList<Feature> features = new ArrayList<>();
    private final ClientCoreEventHandler eventHandler;

    /**
     * Constructor que configura el manejador de eventos del perfil Core del cliente.
     *
     * @param eventHandler los métodos callback para los eventos del cliente
     */
    @Autowired
    public ClientCoreProfile(ClientCoreEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        initializeFeatures();
    }

    /**
     * Inicializa todas las características del perfil core.
     */
    private void initializeFeatures() {
        features.add(new AuthorizeFeature(null));
        features.add(new BootNotificationFeature(null));
        features.add(new ChangeAvailabilityFeature(this));
        features.add(new ChangeConfigurationFeature(this));
        features.add(new ClearCacheFeature(this));
        features.add(new DataTransferFeature(this));
        features.add(new GetConfigurationFeature(this));
        features.add(new HeartbeatFeature(null));
        features.add(new MeterValuesFeature(null));
        features.add(new RemoteStartTransactionFeature(this));
        features.add(new RemoteStopTransactionFeature(this));
        features.add(new ResetFeature(this));
        features.add(new StartTransactionFeature(null));
        features.add(new StatusNotificationFeature(null));
        features.add(new StopTransactionFeature(null));
        features.add(new UnlockConnectorFeature(this));
    }

    // Métodos para crear distintas solicitudes del cliente
    public AuthorizeRequest createAuthorizeRequest(String idTag) {
        return new AuthorizeRequest(idTag);
    }

    public BootNotificationRequest createBootNotificationRequest(String vendor, String model) {
        return new BootNotificationRequest(vendor, model);
    }

    public DataTransferRequest createDataTransferRequest(String vendorId) {
        return new DataTransferRequest(vendorId);
    }

    public HeartbeatRequest createHeartbeatRequest() {
        return new HeartbeatRequest();
    }

    public MeterValuesRequest createMeterValuesRequest(Integer connectorId, ZonedDateTime timestamp, String value) {
        SampledValue sampledValue = new SampledValue(value);
        return createMeterValuesRequest(connectorId, timestamp, sampledValue);
    }

    public MeterValuesRequest createMeterValuesRequest(Integer connectorId, ZonedDateTime timestamp, SampledValue... sampledValues) {
        MeterValue meterValue = new MeterValue(timestamp, sampledValues);
        return createMeterValuesRequest(connectorId, meterValue);
    }

    public MeterValuesRequest createMeterValuesRequest(Integer connectorId, MeterValue... meterValues) {
        MeterValuesRequest request = new MeterValuesRequest(connectorId);
        request.setMeterValue(meterValues);
        return request;
    }

    public StartTransactionRequest createStartTransactionRequest(Integer connectorId, String idTag, Integer meterStart, ZonedDateTime timestamp) {
        return new StartTransactionRequest(connectorId, idTag, meterStart, timestamp);
    }

    public StatusNotificationRequest createStatusNotificationRequest(Integer connectorId, ChargePointErrorCode errorCode, ChargePointStatus status) {
        return new StatusNotificationRequest(connectorId, errorCode, status);
    }

    public StopTransactionRequest createStopTransactionRequest(int meterStop, ZonedDateTime timestamp, int transactionId) {
        return new StopTransactionRequest(meterStop, timestamp, transactionId);
    }

    @Override
    public ProfileFeature[] getFeatureList() {
        return features.toArray(new ProfileFeature[0]);
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        Confirmation result = null;

        if (request instanceof ChangeAvailabilityRequest) {
            result = eventHandler.handleChangeAvailabilityRequest((ChangeAvailabilityRequest) request);
        } else if (request instanceof ChangeConfigurationRequest) {
            result = eventHandler.handleChangeConfigurationRequest((ChangeConfigurationRequest) request);
        } else if (request instanceof ClearCacheRequest) {
            result = eventHandler.handleClearCacheRequest((ClearCacheRequest) request);
        } else if (request instanceof DataTransferRequest) {
            result = eventHandler.handleDataTransferRequest((DataTransferRequest) request);
        } else if (request instanceof GetConfigurationRequest) {
            result = eventHandler.handleGetConfigurationRequest((GetConfigurationRequest) request);
        } else if (request instanceof RemoteStartTransactionRequest) {
            result = eventHandler.handleRemoteStartTransactionRequest((RemoteStartTransactionRequest) request);
        } else if (request instanceof RemoteStopTransactionRequest) {
            result = eventHandler.handleRemoteStopTransactionRequest((RemoteStopTransactionRequest) request);
        } else if (request instanceof ResetRequest) {
            result = eventHandler.handleResetRequest((ResetRequest) request);
        } else if (request instanceof UnlockConnectorRequest) {
            result = eventHandler.handleUnlockConnectorRequest((UnlockConnectorRequest) request);
        }

        return result;
    }
}
