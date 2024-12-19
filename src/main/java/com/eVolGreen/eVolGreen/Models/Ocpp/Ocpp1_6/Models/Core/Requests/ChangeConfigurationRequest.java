package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud enviada desde el sistema central al punto de carga para cambiar una configuración.
 * El contenido y significado de los campos "key" y "value" deben ser acordados entre el punto de carga y el sistema central.
 */
@XmlRootElement
@XmlType(propOrder = {"key", "value"})
public class ChangeConfigurationRequest extends RequestWithId {

    private static final String ERROR_MESSAGE = "Excedido el límite de %s caracteres";
    private static final int KEY_MAX_LENGTH = 50;
    private static final int VALUE_MAX_LENGTH = 500;

    @NotNull(message = "La clave (key) es obligatoria")
    @Size(max = KEY_MAX_LENGTH, message = "La clave no puede exceder los 50 caracteres")
    @JsonProperty("key")
    private String key;

    @NotNull(message = "El valor (value) es obligatorio")
    @Size(max = VALUE_MAX_LENGTH, message = "El valor no puede exceder los 500 caracteres")
    @JsonProperty("value")
    private String value;

    /**
     * Constructor por defecto para la deserialización.
     * @deprecated Use {@link #ChangeConfigurationRequest(String, String)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public ChangeConfigurationRequest() {}

    /**
     * Constructor con campos requeridos.
     *
     * @param key Nombre de la configuración a cambiar. Máximo 50 caracteres.
     * @param value Nuevo valor para la configuración. Máximo 500 caracteres.
     */
    public ChangeConfigurationRequest(String key, String value) {
        setKey(key);
        setValue(value);
    }

    /**
     * Obtiene el nombre de la configuración a cambiar.
     *
     * @return Nombre de la configuración.
     */
    public String getKey() {
        return key;
    }

    /**
     * Establece el nombre de la configuración a cambiar.
     *
     * @param key Nombre de la configuración. No puede ser nulo y máximo 50 caracteres.
     * @throws IllegalArgumentException si la clave excede el límite de caracteres.
     */
    @XmlElement
    public void setKey(String key) {
        if (key != null && key.length() > KEY_MAX_LENGTH) {
            throw new IllegalArgumentException(String.format(ERROR_MESSAGE, KEY_MAX_LENGTH));
        }
        this.key = key;
    }

    /**
     * Obtiene el nuevo valor para la configuración.
     *
     * @return Valor de la configuración.
     */
    public String getValue() {
        return value;
    }

    /**
     * Establece el nuevo valor para la configuración.
     *
     * @param value Nuevo valor. No puede ser nulo y máximo 500 caracteres.
     * @throws IllegalArgumentException si el valor excede el límite de caracteres.
     */
    @XmlElement
    public void setValue(String value) {
        if (value != null && value.length() > VALUE_MAX_LENGTH) {
            throw new IllegalArgumentException(String.format(ERROR_MESSAGE, VALUE_MAX_LENGTH));
        }
        this.value = value;
    }

    /**
     * Valida que los campos obligatorios estén presentes y sean válidos.
     *
     * @return true si la solicitud es válida, false en caso contrario.
     */
    @Override
    public boolean validate() {
        return key != null && !key.isEmpty() && key.length() <= KEY_MAX_LENGTH &&
                value != null && !value.isEmpty() && value.length() <= VALUE_MAX_LENGTH;
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     *
     * @return false, ya que el cambio de configuración no está directamente relacionado con una transacción.
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
        ChangeConfigurationRequest that = (ChangeConfigurationRequest) o;
        return Objects.equals(key, that.key) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "ChangeConfigurationRequest{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", isValid=" + validate() +
                '}';
    }
}