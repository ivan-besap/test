package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.DataTransferResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.DataTransferRequest;

import java.util.UUID;

/**
 * Manejador de eventos de servidor para el bloque funcional de transferencia de datos.
 *
 * <p>Proporciona un método para procesar solicitudes de transferencia de datos recibidas desde estaciones de carga
 * y responder con la confirmación correspondiente.</p>
 */
public interface ServerDataTransferEventHandler {

    /**
     * Maneja una solicitud de transferencia de datos ({@link DataTransferRequest}) y genera una respuesta ({@link DataTransferResponse}).
     *
     * <p>Este método procesa la solicitud entrante enviada por una estación de carga a través de OCPP,
     * permitiendo el intercambio de datos específicos del proveedor o implementación personalizada.</p>
     *
     * @param sessionIndex Identificador único de la sesión donde se recibió la solicitud.
     * @param request       Instancia de la solicitud ({@link DataTransferRequest}) enviada por la estación de carga.
     * @return Una respuesta ({@link DataTransferResponse}) que confirma el procesamiento de la solicitud.
     */
    DataTransferResponse handleDataTransferRequest(UUID sessionIndex, DataTransferRequest request);
}
