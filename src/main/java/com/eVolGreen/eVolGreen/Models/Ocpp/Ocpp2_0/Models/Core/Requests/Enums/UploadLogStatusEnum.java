package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums;

/**
 * Enumeración que define los posibles estados de la carga de registros (logs) en OCPP.
 */
public enum UploadLogStatusEnum {

    /** Indica que el mensaje enviado no es válido. */
    BadMessage,

    /** No se está cargando ningún registro. */
    Idle,

    /** La operación de carga no está soportada por el sistema. */
    NotSupportedOperation,

    /** Permiso denegado para realizar la carga del registro. */
    PermissionDenied,

    /** La carga del registro se completó correctamente. */
    Uploaded,

    /** Hubo un fallo durante la carga del registro. */
    UploadFailure,

    /** El sistema está en proceso de cargar el registro. */
    Uploading,

    /** La carga del registro fue aceptada pero posteriormente cancelada. */
    AcceptedCanceled
}
