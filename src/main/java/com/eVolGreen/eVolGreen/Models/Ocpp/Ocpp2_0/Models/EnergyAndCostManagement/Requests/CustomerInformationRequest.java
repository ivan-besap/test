package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.IdToken;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Requests.Utils.CertificateHashData;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud de Información del Cliente
 *
 * <p>Esta clase representa una solicitud para obtener o limpiar información sobre un cliente en
 * el contexto de una estación de carga.</p>
 *
 * <p>OCPP 2.0.1 FINAL</p>
 */
public class CustomerInformationRequest extends RequestWithId {

    /** Datos personalizados adicionales */
    @Nullable
    private CustomData customData;

    /** Certificado del cliente */
    @Nullable
    private CertificateHashData customerCertificate;

    /**
     * Identificador sensible a mayúsculas y minúsculas para la autorización y el tipo de
     * autorización.
     */
    @Nullable
    private IdToken idToken;

    /** Identificador único de la solicitud */
    private Integer requestId;

    /** Indicador para solicitar información del cliente */
    private Boolean report;

    /** Indicador para limpiar información del cliente */
    private Boolean clear;

    /**
     * Identificador personalizado del cliente (por ejemplo, específico del proveedor) diferente
     * de `IdToken` y `Certificate`.
     */
    @Nullable
    private String customerIdentifier;

    /**
     * Constructor para la clase CustomerInformationRequest.
     *
     * @param requestId Identificador único de la solicitud.
     * @param report Indicador para solicitar información del cliente.
     * @param clear Indicador para limpiar información del cliente.
     */
    public CustomerInformationRequest(Integer requestId, Boolean report, Boolean clear) {
        setRequestId(requestId);
        setReport(report);
        setClear(clear);
    }

    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados adicionales de la solicitud.
     *
     * @param customData Datos personalizados adicionales.
     * @throws PropertyConstraintException si los datos personalizados no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Datos personalizados inválidos.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Obtiene el certificado del cliente.
     *
     * @return Certificado del cliente.
     */
    @Nullable
    public CertificateHashData getCustomerCertificate() {
        return customerCertificate;
    }

    /**
     * Establece el certificado del cliente.
     *
     * @param customerCertificate Certificado del cliente.
     * @throws PropertyConstraintException si el certificado no es válido.
     */
    @Nullable
    public void setCustomerCertificate(@Nullable CertificateHashData customerCertificate) {
        if (!isValidCustomerCertificate(customerCertificate)) {
            throw new PropertyConstraintException(customerCertificate, "Certificado inválido.");
        }
        this.customerCertificate = customerCertificate;
    }

    /**
     * Verifica si el certificado del cliente es válido.
     *
     * @param customerCertificate Certificado del cliente a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidCustomerCertificate(@Nullable CertificateHashData customerCertificate) {
        return customerCertificate == null || customerCertificate.validate();
    }

    /**Agregar datos personalizados adicionales a la solicitud.
     *
     * @param customerCertificate Datos personalizados adicionales.
     * @return La solicitud actualizada.
     */
    public CustomerInformationRequest withCustomerCertificate(
            @Nullable CertificateHashData customerCertificate) {
        setCustomerCertificate(customerCertificate);
        return this;
    }

    /**
     * Obtiene el identificador sensible a mayúsculas y minúsculas para la autorización y el tipo
     * de autorización.
     *
     * @return Identificador utilizado para la autorización.
     */
    @Nullable
    public IdToken getIdToken() {
        return idToken;
    }

    /**
     * Establece el identificador utilizado para la autorización.
     *
     * @param idToken Identificador.
     * @throws PropertyConstraintException si el identificador no es válido.
     */
    public void setIdToken(@Nullable IdToken idToken) {
        if (!isValidIdToken(idToken)) {
            throw new PropertyConstraintException(idToken, "IdToken inválido.");
        }
        this.idToken = idToken;
    }

    /**
     * Verifica si el identificador es válido.
     *
     * @param idToken Identificador a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidIdToken(@Nullable IdToken idToken) {
        return idToken == null || idToken.validate();
    }

    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Agrega un identificador sensible a mayúsculas y minúsculas para la autorización y el tipo de
     * autorización.
     *
     * @param idToken Identificador utilizado para la autorización.
     * @return La solicitud actualizada.
     */
    public CustomerInformationRequest withIdToken(@Nullable IdToken idToken) {
        setIdToken(idToken);
        return this;
    }

    public void setRequestId(Integer requestId) {
        if (!isValidRequestId(requestId)) {
            throw new PropertyConstraintException(requestId,"requestId no puede ser nulo.");
        }
        this.requestId = requestId;
    }

    /**
     * Verifica si el identificador de la solicitud es válido.
     *
     * @param requestId Identificador de la solicitud a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidRequestId(Integer requestId) {
        return requestId != null;
    }

    /**
     * Obtiene el identificador personalizado del cliente.
     *
     * @return Identificador personalizado del cliente.
     */
    public Boolean getReport() {
        return report;
    }

    /**
     * Establece el identificador personalizado del cliente.
     *
     * @param report Identificador personalizado del cliente.
     * @throws PropertyConstraintException si el identificador no es válido.
     */
    public void setReport(Boolean report) {
        if (!isValidReport(report)) {
            throw new PropertyConstraintException(report,"El campo 'report' no puede ser nulo.");
        }
        this.report = report;
    }

    /**
     * Verifica si el identificador personalizado del cliente es válido.
     *
     * @param report Identificador personalizado del cliente a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidReport(Boolean report) {
        return report != null;
    }

    /**
     * Obtiene el identificador para limpiar información del cliente.
     *
     * @return Identificador para limpiar información del cliente.
     */
    public Boolean getClear() {
        return clear;
    }

    /**
     * Establece el identificador para limpiar información del cliente.
     *
     * @param clear Identificador para limpiar información del cliente.
     * @throws PropertyConstraintException si el identificador no es válido.
     */
    public void setClear(Boolean clear) {
        if (!isValidClear(clear)) {
            throw new PropertyConstraintException(clear,"El campo 'clear' no puede ser nulo.");
        }
        this.clear = clear;
    }

    /**
     * Verifica si el identificador para limpiar información del cliente es válido.
     *
     * @param clear Identificador para limpiar información del cliente a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidClear(Boolean clear) {
        return clear != null;
    }

    @Nullable
    public String getCustomerIdentifier() {
        return customerIdentifier;
    }

    public void setCustomerIdentifier(@Nullable String customerIdentifier) {
        if (!isValidCustomerIdentifier(customerIdentifier)) {
            throw new PropertyConstraintException(customerIdentifier, "Identificador demasiado largo.");
        }
        this.customerIdentifier = customerIdentifier;
    }

    /**
     * Verifica si el identificador personalizado del cliente es válido.
     *
     * @param customerIdentifier Identificador personalizado del cliente a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidCustomerIdentifier(@Nullable String customerIdentifier) {
        return customerIdentifier == null || customerIdentifier.length() <= 64;
    }

    /**
     * Agrega un identificador personalizado del cliente.
     *
     * @param customerIdentifier Identificador personalizado del cliente.
     * @return La solicitud actualizada.
     */
    public CustomerInformationRequest withCustomerIdentifier(@Nullable String customerIdentifier) {
        setCustomerIdentifier(customerIdentifier);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidCustomerCertificate(customerCertificate)
                && isValidIdToken(idToken)
                && isValidRequestId(requestId)
                && isValidReport(report)
                && isValidClear(clear)
                && isValidCustomerIdentifier(customerIdentifier);
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerInformationRequest that = (CustomerInformationRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(customerCertificate, that.customerCertificate)
                && Objects.equals(idToken, that.idToken)
                && Objects.equals(requestId, that.requestId)
                && Objects.equals(report, that.report)
                && Objects.equals(clear, that.clear)
                && Objects.equals(customerIdentifier, that.customerIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, customerCertificate, idToken, requestId, report, clear, customerIdentifier);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("customerCertificate", customerCertificate)
                .add("idToken", idToken)
                .add("requestId", requestId)
                .add("report", report)
                .add("clear", clear)
                .add("customerIdentifier", customerIdentifier)
                .add("isValid", validate())
                .toString();
    }
}
