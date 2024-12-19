package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.DataTransferResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.DataTransferRequest;

/**
 * Interfaz de manejo de eventos de transferencia de datos para un cliente.
 *
 * <p>`ClientDataTransferEventHandler` define un contrato para manejar solicitudes de transferencia de datos (`DataTransferRequest`)
 * y generar las respuestas correspondientes (`DataTransferResponse`).
 *
 * <p>Este bloque funcional es utilizado para intercambiar datos personalizados entre la estación de carga y el sistema central
 * en el contexto del protocolo OCPP 2.0.1.
 */
public interface ClientDataTransferEventHandler {

    /**
     * Maneja una solicitud de transferencia de datos entrante y devuelve una respuesta.
     *
     * @param request La solicitud de transferencia de datos de tipo {@link DataTransferRequest}.
     * @return Una respuesta de tipo {@link DataTransferResponse}.
     */
    DataTransferResponse handleDataTransferRequest(DataTransferRequest request);
}
