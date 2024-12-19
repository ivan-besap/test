package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.DataTransferConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.DataTransferRequest;

/**
 * La clase {@code DataTransferFeature} gestiona la funcionalidad de transferencia de datos en el
 * protocolo OCPP. Esta funcionalidad permite la transmisión de datos entre el punto de carga y el
 * sistema central, proporcionando un canal para intercambiar información adicional.
 *
 * <p>Esta característica es especialmente útil cuando es necesario enviar datos personalizados
 * entre el punto de carga y el sistema central, como información específica del fabricante o
 * datos adicionales que no están cubiertos por las funcionalidades estándar de OCPP.
 *
 * <p>Ejemplo de uso:
 * <pre>
 *     DataTransferFeature dataTransferFeature = new DataTransferFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = dataTransferFeature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = dataTransferFeature.getConfirmationType();}
 *     String action = dataTransferFeature.getAction();
 * </pre>
 */
public class DataTransferFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de transferencia de datos con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public DataTransferFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para la transferencia de datos.
     *
     * @return la clase {@link DataTransferRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return DataTransferRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para la transferencia de datos.
     *
     * @return la clase {@link DataTransferConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return DataTransferConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de transferencia de datos.
     * En este caso, la acción es "DataTransfer".
     *
     * @return una cadena de texto con el nombre de la acción: "DataTransfer".
     */
    @Override
    public String getAction() {
        return "DataTransfer";
    }
}
