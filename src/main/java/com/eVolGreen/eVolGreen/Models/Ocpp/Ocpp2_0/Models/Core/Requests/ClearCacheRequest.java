package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * ClearCacheRequest
 *
 * Representa una solicitud para limpiar la memoria caché de la estación de carga.
 * <p>OCPP 2.0.1 FINAL</p>
 */
public class ClearCacheRequest extends RequestWithId {

    /** Datos personalizados que permiten información adicional en la solicitud. */
    @Nullable
    private CustomData customData;

    /** Constructor por defecto de la clase ClearCacheRequest. */
    public ClearCacheRequest() {}

    /**
     * Obtiene los datos personalizados.
     *
     * @return Objeto {@link CustomData} que contiene los datos personalizados, o {@code null} si no se especifican.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Objeto {@link CustomData} que contiene los datos personalizados.
     * @throws PropertyConstraintException si los datos personalizados no son válidos.
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
     * @param customData Objeto {@link CustomData} que contiene los datos personalizados.
     * @return {@code true} si los datos son válidos, {@code false} de lo contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Asigna datos personalizados de manera fluida.
     *
     * @param customData Objeto {@link CustomData} que contiene los datos personalizados.
     * @return La instancia actual de {@link ClearCacheRequest}.
     */
    public ClearCacheRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Valida la solicitud verificando si los datos personalizados son válidos.
     *
     * @return {@code true} si la solicitud es válida, {@code false} de lo contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData);
    }

    /**
     * Indica si la solicitud está relacionada con una transacción.
     *
     * @return {@code false}, ya que esta solicitud no está relacionada con transacciones.
     */
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClearCacheRequest that = (ClearCacheRequest) o;
        return Objects.equals(customData, that.customData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData);
    }

    /**
     * Representación en cadena de la solicitud ClearCacheRequest.
     *
     * @return Una cadena con los valores de los atributos de la clase.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("isValid", validate())
                .toString();
    }
}
