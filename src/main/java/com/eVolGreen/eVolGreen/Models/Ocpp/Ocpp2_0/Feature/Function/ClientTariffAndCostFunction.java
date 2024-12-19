package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.CostUpdatedFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.FunctionFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Requests.CostUpdatedRequest;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que implementa la funcionalidad del bloque "TariffAndCost" en OCPP 2.0.1.
 * <p>
 * Esta clase se utiliza para gestionar solicitudes y respuestas relacionadas con tarifas y costos
 * en una estación de carga.
 * </p>
 */
public class ClientTariffAndCostFunction implements Function {

    private final ClientTariffAndCostEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor de la clase.
     *
     * @param eventHandler El manejador de eventos asociado a esta funcionalidad.
     */
    public ClientTariffAndCostFunction(ClientTariffAndCostEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new CostUpdatedFeature(this));
    }

    /**
     * Obtiene la lista de características disponibles en este bloque funcional.
     *
     * @return Un arreglo de características ({@link FunctionFeature}) soportadas.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja una solicitud recibida asociada al bloque funcional.
     *
     * @param sessionIndex El identificador único de la sesión en la que se recibió la solicitud.
     * @param request       La solicitud recibida, que extiende {@link Request}.
     * @return Una confirmación ({@link Confirmation}) en respuesta a la solicitud, o {@code null}
     * si la solicitud no es soportada.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof CostUpdatedRequest) {
            return eventHandler.handleCostUpdatedRequest((CostUpdatedRequest) request);
        }
        return null;
    }
}

