package com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile;

import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Handler.ServerFirmwareManagementEventHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware.Request.DiagnosticsStatusNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware.Request.FirmwareStatusNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware.Request.GetDiagnosticsRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware.Request.UpdateFirmwareRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.UUID;

/**
 * La clase {@code ServerFirmwareManagementProfile} gestiona la funcionalidad relacionada con la
 * administración de firmware en el servidor, incluyendo la notificación de estado de diagnóstico,
 * notificación de estado del firmware, y solicitudes de obtención o actualización de firmware.
 * <p>
 * Este perfil agrupa las características relacionadas con la gestión del firmware y proporciona métodos
 * para crear solicitudes y manejar respuestas.
 */
public class ServerFirmwareManagementProfile implements Profile {

    private final ServerFirmwareManagementEventHandler eventHandler;
    private HashSet<Feature> features;

    /**
     * Constructor que inicializa el perfil de gestión de firmware en el servidor.
     *
     * @param eventHandler el manejador de eventos de gestión de firmware del servidor.
     */
    public ServerFirmwareManagementProfile(ServerFirmwareManagementEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new HashSet<>();
        features.add(new GetDiagnosticsFeature(null));
        features.add(new DiagnosticsStatusNotificationFeature(this));
        features.add(new FirmwareStatusNotificationFeature(this));
        features.add(new UpdateFirmwareFeature(null));
    }

    /**
     * Devuelve una lista de las características que soporta este perfil.
     *
     * @return un array de {@link ProfileFeature} soportadas por este perfil.
     */
    @Override
    public ProfileFeature[] getFeatureList() {
        return features.toArray(new ProfileFeature[0]);
    }

    /**
     * Maneja las solicitudes recibidas y devuelve la confirmación correspondiente.
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request la solicitud recibida.
     * @return una confirmación de tipo {@link Confirmation}.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        Confirmation result = null;

        if (request instanceof DiagnosticsStatusNotificationRequest) {
            result = eventHandler.handleDiagnosticsStatusNotificationRequest(
                    sessionIndex, (DiagnosticsStatusNotificationRequest) request);
        } else if (request instanceof FirmwareStatusNotificationRequest) {
            result = eventHandler.handleFirmwareStatusNotificationRequest(
                    sessionIndex, (FirmwareStatusNotificationRequest) request);
        }

        return result;
    }

    /**
     * Crea una solicitud de diagnóstico de firmware con los valores requeridos.
     *
     * @param location la ubicación donde se almacenarán los diagnósticos.
     * @return una instancia de {@link GetDiagnosticsRequest}.
     */
    public GetDiagnosticsRequest createGetDiagnosticsRequest(String location) {
        return new GetDiagnosticsRequest(location);
    }

    /**
     * Crea una solicitud de actualización de firmware con los valores requeridos.
     *
     * @param location la URI donde se encuentra el firmware.
     * @param retrieveDate la fecha y hora de recuperación.
     * @return una instancia de {@link UpdateFirmwareRequest}.
     */
    public UpdateFirmwareRequest createUpdateFirmwareRequest(String location, ZonedDateTime retrieveDate) {
        return new UpdateFirmwareRequest(location, retrieveDate);
    }
}
