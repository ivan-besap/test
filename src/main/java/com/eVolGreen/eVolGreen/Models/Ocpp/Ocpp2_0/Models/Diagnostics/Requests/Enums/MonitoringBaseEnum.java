package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums;

/**
 * Especifica la base de monitoreo que se configurará en la estación de carga.
 *
 * <p>Esta enumeración define las posibles bases de monitoreo que pueden establecerse, dependiendo
 * del contexto y la funcionalidad soportada por la estación de carga.
 */
public enum MonitoringBaseEnum {

    /** Configuración para monitorear todos los parámetros disponibles. */
    All,

    /** Configuración predeterminada de fábrica. */
    FactoryDefault,

    /** Monitoreo exclusivo de parámetros cableados físicamente. */
    HardWiredOnly
}
