package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Enums.ChargingStateEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Enums.ReasonEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa una transacción de carga en el protocolo OCPP 2.0.1.
 *
 * <p>Incluye detalles como el ID de la transacción, el estado de carga, el tiempo de carga,
 * la razón de finalización de la transacción y otros datos relevantes.
 */
public class Transaction {

    /** Datos personalizados para extensiones o integraciones específicas. */
    @Nullable
    private CustomData customData;

    /** Identificador único de la transacción. */
    private String transactionId;

    /**
     * Estado actual de la carga (opcional).
     *
     * <p>Especifica si el estado de la carga ha cambiado durante la transacción.
     */
    @Nullable
    private ChargingStateEnum chargingState;

    /**
     * Tiempo total (en segundos) que la energía fluyó desde el EVSE al vehículo.
     *
     * <p>Debe ser menor o igual a la duración total de la transacción.
     */
    @Nullable
    private Integer timeSpentCharging;

    /**
     * Razón por la cual la transacción fue detenida (opcional).
     *
     * <p>Puede omitirse si la razón es "Local".
     */
    @Nullable
    private ReasonEnum stoppedReason;

    /**
     * ID asignado a la solicitud de inicio remoto.
     *
     * <p>Permite al CSMS vincular la transacción iniciada con la solicitud original.
     */
    @Nullable
    private Integer remoteStartId;

    /**
     * Constructor para crear una nueva instancia de Transaction.
     *
     * @param transactionId El ID único de la transacción.
     */
    public Transaction(String transactionId) {
        setTransactionId(transactionId);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Datos personalizados.
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
     * @param customData Datos personalizados.
     * @return Verdadero si los datos son válidos, falso de lo contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Establece los datos personalizados de forma fluida.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia con los datos personalizados establecidos.
     */
    public Transaction withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el ID de la transacción.
     *
     * @return ID de la transacción.
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Establece el ID de la transacción.
     *
     * @param transactionId ID de la transacción.
     */
    public void setTransactionId(String transactionId) {
        if (!isValidTransactionId(transactionId)) {
            throw new PropertyConstraintException(transactionId, "transactionId is invalid");
        }
        this.transactionId = transactionId;
    }

    /**
     * Verifica si el ID de la transacción es válido.
     *
     * @param transactionId ID de la transacción.
     * @return Verdadero si el ID es válido, falso de lo contrario.
     */
    private boolean isValidTransactionId(String transactionId) {
        return transactionId != null && transactionId.length() <= 36;
    }

    /**
     * Establece el ID de la transacción de forma fluida.
     *
     * @return Esta instancia con el ID de la transacción establecido.
     */
    @Nullable
    public ChargingStateEnum getChargingState() {
        return chargingState;
    }

    /**
     * Establece el estado de la transacción de forma fluida.
     *
     * @param chargingState Estado de la transacción.
     */
    public void setChargingState(@Nullable ChargingStateEnum chargingState) {
        this.chargingState = chargingState;
    }

    /**
     * Obtiene el estado de la transacción.
     *
     * @return Estado de la transacción.
     */
    public Transaction withChargingState(@Nullable ChargingStateEnum chargingState) {
        setChargingState(chargingState);
        return this;
    }

    /**
     * Obtiene el tiempo total de carga.
     *
     * @return Tiempo total de carga.
     */
    @Nullable
    public Integer getTimeSpentCharging() {
        return timeSpentCharging;
    }

    /**
     * Establece el tiempo total de carga.
     *
     * @param timeSpentCharging Tiempo total de carga.
     */
    public void setTimeSpentCharging(@Nullable Integer timeSpentCharging) {
        this.timeSpentCharging = timeSpentCharging;
    }

    /**
     * Establece el tiempo total de carga de forma fluida.
     *
     * @param timeSpentCharging Tiempo total de carga.
     * @return Esta instancia con el tiempo total de carga establecido.
     */
    public Transaction withTimeSpentCharging(@Nullable Integer timeSpentCharging) {
        setTimeSpentCharging(timeSpentCharging);
        return this;
    }

    /**
     * Obtiene la razón por la que la transacción fue detenida.
     *
     * @return Razón por la que la transacción fue detenida.
     */
    @Nullable
    public ReasonEnum getStoppedReason() {
        return stoppedReason;
    }

    /**
     * Establece la razón por la que la transacción fue detenida.
     *
     * @param stoppedReason Razón por la que la transacción fue detenida.
     */
    public void setStoppedReason(@Nullable ReasonEnum stoppedReason) {
        this.stoppedReason = stoppedReason;
    }

    /**
     * Establece la razón por la que la transacción fue detenida.
     *
     * @param stoppedReason Razón por la que la transacción fue detenida.
     */
    public Transaction withStoppedReason(@Nullable ReasonEnum stoppedReason) {
        setStoppedReason(stoppedReason);
        return this;
    }

    /**
     * Obtiene el ID de la solicitud de inicio remoto.
     *
     * @return ID de la solicitud de inicio remoto.
     */
    @Nullable
    public Integer getRemoteStartId() {
        return remoteStartId;
    }

    /**
     * Establece el ID de la solicitud de inicio remoto.
     *
     * @param remoteStartId ID de la solicitud de inicio remoto.
     */
    public void setRemoteStartId(@Nullable Integer remoteStartId) {
        this.remoteStartId = remoteStartId;
    }

    /**
     * Establece el ID de la solicitud de inicio remoto de forma fluida.
     *
     * @param remoteStartId ID de la solicitud de inicio remoto.
     * @return Esta instancia con el ID de la solicitud de inicio remoto establecido.
     */
    public Transaction withRemoteStartId(@Nullable Integer remoteStartId) {
        setRemoteStartId(remoteStartId);
        return this;
    }


    public boolean validate() {
        return isValidCustomData(customData) && isValidTransactionId(transactionId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transaction that = (Transaction) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(transactionId, that.transactionId)
                && Objects.equals(chargingState, that.chargingState)
                && Objects.equals(timeSpentCharging, that.timeSpentCharging)
                && Objects.equals(stoppedReason, that.stoppedReason)
                && Objects.equals(remoteStartId, that.remoteStartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData, transactionId, chargingState, timeSpentCharging, stoppedReason, remoteStartId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("transactionId", transactionId)
                .add("chargingState", chargingState)
                .add("timeSpentCharging", timeSpentCharging)
                .add("stoppedReason", stoppedReason)
                .add("remoteStartId", remoteStartId)
                .add("isValid", validate())
                .toString();
    }
}
