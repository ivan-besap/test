package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Enums.ChargingProfileKindEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Enums.ChargingProfilePurposeEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Enums.RecurrencyKindEnum;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * Perfil de Carga.
 *
 * <p>Representa un perfil de carga que incluye un conjunto de horarios (ChargingSchedule) que
 * describen la cantidad de energía o corriente que puede suministrarse en intervalos de tiempo
 * específicos.
 */
public class ChargingProfile {
    /** Datos personalizados asociados al perfil de carga. */
    @Nullable
    private CustomData customData;

    /** Identificador único del perfil de carga. */
    private Integer id;

    /**
     * Nivel en la jerarquía de perfiles.
     *
     * <p>Valores más altos tienen prioridad sobre valores más bajos. El nivel más bajo es 0.
     */
    private Integer stackLevel;

    /** Propósito del perfil de carga. */
    private ChargingProfilePurposeEnum chargingProfilePurpose;

    /** Tipo de programación del perfil de carga. */
    private ChargingProfileKindEnum chargingProfileKind;

    /** Punto de inicio de una recurrencia (opcional). */
    @Nullable private RecurrencyKindEnum recurrencyKind;

    /**
     * Fecha desde la cual el perfil de carga es válido.
     *
     * <p>Si no se proporciona, el perfil es válido tan pronto como se recibe en la estación de carga.
     */
    @Nullable private ZonedDateTime validFrom;

    /**
     * Fecha hasta la cual el perfil de carga es válido.
     *
     * <p>Si no se proporciona, el perfil es válido hasta que se reemplace por otro perfil.
     */
    @Nullable private ZonedDateTime validTo;

    /**
     * Lista de horarios de carga.
     *
     * <p>Define los periodos de carga y los límites aplicables en cada intervalo de tiempo.
     */
    private ChargingSchedule[] chargingSchedule;

    /**
     * Identificador de la transacción asociado al perfil de carga.
     *
     * <p>Solo debe incluirse si el propósito del perfil de carga es `TxProfile`.
     */
    @Nullable private String transactionId;

    /**
     * Constructor para la clase ChargingProfile.
     *
     * @param id Identificador único del perfil.
     * @param stackLevel Nivel jerárquico del perfil.
     * @param chargingProfilePurpose Propósito del perfil.
     * @param chargingProfileKind Tipo de programación del perfil.
     * @param chargingSchedule Lista de horarios de carga.
     */
    public ChargingProfile(
            Integer id,
            Integer stackLevel,
            ChargingProfilePurposeEnum chargingProfilePurpose,
            ChargingProfileKindEnum chargingProfileKind,
            ChargingSchedule[] chargingSchedule) {
        setId(id);
        setStackLevel(stackLevel);
        setChargingProfilePurpose(chargingProfilePurpose);
        setChargingProfileKind(chargingProfileKind);
        setChargingSchedule(chargingSchedule);
    }

    /**
     * Obtiene los datos personalizados asociados al perfil de carga.
     *
     * @return Objeto CustomData con los datos personalizados, o {@code null} si no existen.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados para el perfil de carga.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados son inválidos.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos son válidos, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Método fluido para establecer los datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return Esta misma instancia para facilitar la concatenación de métodos.
     */
    public ChargingProfile withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el identificador único del perfil de carga.
     *
     * @return Identificador del perfil.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del perfil de carga.
     *
     * @param id Identificador del perfil.
     */
    public void setId(Integer id) {
        if (!isValidId(id)) {
            throw new PropertyConstraintException(id, "El identificador es inválido.");
        }
        this.id = id;
    }

    /**
     * Verifica si el identificador es válido.
     *
     * @param id Identificador a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidId(Integer id) {
        return id != null;
    }

    /**
     * Obtiene el nivel jerárquico del perfil de carga.
     *
     * @return Nivel jerárquico del perfil.
     */
    public Integer getStackLevel() {
        return stackLevel;
    }

    /**
     * Establece el nivel jerárquico del perfil de carga.
     *
     * @param stackLevel Nivel jerárquico del perfil.
     */
    public void setStackLevel(Integer stackLevel) {
        if (!isValidStackLevel(stackLevel)) {
            throw new PropertyConstraintException(stackLevel, "El nivel jerárquico es inválido.");
        }
        this.stackLevel = stackLevel;
    }

    /**
     * Verifica si el nivel jerárquico es válido.
     *
     * @param stackLevel Nivel jerárquico a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidStackLevel(Integer stackLevel) {
        return stackLevel != null;
    }

    /**
     * Obtiene el propósito del perfil de carga.
     *
     * @return Propósito del perfil.
     */
    public ChargingProfilePurposeEnum getChargingProfilePurpose() {
        return chargingProfilePurpose;
    }

    /**
     * Establece el propósito del perfil de carga.
     *
     * @param chargingProfilePurpose Propósito del perfil.
     */
    public void setChargingProfilePurpose(ChargingProfilePurposeEnum chargingProfilePurpose) {
        if (!isValidChargingProfilePurpose(chargingProfilePurpose)) {
            throw new PropertyConstraintException(
                    chargingProfilePurpose, "El propósito del perfil es inválido.");
        }
        this.chargingProfilePurpose = chargingProfilePurpose;
    }

    /**
     * Verifica si el propósito del perfil es válido.
     *
     * @param chargingProfilePurpose Propósito del perfil a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidChargingProfilePurpose(ChargingProfilePurposeEnum chargingProfilePurpose) {
        return chargingProfilePurpose != null;
    }

    /**
     * Obtiene el tipo de programación del perfil de carga.
     *
     * @return Tipo de programación del perfil.
     */
    public ChargingProfileKindEnum getChargingProfileKind() {
        return chargingProfileKind;
    }

    /**
     * Obtiene la lista de horarios de carga.
     *
     * @return Lista de horarios de carga.
     */
    public void setChargingProfileKind(ChargingProfileKindEnum chargingProfileKind) {
        if (!isValidChargingProfileKind(chargingProfileKind)) {
            throw new PropertyConstraintException(chargingProfileKind, "chargingProfileKind is invalid");
        }
        this.chargingProfileKind = chargingProfileKind;
    }

    /**
     * Verifica si el tipo de programación del perfil es válido.
     *
     * @param chargingProfileKind Tipo de programación del perfil a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidChargingProfileKind(ChargingProfileKindEnum chargingProfileKind) {
        return chargingProfileKind != null;
    }

    /**
     * Obtiene la lista de horarios de carga.
     *
     * @return Lista de horarios de carga.
     */
    @Nullable
    public RecurrencyKindEnum getRecurrencyKind() {
        return recurrencyKind;
    }

    /**
     * Establece el tipo de recurrencia del perfil de carga.
     *
     * @param recurrencyKind Tipo de recurrencia del perfil.
     */
    public void setRecurrencyKind(@Nullable RecurrencyKindEnum recurrencyKind) {
        this.recurrencyKind = recurrencyKind;
    }

    /**
     * Verifica si el tipo de recurrencia es válido.
     *
     * @param recurrencyKind Tipo de recurrencia a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    public ChargingProfile withRecurrencyKind(@Nullable RecurrencyKindEnum recurrencyKind) {
        setRecurrencyKind(recurrencyKind);
        return this;
    }

    /**
     * Obtiene la fecha desde la cual el perfil de carga es válido.
     *
     * @return Fecha desde la cual el perfil es válido.
     */
    @Nullable
    public ZonedDateTime getValidFrom() {
        return validFrom;
    }

    /**
     * Establece la fecha desde la cual el perfil de carga es válido.
     *
     * @param validFrom Fecha desde la cual el perfil es válido.
     */
    public void setValidFrom(@Nullable ZonedDateTime validFrom) {
        this.validFrom = validFrom;
    }

    /**
     * Verifica si la fecha desde la cual el perfil es válido es válida.
     *
     * @param validFrom Fecha a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    public ChargingProfile withValidFrom(@Nullable ZonedDateTime validFrom) {
        setValidFrom(validFrom);
        return this;
    }

    /**
     * Obtiene la fecha hasta la cual el perfil de carga es válido.
     *
     * @return Fecha hasta la cual el perfil es válido.
     */
    @Nullable
    public ZonedDateTime getValidTo() {
        return validTo;
    }

    /**
     * Establece la fecha hasta la cual el perfil de carga es válido.
     *
     * @param validTo Fecha hasta la cual el perfil es válido.
     */
    public void setValidTo(@Nullable ZonedDateTime validTo) {
        this.validTo = validTo;
    }

    /**
     * Verifica si la fecha hasta la cual el perfil es válido es válida.
     *
     * @param validTo Fecha a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    public ChargingProfile withValidTo(@Nullable ZonedDateTime validTo) {
        setValidTo(validTo);
        return this;
    }

    /**
     * Obtiene la lista de horarios de carga.
     *
     * @return Lista de horarios de carga.
     */
    public ChargingSchedule[] getChargingSchedule() {
        return chargingSchedule;
    }

    /**
     * Establece la lista de horarios de carga.
     *
     * @param chargingSchedule Lista de horarios de carga.
     */
    public void setChargingSchedule(ChargingSchedule[] chargingSchedule) {
        if (!isValidChargingSchedule(chargingSchedule)) {
            throw new PropertyConstraintException(chargingSchedule, "chargingSchedule is invalid");
        }
        this.chargingSchedule = chargingSchedule;
    }

    /**
     * Verifica si la lista de horarios de carga es válida.
     *
     * @param chargingSchedule Lista de horarios de carga a validar.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidChargingSchedule(ChargingSchedule[] chargingSchedule) {
        return chargingSchedule != null
                && chargingSchedule.length >= 1
                && chargingSchedule.length <= 3
                && Arrays.stream(chargingSchedule).allMatch(item -> item.validate());
    }

    /**
     * Obtiene el identificador de la transacción asociado al perfil de carga.
     *
     * @return Identificador de la transacción.
     */
    @Nullable
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Establece el identificador de la transacción asociado al perfil de carga.
     *
     * @param transactionId Identificador de la transacción.
     */
    public void setTransactionId(@Nullable String transactionId) {
        if (!isValidTransactionId(transactionId)) {
            throw new PropertyConstraintException(transactionId, "transactionId is invalid");
        }
        this.transactionId = transactionId;
    }

    /**
     * Verifica si el identificador de la transacción es válido.
     * @param transactionId
     * @return
     */
    private boolean isValidTransactionId(@Nullable String transactionId) {
        return transactionId == null || transactionId.length() <= 36;
    }

    /**
     * Agrega el identificador de la transacción asociado al perfil de carga.
     *
     * @param transactionId Identificador de la transacción.
     * @return Esta misma instancia para facilitar la concatenación de métodos.
     */
    public ChargingProfile withTransactionId(@Nullable String transactionId) {
        setTransactionId(transactionId);
        return this;
    }

    public boolean validate() {
        return isValidCustomData(customData)
                && isValidId(id)
                && isValidStackLevel(stackLevel)
                && isValidChargingProfilePurpose(chargingProfilePurpose)
                && isValidChargingProfileKind(chargingProfileKind)
                && isValidChargingSchedule(chargingSchedule)
                && isValidTransactionId(transactionId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChargingProfile that = (ChargingProfile) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(id, that.id)
                && Objects.equals(stackLevel, that.stackLevel)
                && Objects.equals(chargingProfilePurpose, that.chargingProfilePurpose)
                && Objects.equals(chargingProfileKind, that.chargingProfileKind)
                && Objects.equals(recurrencyKind, that.recurrencyKind)
                && Objects.equals(validFrom, that.validFrom)
                && Objects.equals(validTo, that.validTo)
                && Arrays.equals(chargingSchedule, that.chargingSchedule)
                && Objects.equals(transactionId, that.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData,
                id,
                stackLevel,
                chargingProfilePurpose,
                chargingProfileKind,
                recurrencyKind,
                validFrom,
                validTo,
                Arrays.hashCode(chargingSchedule),
                transactionId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("id", id)
                .add("stackLevel", stackLevel)
                .add("chargingProfilePurpose", chargingProfilePurpose)
                .add("chargingProfileKind", chargingProfileKind)
                .add("recurrencyKind", recurrencyKind)
                .add("validFrom", validFrom)
                .add("validTo", validTo)
                .add("chargingSchedule", chargingSchedule)
                .add("transactionId", transactionId)
                .add("isValid", validate())
                .toString();
    }
}
