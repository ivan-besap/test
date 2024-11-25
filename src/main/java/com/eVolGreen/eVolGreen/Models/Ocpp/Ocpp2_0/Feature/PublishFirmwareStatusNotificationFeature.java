package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Confirmation.PublishFirmwareStatusNotificationResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests.PublishFirmwareStatusNotificationRequest;

/**
 * Característica: Notificación de Estado de Publicación de Firmware.
 *
 * <p>Esta clase implementa la funcionalidad para manejar notificaciones de estado relacionadas con
 * la publicación de firmware en estaciones de carga, de acuerdo con el protocolo OCPP 2.0.1.
 */
public class PublishFirmwareStatusNotificationFeature extends FunctionFeature {

    /**
     * Constructor de la característica PublishFirmwareStatusNotificationFeature.
     *
     * @param ownerFunction La función propietaria que contiene esta característica.
     */
    public PublishFirmwareStatusNotificationFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link PublishFirmwareStatusNotificationRequest}, que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return PublishFirmwareStatusNotificationRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link PublishFirmwareStatusNotificationResponse}, que representa la respuesta
     * de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return PublishFirmwareStatusNotificationResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica en el protocolo OCPP.
     *
     * @return Una cadena que indica la acción ("PublishFirmwareStatusNotification").
     */
    @Override
    public String getAction() {
        return "PublishFirmwareStatusNotification";
    }
}
