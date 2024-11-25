package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.Types.CertificateHashDataType;

import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud de eliminación de certificado en OCPP 2.0.
 * Esta solicitud se utiliza para pedir la eliminación de un certificado específico en una estación de carga.
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     {@code
 *     CertificateHashDataType certificateHashData = new CertificateHashDataType(...);
 *     DeleteCertificateRequest request = new DeleteCertificateRequest(certificateHashData);
 *     System.out.println(request);
 *     }
 * </pre>
 */
public class DeleteCertificateRequest extends RequestWithId {

    private CertificateHashDataType certificateHashData;

    /**
     * Constructor privado por defecto para fines de serialización.
     */
    private DeleteCertificateRequest() {
    }

    /**
     * Constructor que inicializa la solicitud con los datos del hash del certificado.
     *
     * @param certificateHashData Datos del hash del certificado, de tipo {@link CertificateHashDataType}.
     */
    public DeleteCertificateRequest(CertificateHashDataType certificateHashData) {
        setCertificateHashData(certificateHashData);
    }

    /**
     * Obtiene los datos del hash del certificado cuya eliminación se solicita.
     *
     * @return {@link CertificateHashDataType} con la información del certificado.
     */
    public CertificateHashDataType getCertificateHashData() {
        return certificateHashData;
    }

    /**
     * Establece los datos del hash del certificado cuya eliminación se solicita.
     * Este campo es obligatorio.
     *
     * @param certificateHashData {@link CertificateHashDataType} con la información del certificado.
     */
    public void setCertificateHashData(CertificateHashDataType certificateHashData) {
        this.certificateHashData = certificateHashData;
    }

    /**
     * Indica si la solicitud está relacionada con una transacción.
     *
     * @return {@code false}, ya que esta solicitud no está relacionada con una transacción.
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
     * Valida que la solicitud sea válida. Comprueba que {@code certificateHashData} no sea nulo y sea válido.
     *
     * @return {@code true} si la solicitud es válida, {@code false} de lo contrario.
     */
    @Override
    public boolean validate() {
        return certificateHashData != null && certificateHashData.validate();
    }

    /**
     * Compara si dos solicitudes de eliminación de certificado son equivalentes.
     *
     * @param o el objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteCertificateRequest that = (DeleteCertificateRequest) o;
        return Objects.equals(certificateHashData, that.certificateHashData);
    }

    /**
     * Genera un código hash único basado en los datos del hash del certificado.
     *
     * @return el valor hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(certificateHashData);
    }

    /**
     * Devuelve una representación en cadena de la solicitud de eliminación de certificado.
     *
     * @return una cadena que representa la solicitud, incluyendo los datos del certificado y su validez.
     */
    @Override
    public String toString() {
        return "DeleteCertificateRequest{" +
                "certificateHashData=" + certificateHashData +
                ", isValid=" + validate() +
                '}';
    }
}
