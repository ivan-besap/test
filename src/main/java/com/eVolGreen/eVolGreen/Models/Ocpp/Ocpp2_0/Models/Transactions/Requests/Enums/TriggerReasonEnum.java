package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Enums;

/**
 * Enumeración que representa las razones por las cuales una estación de carga (Charging Station)
 * envía un mensaje al Sistema Central de Gestión (CSMS) en el protocolo OCPP 2.0.1.
 *
 * <p>Estas razones especifican eventos o condiciones que desencadenan la comunicación entre
 * la estación de carga y el CSMS.
 *
 * <p>Lista de razones:
 * <ul>
 *   <li><b>Authorized</b>: Un cliente fue autorizado para iniciar una sesión de carga.</li>
 *   <li><b>CablePluggedIn</b>: El cable de carga fue conectado al vehículo eléctrico.</li>
 *   <li><b>ChargingRateChanged</b>: Cambios en la velocidad de carga.</li>
 *   <li><b>ChargingStateChanged</b>: Cambio en el estado del proceso de carga.</li>
 *   <li><b>Deauthorized</b>: El cliente fue desautorizado para continuar la carga.</li>
 *   <li><b>EnergyLimitReached</b>: Se alcanzó el límite de energía establecido.</li>
 *   <li><b>EVCommunicationLost</b>: Pérdida de comunicación con el vehículo eléctrico.</li>
 *   <li><b>EVConnectTimeout</b>: Tiempo de espera agotado al intentar conectar al vehículo eléctrico.</li>
 *   <li><b>MeterValueClock</b>: Se recibió un valor del medidor basado en el reloj.</li>
 *   <li><b>MeterValuePeriodic</b>: Se recibió un valor del medidor de forma periódica.</li>
 *   <li><b>TimeLimitReached</b>: Se alcanzó el límite de tiempo de la sesión de carga.</li>
 *   <li><b>Trigger</b>: Evento activado manualmente.</li>
 *   <li><b>UnlockCommand</b>: Solicitud para desbloquear el conector.</li>
 *   <li><b>StopAuthorized</b>: Autorización para detener la carga.</li>
 *   <li><b>EVDeparted</b>: El vehículo eléctrico se desconectó de la estación.</li>
 *   <li><b>EVDetected</b>: Un vehículo eléctrico fue detectado en la estación.</li>
 *   <li><b>RemoteStop</b>: Solicitud remota para detener la carga.</li>
 *   <li><b>RemoteStart</b>: Solicitud remota para iniciar la carga.</li>
 *   <li><b>AbnormalCondition</b>: Se detectó una condición anormal en el sistema.</li>
 *   <li><b>SignedDataReceived</b>: Se recibió información firmada digitalmente.</li>
 *   <li><b>ResetCommand</b>: Comando de reinicio ejecutado.</li>
 * </ul>
 */
public enum TriggerReasonEnum {
    Authorized,
    CablePluggedIn,
    ChargingRateChanged,
    ChargingStateChanged,
    Deauthorized,
    EnergyLimitReached,
    EVCommunicationLost,
    EVConnectTimeout,
    MeterValueClock,
    MeterValuePeriodic,
    TimeLimitReached,
    Trigger,
    UnlockCommand,
    StopAuthorized,
    EVDeparted,
    EVDetected,
    RemoteStop,
    RemoteStart,
    AbnormalCondition,
    SignedDataReceived,
    ResetCommand
}
