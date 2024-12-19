package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.CertificateSignedResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Confirmation.InstallCertificateResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.CertificateSignedRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests.InstallCertificateRequest;

/**
 * Interfaz para manejar eventos relacionados con la seguridad en el cliente.
 * <p>
 * Esta interfaz define métodos para gestionar solicitudes relacionadas con certificados,
 * como la firma e instalación de certificados en estaciones de carga.
 */
public interface ClientSecurityEventHandler {

    /**
     * Maneja una solicitud de firma de certificado.
     * <p>
     * Este método procesa una solicitud {@link CertificateSignedRequest} para firmar un
     * certificado y devuelve una respuesta {@link CertificateSignedResponse}.
     *
     * @param request La solicitud de firma de certificado entrante.
     * @return La respuesta a la solicitud de firma de certificado.
     */
    CertificateSignedResponse handleCertificateSignedRequest(CertificateSignedRequest request);

    /**
     * Maneja una solicitud de instalación de certificado.
     * <p>
     * Este método procesa una solicitud {@link InstallCertificateRequest} para instalar
     * un certificado en la estación de carga y devuelve una respuesta
     * {@link InstallCertificateResponse}.
     *
     * @param request La solicitud de instalación de certificado entrante.
     * @return La respuesta a la solicitud de instalación de certificado.
     */
    InstallCertificateResponse handleInstallCertificateRequest(InstallCertificateRequest request);
}
