package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Confirmation.Enums;

/**
 * Enum que representa el estado de aceptación de una solicitud por parte de una estación de carga.
 *
 * <p>Este enum es utilizado en respuestas donde se requiere indicar si la solicitud pudo ser
 * procesada con éxito, fue rechazada, no es soportada o el resultado es un conjunto vacío.</p>
 */
public enum GenericDeviceModelStatusEnum {

    /**
     * Indica que la solicitud fue aceptada y procesada exitosamente por la estación de carga.
     */
    Accepted,

    /**
     * Indica que la solicitud fue rechazada por la estación de carga.
     */
    Rejected,

    /**
     * Indica que la estación de carga no soporta la operación solicitada.
     */
    NotSupported,

    /**
     * Indica que el resultado de la solicitud es un conjunto vacío, es decir, no hay datos disponibles
     * para retornar.
     */
    EmptyResultSet
}
