package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.UnlockConnectorConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.UnlockConnectorRequest;

/**
 * La clase {@code UnlockConnectorFeature} gestiona la funcionalidad de desbloqueo del conector
 * dentro del protocolo OCPP. Esto permite a una estación de carga o backend desbloquear un conector
 * que pueda haber sido bloqueado después de una sesión de carga.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     UnlockConnectorFeature unlockConnectorFeature = new UnlockConnectorFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = unlockConnectorFeature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = unlockConnectorFeature.getConfirmationType();}
 *     String action = unlockConnectorFeature.getAction();
 * </pre>
 */
public class UnlockConnectorFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de desbloquear el conector con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public UnlockConnectorFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para desbloquear el conector.
     *
     * @return la clase {@link UnlockConnectorRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return UnlockConnectorRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para el desbloqueo del conector.
     *
     * @return la clase {@link UnlockConnectorConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return UnlockConnectorConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de desbloquear el conector.
     * En este caso, la acción es "UnlockConnector".
     *
     * @return una cadena de texto con el nombre de la acción: "UnlockConnector".
     */
    @Override
    public String getAction() {
        return "UnlockConnector";
    }
}
