package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Enums;

/**
 * Enum UnlockStatusEnum
 *
 * <p>Define el estado resultante del intento de desbloqueo de un conector en una estación de
 * carga.
 *
 * <p>OCPP 2.0.1 FINAL
 */
public enum UnlockStatusEnum {
    /** El conector se desbloqueó con éxito. */
    Unlocked,

    /** Falló el intento de desbloqueo del conector. */
    UnlockFailed,

    /**
     * El desbloqueo no fue posible debido a que una transacción autorizada está en curso.
     */
    OngoingAuthorizedTransaction,

    /** El conector especificado es desconocido. */
    UnknownConnector
}
