package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Enums.ChargingRateUnitEnum;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * Estructura del horario de carga.
 *
 * <p>Define los detalles de un horario de carga, incluyendo períodos, tasa de carga, duración,
 * y datos adicionales. Este horario puede ser utilizado en configuraciones como
 * `GetCompositeSchedule.conf` y `ChargingProfile`.
 */
public class ChargingSchedule {

    /** Datos personalizados opcionales. */
    @Nullable
    private CustomData customData;

    /** Identificador único del horario de carga. */
    private Integer id;

    /**
     * Inicio del horario de carga.
     *
     * <p>Define el momento en que comienza el horario de carga absoluto. Si no está definido,
     * el horario será relativo al inicio de la carga.
     */
    @Nullable
    private ZonedDateTime startSchedule;

    /**
     * Duración del horario de carga en segundos.
     *
     * <p>Si no se especifica, el último período del horario continuará de manera indefinida
     * o hasta que finalice la transacción (si el propósito del perfil es `TxProfile`).
     */
    @Nullable
    private Integer duration;

    /**
     * Unidad de medida para la tasa de carga.
     *
     * <p>Define si los límites de carga se expresan en kW o amperios.
     */
    private ChargingRateUnitEnum chargingRateUnit;

    /**
     * Períodos del horario de carga.
     *
     * <p>Cada período dentro del horario define un intervalo de tiempo con sus respectivas tasas
     * de carga.
     */
    private ChargingSchedulePeriod[] chargingSchedulePeriod;

    /**
     * Tasa mínima de carga soportada por el vehículo eléctrico.
     *
     * <p>Esta tasa se utiliza para optimizar la asignación de energía, especialmente en
     * algoritmos de carga inteligente, y acepta valores con hasta un dígito decimal (por ejemplo, 8.1).
     */
    @Nullable private Double minChargingRate;

    /**
     * Tarifas de venta asociadas al horario.
     *
     * <p>Basado en el estándar ISO 15118-2. Incluye información sobre los costos relacionados
     * con el horario de carga.
     */
    @Nullable private SalesTariff salesTariff;

    /**
     * Constructor de la clase `ChargingSchedule`.
     *
     * @param id Identificador único del horario de carga.
     * @param chargingRateUnit Unidad de medida de la tasa de carga.
     * @param chargingSchedulePeriod Períodos del horario de carga.
     */
    public ChargingSchedule(
            Integer id,
            ChargingRateUnitEnum chargingRateUnit,
            ChargingSchedulePeriod[] chargingSchedulePeriod) {
        setId(id);
        setChargingRateUnit(chargingRateUnit);
        setChargingSchedulePeriod(chargingSchedulePeriod);
    }

    /**
     * Obtiene los datos personalizados asociados al horario.
     *
     * @return Datos personalizados (pueden ser nulos).
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados asociados al horario.
     *
     * @param customData Datos personalizados (pueden ser nulos).
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData no es válido");
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
     * Agrega datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     * @return Esta misma instancia para facilitar la concatenación de métodos.
     */
    public ChargingSchedule withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el identificador único del horario de carga.
     *
     * @return Identificador del horario.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único del horario de carga.
     *
     * @param id Identificador del horario.
     */
    public void setId(Integer id) {
        if (!isValidId(id)) {
            throw new PropertyConstraintException(id, "id no puede ser nulo");
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
     * Obtiene el momento en que comienza el horario de carga.
     *
     * @return Inicio del horario (puede ser nulo si es relativo).
     */
    @Nullable
    public ZonedDateTime getStartSchedule() {
        return startSchedule;
    }

    /**
     * Establece el momento en que comienza el horario de carga.
     *
     * @param startSchedule Inicio del horario.
     */
    public void setStartSchedule(@Nullable ZonedDateTime startSchedule) {
        this.startSchedule = startSchedule;
    }

    /**
     * Agrega el momento en que comienza el horario de carga.
     *
     * @param startSchedule Inicio del horario.
     * @return Esta misma instancia para facilitar la concatenación de métodos.
     */
    public ChargingSchedule withStartSchedule(@Nullable ZonedDateTime startSchedule) {
        setStartSchedule(startSchedule);
        return this;
    }

    /**
     * Obtiene la duración total del horario en segundos.
     *
     * @return Duración del horario (puede ser nulo para duración indefinida).
     */
    @Nullable
    public Integer getDuration() {
        return duration;
    }

    /**
     * Establece la duración total del horario en segundos.
     *
     * @param duration Duración del horario.
     */
    public void setDuration(@Nullable Integer duration) {
        this.duration = duration;
    }

    /**
     * Obtiene la unidad de medida de la tasa de carga.
     *
     * @return Unidad de medida de la tasa de carga.
     */
    public ChargingRateUnitEnum getChargingRateUnit() {
        return chargingRateUnit;
    }

    /**
     * Establece la unidad de medida de la tasa de carga.
     *
     * @param chargingRateUnit Unidad de medida de la tasa de carga.
     */
    public void setChargingRateUnit(ChargingRateUnitEnum chargingRateUnit) {
        if (!isValidChargingRateUnit(chargingRateUnit)) {
            throw new PropertyConstraintException(chargingRateUnit, "chargingRateUnit no puede ser nulo");
        }
        this.chargingRateUnit = chargingRateUnit;
    }

    /**
     * Obtiene la tasa mínima de carga soportada por el vehículo eléctrico.
     *
     * @return Tasa mínima de carga soportada.
     */
    private boolean isValidChargingRateUnit(ChargingRateUnitEnum chargingRateUnit) {
        return chargingRateUnit != null;
    }


    /**
     * Obtiene los períodos del horario de carga.
     *
     * @return Períodos del horario de carga.
     */
    public ChargingSchedulePeriod[] getChargingSchedulePeriod() {
        return chargingSchedulePeriod;
    }

    /**
     * Establece los períodos del horario de carga.
     *
     * @param chargingSchedulePeriod Períodos del horario.
     */
    public void setChargingSchedulePeriod(ChargingSchedulePeriod[] chargingSchedulePeriod) {
        if (!isValidChargingSchedulePeriod(chargingSchedulePeriod)) {
            throw new PropertyConstraintException(
                    chargingSchedulePeriod, "chargingSchedulePeriod no puede ser nulo o vacío");
        }
        this.chargingSchedulePeriod = chargingSchedulePeriod;
    }

    /**
     * Agrega los períodos del horario de carga.
     *
     * @param chargingSchedulePeriod Períodos del horario.
     * @return Esta misma instancia para facilitar la concatenación de métodos.
     */
    private boolean isValidChargingSchedulePeriod(ChargingSchedulePeriod[] chargingSchedulePeriod) {
        return chargingSchedulePeriod != null
                && chargingSchedulePeriod.length >= 1
                && chargingSchedulePeriod.length <= 1024
                && Arrays.stream(chargingSchedulePeriod).allMatch(item -> item.validate());
    }

    /**
     * Obtiene las tarifas de venta asociadas al horario.
     *
     * @return Tarifas de venta asociadas.
     */
    @Nullable
    public Double getMinChargingRate() {
        return minChargingRate;
    }

    /**
     * Establece la tasa mínima de carga soportada por el vehículo eléctrico.
     *
     * @param minChargingRate Tasa mínima de carga soportada.
     */
    public void setMinChargingRate(@Nullable Double minChargingRate) {
        this.minChargingRate = minChargingRate;
    }

    /**
     * Agrega la tasa mínima de carga soportada por el vehículo eléctrico.
     *
     * @param minChargingRate Tasa mínima de carga soportada.
     * @return Esta misma instancia para facilitar la concatenación de métodos.
     */
    public ChargingSchedule withMinChargingRate(@Nullable Double minChargingRate) {
        setMinChargingRate(minChargingRate);
        return this;
    }

    /**
     * Obtiene las tarifas de venta asociadas al horario.
     *
     * @return Tarifas de venta asociadas.
     */
    @Nullable
    public SalesTariff getSalesTariff() {
        return salesTariff;
    }

    /**
     * Establece las tarifas de venta asociadas al horario.
     *
     * @param salesTariff Tarifas de venta asociadas.
     */
    public void setSalesTariff(@Nullable SalesTariff salesTariff) {
        if (!isValidSalesTariff(salesTariff)) {
            throw new PropertyConstraintException(salesTariff, "salesTariff is invalid");
        }
        this.salesTariff = salesTariff;
    }

    /**
     * Agrega las tarifas de venta asociadas al horario.
     *
     * @param salesTariff Tarifas de venta asociadas.
     * @return Esta misma instancia para facilitar la concatenación de métodos.
     */
    private boolean isValidSalesTariff(@Nullable SalesTariff salesTariff) {
        return salesTariff == null || salesTariff.validate();
    }

    /**
     * Agrega las tarifas de venta asociadas al horario.
     *
     * @param salesTariff Tarifas de venta asociadas.
     * @return Esta misma instancia para facilitar la concatenación de métodos.
     */
    public ChargingSchedule withSalesTariff(@Nullable SalesTariff salesTariff) {
        setSalesTariff(salesTariff);
        return this;
    }

    public boolean validate() {
        return isValidCustomData(customData)
                && isValidId(id)
                && isValidChargingRateUnit(chargingRateUnit)
                && isValidChargingSchedulePeriod(chargingSchedulePeriod)
                && isValidSalesTariff(salesTariff);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChargingSchedule that = (ChargingSchedule) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(id, that.id)
                && Objects.equals(startSchedule, that.startSchedule)
                && Objects.equals(duration, that.duration)
                && Objects.equals(chargingRateUnit, that.chargingRateUnit)
                && Arrays.equals(chargingSchedulePeriod, that.chargingSchedulePeriod)
                && Objects.equals(minChargingRate, that.minChargingRate)
                && Objects.equals(salesTariff, that.salesTariff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData,
                id,
                startSchedule,
                duration,
                chargingRateUnit,
                Arrays.hashCode(chargingSchedulePeriod),
                minChargingRate,
                salesTariff);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("id", id)
                .add("startSchedule", startSchedule)
                .add("duration", duration)
                .add("chargingRateUnit", chargingRateUnit)
                .add("chargingSchedulePeriod", chargingSchedulePeriod)
                .add("minChargingRate", minChargingRate)
                .add("salesTariff", salesTariff)
                .add("isValid", validate())
                .toString();
    }
}
