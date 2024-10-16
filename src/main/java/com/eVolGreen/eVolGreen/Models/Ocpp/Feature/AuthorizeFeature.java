package com.eVolGreen.eVolGreen.Models.Ocpp.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.AuthorizeConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.AuthorizeRequest;

/**
 * La clase {@code AuthorizeFeature} representa la funcionalidad de autorización en el protocolo OCPP,
 * manejando las solicitudes y confirmaciones necesarias para validar la identidad del usuario.
 * <p>
 * Este feature permite la verificación del ID del usuario que intenta acceder a la estación de carga.
 * Recibe una solicitud de autorización y devuelve una confirmación dependiendo de los permisos del usuario.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     AuthorizeFeature authorizeFeature = new AuthorizeFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = authorizeFeature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = authorizeFeature.getConfirmationType();}
 *     String action = authorizeFeature.getAction();
 * </pre>
 */
public class AuthorizeFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de autorización con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public AuthorizeFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para la funcionalidad de autorización.
     *
     * @return la clase {@link AuthorizeRequest} correspondiente al tipo de solicitud de autorización.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return AuthorizeRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para la funcionalidad de autorización.
     *
     * @return la clase {@link AuthorizeConfirmation} correspondiente al tipo de confirmación de autorización.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return AuthorizeConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de autorización.
     * En este caso, la acción es "Authorize".
     *
     * @return una cadena de texto con el nombre de la acción: "Authorize".
     */
    @Override
    public String getAction() {
        return "Authorize";
    }
}
