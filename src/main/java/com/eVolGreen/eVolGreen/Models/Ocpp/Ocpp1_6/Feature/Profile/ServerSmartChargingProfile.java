package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.Profile;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Feature.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests.Utils.ChargingProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Request.ClearChargingProfileRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Request.GetCompositeScheduleRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.SmartCharging.Request.SetChargingProfileRequest;

import java.util.HashSet;
import java.util.UUID;

/**
 * Perfil de Smart Charging para el servidor, permitiendo la gestión de perfiles de carga,
 * la obtención de horarios compuestos y la limpieza de perfiles de carga.
 */
public class ServerSmartChargingProfile implements Profile {

    private HashSet<Feature> features;

    /**
     * Constructor que inicializa las características del perfil de Smart Charging en el servidor.
     */
    public ServerSmartChargingProfile() {
        features = new HashSet<>();
        features.add(new ClearChargingProfileFeature(null));
        features.add(new GetCompositeScheduleFeature(null));
        features.add(new SetChargingProfileFeature(null));
    }

    /**
     * Devuelve una lista de las características soportadas por este perfil.
     *
     * @return un array de {@link ProfileFeature} con las características soportadas.
     */
    @Override
    public ProfileFeature[] getFeatureList() {
        return features.toArray(new ProfileFeature[0]);
    }

    /**
     * Maneja las solicitudes recibidas y devuelve la confirmación correspondiente.
     *
     * @param sessionIndex el identificador único de la sesión.
     * @param request la solicitud recibida.
     * @return una confirmación de tipo {@link Confirmation}.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        return null; // Implementación futura para manejar solicitudes de Smart Charging
    }

    /**
     * Crea una solicitud de establecimiento de perfil de carga con los valores requeridos.
     *
     * @param connectorId Integer, identificador del conector.
     * @param chargingProfile el perfil de carga {@link ChargingProfile}.
     * @return una instancia de {@link SetChargingProfileRequest}.
     */
    public SetChargingProfileRequest createSetChargingProfileRequest(
            Integer connectorId, ChargingProfile chargingProfile) {
        return new SetChargingProfileRequest(connectorId, chargingProfile);
    }

    /**
     * Crea una solicitud de limpieza de perfil de carga.
     *
     * @return una instancia de {@link ClearChargingProfileRequest}.
     */
    public ClearChargingProfileRequest createClearChargingProfileRequest() {
        return new ClearChargingProfileRequest();
    }

    /**
     * Crea una solicitud para obtener un horario compuesto con los valores requeridos.
     *
     * @param connectorId Integer, identificador del conector.
     * @param duration Integer, duración en segundos.
     * @return una instancia de {@link GetCompositeScheduleRequest}.
     */
    public GetCompositeScheduleRequest createGetCompositeScheduleRequest(
            Integer connectorId, Integer duration) {
        return new GetCompositeScheduleRequest(connectorId, duration);
    }
}
