package com.eVolGreen.eVolGreen.Models.Ocpp.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.RemoteStopTransactionConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.RemoteStopTransactionRequest;

/**
 * La clase {@code RemoteStopTransactionFeature} gestiona la funcionalidad de detener una transacción
 * de carga de manera remota dentro del protocolo OCPP. Esto permite al backend o sistema central
 * finalizar una sesión de carga que está en progreso en una estación de carga específica.
 * <p>
 * Esta funcionalidad es clave en la administración de la red de cargadores, permitiendo al operador
 * tener control sobre las sesiones de carga activas y detenerlas según sea necesario.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     RemoteStopTransactionFeature feature = new RemoteStopTransactionFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = feature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = feature.getConfirmationType();}
 *     String action = feature.getAction();
 * </pre>
 */
public class RemoteStopTransactionFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de detener transacciones de manera remota con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public RemoteStopTransactionFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para detener una transacción de carga de manera remota.
     *
     * @return la clase {@link RemoteStopTransactionRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return RemoteStopTransactionRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para la solicitud de detener una transacción remota.
     *
     * @return la clase {@link RemoteStopTransactionConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return RemoteStopTransactionConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de detener transacciones de manera remota.
     * En este caso, la acción es "RemoteStopTransaction".
     *
     * @return una cadena de texto con el nombre de la acción: "RemoteStopTransaction".
     */
    @Override
    public String getAction() {
        return "RemoteStopTransaction";
    }
}
