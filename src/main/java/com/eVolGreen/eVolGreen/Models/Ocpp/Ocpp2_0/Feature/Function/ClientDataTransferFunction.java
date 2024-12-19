package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.DataTransferFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.FunctionFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.DataTransferResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.DataTransferRequest;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que implementa la funcionalidad de transferencia de datos para clientes OCPP.
 *
 * <p>Esta clase permite la creación de solicitudes de transferencia de datos y el manejo de
 * respuestas asociadas dentro del bloque funcional "DataTransfer".
 */
public class ClientDataTransferFunction implements Function {

    private final ClientDataTransferEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor que inicializa la función de transferencia de datos con un controlador de eventos.
     *
     * @param eventHandler El controlador de eventos encargado de manejar solicitudes de transferencia de datos.
     */
    public ClientDataTransferFunction(ClientDataTransferEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        this.features = new ArrayList<>();
        features.add(new DataTransferFeature(this));
    }

    /**
     * Obtiene la lista de características (features) soportadas por esta función.
     *
     * @return Un array de {@link FunctionFeature} que representa las características soportadas.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja solicitudes entrantes relacionadas con la funcionalidad de transferencia de datos.
     *
     * <p>Si la solicitud es de tipo {@link DataTransferRequest}, delega su manejo al controlador
     * de eventos especificado.
     *
     * @param sessionIndex El identificador único de la sesión en la que se recibió la solicitud.
     * @param request La solicitud entrante a manejar.
     * @return Una instancia de {@link Confirmation}, que puede ser {@link DataTransferResponse},
     *         o {@code null} si la solicitud no es soportada.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof DataTransferRequest) {
            return eventHandler.handleDataTransferRequest((DataTransferRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud de transferencia de datos ({@link DataTransferRequest}) con los campos requeridos.
     *
     * @param vendorId El identificador del proveedor específico de la implementación.
     * @return Una nueva instancia de {@link DataTransferRequest} inicializada con el ID del proveedor.
     */
    public DataTransferRequest createDataTransferRequest(String vendorId) {
        return new DataTransferRequest(vendorId);
    }
}
