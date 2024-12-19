package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.GetCompositeScheduleResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.GetCompositeScheduleRequest;

/**
 * Característica que representa el mensaje para obtener un cronograma compuesto (GetCompositeSchedule) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al servidor (CSMS) solicitar un cronograma combinado para un punto de carga,
 * con el objetivo de optimizar el uso de energía y gestionar la planificación de carga.
 * </p>
 */
public class GetCompositeScheduleFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para obtener un cronograma compuesto.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public GetCompositeScheduleFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link GetCompositeScheduleRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return GetCompositeScheduleRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link GetCompositeScheduleResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetCompositeScheduleResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "GetCompositeSchedule".
     */
    @Override
    public String getAction() {
        return "GetCompositeSchedule";
    }
}
