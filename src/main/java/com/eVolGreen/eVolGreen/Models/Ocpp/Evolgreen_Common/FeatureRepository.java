package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Feature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;

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

    public FeatureRepository() {
        this(ProtocolVersion.OCPP1_6);
    }

    public FeatureRepository(ProtocolVersion protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    @Override
    public ProtocolVersion getProtocolVersion() {
        return protocolVersion;
    }

    public void addFeatureProfile(Profile profile) {
        for (Feature feature : profile.getFeatureList()) {
            addFeature(feature);
        }
    }

    public void addFeature(Feature feature) {
        actionMap.put(feature.getAction(), feature);
        classMap.put(feature.getRequestType(), feature);
        classMap.put(feature.getConfirmationType(), feature);
    }

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
        StringBuilder sb = new StringBuilder();
        sb.append("FeatureRepository{");
        sb.append("protocolVersion=").append(protocolVersion).append(", ");
        sb.append("actionMap=").append(actionMap).append(", ");
        sb.append("classMap=").append(classMap);
        sb.append('}');
        return sb.toString();
    }
}