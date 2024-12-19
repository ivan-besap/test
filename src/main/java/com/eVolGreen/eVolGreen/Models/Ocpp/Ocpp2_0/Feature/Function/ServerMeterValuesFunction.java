package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.FunctionFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.MeterValuesFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.MeterValuesRequest;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que define los creadores de solicitudes y manejadores para el bloque funcional
 * de valores del medidor (MeterValues) en el servidor.
 */
public class ServerMeterValuesFunction implements Function {

    private final ServerMeterValuesEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor para inicializar el manejador de eventos y registrar las características
     * del bloque funcional.
     *
     * @param eventHandler Manejador de eventos que procesa solicitudes relacionadas con valores
     *                     del medidor.
     */
    public ServerMeterValuesFunction(ServerMeterValuesEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        this.features = new ArrayList<>();
        this.features.add(new MeterValuesFeature(this));
    }

    /**
     * Devuelve la lista de características funcionales compatibles con este bloque funcional.
     *
     * @return Un array de objetos {@link FunctionFeature} que representan las características.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja una solicitud recibida en el servidor para este bloque funcional.
     *
     * @param sessionIndex Identificador único de la sesión donde se recibió la solicitud.
     * @param request Solicitud entrante que implementa la interfaz {@link Request}.
     * @return Una confirmación de tipo {@link Confirmation}, o {@code null} si la solicitud no es compatible.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof MeterValuesRequest) {
            return eventHandler.handleMeterValuesRequest(sessionIndex, (MeterValuesRequest) request);
        }
        return null; // Retornar null si el tipo de solicitud no es compatible.
    }
}

