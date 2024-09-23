package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities;

//import com.evolgreen.ocpp.feature.Feature;
//import com.evolgreen.ocpp.feature.profile.Profile;
//import com.evolgreen.ocpp.model.Confirmation;
//import com.evolgreen.ocpp.model.Request;
//import com.evolgreen.ocpp.utilities.MoreObjects;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Feature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Utils.MoreObjects;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Repositorio de características para gestionar y registrar las características soportadas
 * por el cliente OCPP en el sistema eVolGreen CMS.
 * <p>
 * Se encarga de agregar perfiles de funciones, gestionar las acciones y mapear las solicitudes
 * y confirmaciones con las características correspondientes.
 * </p>
 */
public class FeatureRepository implements IFeatureRepository {

    private final ProtocolVersion protocolVersion;
    private final Map<String, Feature> actionMap = new HashMap<>();
    private final Map<Class<?>, Feature> classMap = new HashMap<>();

    /**
     * Crea una instancia de FeatureRepository con la versión OCPP 1.6 por defecto.
     */
    public FeatureRepository() {
        this(ProtocolVersion.OCPP1_6);
    }

    /**
     * Crea una instancia de FeatureRepository con una versión específica del protocolo OCPP.
     *
     * @param protocolVersion versión del protocolo OCPP utilizada
     */
    public FeatureRepository(ProtocolVersion protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    /**
     * Devuelve la versión del protocolo OCPP utilizada en este repositorio.
     *
     * @return la versión del protocolo
     */
    @Override
    public ProtocolVersion getProtocolVersion() {
        return protocolVersion;
    }

    /**
     * Añade un perfil de funciones que agrupa varias características soportadas.
     *
     * @param profile el perfil de funciones a añadir
     */
    public void addFeatureProfile(Profile profile) {
        for (Feature feature : profile.getFeatureList()) {
            addFeature(feature);
        }
    }

    /**
     * Añade una característica individual al repositorio.
     *
     * @param feature la característica a añadir
     */
    public void addFeature(Feature feature) {
        actionMap.put(feature.getAction(), feature);
        classMap.put(feature.getRequestType(), feature);
        classMap.put(feature.getConfirmationType(), feature);
    }

    /**
     * Busca una característica soportada en el repositorio, ya sea por su nombre de acción o
     * por las clases de solicitud o confirmación.
     *
     * @param needle puede ser un nombre de acción ({@link String}), una solicitud ({@link Request}) o una confirmación ({@link Confirmation})
     * @return una instancia opcional de la característica soportada
     */
    @Override
    public Optional<Feature> findFeature(Object needle) {
        if (needle instanceof String) {
            return Optional.ofNullable(actionMap.get(needle));
        }

        if ((needle instanceof Request) || (needle instanceof Confirmation)) {
            return Optional.ofNullable(classMap.get((needle.getClass())));
        }

        return Optional.empty();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper("FeatureRepository")
                .add("protocolVersion", protocolVersion)
                .add("actionMap", actionMap)
                .add("classMap", classMap)
                .toString();
    }
}
