package com.eVolGreen.eVolGreen.Models.Ocpp.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware.Confirmations.UpdateFirmwareConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Firmware.Request.UpdateFirmwareRequest;


/**
 * La clase {@code UpdateFirmwareFeature} gestiona la funcionalidad de actualización de firmware
 * dentro del protocolo OCPP. Esto permite que una estación de carga o backend gestione la
 * actualización del firmware en los puntos de carga.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     UpdateFirmwareFeature updateFirmwareFeature = new UpdateFirmwareFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = updateFirmwareFeature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = updateFirmwareFeature.getConfirmationType();}
 *     String action = updateFirmwareFeature.getAction();
 * </pre>
 */
public class UpdateFirmwareFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de actualización de firmware con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public UpdateFirmwareFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para la actualización de firmware.
     *
     * @return la clase {@link UpdateFirmwareRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return UpdateFirmwareRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para la actualización de firmware.
     *
     * @return la clase {@link UpdateFirmwareConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return UpdateFirmwareConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de actualización de firmware.
     * En este caso, la acción es "UpdateFirmware".
     *
     * @return una cadena de texto con el nombre de la acción: "UpdateFirmware".
     */
    @Override
    public String getAction() {
        return "UpdateFirmware";
    }
}
