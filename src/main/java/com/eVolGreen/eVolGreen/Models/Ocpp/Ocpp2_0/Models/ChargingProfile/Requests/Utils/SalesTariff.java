package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;

/**
 * Tarifa de Ventas (Sales Tariff)
 *
 * <p>Representa una estructura de tarifas aplicables a sesiones de carga. Basado en los tipos de datos de ISO 15118-2, incluye identificadores únicos, descripciones legibles para humanos, niveles de precios y entradas de tarifas.
 */
public class SalesTariff {

    /** Datos personalizados asociados a la tarifa. */
    @Nullable
    private CustomData customData;

    /**
     * Identificador único de la tarifa de ventas (SalesTariff).
     *
     * <p>Se utiliza para identificar una tarifa específica en el contexto de una sesión de carga. Este identificador debe ser único durante toda la sesión.
     */
    private Integer id;

    /**
     * Descripción de la tarifa de ventas.
     *
     * <p>Texto legible que describe brevemente la tarifa de ventas. Es útil para mostrar en interfaces de usuario (HMI).
     */
    @Nullable private String salesTariffDescription;

    /**
     * Número total de niveles de precios utilizados.
     *
     * <p>Indica la cantidad de niveles de precios distintos que están presentes en todos los elementos de tarifas proporcionados.
     */
    @Nullable private Integer numEPriceLevels;

    /**
     * Entradas de la tarifa de ventas.
     *
     * <p>Una lista que define las diferentes tarifas aplicables durante los períodos especificados.
     */
    private SalesTariffEntry[] salesTariffEntry;

    /**
     * Constructor de la clase `SalesTariff`.
     *
     * @param id Identificador único de la tarifa.
     * @param salesTariffEntry Lista de entradas de tarifas asociadas a esta tarifa.
     */
    public SalesTariff(Integer id, SalesTariffEntry[] salesTariffEntry) {
        setId(id);
        setSalesTariffEntry(salesTariffEntry);
    }

    /**
     * Obtiene los datos personalizados asociados a la tarifa.
     *
     * @return Datos personalizados (pueden ser nulos).
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados asociados a la tarifa.
     *
     * @param customData Datos personalizados (pueden ser nulos).
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Obtiene el identificador único de la tarifa.
     *
     * @return Identificador único de la tarifa.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador único de la tarifa.
     *
     * @param id Identificador único de la tarifa.
     */
    public void setId(Integer id) {
        if (!isValidId(id)) {
            throw new PropertyConstraintException(id, "El ID no puede ser nulo.");
        }
        this.id = id;
    }

    /**
     * Valida el identificador único de la tarifa.
     *
     * @param id Identificador único de la tarifa.
     * @return `true` si el identificador es válido, `false` en caso contrario.
     */
    private boolean isValidId(Integer id) {
        return id != null;
    }

    /**
     * Obtiene la descripción legible de la tarifa.
     *
     * @return Descripción de la tarifa (puede ser nulo).
     */
    @Nullable
    public String getSalesTariffDescription() {
        return salesTariffDescription;
    }

    /**
     * Establece la descripción legible de la tarifa.
     *
     * @param salesTariffDescription Descripción de la tarifa (máximo 32 caracteres).
     */
    public void setSalesTariffDescription(@Nullable String salesTariffDescription) {
        if (!isValidSalesTariffDescription(salesTariffDescription)) {
            throw new PropertyConstraintException(
                    salesTariffDescription, "La descripción excede el límite de 32 caracteres.");
        }
        this.salesTariffDescription = salesTariffDescription;
    }

    /**
     * Valida la descripción legible de la tarifa.
     *
     * @param salesTariffDescription Descripción de la tarifa.
     * @return `true` si la descripción es válida, `false` en caso contrario.
     */
    private boolean isValidSalesTariffDescription(@Nullable String salesTariffDescription) {
        return salesTariffDescription == null || salesTariffDescription.length() <= 32;
    }

    /**
     * Agrega una descripción legible de la tarifa.
     *
     * @param salesTariffDescription Descripción de la tarifa.
     * @return La instancia actual.
     */
    public SalesTariff withSalesTariffDescription(@Nullable String salesTariffDescription) {
        setSalesTariffDescription(salesTariffDescription);
        return this;
    }

    /**
     * Obtiene el número total de niveles de precios distintos.
     *
     * @return Número total de niveles de precios (puede ser nulo).
     */
    @Nullable
    public Integer getNumEPriceLevels() {
        return numEPriceLevels;
    }

    /**
     * Establece el número total de niveles de precios distintos.
     *
     * @param numEPriceLevels Número total de niveles de precios.
     */
    public void setNumEPriceLevels(@Nullable Integer numEPriceLevels) {
        this.numEPriceLevels = numEPriceLevels;
    }

    /**
     * Agrega el número total de niveles de precios distintos.
     *
     * @param numEPriceLevels Número total de niveles de precios.
     * @return La instancia actual.
     */
    public SalesTariff withNumEPriceLevels(@Nullable Integer numEPriceLevels) {
        setNumEPriceLevels(numEPriceLevels);
        return this;
    }

    /**
     * Obtiene las entradas asociadas a esta tarifa.
     *
     * @return Lista de entradas de la tarifa.
     */
    public SalesTariffEntry[] getSalesTariffEntry() {
        return salesTariffEntry;
    }

    /**
     * Establece las entradas asociadas a esta tarifa.
     *
     * @param salesTariffEntry Lista de entradas de la tarifa (mínimo 1, máximo 1024).
     */
    public void setSalesTariffEntry(SalesTariffEntry[] salesTariffEntry) {
        if (!isValidSalesTariffEntry(salesTariffEntry)) {
            throw new PropertyConstraintException(salesTariffEntry, "salesTariffEntry is invalid");
        }
        this.salesTariffEntry = salesTariffEntry;
    }

    /**
     * Valida las entradas de la tarifa.
     *
     * @param salesTariffEntry Lista de entradas de la tarifa.
     * @return `true` si las entradas son válidas, `false` en caso contrario.
     */
    private boolean isValidSalesTariffEntry(SalesTariffEntry[] salesTariffEntry) {
        return salesTariffEntry != null
                && salesTariffEntry.length >= 1
                && salesTariffEntry.length <= 1024
                && Arrays.stream(salesTariffEntry).allMatch(item -> item.validate());
    }

    /**
     * Valida las propiedades obligatorias de la clase.
     *
     * @return `true` si las propiedades son válidas, `false` en caso contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidId(id)
                && isValidSalesTariffDescription(salesTariffDescription)
                && isValidSalesTariffEntry(salesTariffEntry);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SalesTariff that = (SalesTariff) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(id, that.id)
                && Objects.equals(salesTariffDescription, that.salesTariffDescription)
                && Objects.equals(numEPriceLevels, that.numEPriceLevels)
                && Arrays.equals(salesTariffEntry, that.salesTariffEntry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData, id, salesTariffDescription, numEPriceLevels, Arrays.hashCode(salesTariffEntry));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("id", id)
                .add("salesTariffDescription", salesTariffDescription)
                .add("numEPriceLevels", numEPriceLevels)
                .add("salesTariffEntry", salesTariffEntry)
                .add("isValid", validate())
                .toString();
    }
}
