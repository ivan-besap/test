package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.LocalAuthList.Request.Enums;

/**
 * Enum que define los tipos de actualización que pueden aplicarse a la lista local de autorizaciones.
 * <p>
 * Los posibles valores incluyen:
 * <ul>
 *   <li>{@code Full} - Actualización completa de la lista.</li>
 *   <li>{@code Differential} - Actualización diferencial de la lista.</li>
 * </ul>
 *
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     UpdateType tipoActualizacion = UpdateType.Full;
 * </pre>
 */
public enum UpdateType {
    Full,
    Differential
}
