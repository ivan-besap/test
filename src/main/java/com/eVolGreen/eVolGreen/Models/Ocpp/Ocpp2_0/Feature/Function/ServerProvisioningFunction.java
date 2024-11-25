package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.BootNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.ResetEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.HeartbeatRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.ResetRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.SetNetworkProfileRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils.NetworkConnectionProfile;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.GetVariablesRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.NotifyReportRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.SetVariablesRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils.GetVariableData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils.SetVariableData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.Enums.ReportBaseEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.GetBaseReportRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.GetReportRequest;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que gestiona las solicitudes y respuestas del bloque funcional de aprovisionamiento
 * (Provisioning) en el servidor.
 * <p>
 * Permite crear solicitudes específicas y manejar peticiones relacionadas con el aprovisionamiento
 * en OCPP 2.0.1.
 */
public class ServerProvisioningFunction implements Function {

    private final ServerProvisioningEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor para inicializar el manejador de eventos y las características disponibles.
     *
     * @param eventHandler Manejador de eventos para procesar solicitudes relacionadas con el
     *                     aprovisionamiento.
     */
    public ServerProvisioningFunction(ServerProvisioningEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        this.features = new ArrayList<>();
        this.features.add(new BootNotificationFeature(this));
        this.features.add(new GetBaseReportFeature(null));
        this.features.add(new GetReportFeature(null));
        this.features.add(new GetVariablesFeature(null));
        this.features.add(new HeartbeatFeature(this));
        this.features.add(new NotifyReportFeature(this));
        this.features.add(new ResetFeature(null));
        this.features.add(new SetNetworkProfileFeature(null));
        this.features.add(new SetVariablesFeature(null));
    }

    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof BootNotificationRequest) {
            return eventHandler.handleBootNotificationRequest(sessionIndex, (BootNotificationRequest) request);
        } else if (request instanceof HeartbeatRequest) {
            return eventHandler.handleHeartbeatRequest(sessionIndex, (HeartbeatRequest) request);
        } else if (request instanceof NotifyReportRequest) {
            return eventHandler.handleNotifyReportRequest(sessionIndex, (NotifyReportRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud de {@link GetBaseReportRequest} con los campos requeridos.
     *
     * @param requestId  Identificador único de la solicitud.
     * @param reportBase Base del reporte requerido.
     * @return Una instancia de {@link GetBaseReportRequest}.
     */
    public GetBaseReportRequest createGetBaseReportRequest(Integer requestId, ReportBaseEnum reportBase) {
        return new GetBaseReportRequest(requestId, reportBase);
    }

    /**
     * Crea una solicitud de {@link GetReportRequest} con los campos requeridos.
     *
     * @param requestId Identificador único de la solicitud.
     * @return Una instancia de {@link GetReportRequest}.
     */
    public GetReportRequest createGetReportRequest(Integer requestId) {
        return new GetReportRequest(requestId);
    }

    /**
     * Crea una solicitud de {@link GetVariablesRequest} con los campos requeridos.
     *
     * @param getVariableData Array con los datos de las variables requeridas.
     * @return Una instancia de {@link GetVariablesRequest}.
     */
    public GetVariablesRequest createGetVariablesRequest(GetVariableData[] getVariableData) {
        return new GetVariablesRequest(getVariableData);
    }

    /**
     * Crea una solicitud de {@link ResetRequest} con los campos requeridos.
     *
     * @param type Tipo de reinicio que debe realizar la estación de carga.
     * @return Una instancia de {@link ResetRequest}.
     */
    public ResetRequest createResetRequest(ResetEnum type) {
        return new ResetRequest(type);
    }

    /**
     * Crea una solicitud de {@link SetNetworkProfileRequest} con los campos requeridos.
     *
     * @param configurationSlot Slot en el que debe almacenarse la configuración.
     * @param connectionData    Perfil de conexión de red con parámetros funcionales y técnicos.
     * @return Una instancia de {@link SetNetworkProfileRequest}.
     */
    public SetNetworkProfileRequest createSetNetworkProfileRequest(
            Integer configurationSlot, NetworkConnectionProfile connectionData) {
        return new SetNetworkProfileRequest(configurationSlot, connectionData);
    }

    /**
     * Crea una solicitud de {@link SetVariablesRequest} con los campos requeridos.
     *
     * @param setVariableData Array con los datos de las variables que deben establecerse.
     * @return Una instancia de {@link SetVariablesRequest}.
     */
    public SetVariablesRequest createSetVariablesRequest(SetVariableData[] setVariableData) {
        return new SetVariablesRequest(setVariableData);
    }
}
