package com.eVolGreen.eVolGreen.Models.Ocpp.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Reservation.ReserveNowConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Reservation.ReserveNowRequest;

/**
 * La clase {@code ReserveNowFeature} gestiona la funcionalidad de reservar un punto de carga
 * en el protocolo OCPP. Esto permite a un operador de backend reservar un conector específico en una
 * estación de carga para un usuario en particular.
 * <p>
 * Esta funcionalidad es crucial para garantizar la disponibilidad de cargadores para usuarios
 * específicos en un momento determinado, lo que optimiza la eficiencia de la red de cargadores.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     ReserveNowFeature reserveNowFeature = new ReserveNowFeature(perfilPropietario);
 *     Class<? extends Request> requestType = reserveNowFeature.getRequestType();
 *     Class<? extends Confirmation> confirmationType = reserveNowFeature.getConfirmationType();
 *     String action = reserveNowFeature.getAction();
 * </pre>
 */
public class ReserveNowFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de reserva de puntos de carga con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public ReserveNowFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para reservar un punto de carga.
     *
     * @return la clase {@link ReserveNowRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return ReserveNowRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para la solicitud de reserva de un punto de carga.
     *
     * @return la clase {@link ReserveNowConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ReserveNowConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de reserva de puntos de carga.
     * En este caso, la acción es "ReserveNow".
     *
     * @return una cadena de texto con el nombre de la acción: "ReserveNow".
     */
    @Override
    public String getAction() {
        return "ReserveNow";
    }
}
