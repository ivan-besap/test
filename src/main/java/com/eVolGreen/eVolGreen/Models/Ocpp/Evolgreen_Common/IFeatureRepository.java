package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

import com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Feature;

import java.util.Optional;

/**
 * Interfaz para gestionar un repositorio de características (features) de OCPP.
 *
 * <p>Esta interfaz permite almacenar y recuperar características de OCPP que
 * se utilizan para la comunicación entre puntos de carga y servidores OCPP.
 * También proporciona un mecanismo para gestionar versiones del protocolo OCPP.</p>
 */
public interface IFeatureRepository {

    /**
     * Obtiene la versión del protocolo OCPP que se está utilizando.
     *
     * @return una instancia de {@link ProtocolVersion} que representa la versión del protocolo.
     */
    ProtocolVersion getProtocolVersion();

    /**
     * Busca y encuentra una característica (feature) específica dentro del repositorio.
     *
     * @param needle el objeto que se utiliza como clave de búsqueda de la característica.
     * @return un {@link Optional} que contiene la característica {@link Feature} si se encuentra,
     * o un {@link Optional#empty()} si no se encuentra.
     */
    Optional<Feature> findFeature(Object needle);
}
