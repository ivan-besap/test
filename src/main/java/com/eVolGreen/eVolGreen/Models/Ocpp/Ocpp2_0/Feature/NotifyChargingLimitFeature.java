package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Confirmation.NotifyChargingLimitResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests.NotifyChargingLimitRequest;

/**
 * Característica que representa el mensaje para notificar el límite de carga (NotifyChargingLimit) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite que el punto de carga informe al servidor (CSMS) sobre los límites
 * de carga que están actualmente activos o configurados, proporcionando información clave
 * para gestionar la distribución de energía y las estrategias de carga.
 * </p>
 */
public class NotifyChargingLimitFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para manejar notificaciones del límite de carga.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public NotifyChargingLimitFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link NotifyChargingLimitRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return NotifyChargingLimitRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link NotifyChargingLimitResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return NotifyChargingLimitResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "NotifyChargingLimit".
     */
    @Override
    public String getAction() {
        return "NotifyChargingLimit";
    }
}
