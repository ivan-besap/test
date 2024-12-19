package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request.Types.CertificateUseEnumType;

import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud para obtener las identificaciones de los certificados instalados en el punto de carga.
 * Esta solicitud indica el tipo de certificados que se están solicitando.
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     {@code
 *     GetInstalledCertificateIdsRequest request = new GetInstalledCertificateIdsRequest(CertificateUseEnumType.VCS);
 *     System.out.println(request);
 *     }
 * </pre>
 */
public class GetInstalledCertificateIdsRequest extends RequestWithId {

    private CertificateUseEnumType certificateType;

    /**
     * Constructor privado para propósitos de serialización.
     */
    private GetInstalledCertificateIdsRequest() {
    }

    /**
     * Constructor que maneja los campos requeridos.
     *
     * @param certificateType Tipo de certificado solicitado. Ver {@link #setCertificateType(CertificateUseEnumType)}.
     */
    public GetInstalledCertificateIdsRequest(CertificateUseEnumType certificateType) {
        setCertificateType(certificateType);
    }

    /**
     * Indica el tipo de certificados solicitados.
     *
     * @return {@link CertificateUseEnumType} que indica el tipo de certificados.
     */
    public CertificateUseEnumType getCertificateType() {
        return certificateType;
    }

    /**
     * Campo obligatorio. Establece el tipo de certificados solicitados.
     *
     * @param certificateType {@link CertificateUseEnumType} que indica el tipo de certificados solicitados.
     */
    public void setCertificateType(CertificateUseEnumType certificateType) {
        this.certificateType = certificateType;
    }

    /**
     * Indica si la solicitud está relacionada con una transacción.
     *
     * @return {@code false}, ya que esta solicitud no está relacionada con transacciones.
     */
    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public String getAction() {
        return "";
    }

    @Override
    public UUID getSessionIndex() {
        return null;
    }

    /**
     * Valida si la solicitud es válida, comprobando que el tipo de certificado no sea nulo.
     *
     * @return {@code true} si la solicitud es válida, {@code false} de lo contrario.
     */
    @Override
    public boolean validate() {
        return certificateType != null;
    }

    /**
     * Compara si dos solicitudes de identificación de certificados instalados son equivalentes.
     *
     * @param o el objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetInstalledCertificateIdsRequest that = (GetInstalledCertificateIdsRequest) o;
        return Objects.equals(certificateType, that.certificateType);
    }

    /**
     * Genera un código hash único basado en el tipo de certificado.
     *
     * @return el valor hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(certificateType);
    }

    /**
     * Devuelve una representación en cadena de la solicitud de identificación de certificados instalados.
     *
     * @return una cadena que representa la solicitud, incluyendo el tipo de certificado y su validez.
     */
    @Override
    public String toString() {
        return "GetInstalledCertificateIdsRequest{" +
                "certificateType=" + certificateType +
                ", isValid=" + validate() +
                '}';
    }
}
