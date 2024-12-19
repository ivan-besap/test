package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Core.Confirmations.Utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Clase que representa un par clave-valor en la configuración.
 */
public class KeyValueType {

    @NotNull(message = "La clave es obligatoria")
    @Size(max = 50, message = "La clave no debe exceder los 50 caracteres")
    @JsonProperty("key")
    private String key;

    @NotNull(message = "El campo readonly es obligatorio")
    @JsonProperty("readonly")
    private Boolean readonly;

    @Size(max = 500, message = "El valor no debe exceder los 500 caracteres")
    @JsonProperty("value")
    private String value;

    // Constructor por defecto
    public KeyValueType() {}

    // Constructor con campos requeridos
    public KeyValueType(String key, Boolean readonly) {
        this.key = key;
        this.readonly = readonly;
    }

    // Getters y setters
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getReadonly() {
        return readonly;
    }

    public void setReadonly(Boolean readonly) {
        this.readonly = readonly;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // Método de validación
    public boolean validate() {
        return key != null && key.length() <= 50 && readonly != null && (value == null || value.length() <= 500);
    }

    @Override
    public String toString() {
        return "KeyValueType{" +
                "key='" + key + '\'' +
                ", readonly=" + readonly +
                ", value='" + value + '\'' +
                ", isValid=" + validate() +
                '}';
    }
}
