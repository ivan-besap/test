package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums;

/**
 * Enumera los posibles motivos por los cuales se envía un mensaje de notificación de arranque al
 * CSMS (Sistema Central de Gestión de Carga).
 */
public enum BootReasonEnum {

    /** Reinicio de la aplicación. */
    ApplicationReset,

    /** Actualización del firmware. */
    FirmwareUpdate,

    /** Reinicio local de la estación de carga. */
    LocalReset,

    /** Encendido de la estación de carga. */
    PowerUp,

    /** Reinicio remoto iniciado por el CSMS. */
    RemoteReset,

    /** Reinicio programado previamente. */
    ScheduledReset,

    /** Reinicio activado por un evento o comando específico. */
    Triggered,

    /** Motivo desconocido para el arranque. */
    Unknown,

    /** Reinicio provocado por el temporizador de vigilancia (watchdog). */
    Watchdog
}
