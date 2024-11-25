package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Enums;

/**
 * Tipo de costo.
 *
 * <p>Este enumerado define los diferentes tipos de costos que pueden estar asociados con un consumo
 * o tarifa en un sistema de carga.
 */
public enum CostKindEnum {

    /**
     * Emisión de dióxido de carbono.
     *
     * <p>El costo está relacionado con la cantidad de dióxido de carbono (CO₂) emitida durante
     * el proceso de generación o consumo de energía.
     */
    CarbonDioxideEmission,

    /**
     * Precio relativo en porcentaje.
     *
     * <p>El costo se expresa como un porcentaje relativo, indicando la relación con un precio base o
     * estándar.
     */
    RelativePricePercentage,

    /**
     * Porcentaje de generación renovable.
     *
     * <p>El costo está relacionado con el porcentaje de generación de energía proveniente de fuentes
     * renovables, como solar o eólica.
     */
    RenewableGenerationPercentage
}

