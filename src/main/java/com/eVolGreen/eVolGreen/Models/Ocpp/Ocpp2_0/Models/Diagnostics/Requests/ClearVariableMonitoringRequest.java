package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud para borrar la monitorización de variables.
 *
 * <p>Representa una solicitud para eliminar monitores específicos asociados a variables, identificados por sus ID.
 * Este mensaje es parte del protocolo OCPP 2.0.1.</p>
 */
public class ClearVariableMonitoringRequest extends RequestWithId {

    /**
     * Datos personalizados.
     *
     * <p>Permite incluir atributos adicionales específicos de la implementación.</p>
     */
    @Nullable
    private CustomData customData;

    /**
     * Lista de monitores que se deben borrar, identificados por su ID.
     */
    private Integer[] id;

    /**
     * Constructor de la clase ClearVariableMonitoringRequest.
     *
     * @param id Lista de monitores que se deben borrar, identificados por su ID.
     */
    public ClearVariableMonitoringRequest(Integer[] id) {
        setId(id);
    }

    /**
     * Obtiene los datos personalizados.
     *
     * @return Los datos personalizados, o {@code null} si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados.
     *
     * @param customData Los datos personalizados.
     * @throws PropertyConstraintException si los datos personalizados no son válidos.
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
     * @param customData Los datos personalizados a verificar.
     * @return {@code true} si los datos son válidos, de lo contrario {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Método fluido para establecer datos personalizados.
     *
     * @param customData Los datos personalizados.
     * @return La instancia actual de la solicitud.
     */
    public ClearVariableMonitoringRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene la lista de monitores que se deben borrar, identificados por su ID.
     *
     * @return Lista de monitores que se deben borrar.
     */
    public Integer[] getId() {
        return id;
    }

    /**
     * Establece la lista de monitores que se deben borrar, identificados por su ID.
     *
     * @param id Lista de monitores que se deben borrar.
     * @throws PropertyConstraintException si la lista de IDs es inválida.
     */
    public void setId(Integer[] id) {
        if (!isValidId(id)) {
            throw new PropertyConstraintException(id, "La lista de IDs es inválida. Debe contener al menos un elemento.");
        }
        this.id = id;
    }

    /**
     * Verifica si la lista de IDs es válida.
     *
     * @param id Lista de IDs a verificar.
     * @return {@code true} si la lista es válida, de lo contrario {@code false}.
     */
    private boolean isValidId(Integer[] id) {
        return id != null && id.length >= 1;
    }

    /**
     * Valida la solicitud.
     *
     * @return {@code true} si la solicitud es válida, de lo contrario {@code false}.
     */
    @Override
    public boolean validate() {
        return isValidCustomData(customData) && isValidId(id);
    }

    /**
     * Indica si la solicitud está relacionada con una transacción.
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
     * Compara esta solicitud con otro objeto.
     *
     * @param o El objeto a comparar.
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
        ClearVariableMonitoringRequest that = (ClearVariableMonitoringRequest) o;
        return Objects.equals(customData, that.customData) && Arrays.equals(id, that.id);
    }

    /**
     * Calcula el código hash de la solicitud.
     *
     * @return El código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(customData, Arrays.hashCode(id));
    }

    /**
     * Genera una representación en cadena de la solicitud.
     *
     * @return La representación en cadena.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("id", id)
                .add("isValid", validate())
                .toString();
    }
}
