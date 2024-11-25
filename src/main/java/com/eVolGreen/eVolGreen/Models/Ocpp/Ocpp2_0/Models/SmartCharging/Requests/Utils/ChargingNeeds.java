package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.SmartCharging.Requests.Enums.EnergyTransferModeEnum;
import org.springframework.lang.Nullable;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Necesidades de carga
 *
 * <p>Representa los parámetros de carga requeridos por un vehículo eléctrico, incluyendo detalles
 * sobre los modos de transferencia de energía y tiempos estimados de salida.
 */
public class ChargingNeeds {
    /** Datos personalizados adicionales. */
    @Nullable
    private CustomData customData;

    /** Parámetros de carga en corriente alterna (AC) del vehículo eléctrico. */
    @Nullable
    private ACChargingParameters acChargingParameters;

    /** Parámetros de carga en corriente continua (DC) del vehículo eléctrico. */
    @Nullable
    private DCChargingParameters dcChargingParameters;

    /** Modo de transferencia de energía solicitado por el vehículo eléctrico. */
    private EnergyTransferModeEnum requestedEnergyTransfer;

    /** Tiempo estimado de salida del vehículo eléctrico. */
    @Nullable private ZonedDateTime departureTime;

    /**
     * Constructor para la clase ChargingNeeds.
     *
     * @param requestedEnergyTransfer Modo de transferencia de energía solicitado por el vehículo.
     */
    public ChargingNeeds(EnergyTransferModeEnum requestedEnergyTransfer) {
        setRequestedEnergyTransfer(requestedEnergyTransfer);
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
            throw new PropertyConstraintException(customData, "Datos personalizados inválidos");
        }
        this.customData = customData;
    }

    /**
     * Valida si los datos personalizados son válidos.
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
     * @return Esta instancia de la clase.
     */
    public ChargingNeeds withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene los parámetros de carga en corriente alterna (AC).
     *
     * @return Parámetros de carga AC.
     */
    @Nullable
    public ACChargingParameters getAcChargingParameters() {
        return acChargingParameters;
    }

    /**
     * Establece los parámetros de carga en corriente alterna (AC).
     *
     * @param acChargingParameters Parámetros de carga AC.
     */
    public void setAcChargingParameters(@Nullable ACChargingParameters acChargingParameters) {
        if (!isValidAcChargingParameters(acChargingParameters)) {
            throw new PropertyConstraintException(
                    acChargingParameters, "Parámetros de carga AC inválidos");
        }
        this.acChargingParameters = acChargingParameters;
    }

    /**
     * Valida si los parámetros de carga en corriente alterna (AC) son válidos.
     *
     * @param acChargingParameters Parámetros de carga AC a validar.
     * @return {@code true} si son válidos, {@code false} en caso contrario.
     */
    private boolean isValidAcChargingParameters(@Nullable ACChargingParameters acChargingParameters) {
        return acChargingParameters == null || acChargingParameters.validate();
    }

    /**
     * Agrega los parámetros de carga en corriente alterna (AC).
     *
     * @param acChargingParameters Parámetros de carga AC.
     * @return Esta instancia de la clase.
     */
    public ChargingNeeds withAcChargingParameters(
            @Nullable ACChargingParameters acChargingParameters) {
        setAcChargingParameters(acChargingParameters);
        return this;
    }

    /**
     * Obtiene los parámetros de carga en corriente continua (DC).
     *
     * @return Parámetros de carga DC.
     */
    @Nullable
    public DCChargingParameters getDcChargingParameters() {
        return dcChargingParameters;
    }

    /**
     * Establece los parámetros de carga en corriente continua (DC).
     *
     * @param dcChargingParameters Parámetros de carga DC.
     */
    public void setDcChargingParameters(@Nullable DCChargingParameters dcChargingParameters) {
        if (!isValidDcChargingParameters(dcChargingParameters)) {
            throw new PropertyConstraintException(
                    dcChargingParameters, "Parámetros de carga DC inválidos");
        }
        this.dcChargingParameters = dcChargingParameters;
    }

    /**
     * Valida si los parámetros de carga en corriente continua (DC) son válidos.
     *
     * @param dcChargingParameters Parámetros de carga DC a validar.
     * @return {@code true} si son válidos, {@code false} en caso contrario.
     */
    private boolean isValidDcChargingParameters(@Nullable DCChargingParameters dcChargingParameters) {
        return dcChargingParameters == null || dcChargingParameters.validate();
    }

    /**
     * Agrega los parámetros de carga en corriente continua (DC).
     *
     * @param dcChargingParameters Parámetros de carga DC.
     * @return Esta instancia de la clase.
     */
    public ChargingNeeds withDcChargingParameters(
            @Nullable DCChargingParameters dcChargingParameters) {
        setDcChargingParameters(dcChargingParameters);
        return this;
    }

    /**
     * Obtiene el modo de transferencia de energía solicitado por el vehículo eléctrico.
     *
     * @return Modo de transferencia de energía solicitado.
     */
    public EnergyTransferModeEnum getRequestedEnergyTransfer() {
        return requestedEnergyTransfer;
    }

    /**
     * Establece el modo de transferencia de energía solicitado por el vehículo eléctrico.
     *
     * @param requestedEnergyTransfer Modo de transferencia de energía solicitado.
     */
    public void setRequestedEnergyTransfer(EnergyTransferModeEnum requestedEnergyTransfer) {
        if (!isValidRequestedEnergyTransfer(requestedEnergyTransfer)) {
            throw new PropertyConstraintException(
                    requestedEnergyTransfer, "Modo de transferencia de energía inválido");
        }
        this.requestedEnergyTransfer = requestedEnergyTransfer;
    }

    /**
     * Valida si el modo de transferencia de energía solicitado es válido.
     *
     * @param requestedEnergyTransfer Modo de transferencia a validar.
     * @return {@code true} si es válido, {@code false} en caso contrario.
     */
    private boolean isValidRequestedEnergyTransfer(EnergyTransferModeEnum requestedEnergyTransfer) {
        return requestedEnergyTransfer != null;
    }

    /**
     * Obtiene el tiempo estimado de salida del vehículo eléctrico.
     *
     * @return Tiempo estimado de salida.
     */
    @Nullable
    public ZonedDateTime getDepartureTime() {
        return departureTime;
    }

    /**
     * Establece el tiempo estimado de salida del vehículo eléctrico.
     *
     * @param departureTime Tiempo estimado de salida.
     */
    public void setDepartureTime(@Nullable ZonedDateTime departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * Agrega el tiempo estimado de salida del vehículo eléctrico.
     *
     * @param departureTime Tiempo estimado de salida.
     * @return Esta instancia de la clase.
     */
    public ChargingNeeds withDepartureTime(@Nullable ZonedDateTime departureTime) {
        setDepartureTime(departureTime);
        return this;
    }

    public boolean validate() {
        return isValidCustomData(customData)
                && isValidAcChargingParameters(acChargingParameters)
                && isValidDcChargingParameters(dcChargingParameters)
                && isValidRequestedEnergyTransfer(requestedEnergyTransfer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChargingNeeds that = (ChargingNeeds) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(acChargingParameters, that.acChargingParameters)
                && Objects.equals(dcChargingParameters, that.dcChargingParameters)
                && Objects.equals(requestedEnergyTransfer, that.requestedEnergyTransfer)
                && Objects.equals(departureTime, that.departureTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData,
                acChargingParameters,
                dcChargingParameters,
                requestedEnergyTransfer,
                departureTime);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("acChargingParameters", acChargingParameters)
                .add("dcChargingParameters", dcChargingParameters)
                .add("requestedEnergyTransfer", requestedEnergyTransfer)
                .add("departureTime", departureTime)
                .add("isValid", validate())
                .toString();
    }
}
