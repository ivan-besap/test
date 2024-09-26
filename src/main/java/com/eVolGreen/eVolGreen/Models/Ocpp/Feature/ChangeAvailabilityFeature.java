package com.eVolGreen.eVolGreen.Models.Ocpp.Feature;


import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.ChangeAvailabilityConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.ChangeAvailabilityRequest;

/**
 * La clase {@code ChangeAvailabilityFeature} gestiona la funcionalidad de cambiar la disponibilidad
 * de un punto de carga dentro del protocolo OCPP. Esto permite a una estación de carga o backend
 * ajustar la disponibilidad de los puntos de carga, marcándolos como disponibles o no disponibles.
 * <p>
 * Este feature es crucial para la gestión de la disponibilidad de las estaciones de carga,
 * optimizando el uso y la planificación de la red de cargadores.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     ChangeAvailabilityFeature changeAvailabilityFeature = new ChangeAvailabilityFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = changeAvailabilityFeature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = changeAvailabilityFeature.getConfirmationType();}
 *     String action = changeAvailabilityFeature.getAction();
 * </pre>
 */
public class ChangeAvailabilityFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de cambio de disponibilidad con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public ChangeAvailabilityFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para cambiar la disponibilidad.
     *
     * @return la clase {@link ChangeAvailabilityRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return ChangeAvailabilityRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para cambiar la disponibilidad.
     *
     * @return la clase {@link ChangeAvailabilityConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ChangeAvailabilityConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de cambio de disponibilidad.
     * En este caso, la acción es "ChangeAvailability".
     *
     * @return una cadena de texto con el nombre de la acción: "ChangeAvailability".
     */
    @Override
    public String getAction() {
        return "ChangeAvailability";
    }
}
