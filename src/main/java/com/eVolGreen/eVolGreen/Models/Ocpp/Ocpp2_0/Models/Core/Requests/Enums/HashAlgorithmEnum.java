package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums;

/**
 * Enumeración que representa los algoritmos de hash disponibles para los datos proporcionados.
 *
 * <p>Se utiliza en el contexto del protocolo OCPP 2.0.1 para definir los algoritmos empleados en
 * la generación de valores hash.
 */
public enum HashAlgorithmEnum {

    /** Algoritmo de hash SHA-256. */
    SHA256,

    /** Algoritmo de hash SHA-384. */
    SHA384,

    /** Algoritmo de hash SHA-512. */
    SHA512
}
