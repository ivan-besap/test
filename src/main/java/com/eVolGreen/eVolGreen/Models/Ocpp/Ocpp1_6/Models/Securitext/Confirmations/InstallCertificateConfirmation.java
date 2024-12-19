package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.Types.CertificateStatusEnumType;

import java.util.Objects;

/**
 * Confirmación de instalación de certificado en el punto de carga.
 * Esta confirmación es enviada por el punto de carga para indicar si la instalación del certificado fue exitosa o fallida.
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     {@code
 *     InstallCertificateConfirmation confirmation = new InstallCertificateConfirmation(CertificateStatusEnumType.Accepted);
 *     System.out.println(confirmation);
 *     }
 * </pre>
 */
public class InstallCertificateConfirmation extends Confirmation {

    private CertificateStatusEnumType status;

    /**
     * Constructor privado para propósitos de serialización.
     */
    private InstallCertificateConfirmation() {
    }

    /**
     * Constructor que maneja los campos obligatorios.
     *
     * @param status Estado de la instalación del certificado. Ver {@link #setStatus(CertificateStatusEnumType)}
     */
    public InstallCertificateConfirmation(CertificateStatusEnumType status) {
        setStatus(status);
    }

    /**
     * Indica si la instalación del certificado fue exitosa o fallida.
     *
     * @return {@link CertificateStatusEnumType} Estado de la instalación.
     */
    public CertificateStatusEnumType getStatus() {
        return status;
    }

    /**
     * Establece el estado de la instalación del certificado.
     * Este campo es obligatorio.
     *
     * @param status Estado de la instalación del certificado.
     */
    public void setStatus(CertificateStatusEnumType status) {
        this.status = status;
    }

    /**
     * Valida si la confirmación es válida, asegurando que el campo {@code status} no sea nulo.
     *
     * @return {@code true} si la confirmación es válida, {@code false} de lo contrario.
     */
    @Override
    public boolean validate() {
        return status != null;
    }

    /**
     * Compara si dos confirmaciones de instalación de certificado son equivalentes.
     *
     * @param o El objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstallCertificateConfirmation that = (InstallCertificateConfirmation) o;
        return Objects.equals(status, that.status);
    }

    /**
     * Genera un código hash único basado en el estado de la instalación.
     *
     * @return El valor hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    /**
     * Devuelve una representación en cadena de la confirmación de instalación de certificado.
     *
     * @return Una cadena que representa la confirmación, incluyendo el estado de la instalación.
     */
    @Override
    public String toString() {
        return "InstallCertificateConfirmation{" +
                "status=" + status +
                ", isValid=" + validate() +
                '}';
    }
}
