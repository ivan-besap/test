package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums;

/**
 * Enum que representa las ubicaciones donde se puede realizar una medición.
 * <p>
 * Indica el lugar donde se tomó el valor medido. Por defecto, se asume la ubicación "Outlet".
 */
public enum LocationEnum {

    /** Medición realizada en el cuerpo del dispositivo. */
    Body,

    /** Medición realizada en el cable. */
    Cable,

    /** Medición realizada en el vehículo eléctrico (EV). */
    EV,

    /** Medición realizada en la entrada de carga (Inlet). */
    Inlet,

    /** Medición realizada en la salida de carga (Outlet). */
    Outlet
}
