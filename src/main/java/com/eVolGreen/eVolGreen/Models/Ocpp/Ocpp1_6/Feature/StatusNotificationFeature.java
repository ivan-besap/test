package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.StatusNotificationConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.StatusNotificationRequest;

/**
 * La clase {@code StatusNotificationFeature} gestiona la funcionalidad de notificación de estado
 * en el protocolo OCPP. Este feature permite a la estación de carga notificar al sistema central
 * sobre cambios en su estado, como disponibilidad, fallos o advertencias.
 * <p>
 * Esta característica es crucial para asegurar la comunicación efectiva del estado de los
 * dispositivos de carga, facilitando la gestión y el monitoreo de los puntos de carga.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     StatusNotificationFeature statusNotificationFeature = new StatusNotificationFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = statusNotificationFeature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = statusNotificationFeature.getConfirmationType();}
 *     String action = statusNotificationFeature.getAction();
 * </pre>
 */
public class StatusNotificationFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de notificación de estado con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public StatusNotificationFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para la notificación de estado.
     *
     * @return la clase {@link StatusNotificationRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return StatusNotificationRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para la notificación de estado.
     *
     * @return la clase {@link StatusNotificationConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return StatusNotificationConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de notificación de estado.
     * En este caso, la acción es "StatusNotification".
     *
     * @return una cadena de texto con el nombre de la acción: "StatusNotification".
     */
    @Override
    public String getAction() {
        return "StatusNotification";
    }
}
