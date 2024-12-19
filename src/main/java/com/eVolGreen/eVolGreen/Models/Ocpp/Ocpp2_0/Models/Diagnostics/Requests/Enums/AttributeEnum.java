package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums;

/**
 * Enum AttributeEnum
 *
 * <p>Tipo de atributo: Actual, Target, MinSet, MaxSet.
 *
 * <p>Si no se especifica, el valor por defecto es "Actual".
 */
public enum AttributeEnum {
    /** Representa el valor actual del atributo. */
    Actual,

    /** Representa el valor objetivo del atributo. */
    Target,

    /** Representa el valor mínimo establecido para el atributo. */
    MinSet,

    /** Representa el valor máximo establecido para el atributo. */
    MaxSet
}
