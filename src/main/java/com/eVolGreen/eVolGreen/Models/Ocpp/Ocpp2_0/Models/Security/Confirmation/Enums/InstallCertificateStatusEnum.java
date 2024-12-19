package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.Enums;

/**
 * Enumeración que indica el estado de la instalación del certificado.
 */
public enum InstallCertificateStatusEnum {
    /** La instalación del certificado fue aceptada y completada con éxito. */
    Accepted,

    /** La instalación del certificado fue rechazada. */
    Rejected,

    /** La instalación del certificado falló debido a un error. */
    Failed
}
