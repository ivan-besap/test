package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.Enums;

/**
 * Enum para definir criterios de componentes.
 *
 * <p>Estos criterios se utilizan para filtrar componentes específicos en solicitudes de reporte.
 */
public enum ComponentCriterionEnum {
    /** Componentes actualmente activos. */
    Active,

    /** Componentes que están disponibles para ser utilizados. */
    Available,

    /** Componentes habilitados en el sistema. */
    Enabled,

    /** Componentes que presentan problemas o fallas. */
    Problem
}
