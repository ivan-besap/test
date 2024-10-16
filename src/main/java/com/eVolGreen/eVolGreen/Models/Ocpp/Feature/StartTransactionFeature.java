package com.eVolGreen.eVolGreen.Models.Ocpp.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.StartTransactionConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.StartTransactionRequest;

/**
 * La clase {@code StartTransactionFeature} gestiona la funcionalidad de iniciar una transacción
 * de carga en el protocolo OCPP. Este feature permite a la estación de carga o backend iniciar
 * una sesión de carga, asociada a una transacción específica.
 * <p>
 * Iniciar una transacción es un paso fundamental en la operación de una estación de carga, ya que
 * asocia el tiempo y la energía suministrada a una transacción, que generalmente está ligada a un usuario.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     StartTransactionFeature startTransactionFeature = new StartTransactionFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = startTransactionFeature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = startTransactionFeature.getConfirmationType();}
 *     String action = startTransactionFeature.getAction();
 * </pre>
 */
public class StartTransactionFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de inicio de transacción con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public StartTransactionFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para iniciar una transacción.
     *
     * @return la clase {@link StartTransactionRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return StartTransactionRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para la solicitud de inicio de transacción.
     *
     * @return la clase {@link StartTransactionConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return StartTransactionConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de inicio de transacción.
     * En este caso, la acción es "StartTransaction".
     *
     * @return una cadena de texto con el nombre de la acción: "StartTransaction".
     */
    @Override
    public String getAction() {
        return "StartTransaction";
    }
}
