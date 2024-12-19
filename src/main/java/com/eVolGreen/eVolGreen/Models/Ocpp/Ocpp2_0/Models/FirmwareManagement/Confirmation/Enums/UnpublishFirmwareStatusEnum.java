package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Confirmation.Enums;

/**
 * Enum UnpublishFirmwareStatusEnum
 *
 * <p>Indica el estado del resultado de la solicitud para despublicar un firmware en el controlador local.
 *
 * <p>OCPP 2.0.1 FINAL
 */
public enum UnpublishFirmwareStatusEnum {
    /**
     * Indica que la despublicación no se pudo realizar porque hay una descarga en curso.
     */
    DownloadOngoing,

    /**
     * Indica que no se encontró un firmware previamente publicado para despublicar.
     */
    NoFirmware,

    /**
     * Indica que el firmware ha sido despublicado con éxito.
     */
    Unpublished
}
