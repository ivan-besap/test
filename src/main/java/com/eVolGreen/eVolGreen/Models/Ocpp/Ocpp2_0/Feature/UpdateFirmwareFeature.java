package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Confirmation.UpdateFirmwareResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.FirmwareManagement.Requests.UpdateFirmwareRequest;

/**
 * Funcionalidad: Actualización de firmware.
 *
 * <p>La clase `UpdateFirmwareFeature` implementa la funcionalidad del protocolo OCPP 2.0.1 que
 * permite al sistema central solicitar a una estación de carga que descargue e instale una nueva versión de firmware.
 */
public class UpdateFirmwareFeature extends FunctionFeature {

    /**
     * Constructor de la clase UpdateFirmwareFeature.
     *
     * @param ownerFunction La función propietaria que contiene esta característica.
     */
    public UpdateFirmwareFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado a esta característica.
     *
     * @return La clase {@link UpdateFirmwareRequest} que representa la solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return UpdateFirmwareRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado a esta característica.
     *
     * @return La clase {@link UpdateFirmwareResponse} que representa la respuesta.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return UpdateFirmwareResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta característica.
     *
     * @return Una cadena que contiene el nombre de la acción ("UpdateFirmware").
     */
    @Override
    public String getAction() {
        return "UpdateFirmware";
    }
}
