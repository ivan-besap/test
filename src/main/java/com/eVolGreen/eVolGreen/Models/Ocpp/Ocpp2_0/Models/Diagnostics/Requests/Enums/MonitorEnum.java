package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums;

/**
 * Tipo de monitor utilizado en la supervisión de variables.
 *
 * <p>Este enumerado define los diferentes tipos de monitoreo que pueden aplicarse
 * a las variables de un componente, como umbrales superior e inferior, monitoreo
 * periódico o monitoreo basado en un reloj.
 */
public enum MonitorEnum {
    /** Monitoreo basado en un umbral superior. */
    UpperThreshold,

    /** Monitoreo basado en un umbral inferior. */
    LowerThreshold,

    /** Monitoreo basado en un cambio delta en el valor de la variable. */
    Delta,

    /** Monitoreo periódico en intervalos de tiempo constantes. */
    Periodic,

    /** Monitoreo periódico alineado con el reloj del sistema. */
    PeriodicClockAligned
}
