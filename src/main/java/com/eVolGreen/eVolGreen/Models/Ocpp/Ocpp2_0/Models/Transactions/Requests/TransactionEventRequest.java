package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils.EVSE;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils.MeterValue;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Enums.TransactionEventEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Enums.TriggerReasonEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Utils.IdToken;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Utils.Transaction;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * TransactionEventRequest
 *
 * <p>Este mensaje representa un evento relacionado con una transacción, que puede ser de inicio,
 * actualización o finalización, enviado desde una estación de carga al CSMS en el contexto del
 * protocolo OCPP 2.0.1.
 */
public class TransactionEventRequest extends RequestWithId {
    /** Datos personalizados opcionales. */
    @Nullable
    private CustomData customData;

    /** Tipo de evento de transacción: "Started", "Updated" o "Ended". */
    private TransactionEventEnum eventType;

    /**
     * Colección de valores de medición muestreados relacionados con el evento de la transacción.
     * Opcional.
     */
    @Nullable private MeterValue[] meterValue;

    /** Marca de tiempo del evento. */
    private ZonedDateTime timestamp;

    /** Razón que desencadena el envío del evento desde la estación de carga. */
    private TriggerReasonEnum triggerReason;

    /**
     * Número de secuencia incremental que ayuda a determinar si se han recibido todos los mensajes
     * relacionados con una transacción.
     */
    private Integer seqNo;

    /**
     * Indica si el evento ocurrió cuando la estación de carga estaba desconectada. Por defecto es
     * "false".
     */
    @Nullable private Boolean offline;

    /**
     * Número de fases usadas durante la transacción. Es opcional y, si no se proporciona, el CSMS
     * puede inferirlo mediante gestión de dispositivos.
     */
    @Nullable private Integer numberOfPhasesUsed;

    /** Corriente máxima del cable conectado en amperios (A). */
    @Nullable private Integer cableMaxCurrent;

    /** ID de la reserva asociada que termina como resultado de este evento. */
    @Nullable private Integer reservationId;

    /** Información detallada de la transacción. */
    private Transaction transactionInfo;

    /** Información del EVSE (Electric Vehicle Supply Equipment). Opcional. */
    @Nullable private EVSE evse;

    /**
     * Token de identificación utilizado para la autorización. Puede incluir información sobre el
     * tipo de autorización.
     */
    @Nullable private IdToken idToken;

    /**
     * Constructor para TransactionEventRequest.
     *
     * @param eventType Tipo de evento (Started, Updated, Ended).
     * @param timestamp Marca de tiempo del evento.
     * @param triggerReason Razón del evento.
     * @param seqNo Número de secuencia incremental.
     * @param transactionInfo Información de la transacción.
     */
    public TransactionEventRequest(
            TransactionEventEnum eventType,
            ZonedDateTime timestamp,
            TriggerReasonEnum triggerReason,
            Integer seqNo,
            Transaction transactionInfo) {
        setEventType(eventType);
        setTimestamp(timestamp);
        setTriggerReason(triggerReason);
        setSeqNo(seqNo);
        setTransactionInfo(transactionInfo);
    }

    /**
     * Obtiene los datos personalizados opcionales.
     *
     * @return Datos personalizados opcionales.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados opcionales.
     *
     * @param customData Datos personalizados opcionales.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData is invalid");
        }
        this.customData = customData;
    }

    /**
     * Obtiene el tipo de evento de transacción.
     *
     * @return Tipo de evento de transacción.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Establece el tipo de evento de transacción.
     *
     */
    public TransactionEventRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el tipo de evento de transacción.
     *
     * @return Tipo de evento de transacción.
     */
    public TransactionEventEnum getEventType() {
        return eventType;
    }

    /**
     * Establece el tipo de evento de transacción.
     *
     * @param eventType Tipo de evento de transacción.
     */
    public void setEventType(TransactionEventEnum eventType) {
        if (!isValidEventType(eventType)) {
            throw new PropertyConstraintException(eventType, "eventType is invalid");
        }
        this.eventType = eventType;
    }

    /**
     * Establece el tipo de evento de transacción.
     *
     * @param eventType Tipo de evento de transacción.
     * @return Esta instancia.
     */
    private boolean isValidEventType(TransactionEventEnum eventType) {
        return eventType != null;
    }

    /**
     * Establece el tipo de evento de transacción.
     *
     * @return Esta instancia.
     */
    @Nullable
    public MeterValue[] getMeterValue() {
        return meterValue;
    }

    /**
     * Establece el tipo de evento de transacción.
     *
     * @param meterValue Tipo de evento de transacción.
     */
    public void setMeterValue(@Nullable MeterValue[] meterValue) {
        if (!isValidMeterValue(meterValue)) {
            throw new PropertyConstraintException(meterValue, "meterValue is invalid");
        }
        this.meterValue = meterValue;
    }

    /**
     * Establece el tipo de evento de transacción.
     *
     * @param meterValue Tipo de evento de transacción.
     * @return Esta instancia.
     */
    private boolean isValidMeterValue(@Nullable MeterValue[] meterValue) {
        return meterValue == null
                || (meterValue.length >= 1 && Arrays.stream(meterValue).allMatch(item -> item.validate()));
    }

    /**
     * Establece el tipo de evento de transacción.
     *
     * @return Esta instancia.
     */
    public TransactionEventRequest withMeterValue(@Nullable MeterValue[] meterValue) {
        setMeterValue(meterValue);
        return this;
    }

    /**
     * Obtiene la marca de tiempo del evento.
     *
     * @return Marca de tiempo del evento.
     */
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @param timestamp Marca de tiempo del evento.
     */
    public void setTimestamp(ZonedDateTime timestamp) {
        if (!isValidTimestamp(timestamp)) {
            throw new PropertyConstraintException(timestamp, "timestamp is invalid");
        }
        this.timestamp = timestamp;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @param timestamp Marca de tiempo del evento.
     * @return Esta instancia.
     */
    private boolean isValidTimestamp(ZonedDateTime timestamp) {
        return timestamp != null;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @return Esta instancia.
     */
    public TriggerReasonEnum getTriggerReason() {
        return triggerReason;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @param triggerReason Marca de tiempo del evento.
     */
    public void setTriggerReason(TriggerReasonEnum triggerReason) {
        if (!isValidTriggerReason(triggerReason)) {
            throw new PropertyConstraintException(triggerReason, "triggerReason is invalid");
        }
        this.triggerReason = triggerReason;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @param triggerReason Marca de tiempo del evento.
     * @return Esta instancia.
     */
    private boolean isValidTriggerReason(TriggerReasonEnum triggerReason) {
        return triggerReason != null;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @return Esta instancia.
     */
    public Integer getSeqNo() {
        return seqNo;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @param seqNo Marca de tiempo del evento.
     */
    public void setSeqNo(Integer seqNo) {
        if (!isValidSeqNo(seqNo)) {
            throw new PropertyConstraintException(seqNo, "seqNo is invalid");
        }
        this.seqNo = seqNo;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @param seqNo Marca de tiempo del evento.
     * @return Esta instancia.
     */
    private boolean isValidSeqNo(Integer seqNo) {
        return seqNo != null;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @return Esta instancia.
     */
    public Boolean getOffline() {
        return offline != null ? offline : false;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @param offline Marca de tiempo del evento.
     */
    public void setOffline(@Nullable Boolean offline) {
        this.offline = offline;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @param offline Marca de tiempo del evento.
     * @return Esta instancia.
     */
    public TransactionEventRequest withOffline(@Nullable Boolean offline) {
        setOffline(offline);
        return this;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @return Esta instancia.
     */
    @Nullable
    public Integer getNumberOfPhasesUsed() {
        return numberOfPhasesUsed;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @param numberOfPhasesUsed Marca de tiempo del evento.
     */
    public void setNumberOfPhasesUsed(@Nullable Integer numberOfPhasesUsed) {
        this.numberOfPhasesUsed = numberOfPhasesUsed;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @param numberOfPhasesUsed Marca de tiempo del evento.
     * @return Esta instancia.
     */
    public TransactionEventRequest withNumberOfPhasesUsed(@Nullable Integer numberOfPhasesUsed) {
        setNumberOfPhasesUsed(numberOfPhasesUsed);
        return this;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @return Esta instancia.
     */
    @Nullable
    public Integer getCableMaxCurrent() {
        return cableMaxCurrent;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @param cableMaxCurrent Marca de tiempo del evento.
     */
    public void setCableMaxCurrent(@Nullable Integer cableMaxCurrent) {
        this.cableMaxCurrent = cableMaxCurrent;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @param cableMaxCurrent Marca de tiempo del evento.
     * @return Esta instancia.
     */
    public TransactionEventRequest withCableMaxCurrent(@Nullable Integer cableMaxCurrent) {
        setCableMaxCurrent(cableMaxCurrent);
        return this;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @return Esta instancia.
     */
    @Nullable
    public Integer getReservationId() {
        return reservationId;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @param reservationId Marca de tiempo del evento.
     */
    public void setReservationId(@Nullable Integer reservationId) {
        this.reservationId = reservationId;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @param reservationId Marca de tiempo del evento.
     * @return Esta instancia.
     */
    public TransactionEventRequest withReservationId(@Nullable Integer reservationId) {
        setReservationId(reservationId);
        return this;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @return Esta instancia.
     */
    public Transaction getTransactionInfo() {
        return transactionInfo;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @param transactionInfo Marca de tiempo del evento.
     */
    public void setTransactionInfo(Transaction transactionInfo) {
        if (!isValidTransactionInfo(transactionInfo)) {
            throw new PropertyConstraintException(transactionInfo, "transactionInfo is invalid");
        }
        this.transactionInfo = transactionInfo;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @param transactionInfo Marca de tiempo del evento.
     * @return Esta instancia.
     */
    private boolean isValidTransactionInfo(Transaction transactionInfo) {
        return transactionInfo != null && transactionInfo.validate();
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @return Esta instancia.
     */
    @Nullable
    public EVSE getEvse() {
        return evse;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @param evse Marca de tiempo del evento.
     */
    public void setEvse(@Nullable EVSE evse) {
        if (!isValidEvse(evse)) {
            throw new PropertyConstraintException(evse, "evse is invalid");
        }
        this.evse = evse;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @param evse Marca de tiempo del evento.
     * @return Esta instancia.
     */
    private boolean isValidEvse(@Nullable EVSE evse) {
        return evse == null || evse.validate();
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @return Esta instancia.
     */
    public TransactionEventRequest withEvse(@Nullable EVSE evse) {
        setEvse(evse);
        return this;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @return Esta instancia.
     */
    @Nullable
    public IdToken getIdToken() {
        return idToken;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @param idToken Marca de tiempo del evento.
     */
    public void setIdToken(@Nullable IdToken idToken) {
        if (!isValidIdToken(idToken)) {
            throw new PropertyConstraintException(idToken, "idToken is invalid");
        }
        this.idToken = idToken;
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @param idToken Marca de tiempo del evento.
     * @return Esta instancia.
     */
    private boolean isValidIdToken(@Nullable IdToken idToken) {
        return idToken == null || idToken.validate();
    }

    /**
     * Establece la marca de tiempo del evento.
     *
     * @return Esta instancia.
     */
    public TransactionEventRequest withIdToken(@Nullable IdToken idToken) {
        setIdToken(idToken);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidEventType(eventType)
                && isValidMeterValue(meterValue)
                && isValidTimestamp(timestamp)
                && isValidTriggerReason(triggerReason)
                && isValidSeqNo(seqNo)
                && isValidTransactionInfo(transactionInfo)
                && isValidEvse(evse)
                && isValidIdToken(idToken);
    }

    @Override
    public boolean transactionRelated() {
        return true;
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TransactionEventRequest that = (TransactionEventRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(eventType, that.eventType)
                && Arrays.equals(meterValue, that.meterValue)
                && Objects.equals(timestamp, that.timestamp)
                && Objects.equals(triggerReason, that.triggerReason)
                && Objects.equals(seqNo, that.seqNo)
                && Objects.equals(offline, that.offline)
                && Objects.equals(numberOfPhasesUsed, that.numberOfPhasesUsed)
                && Objects.equals(cableMaxCurrent, that.cableMaxCurrent)
                && Objects.equals(reservationId, that.reservationId)
                && Objects.equals(transactionInfo, that.transactionInfo)
                && Objects.equals(evse, that.evse)
                && Objects.equals(idToken, that.idToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData,
                eventType,
                Arrays.hashCode(meterValue),
                timestamp,
                triggerReason,
                seqNo,
                offline,
                numberOfPhasesUsed,
                cableMaxCurrent,
                reservationId,
                transactionInfo,
                evse,
                idToken);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("eventType", eventType)
                .add("meterValue", meterValue)
                .add("timestamp", timestamp)
                .add("triggerReason", triggerReason)
                .add("seqNo", seqNo)
                .add("offline", offline)
                .add("numberOfPhasesUsed", numberOfPhasesUsed)
                .add("cableMaxCurrent", cableMaxCurrent)
                .add("reservationId", reservationId)
                .add("transactionInfo", transactionInfo)
                .add("evse", evse)
                .add("idToken", idToken)
                .add("isValid", validate())
                .toString();
    }
}
