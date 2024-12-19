package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Securityext;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.ProfileFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.SignedFirmwareStatusNotificationConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.SignedFirmwareStatusNotificationRequest;

/**
 * La clase {@code SignedFirmwareStatusNotificationFeature} maneja las notificaciones relacionadas con el estado
 * de la actualización de firmware firmado en el sistema de carga.
 *
 * <p>Esta característica se utiliza para reportar el estado de la actualización de firmware que ha sido firmado
 * y verificar si la operación fue exitosa, fallida o está en progreso.</p>
 *
 * <pre>
 * Ejemplo de uso:
 *
 * // Crear el perfil y la característica
 * Profile ownerProfile = new ClientSecurityExtProfile(eventHandler);
 * SignedFirmwareStatusNotificationFeature feature = new SignedFirmwareStatusNotificationFeature(ownerProfile);
 *
 * // Crear una solicitud de notificación de estado de firmware firmado
 * SignedFirmwareStatusNotificationRequest request = new SignedFirmwareStatusNotificationRequest(FirmwareStatusEnumType.Installed);
 *
 * // Manejar la solicitud
 * SignedFirmwareStatusNotificationConfirmation confirmation = (SignedFirmwareStatusNotificationConfirmation) feature
 *      .getOwnerProfile().handleRequest(sessionId, request);
 * </pre>
 */
public class SignedFirmwareStatusNotificationFeature extends ProfileFeature {

    /**
     * Constructor para inicializar la característica "SignedFirmwareStatusNotification".
     *
     * @param ownerProfile El perfil al que pertenece esta característica.
     */
    public SignedFirmwareStatusNotificationFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la clase {@link SignedFirmwareStatusNotificationRequest} que representa el tipo de solicitud
     * que maneja esta característica.</p>
     *
     * @return La clase del tipo de solicitud {@code SignedFirmwareStatusNotificationRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return SignedFirmwareStatusNotificationRequest.class;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la clase {@link SignedFirmwareStatusNotificationConfirmation} que representa el tipo de confirmación
     * que maneja esta característica.</p>
     *
     * @return La clase del tipo de confirmación {@code SignedFirmwareStatusNotificationConfirmation}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SignedFirmwareStatusNotificationConfirmation.class;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la acción asociada con esta característica. En este caso, la acción es "SignedFirmwareStatusNotification".</p>
     *
     * @return La acción de esta característica, que es "SignedFirmwareStatusNotification".
     */
    @Override
    public String getAction() {
        return "SignedFirmwareStatusNotification";
    }
}
