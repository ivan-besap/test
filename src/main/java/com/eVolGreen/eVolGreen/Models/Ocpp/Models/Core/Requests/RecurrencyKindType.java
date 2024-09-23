package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests;

/**
 * Enumeración que define los tipos de recurrencia para un perfil de carga en OCPP 1.6.
 * Estos valores son utilizados para establecer la frecuencia con la que se aplicará
 * un perfil de carga, ya sea diario o semanal.
 */
public enum RecurrencyKindType {
    /** Perfil de carga que se aplica diariamente. */
    Daily,

    /** Perfil de carga que se aplica semanalmente. */
    Weekly
}
