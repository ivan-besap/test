package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.Enums;

/**
 * Enum que indica si un mensaje relacionado con el certificado 15118 EV fue procesado correctamente.
 *
 * <p>Este enum se utiliza para determinar el resultado de operaciones relacionadas con la gestión
 * de certificados 15118 EV, como la instalación o actualización de certificados.</p>
 */
public enum Iso15118EVCertificateStatusEnum {

    /**
     * Indica que el mensaje fue procesado exitosamente.
     */
    Accepted,

    /**
     * Indica que el procesamiento del mensaje falló.
     */
    Failed
}
