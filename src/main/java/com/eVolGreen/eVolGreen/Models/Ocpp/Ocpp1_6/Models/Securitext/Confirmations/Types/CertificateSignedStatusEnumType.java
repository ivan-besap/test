package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.Types;


import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.CertificateSignedConfirmation;

/**
 * Enumeración que define los posibles estados de un certificado firmado.
 * <p>
 * Esta enumeración es utilizada en {@link CertificateSignedConfirmation} para indicar el resultado
 * de la firma de un certificado.
 * </p>
 *
 * <p><b>Valores posibles:</b></p>
 * <ul>
 *     <li>{@link #Accepted}: El certificado firmado es válido.</li>
 *     <li>{@link #Rejected}: El certificado firmado es inválido.</li>
 * </ul>
 */
public enum CertificateSignedStatusEnumType {

    /**
     * El certificado firmado es válido y ha sido aceptado.
     */
    Accepted,

    /**
     * El certificado firmado no es válido y ha sido rechazado.
     */
    Rejected
}
