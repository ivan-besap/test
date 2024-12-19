package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * EVSE (Equipo de Suministro de Energía para Vehículos Eléctricos)
 *
 * Representa un equipo específico dentro de una estación de carga.
 */
public class EVSE {

    /** Datos personalizados para atributos adicionales */
    @Nullable
    private CustomData customData;

    /**
     * Identificador del EVSE.
     * Contiene un número mayor que 0 que designa un EVSE dentro de la estación de carga.
     */
    private Integer id;

    /**
     * Identificador del conector.
     * Un identificador que designa un conector específico en el EVSE mediante un número de índice.
     */
    @Nullable
    private Integer connectorId;

    /**
     * Constructor para la clase EVSE.
     *
     * @param id Identificador del EVSE.
     */
    public EVSE(Integer id) {
        setId(id);
    }

    /** Obtiene los datos personalizados. */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /** Establece los datos personalizados. */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /** Valida los datos personalizados. */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /** Establece los datos personalizados de forma fluida. */
    public EVSE withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /** Obtiene el identificador del EVSE. */
    public Integer getId() {
        return id;
    }

    /** Establece el identificador del EVSE. */
    public void setId(Integer id) {
        if (!isValidId(id)) {
            throw new PropertyConstraintException(id, "El identificador del EVSE no es válido.");
        }
        this.id = id;
    }

    /** Valida el identificador del EVSE. */
    private boolean isValidId(Integer id) {
        return id != null;
    }

    /** Obtiene el identificador del conector. */
    @Nullable
    public Integer getConnectorId() {
        return connectorId;
    }

    /** Establece el identificador del conector. */
    public void setConnectorId(@Nullable Integer connectorId) {
        this.connectorId = connectorId;
    }

    /** Establece el identificador del conector de forma fluida. */
    public EVSE withConnectorId(@Nullable Integer connectorId) {
        setConnectorId(connectorId);
        return this;
    }

    /** Valida la entidad. */
    public boolean validate() {
        return isValidCustomData(customData) && isValidId(id);
    }

    /** Compara esta instancia con otro objeto. */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EVSE that = (EVSE) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(id, that.id)
                && Objects.equals(connectorId, that.connectorId);
    }

    /** Genera un código hash para esta instancia. */
    @Override
    public int hashCode() {
        return Objects.hash(customData, id, connectorId);
    }

    /** Representación en cadena de la entidad. */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("id", id)
                .add("connectorId", connectorId)
                .add("isValid", validate())
                .toString();
    }
}
