package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Request;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation.Validator;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validation.ValidatorBuilder;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Validation.StringMaxLengthValidationRule;


import java.util.Objects;
import java.util.UUID;

/**
 * Clase que representa la solicitud de firma de certificado en OCPP 2.0.
 * Esta solicitud contiene la cadena de certificados firmados que será enviada al sistema central.
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     {@code
 *     CertificateSignedRequest request = new CertificateSignedRequest("cadenaCertificados");
 *     System.out.println(request);
 *     }
 * </pre>
 */
public class CertificateSignedRequest extends RequestWithId {

    private static final transient Validator certificateChainValidator =
            new ValidatorBuilder()
                    .addRule(new StringMaxLengthValidationRule(10000))
                    .setRequired(true)
                    .build();

    private String certificateChain;

    /**
     * Constructor por defecto privado, requerido para fines de serialización.
     */
    private CertificateSignedRequest() {
    }

    /**
     * Constructor que inicializa la solicitud con la cadena de certificados.
     *
     * @param certificateChain La cadena de certificados firmados en formato PEM.
     */
    public CertificateSignedRequest(String certificateChain) {
        setCertificateChain(certificateChain);
    }

    /**
     * Obtiene la cadena de certificados firmados.
     * Puede incluir subcertificados CA necesarios. El tamaño máximo está limitado a 10,000 caracteres.
     *
     * @return La cadena de certificados, de tipo {@link String}.
     */
    public String getCertificateChain() {
        return certificateChain;
    }

    /**
     * Establece la cadena de certificados firmados en formato PEM.
     * Este campo es requerido y su tamaño máximo es de 10,000 caracteres.
     *
     * @param certificateChain La cadena de certificados firmados.
     */
    public void setCertificateChain(String certificateChain) {
        certificateChainValidator.validate(certificateChain);
        this.certificateChain = certificateChain;
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
     * Valida si la solicitud es válida, verificando que la cadena de certificados no exceda el tamaño permitido.
     *
     * @return {@code true} si la solicitud es válida, {@code false} de lo contrario.
     */
    @Override
    public boolean validate() {
        return certificateChainValidator.safeValidate(certificateChain);
    }

    /**
     * Compara si dos solicitudes de firma de certificado son equivalentes.
     *
     * @param o el objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CertificateSignedRequest that = (CertificateSignedRequest) o;
        return Objects.equals(certificateChain, that.certificateChain);
    }

    /**
     * Genera un código hash único basado en la cadena de certificados.
     *
     * @return el valor hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(certificateChain);
    }

    /**
     * Devuelve una representación en cadena de la solicitud de firma de certificado.
     *
     * @return una cadena que representa la solicitud, incluyendo la validez de la misma.
     */
    @Override
    public String toString() {
        return "CertificateSignedRequest{" +
                "certificateChain='" + certificateChain + '\'' +
                ", isValid=" + validate() +
                '}';
    }
}
