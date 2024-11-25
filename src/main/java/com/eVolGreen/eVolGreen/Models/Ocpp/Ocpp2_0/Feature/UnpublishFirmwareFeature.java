package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Confirmation.UnpublishFirmwareResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests.UnpublishFirmwareRequest;

/**
 * Funcionalidad: Despublicar firmware.
 *
 * <p>La clase `UnpublishFirmwareFeature` implementa la funcionalidad del protocolo OCPP 2.0.1 que
 * permite al sistema central solicitar a una estación de carga que elimine la disponibilidad de un firmware previamente publicado.
 */
public class UnpublishFirmwareFeature extends FunctionFeature {

    /**
     * Constructor de la clase UnpublishFirmwareFeature.
     *
     * @param ownerFunction La función propietaria que contiene esta característica.
     */
    public UnpublishFirmwareFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link UnpublishFirmwareRequest} que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return UnpublishFirmwareRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link UnpublishFirmwareResponse} que representa la respuesta.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return UnpublishFirmwareResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica.
     *
     * @return Una cadena que contiene el nombre de la acción ("UnpublishFirmware").
     */
    @Override
    public String getAction() {
        return "UnpublishFirmware";
    }
}
