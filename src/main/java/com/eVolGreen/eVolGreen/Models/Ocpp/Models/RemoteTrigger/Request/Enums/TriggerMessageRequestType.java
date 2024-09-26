package com.eVolGreen.eVolGreen.Models.Ocpp.Models.RemoteTrigger.Request.Enums;

/**
 * Enum que representa los tipos de mensajes que se pueden solicitar para ser disparados desde el Sistema Central hacia el Punto de Carga.
 * <p>
 * Los tipos de mensajes incluyen:
 * <ul>
 *   <li>{@code BootNotification} - Notificación de arranque.</li>
 *   <li>{@code DiagnosticsStatusNotification} - Notificación del estado de diagnóstico.</li>
 *   <li>{@code FirmwareStatusNotification} - Notificación del estado del firmware.</li>
 *   <li>{@code Heartbeat} - Mensaje de latido.</li>
 *   <li>{@code MeterValues} - Valores de medidores.</li>
 *   <li>{@code StatusNotification} - Notificación de estado.</li>
 * </ul>
 *
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     TriggerMessageRequestType tipoMensaje = TriggerMessageRequestType.BootNotification;
 * </pre>
 */
public enum TriggerMessageRequestType {
    BootNotification,
    DiagnosticsStatusNotification,
    FirmwareStatusNotification,
    Heartbeat,
    MeterValues,
    StatusNotification
}
