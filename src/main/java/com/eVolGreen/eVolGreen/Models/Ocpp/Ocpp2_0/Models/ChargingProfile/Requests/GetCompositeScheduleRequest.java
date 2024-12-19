package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Enums.ChargingRateUnitEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud para obtener un cronograma compuesto en una estación de carga.
 *
 * <p>OCPP 2.0.1 FINAL
 */
public class GetCompositeScheduleRequest extends RequestWithId {
    /** Datos personalizados asociados con la solicitud. */
    @Nullable
    private CustomData customData;

    /** Duración del cronograma solicitado en segundos. */
    private Integer duration;

    /** Unidad de medida preferida para el perfil de carga (potencia o corriente). */
    @Nullable private ChargingRateUnitEnum chargingRateUnit;

    /**
     * Identificador del EVSE (Electric Vehicle Supply Equipment) para el cual se solicita el
     * cronograma. Si el EVSEID es 0, la estación de carga calculará el consumo esperado para la
     * conexión a la red.
     */
    private Integer evseId;

    /**
     * Constructor de la clase GetCompositeScheduleRequest.
     *
     * @param duration Duración del cronograma solicitado en segundos.
     * @param evseId Identificador del EVSE para el cual se solicita el cronograma.
     */
    public GetCompositeScheduleRequest(Integer duration, Integer evseId) {
        setDuration(duration);
        setEvseId(evseId);
    }

    /**
     * Obtiene los datos personalizados asociados con la solicitud.
     *
     * @return Datos personalizados.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados asociados con la solicitud.
     *
     * @param customData Datos personalizados.
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
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Asigna datos personalizados a la solicitud y devuelve la instancia actual.
     *
     * @param customData Datos personalizados.
     * @return La instancia actual de la solicitud.
     */
    public GetCompositeScheduleRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la duración del cronograma solicitado en segundos.
     *
     * @return Duración del cronograma en segundos.
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Establece la duración del cronograma solicitado en segundos.
     *
     * @param duration Duración en segundos.
     */
    public void setDuration(Integer duration) {
        if (!isValidDuration(duration)) {
            throw new PropertyConstraintException(duration, "duration es inválido");
        }
        this.duration = duration;
    }

    /**
     * Verifica si la duración proporcionada es válida.
     *
     * @param duration Duración a validar.
     * @return {@code true} si la duración es válida, {@code false} en caso contrario.
     */
    private boolean isValidDuration(Integer duration) {
        return duration != null;
    }

    /**
     * Obtiene la unidad de medida preferida para el perfil de carga (potencia o corriente).
     *
     * @return Unidad de medida preferida.
     */
    @Nullable
    public ChargingRateUnitEnum getChargingRateUnit() {
        return chargingRateUnit;
    }

    /**
     * Establece la unidad de medida preferida para el perfil de carga.
     *
     * @param chargingRateUnit Unidad de medida preferida.
     */
    public void setChargingRateUnit(@Nullable ChargingRateUnitEnum chargingRateUnit) {
        this.chargingRateUnit = chargingRateUnit;
    }

    /**
     * Asigna la unidad de medida preferida a la solicitud y devuelve la instancia actual.
     *
     * @param chargingRateUnit Unidad de medida preferida.
     * @return La instancia actual de la solicitud.
     */
    public GetCompositeScheduleRequest withChargingRateUnit(
            @Nullable ChargingRateUnitEnum chargingRateUnit) {
        setChargingRateUnit(chargingRateUnit);
        return this;
    }

    /**
     * Obtiene el identificador del EVSE para el cual se solicita el cronograma.
     *
     * @return Identificador del EVSE.
     */
    public Integer getEvseId() {
        return evseId;
    }

    /**
     * Establece el identificador del EVSE para el cual se solicita el cronograma.
     *
     * @param evseId Identificador del EVSE.
     */
    public void setEvseId(Integer evseId) {
        if (!isValidEvseId(evseId)) {
            throw new PropertyConstraintException(evseId, "evseId es inválido");
        }
        this.evseId = evseId;
    }

    /**
     * Verifica si el identificador del EVSE proporcionado es válido.
     *
     * @param evseId Identificador del EVSE a validar.
     * @return {@code true} si el EVSEID es válido, {@code false} en caso contrario.
     */
    private boolean isValidEvseId(Integer evseId) {
        return evseId != null;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidDuration(duration) && isValidEvseId(evseId);
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
        GetCompositeScheduleRequest that = (GetCompositeScheduleRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(duration, that.duration)
                && Objects.equals(chargingRateUnit, that.chargingRateUnit)
                && Objects.equals(evseId, that.evseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, duration, chargingRateUnit, evseId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("duration", duration)
                .add("chargingRateUnit", chargingRateUnit)
                .add("evseId", evseId)
                .add("isValid", validate())
                .toString();
    }
}
