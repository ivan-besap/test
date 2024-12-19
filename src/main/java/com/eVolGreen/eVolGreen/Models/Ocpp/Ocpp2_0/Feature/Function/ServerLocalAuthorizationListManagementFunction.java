package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.FunctionFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.GetLocalListVersionFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.SendLocalListFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.UpdateEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.SendLocalListRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.GetLocalListVersionRequest;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que proporciona creadores de solicitudes y manejadores del servidor
 * para el bloque funcional de gestión de listas de autorización local.
 */
public class ServerLocalAuthorizationListManagementFunction implements Function {

    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor que inicializa las características del bloque funcional de gestión de listas
     * de autorización local.
     */
    public ServerLocalAuthorizationListManagementFunction() {
        features = new ArrayList<>();
        features.add(new GetLocalListVersionFeature(null));
        features.add(new SendLocalListFeature(null));
    }

    /**
     * Devuelve la lista de características soportadas por esta función.
     *
     * @return un arreglo de {@link FunctionFeature}.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja solicitudes entrantes. Actualmente, no se manejan solicitudes en esta implementación.
     *
     * @param sessionIndex Identificador de la sesión en la que se recibió la solicitud.
     * @param request Solicitud entrante de tipo {@link Request}.
     * @return Siempre retorna {@code null} ya que esta implementación no maneja solicitudes.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return null;
    }

    /**
     * Crea una solicitud del servidor de tipo {@link GetLocalListVersionRequest}.
     *
     * @return una instancia de {@link GetLocalListVersionRequest}.
     */
    public GetLocalListVersionRequest createGetLocalListVersionRequest() {
        return new GetLocalListVersionRequest();
    }

    /**
     * Crea una solicitud del servidor de tipo {@link SendLocalListRequest} con los campos requeridos.
     *
     * @param versionNumber Número de versión de la lista después de la actualización.
     *                      En el caso de una actualización completa, es el número de versión de la lista completa.
     * @param updateType Tipo de actualización (completa o diferencial).
     * @return una instancia de {@link SendLocalListRequest}.
     */
    public SendLocalListRequest createSendLocalListRequest(
            Integer versionNumber, UpdateEnum updateType) {
        return new SendLocalListRequest(versionNumber, updateType);
    }
}
