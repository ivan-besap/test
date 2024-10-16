package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Confirmations.Types.CertificateHashDataType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Confirmations.Types.GetInstalledCertificateStatusEnumType;

import java.util.Arrays;
import java.util.Objects;

/**
 * Confirmación para la solicitud de identificación de certificados instalados en OCPP 2.0.
 * Esta clase contiene el estado de la operación y los datos de hash de certificados si están disponibles.
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     {@code
 *     GetInstalledCertificateIdsConfirmation confirmation = new GetInstalledCertificateIdsConfirmation(GetInstalledCertificateStatusEnumType.Accepted);
 *     confirmation.setCertificateHashData(new CertificateHashDataType[]{new CertificateHashDataType()});
 *     System.out.println(confirmation);
 *     }
 * </pre>
 */
public class GetInstalledCertificateIdsConfirmation extends Confirmation {

    private GetInstalledCertificateStatusEnumType status;
    private CertificateHashDataType[] certificateHashData;

    /**
     * Constructor privado para propósitos de serialización.
     */
    private GetInstalledCertificateIdsConfirmation() {
    }

    /**
     * Constructor que maneja los campos requeridos.
     *
     * @param status Estado de la solicitud de certificados instalados. Ver {@link #setStatus(GetInstalledCertificateStatusEnumType)}.
     */
    public GetInstalledCertificateIdsConfirmation(GetInstalledCertificateStatusEnumType status) {
        setStatus(status);
    }

    /**
     * El punto de carga indica si puede procesar la solicitud.
     *
     * @return {@link GetInstalledCertificateStatusEnumType} que representa el estado de la solicitud.
     */
    public GetInstalledCertificateStatusEnumType getStatus() {
        return status;
    }

    /**
     * Campo obligatorio. Establece el estado de la solicitud de identificación de certificados instalados.
     *
     * @param status {@link GetInstalledCertificateStatusEnumType} que indica si la solicitud fue aceptada o rechazada.
     */
    public void setStatus(GetInstalledCertificateStatusEnumType status) {
        this.status = status;
    }

    /**
     * El punto de carga incluye la información del certificado para cada certificado disponible.
     *
     * @return Arreglo de {@link CertificateHashDataType} que contiene los datos hash de los certificados.
     */
    public CertificateHashDataType[] getCertificateHashData() {
        return certificateHashData;
    }

    /**
     * Campo opcional. Establece los datos hash de los certificados disponibles en el punto de carga.
     *
     * @param certificateHashData Arreglo de {@link CertificateHashDataType} que contiene los datos hash de los certificados.
     */
    public void setCertificateHashData(CertificateHashDataType[] certificateHashData) {
        this.certificateHashData = certificateHashData;
    }

    /**
     * Valida que la confirmación sea válida, asegurando que el estado no sea nulo y que los datos de los certificados sean válidos.
     *
     * @return {@code true} si la confirmación es válida, {@code false} de lo contrario.
     */
    @Override
    public boolean validate() {
        return status != null && validateCertificateHashData();
    }

    /**
     * Valida que los datos de los certificados sean válidos, verificando cada uno de los elementos del arreglo.
     *
     * @return {@code true} si los datos de los certificados son válidos o si el arreglo es nulo, {@code false} de lo contrario.
     */
    private boolean validateCertificateHashData() {
        if (certificateHashData == null) {
            return true;
        }
        for (CertificateHashDataType chd : certificateHashData) {
            if (!chd.validate()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compara si dos confirmaciones de identificación de certificados instalados son equivalentes.
     *
     * @param o el objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetInstalledCertificateIdsConfirmation that = (GetInstalledCertificateIdsConfirmation) o;
        return Objects.equals(status, that.status) &&
                Arrays.equals(certificateHashData, that.certificateHashData);
    }

    /**
     * Genera un código hash único basado en el estado y los datos hash de los certificados.
     *
     * @return el valor hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(status, Arrays.hashCode(certificateHashData));
    }

    /**
     * Devuelve una representación en cadena de la confirmación de certificados instalados.
     *
     * @return una cadena que representa la confirmación, incluyendo el estado, los datos de los certificados y su validez.
     */
    @Override
    public String toString() {
        return "GetInstalledCertificateIdsConfirmation{" +
                "status=" + status +
                ", certificateHashData=" + Arrays.toString(certificateHashData) +
                ", isValid=" + validate() +
                '}';
    }
}
