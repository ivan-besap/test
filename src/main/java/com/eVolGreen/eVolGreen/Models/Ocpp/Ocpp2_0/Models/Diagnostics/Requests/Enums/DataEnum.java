package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums;

import com.google.gson.annotations.SerializedName;

/**
 * Tipo de dato de esta variable.
 * <p>
 * Representa los tipos de datos que una variable puede adoptar en el contexto de OCPP 2.0.1.
 * </p>
 */
public enum DataEnum {

    /** Tipo de dato: Cadena de caracteres. */
    string,

    /** Tipo de dato: Número decimal. */
    decimal,

    /** Tipo de dato: Número entero. */
    integer,

    /** Tipo de dato: Fecha y hora. */
    dateTime,

    /** Tipo de dato: Booleano (true o false). */
    @SerializedName("boolean")
    booleanType,

    /**
     * Tipo de dato: Lista de opciones.
     * <p>
     * La variable debe contener un valor único seleccionado de una lista predefinida de opciones.
     * </p>
     */
    OptionList,

    /**
     * Tipo de dato: Lista de secuencias.
     * <p>
     * La variable puede contener una lista ordenada de valores predefinidos.
     * </p>
     */
    SequenceList,

    /**
     * Tipo de dato: Lista de miembros.
     * <p>
     * La variable puede contener un subconjunto no ordenado de valores predefinidos.
     * </p>
     */
    MemberList
}
