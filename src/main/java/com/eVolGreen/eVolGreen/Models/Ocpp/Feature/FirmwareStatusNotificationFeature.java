package com.eVolGreen.eVolGreen.Models.Ocpp.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware.FirmwareStatusNotificationConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware.FirmwareStatusNotificationRequest;


/**
 * La clase {@code FirmwareStatusNotificationFeature} gestiona las notificaciones del estado del firmware
 * en el protocolo OCPP. Este feature permite que la estación de carga informe al sistema central sobre
 * el estado de una actualización de firmware.
 * <p>
 * Las actualizaciones de firmware son esenciales para garantizar la estabilidad y la seguridad de las estaciones de carga.
 * Este feature asegura que el sistema central esté informado sobre el éxito o fracaso de dichas actualizaciones.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     FirmwareStatusNotificationFeature feature = new FirmwareStatusNotificationFeature(perfilPropietario);
 *     Class<? extends Request> requestType = feature.getRequestType();
 *     Class<? extends Confirmation> confirmationType = feature.getConfirmationType();
 *     String action = feature.getAction();
 * </pre>
 */
public class FirmwareStatusNotificationFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de notificación de estado del firmware con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public FirmwareStatusNotificationFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para la notificación de estado del firmware.
     *
     * @return la clase {@link FirmwareStatusNotificationRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return FirmwareStatusNotificationRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para la notificación de estado del firmware.
     *
     * @return la clase {@link FirmwareStatusNotificationConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return FirmwareStatusNotificationConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de notificación de estado del firmware.
     * En este caso, la acción es "FirmwareStatusNotification".
     *
     * @return una cadena de texto con el nombre de la acción: "FirmwareStatusNotification".
     */
    @Override
    public String getAction() {
        return "FirmwareStatusNotification";
    }
}
