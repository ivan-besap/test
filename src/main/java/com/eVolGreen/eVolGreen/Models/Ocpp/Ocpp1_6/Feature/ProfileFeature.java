package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature;

//import com.eVolGreen.eVolGreen.Models.Ocpp.feature.profile.Profile;
//import com.eVolGreen.eVolGreen.Models.Ocpp.model.Confirmation;
//import com.eVolGreen.eVolGreen.Models.Ocpp.model.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;

import java.util.UUID;

/**
 * Clase abstracta para gestionar las características asociadas a un perfil en eVolGreen.
 * Proporciona un enlace al perfil propietario de las características y maneja las solicitudes.
 */
public abstract class ProfileFeature implements Feature {

    private Profile profile;

    /**
     * Crea un enlace al {@link Profile} que es propietario de esta función.
     *
     * @param ownerProfile el {@link Profile} que posee esta característica.
     */
    public ProfileFeature(Profile ownerProfile) {
        this.profile = ownerProfile;
    }

    /**
     * Llama al {@link Profile} para manejar una solicitud.
     *
     * @param sessionIndex fuente de la solicitud.
     * @param request la solicitud que debe ser gestionada.
     * @return la {@link Confirmation} que se enviará como respuesta.
     */
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return profile.handleRequest(sessionIndex, request);
    }
}
