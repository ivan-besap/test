package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Confirmations.Enums;

/**
 * Enum que representa los valores aceptados en la confirmación del perfil de carga.
 * <p>
 * Los posibles valores para {@code ChargingProfileStatus} incluyen:
 * <ul>
 *   <li>{@code Accepted} - El perfil de carga ha sido aceptado.</li>
 *   <li>{@code Rejected} - El perfil de carga ha sido rechazado.</li>
 *   <li>{@code NotSupported} - El perfil de carga no es soportado.</li>
 * </ul>
 *
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     ChargingProfileStatus status = ChargingProfileStatus.Accepted;
 * </pre>
 */
public enum ChargingProfileStatus {
    Accepted,
    Rejected,
    NotSupported
}
