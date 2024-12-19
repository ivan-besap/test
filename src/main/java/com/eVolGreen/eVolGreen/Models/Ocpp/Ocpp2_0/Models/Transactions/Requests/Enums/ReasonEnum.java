package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Enums;

/**
 * Razón por la cual la transacción fue detenida.
 *
 * <p>Este enum define los posibles motivos por los que una transacción puede ser finalizada en el
 * protocolo OCPP 2.0.1. Puede ser utilizado para proporcionar información adicional al CSMS sobre
 * por qué se detuvo una transacción.
 *
 * <p>Nota: Puede omitirse si la razón es "Local".
 */
public enum ReasonEnum {
    /** El cliente fue desautorizado durante la transacción. */
    DeAuthorized,

    /** Se activó una parada de emergencia en la estación de carga. */
    EmergencyStop,

    /** Se alcanzó el límite de energía establecido para la transacción. */
    EnergyLimitReached,

    /** El vehículo eléctrico fue desconectado del EVSE. */
    EVDisconnected,

    /** Se detectó una falla a tierra. */
    GroundFault,

    /** Se ejecutó un reinicio inmediato de la estación de carga. */
    ImmediateReset,

    /** La transacción fue detenida localmente sin proporcionar una razón específica. */
    Local,

    /** El crédito local del cliente se agotó. */
    LocalOutOfCredit,

    /** La transacción fue detenida utilizando una tarjeta maestra o credencial especial. */
    MasterPass,

    /** Otra razón no especificada en esta lista. */
    Other,

    /** Se detectó una sobrecorriente durante la carga. */
    OvercurrentFault,

    /** Se produjo una pérdida de energía en la estación de carga. */
    PowerLoss,

    /** Problemas de calidad de energía detectados durante la carga. */
    PowerQuality,

    /** La estación de carga se está reiniciando. */
    Reboot,

    /** La transacción fue detenida de forma remota por el CSMS. */
    Remote,

    /** Se alcanzó el límite de estado de carga (SoC) establecido. */
    SOCLimitReached,

    /** El vehículo eléctrico solicitó detener la carga. */
    StoppedByEV,

    /** Se alcanzó el límite de tiempo establecido para la transacción. */
    TimeLimitReached,

    /** La transacción fue detenida debido a un tiempo de espera. */
    Timeout
}
