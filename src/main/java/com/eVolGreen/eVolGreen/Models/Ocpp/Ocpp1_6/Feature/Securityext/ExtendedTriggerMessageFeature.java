package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Securityext;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.ProfileFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.ExtendedTriggerMessageConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.ExtendedTriggerMessageRequest;

/**
 * La clase {@code ExtendedTriggerMessageFeature} implementa la funcionalidad para manejar solicitudes y confirmaciones
 * relacionadas con la solicitud extendida de mensajes en el contexto de la seguridad extendida OCPP.
 *
 * <p>Esta característica permite a los sistemas solicitar de manera explícita el envío de ciertos tipos de mensajes
 * a través del protocolo OCPP, como las notificaciones de estado, valores de medidor, etc.</p>
 *
 * <pre>
 * Ejemplo de uso:
 *
 * // Crear el perfil y la característica
 * Profile ownerProfile = new ClientSecurityExtProfile(eventHandler);
 * ExtendedTriggerMessageFeature feature = new ExtendedTriggerMessageFeature(ownerProfile);
 *
 * // Crear una solicitud de mensaje extendido
 * ExtendedTriggerMessageRequest request = new ExtendedTriggerMessageRequest(MessageTriggerEnumType.BootNotification);
 *
 * // Manejar la solicitud
 * ExtendedTriggerMessageConfirmation confirmation = (ExtendedTriggerMessageConfirmation) feature.getOwnerProfile()
 *      .handleRequest(sessionId, request);
 * </pre>
 */
public class ExtendedTriggerMessageFeature extends ProfileFeature {

    /**
     * Constructor para inicializar la característica "ExtendedTriggerMessage".
     *
     * @param ownerProfile El perfil al que pertenece esta característica.
     */
    public ExtendedTriggerMessageFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la clase {@link ExtendedTriggerMessageRequest} que representa el tipo de solicitud
     * que maneja esta característica.</p>
     *
     * @return La clase del tipo de solicitud {@code ExtendedTriggerMessageRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return ExtendedTriggerMessageRequest.class;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la clase {@link ExtendedTriggerMessageConfirmation} que representa el tipo de confirmación
     * que maneja esta característica.</p>
     *
     * @return La clase del tipo de confirmación {@code ExtendedTriggerMessageConfirmation}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ExtendedTriggerMessageConfirmation.class;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Devuelve la acción asociada con esta característica. En este caso, la acción es "ExtendedTriggerMessage".</p>
     *
     * @return La acción de esta característica, que es "ExtendedTriggerMessage".
     */
    @Override
    public String getAction() {
        return "ExtendedTriggerMessage";
    }
}
