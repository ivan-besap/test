package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Handler.ClientFirmwareManagementEventHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Confirmations.Enums.DiagnosticsStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Request.DiagnosticsStatusNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Request.Enums.FirmwareStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Request.FirmwareStatusNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Request.GetDiagnosticsRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Request.UpdateFirmwareRequest;


import java.util.HashSet;
import java.util.UUID;

/**
 * El perfil de gestión de firmware del cliente gestiona las interacciones relacionadas con
 * el diagnóstico y actualización del firmware en estaciones de carga. Este perfil maneja
 * las características específicas que permiten a un punto de carga reportar el estado de
 * diagnóstico y firmware.
 */
public class ClientFirmwareManagementProfile implements Profile {

    private HashSet<Feature> features;
    private ClientFirmwareManagementEventHandler eventHandler;

    /**
     * Constructor que inicializa el perfil de gestión de firmware con el manejador de eventos.
     *
     * @param eventHandler El manejador de eventos que gestiona las solicitudes de diagnóstico y actualización de firmware.
     */
    public ClientFirmwareManagementProfile(ClientFirmwareManagementEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new HashSet<>();
        features.add(new DiagnosticsStatusNotificationFeature(null));
        features.add(new FirmwareStatusNotificationFeature(null));
        features.add(new GetDiagnosticsFeature(this));
        features.add(new UpdateFirmwareFeature(this));
    }

    /**
     * Crea una solicitud {@link DiagnosticsStatusNotificationRequest} con los valores requeridos.
     *
     * @param status Estado de diagnóstico requerido {@link DiagnosticsStatus}.
     * @return Una instancia de {@link DiagnosticsStatusNotificationRequest}.
     */
    public DiagnosticsStatusNotificationRequest createDiagnosticsStatusNotificationRequest(
            DiagnosticsStatus status) {
        return new DiagnosticsStatusNotificationRequest(status);
    }

    /**
     * Crea una solicitud {@link FirmwareStatusNotificationRequest} con los valores requeridos.
     *
     * @param status Estado del firmware requerido {@link FirmwareStatus}.
     * @return Una instancia de {@link FirmwareStatusNotificationRequest}.
     */
    public FirmwareStatusNotificationRequest createFirmwareStatusNotificationRequest(
            FirmwareStatus status) {
        return new FirmwareStatusNotificationRequest(status);
    }

    @Override
    public ProfileFeature[] getFeatureList() {
        return features.toArray(new ProfileFeature[0]);
    }

    /**
     * Maneja solicitudes entrantes y las delega al manejador de eventos adecuado.
     *
     * @param sessionIndex El indice de la sesión.
     * @param request La solicitud entrante.
     * @return La confirmación basada en la solicitud procesada.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        Confirmation result = null;

        if (request instanceof GetDiagnosticsRequest) {
            result = eventHandler.handleGetDiagnosticsRequest((GetDiagnosticsRequest) request);
        } else if (request instanceof UpdateFirmwareRequest) {
            result = eventHandler.handleUpdateFirmwareRequest((UpdateFirmwareRequest) request);
        }

        return result;
    }
}
