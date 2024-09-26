package com.eVolGreen.eVolGreen.Models.Ocpp.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.GetConfigurationConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.GetConfigurationRequest;

/**
 * La clase {@code GetConfigurationFeature} permite al sistema obtener la configuración actual de
 * la estación de carga, a través del protocolo OCPP. Esta solicitud se utiliza para consultar
 * parámetros de configuración específicos o para obtener la lista completa de parámetros
 * configurables de la estación.
 * <p>
 * Esta funcionalidad es fundamental para la gestión remota de estaciones de carga, proporcionando
 * una forma de validar y ajustar configuraciones de forma centralizada.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     GetConfigurationFeature feature = new GetConfigurationFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = feature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = feature.getConfirmationType();}
 *     String action = feature.getAction();
 * </pre>
 */
public class GetConfigurationFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de obtención de configuración con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public GetConfigurationFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para obtener la configuración de la estación de carga.
     *
     * @return la clase {@link GetConfigurationRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return GetConfigurationRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para la obtención de la configuración de la estación de carga.
     *
     * @return la clase {@link GetConfigurationConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetConfigurationConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de obtención de configuración de la estación de carga.
     * En este caso, la acción es "GetConfiguration".
     *
     * @return una cadena de texto con el nombre de la acción: "GetConfiguration".
     */
    @Override
    public String getAction() {
        return "GetConfiguration";
    }
}
