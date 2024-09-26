package com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Handler;

import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.*;
import org.springframework.stereotype.Component;

/**
 * Implementación predeterminada de {@link ClientCoreEventHandler}.
 * Esta clase maneja las solicitudes entrantes del sistema central y devuelve las confirmaciones correspondientes.
 */
@Component
@SuppressWarnings("deprecation") // Ignora advertencias de deprecación temporalmente
public class DefaultClientCoreEventHandler implements ClientCoreEventHandler {

    @Override
    public ChangeAvailabilityConfirmation handleChangeAvailabilityRequest(ChangeAvailabilityRequest request) {
        // Implementa la lógica para manejar la solicitud
        return new ChangeAvailabilityConfirmation();
    }

    @Override
    public GetConfigurationConfirmation handleGetConfigurationRequest(GetConfigurationRequest request) {
        // Implementa la lógica
        return new GetConfigurationConfirmation();
    }

    @Override
    public ChangeConfigurationConfirmation handleChangeConfigurationRequest(ChangeConfigurationRequest request) {
        return new ChangeConfigurationConfirmation();
    }

    @Override
    public ClearCacheConfirmation handleClearCacheRequest(ClearCacheRequest request) {
        return new ClearCacheConfirmation();
    }

    @Override
    public DataTransferConfirmation handleDataTransferRequest(DataTransferRequest request) {
        return new DataTransferConfirmation();
    }

    @Override
    public RemoteStartTransactionConfirmation handleRemoteStartTransactionRequest(RemoteStartTransactionRequest request) {
        return new RemoteStartTransactionConfirmation();
    }

    @Override
    public RemoteStopTransactionConfirmation handleRemoteStopTransactionRequest(RemoteStopTransactionRequest request) {
        return new RemoteStopTransactionConfirmation();
    }

    @Override
    public ResetConfirmation handleResetRequest(ResetRequest request) {
        return new ResetConfirmation();
    }

    @Override
    public UnlockConnectorConfirmation handleUnlockConnectorRequest(UnlockConnectorRequest request) {
        return new UnlockConnectorConfirmation();
    }
}
