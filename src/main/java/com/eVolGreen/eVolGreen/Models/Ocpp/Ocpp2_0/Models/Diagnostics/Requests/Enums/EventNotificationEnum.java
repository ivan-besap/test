package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums;

/**
 * Especifica el tipo de notificación de evento en el mensaje.
 *
 * <p>Representa las diferentes categorías de notificaciones relacionadas con eventos en el sistema.
 */
public enum EventNotificationEnum {
    /** Notificación generada por hardware. */
    HardWiredNotification,

    /** Monitorización basada en hardware. */
    HardWiredMonitor,

    /** Monitorización preconfigurada. */
    PreconfiguredMonitor,

    /** Monitorización personalizada. */
    CustomMonitor
}
