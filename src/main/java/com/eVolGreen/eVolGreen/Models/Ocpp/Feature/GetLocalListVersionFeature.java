package com.eVolGreen.eVolGreen.Models.Ocpp.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.LocalAuthList.Confirmations.GetLocalListVersionConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.LocalAuthList.Request.GetLocalListVersionRequest;


/**
 * La clase {@code GetLocalListVersionFeature} maneja la funcionalidad relacionada con la obtención de la
 * versión de la lista local de autorizaciones en una estación de carga. Esta solicitud es enviada por el
 * sistema central para consultar la versión actual de la lista local mantenida en una estación.
 * <p>
 * Esta función es útil para sincronizar los datos de autenticación local entre la estación de carga y
 * el backend, asegurando que las versiones estén actualizadas.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     GetLocalListVersionFeature feature = new GetLocalListVersionFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = feature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = feature.getConfirmationType();}
 *     String action = feature.getAction();
 * </pre>
 */
public class GetLocalListVersionFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de obtención de la versión de la lista local con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public GetLocalListVersionFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para obtener la versión de la lista local de autorizaciones.
     *
     * @return la clase {@link GetLocalListVersionRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return GetLocalListVersionRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para la solicitud de obtención de la versión de la lista local.
     *
     * @return la clase {@link GetLocalListVersionConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return GetLocalListVersionConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de obtención de la versión de la lista local.
     * En este caso, la acción es "GetLocalListVersion".
     *
     * @return una cadena de texto con el nombre de la acción: "GetLocalListVersion".
     */
    @Override
    public String getAction() {
        return "GetLocalListVersion";
    }
}
