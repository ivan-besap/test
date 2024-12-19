package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.Types;

/**
 * Enumeración que define los tipos de algoritmos de hash soportados.
 *
 * <p>Esta enumeración se utiliza para indicar el tipo de algoritmo de hash que
 * se emplea para generar los valores hash dentro de los datos de seguridad del certificado.</p>
 *
 * <p>Es referenciado por {@link CertificateHashDataType} y otros componentes de seguridad en el sistema OCPP.
 * Los algoritmos soportados incluyen SHA-256, SHA-384 y SHA-512.</p>
 */
public enum HashAlgorithmEnumType {

    /**
     * Algoritmo de hash SHA-256.
     */
    SHA256,

    /**
     * Algoritmo de hash SHA-384.
     */
    SHA384,

    /**
     * Algoritmo de hash SHA-512.
     */
    SHA512
}
