package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.Types;

import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.ExtendedTriggerMessageRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.SignedFirmwareStatusNotificationRequest;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.SignedUpdateFirmwareRequest;

/**
 * Enum que define el estado de la descarga e instalación de firmware en el sistema eVolGreen.
 *
 * <p>Algunos valores son estados intermedios ("Intermediate state"), lo que indica que el proceso de actualización no ha finalizado.
 * Otros valores son estados de fallo ("Failure end state") o éxito ("Successful end state"), indicando si la actualización
 * fue exitosa o fallida.
 * </p>
 *
 * <p>FirmwareStatusEnumType es utilizado por {@link SignedFirmwareStatusNotificationRequest}.</p>
 */
public enum FirmwareStatusEnumType {
    /**
     * Estado intermedio. El nuevo firmware ha sido descargado por el Charge Point.
     */
    Downloaded,

    /**
     * Estado de fallo. El Charge Point no pudo descargar el firmware.
     */
    DownloadFailed,

    /**
     * Estado intermedio. El firmware se está descargando.
     */
    Downloading,

    /**
     * Estado intermedio. La descarga del nuevo firmware ha sido programada.
     */
    DownloadScheduled,

    /**
     * Estado intermedio. La descarga ha sido pausada.
     */
    DownloadPaused,

    /**
     * El Charge Point no está realizando tareas relacionadas con la actualización del firmware.
     * El estado Idle solo se debe utilizar en una {@link SignedFirmwareStatusNotificationRequest}
     * que fue activada por {@link ExtendedTriggerMessageRequest}.
     */
    Idle,

    /**
     * Estado de fallo. La instalación del nuevo firmware ha fallado.
     */
    InstallationFailed,

    /**
     * Estado intermedio. El firmware se está instalando.
     */
    Installing,

    /**
     * Estado de éxito. El nuevo firmware ha sido instalado con éxito en el Charge Point.
     */
    Installed,

    /**
     * Estado intermedio. El Charge Point está a punto de reiniciarse para activar el nuevo firmware.
     * Este estado puede omitirse si el reinicio es parte integral de la instalación y no puede ser informado por separado.
     */
    InstallRebooting,

    /**
     * Estado intermedio. La instalación del firmware descargado está programada para realizarse en la fecha y hora
     * especificadas en {@link SignedUpdateFirmwareRequest}.
     */
    InstallScheduled,

    /**
     * Estado de fallo. La verificación del nuevo firmware (por ejemplo, mediante una suma de verificación) ha fallado,
     * y la instalación no continuará.
     */
    InstallVerificationFailed,

    /**
     * Estado de fallo. La firma del firmware no es válida.
     */
    InvalidSignature,

    /**
     * Estado intermedio. La firma proporcionada ha sido verificada con éxito.
     */
    SignatureVerified
}
