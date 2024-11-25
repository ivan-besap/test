
package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils.GetVariableData;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud para obtener valores de variables en un Charging Station.
 *
 * <p>Esta clase representa la solicitud para recuperar valores de variables de acuerdo a los
 * parámetros especificados en el mensaje GetVariableData.
 */
public class GetVariablesRequest extends RequestWithId {

    /** Datos personalizados opcionales. */
    @Nullable
    private CustomData customData;

    /** Lista de parámetros para la solicitud de variables. */
    private GetVariableData[] getVariableData;

    /**
     * Constructor de la clase GetVariablesRequest.
     *
     * @param getVariableData Lista de parámetros para la solicitud de variables.
     */
    public GetVariablesRequest(GetVariableData[] getVariableData) {
        setGetVariableData(getVariableData);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Datos personalizados.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
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
     * @param customData Datos personalizados.
     * @return {@code true} si son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return La instancia actual.
     */
    public GetVariablesRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la lista de parámetros para la solicitud de variables.
     *
     * @return Lista de parámetros de solicitud.
     */
    public GetVariableData[] getGetVariableData() {
        return getVariableData;
    }

    /**
     * Establece la lista de parámetros para la solicitud de variables.
     *
     * @param getVariableData Lista de parámetros de solicitud.
     */
    public void setGetVariableData(GetVariableData[] getVariableData) {
        if (!isValidGetVariableData(getVariableData)) {
            throw new PropertyConstraintException(getVariableData, "getVariableData es inválido");
        }
        this.getVariableData = getVariableData;
    }

    /**
     * Verifica si la lista de parámetros de solicitud es válida.
     *
     * @param getVariableData Lista de parámetros de solicitud.
     * @return {@code true} si es válida, {@code false} en caso contrario.
     */
    private boolean isValidGetVariableData(GetVariableData[] getVariableData) {
        return getVariableData != null
                && getVariableData.length >= 1
                && Arrays.stream(getVariableData).allMatch(GetVariableData::validate);
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidGetVariableData(getVariableData);
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
        GetVariablesRequest that = (GetVariablesRequest) o;
        return Objects.equals(customData, that.customData)
                && Arrays.equals(getVariableData, that.getVariableData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, Arrays.hashCode(getVariableData));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("getVariableData", getVariableData)
                .add("isValid", validate())
                .toString();
    }
}
