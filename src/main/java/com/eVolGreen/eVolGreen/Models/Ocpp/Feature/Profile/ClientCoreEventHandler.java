package com.eVolGreen.eVolGreen.Models.Ocpp.Feature.Profile;

import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.*;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.*;

/**
 * La interfaz {@code ClientCoreEventHandler} define métodos de callback para manejar eventos
 * relacionados con el perfil de características centrales de OCPP (Protocolo Abierto de Punto de Carga).
 * Estos eventos ocurren cuando el cliente (punto de carga) recibe diversas solicitudes desde el sistema central.
 * <p>
 * Cada método en esta interfaz corresponde a la gestión de un tipo específico de solicitud de OCPP
 * y proporciona una confirmación como respuesta.
 */
public interface ClientCoreEventHandler {

    /**
     * Maneja una solicitud entrante {@link ChangeAvailabilityRequest} y devuelve una {@link ChangeAvailabilityConfirmation}.
     * Este método se utiliza cuando el sistema central quiere cambiar la disponibilidad de un conector.
     *
     * @param request la solicitud entrante para cambiar la disponibilidad de un conector.
     * @return una confirmación que responde a la solicitud de cambio de disponibilidad.
     */
    ChangeAvailabilityConfirmation handleChangeAvailabilityRequest(ChangeAvailabilityRequest request);

    /**
     * Maneja una solicitud entrante {@link GetConfigurationRequest} y devuelve una {@link GetConfigurationConfirmation}.
     * Este método se llama cuando el sistema central solicita recuperar claves de configuración desde el punto de carga.
     *
     * @param request la solicitud entrante para obtener detalles de la configuración.
     * @return una confirmación que contiene los detalles de la configuración.
     */
    GetConfigurationConfirmation handleGetConfigurationRequest(GetConfigurationRequest request);

    /**
     * Maneja una solicitud entrante {@link ChangeConfigurationRequest} y devuelve una {@link ChangeConfigurationConfirmation}.
     * Este método se invoca cuando el sistema central envía una solicitud para modificar una clave de configuración.
     *
     * @param request la solicitud entrante para cambiar una configuración.
     * @return una confirmación que responde a la solicitud de cambio de configuración.
     */
    ChangeConfigurationConfirmation handleChangeConfigurationRequest(ChangeConfigurationRequest request);

    /**
     * Maneja una solicitud entrante {@link ClearCacheRequest} y devuelve una {@link ClearCacheConfirmation}.
     * Este método se llama cuando el sistema central solicita borrar la caché de autorizaciones del punto de carga.
     *
     * @param request la solicitud entrante para limpiar la caché.
     * @return una confirmación que indica si la caché fue borrada con éxito.
     */
    ClearCacheConfirmation handleClearCacheRequest(ClearCacheRequest request);

    /**
     * Maneja una solicitud entrante {@link DataTransferRequest} y devuelve una {@link DataTransferConfirmation}.
     * Este método se utiliza para transferir datos personalizados entre el sistema central y el punto de carga.
     *
     * @param request la solicitud de transferencia de datos entrante.
     * @return una confirmación que responde a la solicitud de transferencia de datos.
     */
    DataTransferConfirmation handleDataTransferRequest(DataTransferRequest request);

    /**
     * Maneja una solicitud entrante {@link RemoteStartTransactionRequest} y devuelve una {@link RemoteStartTransactionConfirmation}.
     * Este método se invoca cuando el sistema central quiere iniciar remotamente una transacción de carga.
     *
     * @param request la solicitud entrante para iniciar una transacción remotamente.
     * @return una confirmación que responde a la solicitud de inicio remoto.
     */
    RemoteStartTransactionConfirmation handleRemoteStartTransactionRequest(RemoteStartTransactionRequest request);

    /**
     * Maneja una solicitud entrante {@link RemoteStopTransactionRequest} y devuelve una {@link RemoteStopTransactionConfirmation}.
     * Este método se utiliza cuando el sistema central solicita detener remotamente una transacción de carga en curso.
     *
     * @param request la solicitud entrante para detener una transacción remotamente.
     * @return una confirmación que responde a la solicitud de detención remota.
     */
    RemoteStopTransactionConfirmation handleRemoteStopTransactionRequest(RemoteStopTransactionRequest request);

    /**
     * Maneja una solicitud entrante {@link ResetRequest} y devuelve una {@link ResetConfirmation}.
     * Este método se llama cuando el sistema central solicita que el punto de carga se reinicie.
     *
     * @param request la solicitud entrante para reiniciar el punto de carga.
     * @return una confirmación que responde a la solicitud de reinicio.
     */
    ResetConfirmation handleResetRequest(ResetRequest request);

    /**
     * Maneja una solicitud entrante {@link UnlockConnectorRequest} y devuelve una {@link UnlockConnectorConfirmation}.
     * Este método se invoca cuando el sistema central solicita desbloquear un conector del punto de carga.
     *
     * @param request la solicitud entrante para desbloquear el conector.
     * @return una confirmación que responde a la solicitud de desbloqueo del conector.
     */
    UnlockConnectorConfirmation handleUnlockConnectorRequest(UnlockConnectorRequest request);
}
