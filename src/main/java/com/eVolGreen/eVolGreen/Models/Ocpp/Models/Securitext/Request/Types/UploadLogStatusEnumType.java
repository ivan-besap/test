package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request.Types;

import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request.ExtendedTriggerMessageRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request.LogStatusNotificationRequest;

/**
 * Enumeración que define los posibles estados del proceso de subida de un archivo de logs.
 *
 * <p>Esta enumeración es utilizada por {@link LogStatusNotificationRequest} para indicar el estado
 * del proceso de subida de archivos de logs en el sistema OCPP de eVolGreen.</p>
 */
public enum UploadLogStatusEnumType {

    /**
     * Se detectó un paquete mal formateado u otra incompatibilidad de protocolo.
     */
    BadMessage,

    /**
     * El Charge Point no está subiendo ningún archivo de log.
     * Idle solo debe usarse cuando el mensaje es activado por {@link ExtendedTriggerMessageRequest}.
     */
    Idle,

    /**
     * El servidor no soporta la operación solicitada.
     */
    NotSupportedOperation,

    /**
     * Permisos insuficientes para realizar la operación.
     */
    PermissionDenied,

    /**
     * El archivo ha sido subido exitosamente.
     */
    Uploaded,

    /**
     * Fallo al subir el archivo solicitado.
     */
    UploadFailure,

    /**
     * El archivo se está subiendo.
     */
    Uploading
}
