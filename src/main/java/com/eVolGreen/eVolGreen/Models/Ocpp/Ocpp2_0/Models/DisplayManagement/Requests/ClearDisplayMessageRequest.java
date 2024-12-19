package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.DisplayManagement.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud para eliminar un mensaje mostrado en la estación de carga.
 *
 * Representa una petición para eliminar un mensaje de visualización específico de la estación
 * de carga.
 */
public class ClearDisplayMessageRequest extends RequestWithId {

    /**
     * Datos personalizados para atributos adicionales.
     */
    @Nullable
    private CustomData customData;

    /**
     * Identificador del mensaje que debe ser eliminado de la estación de carga.
     */
    private Integer id;

    /**
     * Constructor para la clase ClearDisplayMessageRequest.
     *
     * @param id Identificador del mensaje que debe ser eliminado de la estación de carga.
     */
    public ClearDisplayMessageRequest(Integer id) {
        setId(id);
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
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos.");
        }
        this.customData = customData;
    }

    /**
     * Valida los datos personalizados.
     *
     * @param customData Datos personalizados.
     * @return {@code true} si los datos son válidos, {@code false} de lo contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Establece los datos personalizados de forma fluida.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia de la solicitud.
     */
    public ClearDisplayMessageRequest withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el identificador del mensaje a eliminar.
     *
     * @return Identificador del mensaje.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador del mensaje a eliminar.
     *
     * @param id Identificador del mensaje.
     */
    public void setId(Integer id) {
        if (!isValidId(id)) {
            throw new PropertyConstraintException(id, "El identificador del mensaje no puede ser nulo.");
        }
        this.id = id;
    }

    /**
     * Valida el identificador del mensaje.
     *
     * @param id Identificador del mensaje.
     * @return {@code true} si el identificador es válido, {@code false} de lo contrario.
     */
    private boolean isValidId(Integer id) {
        return id != null;
    }

    /**
     * Método de validación para la solicitud.
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
     * @return {@code false}, ya que esta solicitud no está relacionada con una transacción.
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
        ClearDisplayMessageRequest that = (ClearDisplayMessageRequest) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, id);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("id", id)
                .add("isValid", validate())
                .toString();
    }
}
