package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta al reporte de perfiles de carga.
 *
 * <p>Este mensaje se utiliza como confirmación para un reporte de perfiles de carga enviado por
 * una estación de carga. Es compatible con el estándar OCPP 2.0.1 FINAL.
 */
public class ReportChargingProfilesResponse extends Confirmation {

    /**
     * Datos personalizados.
     *
     * <p>Información adicional específica del cliente o del fabricante.
     */
    @Nullable
    private CustomData customData;

    /** Constructor para la clase ReportChargingProfilesResponse. */
    public ReportChargingProfilesResponse() {}

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados, o {@code null} si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Datos personalizados que se desean asignar.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos son válidos, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados.
     *
     * @param customData Datos personalizados que se desean agregar.
     * @return Esta instancia de {@link ReportChargingProfilesResponse}.
     */
    public ReportChargingProfilesResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Valida si esta respuesta contiene datos válidos.
     *
     * @return {@code true} si la respuesta es válida, de lo contrario {@code false}.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData);
    }

    /**
     * Verifica la igualdad entre esta instancia y otro objeto.
     *
     * @param o El objeto con el que se comparará.
     * @return {@code true} si ambos objetos son iguales, de lo contrario {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReportChargingProfilesResponse that = (ReportChargingProfilesResponse) o;
        return Objects.equals(customData, that.customData);
    }

    /**
     * Genera el código hash de esta instancia.
     *
     * @return Código hash calculado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customData);
    }

    /**
     * Genera una representación en forma de cadena para esta instancia.
     *
     * @return Cadena que representa esta instancia.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("isValid", validate())
                .toString();
    }
}
