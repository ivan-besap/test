package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.Types;


import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.GetInstalledCertificateIdsRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.InstallCertificateRequest;

/**
 * Enumeración que define el uso del certificado en el sistema OCPP de eVolGreen.
 *
 * <p>Esta enumeración se utiliza en {@link GetInstalledCertificateIdsRequest} y
 * {@link InstallCertificateRequest} para especificar el tipo de certificado
 * que se está manejando en el proceso de instalación o verificación.</p>
 */
public enum CertificateUseEnumType {

    /**
     * Certificado raíz, utilizado por la Autoridad Certificadora (CA) para firmar los certificados del
     * sistema central y el punto de carga.
     */
    CentralSystemRootCertificate,

    /**
     * Certificado raíz para la verificación del certificado del fabricante.
     */
    ManufacturerRootCertificate
}
