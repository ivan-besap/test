package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.HeartbeatConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.HeartbeatRequest;

/**
 * La clase {@code HeartbeatFeature} gestiona la funcionalidad del latido ("Heartbeat") en el protocolo OCPP.
 * Este mensaje es enviado periódicamente por una estación de carga al sistema central para indicar que sigue en línea.
 * <p>
 * El latido es esencial para el mantenimiento de la conexión entre el punto de carga y el sistema central,
 * ya que permite detectar rápidamente desconexiones o problemas en la comunicación.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     HeartbeatFeature heartbeatFeature = new HeartbeatFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = heartbeatFeature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = heartbeatFeature.getConfirmationType();}
 *     String action = heartbeatFeature.getAction();
 * </pre>
 */
public class HeartbeatFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de latido ("Heartbeat") con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public HeartbeatFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para el latido.
     *
     * @return la clase {@link HeartbeatRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return HeartbeatRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para el latido.
     *
     * @return la clase {@link HeartbeatConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return HeartbeatConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de latido.
     * En este caso, la acción es "Heartbeat".
     *
     * @return una cadena de texto con el nombre de la acción: "Heartbeat".
     */
    @Override
    public String getAction() {
        return "Heartbeat";
    }
}
