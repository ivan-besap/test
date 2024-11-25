package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils.EventData;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * Notificación de eventos.
 *
 * <p>Este mensaje es enviado por la estación de carga para notificar eventos relacionados con sus componentes o variables.
 */
public class NotifyEventRequest extends RequestWithId {
    /** Datos personalizados adicionales. */
    @Nullable private CustomData customData;

    /** Marca de tiempo en la que se generó este mensaje en la estación de carga. */
    private ZonedDateTime generatedAt;

    /**
     * Indicador "continuará". Indica si otra parte del informe sigue en un próximo mensaje
     * NotifyEventRequest. Valor predeterminado cuando se omite: false.
     */
    @Nullable private Boolean tbc;

    /** Número de secuencia de este mensaje. El primer mensaje comienza en 0. */
    private Integer seqNo;

    /** Clase para reportar una notificación de evento para un componente o variable. */
    private EventData[] eventData;

    /**
     * Constructor para la clase NotifyEventRequest.
     *
     * @param generatedAt Marca de tiempo en la que se generó este mensaje en la estación de carga.
     * @param seqNo Número de secuencia de este mensaje. El primer mensaje comienza en 0.
     * @param eventData Clase para reportar una notificación de evento para un componente o variable.
     */
    public NotifyEventRequest(ZonedDateTime generatedAt, Integer seqNo, EventData[] eventData) {
        setGeneratedAt(generatedAt);
        setSeqNo(seqNo);
        setEventData(eventData);
    }

    /**
     * Obtiene los datos personalizados adicionales.
     *
     * @return Datos personalizados adicionales.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados son inválidos.");
        }
        this.customData = customData;
    }

    /**
     * Valida los datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales a validar.
     * @return {@code true} si son válidos, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega los datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     * @return Esta misma instancia para facilitar la concatenación de métodos.
     */
    public NotifyEventRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la marca de tiempo en la que se generó este mensaje en la estación de carga.
     *
     * @return Marca de tiempo en la que se generó este mensaje.
     */
    public ZonedDateTime getGeneratedAt() {
        return generatedAt;
    }

    /**
     * Establece la marca de tiempo en la que se generó este mensaje en la estación de carga.
     *
     * @param generatedAt Marca de tiempo en la que se generó este mensaje.
     */
    public void setGeneratedAt(ZonedDateTime generatedAt) {
        if (!isValidGeneratedAt(generatedAt)) {
            throw new PropertyConstraintException(generatedAt, "La marca de tiempo proporcionada es inválida.");
        }
        this.generatedAt = generatedAt;
    }

    /**
     * Valida la marca de tiempo en la que se generó este mensaje en la estación de carga.
     *
     * @param generatedAt Marca de tiempo en la que se generó este mensaje.
     * @return {@code true} si es válida, de lo contrario {@code false}.
     */
    private boolean isValidGeneratedAt(ZonedDateTime generatedAt) {
        return generatedAt != null;
    }

    /**
     * Obtiene el indicador "continuará".
     *
     * @return Indicador "continuará". Valor predeterminado: false.
     */
    public Boolean getTbc() {
        return tbc != null ? tbc : false;
    }

    /**
     * Establece el indicador "continuará".
     *
     * @param tbc Indicador "continuará".
     */
    public void setTbc(@Nullable Boolean tbc) {
        this.tbc = tbc;
    }

    /**
     * Agrega el indicador "continuará".
     *
     * @param tbc Indicador "continuará".
     * @return Esta misma instancia para facilitar la concatenación de métodos.
     */
    public NotifyEventRequest withTbc(@Nullable Boolean tbc) {
        setTbc(tbc);
        return this;
    }

    /**
     * Obtiene el número de secuencia de este mensaje.
     *
     * @return Número de secuencia de este mensaje.
     */
    public Integer getSeqNo() {
        return seqNo;
    }

    /**
     * Establece el número de secuencia de este mensaje.
     *
     * @param seqNo Número de secuencia de este mensaje.
     */
    public void setSeqNo(Integer seqNo) {
        if (!isValidSeqNo(seqNo)) {
            throw new PropertyConstraintException(seqNo, "El número de secuencia proporcionado es inválido.");
        }
        this.seqNo = seqNo;
    }

    /**
     * Valida el número de secuencia de este mensaje.
     *
     * @param seqNo Número de secuencia de este mensaje.
     * @return {@code true} si es válido, de lo contrario {@code false}.
     */
    private boolean isValidSeqNo(Integer seqNo) {
        return seqNo != null;
    }

    /**
     * Obtiene los datos del evento.
     *
     * @return Datos del evento.
     */
    public EventData[] getEventData() {
        return eventData;
    }

    /**
     * Establece los datos del evento.
     *
     * @param eventData Datos del evento.
     */
    public void setEventData(EventData[] eventData) {
        if (!isValidEventData(eventData)) {
            throw new PropertyConstraintException(eventData, "Los datos del evento son inválidos.");
        }
        this.eventData = eventData;
    }

    /**
     * Valida los datos del evento.
     *
     * @param eventData Datos del evento a validar.
     * @return {@code true} si son válidos, de lo contrario {@code false}.
     */
    private boolean isValidEventData(EventData[] eventData) {
        return eventData != null
                && eventData.length >= 1
                && Arrays.stream(eventData).allMatch(item -> item.validate());
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidGeneratedAt(generatedAt)
                && isValidSeqNo(seqNo)
                && isValidEventData(eventData);
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NotifyEventRequest that = (NotifyEventRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(generatedAt, that.generatedAt)
                && Objects.equals(tbc, that.tbc)
                && Objects.equals(seqNo, that.seqNo)
                && Arrays.equals(eventData, that.eventData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, generatedAt, tbc, seqNo, Arrays.hashCode(eventData));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("generatedAt", generatedAt)
                .add("tbc", tbc)
                .add("seqNo", seqNo)
                .add("eventData", eventData)
                .add("isValid", validate())
                .toString();
    }
}
