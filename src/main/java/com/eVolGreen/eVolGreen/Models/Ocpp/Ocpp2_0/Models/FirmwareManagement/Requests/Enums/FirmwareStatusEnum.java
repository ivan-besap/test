package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests.Enums;

/**
 * Representa los diferentes estados de progreso de la instalación del firmware en una estación de carga.
 * Este enumerador se utiliza para describir el estado actual del proceso de instalación o actualización.
 */
public enum FirmwareStatusEnum {

    /**
     * El firmware se ha descargado correctamente y está listo para ser instalado.
     */
    Downloaded,

    /**
     * Falló la descarga del firmware debido a un error.
     */
    DownloadFailed,

    /**
     * La descarga del firmware está en progreso.
     */
    Downloading,

    /**
     * La descarga del firmware ha sido programada y está pendiente.
     */
    DownloadScheduled,

    /**
     * La descarga del firmware está en pausa.
     */
    DownloadPaused,

    /**
     * No hay actualizaciones de firmware en progreso; el sistema está en estado inactivo.
     */
    Idle,

    /**
     * Falló la instalación del firmware debido a un error.
     */
    InstallationFailed,

    /**
     * La instalación del firmware está en progreso.
     */
    Installing,

    /**
     * El firmware se instaló correctamente.
     */
    Installed,

    /**
     * El sistema se está reiniciando después de una instalación exitosa del firmware.
     */
    InstallRebooting,

    /**
     * La instalación del firmware ha sido programada y está pendiente.
     */
    InstallScheduled,

    /**
     * Falló la verificación del firmware después de la instalación.
     */
    InstallVerificationFailed,

    /**
     * La firma del firmware es inválida o no se pudo verificar.
     */
    InvalidSignature,

    /**
     * La firma del firmware se ha verificado correctamente.
     */
    SignatureVerified
}
