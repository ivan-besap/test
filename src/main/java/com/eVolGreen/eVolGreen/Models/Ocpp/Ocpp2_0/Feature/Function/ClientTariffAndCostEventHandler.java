package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Confirmation.CostUpdatedResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Requests.CostUpdatedRequest;

/**
 * Interfaz de manejador de eventos para el bloque funcional de Tarifas y Costos (TariffAndCost).
 * <p>
 * Define los métodos de callback que se utilizan para manejar solicitudes específicas relacionadas
 * con actualizaciones de costos enviadas desde el CSMS hacia el cliente.
 */
public interface ClientTariffAndCostEventHandler {

    /**
     * Maneja una solicitud {@link CostUpdatedRequest} y devuelve una respuesta {@link CostUpdatedResponse}.
     * <p>
     * Este método es invocado cuando el servidor envía una solicitud para informar sobre cambios
     * o actualizaciones en los costos asociados al proceso de carga.
     *
     * @param request La solicitud {@link CostUpdatedRequest} entrante que contiene información
     *                sobre la actualización de costos.
     * @return Una instancia de {@link CostUpdatedResponse} que representa la respuesta a la solicitud.
     */
    CostUpdatedResponse handleCostUpdatedRequest(CostUpdatedRequest request);
}
