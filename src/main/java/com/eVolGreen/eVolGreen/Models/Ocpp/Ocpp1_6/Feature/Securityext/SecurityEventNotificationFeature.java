package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Securityext;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.ProfileFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.SecurityEventNotificationConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.SecurityEventNotificationRequest;

/**
 * La clase {@code SecurityEventNotificationFeature} implementa la funcionalidad para manejar
 * notificaciones de eventos de seguridad en el sistema de carga.
 *
 * <p>Esta característica permite que un punto de carga notifique al sistema central sobre
 * eventos de seguridad, tales como intentos de acceso no autorizados o eventos críticos del sistema.</p>
 *
 * <pre>
 * Ejemplo de uso:
 *
 * // Crear el perfil y la característica
 * Profile ownerProfile = new ServerSecurityExtProfile(eventHandler);
 * SecurityEventNotificationFeature feature = new SecurityEventNotificationFeature(ownerProfile);
 *
 * // Crear una solicitud de notificación de evento de seguridad
 * SecurityEventNotificationRequest request = new SecurityEventNotificationRequest("AccessDenied", ZonedDateTime.now());
 *
 * // Manejar la solicitud
 * SecurityEventNotificationConfirmation confirmation = (SecurityEventNotificationConfirmation) feature.getOwnerProfile()
 *      .handleRequest(sessionId, request);
 * </pre>
 */
public class SecurityEventNotificationFeature extends ProfileFeature {

    /**
     * Constructor para inicializar la característica "SecurityEventNotification".
     *
     * @param ownerProfile El perfil al que pertenece esta característica.
     */
    public SecurityEventNotificationFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la clase {@link SecurityEventNotificationRequest} que representa el tipo de solicitud
     * que maneja esta característica.</p>
     *
     * @return La clase del tipo de solicitud {@code SecurityEventNotificationRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return SecurityEventNotificationRequest.class;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la clase {@link SecurityEventNotificationConfirmation} que representa el tipo de confirmación
     * que maneja esta característica.</p>
     *
     * @return La clase del tipo de confirmación {@code SecurityEventNotificationConfirmation}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SecurityEventNotificationConfirmation.class;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la acción asociada con esta característica. En este caso, la acción es "SecurityEventNotification".</p>
     *
     * @return La acción de esta característica, que es "SecurityEventNotification".
     */
    @Override
    public String getAction() {
        return "SecurityEventNotification";
    }
}
