package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Confirmation.NotifyEVChargingScheduleResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests.NotifyEVChargingScheduleRequest;

/**
 * Característica que representa el mensaje para notificar el horario de carga del vehículo eléctrico
 * (NotifyEVChargingSchedule) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite que el punto de carga informe al servidor (CSMS) sobre el horario de carga
 * del vehículo eléctrico, proporcionando detalles sobre los tiempos de carga programados y la energía requerida.
 * </p>
 */
public class NotifyEVChargingScheduleFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para manejar notificaciones del horario de carga del vehículo eléctrico.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public NotifyEVChargingScheduleFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link NotifyEVChargingScheduleRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return NotifyEVChargingScheduleRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link NotifyEVChargingScheduleResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return NotifyEVChargingScheduleResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "NotifyEVChargingSchedule".
     */
    @Override
    public String getAction() {
        return "NotifyEVChargingSchedule";
    }
}
