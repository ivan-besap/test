package com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Feature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Handler.ClientRemoteTriggerEventHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.ProfileFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.TriggerMessageFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.RemoteTrigger.Request.TriggerMessageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Perfil del cliente para el manejo de eventos relacionados con los disparadores remotos en OCPP.
 * Esta clase permite gestionar las solicitudes y respuestas relacionadas con mensajes que activan
 * acciones remotas en un punto de carga.
 */
@Component
public class ClientRemoteTriggerProfile implements Profile {
    private ClientRemoteTriggerEventHandler eventHandler;
    private ArrayList<Feature> features;

    /**
     * Constructor que inicializa el perfil de disparador remoto con su manejador de eventos asociado.
     *
     * @param handler El manejador de eventos para solicitudes de disparadores remotos.
     */
    public ClientRemoteTriggerProfile(ClientRemoteTriggerEventHandler handler) {
        features = new ArrayList<>();
        eventHandler = handler;

        features.add(new TriggerMessageFeature(this));
    }

    /**
     * Devuelve la lista de características (features) disponibles para este perfil.
     *
     * @return Un array de {@link ProfileFeature} que contiene las características implementadas.
     */
    @Override
    public ProfileFeature[] getFeatureList() {
        return features.toArray(new ProfileFeature[0]);
    }

    /**
     * Maneja las solicitudes recibidas de disparadores remotos.
     *
     * @param sessionIndex El índice de la sesión de la solicitud.
     * @param request La solicitud que debe ser procesada.
     * @return La confirmación resultante de manejar la solicitud.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        Confirmation result = null;

        if (request instanceof TriggerMessageRequest) {
            result = eventHandler.handleTriggerMessageRequest((TriggerMessageRequest) request);
        }

        return result;
    }
}
