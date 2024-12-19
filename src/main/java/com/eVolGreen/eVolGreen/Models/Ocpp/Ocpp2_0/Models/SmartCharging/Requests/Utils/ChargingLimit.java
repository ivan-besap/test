package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Enums.ChargingLimitSourceEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Límite de carga
 *
 * <p>Clase que representa un límite de carga con su origen y la posibilidad de marcarlo como crítico para la red eléctrica.
 */
public class ChargingLimit {
    /** Datos personalizados opcionales */
    @Nullable
    private CustomData customData;

    /**
     * Origen del límite de carga.
     *
     * <p>Indica la fuente del límite de carga.
     */
    private ChargingLimitSourceEnum chargingLimitSource;

    /**
     * Indicador de criticidad para la red.
     *
     * <p>Indica si el límite de carga es crítico para la estabilidad de la red eléctrica.
     */
    @Nullable private Boolean isGridCritical;

    /**
     * Constructor de la clase ChargingLimit
     *
     * @param chargingLimitSource Origen del límite de carga
     */
    public ChargingLimit(ChargingLimitSourceEnum chargingLimitSource) {
        setChargingLimitSource(chargingLimitSource);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Datos personalizados
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados
     * @return {@code true} si son válidos, {@code false} si no
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados.
     *
     * @param customData Datos personalizados
     * @return esta instancia
     */
    public ChargingLimit withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el origen del límite de carga.
     *
     * @return Origen del límite de carga
     */
    public ChargingLimitSourceEnum getChargingLimitSource() {
        return chargingLimitSource;
    }

    /**
     * Establece el origen del límite de carga.
     *
     * @param chargingLimitSource Origen del límite de carga
     */
    public void setChargingLimitSource(ChargingLimitSourceEnum chargingLimitSource) {
        if (!isValidChargingLimitSource(chargingLimitSource)) {
            throw new PropertyConstraintException(chargingLimitSource, "El origen del límite de carga no es válido");
        }
        this.chargingLimitSource = chargingLimitSource;
    }

    /**
     * Verifica si el origen del límite de carga es válido.
     *
     * @param chargingLimitSource Origen del límite de carga
     * @return {@code true} si es válido, {@code false} si no
     */
    private boolean isValidChargingLimitSource(ChargingLimitSourceEnum chargingLimitSource) {
        return chargingLimitSource != null;
    }

    /**
     * Obtiene el indicador de criticidad para la red.
     *
     * @return Indicador de criticidad para la red
     */
    @Nullable
    public Boolean getIsGridCritical() {
        return isGridCritical;
    }

    /**
     * Establece el indicador de criticidad para la red.
     *
     * @param isGridCritical Indicador de criticidad para la red
     */
    public void setIsGridCritical(@Nullable Boolean isGridCritical) {
        this.isGridCritical = isGridCritical;
    }

    /**
     * Agrega el indicador de criticidad para la red.
     *
     * @param isGridCritical Indicador de criticidad para la red
     * @return esta instancia
     */
    public ChargingLimit withIsGridCritical(@Nullable Boolean isGridCritical) {
        setIsGridCritical(isGridCritical);
        return this;
    }

    /**
     * Valida la instancia actual.
     *
     * @return {@code true} si todos los campos son válidos, {@code false} si no
     */
    public boolean validate() {
        return isValidCustomData(customData) && isValidChargingLimitSource(chargingLimitSource);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChargingLimit that = (ChargingLimit) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(chargingLimitSource, that.chargingLimitSource)
                && Objects.equals(isGridCritical, that.isGridCritical);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, chargingLimitSource, isGridCritical);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("chargingLimitSource", chargingLimitSource)
                .add("isGridCritical", isGridCritical)
                .add("isValid", validate())
                .toString();
    }
}
