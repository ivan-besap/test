package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Enums;

/**
 * Enum que representa los posibles resultados de un intento de desbloquear un conector.
 * <p>
 * Los valores posibles incluyen:
 * <ul>
 *   <li>{@code Unlocked} - El conector fue desbloqueado con éxito.</li>
 *   <li>{@code UnlockFailed} - El intento de desbloquear el conector falló.</li>
 *   <li>{@code NotSupported} - El desbloqueo no es compatible con el conector especificado.</li>
 * </ul>
 */
public enum UnlockStatus {
    Unlocked,
    UnlockFailed,
    NotSupported
}
