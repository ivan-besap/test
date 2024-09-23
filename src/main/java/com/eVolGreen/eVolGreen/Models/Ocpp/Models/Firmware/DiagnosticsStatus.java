package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware;

/**
 * Valores aceptados que representan el estado del diagnóstico, usados en {@link DiagnosticsStatusNotificationRequest}.
 * <p>
 * Estos valores indican el estado actual del proceso de diagnóstico, como "Subido" o "Fallo de subida".
 * </p>
 *
 * <ul>
 *   <li>{@code Idle} - El punto de carga no está realizando ninguna operación de diagnóstico.</li>
 *   <li>{@code Uploaded} - Los archivos de diagnóstico han sido subidos con éxito.</li>
 *   <li>{@code UploadFailed} - La subida de los archivos de diagnóstico ha fallado.</li>
 *   <li>{@code Uploading} - Los archivos de diagnóstico están siendo subidos.</li>
 * </ul>
 */
public enum DiagnosticsStatus {
    Idle,
    Uploaded,
    UploadFailed,
    Uploading
}
