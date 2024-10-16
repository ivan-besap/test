package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Confirmations.Types;


import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Confirmations.InstallCertificateConfirmation;

/**
 * Enumeración que define los posibles estados de la instalación de un certificado en el sistema OCPP.
 *
 * <p>Esta enumeración es utilizada por {@link InstallCertificateConfirmation} para indicar el resultado de la
 * instalación de un certificado en la estación de carga. Los estados incluyen la aceptación, fallo por razones
 * diversas o rechazo debido a errores en el certificado.</p>
 */
public enum CertificateStatusEnumType {

    /**
     * La instalación del certificado fue exitosa.
     */
    Accepted,

    /**
     * El certificado es válido, pero la instalación falló por otras razones.
     */
    Failed,

    /**
     * El certificado es inválido o incorrecto, o se intentó instalar más certificados de los permitidos.
     */
    Rejected
}
