package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Reservation.Requests.Enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enumeración de tipos de conectores (ConnectorEnum).
 *
 * <p>Representa los diferentes tipos de conectores que pueden utilizarse en estaciones de carga.
 */
public enum ConnectorEnum {
    /** Conector CCS Tipo 1. */
    cCCS1,

    /** Conector CCS Tipo 2. */
    cCCS2,

    /** Conector G105. */
    cG105,

    /** Conector Tesla. */
    cTesla,

    /** Conector Tipo 1. */
    cType1,

    /** Conector Tipo 2. */
    cType2,

    /** Conector Schuko 309 monofásico, 16 amperios. */
    @SerializedName("s309-1P-16A")
    s309_1P_16A,

    /** Conector Schuko 309 monofásico, 32 amperios. */
    @SerializedName("s309-1P-32A")
    s309_1P_32A,

    /** Conector Schuko 309 trifásico, 16 amperios. */
    @SerializedName("s309-3P-16A")
    s309_3P_16A,

    /** Conector Schuko 309 trifásico, 32 amperios. */
    @SerializedName("s309-3P-32A")
    s309_3P_32A,

    /** Conector BS 1361. */
    sBS1361,

    /** Conector CEE 7/7. */
    @SerializedName("sCEE-7-7")
    sCEE_7_7,

    /** Conector Tipo 2 (socket). */
    sType2,

    /** Conector Tipo 3. */
    sType3,

    /** Otro conector monofásico con corriente máxima de 16 amperios. */
    Other1PhMax16A,

    /** Otro conector monofásico con corriente superior a 16 amperios. */
    Other1PhOver16A,

    /** Otro conector trifásico. */
    Other3Ph,

    /** Conector Pan. */
    Pan,

    /** Carga inalámbrica por inducción. */
    wInductive,

    /** Carga inalámbrica por resonancia. */
    wResonant,

    /** Conector indeterminado. */
    Undetermined,

    /** Conector desconocido. */
    Unknown
}
