package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation.Validator;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation.ValidatorBuilder;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Validation.StringMaxLengthValidationRule;

import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud de firma de certificado en OCPP.
 *
 * <p>El Charge Point envía la clave pública en forma de una Solicitud de Firma de Certificado (CSR),
 * que está codificada en PEM y descrita según el RFC 2986.</p>
 *
 * <p><b>Ejemplo de uso:</b></p>
 * <pre>
 *     {@code
 *     SignCertificateRequest request = new SignCertificateRequest(csrString);
 *     System.out.println(request);
 *     }
 * </pre>
 */
public class SignCertificateRequest extends RequestWithId {

    // Validador para la CSR con un tamaño máximo de 5500 caracteres
    private static final transient Validator csrValidator =
            new ValidatorBuilder()
                    .addRule(new StringMaxLengthValidationRule(5500))
                    .setRequired(true)
                    .build();

    // CSR enviada por el Charge Point
    private String csr;

    /**
     * Constructor privado por defecto, necesario para la serialización.
     */
    private SignCertificateRequest() {
    }

    /**
     * Constructor que maneja los campos obligatorios.
     *
     * @param csr CSR en formato PEM que se enviará al sistema central para su firma.
     */
    public SignCertificateRequest(String csr) {
        setCsr(csr);
    }

    /**
     * Obtiene la CSR (Certificate Signing Request) enviada por el Charge Point.
     *
     * @return string[0..5500] CSR en formato PEM.
     */
    public String getCsr() {
        return csr;
    }

    /**
     * Establece la CSR (Certificate Signing Request) que se enviará.
     *
     * @param csr string[0..5500] CSR en formato PEM.
     */
    public void setCsr(String csr) {
        csrValidator.validate(csr);
        this.csr = csr;
    }

    /**
     * Indica si la solicitud está relacionada con una transacción.
     *
     * @return {@code false}, ya que la solicitud no está relacionada con una transacción.
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
     * Valida que la CSR cumpla con las reglas establecidas (no nula, longitud correcta).
     *
     * @return {@code true} si la solicitud es válida, {@code false} de lo contrario.
     */
    @Override
    public boolean validate() {
        return csrValidator.safeValidate(csr);
    }

    /**
     * Compara si dos solicitudes de firma de certificado son equivalentes.
     *
     * @param o Objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignCertificateRequest that = (SignCertificateRequest) o;
        return Objects.equals(csr, that.csr);
    }

    /**
     * Genera un código hash único basado en la CSR.
     *
     * @return el valor hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(csr);
    }

    /**
     * Devuelve una representación en cadena de la solicitud de firma de certificado.
     *
     * @return una cadena que representa la solicitud, incluyendo la CSR y su estado de validación.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("csr", csr)
                .add("isValid", validate())
                .toString();
    }
}
