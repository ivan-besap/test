package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Enums.ChargingLimitSourceEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Utils.ChargingProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Utils.ChargingSchedule;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests.NotifyChargingLimitRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests.NotifyEVChargingNeedsRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests.NotifyEVChargingScheduleRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests.Utils.ChargingLimit;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests.Utils.ChargingNeeds;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que proporciona creadores de solicitudes y manejadores para el bloque funcional
 * de Carga Inteligente (Smart Charging).
 * <p>
 * Incluye funcionalidades como manejo de perfiles de carga, notificaciones de necesidades
 * de carga de vehículos eléctricos y programación compuesta de horarios de carga.
 */
public class ClientSmartChargingFunction implements Function {

    private final ClientSmartChargingEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor que inicializa los manejadores de eventos y características asociadas.
     *
     * @param eventHandler Manejador de eventos para las solicitudes relacionadas con carga inteligente.
     */
    public ClientSmartChargingFunction(ClientSmartChargingEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new ClearChargingProfileFeature(this));
        features.add(new ClearedChargingLimitFeature(null));
        features.add(new GetChargingProfilesFeature(this));
        features.add(new GetCompositeScheduleFeature(this));
        features.add(new NotifyChargingLimitFeature(null));
        features.add(new NotifyEVChargingNeedsFeature(null));
        features.add(new NotifyEVChargingScheduleFeature(null));
        features.add(new ReportChargingProfilesFeature(null));
        features.add(new SetChargingProfileFeature(this));
    }

    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof ClearChargingProfileRequest) {
            return eventHandler.handleClearChargingProfileRequest((ClearChargingProfileRequest) request);
        } else if (request instanceof GetChargingProfilesRequest) {
            return eventHandler.handleGetChargingProfilesRequest((GetChargingProfilesRequest) request);
        } else if (request instanceof GetCompositeScheduleRequest) {
            return eventHandler.handleGetCompositeScheduleRequest((GetCompositeScheduleRequest) request);
        } else if (request instanceof SetChargingProfileRequest) {
            return eventHandler.handleSetChargingProfileRequest((SetChargingProfileRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud {@link ClearedChargingLimitRequest} con los campos requeridos.
     *
     * @param chargingLimitSource La fuente del límite de carga.
     * @return Una instancia de {@link ClearedChargingLimitRequest}.
     */
    public ClearedChargingLimitRequest createClearedChargingLimitRequest(
            ChargingLimitSourceEnum chargingLimitSource) {
        return new ClearedChargingLimitRequest(chargingLimitSource);
    }

    /**
     * Crea una solicitud {@link NotifyChargingLimitRequest} con los campos requeridos.
     *
     * @param chargingLimit Límite de carga especificado.
     * @return Una instancia de {@link NotifyChargingLimitRequest}.
     */
    public NotifyChargingLimitRequest createNotifyChargingLimitRequest(ChargingLimit chargingLimit) {
        return new NotifyChargingLimitRequest(chargingLimit);
    }

    /**
     * Crea una solicitud {@link NotifyEVChargingNeedsRequest} con los campos requeridos.
     *
     * @param chargingNeeds Necesidades de carga del vehículo eléctrico.
     * @param evseId Identificador de la estación de carga (EVSE). No puede ser 0.
     * @return Una instancia de {@link NotifyEVChargingNeedsRequest}.
     */
    public NotifyEVChargingNeedsRequest createNotifyEVChargingNeedsRequest(
            ChargingNeeds chargingNeeds, Integer evseId) {
        return new NotifyEVChargingNeedsRequest(chargingNeeds, evseId);
    }

    /**
     * Crea una solicitud {@link NotifyEVChargingScheduleRequest} con los campos requeridos.
     *
     * @param timeBase Punto de referencia para los períodos contenidos en el perfil de carga.
     * @param chargingSchedule Estructura del horario de carga que incluye períodos de carga.
     * @param evseId Identificador de la estación de carga (EVSE). Debe ser mayor a 0.
     * @return Una instancia de {@link NotifyEVChargingScheduleRequest}.
     */
    public NotifyEVChargingScheduleRequest createNotifyEVChargingScheduleRequest(
            ZonedDateTime timeBase, ChargingSchedule chargingSchedule, Integer evseId) {
        return new NotifyEVChargingScheduleRequest(timeBase, chargingSchedule, evseId);
    }

    /**
     * Crea una solicitud {@link ReportChargingProfilesRequest} con los campos requeridos.
     *
     * @param requestId Identificador de la solicitud inicial de obtención de perfiles de carga.
     * @param chargingLimitSource Fuente que instaló este perfil de carga.
     * @param chargingProfile Perfiles de carga que describen los períodos de carga.
     * @param evseId Identificador de la estación de carga (EVSE). Si es 0, aplica al cargador completo.
     * @return Una instancia de {@link ReportChargingProfilesRequest}.
     */
    public ReportChargingProfilesRequest createReportChargingProfilesRequest(
            Integer requestId,
            ChargingLimitSourceEnum chargingLimitSource,
            ChargingProfile[] chargingProfile,
            Integer evseId) {
        return new ReportChargingProfilesRequest(requestId, chargingLimitSource, chargingProfile, evseId);
    }
}
