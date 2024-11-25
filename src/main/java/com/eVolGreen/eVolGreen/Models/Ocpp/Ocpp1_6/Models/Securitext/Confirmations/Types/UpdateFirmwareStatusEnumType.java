package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.Types;


import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.SignedUpdateFirmwareConfirmation;

/**
 * Enumeración que define los posibles estados en la actualización de firmware.
 *
 * <p>Esta enumeración es utilizada por {@link SignedUpdateFirmwareConfirmation} para describir el estado del proceso de
 * actualización del firmware en un punto de carga. El estado puede indicar aceptación, rechazo o problemas con el
 * certificado.</p>
 */
public enum UpdateFirmwareStatusEnumType {

    /**
     * Se ha aceptado la solicitud de actualización de firmware. Esto no garantiza el éxito de la actualización, solo que
     * el punto de carga comenzará el proceso.
     */
    Accepted,

    /**
     * La solicitud de actualización de firmware ha sido rechazada.
     */
    Rejected,

    /**
     * Se ha aceptado la solicitud de actualización de firmware, pero en el proceso se ha cancelado una actualización de
     * firmware en curso.
     */
    AcceptedCanceled,

    /**
     * El certificado proporcionado es inválido.
     */
    InvalidCertificate,

    /**
     * Estado final de fallo. El certificado de firma del firmware ha sido revocado.
     */
    RevokedCertificate
}
