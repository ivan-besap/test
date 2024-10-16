package com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Securityext;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.ProfileFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Confirmations.SignedUpdateFirmwareConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request.SignedUpdateFirmwareRequest;

/**
 * La clase {@code SignedUpdateFirmwareFeature} maneja las solicitudes relacionadas con la actualización de firmware firmado.
 *
 * <p>Este proceso asegura que el firmware se actualiza correctamente utilizando un certificado de firma digital,
 * y reporta el éxito o fallo de la operación.</p>
 *
 * <pre>
 * Ejemplo de uso:
 *
 * // Crear el perfil y la característica
 * Profile ownerProfile = new ServerSecurityExtProfile(eventHandler);
 * SignedUpdateFirmwareFeature feature = new SignedUpdateFirmwareFeature(ownerProfile);
 *
 * // Crear una solicitud de actualización de firmware firmado
 * SignedUpdateFirmwareRequest request = new SignedUpdateFirmwareRequest(12345, firmwareType);
 *
 * // Manejar la solicitud
 * SignedUpdateFirmwareConfirmation confirmation = (SignedUpdateFirmwareConfirmation) feature
 *      .getOwnerProfile().handleRequest(sessionId, request);
 * </pre>
 */
public class SignedUpdateFirmwareFeature extends ProfileFeature {

    /**
     * Constructor para inicializar la característica "SignedUpdateFirmware".
     *
     * @param ownerProfile El perfil al que pertenece esta característica.
     */
    public SignedUpdateFirmwareFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la clase {@link SignedUpdateFirmwareRequest} que representa el tipo de solicitud que maneja esta característica.</p>
     *
     * @return La clase del tipo de solicitud {@code SignedUpdateFirmwareRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return SignedUpdateFirmwareRequest.class;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la clase {@link SignedUpdateFirmwareConfirmation} que representa el tipo de confirmación que maneja esta característica.</p>
     *
     * @return La clase del tipo de confirmación {@code SignedUpdateFirmwareConfirmation}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SignedUpdateFirmwareConfirmation.class;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la acción asociada con esta característica. En este caso, la acción es "SignedUpdateFirmware".</p>
     *
     * @return La acción de esta característica, que es "SignedUpdateFirmware".
     */
    @Override
    public String getAction() {
        return "SignedUpdateFirmware";
    }
}
