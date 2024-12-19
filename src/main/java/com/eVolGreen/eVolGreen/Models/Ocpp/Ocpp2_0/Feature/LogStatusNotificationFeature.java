package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.LogStatusNotificationResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.LogStatusNotificationRequest;

/**
 * Característica que representa el mensaje de notificación del estado del registro (LogStatusNotification) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite que el punto de carga informe al servidor (CSMS) sobre el estado
 * de un proceso relacionado con la creación o transferencia de registros, como la generación o el envío de un log.
 * </p>
 */
public class LogStatusNotificationFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad para manejar notificaciones de estado de registro.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public LogStatusNotificationFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link LogStatusNotificationRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return LogStatusNotificationRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link LogStatusNotificationResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return LogStatusNotificationResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "LogStatusNotification".
     */
    @Override
    public String getAction() {
        return "LogStatusNotification";
    }
}

