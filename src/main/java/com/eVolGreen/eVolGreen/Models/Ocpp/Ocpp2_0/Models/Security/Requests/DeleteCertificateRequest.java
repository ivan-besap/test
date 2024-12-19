package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Security.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.EnergyAndCostManagement.Requests.Utils.CertificateHashData;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud para eliminar un certificado específico mediante su hash.
 */
public class DeleteCertificateRequest extends RequestWithId {

    /** Datos personalizados para extensiones específicas. */
    @Nullable
    private CustomData customData;

    /** Información del hash del certificado a eliminar. */
    private CertificateHashData certificateHashData;

    /**
     * Constructor para inicializar la solicitud con los datos del hash del certificado.
     *
     * @param certificateHashData Información del hash del certificado.
     */
    public DeleteCertificateRequest(CertificateHashData certificateHashData) {
        setCertificateHashData(certificateHashData);
    }

    /**
     * Obtiene los datos personalizados de la solicitud.
     *
     * @return Datos personalizados, o null si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados de la solicitud.
     *
     * @param customData Datos personalizados.
     * @throws PropertyConstraintException Si los datos personalizados no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData es inválido");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return true si son válidos, false de lo contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados y retorna la instancia actualizada.
     *
     * @param customData Datos personalizados.
     * @return La instancia actualizada de la solicitud.
     */
    public DeleteCertificateRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la información del hash del certificado a eliminar.
     *
     * @return Información del hash del certificado.
     */
    public CertificateHashData getCertificateHashData() {
        return certificateHashData;
    }

    /**
     * Establece la información del hash del certificado a eliminar.
     *
     * @param certificateHashData Información del hash del certificado.
     * @throws PropertyConstraintException Si los datos del hash no son válidos.
     */
    public void setCertificateHashData(CertificateHashData certificateHashData) {
        if (!isValidCertificateHashData(certificateHashData)) {
            throw new PropertyConstraintException(certificateHashData, "certificateHashData es inválido");
        }
        this.certificateHashData = certificateHashData;
    }

    /**
     * Verifica si la información del hash del certificado es válida.
     *
     * @param certificateHashData Información del hash del certificado.
     * @return true si es válida, false de lo contrario.
     */
    private boolean isValidCertificateHashData(CertificateHashData certificateHashData) {
        return certificateHashData != null && certificateHashData.validate();
    }

    /**
     * Valida si los datos de la solicitud son correctos.
     *
     * @return true si la solicitud es válida, false de lo contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidCertificateHashData(certificateHashData);
    }

    /**
     * Indica si la solicitud está relacionada con una transacción.
     *
     * @return false ya que esta solicitud no está relacionada con una transacción.
     */
    @Override
    public boolean transactionRelated() {
        return false;
    }

    /**
     * Obtiene el nombre de la acción asociada a esta solicitud.
     *
     * @return Una cadena vacía, ya que no se define una acción específica.
     */

    @Override
    public String getAction() {
        return "";
    }

    /**
     * Obtiene el índice de sesión asociado a esta solicitud.
     *
     * @return null ya que no hay un índice de sesión definido.
     */
    @Override
    public UUID getSessionIndex() {
        return null;
    }

    /**
     * Compara esta instancia con otro objeto.
     *
     * @param o El objeto con el que comparar.
     * @return true si ambos objetos son iguales, false de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeleteCertificateRequest that = (DeleteCertificateRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(certificateHashData, that.certificateHashData);
    }

    /**
     * Genera un código hash para esta instancia.
     *
     * @return El código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customData, certificateHashData);
    }

    /**
     * Convierte la instancia en una representación de cadena.
     *
     * @return Una cadena que representa la instancia.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("certificateHashData", certificateHashData)
                .add("isValid", validate())
                .toString();
    }
}
