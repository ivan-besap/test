package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.UploadLogStatusEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.LogStatusNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Requests.CustomerInformationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Requests.NotifyCustomerInformationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.GetLogRequest;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que gestiona la creación de solicitudes y el manejo de respuestas para el bloque funcional
 * de Diagnósticos en un cliente OCPP.
 *
 * <p>Proporciona métodos para crear solicitudes específicas y manejar las respuestas entrantes de
 * las estaciones de carga.
 */
public class ClientDiagnosticsFunction implements Function {

    private final ClientDiagnosticsEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor que inicializa el manejador de eventos y los rasgos funcionales relacionados
     * con diagnósticos.
     *
     * @param eventHandler El manejador para procesar las solicitudes entrantes relacionadas con diagnósticos.
     */
    public ClientDiagnosticsFunction(ClientDiagnosticsEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new ClearVariableMonitoringFeature(this));
        features.add(new CustomerInformationFeature(this));
        features.add(new GetLogFeature(this));
        features.add(new GetMonitoringReportFeature(this));
        features.add(new LogStatusNotificationFeature(null));
        features.add(new NotifyCustomerInformationFeature(null));
        features.add(new NotifyMonitoringReportFeature(null));
        features.add(new SetMonitoringBaseFeature(this));
        features.add(new SetMonitoringLevelFeature(this));
        features.add(new SetVariableMonitoringFeature(this));
    }

    /**
     * Devuelve la lista de características funcionales relacionadas con los diagnósticos.
     *
     * @return Un array de rasgos funcionales compatibles.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja una solicitud entrante relacionada con diagnósticos y devuelve la confirmación correspondiente.
     *
     * @param sessionIndex El identificador único de la sesión.
     * @param request La solicitud entrante.
     * @return La confirmación procesada o {@code null} si la solicitud no es compatible.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof ClearVariableMonitoringRequest) {
            return eventHandler.handleClearVariableMonitoringRequest(
                    (ClearVariableMonitoringRequest) request);
        } else if (request instanceof CustomerInformationRequest) {
            return eventHandler.handleCustomerInformationRequest((CustomerInformationRequest) request);
        } else if (request instanceof GetLogRequest) {
            return eventHandler.handleGetLogRequest((GetLogRequest) request);
        } else if (request instanceof GetMonitoringReportRequest) {
            return eventHandler.handleGetMonitoringReportRequest((GetMonitoringReportRequest) request);
        } else if (request instanceof SetMonitoringBaseRequest) {
            return eventHandler.handleSetMonitoringBaseRequest((SetMonitoringBaseRequest) request);
        } else if (request instanceof SetMonitoringLevelRequest) {
            return eventHandler.handleSetMonitoringLevelRequest((SetMonitoringLevelRequest) request);
        } else if (request instanceof SetVariableMonitoringRequest) {
            return eventHandler.handleSetVariableMonitoringRequest(
                    (SetVariableMonitoringRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud {@link LogStatusNotificationRequest} con todos los campos requeridos.
     *
     * @param status El estado de la carga del log.
     * @return Una instancia de {@link LogStatusNotificationRequest}.
     */
    public LogStatusNotificationRequest createLogStatusNotificationRequest(UploadLogStatusEnum status) {
        return new LogStatusNotificationRequest(status);
    }

    /**
     * Crea una solicitud {@link NotifyCustomerInformationRequest} con todos los campos requeridos.
     *
     * @param data Los datos solicitados (pueden estar parcializados). Deberían ser legibles por humanos.
     * @param seqNo El número de secuencia del mensaje. La numeración empieza en 0.
     * @param generatedAt La marca de tiempo en la que se generó el mensaje en la estación de carga.
     * @param requestId El identificador único de la solicitud.
     * @return Una instancia de {@link NotifyCustomerInformationRequest}.
     */
    public NotifyCustomerInformationRequest createNotifyCustomerInformationRequest(
            String data, Integer seqNo, ZonedDateTime generatedAt, Integer requestId) {
        return new NotifyCustomerInformationRequest(data, seqNo, generatedAt, requestId);
    }

    /**
     * Crea una solicitud {@link NotifyMonitoringReportRequest} con todos los campos requeridos.
     *
     * @param requestId El identificador único de la solicitud de monitoreo que originó este informe.
     * @param seqNo El número de secuencia del mensaje. La numeración empieza en 0.
     * @param generatedAt La marca de tiempo en la que se generó el mensaje en la estación de carga.
     * @return Una instancia de {@link NotifyMonitoringReportRequest}.
     */
    public NotifyMonitoringReportRequest createNotifyMonitoringReportRequest(
            Integer requestId, Integer seqNo, ZonedDateTime generatedAt) {
        return new NotifyMonitoringReportRequest(requestId, seqNo, generatedAt);
    }
}

