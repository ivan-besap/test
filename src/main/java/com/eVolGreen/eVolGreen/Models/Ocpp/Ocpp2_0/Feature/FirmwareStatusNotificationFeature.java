package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Confirmation.FirmwareStatusNotificationResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests.FirmwareStatusNotificationRequest;

/**
 * Característica que representa el mensaje de notificación de estado del firmware (FirmwareStatusNotification) en OCPP 2.0.1.
 * <p>
 * Este mensaje es enviado por un punto de carga al servidor (CSMS) para informar sobre el estado
 * de las operaciones relacionadas con el firmware, como descargas, instalación o fallos.
 * </p>
 */
public class FirmwareStatusNotificationFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad de notificación de estado del firmware.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public FirmwareStatusNotificationFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link FirmwareStatusNotificationRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return FirmwareStatusNotificationRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link FirmwareStatusNotificationResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return FirmwareStatusNotificationResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "FirmwareStatusNotification".
     */
    @Override
    public String getAction() {
        return "FirmwareStatusNotification";
    }
}
