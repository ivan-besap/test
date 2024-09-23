package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

/**
 * Representa la respuesta a la solicitud StartTransaction en OCPP 1.6.
 * Enviada por el sistema central a la estación de carga en respuesta a una {@link StartTransactionRequest}.
 * Esta clase extiende {@link Confirmation} para mantener la consistencia con otras confirmaciones en el sistema.
 *
 * <b>Campos:</b>
 * - {@code idTagInfo}: Información sobre el estado de autorización, expiración e ID padre (obligatorio).
 * - {@code transactionId}: Identificador de la transacción suministrado por el sistema central (obligatorio).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     IdTagInfo idTagInfo = new IdTagInfo(AuthorizationStatus.ACCEPTED);
 *     StartTransactionConfirmation confirmation = new StartTransactionConfirmation(idTagInfo, 12345);
 *
 *     if (confirmation.validate()) {
 *         System.out.println("Confirmación válida: " + confirmation);
 *         // Procesar la confirmación
 *     } else {
 *         System.out.println("Confirmación inválida");
 *     }
 * </pre>
 */
@XmlRootElement(name = "startTransactionResponse")
@XmlType(propOrder = {"transactionId", "idTagInfo"})
public class StartTransactionConfirmation extends Confirmation {

    @NotNull(message = "idTagInfo es obligatorio")
    @JsonProperty("idTagInfo")
    private IdTagInfo idTagInfo;

    @NotNull(message = "transactionId es obligatorio")
    @JsonProperty("transactionId")
    private Integer transactionId;

    /**
     * Constructor por defecto.
     * @deprecated Use {@link #StartTransactionConfirmation(IdTagInfo, Integer)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public StartTransactionConfirmation() {}

    /**
     * Constructor con campos requeridos.
     *
     * @param idTagInfo Información sobre la etiqueta de identificación.
     * @param transactionId Identificador de la transacción.
     */
    public StartTransactionConfirmation(IdTagInfo idTagInfo, Integer transactionId) {
        setIdTagInfo(idTagInfo);
        setTransactionId(transactionId);
    }

    /**
     * Obtiene la información sobre la etiqueta de identificación.
     *
     * @return {@link IdTagInfo} con la información de autorización.
     */
    public IdTagInfo getIdTagInfo() {
        return idTagInfo;
    }

    /**
     * Establece la información sobre la etiqueta de identificación.
     *
     * @param idTagInfo {@link IdTagInfo} con la información de autorización.
     */
    @XmlElement
    public void setIdTagInfo(IdTagInfo idTagInfo) {
        this.idTagInfo = idTagInfo;
    }

    /**
     * Obtiene el identificador de la transacción.
     *
     * @return Integer con el ID de la transacción.
     */
    public Integer getTransactionId() {
        return transactionId;
    }

    /**
     * Establece el identificador de la transacción.
     *
     * @param transactionId Integer con el ID de la transacción.
     */
    @XmlElement
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Valida si los datos de la confirmación son correctos.
     *
     * @return true si los datos son válidos, false en caso contrario.
     */
    @Override
    public boolean validate() {
        boolean valid = true;
        if (valid &= idTagInfo != null) valid &= idTagInfo.validate();
        valid &= transactionId != null;
        return valid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StartTransactionConfirmation that = (StartTransactionConfirmation) o;
        return Objects.equals(idTagInfo, that.idTagInfo) &&
                Objects.equals(transactionId, that.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTagInfo, transactionId);
    }

    @Override
    public String toString() {
        return "StartTransactionConfirmation{" +
                "idTagInfo=" + idTagInfo +
                ", transactionId=" + transactionId +
                ", isValid=" + validate() +
                '}';
    }
}