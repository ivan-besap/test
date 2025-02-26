package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Confirmations.ClearChargingProfileConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Request.ClearChargingProfileRequest;


/**
 * La clase {@code ClearChargingProfileFeature} gestiona la funcionalidad de limpiar perfiles
 * de carga en estaciones de carga dentro del protocolo OCPP.
 *
 * <p>Permite a un sistema central enviar una solicitud para limpiar un perfil de carga
 * específico de un punto de carga, asegurando que no se apliquen restricciones o perfiles
 * antiguos a nuevas sesiones de carga.</p>
 *
 * <p>Ejemplo de uso:</p>
 * <pre>
 *     ClearChargingProfileFeature feature = new ClearChargingProfileFeature(ownerProfile);
 *     {@code Class<? extends Request> requestType = feature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = feature.getConfirmationType();}
 *     String action = feature.getAction();
 * </pre>
 */
public class ClearChargingProfileFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de limpiar perfiles de carga con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public ClearChargingProfileFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para limpiar perfiles de carga.
     *
     * @return la clase {@link ClearChargingProfileRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return ClearChargingProfileRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para la limpieza de perfiles de carga.
     *
     * @return la clase {@link ClearChargingProfileConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ClearChargingProfileConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de limpieza de perfiles de carga.
     * En este caso, la acción es "ClearChargingProfile".
     *
     * @return una cadena de texto con el nombre de la acción: "ClearChargingProfile".
     */
    @Override
    public String getAction() {
        return "ClearChargingProfile";
    }
}
