package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.MeterValuesResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.MeterValuesRequest;

import java.util.UUID;

/**
 * Interfaz para manejar eventos del servidor relacionados con el bloque funcional
 * de valores del medidor (MeterValues).
 */
public interface ServerMeterValuesEventHandler {

    /**
     * Maneja una solicitud de tipo {@link MeterValuesRequest} y devuelve una respuesta de tipo
     * {@link MeterValuesResponse}.
     *
     * @param sessionIndex Identificador único de la sesión en la que se recibió la solicitud.
     * @param request Solicitud entrante de tipo {@link MeterValuesRequest} que contiene
     *                datos del medidor enviados por la estación de carga.
     * @return Respuesta de tipo {@link MeterValuesResponse} que será enviada de vuelta al cliente.
     */
    MeterValuesResponse handleMeterValuesRequest(UUID sessionIndex, MeterValuesRequest request);
}
