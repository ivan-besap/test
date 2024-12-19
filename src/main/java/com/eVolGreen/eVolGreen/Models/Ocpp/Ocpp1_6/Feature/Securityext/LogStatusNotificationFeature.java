package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Securityext;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.ProfileFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.LogStatusNotificationConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.LogStatusNotificationRequest;

/**
 * La clase {@code LogStatusNotificationFeature} implementa la funcionalidad para manejar
 * notificaciones sobre el estado del registro de logs en el sistema de carga.
 *
 * <p>Esta característica permite que un punto de carga notifique al sistema central sobre
 * el estado actual del proceso de registro de logs, como la carga o el fallo del envío de logs.</p>
 *
 * <pre>
 * Ejemplo de uso:
 *
 * // Crear el perfil y la característica
 * Profile ownerProfile = new ServerSecurityExtProfile(eventHandler);
 * LogStatusNotificationFeature feature = new LogStatusNotificationFeature(ownerProfile);
 *
 * // Crear una solicitud de notificación de estado de logs
 * LogStatusNotificationRequest request = new LogStatusNotificationRequest(UploadLogStatusEnumType.Uploading);
 *
 * // Manejar la solicitud
 * LogStatusNotificationConfirmation confirmation = (LogStatusNotificationConfirmation) feature.getOwnerProfile()
 *      .handleRequest(sessionId, request);
 * </pre>
 */
public class LogStatusNotificationFeature extends ProfileFeature {

    /**
     * Constructor para inicializar la característica "LogStatusNotification".
     *
     * @param ownerProfile El perfil al que pertenece esta característica.
     */
    public LogStatusNotificationFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la clase {@link LogStatusNotificationRequest} que representa el tipo de solicitud
     * que maneja esta característica.</p>
     *
     * @return La clase del tipo de solicitud {@code LogStatusNotificationRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return LogStatusNotificationRequest.class;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la clase {@link LogStatusNotificationConfirmation} que representa el tipo de confirmación
     * que maneja esta característica.</p>
     *
     * @return La clase del tipo de confirmación {@code LogStatusNotificationConfirmation}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return LogStatusNotificationConfirmation.class;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la acción asociada con esta característica. En este caso, la acción es "LogStatusNotification".</p>
     *
     * @return La acción de esta característica, que es "LogStatusNotification".
     */
    @Override
    public String getAction() {
        return "LogStatusNotification";
    }
}
