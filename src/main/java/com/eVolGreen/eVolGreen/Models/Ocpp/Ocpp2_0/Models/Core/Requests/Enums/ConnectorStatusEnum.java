package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums;

/**
 * Representa el estado actual del conector en un EVSE.
 *
 * <p>Posibles estados:
 * <ul>
 *   <li><b>Available:</b> El conector está disponible para ser utilizado.</li>
 *   <li><b>Occupied:</b> El conector está ocupado y en uso.</li>
 *   <li><b>Reserved:</b> El conector está reservado para un usuario específico.</li>
 *   <li><b>Unavailable:</b> El conector no está disponible temporalmente.</li>
 *   <li><b>Faulted:</b> El conector está en un estado de falla y no puede ser utilizado.</li>
 * </ul>
 */
public enum ConnectorStatusEnum {
    Available,   // El conector está disponible para su uso.
    Occupied,    // El conector está actualmente en uso.
    Reserved,    // El conector ha sido reservado para un cliente.
    Unavailable, // El conector no está operativo temporalmente.
    Faulted      // El conector presenta fallas y requiere intervención.
}
