package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.LoggingAndReporting.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud para obtener la versión actual de la lista local.
 *
 * <p>Este mensaje permite al CSMS solicitar a la estación de carga la versión de la lista local de autorización.
 */
public class GetLocalListVersionRequest extends RequestWithId {

    /** Datos personalizados opcionales. */
    @Nullable
    private CustomData customData;

    /**
     * Constructor predeterminado para la clase GetLocalListVersionRequest.
     */
    public GetLocalListVersionRequest() {}

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
     * Establece los datos personalizados para la solicitud.
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
     * Establece y retorna la instancia actual de la solicitud con los datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return La instancia actual de la solicitud.
     */
    public GetLocalListVersionRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Valida la solicitud para asegurar que cumple con los requisitos necesarios.
     *
     * @return {@code true} si la solicitud es válida, {@code false} en caso contrario.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData);
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     *
     * @return {@code false}, ya que no está relacionada con una transacción.
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

    /**
     * Compara esta solicitud con otro objeto para verificar igualdad.
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
        GetLocalListVersionRequest that = (GetLocalListVersionRequest) o;
        return Objects.equals(customData, that.customData);
    }

    /**
     * Genera un código hash para esta solicitud.
     *
     * @return El código hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customData);
    }

    /**
     * Representación en cadena de texto de esta solicitud.
     *
     * @return Cadena de texto que representa la solicitud.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("isValid", validate())
                .toString();
    }
}
