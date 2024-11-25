package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.Types.DeleteCertificateStatusEnumType;

import java.util.Objects;

/**
 * Representa la confirmación de la eliminación de un certificado en OCPP 2.0.
 * Esta confirmación es enviada por el Charge Point al sistema central en respuesta a una solicitud de eliminación de certificado.
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     {@code
 *     DeleteCertificateConfirmation confirmation = new DeleteCertificateConfirmation(DeleteCertificateStatusEnumType.Accepted);
 *     System.out.println(confirmation);
 *     }
 * </pre>
 */
public class DeleteCertificateConfirmation extends Confirmation {

    private DeleteCertificateStatusEnumType status;

    /**
     * Constructor privado por defecto, requerido para fines de serialización.
     */
    private DeleteCertificateConfirmation() {
    }

    /**
     * Constructor que inicializa la confirmación con el estado de eliminación de certificado.
     *
     * @param status Estado de la solicitud de eliminación, de tipo {@link DeleteCertificateStatusEnumType}.
     */
    public DeleteCertificateConfirmation(DeleteCertificateStatusEnumType status) {
        setStatus(status);
    }

    /**
     * Obtiene el estado de la solicitud de eliminación de certificado.
     *
     * @return Estado de la solicitud, de tipo {@link DeleteCertificateStatusEnumType}.
     */
    public DeleteCertificateStatusEnumType getStatus() {
        return status;
    }

    /**
     * Establece el estado de la solicitud de eliminación de certificado.
     * Este campo es requerido.
     *
     * @param status Estado de la solicitud, de tipo {@link DeleteCertificateStatusEnumType}.
     */
    public void setStatus(DeleteCertificateStatusEnumType status) {
        this.status = status;
    }

    /**
     * Valida si la confirmación es válida, verificando que el estado no sea nulo.
     *
     * @return {@code true} si el estado no es nulo, {@code false} de lo contrario.
     */
    @Override
    public boolean validate() {
        return status != null;
    }

    /**
     * Compara si dos confirmaciones de eliminación de certificado son equivalentes.
     *
     * @param o el objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteCertificateConfirmation that = (DeleteCertificateConfirmation) o;
        return Objects.equals(status, that.status);
    }

    /**
     * Genera un código hash único basado en el estado de la confirmación.
     *
     * @return el valor hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    /**
     * Devuelve una representación en cadena de la confirmación de eliminación de certificado.
     *
     * @return una cadena que representa la confirmación, incluyendo el estado y su validez.
     */
    @Override
    public String toString() {
        return "DeleteCertificateConfirmation{" +
                "status=" + status +
                ", isValid=" + validate() +
                '}';
    }
}
