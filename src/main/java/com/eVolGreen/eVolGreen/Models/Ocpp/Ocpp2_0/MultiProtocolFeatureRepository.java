package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.FeatureRepository;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.IFeatureRepository;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.ProtocolVersion;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Feature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Repositorio de características para manejar múltiples versiones del protocolo OCPP.
 * Proporciona soporte para agregar perfiles y funciones de características específicas
 * para diferentes versiones del protocolo, tanto para el cliente como para el servidor.
 */
public class MultiProtocolFeatureRepository implements IFeatureRepository {

    // Mapeo de versiones de protocolo a sus respectivos repositorios de características.
    private final Map<ProtocolVersion, FeatureRepository> featureRepositories;

    // Repositorio de características seleccionado según la versión de protocolo actual.
    private IFeatureRepository selectedFeatureRepository;

    /**
     * Constructor que inicializa un repositorio de características por cada versión del protocolo
     * proporcionada en la lista.
     *
     * @param protocolVersions lista de versiones de protocolo que deben ser soportadas.
     */
    public MultiProtocolFeatureRepository(List<ProtocolVersion> protocolVersions) {
        featureRepositories = new HashMap<>();
        for (ProtocolVersion protocolVersion : protocolVersions) {
            featureRepositories.put(protocolVersion, new FeatureRepository(protocolVersion));
        }
        selectedFeatureRepository = null;
    }

    /* Métodos compartidos entre cliente y servidor */

    /**
     * Agrega un perfil de características a un repositorio específico basado en la versión del
     * protocolo.
     *
     * @param protocolVersion la versión del protocolo asociada al perfil.
     * @param profile el perfil de características a agregar.
     * @throws IllegalArgumentException si la versión del protocolo no tiene un repositorio asociado.
     */
    public void addFeatureProfile(ProtocolVersion protocolVersion, Profile profile) {
        getFeatureRepository(protocolVersion).addFeatureProfile(profile);
    }

    /**
     * Agrega una función de características a un repositorio específico basado en la versión del
     * protocolo.
     *
     * @param protocolVersion la versión del protocolo asociada a la función.
     * @param function la función de características a agregar.
     * @throws IllegalArgumentException si la versión del protocolo no tiene un repositorio asociado.
     */
    public void addFeatureFunction(ProtocolVersion protocolVersion, Function function) {
        FeatureRepository featureRepository = getFeatureRepository(protocolVersion);
        for (Feature feature : function.getFeatureList()) {
            featureRepository.addFeature(feature);
        }
    }

    /* Métodos utilizados por el servidor */

    /**
     * Recupera el repositorio de características asociado a una versión específica del protocolo.
     *
     * @param protocolVersion la versión del protocolo cuyo repositorio se requiere.
     * @return el repositorio de características correspondiente.
     * @throws IllegalArgumentException si no existe un repositorio para la versión del protocolo.
     */
    public FeatureRepository getFeatureRepository(ProtocolVersion protocolVersion) {
        FeatureRepository featureRepository = featureRepositories.get(protocolVersion);
        if (featureRepository == null) {
            throw new IllegalArgumentException("No feature repository for protocol " + protocolVersion);
        }
        return featureRepository;
    }

    /* Métodos utilizados por el cliente */

    /**
     * Selecciona la versión del protocolo que debe ser utilizada por el cliente.
     *
     * @param protocolVersion la versión del protocolo a seleccionar, o {@code null} para deseleccionar.
     */
    public void selectProtocolVersion(@Nullable ProtocolVersion protocolVersion) {
        selectedFeatureRepository = featureRepositories.get(protocolVersion);
    }

    /**
     * Obtiene la versión del protocolo actualmente seleccionada.
     *
     * @return la versión del protocolo seleccionada, o {@code null} si no hay ninguna seleccionada.
     */
    @Nullable
    public ProtocolVersion getProtocolVersion() {
        IFeatureRepository featureRepository = selectedFeatureRepository;
        return featureRepository != null ? featureRepository.getProtocolVersion() : null;
    }

    /**
     * Busca una característica específica en el repositorio seleccionado actualmente.
     *
     * @param needle el identificador de la característica buscada.
     * @return un {@link Optional} que contiene la característica encontrada o vacío si no se encontró.
     */
    @Override
    public Optional<Feature> findFeature(Object needle) {
        IFeatureRepository featureRepository = selectedFeatureRepository;
        return featureRepository != null ? featureRepository.findFeature(needle) : Optional.empty();
    }

    /**
     * Genera una representación en cadena del objeto para propósitos de depuración.
     *
     * @return una cadena que representa el estado actual del objeto.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("featureRepositories", featureRepositories)
                .add("selectedFeatureRepository", selectedFeatureRepository)
                .toString();
    }
}

