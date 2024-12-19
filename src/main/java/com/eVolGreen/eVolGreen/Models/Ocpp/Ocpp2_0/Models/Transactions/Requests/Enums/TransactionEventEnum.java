package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Enums;

/**
 * Enum que define los posibles tipos de eventos asociados a una transacción en el protocolo OCPP 2.0.1.
 *
 * <p>Los eventos se utilizan para identificar el estado de una transacción en su ciclo de vida:
 *
 * <ul>
 *   <li><b>Started</b>: Indica que la transacción ha iniciado.
 *   <li><b>Updated</b>: Representa un cambio o actualización en la transacción activa.
 *   <li><b>Ended</b>: Señala la finalización de la transacción.
 * </ul>
 *
 * <p>Restricciones:
 *
 * <ul>
 *   <li>El primer evento de una transacción debe ser "Started".
 *   <li>El último evento de una transacción debe ser "Ended".
 *   <li>Todos los demás eventos intermedios deben ser "Updated".
 * </ul>
 */
public enum TransactionEventEnum {
    /** Representa el inicio de una transacción. */
    Started,

    /** Representa una actualización de los datos de una transacción en curso. */
    Updated,

    /** Representa el fin de una transacción. */
    Ended
}
