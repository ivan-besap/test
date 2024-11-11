package com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Handler;

import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Enums.ResetStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Enums.UnlockStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Utils.KeyValueType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implementación predeterminada de {@link ClientCoreEventHandler}.
 * Esta clase maneja las solicitudes entrantes del sistema central y devuelve las confirmaciones correspondientes.
 */
@Component
@Primary
public class DefaultClientCoreEventHandler implements ClientCoreEventHandler {

    @Override
    public ChangeAvailabilityConfirmation handleChangeAvailabilityRequest(ChangeAvailabilityRequest request) {
        // Implementa la lógica para manejar la solicitud
        return new ChangeAvailabilityConfirmation();
    }

    @Override
    public GetConfigurationConfirmation handleGetConfigurationRequest(GetConfigurationRequest request) {
        // Crear una instancia de GetConfigurationConfirmation
        GetConfigurationConfirmation confirmation = new GetConfigurationConfirmation();

        // Simular la búsqueda de configuraciones existentes
        List<KeyValueType> existingConfigurations = new ArrayList<>(); // Lista de configuraciones conocidas
        List<String> unknownKeys = new ArrayList<>(); // Lista de claves desconocidas

        // Ejemplo de valores de configuración conocidos en el sistema
        Map<String, String> knownConfigurations = Map.of(
                "key1", "value1",
                "key2", "value2",
                "key3", "value3"
        );

        for (String key : request.getKey()) {
            if (knownConfigurations.containsKey(key)) {
                // Si la clave es conocida, se agrega como configuración conocida con `readonly = true`
                KeyValueType configEntry = new KeyValueType(key, true); // Puedes ajustar `readonly` si es necesario
                configEntry.setValue(knownConfigurations.get(key));
                existingConfigurations.add(configEntry);
            } else {
                // Si la clave no es conocida, agregarla a la lista de claves desconocidas
                unknownKeys.add(key);
            }
        }

        // Asignar las configuraciones conocidas y desconocidas a la confirmación
        confirmation.setConfigurationKey(existingConfigurations.toArray(new KeyValueType[0]));
        confirmation.setUnknownKey(unknownKeys.toArray(new String[0]));

        return confirmation;
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
        ResetStatus resetStatus;
        resetStatus = ResetStatus.Accepted;
        return new ResetConfirmation(resetStatus);
    }

    @Override
    public UnlockConnectorConfirmation handleUnlockConnectorRequest(UnlockConnectorRequest request) {
        UnlockStatus status;
        status = UnlockStatus.Unlocked;
        return new UnlockConnectorConfirmation(status);
    }
}
