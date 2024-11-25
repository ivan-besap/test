package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.ClearChargingProfileResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.GetChargingProfilesResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.GetCompositeScheduleResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation.SetChargingProfileResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.ClearChargingProfileRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.GetChargingProfilesRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.GetCompositeScheduleRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.SetChargingProfileRequest;

/**
 * Interfaz para manejar eventos relacionados con el bloque funcional de Carga Inteligente (Smart Charging).
 * <p>
 * Proporciona métodos para gestionar solicitudes entrantes relacionadas con perfiles de carga,
 * programación compuesta y configuraciones avanzadas de carga.
 */
public interface ClientSmartChargingEventHandler {

    /**
     * Maneja una solicitud de eliminación de perfiles de carga ({@link ClearChargingProfileRequest})
     * y devuelve una respuesta correspondiente ({@link ClearChargingProfileResponse}).
     *
     * @param request La solicitud entrante para eliminar un perfil de carga.
     * @return La respuesta de la eliminación del perfil de carga.
     */
    ClearChargingProfileResponse handleClearChargingProfileRequest(
            ClearChargingProfileRequest request);

    /**
     * Maneja una solicitud para obtener perfiles de carga ({@link GetChargingProfilesRequest})
     * y devuelve una respuesta correspondiente ({@link GetChargingProfilesResponse}).
     *
     * @param request La solicitud entrante para obtener perfiles de carga.
     * @return La respuesta con los perfiles de carga solicitados.
     */
    GetChargingProfilesResponse handleGetChargingProfilesRequest(
            GetChargingProfilesRequest request);

    /**
     * Maneja una solicitud para obtener un horario compuesto ({@link GetCompositeScheduleRequest})
     * y devuelve una respuesta correspondiente ({@link GetCompositeScheduleResponse}).
     *
     * @param request La solicitud entrante para obtener un horario compuesto.
     * @return La respuesta con el horario compuesto solicitado.
     */
    GetCompositeScheduleResponse handleGetCompositeScheduleRequest(
            GetCompositeScheduleRequest request);

    /**
     * Maneja una solicitud para establecer un perfil de carga ({@link SetChargingProfileRequest})
     * y devuelve una respuesta correspondiente ({@link SetChargingProfileResponse}).
     *
     * @param request La solicitud entrante para establecer un perfil de carga.
     * @return La respuesta después de establecer el perfil de carga.
     */
    SetChargingProfileResponse handleSetChargingProfileRequest(
            SetChargingProfileRequest request);
}
