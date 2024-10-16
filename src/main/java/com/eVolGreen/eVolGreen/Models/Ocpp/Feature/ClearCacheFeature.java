package com.eVolGreen.eVolGreen.Models.Ocpp.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;

import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.ClearCacheConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.ClearCacheRequest;

/**
 * La clase {@code ClearCacheFeature} proporciona la funcionalidad de limpiar la caché
 * en la estación de carga a través de OCPP. Esto es útil cuando se requiere eliminar
 * información almacenada temporalmente en la estación para que pueda aceptar nuevos
 * datos o cambios.
 * <p>
 * Esta funcionalidad es especialmente importante para la gestión eficiente de los
 * recursos y para asegurar que la estación esté actualizada y libre de datos obsoletos.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     ClearCacheFeature clearCacheFeature = new ClearCacheFeature(perfilPropietario);
 *     {@code Class<? extends Request> requestType = clearCacheFeature.getRequestType();}
 *     {@code Class<? extends Confirmation> confirmationType = clearCacheFeature.getConfirmationType();}
 *     String action = clearCacheFeature.getAction();
 * </pre>
 */
public class ClearCacheFeature extends ProfileFeature {

    /**
     * Constructor que inicializa la funcionalidad de limpiar la caché con el perfil asociado.
     *
     * @param ownerProfile el perfil propietario de esta funcionalidad.
     */
    public ClearCacheFeature(Profile ownerProfile) {
        super(ownerProfile);
    }

    /**
     * Devuelve la clase que representa el tipo de solicitud para limpiar la caché.
     *
     * @return la clase {@link ClearCacheRequest} correspondiente al tipo de solicitud.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return ClearCacheRequest.class;
    }

    /**
     * Devuelve la clase que representa el tipo de confirmación para limpiar la caché.
     *
     * @return la clase {@link ClearCacheConfirmation} correspondiente al tipo de confirmación.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ClearCacheConfirmation.class;
    }

    /**
     * Devuelve la acción que representa la funcionalidad de limpieza de caché.
     * En este caso, la acción es "ClearCache".
     *
     * @return una cadena de texto con el nombre de la acción: "ClearCache".
     */
    @Override
    public String getAction() {
        return "ClearCache";
    }
}
