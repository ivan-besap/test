package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.ProtocolVersion;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile.Profile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.IServerAPI;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function.Function;

/**
 * Interfaz para la API del servidor multi-protocolo en OCPP.
 * Proporciona funcionalidades para agregar perfiles y funciones de diferentes versiones del protocolo OCPP
 * y para consultar el puerto en el que el servidor está escuchando.
 */
public interface IMultiProtocolServerAPI extends IServerAPI {

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
     * Devuelve el número de puerto en el que el servidor está escuchando.
     *
     * @return el número de puerto en el que el servidor está escuchando, o 0 si el servidor no está activo.
     */
    int getPort();
}
