package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Confirmations.SetChargingProfileConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Request.SetChargingProfileRequest;


/**
 * La clase {@code SetChargingProfileFeature} gestiona la funcionalidad de establecer un perfil de carga
 * dentro del protocolo OCPP. Este perfil de carga define cómo y cuándo una estación de carga debe administrar
 * la potencia y energía suministrada a un vehículo eléctrico.
 * <p>
 * Este feature es esencial para optimizar la gestión de carga, permitiendo configurar horarios de carga
 * y perfiles personalizados que ajusten la potencia en función de las necesidades del sistema.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     SetChargingProfileFeature setChargingProfileFeature = new SetChargingProfileFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = setChargingProfileFeature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = setChargingProfileFeature.getConfirmationType();}
 *     String action = setChargingProfileFeature.getAction();
 * </pre>
 */
public class SetChargingProfileFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de establecimiento de perfil de carga con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public SetChargingProfileFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para establecer el perfil de carga.
     *
     * @return la clase {@link SetChargingProfileRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return SetChargingProfileRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para la solicitud de establecimiento
     * de perfil de carga.
     *
     * @return la clase {@link SetChargingProfileConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return SetChargingProfileConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de establecimiento de perfil de carga.
     * En este caso, la acción es "SetChargingProfile".
     *
     * @return una cadena de texto con el nombre de la acción: "SetChargingProfile".
     */
    @Override
    public String getAction() {
        return "SetChargingProfile";
    }
}
