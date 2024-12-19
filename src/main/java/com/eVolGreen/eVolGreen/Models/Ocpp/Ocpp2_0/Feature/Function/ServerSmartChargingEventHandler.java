package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.ClearedChargingLimitResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.ReportChargingProfilesResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.ClearedChargingLimitRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.ReportChargingProfilesRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Confirmation.NotifyChargingLimitResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Confirmation.NotifyEVChargingNeedsResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Confirmation.NotifyEVChargingScheduleResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests.NotifyChargingLimitRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests.NotifyEVChargingNeedsRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests.NotifyEVChargingScheduleRequest;

import java.util.UUID;

/**
 * Interfaz para manejar los eventos del bloque funcional de Carga Inteligente (SmartCharging) en el servidor.
 * Proporciona métodos para procesar solicitudes específicas relacionadas con la gestión de carga inteligente.
 */
public interface ServerSmartChargingEventHandler {

    /**
     * Maneja una solicitud de límite de carga borrado y devuelve una respuesta correspondiente.
     *
     * @param sessionIndex Identificador de la sesión donde se recibió la solicitud.
     * @param request Solicitud entrante de tipo {@link ClearedChargingLimitRequest}.
     * @return Respuesta de tipo {@link ClearedChargingLimitResponse}.
     */
    ClearedChargingLimitResponse handleClearedChargingLimitRequest(
            UUID sessionIndex, ClearedChargingLimitRequest request);

    /**
     * Maneja una solicitud de notificación de límite de carga y devuelve una respuesta correspondiente.
     *
     * @param sessionIndex Identificador de la sesión donde se recibió la solicitud.
     * @param request Solicitud entrante de tipo {@link NotifyChargingLimitRequest}.
     * @return Respuesta de tipo {@link NotifyChargingLimitResponse}.
     */
    NotifyChargingLimitResponse handleNotifyChargingLimitRequest(
            UUID sessionIndex, NotifyChargingLimitRequest request);

    /**
     * Maneja una solicitud de notificación de necesidades de carga del vehículo eléctrico
     * y devuelve una respuesta correspondiente.
     *
     * @param sessionIndex Identificador de la sesión donde se recibió la solicitud.
     * @param request Solicitud entrante de tipo {@link NotifyEVChargingNeedsRequest}.
     * @return Respuesta de tipo {@link NotifyEVChargingNeedsResponse}.
     */
    NotifyEVChargingNeedsResponse handleNotifyEVChargingNeedsRequest(
            UUID sessionIndex, NotifyEVChargingNeedsRequest request);

    /**
     * Maneja una solicitud de notificación de programación de carga del vehículo eléctrico
     * y devuelve una respuesta correspondiente.
     *
     * @param sessionIndex Identificador de la sesión donde se recibió la solicitud.
     * @param request Solicitud entrante de tipo {@link NotifyEVChargingScheduleRequest}.
     * @return Respuesta de tipo {@link NotifyEVChargingScheduleResponse}.
     */
    NotifyEVChargingScheduleResponse handleNotifyEVChargingScheduleRequest(
            UUID sessionIndex, NotifyEVChargingScheduleRequest request);

    /**
     * Maneja una solicitud de informe de perfiles de carga y devuelve una respuesta correspondiente.
     *
     * @param sessionIndex Identificador de la sesión donde se recibió la solicitud.
     * @param request Solicitud entrante de tipo {@link ReportChargingProfilesRequest}.
     * @return Respuesta de tipo {@link ReportChargingProfilesResponse}.
     */
    ReportChargingProfilesResponse handleReportChargingProfilesRequest(
            UUID sessionIndex, ReportChargingProfilesRequest request);
}
