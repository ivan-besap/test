package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.StopTransactionConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.StopTransactionRequest;

/**
 * La clase {@code StopTransactionFeature} gestiona la funcionalidad de detener una transacción
 * dentro del protocolo OCPP. Este feature permite que el punto de carga envíe al sistema central
 * la solicitud de detener una transacción de carga en curso.
 * <p>
 * Esta característica es esencial para gestionar el fin de una sesión de carga, enviando los
 * datos necesarios, como el estado de la transacción, la cantidad de energía suministrada, y otros
 * detalles relevantes.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     StopTransactionFeature stopTransactionFeature = new StopTransactionFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = stopTransactionFeature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = stopTransactionFeature.getConfirmationType();}
 *     String action = stopTransactionFeature.getAction();
 * </pre>
 */
public class StopTransactionFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de detener una transacción con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public StopTransactionFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para detener una transacción.
     *
     * @return la clase {@link StopTransactionRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return StopTransactionRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para detener una transacción.
     *
     * @return la clase {@link StopTransactionConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return StopTransactionConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de detener una transacción.
     * En este caso, la acción es "StopTransaction".
     *
     * @return una cadena de texto con el nombre de la acción: "StopTransaction".
     */
    @Override
    public String getAction() {
        return "StopTransaction";
    }
}
