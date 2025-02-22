package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations.Types.GenericStatusEnumType;


import java.util.Objects;

/**
 * Confirmación de la firma de un certificado en OCPP.
 *
 * <p>Esta confirmación es enviada por el sistema central en respuesta a una solicitud
 * para firmar un certificado. Indica si el sistema puede procesar la solicitud de firma.</p>
 *
 * <p><b>Ejemplo de uso:</b></p>
 * <pre>
 *     {@code
 *     SignCertificateConfirmation confirmation = new SignCertificateConfirmation(GenericStatusEnumType.Accepted);
 *     System.out.println(confirmation);
 *     }
 * </pre>
 */
public class SignCertificateConfirmation extends Confirmation {

    // Estado que indica si el sistema central puede procesar la solicitud
    private GenericStatusEnumType status;

    /**
     * Constructor privado por defecto, necesario para la serialización.
     */
    public SignCertificateConfirmation() {
    }

    /**
     * Constructor que maneja los campos obligatorios de la confirmación.
     *
     * @param status Estado genérico que indica si el sistema central puede procesar la solicitud.
     */
    public SignCertificateConfirmation(GenericStatusEnumType status) {
        setStatus(status);
    }

    /**
     * Obtiene el estado que especifica si el sistema central puede procesar la solicitud.
     *
     * @return {@link GenericStatusEnumType} Estado de la confirmación.
     */
    public GenericStatusEnumType getStatus() {
        return status;
    }

    /**
     * Establece el estado que especifica si el sistema central puede procesar la solicitud.
     * Este campo es obligatorio.
     *
     * @param status Estado de la confirmación.
     */
    public void setStatus(GenericStatusEnumType status) {
        this.status = status;
    }

    /**
     * Valida que los campos obligatorios de la confirmación sean válidos.
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
     * @param o Objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignCertificateConfirmation that = (SignCertificateConfirmation) o;
        return Objects.equals(status, that.status);
    }

    /**
     * Genera un código hash único basado en el campo status.
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
     * @return una cadena que representa la confirmación, incluyendo el estado.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("status", status)
                .add("isValid", validate())
                .toString();
    }
}
