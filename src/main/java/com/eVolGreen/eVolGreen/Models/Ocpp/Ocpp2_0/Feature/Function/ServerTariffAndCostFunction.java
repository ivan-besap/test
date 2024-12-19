package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.CostUpdatedFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.FunctionFeature;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Requests.CostUpdatedRequest;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que gestiona las solicitudes y respuestas relacionadas con el bloque funcional de TariffAndCost.
 * Este bloque incluye la actualización de costos de transacciones en el sistema de gestión central (CSMS).
 */
public class ServerTariffAndCostFunction implements Function {

    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor que inicializa la lista de características del bloque funcional de TariffAndCost.
     */
    public ServerTariffAndCostFunction() {
        features = new ArrayList<>();
        features.add(new CostUpdatedFeature(null));
    }

    /**
     * Devuelve la lista de características soportadas por este bloque funcional.
     *
     * @return Un arreglo de objetos {@link FunctionFeature}.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja las solicitudes recibidas para este bloque funcional.
     *
     * @param sessionIndex Identificador de la sesión donde se recibió la solicitud.
     * @param request Solicitud entrante que necesita ser procesada.
     * @return Confirmación como respuesta a la solicitud procesada, o null si no se reconoce la solicitud.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return null;
    }

    /**
     * Crea una solicitud {@link CostUpdatedRequest} con los campos requeridos.
     *
     * @param totalCost Costo total actual, basado en la información conocida por el CSMS, incluyendo impuestos.
     *                  Expresado en la moneda configurada por la variable de configuración: [Currency].
     * @param transactionId Identificador de la transacción para la cual se solicita el costo actual.
     * @return Una instancia de {@link CostUpdatedRequest}.
     */
    public CostUpdatedRequest createCostUpdatedRequest(Double totalCost, String transactionId) {
        return new CostUpdatedRequest(totalCost, transactionId);
    }
}
