package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.Types;


import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.DeleteCertificateConfirmation;

/**
 * Enumeración que define los posibles estados al eliminar un certificado.
 * <p>
 * Esta enumeración es utilizada en {@link DeleteCertificateConfirmation} para indicar el resultado
 * de la eliminación de un certificado.
 * </p>
 *
 * <p><b>Valores posibles:</b></p>
 * <ul>
 *     <li>{@link #Accepted}: Eliminación completada con éxito.</li>
 *     <li>{@link #Failed}: Fallo en el procesamiento de la eliminación.</li>
 *     <li>{@link #NotFound}: El certificado solicitado no se encontró.</li>
 * </ul>
 */
public enum DeleteCertificateStatusEnumType {

    /**
     * Eliminación completada con éxito (sin errores).
     */
    Accepted,

    /**
     * Fallo en el procesamiento.
     */
    Failed,

    /**
     * El recurso solicitado no fue encontrado.
     */
    NotFound
}
