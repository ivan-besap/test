package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.Types;


import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.GetInstalledCertificateIdsConfirmation;

/**
 * Enumeración que define el estado de los certificados instalados.
 *
 * <p>Esta enumeración se utiliza en {@link GetInstalledCertificateIdsConfirmation} para indicar si
 * la operación de obtención de los certificados instalados fue exitosa o si los certificados solicitados no se encontraron.</p>
 */
public enum GetInstalledCertificateStatusEnumType {

    /**
     * Indica que la operación se completó con éxito.
     */
    Accepted,

    /**
     * Indica que no se encontró el certificado solicitado.
     */
    NotFound
}
