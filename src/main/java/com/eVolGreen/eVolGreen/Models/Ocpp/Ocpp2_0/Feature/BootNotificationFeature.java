package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.BootNotificationResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.BootNotificationRequest;

/**
 * Característica que representa el mensaje de notificación de arranque (BootNotification) en OCPP 2.0.1.
 * <p>
 * Este mensaje es enviado por un punto de carga (Charge Point) al servidor (CSMS) al iniciar su operación o
 * después de un reinicio, proporcionando información clave sobre su estado y configuración.
 * </p>
 */
public class BootNotificationFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad de notificación de arranque.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public BootNotificationFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link BootNotificationRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return BootNotificationRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link BootNotificationResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return BootNotificationResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "BootNotification".
     */
    @Override
    public String getAction() {
        return "BootNotification";
    }
}

