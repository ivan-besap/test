package com.eVolGreen.eVolGreen.Models.Ocpp.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.MeterValuesConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.MeterValuesRequest;

/**
 * La clase {@code MeterValuesFeature} gestiona la funcionalidad de recolección de valores del medidor en el protocolo OCPP.
 * Esta funcionalidad permite a una estación de carga enviar valores de medición, como el consumo de energía, al sistema central.
 * <p>
 * Los valores del medidor son esenciales para el monitoreo del uso de energía y para la facturación adecuada de los usuarios de vehículos eléctricos.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     MeterValuesFeature meterValuesFeature = new MeterValuesFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = meterValuesFeature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = meterValuesFeature.getConfirmationType();}
 *     String action = meterValuesFeature.getAction();
 * </pre>
 */
public class MeterValuesFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de valores del medidor con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public MeterValuesFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para los valores del medidor.
     *
     * @return la clase {@link MeterValuesRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return MeterValuesRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para los valores del medidor.
     *
     * @return la clase {@link MeterValuesConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return MeterValuesConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de valores del medidor.
     * En este caso, la acción es "MeterValues".
     *
     * @return una cadena de texto con el nombre de la acción: "MeterValues".
     */
    @Override
    public String getAction() {
        return "MeterValues";
    }
}
