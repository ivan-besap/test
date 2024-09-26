package com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Handler;

import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware.Confirmations.GetDiagnosticsConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware.Request.GetDiagnosticsRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware.Confirmations.UpdateFirmwareConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware.Request.UpdateFirmwareRequest;

/**
 * La interfaz {@code ClientFirmwareManagementEventHandler} define los métodos de callback
 * que manejan las solicitudes relacionadas con la gestión de firmware.
 * <p>
 * Estas solicitudes provienen del sistema central y se refieren a la gestión de diagnósticos y actualizaciones de firmware.
 */
public interface ClientFirmwareManagementEventHandler {

    /**
     * Maneja una solicitud entrante {@link GetDiagnosticsRequest} y devuelve una {@link GetDiagnosticsConfirmation}.
     * Este método se utiliza cuando el sistema central solicita un diagnóstico de la estación de carga.
     *
     * @param request la solicitud entrante para obtener diagnósticos.
     * @return una confirmación que responde con los resultados del diagnóstico.
     */
    GetDiagnosticsConfirmation handleGetDiagnosticsRequest(GetDiagnosticsRequest request);

    /**
     * Maneja una solicitud entrante {@link UpdateFirmwareRequest} y devuelve una {@link UpdateFirmwareConfirmation}.
     * Este método se utiliza cuando el sistema central solicita una actualización de firmware en la estación de carga.
     *
     * @param request la solicitud entrante para actualizar el firmware.
     * @return una confirmación que responde a la solicitud de actualización del firmware.
     */
    UpdateFirmwareConfirmation handleUpdateFirmwareRequest(UpdateFirmwareRequest request);
}
