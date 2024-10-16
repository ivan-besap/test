package com.eVolGreen.eVolGreen.Models.Ocpp.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.SmartCharging.Confirmations.GetCompositeScheduleConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.SmartCharging.Request.GetCompositeScheduleRequest;


/**
 * La clase {@code GetCompositeScheduleFeature} gestiona la funcionalidad de obtener un cronograma compuesto
 * para la carga en una estación de carga, dentro del protocolo OCPP. Esta solicitud permite al sistema
 * obtener un cronograma compuesto de carga de múltiples conectores.
 * <p>
 * Este feature es esencial para la planificación y gestión eficiente de la energía y la distribución de
 * la carga en una red de cargadores, proporcionando una visión general de los horarios de carga disponibles.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     GetCompositeScheduleFeature feature = new GetCompositeScheduleFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = feature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = feature.getConfirmationType();}
 *     String action = feature.getAction();
 * </pre>
 */
public class GetCompositeScheduleFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de obtención de cronograma compuesto con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public GetCompositeScheduleFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para obtener un cronograma compuesto de carga.
     *
     * @return la clase {@link GetCompositeScheduleRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return GetCompositeScheduleRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para la obtención de un cronograma compuesto de carga.
     *
     * @return la clase {@link GetCompositeScheduleConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetCompositeScheduleConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de obtención de cronograma compuesto de carga.
     * En este caso, la acción es "GetCompositeSchedule".
     *
     * @return una cadena de texto con el nombre de la acción: "GetCompositeSchedule".
     */
    @Override
    public String getAction() {
        return "GetCompositeSchedule";
    }
}
