package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Confirmations.DiagnosticsStatusNotificationConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Firmware.Request.DiagnosticsStatusNotificationRequest;


/**
 * La clase {@code DiagnosticsStatusNotificationFeature} gestiona la notificación del estado de diagnóstico
 * en el protocolo OCPP. Este feature permite a la estación de carga informar al sistema central sobre
 * el estado de un informe de diagnóstico.
 * <p>
 * Es crucial para la monitorización y mantenimiento de las estaciones de carga, asegurando que el
 * sistema central reciba notificaciones de finalización, errores o estado actual del diagnóstico.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     DiagnosticsStatusNotificationFeature feature = new DiagnosticsStatusNotificationFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = feature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = feature.getConfirmationType();}
 *     String action = feature.getAction();
 * </pre>
 */
public class DiagnosticsStatusNotificationFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de notificación de estado de diagnóstico con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public DiagnosticsStatusNotificationFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para la notificación de estado de diagnóstico.
     *
     * @return la clase {@link DiagnosticsStatusNotificationRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return DiagnosticsStatusNotificationRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para la notificación de estado de diagnóstico.
     *
     * @return la clase {@link DiagnosticsStatusNotificationConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return DiagnosticsStatusNotificationConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de notificación de estado de diagnóstico.
     * En este caso, la acción es "DiagnosticsStatusNotification".
     *
     * @return una cadena de texto con el nombre de la acción: "DiagnosticsStatusNotification".
     */
    @Override
    public String getAction() {
        return "DiagnosticsStatusNotification";
    }
}
