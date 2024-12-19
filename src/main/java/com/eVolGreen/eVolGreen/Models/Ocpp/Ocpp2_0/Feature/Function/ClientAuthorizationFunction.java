package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.AuthorizeFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.ClearCacheFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.FunctionFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.AuthorizeRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.ClearCacheRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Utils.IdToken;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase para gestionar solicitudes y respuestas del bloque funcional de autorización en el cliente.
 *
 * <p>La clase `ClientAuthorizationFunction` implementa la interfaz {@link Function} y proporciona métodos
 * para manejar solicitudes relacionadas con la autorización en OCPP 2.0.1. Esta funcionalidad incluye
 * la creación de solicitudes de autorización y el manejo de eventos como la limpieza de caché.
 */
public class ClientAuthorizationFunction implements Function {

    private final ClientAuthorizationEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor de la clase `ClientAuthorizationFunction`.
     *
     * @param eventHandler El manejador de eventos que implementa {@link ClientAuthorizationEventHandler},
     *                     utilizado para procesar eventos de autorización.
     */
    public ClientAuthorizationFunction(ClientAuthorizationEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new AuthorizeFeature(null));
        features.add(new ClearCacheFeature(this));
    }

    /**
     * Obtiene la lista de características compatibles con esta función.
     *
     * @return Un arreglo de {@link FunctionFeature} que representa las características compatibles.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja una solicitud entrante de tipo {@link Request}.
     *
     * <p>Actualmente soporta solicitudes de tipo {@link ClearCacheRequest}.
     *
     * @param sessionIndex El identificador único de la sesión asociada a esta solicitud.
     * @param request La solicitud entrante de tipo {@link Request}.
     * @return Una instancia de {@link Confirmation} que contiene la respuesta a la solicitud, o
     *         {@code null} si el tipo de solicitud no es soportado.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof ClearCacheRequest) {
            return eventHandler.handleClearCacheRequest((ClearCacheRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud de autorización {@link AuthorizeRequest}.
     *
     * <p>Este método permite construir una solicitud de autorización con los campos requeridos.
     *
     * @param idToken Un identificador insensible a mayúsculas y minúsculas utilizado para la autorización,
     *                junto con el tipo de autorización para soportar múltiples formas de identificadores.
     * @return Una instancia de {@link AuthorizeRequest} lista para ser enviada.
     */
    public AuthorizeRequest createAuthorizeRequest(IdToken idToken) {
        return new AuthorizeRequest(idToken);
    }
}
