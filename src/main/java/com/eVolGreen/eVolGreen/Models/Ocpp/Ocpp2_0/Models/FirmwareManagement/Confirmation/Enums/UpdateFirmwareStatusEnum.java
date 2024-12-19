package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Confirmation.Enums;

/**
 * UpdateFirmwareStatusEnum
 *
 * <p>Enumeración que indica si la estación de carga pudo aceptar la solicitud de actualización de firmware.
 */
public enum UpdateFirmwareStatusEnum {

    /** La solicitud de actualización fue aceptada. */
    Accepted,

    /** La solicitud de actualización fue rechazada. */
    Rejected,

    /** La solicitud fue inicialmente aceptada pero posteriormente cancelada. */
    AcceptedCanceled,

    /** La solicitud fue rechazada debido a un certificado inválido. */
    InvalidCertificate,

    /** La solicitud fue rechazada debido a un certificado revocado. */
    RevokedCertificate
}
