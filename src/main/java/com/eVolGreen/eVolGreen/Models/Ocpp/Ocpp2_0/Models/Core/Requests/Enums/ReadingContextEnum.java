package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enum que representa el contexto de una lectura.
 * <p>
 * Define el tipo de detalle de valor medido, como el inicio o fin de una transacción, interrupción o lectura periódica.
 * Por defecto, se establece en "Sample.Periodic".
 */
public enum ReadingContextEnum {

    /** Inicio de una interrupción en el suministro. */
    @SerializedName("Interruption.Begin")
    InterruptionBegin,

    /** Fin de una interrupción en el suministro. */
    @SerializedName("Interruption.End")
    InterruptionEnd,

    /** Contexto no especificado o alternativo. */
    Other,

    /** Lectura realizada en un tiempo específico del reloj. */
    @SerializedName("Sample.Clock")
    SampleClock,

    /** Lectura periódica de los valores muestreados. */
    @SerializedName("Sample.Periodic")
    SamplePeriodic,

    /** Inicio de una transacción de carga. */
    @SerializedName("Transaction.Begin")
    TransactionBegin,

    /** Fin de una transacción de carga. */
    @SerializedName("Transaction.End")
    TransactionEnd,

    /** Lectura activada por un evento o desencadenante específico. */
    Trigger
}

