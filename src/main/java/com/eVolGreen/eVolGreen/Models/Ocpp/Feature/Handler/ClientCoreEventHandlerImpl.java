package com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Handler;

import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Handler.ClientCoreEventHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.*;

import org.springframework.stereotype.Component;

@Component
public class ClientCoreEventHandlerImpl implements ClientCoreEventHandler {

    @Override
    public ChangeAvailabilityConfirmation handleChangeAvailabilityRequest(ChangeAvailabilityRequest request) {
        // Lógica de negocio para cambiar la disponibilidad
        return new ChangeAvailabilityConfirmation();
    }

    @Override
    public GetConfigurationConfirmation handleGetConfigurationRequest(GetConfigurationRequest request) {
        // Lógica de negocio para obtener la configuración
        return new GetConfigurationConfirmation();
    }

    @Override
    public ChangeConfigurationConfirmation handleChangeConfigurationRequest(ChangeConfigurationRequest request) {
        // Lógica de negocio para cambiar la configuración
        return new ChangeConfigurationConfirmation();
    }

    @Override
    public ClearCacheConfirmation handleClearCacheRequest(ClearCacheRequest request) {
        // Lógica de negocio para limpiar la caché
        return new ClearCacheConfirmation();
    }

    @Override
    public DataTransferConfirmation handleDataTransferRequest(DataTransferRequest request) {
        // Lógica de negocio para transferencia de datos
        return new DataTransferConfirmation();
    }

    @Override
    public RemoteStartTransactionConfirmation handleRemoteStartTransactionRequest(RemoteStartTransactionRequest request) {
        // Lógica de negocio para iniciar transacción remotamente
        return new RemoteStartTransactionConfirmation();
    }

    @Override
    public RemoteStopTransactionConfirmation handleRemoteStopTransactionRequest(RemoteStopTransactionRequest request) {
        // Lógica de negocio para detener transacción remotamente
        return new RemoteStopTransactionConfirmation();
    }

    @Override
    public ResetConfirmation handleResetRequest(ResetRequest request) {
        // Lógica de negocio para reiniciar el punto de carga
        return new ResetConfirmation();
    }

    @Override
    public UnlockConnectorConfirmation handleUnlockConnectorRequest(UnlockConnectorRequest request) {
        // Lógica de negocio para desbloquear el conector
        return new UnlockConnectorConfirmation();
    }
}
