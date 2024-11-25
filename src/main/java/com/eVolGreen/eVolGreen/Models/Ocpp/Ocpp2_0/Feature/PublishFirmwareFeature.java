package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;


import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Confirmation.PublishFirmwareResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests.PublishFirmwareRequest;

/**
 * Característica: Publicación de Firmware.
 *
 * <p>Esta clase implementa la funcionalidad de la publicación de firmware en estaciones de carga
 * utilizando el protocolo OCPP 2.0.1. Permite a un sistema central enviar una solicitud a la
 * estación de carga para que publique un nuevo firmware.
 */
public class PublishFirmwareFeature extends FunctionFeature {

    /**
     * Constructor de la característica PublishFirmwareFeature.
     *
     * @param ownerFunction La función propietaria que contiene esta característica.
     */
    public PublishFirmwareFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link PublishFirmwareRequest}, que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return PublishFirmwareRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link PublishFirmwareResponse}, que representa la respuesta de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return PublishFirmwareResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica en el protocolo OCPP.
     *
     * @return Una cadena que indica la acción ("PublishFirmware").
     */
    @Override
    public String getAction() {
        return "PublishFirmware";
    }
}
