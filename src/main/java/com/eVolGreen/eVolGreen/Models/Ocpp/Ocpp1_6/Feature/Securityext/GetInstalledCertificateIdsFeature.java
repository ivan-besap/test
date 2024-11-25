package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Securityext;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.ProfileFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.GetInstalledCertificateIdsConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.GetInstalledCertificateIdsRequest;

/**
 * La clase {@code GetInstalledCertificateIdsFeature} implementa la funcionalidad para manejar solicitudes
 * y confirmaciones relacionadas con la obtención de los identificadores de certificados instalados.
 *
 * <p>Esta característica es utilizada para consultar los tipos y números de certificados que están actualmente
 * instalados en el sistema de carga.</p>
 *
 * <pre>
 * Ejemplo de uso:
 *
 * // Crear el perfil y la característica
 * Profile ownerProfile = new ServerSecurityExtProfile(eventHandler);
 * GetInstalledCertificateIdsFeature feature = new GetInstalledCertificateIdsFeature(ownerProfile);
 *
 * // Crear una solicitud de IDs de certificados instalados
 * GetInstalledCertificateIdsRequest request = new GetInstalledCertificateIdsRequest(CertificateUseEnumType.CentralSystemRootCertificate);
 *
 * // Manejar la solicitud
 * GetInstalledCertificateIdsConfirmation confirmation = (GetInstalledCertificateIdsConfirmation) feature.getOwnerProfile()
 *      .handleRequest(sessionId, request);
 * </pre>
 */
public class GetInstalledCertificateIdsFeature extends ProfileFeature {

    /**
     * Constructor para inicializar la característica "GetInstalledCertificateIds".
     *
     * @param ownerProfile El perfil al que pertenece esta característica.
     */
    public GetInstalledCertificateIdsFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la clase {@link GetInstalledCertificateIdsRequest} que representa el tipo de solicitud
     * que maneja esta característica.</p>
     *
     * @return La clase del tipo de solicitud {@code GetInstalledCertificateIdsRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return GetInstalledCertificateIdsRequest.class;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la clase {@link GetInstalledCertificateIdsConfirmation} que representa el tipo de confirmación
     * que maneja esta característica.</p>
     *
     * @return La clase del tipo de confirmación {@code GetInstalledCertificateIdsConfirmation}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetInstalledCertificateIdsConfirmation.class;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la acción asociada con esta característica. En este caso, la acción es "GetInstalledCertificateIds".</p>
     *
     * @return La acción de esta característica, que es "GetInstalledCertificateIds".
     */
    @Override
    public String getAction() {
        return "GetInstalledCertificateIds";
    }
}
