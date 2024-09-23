package com.eVolGreen.eVolGreen.Models.Ocpp.Models.RemoteTrigger;

/**
 * Enum que representa el estado de la solicitud de disparar un mensaje enviada desde el Sistema Central al Punto de Carga.
 * <p>
 * Los posibles valores incluyen:
 * <ul>
 *   <li>{@code Accepted} - La solicitud fue aceptada.</li>
 *   <li>{@code Rejected} - La solicitud fue rechazada.</li>
 *   <li>{@code NotImplemented} - La solicitud no está implementada en el Punto de Carga.</li>
 * </ul>
 * </p>
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     TriggerMessageStatus estado = TriggerMessageStatus.Accepted;
 * </pre>
 */
public enum TriggerMessageStatus {
    Accepted,
    Rejected,
    NotImplemented
}
