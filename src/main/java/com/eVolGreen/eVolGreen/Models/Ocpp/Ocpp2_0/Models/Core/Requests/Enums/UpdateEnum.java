package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums;

/**
 * Tipo de actualización que se debe realizar en una lista.
 *
 * Esta enumeración define si la actualización es completa o diferencial.
 */
public enum UpdateEnum {

    /**
     * Actualización diferencial.
     *
     * Se aplica únicamente a los cambios necesarios desde la última versión de la lista.
     */
    Differential,

    /**
     * Actualización completa.
     *
     * Reemplaza completamente la lista actual con una nueva versión.
     */
    Full
}

