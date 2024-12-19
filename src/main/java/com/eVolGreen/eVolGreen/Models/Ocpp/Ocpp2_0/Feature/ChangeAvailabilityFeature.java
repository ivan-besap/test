package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.ChangeAvailabilityResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.ChangeAvailabilityRequest;

/**
 * Característica que representa el mensaje para cambiar la disponibilidad de un punto de carga
 * (ChangeAvailability) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al servidor (CSMS) cambiar el estado de disponibilidad de un punto de carga,
 * indicándole si debe estar operativo o fuera de servicio.
 * </p>
 */
public class ChangeAvailabilityFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para cambiar la disponibilidad de un punto de carga.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public ChangeAvailabilityFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link ChangeAvailabilityRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return ChangeAvailabilityRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link ChangeAvailabilityResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ChangeAvailabilityResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "ChangeAvailability".
     */
    @Override
    public String getAction() {
        return "ChangeAvailability";
    }
}

