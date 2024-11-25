package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.LogStatusNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Enums.MonitoringBaseEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils.SetMonitoringData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Requests.CustomerInformationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Requests.NotifyCustomerInformationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.Enums.LogEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.GetLogRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.Utils.LogParameters;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que contiene los creadores de solicitudes y manejadores para el bloque funcional
 * de Diagnósticos en el servidor.
 *
 * <p>Esta clase se encarga de gestionar las solicitudes relacionadas con diagnósticos,
 * como la configuración de monitoreo, notificaciones y generación de reportes.</p>
 */
public class ServerDiagnosticsFunction implements Function {

    private final ServerDiagnosticsEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor de la clase.
     *
     * @param eventHandler Manejador de eventos asociado a las solicitudes de diagnósticos.
     */
    public ServerDiagnosticsFunction(ServerDiagnosticsEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new ClearVariableMonitoringFeature(null));
        features.add(new CustomerInformationFeature(null));
        features.add(new GetLogFeature(null));
        features.add(new GetMonitoringReportFeature(null));
        features.add(new LogStatusNotificationFeature(this));
        features.add(new NotifyCustomerInformationFeature(this));
        features.add(new NotifyMonitoringReportFeature(this));
        features.add(new SetMonitoringBaseFeature(null));
        features.add(new SetMonitoringLevelFeature(null));
        features.add(new SetVariableMonitoringFeature(null));
    }

    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof LogStatusNotificationRequest) {
            return eventHandler.handleLogStatusNotificationRequest(
                    sessionIndex, (LogStatusNotificationRequest) request);
        } else if (request instanceof NotifyCustomerInformationRequest) {
            return eventHandler.handleNotifyCustomerInformationRequest(
                    sessionIndex, (NotifyCustomerInformationRequest) request);
        } else if (request instanceof NotifyMonitoringReportRequest) {
            return eventHandler.handleNotifyMonitoringReportRequest(
                    sessionIndex, (NotifyMonitoringReportRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud {@link ClearVariableMonitoringRequest}.
     *
     * @param id Lista de identificadores de los monitores que se deben borrar.
     * @return una instancia de {@link ClearVariableMonitoringRequest}.
     */
    public ClearVariableMonitoringRequest createClearVariableMonitoringRequest(Integer[] id) {
        return new ClearVariableMonitoringRequest(id);
    }

    /**
     * Crea una solicitud {@link CustomerInformationRequest}.
     *
     * @param requestId Identificador de la solicitud.
     * @param report Indicador para que la estación de carga devuelva información del cliente.
     * @param clear Indicador para que la estación de carga elimine la información del cliente.
     * @return una instancia de {@link CustomerInformationRequest}.
     */
    public CustomerInformationRequest createCustomerInformationRequest(
            Integer requestId, Boolean report, Boolean clear) {
        return new CustomerInformationRequest(requestId, report, clear);
    }

    /**
     * Crea una solicitud {@link GetLogRequest}.
     *
     * @param log Parámetros del archivo de registro solicitado.
     * @param logType Tipo de archivo de registro que debe enviar la estación de carga.
     * @param requestId Identificador de la solicitud.
     * @return una instancia de {@link GetLogRequest}.
     */
    public GetLogRequest createGetLogRequest(LogParameters log, LogEnum logType, Integer requestId) {
        return new GetLogRequest(log, logType, requestId);
    }

    /**
     * Crea una solicitud {@link GetMonitoringReportRequest}.
     *
     * @param requestId Identificador de la solicitud.
     * @return una instancia de {@link GetMonitoringReportRequest}.
     */
    public GetMonitoringReportRequest createGetMonitoringReportRequest(Integer requestId) {
        return new GetMonitoringReportRequest(requestId);
    }

    /**
     * Crea una solicitud {@link SetMonitoringBaseRequest}.
     *
     * @param monitoringBase Base de monitoreo que se configurará.
     * @return una instancia de {@link SetMonitoringBaseRequest}.
     */
    public SetMonitoringBaseRequest createSetMonitoringBaseRequest(MonitoringBaseEnum monitoringBase) {
        return new SetMonitoringBaseRequest(monitoringBase);
    }

    /**
     * Crea una solicitud {@link SetMonitoringLevelRequest}.
     *
     * @param severity Nivel de severidad que se reportará. El rango de severidad es de 0 (máximo)
     *                 a 9 (mínimo).
     * @return una instancia de {@link SetMonitoringLevelRequest}.
     */
    public SetMonitoringLevelRequest createSetMonitoringLevelRequest(Integer severity) {
        return new SetMonitoringLevelRequest(severity);
    }

    /**
     * Crea una solicitud {@link SetVariableMonitoringRequest}.
     *
     * @param setMonitoringData Lista de parámetros para configurar el monitoreo de variables.
     * @return una instancia de {@link SetVariableMonitoringRequest}.
     */
    public SetVariableMonitoringRequest createSetVariableMonitoringRequest(
            SetMonitoringData[] setMonitoringData) {
        return new SetVariableMonitoringRequest(setMonitoringData);
    }
}
