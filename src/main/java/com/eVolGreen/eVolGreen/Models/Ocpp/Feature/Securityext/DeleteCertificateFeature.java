package com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Securityext;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.ProfileFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Confirmations.DeleteCertificateConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request.DeleteCertificateRequest;

/**
 * La clase {@code DeleteCertificateFeature} implementa la característica para manejar solicitudes y confirmaciones
 * relacionadas con la eliminación de certificados en el contexto de la seguridad extendida OCPP.
 *
 * <p>Esta característica se utiliza para gestionar la acción "DeleteCertificate", que permite a los sistemas
 * eliminar un certificado instalado en una estación de carga.</p>
 *
 * <pre>
 * Ejemplo de uso:
 *
 * // Crear el perfil y la característica
 * Profile ownerProfile = new ClientSecurityExtProfile(eventHandler);
 * DeleteCertificateFeature feature = new DeleteCertificateFeature(ownerProfile);
 *
 * // Crear una solicitud de eliminación de certificado
 * DeleteCertificateRequest request = new DeleteCertificateRequest(certificateHashData);
 *
 * // Manejar la solicitud
 * DeleteCertificateConfirmation confirmation = (DeleteCertificateConfirmation) feature.getOwnerProfile()
 *      .handleRequest(sessionId, request);
 * </pre>
 */
public class DeleteCertificateFeature extends ProfileFeature {

    /**
     * Constructor para inicializar la característica "DeleteCertificate".
     *
     * @param ownerProfile El perfil al que pertenece esta característica.
     */
    public DeleteCertificateFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la clase {@link DeleteCertificateRequest} que representa el tipo de solicitud
     * que maneja esta característica.</p>
     *
     * @return La clase del tipo de solicitud {@code DeleteCertificateRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return DeleteCertificateRequest.class;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la clase {@link DeleteCertificateConfirmation} que representa el tipo de confirmación
     * que maneja esta característica.</p>
     *
     * @return La clase del tipo de confirmación {@code DeleteCertificateConfirmation}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return DeleteCertificateConfirmation.class;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la acción asociada con esta característica. En este caso, la acción es "DeleteCertificate".</p>
     *
     * @return La acción de esta característica, que es "DeleteCertificate".
     */
    @Override
    public String getAction() {
        return "DeleteCertificate";
    }
}
