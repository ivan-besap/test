package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Representa el perfil de carga que será utilizado por la estación de carga para controlar
 * la energía entregada durante una transacción de carga en OCPP 1.6.
 * Un perfil de carga puede incluir varios parámetros, como la duración y los límites de potencia.
 */
@XmlRootElement
public class ChargingProfile {

    @NotNull(message = "chargingProfileId es obligatorio")
    @JsonProperty("chargingProfileId")
    private Integer chargingProfileId;

    @JsonProperty("transactionId")
    private Integer transactionId;

    @NotNull(message = "stackLevel es obligatorio")
    @JsonProperty("stackLevel")
    private Integer stackLevel;

    @NotNull(message = "chargingProfilePurpose es obligatorio")
    @JsonProperty("chargingProfilePurpose")
    private ChargingProfilePurposeType chargingProfilePurpose;

    @NotNull(message = "chargingProfileKind es obligatorio")
    @JsonProperty("chargingProfileKind")
    private ChargingProfileKindType chargingProfileKind;

    @JsonProperty("recurrencyKind")
    private RecurrencyKindType recurrencyKind;

    @JsonProperty("validFrom")
    private ZonedDateTime validFrom;

    @JsonProperty("validTo")
    private ZonedDateTime validTo;

    @NotNull(message = "chargingSchedule es obligatorio")
    @JsonProperty("chargingSchedule")
    private ChargingSchedule chargingSchedule;

    /**
     * Constructor por defecto.
     */
    public ChargingProfile() {}

    /**
     * Constructor con los campos requeridos.
     *
     * @param chargingProfileId Identificador único para este perfil de carga.
     * @param stackLevel Nivel de prioridad en la pila de perfiles.
     * @param chargingProfilePurpose Propósito del perfil de carga.
     * @param chargingProfileKind Tipo de perfil de carga.
     * @param chargingSchedule Horario de carga que especifica los límites de potencia o corriente.
     */
    public ChargingProfile(Integer chargingProfileId, Integer stackLevel, ChargingProfilePurposeType chargingProfilePurpose,
                           ChargingProfileKindType chargingProfileKind, ChargingSchedule chargingSchedule) {
        this.chargingProfileId = chargingProfileId;
        this.stackLevel = stackLevel;
        this.chargingProfilePurpose = chargingProfilePurpose;
        this.chargingProfileKind = chargingProfileKind;
        this.chargingSchedule = chargingSchedule;
    }

    // Getters y setters

    public Integer getChargingProfileId() {
        return chargingProfileId;
    }

    @XmlElement
    public void setChargingProfileId(Integer chargingProfileId) {
        this.chargingProfileId = chargingProfileId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    @XmlElement
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getStackLevel() {
        return stackLevel;
    }

    @XmlElement
    public void setStackLevel(Integer stackLevel) {
        if (stackLevel == null || stackLevel < 0) {
            throw new IllegalArgumentException("stackLevel debe ser mayor o igual a 0");
        }
        this.stackLevel = stackLevel;
    }

    public ChargingProfilePurposeType getChargingProfilePurpose() {
        return chargingProfilePurpose;
    }

    @XmlElement
    public void setChargingProfilePurpose(ChargingProfilePurposeType chargingProfilePurpose) {
        this.chargingProfilePurpose = chargingProfilePurpose;
    }

    public ChargingProfileKindType getChargingProfileKind() {
        return chargingProfileKind;
    }

    @XmlElement
    public void setChargingProfileKind(ChargingProfileKindType chargingProfileKind) {
        this.chargingProfileKind = chargingProfileKind;
    }

    public RecurrencyKindType getRecurrencyKind() {
        return recurrencyKind;
    }

    @XmlElement
    public void setRecurrencyKind(RecurrencyKindType recurrencyKind) {
        this.recurrencyKind = recurrencyKind;
    }

    public ZonedDateTime getValidFrom() {
        return validFrom;
    }

    @XmlElement
    public void setValidFrom(ZonedDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public ZonedDateTime getValidTo() {
        return validTo;
    }

    @XmlElement
    public void setValidTo(ZonedDateTime validTo) {
        this.validTo = validTo;
    }

    public ChargingSchedule getChargingSchedule() {
        return chargingSchedule;
    }

    @XmlElement
    public void setChargingSchedule(ChargingSchedule chargingSchedule) {
        this.chargingSchedule = chargingSchedule;
    }

    /**
     * Método para validar que el perfil de carga sea válido.
     * Verifica que los campos requeridos estén presentes y correctos.
     *
     * @return true si el perfil es válido, false en caso contrario.
     */
    public boolean validate() {
        boolean valid = chargingProfileId != null;
        valid &= stackLevel != null && stackLevel >= 0;
        valid &= chargingProfilePurpose != null;
        valid &= (transactionId == null || chargingProfilePurpose == ChargingProfilePurposeType.TxProfile);
        valid &= chargingProfileKind != null;
        valid &= chargingSchedule != null && chargingSchedule.validate();
        return valid;
    }

    @Override
    public String toString() {
        return "ChargingProfile{" +
                "chargingProfileId=" + chargingProfileId +
                ", transactionId=" + transactionId +
                ", stackLevel=" + stackLevel +
                ", chargingProfilePurpose=" + chargingProfilePurpose +
                ", chargingProfileKind=" + chargingProfileKind +
                ", recurrencyKind=" + recurrencyKind +
                ", validFrom=" + validFrom +
                ", validTo=" + validTo +
                ", chargingSchedule=" + chargingSchedule +
                ", isValid=" + validate() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChargingProfile that = (ChargingProfile) o;
        return Objects.equals(chargingProfileId, that.chargingProfileId) &&
                Objects.equals(transactionId, that.transactionId) &&
                Objects.equals(stackLevel, that.stackLevel) &&
                chargingProfilePurpose == that.chargingProfilePurpose &&
                chargingProfileKind == that.chargingProfileKind &&
                recurrencyKind == that.recurrencyKind &&
                Objects.equals(validFrom, that.validFrom) &&
                Objects.equals(validTo, that.validTo) &&
                Objects.equals(chargingSchedule, that.chargingSchedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chargingProfileId, transactionId, stackLevel, chargingProfilePurpose, chargingProfileKind,
                recurrencyKind, validFrom, validTo, chargingSchedule);
    }
}
