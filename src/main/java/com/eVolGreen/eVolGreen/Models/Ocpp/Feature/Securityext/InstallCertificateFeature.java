package com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Securityext;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.ProfileFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Confirmations.InstallCertificateConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request.InstallCertificateRequest;

/**
 * La clase {@code InstallCertificateFeature} implementa la funcionalidad para manejar solicitudes
 * de instalación de certificados en el sistema de carga.
 *
 * <p>Esta característica permite la instalación de certificados X.509 en el sistema, lo que puede
 * incluir certificados de seguridad para la comunicación segura entre el punto de carga y el sistema central.</p>
 *
 * <pre>
 * Ejemplo de uso:
 *
 * // Crear el perfil y la característica
 * Profile ownerProfile = new ServerSecurityExtProfile(eventHandler);
 * InstallCertificateFeature feature = new InstallCertificateFeature(ownerProfile);
 *
 * // Crear una solicitud para instalar un certificado
 * InstallCertificateRequest request = new InstallCertificateRequest(CertificateUseEnumType.CentralSystemRootCertificate, "MIICIjANBgkqhki...");
 *
 * // Manejar la solicitud
 * InstallCertificateConfirmation confirmation = (InstallCertificateConfirmation) feature.getOwnerProfile()
 *      .handleRequest(sessionId, request);
 * </pre>
 */
public class InstallCertificateFeature extends ProfileFeature {

    /**
     * Constructor para inicializar la característica "InstallCertificate".
     *
     * @param ownerProfile El perfil al que pertenece esta característica.
     */
    public InstallCertificateFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la clase {@link InstallCertificateRequest} que representa el tipo de solicitud
     * que maneja esta característica.</p>
     *
     * @return La clase del tipo de solicitud {@code InstallCertificateRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return InstallCertificateRequest.class;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la clase {@link InstallCertificateConfirmation} que representa el tipo de confirmación
     * que maneja esta característica.</p>
     *
     * @return La clase del tipo de confirmación {@code InstallCertificateConfirmation}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return InstallCertificateConfirmation.class;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la acción asociada con esta característica. En este caso, la acción es "InstallCertificate".</p>
     *
     * @return La acción de esta característica, que es "InstallCertificate".
     */
    @Override
    public String getAction() {
        return "InstallCertificate";
    }
}
