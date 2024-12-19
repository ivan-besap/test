package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa una estación de carga física donde se puede cargar un vehículo eléctrico (EV).
 *
 * <p>Incluye detalles como el modelo, número de serie, firmware y parámetros para comunicación
 * inalámbrica.
 */
public class ChargingStation {

    /** Datos personalizados asociados con la estación de carga. */
    @Nullable
    private CustomData customData;

    /** Número de serie específico del dispositivo proporcionado por el proveedor. */
    @Nullable private String serialNumber;

    /** Modelo del dispositivo. */
    private String model;

    /**
     * Módulo de comunicación inalámbrica.
     *
     * <p>Parámetros necesarios para iniciar y mantener comunicación inalámbrica con otros
     * dispositivos.
     */
    @Nullable private Modem modem;

    /** Nombre del proveedor del dispositivo (no necesariamente único). */
    private String vendorName;

    /** Versión del firmware de la estación de carga. */
    @Nullable private String firmwareVersion;

    /**
     * Constructor para la clase ChargingStation.
     *
     * @param model Modelo del dispositivo.
     * @param vendorName Nombre del proveedor del dispositivo.
     */
    public ChargingStation(String model, String vendorName) {
        setModel(model);
        setVendorName(vendorName);
    }

    /** @return Datos personalizados asociados con la estación de carga. */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece datos personalizados asociados con la estación de carga.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos");
        }
        this.customData = customData;
    }

    /** Verifica si los datos personalizados son válidos. */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    public ChargingStation withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /** @return Número de serie específico del dispositivo. */
    @Nullable
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Establece el número de serie específico del dispositivo.
     *
     * @param serialNumber Número de serie.
     */
    public void setSerialNumber(@Nullable String serialNumber) {
        if (!isValidSerialNumber(serialNumber)) {
            throw new PropertyConstraintException(serialNumber, "El número de serie no es válido");
        }
        this.serialNumber = serialNumber;
    }

    /** Verifica si el número de serie es válido. */
    private boolean isValidSerialNumber(@Nullable String serialNumber) {
        return serialNumber == null || serialNumber.length() <= 25;
    }

    public ChargingStation withSerialNumber(@Nullable String serialNumber) {
        setSerialNumber(serialNumber);
        return this;
    }

    /** @return Modelo del dispositivo. */
    public String getModel() {
        return model;
    }

    /**
     * Establece el modelo del dispositivo.
     *
     * @param model Modelo del dispositivo.
     */
    public void setModel(String model) {
        if (!isValidModel(model)) {
            throw new PropertyConstraintException(model, "El modelo no es válido");
        }
        this.model = model;
    }

    /** Verifica si el modelo del dispositivo es válido. */
    private boolean isValidModel(String model) {
        return model != null && model.length() <= 20;
    }

    /** @return Módulo de comunicación inalámbrica asociado. */
    @Nullable
    public Modem getModem() {
        return modem;
    }

    /**
     * Establece el módulo de comunicación inalámbrica asociado.
     *
     * @param modem Módulo de comunicación inalámbrica.
     */
    public void setModem(@Nullable Modem modem) {
        if (!isValidModem(modem)) {
            throw new PropertyConstraintException(modem, "El módulo de comunicación no es válido");
        }
        this.modem = modem;
    }

    /** Verifica si el módulo de comunicación inalámbrica es válido. */
    private boolean isValidModem(@Nullable Modem modem) {
        return modem == null || modem.validate();
    }


    public ChargingStation withModem(@Nullable Modem modem) {
        setModem(modem);
        return this;
    }

    /** @return Nombre del proveedor del dispositivo. */
    public String getVendorName() {
        return vendorName;
    }

    /**
     * Establece el nombre del proveedor del dispositivo.
     *
     * @param vendorName Nombre del proveedor.
     */
    public void setVendorName(String vendorName) {
        if (!isValidVendorName(vendorName)) {
            throw new PropertyConstraintException(vendorName, "El nombre del proveedor no es válido");
        }
        this.vendorName = vendorName;
    }

    /** Verifica si el nombre del proveedor es válido. */
    private boolean isValidVendorName(String vendorName) {
        return vendorName != null && vendorName.length() <= 50;
    }

    /** @return Versión del firmware de la estación de carga. */
    @Nullable
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    /**
     * Establece la versión del firmware de la estación de carga.
     *
     * @param firmwareVersion Versión del firmware.
     */
    public void setFirmwareVersion(@Nullable String firmwareVersion) {
        if (!isValidFirmwareVersion(firmwareVersion)) {
            throw new PropertyConstraintException(firmwareVersion, "La versión del firmware no es válida");
        }
        this.firmwareVersion = firmwareVersion;
    }

    /** Verifica si la versión del firmware es válida. */
    private boolean isValidFirmwareVersion(@Nullable String firmwareVersion) {
        return firmwareVersion == null || firmwareVersion.length() <= 50;
    }

    public ChargingStation withFirmwareVersion(@Nullable String firmwareVersion) {
        setFirmwareVersion(firmwareVersion);
        return this;
    }

    /**
     * Valida si todos los datos de la estación de carga son correctos.
     *
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidSerialNumber(serialNumber)
                && isValidModel(model)
                && isValidModem(modem)
                && isValidVendorName(vendorName)
                && isValidFirmwareVersion(firmwareVersion);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChargingStation that = (ChargingStation) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(serialNumber, that.serialNumber)
                && Objects.equals(model, that.model)
                && Objects.equals(modem, that.modem)
                && Objects.equals(vendorName, that.vendorName)
                && Objects.equals(firmwareVersion, that.firmwareVersion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, serialNumber, model, modem, vendorName, firmwareVersion);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("serialNumber", serialNumber)
                .add("model", model)
                .add("modem", modem)
                .add("vendorName", vendorName)
                .add("firmwareVersion", firmwareVersion)
                .add("isValid", validate())
                .toString();
    }
}
