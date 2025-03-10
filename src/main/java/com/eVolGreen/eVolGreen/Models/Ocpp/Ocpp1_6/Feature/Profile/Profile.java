package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.ProfileFeature;

import java.util.UUID;

/**
 * Interfaz utilizada para los perfiles de características (Feature Profiles) en eVolGreen.
 */
public interface Profile {

    /**
     * Obtiene una lista de características compatibles para este perfil.
     *
     * @return un arreglo de las características compatibles.
     */
    ProfileFeature[] getFeatureList();

    /**
     * Maneja las solicitudes entrantes.
     *
     * @param sessionIndex identificador de la sesión fuente de la solicitud.
     * @param request la solicitud a manejar.
     * @return la confirmación que se enviará como respuesta.
     */
    Confirmation handleRequest(UUID sessionIndex, Request request);
}
