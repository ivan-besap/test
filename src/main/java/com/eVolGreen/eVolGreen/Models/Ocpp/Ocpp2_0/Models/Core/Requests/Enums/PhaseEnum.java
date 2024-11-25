package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enum que representa las fases en las que se puede interpretar un valor medido.
 * <p>
 * Indica cómo debe interpretarse el valor medido, por ejemplo, entre una fase específica (L1, L2, L3) y el neutro (N),
 * o entre dos fases. Si la fase está ausente, se interpreta como un valor general.
 */
public enum PhaseEnum {

    /** Fase L1. */
    L1,

    /** Fase L2. */
    L2,

    /** Fase L3. */
    L3,

    /** Neutro. */
    N,

    /** Medición entre L1 y Neutro (L1-N). */
    @SerializedName("L1-N")
    L1_N,

    /** Medición entre L2 y Neutro (L2-N). */
    @SerializedName("L2-N")
    L2_N,

    /** Medición entre L3 y Neutro (L3-N). */
    @SerializedName("L3-N")
    L3_N,

    /** Medición entre L1 y L2 (L1-L2). */
    @SerializedName("L1-L2")
    L1_L2,

    /** Medición entre L2 y L3 (L2-L3). */
    @SerializedName("L2-L3")
    L2_L3,

    /** Medición entre L3 y L1 (L3-L1). */
    @SerializedName("L3-L1")
    L3_L1
}
