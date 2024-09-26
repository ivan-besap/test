package com.eVolGreen.eVolGreen.Models.Ocpp.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;

import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.ChangeConfigurationConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.ChangeConfigurationRequest;

/**
 * La clase {@code ChangeConfigurationFeature} permite cambiar la configuración de una estación de carga
 * a través del protocolo OCPP. Esta funcionalidad es esencial para ajustar configuraciones clave
 * en los cargadores, tales como los parámetros de red, límites de corriente, entre otros.
 * <p>
 * El feature de cambio de configuración es fundamental para la gestión remota y actualización
 * de las estaciones de carga de acuerdo con las necesidades del operador.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     ChangeConfigurationFeature changeConfigurationFeature = new ChangeConfigurationFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = changeConfigurationFeature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = changeConfigurationFeature.getConfirmationType();}
 *     String action = changeConfigurationFeature.getAction();
 * </pre>
 */
public class ChangeConfigurationFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de cambio de configuración con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public ChangeConfigurationFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para cambiar la configuración.
     *
     * @return la clase {@link ChangeConfigurationRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return ChangeConfigurationRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para cambiar la configuración.
     *
     * @return la clase {@link ChangeConfigurationConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ChangeConfigurationConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de cambio de configuración.
     * En este caso, la acción es "ChangeConfiguration".
     *
     * @return una cadena de texto con el nombre de la acción: "ChangeConfiguration".
     */
    @Override
    public String getAction() {
        return "ChangeConfiguration";
    }
}
