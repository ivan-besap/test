package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Feature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.ProfileFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.TriggerMessageFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.RemoteTrigger.Request.TriggerMessageRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.RemoteTrigger.Request.Enums.TriggerMessageRequestType;
import java.util.HashSet;
import java.util.UUID;

/**
 * La clase {@code ServerRemoteTriggerProfile} gestiona la funcionalidad de activación remota
 * de mensajes en el servidor, permitiendo enviar solicitudes para activar eventos
 * remotos en las estaciones de carga.
 * <p>
 * Este perfil contiene características esenciales que permiten al servidor gestionar
 * eventos remotos en las estaciones de carga.
 */
public class ServerRemoteTriggerProfile implements Profile {

    private HashSet<Feature> features;

    /**
     * Constructor que inicializa el perfil de activación remota en el servidor.
     */
    public ServerRemoteTriggerProfile() {
        features = new HashSet<>();
        features.add(new TriggerMessageFeature(null));
    }

    /**
     * Devuelve una lista de las características que soporta este perfil.
     *
     * @return un array de {@link ProfileFeature} soportadas por este perfil.
     */
    @Override
    public ProfileFeature[] getFeatureList() {
        return features.toArray(new ProfileFeature[0]);
    }

    /**
     * Maneja las solicitudes recibidas y devuelve la confirmación correspondiente.
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request la solicitud recibida.
     * @return una confirmación de tipo {@link Confirmation}.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return null;
    }

    /**
     * Crea una solicitud {@link TriggerMessageRequest} con los valores requeridos.
     *
     * @param triggerMessageRequestType el tipo de mensaje de activación {@link TriggerMessageRequestType}.
     * @return una instancia de {@link TriggerMessageRequest}.
     */
    public TriggerMessageRequest createTriggerMessageRequest(TriggerMessageRequestType triggerMessageRequestType) {
        return createTriggerMessageRequest(triggerMessageRequestType, null);
    }

    /**
     * Crea una solicitud {@link TriggerMessageRequest} con los valores requeridos, incluyendo un conector.
     *
     * @param triggerMessageRequestType el tipo de mensaje de activación {@link TriggerMessageRequestType}.
     * @param connectorId el identificador del conector. Debe ser mayor que 0.
     * @return una instancia de {@link TriggerMessageRequest}.
     */
    public TriggerMessageRequest createTriggerMessageRequest(TriggerMessageRequestType triggerMessageRequestType, Integer connectorId) {
        TriggerMessageRequest request = new TriggerMessageRequest(triggerMessageRequestType);
        request.setConnectorId(connectorId);
        return request;
    }
}
