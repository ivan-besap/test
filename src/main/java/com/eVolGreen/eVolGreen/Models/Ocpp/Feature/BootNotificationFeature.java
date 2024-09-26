package com.eVolGreen.eVolGreen.Models.Ocpp.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.BootNotificationConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.BootNotificationRequest;


/**
 * La clase {@code BootNotificationFeature} maneja la funcionalidad relacionada con la notificación
 * de arranque en el protocolo OCPP, permitiendo a una estación de carga enviar una solicitud de arranque
 * cuando se enciende y recibir una confirmación del servidor.
 * <p>
 * Este feature es crucial para que la estación de carga informe su estado al sistema de backend al iniciar,
 * lo cual incluye detalles como su modelo, fabricante, y versión de firmware.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     BootNotificationFeature bootNotificationFeature = new BootNotificationFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = bootNotificationFeature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = bootNotificationFeature.getConfirmationType();}
 *     String action = bootNotificationFeature.getAction();
 * </pre>
 */
public class BootNotificationFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de notificación de arranque con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public BootNotificationFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para la funcionalidad de notificación de arranque.
     *
     * @return la clase {@link BootNotificationRequest} correspondiente al tipo de solicitud de notificación de arranque.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return BootNotificationRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para la funcionalidad de notificación de arranque.
     *
     * @return la clase {@link BootNotificationConfirmation} correspondiente al tipo de confirmación de notificación de arranque.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return BootNotificationConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de notificación de arranque.
     * En este caso, la acción es "BootNotification".
     *
     * @return una cadena de texto con el nombre de la acción: "BootNotification".
     */
    @Override
    public String getAction() {
        return "BootNotification";
    }
}
