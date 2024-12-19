package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.RemoteStartTransactionConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.RemoteStartTransactionRequest;

/**
 * La clase {@code RemoteStartTransactionFeature} permite la funcionalidad de iniciar una transacción
 * de carga de manera remota dentro del protocolo OCPP. Esto permite al backend o sistema central
 * activar una sesión de carga en una estación de carga específica.
 * <p>
 * Esta funcionalidad es crucial en escenarios donde se desea controlar las estaciones de carga de manera remota,
 * permitiendo iniciar una transacción sin la intervención directa en la estación.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     RemoteStartTransactionFeature feature = new RemoteStartTransactionFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = feature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = feature.getConfirmationType();}
 *     String action = feature.getAction();
 * </pre>
 */
public class RemoteStartTransactionFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de inicio remoto de transacciones con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public RemoteStartTransactionFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para iniciar una transacción de carga de manera remota.
     *
     * @return la clase {@link RemoteStartTransactionRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return RemoteStartTransactionRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para la solicitud de inicio remoto de transacción.
     *
     * @return la clase {@link RemoteStartTransactionConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return RemoteStartTransactionConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de inicio remoto de transacciones.
     * En este caso, la acción es "RemoteStartTransaction".
     *
     * @return una cadena de texto con el nombre de la acción: "RemoteStartTransaction".
     */
    @Override
    public String getAction() {
        return "RemoteStartTransaction";
    }
}
