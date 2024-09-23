package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware;

/**
 * Valores aceptados que representan el estado del firmware, usados en {@link FirmwareStatusNotificationRequest}.
 * <p>
 * Estos valores indican el estado actual del proceso de firmware, como "Descargando" o "Instalado".
 * </p>
 *
 * <ul>
 *   <li>{@code Downloaded} - El firmware ha sido descargado con éxito.</li>
 *   <li>{@code DownloadFailed} - La descarga del firmware ha fallado.</li>
 *   <li>{@code Downloading} - El firmware está en proceso de descarga.</li>
 *   <li>{@code Idle} - El punto de carga no está realizando ninguna operación de firmware.</li>
 *   <li>{@code InstallationFailed} - La instalación del firmware ha fallado.</li>
 *   <li>{@code Installing} - El firmware está siendo instalado.</li>
 *   <li>{@code Installed} - El firmware ha sido instalado con éxito.</li>
 * </ul>
 */
public enum FirmwareStatus {
    Downloaded,
    DownloadFailed,
    Downloading,
    Idle,
    InstallationFailed,
    Installing,
    Installed
}
