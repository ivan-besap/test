package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.ResetResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.SetNetworkProfileResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.ResetRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.SetNetworkProfileRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.GetVariablesResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.SetVariablesResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.GetVariablesRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.SetVariablesRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Confirmation.GetBaseReportResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Confirmation.GetReportResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.GetBaseReportRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests.GetReportRequest;

/**
 * Interfaz que define los métodos de manejo de eventos relacionados con el bloque funcional de
 * aprovisionamiento en el cliente OCPP.
 *
 * Esta interfaz proporciona métodos para procesar solicitudes entrantes del sistema central (CSMS)
 * relacionadas con la configuración y gestión de variables, informes y perfiles de red en la
 * estación de carga.
 */
public interface ClientProvisioningEventHandler {

    /**
     * Maneja una solicitud {@link GetBaseReportRequest} para obtener un informe base de la estación
     * de carga.
     *
     * @param request Solicitud entrante del tipo {@link GetBaseReportRequest}.
     * @return Respuesta correspondiente del tipo {@link GetBaseReportResponse}.
     */
    GetBaseReportResponse handleGetBaseReportRequest(GetBaseReportRequest request);

    /**
     * Maneja una solicitud {@link GetReportRequest} para obtener un informe detallado sobre
     * componentes o variables específicas de la estación de carga.
     *
     * @param request Solicitud entrante del tipo {@link GetReportRequest}.
     * @return Respuesta correspondiente del tipo {@link GetReportResponse}.
     */
    GetReportResponse handleGetReportRequest(GetReportRequest request);

    /**
     * Maneja una solicitud {@link GetVariablesRequest} para recuperar el valor actual de variables
     * específicas en la estación de carga.
     *
     * @param request Solicitud entrante del tipo {@link GetVariablesRequest}.
     * @return Respuesta correspondiente del tipo {@link GetVariablesResponse}.
     */
    GetVariablesResponse handleGetVariablesRequest(GetVariablesRequest request);

    /**
     * Maneja una solicitud {@link ResetRequest} para reiniciar la estación de carga.
     *
     * @param request Solicitud entrante del tipo {@link ResetRequest}.
     * @return Respuesta correspondiente del tipo {@link ResetResponse}.
     */
    ResetResponse handleResetRequest(ResetRequest request);

    /**
     * Maneja una solicitud {@link SetNetworkProfileRequest} para configurar el perfil de red de la
     * estación de carga.
     *
     * @param request Solicitud entrante del tipo {@link SetNetworkProfileRequest}.
     * @return Respuesta correspondiente del tipo {@link SetNetworkProfileResponse}.
     */
    SetNetworkProfileResponse handleSetNetworkProfileRequest(SetNetworkProfileRequest request);

    /**
     * Maneja una solicitud {@link SetVariablesRequest} para configurar el valor de variables
     * específicas en la estación de carga.
     *
     * @param request Solicitud entrante del tipo {@link SetVariablesRequest}.
     * @return Respuesta correspondiente del tipo {@link SetVariablesResponse}.
     */
    SetVariablesResponse handleSetVariablesRequest(SetVariablesRequest request);
}

