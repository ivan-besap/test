package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Securityext;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.ProfileFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.SignCertificateConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.SignCertificateRequest;

/**
 * La clase {@code SignCertificateFeature} implementa la funcionalidad para manejar solicitudes
 * de firma de certificados en el sistema de carga.
 *
 * <p>Esta característica permite a un punto de carga solicitar al sistema central la firma de un certificado,
 * lo que asegura que el certificado es válido y puede ser utilizado en comunicaciones seguras.</p>
 *
 * <pre>
 * Ejemplo de uso:
 *
 * // Crear el perfil y la característica
 * Profile ownerProfile = new ClientSecurityExtProfile(eventHandler);
 * SignCertificateFeature feature = new SignCertificateFeature(ownerProfile);
 *
 * // Crear una solicitud de firma de certificado
 * SignCertificateRequest request = new SignCertificateRequest("MIICIjANBgkqhki...");
 *
 * // Manejar la solicitud
 * SignCertificateConfirmation confirmation = (SignCertificateConfirmation) feature.getOwnerProfile()
 *      .handleRequest(sessionId, request);
 * </pre>
 */
public class SignCertificateFeature extends ProfileFeature {

    /**
     * Constructor para inicializar la característica "SignCertificate".
     *
     * @param ownerProfile El perfil al que pertenece esta característica.
     */
    public SignCertificateFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la clase {@link SignCertificateRequest} que representa el tipo de solicitud
     * que maneja esta característica.</p>
     *
     * @return La clase del tipo de solicitud {@code SignCertificateRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return SignCertificateRequest.class;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la clase {@link SignCertificateConfirmation} que representa el tipo de confirmación
     * que maneja esta característica.</p>
     *
     * @return La clase del tipo de confirmación {@code SignCertificateConfirmation}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SignCertificateConfirmation.class;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la acción asociada con esta característica. En este caso, la acción es "SignCertificate".</p>
     *
     * @return La acción de esta característica, que es "SignCertificate".
     */
    @Override
    public String getAction() {
        return "SignCertificate";
    }
}
