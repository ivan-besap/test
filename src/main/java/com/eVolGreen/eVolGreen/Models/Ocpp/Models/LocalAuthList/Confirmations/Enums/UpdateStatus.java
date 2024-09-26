package com.eVolGreen.eVolGreen.Models.Ocpp.Models.LocalAuthList.Confirmations.Enums;

/**
 * Enum que representa el estado de la actualización de la lista local de autorizaciones.
 * <p>
 * Los posibles valores incluyen:
 * <ul>
 *   <li>{@code Accepted} - La actualización fue aceptada con éxito.</li>
 *   <li>{@code Failed} - La actualización falló.</li>
 *   <li>{@code NotSupported} - La actualización no es compatible.</li>
 *   <li>{@code VersionMismatch} - Hay una discrepancia de versiones en la lista.</li>
 * </ul>
 *
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     UpdateStatus estadoActualizacion = UpdateStatus.Accepted;
 * </pre>
 */
public enum UpdateStatus {
    Accepted,
    Failed,
    NotSupported,
    VersionMismatch
}
