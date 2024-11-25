package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Confirmation.Enums;

/**
 * Enum para indicar el estado de una solicitud de información del cliente.
 *
 * <p>Representa los posibles resultados de la solicitud `CustomerInformationRequest`.</p>
 *
 * <ul>
 *   <li><b>Accepted:</b> La solicitud fue aceptada y se procesará.</li>
 *   <li><b>Rejected:</b> La solicitud fue rechazada y no se procesará.</li>
 *   <li><b>Invalid:</b> La solicitud es inválida debido a datos incorrectos o faltantes.</li>
 * </ul>
 *
 * <p>OCPP 2.0.1 FINAL</p>
 */
public enum CustomerInformationStatusEnum {
    /** La solicitud fue aceptada y se procesará. */
    Accepted,

    /** La solicitud fue rechazada y no se procesará. */
    Rejected,

    /** La solicitud es inválida debido a datos incorrectos o faltantes. */
    Invalid
}
