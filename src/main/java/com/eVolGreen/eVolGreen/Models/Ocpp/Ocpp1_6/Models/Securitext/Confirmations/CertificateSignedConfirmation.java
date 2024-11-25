package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.Types.CertificateSignedStatusEnumType;

import java.util.Objects;

/**
 * Clase que representa la confirmación de firma de certificado en OCPP 2.0.
 * Esta confirmación indica si la firma del certificado ha sido aceptada o rechazada.
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     {@code
 *     CertificateSignedConfirmation confirmation = new CertificateSignedConfirmation(CertificateSignedStatusEnumType.Accepted);
 *     System.out.println(confirmation);
 *     }
 * </pre>
 */
public class CertificateSignedConfirmation extends Confirmation {

    private CertificateSignedStatusEnumType status;

    /**
     * Constructor privado por defecto, necesario para fines de serialización.
     */
    private CertificateSignedConfirmation() {
    }

    /**
     * Constructor que inicializa el estado de la confirmación con un valor de {@link CertificateSignedStatusEnumType}.
     *
     * @param status Estado de la firma del certificado. Ver {@link #setStatus(CertificateSignedStatusEnumType)}.
     */
    public CertificateSignedConfirmation(CertificateSignedStatusEnumType status) {
        setStatus(status);
    }

    /**
     * Obtiene el estado de la firma del certificado.
     *
     * @return El estado actual de la firma del certificado, de tipo {@link CertificateSignedStatusEnumType}.
     */
    public CertificateSignedStatusEnumType getStatus() {
        return status;
    }

    /**
     * Establece el estado de la firma del certificado.
     * Es un campo obligatorio que indica si la firma fue aceptada o rechazada.
     *
     * @param status El estado de la firma del certificado, de tipo {@link CertificateSignedStatusEnumType}.
     */
    public void setStatus(CertificateSignedStatusEnumType status) {
        this.status = status;
    }

    /**
     * Valida si la confirmación es válida, asegurándose de que el campo {@code status} no sea nulo.
     *
     * @return {@code true} si la confirmación es válida, {@code false} de lo contrario.
     */
    @Override
    public boolean validate() {
        return status != null;
    }

    /**
     * Compara si dos confirmaciones de firma de certificado son equivalentes.
     *
     * @param o el objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CertificateSignedConfirmation that = (CertificateSignedConfirmation) o;
        return Objects.equals(status, that.status);
    }

    /**
     * Genera un código hash único basado en el campo {@code status}.
     *
     * @return el valor hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    /**
     * Devuelve una representación en cadena de la confirmación de firma de certificado.
     *
     * @return una cadena que representa la confirmación, incluyendo el estado de la firma.
     */
    @Override
    public String toString() {
        return "CertificateSignedConfirmation{" +
                "status=" + status +
                ", isValid=" + validate() +
                '}';
    }
}
