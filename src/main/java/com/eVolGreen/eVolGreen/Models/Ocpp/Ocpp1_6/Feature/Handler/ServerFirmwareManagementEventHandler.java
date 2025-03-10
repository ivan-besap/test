package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Handler;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Confirmations.DiagnosticsStatusNotificationConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Request.DiagnosticsStatusNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Confirmations.FirmwareStatusNotificationConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Request.FirmwareStatusNotificationRequest;
import java.util.UUID;

/**
 * La interfaz {@code ServerFirmwareManagementEventHandler} define los métodos necesarios para manejar
 * las solicitudes relacionadas con la gestión de firmware en el servidor.
 * <p>
 * Los métodos implementados permiten gestionar las solicitudes de notificación de estado de diagnóstico
 * y de firmware, proporcionando las confirmaciones necesarias para mantener la operación del sistema
 * de carga.
 */
public interface ServerFirmwareManagementEventHandler {

    /**
     * Maneja una solicitud de notificación de estado de diagnóstico y devuelve una confirmación correspondiente.
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request la solicitud {@link DiagnosticsStatusNotificationRequest} recibida.
     * @return una instancia de {@link DiagnosticsStatusNotificationConfirmation} como respuesta.
     */
    DiagnosticsStatusNotificationConfirmation handleDiagnosticsStatusNotificationRequest(
            UUID sessionIndex, DiagnosticsStatusNotificationRequest request);

    /**
     * Maneja una solicitud de notificación de estado de firmware y devuelve una confirmación correspondiente.
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request la solicitud {@link FirmwareStatusNotificationRequest} recibida.
     * @return una instancia de {@link FirmwareStatusNotificationConfirmation} como respuesta.
     */
    FirmwareStatusNotificationConfirmation handleFirmwareStatusNotificationRequest(
            UUID sessionIndex, FirmwareStatusNotificationRequest request);
}
