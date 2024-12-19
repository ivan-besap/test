package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.RemoteTrigger.Request.Enums;

/**
 * Tipo de mensaje que se debe disparar.
 *
 * <p>Enumeración que representa los diferentes tipos de mensajes que pueden ser disparados desde
 * el sistema de gestión (CSMS) a la estación de carga en el contexto de OCPP 2.0.1.
 */
public enum MessageTriggerEnum {

    /** Notificación de arranque (BootNotification). */
    BootNotification,

    /** Notificación del estado de los registros (LogStatusNotification). */
    LogStatusNotification,

    /** Notificación del estado del firmware (FirmwareStatusNotification). */
    FirmwareStatusNotification,

    /** Notificación periódica del estado (Heartbeat). */
    Heartbeat,

    /** Notificación de valores del medidor (MeterValues). */
    MeterValues,

    /** Solicitud de firma del certificado de la estación de carga (SignChargingStationCertificate). */
    SignChargingStationCertificate,

    /** Solicitud de firma del certificado V2G (SignV2GCertificate). */
    SignV2GCertificate,

    /** Notificación del estado del conector o estación (StatusNotification). */
    StatusNotification,

    /** Evento de transacción (TransactionEvent). */
    TransactionEvent,

    /** Solicitud de firma de certificado combinado (SignCombinedCertificate). */
    SignCombinedCertificate,

    /** Notificación del estado de la publicación del firmware (PublishFirmwareStatusNotification). */
    PublishFirmwareStatusNotification
}
