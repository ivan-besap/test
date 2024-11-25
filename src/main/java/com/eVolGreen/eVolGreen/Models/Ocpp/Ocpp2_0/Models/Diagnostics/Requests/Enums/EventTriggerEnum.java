package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums;

/**
 * Tipo de monitor que activó este evento.
 *
 * <p>Representa los diferentes tipos de activadores que pueden generar un evento, como superar un
 * valor umbral, un cambio en el valor (delta), o un monitoreo periódico.
 */
public enum EventTriggerEnum {
    /** Se activó una alerta. */
    Alerting,

    /** Se activó debido a un cambio en el valor (delta). */
    Delta,

    /** Se activó de forma periódica. */
    Periodic
}
