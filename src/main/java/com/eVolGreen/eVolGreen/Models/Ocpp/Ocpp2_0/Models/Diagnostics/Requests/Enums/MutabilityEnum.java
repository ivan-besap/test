package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums;

/**
 * Enumeración que define la mutabilidad de un atributo en el protocolo OCPP.
 * <p>
 * Indica cómo puede interactuar un cliente o estación de carga con un atributo
 * en términos de lectura y escritura.
 * </p>
 * <ul>
 *     <li><b>ReadOnly</b>: Solo lectura, el valor no puede ser modificado.</li>
 *     <li><b>WriteOnly</b>: Solo escritura, el valor no puede ser leído.</li>
 *     <li><b>ReadWrite</b>: Lectura y escritura permitidas.</li>
 * </ul>
 * El valor predeterminado es {@code ReadWrite} si no se especifica.
 */
public enum MutabilityEnum {
    /** Solo lectura, el atributo no puede ser modificado. */
    ReadOnly,

    /** Solo escritura, el atributo no puede ser leído. */
    WriteOnly,

    /** Lectura y escritura permitidas. */
    ReadWrite
}
