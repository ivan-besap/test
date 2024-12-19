package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Feature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.GetLocalListVersionFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Handler.ClientLocalAuthListEventHandler;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.ProfileFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.SendLocalListFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.LocalAuthList.Request.GetLocalListVersionRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.LocalAuthList.Request.SendLocalListRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import java.util.ArrayList;
import java.util.UUID;

/**
 * El perfil {@code ClientLocalAuthListProfile} maneja las solicitudes relacionadas con la lista de autenticación
 * local de un punto de carga. Implementa la lógica para gestionar solicitudes de obtención y envío de listas
 * locales a los puntos de carga.
 */
public class ClientLocalAuthListProfile implements Profile {

    private ClientLocalAuthListEventHandler eventHandler;
    private ArrayList<Feature> featureList;

    /**
     * Constructor que inicializa el perfil de autenticación local con un controlador de eventos.
     *
     * @param handler El controlador de eventos que maneja las solicitudes relacionadas con la lista de autenticación local.
     */
    public ClientLocalAuthListProfile(ClientLocalAuthListEventHandler handler) {
        eventHandler = handler;

        featureList = new ArrayList<>();
        featureList.add(new GetLocalListVersionFeature(this));
        featureList.add(new SendLocalListFeature(this));
    }

    /**
     * Devuelve una lista de las características (features) soportadas por este perfil.
     *
     * @return Un array de las características implementadas.
     */
    @Override
    public ProfileFeature[] getFeatureList() {
        return featureList.toArray(new ProfileFeature[0]);
    }

    /**
     * Maneja las solicitudes recibidas y delega su procesamiento al controlador de eventos.
     *
     * @param sessionIndex El identificador de la sesión.
     * @param request La solicitud que se ha recibido.
     * @return La confirmación generada por el controlador de eventos.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        Confirmation result = null;

        if (request instanceof GetLocalListVersionRequest) {
            result = eventHandler.handleGetLocalListVersionRequest((GetLocalListVersionRequest) request);
        } else if (request instanceof SendLocalListRequest) {
            result = eventHandler.handleSendLocalListRequest((SendLocalListRequest) request);
        }

        return result;
    }
}
