package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.DataTransferFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.FunctionFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.DataTransferRequest;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que proporciona métodos para manejar solicitudes y respuestas relacionadas
 * con el bloque funcional de transferencia de datos en el servidor.
 *
 * <p>Incluye la capacidad de manejar solicitudes de transferencia de datos específicas
 * de proveedores y generar solicitudes desde el servidor.</p>
 */
public class ServerDataTransferFunction implements Function {

    private final ServerDataTransferEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor de la clase que inicializa el manejador de eventos y las características.
     *
     * @param eventHandler Instancia del manejador de eventos para procesar solicitudes
     *                     relacionadas con la transferencia de datos.
     */
    public ServerDataTransferFunction(ServerDataTransferEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new DataTransferFeature(this));
    }

    /**
     * Obtiene la lista de características soportadas por esta función.
     *
     * @return Un array de {@link FunctionFeature} que representa las características soportadas.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja una solicitud recibida desde una estación de carga.
     *
     * @param sessionIndex Identificador único de la sesión en la que se recibió la solicitud.
     * @param request       Instancia de la solicitud {@link Request}.
     * @return Una instancia de {@link Confirmation} que representa la respuesta al cliente,
     *         o {@code null} si la solicitud no es soportada.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof DataTransferRequest) {
            return eventHandler.handleDataTransferRequest(sessionIndex, (DataTransferRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud de transferencia de datos desde el servidor con los campos obligatorios.
     *
     * <p>Se utiliza para iniciar solicitudes específicas de proveedor.</p>
     *
     * @param vendorId Identificador del proveedor específico de la implementación.
     * @return Una instancia de {@link DataTransferRequest}.
     */
    public DataTransferRequest createDataTransferRequest(String vendorId) {
        return new DataTransferRequest(vendorId);
    }
}
