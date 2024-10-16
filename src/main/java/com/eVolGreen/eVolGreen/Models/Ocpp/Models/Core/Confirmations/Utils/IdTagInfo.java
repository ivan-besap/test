package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Validatable;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.AuthorizeConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Enums.AuthorizationStatus;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.StartTransactionConfirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.StopTransactionConfirmation;

import java.time.ZonedDateTime;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Contiene información de estado sobre un identificador. Es devuelto en las respuestas de {@link AuthorizeConfirmation},
 * {@link StartTransactionConfirmation} y {@link StopTransactionConfirmation}.
 * Si no se proporciona expiryDate, el estado no tiene una fecha de finalización.
 */
@XmlRootElement
@XmlType(propOrder = {"status", "expiryDate", "parentIdTag"})
public class IdTagInfo implements Validatable {

    private ZonedDateTime expiryDate;
    private String parentIdTag;
    private AuthorizationStatus status;

    /**
     * Constructor predeterminado (obsoleto).
     * No se recomienda su uso, ya que puede dejar de lado la asignación de campos obligatorios.
     */
    @Deprecated
    public IdTagInfo() {}

    /**
     * Constructor que maneja los campos obligatorios.
     *
     * @param status el {@link AuthorizationStatus} para IdTag, ver {@link #setStatus(AuthorizationStatus)}
     */
    public IdTagInfo(AuthorizationStatus status) {
        this.status = status;
    }

    // Constructor que maneja el status y la fecha de expiración
    public IdTagInfo(AuthorizationStatus status, ZonedDateTime expiryDate) {
        this.status = status;
        this.expiryDate = expiryDate;
    }

    /**
     * Obtiene la fecha de caducidad, que indica cuándo el idTag debe ser eliminado del cache de autorización.
     *
     * @return Fecha de caducidad del idTag.
     */
    public ZonedDateTime getExpiryDate() {
        return expiryDate;
    }

    /**
     * Establece la fecha de caducidad opcional para el idTag.
     *
     * @param expiryDate La fecha y hora en que el idTag debe ser eliminado del cache de autorización.
     */
    @XmlElement
    public void setExpiryDate(ZonedDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Obtiene el identificador del padre del idTag actual, si existe.
     *
     * @return El IdToken del padre.
     */
    public String getParentIdTag() {
        return parentIdTag;
    }

    /**
     * Establece el identificador del padre opcional para el idTag.
     *
     * @param parentIdTag Un IdToken que representa el idTag padre.
     */
    @XmlElement
    public void setParentIdTag(String parentIdTag) {
        this.parentIdTag = parentIdTag;
    }

    /**
     * Obtiene el estado de autorización del idTag.
     *
     * @return El {@link AuthorizationStatus} del idTag.
     */
    public AuthorizationStatus getStatus() {
        return status;
    }

    /**
     * Establece el estado de autorización requerido para el idTag.
     *
     * @param status El {@link AuthorizationStatus} que representa si el idTag ha sido aceptado o no por el sistema central.
     */
    @XmlElement
    public void setStatus(AuthorizationStatus status) {
        this.status = status;
    }

    /**
     * Valida el estado del objeto IdTagInfo.
     * Verifica que el estado de autorización no sea nulo.
     *
     * @return true si el objeto es válido, de lo contrario false.
     */
    @Override
    public boolean validate() {
        return this.status != null;
    }

    /**
     * Compara este objeto con otro para verificar la igualdad.
     *
     * @param o El objeto a comparar.
     * @return true si los objetos son iguales, de lo contrario false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdTagInfo idTagInfo = (IdTagInfo) o;
        return Objects.equals(expiryDate, idTagInfo.expiryDate)
                && Objects.equals(parentIdTag, idTagInfo.parentIdTag)
                && status == idTagInfo.status;
    }

    /**
     * Genera un código hash para este objeto.
     *
     * @return Un valor hash basado en los campos del objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(expiryDate, parentIdTag, status);
    }

    /**
     * Proporciona una representación de cadena del objeto.
     *
     * @return Una cadena que describe el objeto.
     */
    @Override
    public String toString() {
        return "IdTagInfo{" +
                "expiryDate=" + expiryDate +
                ", parentIdTag='" + parentIdTag + '\'' +
                ", status=" + status +
                '}';
    }
}
