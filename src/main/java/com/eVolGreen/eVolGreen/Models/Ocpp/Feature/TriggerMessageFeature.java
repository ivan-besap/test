package com.eVolGreen.eVolGreen.Models.Ocpp.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.RemoteTrigger.Confirmations.TriggerMessageConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.RemoteTrigger.Request.TriggerMessageRequest;


/**
 * La clase {@code TriggerMessageFeature} gestiona la funcionalidad de desencadenar el envío
 * de un mensaje dentro del protocolo OCPP. Este feature permite al sistema central solicitar
 * al punto de carga que envíe un mensaje específico.
 * <p>
 * Esta funcionalidad es útil para realizar diagnósticos o para forzar el envío de información
 * cuando sea necesario.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     TriggerMessageFeature triggerMessageFeature = new TriggerMessageFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = triggerMessageFeature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = triggerMessageFeature.getConfirmationType();}
 *     String action = triggerMessageFeature.getAction();
 * </pre>
 */
public class TriggerMessageFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de desencadenar un mensaje con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public TriggerMessageFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para desencadenar un mensaje.
     *
     * @return la clase {@link TriggerMessageRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return TriggerMessageRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para el envío de un mensaje.
     *
     * @return la clase {@link TriggerMessageConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return TriggerMessageConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de desencadenar un mensaje.
     * En este caso, la acción es "TriggerMessage".
     *
     * @return una cadena de texto con el nombre de la acción: "TriggerMessage".
     */
    @Override
    public String getAction() {
        return "TriggerMessage";
    }
}
