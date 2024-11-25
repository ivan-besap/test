package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Utils.ChargingProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Utils.ChargingProfileCriterion;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests.NotifyChargingLimitRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests.NotifyEVChargingNeedsRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests.NotifyEVChargingScheduleRequest;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que gestiona las solicitudes y respuestas relacionadas con el bloque funcional de SmartCharging.
 * Este bloque funcional incluye funcionalidades como la configuración de perfiles de carga, límites de carga,
 * y reportes sobre necesidades y horarios de carga de vehículos eléctricos.
 */
public class ServerSmartChargingFunction implements Function {

    private final ServerSmartChargingEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor que inicializa el manejador de eventos y las características asociadas al bloque de SmartCharging.
     *
     * @param eventHandler Manejador de eventos para procesar solicitudes relacionadas con SmartCharging.
     */
    public ServerSmartChargingFunction(ServerSmartChargingEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new ClearChargingProfileFeature(null));
        features.add(new ClearedChargingLimitFeature(this));
        features.add(new GetChargingProfilesFeature(null));
        features.add(new GetCompositeScheduleFeature(null));
        features.add(new NotifyChargingLimitFeature(this));
        features.add(new NotifyEVChargingNeedsFeature(this));
        features.add(new NotifyEVChargingScheduleFeature(this));
        features.add(new ReportChargingProfilesFeature(this));
        features.add(new SetChargingProfileFeature(null));
    }

    /**
     * Devuelve la lista de características soportadas por este bloque funcional.
     *
     * @return Un arreglo de objetos {@link FunctionFeature}.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja las solicitudes recibidas para este bloque funcional.
     *
     * @param sessionIndex Identificador de la sesión donde se recibió la solicitud.
     * @param request Solicitud entrante que necesita ser procesada.
     * @return Confirmación como respuesta a la solicitud procesada, o null si no se reconoce la solicitud.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof ClearedChargingLimitRequest) {
            return eventHandler.handleClearedChargingLimitRequest(
                    sessionIndex, (ClearedChargingLimitRequest) request);
        } else if (request instanceof NotifyChargingLimitRequest) {
            return eventHandler.handleNotifyChargingLimitRequest(
                    sessionIndex, (NotifyChargingLimitRequest) request);
        } else if (request instanceof NotifyEVChargingNeedsRequest) {
            return eventHandler.handleNotifyEVChargingNeedsRequest(
                    sessionIndex, (NotifyEVChargingNeedsRequest) request);
        } else if (request instanceof NotifyEVChargingScheduleRequest) {
            return eventHandler.handleNotifyEVChargingScheduleRequest(
                    sessionIndex, (NotifyEVChargingScheduleRequest) request);
        } else if (request instanceof ReportChargingProfilesRequest) {
            return eventHandler.handleReportChargingProfilesRequest(
                    sessionIndex, (ReportChargingProfilesRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud {@link ClearChargingProfileRequest}.
     *
     * @return Una instancia de {@link ClearChargingProfileRequest}.
     */
    public ClearChargingProfileRequest createClearChargingProfileRequest() {
        return new ClearChargingProfileRequest();
    }

    /**
     * Crea una solicitud {@link GetChargingProfilesRequest}.
     *
     * @param requestId Identificador de la solicitud para ser referenciado en el reporte.
     * @param chargingProfile Criterios del perfil de carga solicitado.
     * @return Una instancia de {@link GetChargingProfilesRequest}.
     */
    public GetChargingProfilesRequest createGetChargingProfilesRequest(
            Integer requestId, ChargingProfileCriterion chargingProfile) {
        return new GetChargingProfilesRequest(requestId, chargingProfile);
    }

    /**
     * Crea una solicitud {@link GetCompositeScheduleRequest}.
     *
     * @param duration Duración en segundos para la cual se solicita el horario compuesto.
     * @param evseId Identificador del EVSE para el cual se solicita el horario.
     * @return Una instancia de {@link GetCompositeScheduleRequest}.
     */
    public GetCompositeScheduleRequest createGetCompositeScheduleRequest(
            Integer duration, Integer evseId) {
        return new GetCompositeScheduleRequest(duration, evseId);
    }

    /**
     * Crea una solicitud {@link SetChargingProfileRequest}.
     *
     * @param evseId Identificador del EVSE al que se aplica el perfil de carga.
     * @param chargingProfile Perfil de carga que se aplicará.
     * @return Una instancia de {@link SetChargingProfileRequest}.
     */
    public SetChargingProfileRequest createSetChargingProfileRequest(
            Integer evseId, ChargingProfile chargingProfile) {
        return new SetChargingProfileRequest(evseId, chargingProfile);
    }
}
