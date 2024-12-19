package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.AuthorizeFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.ClearCacheFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.FunctionFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.AuthorizeRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.ClearCacheRequest;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que define los creadores de solicitudes y manejadores relacionados con el bloque funcional de autorización.
 *
 * <p>Esta clase proporciona los métodos necesarios para gestionar las solicitudes de autorización
 * desde el servidor, así como para generar las solicitudes correspondientes hacia otros sistemas.</p>
 */
public class ServerAuthorizationFunction implements Function {

    private final ServerAuthorizationEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor que inicializa el manejador de eventos de autorización y las características soportadas.
     *
     * @param eventHandler El manejador de eventos que procesará las solicitudes relacionadas con la autorización.
     */
    public ServerAuthorizationFunction(ServerAuthorizationEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new AuthorizeFeature(this));
        features.add(new ClearCacheFeature(null));
    }

    /**
     * Obtiene la lista de características soportadas por esta función.
     *
     * @return Un arreglo de las características disponibles como {@link FunctionFeature}.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja una solicitud recibida por el servidor y genera la respuesta correspondiente.
     *
     * @param sessionIndex El identificador único de la sesión donde se recibió la solicitud.
     * @param request       La solicitud entrante que debe ser procesada.
     * @return Una instancia de {@link Confirmation} que contiene la respuesta a la solicitud,
     *         o {@code null} si el tipo de solicitud no es soportado.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof AuthorizeRequest) {
            return eventHandler.handleAuthorizeRequest(sessionIndex, (AuthorizeRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud {@link ClearCacheRequest} para limpiar la memoria caché.
     *
     * <p>Esta solicitud se utiliza para instruir al cliente a que limpie la caché que almacena
     * datos temporales como identificadores de tarjetas RFID autorizadas.</p>
     *
     * @return Una instancia de {@link ClearCacheRequest}.
     */
    public ClearCacheRequest createClearCacheRequest() {
        return new ClearCacheRequest();
    }
}
