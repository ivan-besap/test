package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Confirmation.NotifyEVChargingNeedsResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests.NotifyEVChargingNeedsRequest;

/**
 * Característica que representa el mensaje para notificar las necesidades de carga del vehículo eléctrico
 * (NotifyEVChargingNeeds) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite que el punto de carga informe al servidor (CSMS) sobre las necesidades específicas
 * de carga de un vehículo eléctrico, como la cantidad de energía requerida o las limitaciones de tiempo.
 * </p>
 */
public class NotifyEVChargingNeedsFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para manejar notificaciones de necesidades de carga del vehículo eléctrico.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public NotifyEVChargingNeedsFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link NotifyEVChargingNeedsRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return NotifyEVChargingNeedsRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link NotifyEVChargingNeedsResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return NotifyEVChargingNeedsResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "NotifyEVChargingNeeds".
     */
    @Override
    public String getAction() {
        return "NotifyEVChargingNeeds";
    }
}

