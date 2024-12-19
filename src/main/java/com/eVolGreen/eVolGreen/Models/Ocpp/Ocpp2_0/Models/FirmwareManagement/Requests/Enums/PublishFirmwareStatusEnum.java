package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests.Enums;

/**
 * Representa los estados de progreso de la instalación de la publicación del firmware.
 * Este enum indica en qué etapa del proceso de publicación del firmware se encuentra el sistema.
 */
public enum PublishFirmwareStatusEnum {

    /** Sin actividad en progreso relacionada con la publicación del firmware. */
    Idle,

    /** La descarga del firmware está programada. */
    DownloadScheduled,

    /** El firmware se está descargando actualmente. */
    Downloading,

    /** El firmware se ha descargado con éxito. */
    Downloaded,

    /** El firmware se ha publicado exitosamente. */
    Published,

    /** La descarga del firmware falló. */
    DownloadFailed,

    /** La descarga del firmware está pausada. */
    DownloadPaused,

    /** El checksum del firmware es inválido. */
    InvalidChecksum,

    /** El checksum del firmware ha sido verificado correctamente. */
    ChecksumVerified,

    /** La publicación del firmware falló. */
    PublishFailed;
}
