package com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile;

import com.eVolGreen.eVolGreen.Models.Ocpp.Models.SmartCharging.*;

/**
 * La interfaz {@code ClientSmartChargingEventHandler} define los métodos de manejo de eventos
 * para gestionar la carga inteligente (Smart Charging) en las estaciones de carga.
 * <p>
 * Estos eventos permiten que el backend gestione los perfiles de carga, limpiando o
 * configurando estos perfiles según sea necesario, y también obtener el horario compuesto
 * de carga (composite schedule).
 * </p>
 */
public interface ClientSmartChargingEventHandler {

    /**
     * Maneja una solicitud para configurar un perfil de carga, {@link SetChargingProfileRequest},
     * y devuelve una confirmación correspondiente {@link SetChargingProfileConfirmation}.
     *
     * @param request la solicitud entrante {@link SetChargingProfileRequest}.
     * @return una respuesta {@link SetChargingProfileConfirmation} como confirmación de la solicitud.
     */
    SetChargingProfileConfirmation handleSetChargingProfileRequest(SetChargingProfileRequest request);

    /**
     * Maneja una solicitud para limpiar un perfil de carga, {@link ClearChargingProfileRequest},
     * y devuelve una confirmación correspondiente {@link ClearChargingProfileConfirmation}.
     *
     * @param request la solicitud entrante {@link ClearChargingProfileRequest}.
     * @return una respuesta {@link ClearChargingProfileConfirmation} como confirmación de la solicitud.
     */
    ClearChargingProfileConfirmation handleClearChargingProfileRequest(ClearChargingProfileRequest request);

    /**
     * Maneja una solicitud para obtener un horario compuesto de carga, {@link GetCompositeScheduleRequest},
     * y devuelve una confirmación correspondiente {@link GetCompositeScheduleConfirmation}.
     *
     * @param request la solicitud entrante {@link GetCompositeScheduleRequest}.
     * @return una respuesta {@link GetCompositeScheduleConfirmation} como confirmación de la solicitud.
     */
    GetCompositeScheduleConfirmation handleGetCompositeScheduleRequest(GetCompositeScheduleRequest request);
}
