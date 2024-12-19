package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.ClearCacheResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.ClearCacheRequest;

/**
 * Manejador de eventos de autorización para clientes.
 *
 * <p>La interfaz `ClientAuthorizationEventHandler` proporciona un punto de extensión para que los clientes gestionen eventos relacionados con el bloque funcional de autorización definido en OCPP 2.0.1.
 */
public interface ClientAuthorizationEventHandler {

    /**
     * Maneja una solicitud {@link ClearCacheRequest} y devuelve una respuesta {@link ClearCacheResponse}.
     *
     * <p>Este método permite al cliente procesar solicitudes del sistema central para borrar la caché en una estación de carga, asegurando que los datos de autorización se sincronicen con el sistema central.
     *
     * @param request La solicitud entrante {@link ClearCacheRequest} enviada por el sistema central.
     * @return Una respuesta {@link ClearCacheResponse} que indica si la operación de limpieza de caché fue aceptada o rechazada.
     */
    ClearCacheResponse handleClearCacheRequest(ClearCacheRequest request);
}
