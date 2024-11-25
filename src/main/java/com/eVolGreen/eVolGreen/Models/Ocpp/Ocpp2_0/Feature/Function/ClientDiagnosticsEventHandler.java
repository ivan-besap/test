package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Confirmation.CustomerInformationResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Requests.CustomerInformationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Confirmation.GetLogResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.GetLogRequest;

/**
 * Interfaz para manejar eventos relacionados con el bloque funcional de Diagnósticos en un cliente OCPP.
 *
 * <p>Proporciona métodos para manejar solicitudes de diagnóstico y devolver las respuestas correspondientes.
 */
public interface ClientDiagnosticsEventHandler {

    /**
     * Maneja una solicitud de monitoreo de variables y devuelve la respuesta correspondiente.
     *
     * @param request La solicitud entrante de tipo {@link ClearVariableMonitoringRequest}.
     * @return La respuesta de tipo {@link ClearVariableMonitoringResponse}.
     */
    ClearVariableMonitoringResponse handleClearVariableMonitoringRequest(
            ClearVariableMonitoringRequest request);

    /**
     * Maneja una solicitud de información del cliente y devuelve la respuesta correspondiente.
     *
     * @param request La solicitud entrante de tipo {@link CustomerInformationRequest}.
     * @return La respuesta de tipo {@link CustomerInformationResponse}.
     */
    CustomerInformationResponse handleCustomerInformationRequest(CustomerInformationRequest request);

    /**
     * Maneja una solicitud para obtener registros y devuelve la respuesta correspondiente.
     *
     * @param request La solicitud entrante de tipo {@link GetLogRequest}.
     * @return La respuesta de tipo {@link GetLogResponse}.
     */
    GetLogResponse handleGetLogRequest(GetLogRequest request);

    /**
     * Maneja una solicitud para obtener un informe de monitoreo y devuelve la respuesta correspondiente.
     *
     * @param request La solicitud entrante de tipo {@link GetMonitoringReportRequest}.
     * @return La respuesta de tipo {@link GetMonitoringReportResponse}.
     */
    GetMonitoringReportResponse handleGetMonitoringReportRequest(GetMonitoringReportRequest request);

    /**
     * Maneja una solicitud para establecer la base de monitoreo y devuelve la respuesta correspondiente.
     *
     * @param request La solicitud entrante de tipo {@link SetMonitoringBaseRequest}.
     * @return La respuesta de tipo {@link SetMonitoringBaseResponse}.
     */
    SetMonitoringBaseResponse handleSetMonitoringBaseRequest(SetMonitoringBaseRequest request);

    /**
     * Maneja una solicitud para establecer el nivel de monitoreo y devuelve la respuesta correspondiente.
     *
     * @param request La solicitud entrante de tipo {@link SetMonitoringLevelRequest}.
     * @return La respuesta de tipo {@link SetMonitoringLevelResponse}.
     */
    SetMonitoringLevelResponse handleSetMonitoringLevelRequest(SetMonitoringLevelRequest request);

    /**
     * Maneja una solicitud para establecer el monitoreo de variables y devuelve la respuesta correspondiente.
     *
     * @param request La solicitud entrante de tipo {@link SetVariableMonitoringRequest}.
     * @return La respuesta de tipo {@link SetVariableMonitoringResponse}.
     */
    SetVariableMonitoringResponse handleSetVariableMonitoringRequest(
            SetVariableMonitoringRequest request);
}

