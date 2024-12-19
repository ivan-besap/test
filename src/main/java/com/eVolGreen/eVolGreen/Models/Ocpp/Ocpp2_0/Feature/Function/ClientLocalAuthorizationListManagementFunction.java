package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.FunctionFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.GetLocalListVersionFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.SendLocalListFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.SendLocalListRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.GetLocalListVersionRequest;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que proporciona métodos para crear solicitudes y manejar respuestas
 * relacionadas con la gestión de la lista de autorizaciones locales.
 *
 * Esta clase implementa las funcionalidades del bloque funcional
 * "LocalAuthorizationListManagement" definido en OCPP 2.0.1.
 */
public class ClientLocalAuthorizationListManagementFunction implements Function {

    private final ClientLocalAuthorizationListManagementEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor de la clase.
     *
     * @param eventHandler El manejador de eventos encargado de procesar las solicitudes
     *                     relacionadas con la lista de autorizaciones locales.
     */
    public ClientLocalAuthorizationListManagementFunction(
            ClientLocalAuthorizationListManagementEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new GetLocalListVersionFeature(this));
        features.add(new SendLocalListFeature(this));
    }

    /**
     * Devuelve la lista de características (features) soportadas por esta función.
     *
     * @return Un arreglo de objetos {@link FunctionFeature} que representan las
     *         características soportadas.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja las solicitudes entrantes para este bloque funcional.
     *
     * @param sessionIndex El identificador de la sesión en la que se recibió la solicitud.
     * @param request       La solicitud que se debe manejar.
     * @return Un objeto {@link Confirmation} que representa la respuesta generada.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof GetLocalListVersionRequest) {
            return eventHandler.handleGetLocalListVersionRequest((GetLocalListVersionRequest) request);
        } else if (request instanceof SendLocalListRequest) {
            return eventHandler.handleSendLocalListRequest((SendLocalListRequest) request);
        }
        return null;
    }
}
