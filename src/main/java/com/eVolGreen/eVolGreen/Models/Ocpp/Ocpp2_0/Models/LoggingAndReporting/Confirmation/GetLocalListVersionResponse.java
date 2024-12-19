package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Confirmation;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Respuesta al mensaje GetLocalListVersionRequest.
 *
 * <p>Indica la versión actual de la lista local de autorizaciones en la estación de carga.
 */
public class GetLocalListVersionResponse extends Confirmation {

    /** Datos personalizados opcionales. */
    @Nullable
    private CustomData customData;

    /**
     * Número de versión actual de la lista local de autorizaciones en la estación de carga.
     */
    private Integer versionNumber;

    /**
     * Constructor de la clase GetLocalListVersionResponse.
     *
     * @param versionNumber Número de versión actual de la lista local de autorizaciones.
     */
    public GetLocalListVersionResponse(Integer versionNumber) {
        setVersionNumber(versionNumber);
    }

    /**
     * Obtiene los datos personalizados asociados con la respuesta.
     *
     * @return Datos personalizados.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados para la respuesta.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados proporcionados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Establece y retorna la instancia actual de la respuesta con los datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return La instancia actual de la respuesta.
     */
    public GetLocalListVersionResponse withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el número de versión actual de la lista local de autorizaciones.
     *
     * @return Número de versión actual de la lista local.
     */
    public Integer getVersionNumber() {
        return versionNumber;
    }

    /**
     * Establece el número de versión actual de la lista local de autorizaciones.
     *
     * @param versionNumber Número de versión actual de la lista local.
     */
    public void setVersionNumber(Integer versionNumber) {
        if (!isValidVersionNumber(versionNumber)) {
            throw new PropertyConstraintException(versionNumber, "El número de versión no es válido");
        }
        this.versionNumber = versionNumber;
    }

    /**
     * Verifica si el número de versión proporcionado es válido.
     *
     * @param versionNumber Número de versión a validar.
     * @return {@code true} si el número de versión es válido, {@code false} en caso contrario.
     */
    private boolean isValidVersionNumber(Integer versionNumber) {
        return versionNumber != null;
    }

    /**
     * Valida la respuesta para asegurar que cumple con los requisitos necesarios.
     *
     * @return {@code true} si la respuesta es válida, {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidVersionNumber(versionNumber);
    }

    /**
     * Compara esta respuesta con otro objeto para verificar igualdad.
     *
     * @param o El objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetLocalListVersionResponse that = (GetLocalListVersionResponse) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(versionNumber, that.versionNumber);
    }

    /**
     * Genera un código hash para esta respuesta.
     *
     * @return El código hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customData, versionNumber);
    }

    /**
     * Representación en cadena de texto de esta respuesta.
     *
     * @return Cadena de texto que representa la respuesta.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("versionNumber", versionNumber)
                .add("isValid", validate())
                .toString();
    }
}
