package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Feature.Function;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.UnlockConnectorResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.UnlockConnectorRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.RemoteTrigger.Confirmation.TriggerMessageResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.RemoteTrigger.Request.TriggerMessageRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Confirmation.RequestStartTransactionResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Confirmation.RequestStopTransactionResponse;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.RequestStartTransactionRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.RequestStopTransactionRequest;

/**
 * Interfaz para manejar eventos del cliente relacionados con el bloque funcional de control remoto en OCPP.
 *
 * Proporciona métodos para manejar solicitudes remotas como el inicio y detención de transacciones,
 * activación de mensajes específicos y desbloqueo de conectores.
 */
public interface ClientRemoteControlEventHandler {

    /**
     * Maneja una solicitud de inicio remoto de transacción {@link RequestStartTransactionRequest}
     * y genera una respuesta {@link RequestStartTransactionResponse}.
     *
     * @param request Objeto {@link RequestStartTransactionRequest} que representa la solicitud entrante.
     * @return Objeto {@link RequestStartTransactionResponse} que contiene la respuesta generada.
     */
    RequestStartTransactionResponse handleRequestStartTransactionRequest(
            RequestStartTransactionRequest request);

    /**
     * Maneja una solicitud de detención remota de transacción {@link RequestStopTransactionRequest}
     * y genera una respuesta {@link RequestStopTransactionResponse}.
     *
     * @param request Objeto {@link RequestStopTransactionRequest} que representa la solicitud entrante.
     * @return Objeto {@link RequestStopTransactionResponse} que contiene la respuesta generada.
     */
    RequestStopTransactionResponse handleRequestStopTransactionRequest(
            RequestStopTransactionRequest request);

    /**
     * Maneja una solicitud de activación de un mensaje específico {@link TriggerMessageRequest}
     * y genera una respuesta {@link TriggerMessageResponse}.
     *
     * @param request Objeto {@link TriggerMessageRequest} que representa la solicitud entrante.
     * @return Objeto {@link TriggerMessageResponse} que contiene la respuesta generada.
     */
    TriggerMessageResponse handleTriggerMessageRequest(TriggerMessageRequest request);

    /**
     * Maneja una solicitud de desbloqueo de un conector {@link UnlockConnectorRequest}
     * y genera una respuesta {@link UnlockConnectorResponse}.
     *
     * @param request Objeto {@link UnlockConnectorRequest} que representa la solicitud entrante.
     * @return Objeto {@link UnlockConnectorResponse} que contiene la respuesta generada.
     */
    UnlockConnectorResponse handleUnlockConnectorRequest(UnlockConnectorRequest request);
}
