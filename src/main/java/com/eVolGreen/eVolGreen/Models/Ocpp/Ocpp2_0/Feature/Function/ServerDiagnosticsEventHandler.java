package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.LogStatusNotificationResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.LogStatusNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Confirmation.NotifyMonitoringReportResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.NotifyMonitoringReportRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Confirmation.NotifyCustomerInformationResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Requests.NotifyCustomerInformationRequest;

import java.util.UUID;

/**
 * Manejador de eventos de servidor para el bloque funcional de diagnósticos.
 *
 * <p>Proporciona métodos para procesar notificaciones relacionadas con el estado de los logs,
 * información del cliente y reportes de monitoreo.</p>
 */
public interface ServerDiagnosticsEventHandler {

    /**
     * Maneja una solicitud de notificación de estado de log ({@link LogStatusNotificationRequest})
     * y genera una respuesta ({@link LogStatusNotificationResponse}).
     *
     * <p>Permite que una estación de carga informe sobre el estado de una operación de log,
     * como la carga o transferencia de archivos de log.</p>
     *
     * @param sessionIndex Identificador único de la sesión donde se recibió la solicitud.
     * @param request       Instancia de la solicitud ({@link LogStatusNotificationRequest})
     *                      enviada por la estación de carga.
     * @return Una respuesta ({@link LogStatusNotificationResponse}) que confirma el
     *         procesamiento de la solicitud.
     */
    LogStatusNotificationResponse handleLogStatusNotificationRequest(
            UUID sessionIndex, LogStatusNotificationRequest request);

    /**
     * Maneja una solicitud de notificación de información del cliente
     * ({@link NotifyCustomerInformationRequest}) y genera una respuesta
     * ({@link NotifyCustomerInformationResponse}).
     *
     * <p>Permite a la estación de carga proporcionar información solicitada previamente por
     * el sistema central o CSMS, como detalles específicos del cliente.</p>
     *
     * @param sessionIndex Identificador único de la sesión donde se recibió la solicitud.
     * @param request       Instancia de la solicitud ({@link NotifyCustomerInformationRequest})
     *                      enviada por la estación de carga.
     * @return Una respuesta ({@link NotifyCustomerInformationResponse}) que confirma el
     *         procesamiento de la solicitud.
     */
    NotifyCustomerInformationResponse handleNotifyCustomerInformationRequest(
            UUID sessionIndex, NotifyCustomerInformationRequest request);

    /**
     * Maneja una solicitud de notificación de reporte de monitoreo
     * ({@link NotifyMonitoringReportRequest}) y genera una respuesta
     * ({@link NotifyMonitoringReportResponse}).
     *
     * <p>Permite a la estación de carga enviar reportes de monitoreo generados a partir de
     * solicitudes específicas, como reportes de variables monitoreadas.</p>
     *
     * @param sessionIndex Identificador único de la sesión donde se recibió la solicitud.
     * @param request       Instancia de la solicitud ({@link NotifyMonitoringReportRequest})
     *                      enviada por la estación de carga.
     * @return Una respuesta ({@link NotifyMonitoringReportResponse}) que confirma el
     *         procesamiento de la solicitud.
     */
    NotifyMonitoringReportResponse handleNotifyMonitoringReportRequest(
            UUID sessionIndex, NotifyMonitoringReportRequest request);
}
