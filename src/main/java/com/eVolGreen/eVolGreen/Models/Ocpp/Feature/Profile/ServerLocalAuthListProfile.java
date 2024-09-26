package com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile;

import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Feature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.GetLocalListVersionFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.ProfileFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.SendLocalListFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.LocalAuthList.Request.GetLocalListVersionRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.LocalAuthList.Request.SendLocalListRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.LocalAuthList.Request.Enums.UpdateType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import java.util.HashSet;
import java.util.UUID;

/**
 * La clase {@code ServerLocalAuthListProfile} gestiona la lista de autenticación local en el servidor,
 * permitiendo operaciones como obtener la versión de la lista o actualizarla.
 * <p>
 * Este perfil contiene funcionalidades esenciales para manejar las listas locales de autenticación, las cuales
 * se utilizan para permitir la autenticación de usuarios en estaciones de carga sin depender de la conectividad
 * con el sistema central.
 */
public class ServerLocalAuthListProfile implements Profile {

    private HashSet<Feature> featureList;

    /**
     * Constructor que inicializa el perfil de lista de autenticación local del servidor.
     */
    public ServerLocalAuthListProfile() {
        featureList = new HashSet<>();
        featureList.add(new GetLocalListVersionFeature(null));
        featureList.add(new SendLocalListFeature(null));
    }

    /**
     * Devuelve una lista de las características que soporta este perfil.
     *
     * @return un array de {@link ProfileFeature} soportadas por este perfil.
     */
    @Override
    public ProfileFeature[] getFeatureList() {
        return featureList.toArray(new ProfileFeature[0]);
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
     * Crea una solicitud de envío de lista local con los valores requeridos.
     *
     * @param listVersion el número de versión de la lista.
     * @param updateType el tipo de actualización.
     * @return una instancia de {@link SendLocalListRequest}.
     */
    public SendLocalListRequest createSendLocalListRequest(int listVersion, UpdateType updateType) {
        return new SendLocalListRequest(listVersion, updateType);
    }

    /**
     * Crea una solicitud para obtener la versión de la lista local.
     *
     * @return una instancia de {@link GetLocalListVersionRequest}.
     */
    public GetLocalListVersionRequest createGetLocalListVersionRequest() {
        return new GetLocalListVersionRequest();
    }
}
