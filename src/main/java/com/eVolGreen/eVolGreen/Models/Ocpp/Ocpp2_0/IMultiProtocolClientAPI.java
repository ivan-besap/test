package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.ProtocolVersion;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.IClientAPI;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;

/**
 * Interfaz para la API de cliente multi-protocolo en OCPP.
 * Proporciona funcionalidades para agregar perfiles y funciones de diferentes versiones del protocolo OCPP
 * y para consultar la versión de protocolo seleccionada por el servidor.
 */
public interface IMultiProtocolClientAPI extends IClientAPI {

    /**
     * Agrega una implementación de perfil de OCPP 1.x para una versión específica del protocolo.
     *
     * @param protocolVersion la versión del protocolo representada por {@link ProtocolVersion}.
     *                        Debe corresponder a una implementación válida de OCPP 1.x.
     * @param profile la implementación del {@link Profile} para la versión especificada del protocolo.
     */
    void addFeatureProfile(ProtocolVersion protocolVersion, Profile profile);

    /**
     * Agrega una implementación de función de OCPP 2.x para una versión específica del protocolo.
     *
     * @param protocolVersion la versión del protocolo representada por {@link ProtocolVersion}.
     *                        Debe corresponder a una implementación válida de OCPP 2.x.
     * @param function la implementación de {@link Function} para la versión especificada del protocolo.
     */
    void addFunction(ProtocolVersion protocolVersion, Function function);

    /**
     * Devuelve la versión del protocolo seleccionada por el servidor después de la conexión.
     *
     * @return la versión del protocolo representada por {@link ProtocolVersion} seleccionada por el servidor,
     *         o {@code null} si el cliente no está conectado.
     */
    ProtocolVersion getProtocolVersion();
}
