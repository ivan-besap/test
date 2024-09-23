package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests;

/**
 * Enumeración que define los tipos de reinicio para un punto de carga en OCPP 1.6.
 * Un reinicio puede ser "Hard" (reinicio completo) o "Soft" (reinicio suave).
 */
public enum ResetType {
    /** Reinicio completo del sistema. */
    Hard,

    /** Reinicio suave del sistema. */
    Soft
}
