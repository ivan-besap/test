package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Enums.ResetEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud para realizar un reinicio en la estación de carga o en un EVSE específico.
 *
 * <p>El mensaje "ResetRequest" permite solicitar diferentes tipos de reinicio, ya sea para toda la
 * estación de carga o para un EVSE en particular, según se especifique.
 */
public class ResetRequest extends RequestWithId {
    /** Datos personalizados que pueden ser añadidos por el cliente. */
    @Nullable
    private CustomData customData;

    /** Tipo de reinicio que debe realizar la estación de carga o el EVSE. */
    private ResetEnum type;

    /**
     * Identificador de un EVSE específico que debe ser reiniciado, en lugar de toda la estación de
     * carga.
     */
    @Nullable
    private Integer evseId;

    /**
     * Constructor para la clase ResetRequest.
     *
     * @param type Tipo de reinicio que debe realizar la estación de carga o el EVSE.
     */
    public ResetRequest(ResetEnum type) {
        setType(type);
    }

    /**
     * Obtiene los datos personalizados proporcionados por el cliente.
     *
     * @return Datos personalizados o null si no se han proporcionado.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados proporcionados por el cliente.
     *
     * @param customData Datos personalizados que pueden ser añadidos por el cliente.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados proporcionados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos personalizados son válidos, {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados proporcionados por el cliente.
     *
     * @param customData Datos personalizados que pueden ser añadidos por el cliente.
     * @return La instancia actual para encadenar métodos.
     */
    public ResetRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el tipo de reinicio que debe realizarse.
     *
     * @return Tipo de reinicio que debe realizar la estación de carga o el EVSE.
     */
    public ResetEnum getType() {
        return type;
    }

    /**
     * Establece el tipo de reinicio que debe realizarse.
     *
     * @param type Tipo de reinicio que debe realizar la estación de carga o el EVSE.
     */
    public void setType(ResetEnum type) {
        if (!isValidType(type)) {
            throw new PropertyConstraintException(type, "El tipo de reinicio no es válido.");
        }
        this.type = type;
    }

    /**
     * Verifica si el tipo de reinicio proporcionado es válido.
     *
     * @param type Tipo de reinicio a validar.
     * @return {@code true} si el tipo es válido, {@code false} en caso contrario.
     */
    private boolean isValidType(ResetEnum type) {
        return type != null;
    }

    /**
     * Obtiene el identificador del EVSE que debe ser reiniciado.
     *
     * @return Identificador del EVSE o null si no se ha especificado.
     */
    @Nullable
    public Integer getEvseId() {
        return evseId;
    }

    /**
     * Establece el identificador de un EVSE específico que debe ser reiniciado.
     *
     * @param evseId Identificador del EVSE que debe ser reiniciado.
     */
    public void setEvseId(@Nullable Integer evseId) {
        this.evseId = evseId;
    }

    /**
     * Agrega el identificador de un EVSE específico que debe ser reiniciado.
     *
     * @param evseId Identificador del EVSE que debe ser reiniciado.
     * @return La instancia actual para encadenar métodos.
     */
    public ResetRequest withEvseId(@Nullable Integer evseId) {
        setEvseId(evseId);
        return this;
    }

    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidType(type);
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
        ResetRequest that = (ResetRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(type, that.type)
                && Objects.equals(evseId, that.evseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, type, evseId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("type", type)
                .add("evseId", evseId)
                .add("isValid", validate())
                .toString();
    }
}
