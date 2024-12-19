package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Securityext;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.ProfileFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.CertificateSignedConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.CertificateSignedRequest;

/**
 * La clase {@code CertificateSignedFeature} implementa la característica para manejar solicitudes y confirmaciones
 * relacionadas con la firma de certificados en el contexto de la seguridad extendida OCPP.
 *
 * <p>Esta característica se utiliza para gestionar la acción "CertificateSigned", que corresponde a la confirmación
 * de que un certificado ha sido firmado por la entidad correspondiente (como una Autoridad de Certificación, CA).</p>
 *
 * <pre>
 * Ejemplo de uso:
 *
 * // Crear el perfil y la característica
 * Profile ownerProfile = new ClientSecurityExtProfile(eventHandler);
 * CertificateSignedFeature feature = new CertificateSignedFeature(ownerProfile);
 *
 * // Crear una solicitud de firma de certificado
 * CertificateSignedRequest request = new CertificateSignedRequest("MIICIjANBgkqhkiG...");
 *
 * // Manejar la solicitud
 * CertificateSignedConfirmation confirmation = (CertificateSignedConfirmation) feature.getOwnerProfile()
 *      .handleRequest(sessionId, request);
 * </pre>
 */
public class CertificateSignedFeature extends ProfileFeature {

    /**
     * Constructor para inicializar la característica "CertificateSigned".
     *
     * @param ownerProfile El perfil al que pertenece esta característica.
     */
    public CertificateSignedFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la clase {@link CertificateSignedRequest} que representa el tipo de solicitud
     * que maneja esta característica.</p>
     *
     * @return La clase del tipo de solicitud {@code CertificateSignedRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return CertificateSignedRequest.class;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la clase {@link CertificateSignedConfirmation} que representa el tipo de confirmación
     * que maneja esta característica.</p>
     *
     * @return La clase del tipo de confirmación {@code CertificateSignedConfirmation}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return CertificateSignedConfirmation.class;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la acción asociada con esta característica. En este caso, la acción es "CertificateSigned".</p>
     *
     * @return La acción de esta característica, que es "CertificateSigned".
     */
    @Override
    public String getAction() {
        return "CertificateSigned";
    }
}
