package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Request;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.BootNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.BootReasonEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.HeartbeatRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.ResetRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.SetNetworkProfileRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils.ChargingStation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.GetVariablesRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.NotifyReportRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.SetVariablesRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.GetBaseReportRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.GetReportRequest;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Clase que proporciona métodos para la creación de solicitudes del cliente y el manejo de eventos
 * relacionados con el bloque funcional de aprovisionamiento en OCPP.
 *
 * Este bloque funcional incluye características como notificación de inicio del sistema,
 * configuración de variables, perfiles de red y generación de informes.
 */
public class ClientProvisioningFunction implements Function {

    private final ClientProvisioningEventHandler eventHandler;
    private final ArrayList<FunctionFeature> features;

    /**
     * Constructor que inicializa el manejador de eventos y las características funcionales
     * relacionadas con el aprovisionamiento.
     *
     * @param eventHandler Manejador de eventos para solicitudes del bloque funcional.
     */
    public ClientProvisioningFunction(ClientProvisioningEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        features = new ArrayList<>();
        features.add(new BootNotificationFeature(null));
        features.add(new GetBaseReportFeature(this));
        features.add(new GetReportFeature(this));
        features.add(new GetVariablesFeature(this));
        features.add(new HeartbeatFeature(null));
        features.add(new NotifyReportFeature(null));
        features.add(new ResetFeature(this));
        features.add(new SetNetworkProfileFeature(this));
        features.add(new SetVariablesFeature(this));
    }

    /**
     * Obtiene la lista de características funcionales soportadas por esta clase.
     *
     * @return Un array de objetos {@link FunctionFeature} que representan las características.
     */
    @Override
    public FunctionFeature[] getFeatureList() {
        return features.toArray(new FunctionFeature[0]);
    }

    /**
     * Maneja solicitudes entrantes relacionadas con el bloque funcional de aprovisionamiento.
     *
     * @param sessionIndex Índice de la sesión que envía la solicitud.
     * @param request Objeto de tipo {@link Request} que representa la solicitud entrante.
     * @return Un objeto de tipo {@link Confirmation} con la respuesta correspondiente, o null si la
     *         solicitud no es manejada.
     */
    @Override
    public Confirmation handleRequest(UUID sessionIndex, Request request) {
        if (request instanceof GetBaseReportRequest) {
            return eventHandler.handleGetBaseReportRequest((GetBaseReportRequest) request);
        } else if (request instanceof GetReportRequest) {
            return eventHandler.handleGetReportRequest((GetReportRequest) request);
        } else if (request instanceof GetVariablesRequest) {
            return eventHandler.handleGetVariablesRequest((GetVariablesRequest) request);
        } else if (request instanceof ResetRequest) {
            return eventHandler.handleResetRequest((ResetRequest) request);
        } else if (request instanceof SetNetworkProfileRequest) {
            return eventHandler.handleSetNetworkProfileRequest((SetNetworkProfileRequest) request);
        } else if (request instanceof SetVariablesRequest) {
            return eventHandler.handleSetVariablesRequest((SetVariablesRequest) request);
        }
        return null;
    }

    /**
     * Crea una solicitud de notificación de inicio del sistema {@link BootNotificationRequest}.
     *
     * @param chargingStation Información sobre la estación de carga física.
     * @param reason Razón para enviar esta solicitud al CSMS.
     * @return Una instancia de {@link BootNotificationRequest}.
     */
    public BootNotificationRequest createBootNotificationRequest(
            ChargingStation chargingStation, BootReasonEnum reason) {
        return new BootNotificationRequest(chargingStation, reason);
    }

    /**
     * Crea una solicitud de latido de vida {@link HeartbeatRequest}.
     *
     * @return Una instancia de {@link HeartbeatRequest}.
     */
    public HeartbeatRequest createHeartbeatRequest() {
        return new HeartbeatRequest();
    }

    /**
     * Crea una solicitud de notificación de informe {@link NotifyReportRequest}.
     *
     * @param requestId ID de la solicitud que originó este informe.
     * @param generatedAt Marca de tiempo que indica cuándo se generó este mensaje en la estación de carga.
     * @param seqNo Número de secuencia de este mensaje. La numeración comienza en 0.
     * @return Una instancia de {@link NotifyReportRequest}.
     */
    public NotifyReportRequest createNotifyReportRequest(
            Integer requestId, ZonedDateTime generatedAt, Integer seqNo) {
        return new NotifyReportRequest(requestId, generatedAt, seqNo);
    }
}

