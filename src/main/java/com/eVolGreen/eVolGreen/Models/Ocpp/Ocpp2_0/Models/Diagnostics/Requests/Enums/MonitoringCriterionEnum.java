package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums;

/**
 * Representa los criterios de monitoreo disponibles para componentes y variables
 * en una estación de carga según OCPP 2.0.1.
 */
public enum MonitoringCriterionEnum {

    /** Monitoreo basado en umbrales específicos. */
    ThresholdMonitoring,

    /** Monitoreo basado en cambios (delta) en los valores observados. */
    DeltaMonitoring,

    /** Monitoreo periódico a intervalos definidos. */
    PeriodicMonitoring;
}
