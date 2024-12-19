package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.ClearCacheResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.ClearCacheRequest;

/**
 * Característica que representa el mensaje de limpieza de caché (ClearCache) en OCPP 2.0.1.
 * <p>
 * Este mensaje permite al servidor (CSMS) solicitar a un punto de carga que limpie su memoria caché
 * de información local, como datos de autorización previamente almacenados.
 * </p>
 */
public class ClearCacheFeature extends FunctionFeature {

    /**
     * Constructor que inicializa la funcionalidad de limpieza de caché.
     *
     * @param ownerFunction la función propietaria que gestiona esta característica.
     */
    public ClearCacheFeature(Function ownerFunction) {
        super(ownerFunction);
    }

    /**
     * Obtiene el tipo de mensaje de solicitud asociado con esta característica.
     *
     * @return la clase {@link ClearCacheRequest}.
     */
    @Override
    public Class<? extends Request> getRequestType() {
        return ClearCacheRequest.class;
    }

    /**
     * Obtiene el tipo de mensaje de confirmación asociado con esta característica.
     *
     * @return la clase {@link ClearCacheResponse}.
     */
    @Override
    public Class<? extends Confirmation> getConfirmationType() {
        return ClearCacheResponse.class;
    }

    /**
     * Obtiene el nombre de la acción asociada con esta funcionalidad.
     *
     * @return el nombre de la acción, en este caso "ClearCache".
     */
    @Override
    public String getAction() {
        return "ClearCache";
    }
}
