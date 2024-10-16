package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations.Utils.KeyValueType;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests.GetConfigurationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Arrays;
import java.util.Objects;

/**
 * Representa la confirmación enviada por el Punto de Carga al Sistema Central en respuesta a una solicitud {@link GetConfigurationRequest}.
 * Esta clase extiende {@link Confirmation} para mantener la consistencia con otras confirmaciones en el sistema.
 *
 * <b>Campos:</b>
 * - {@code configurationKey}: Lista de claves y valores de configuración conocidos (opcional).
 * - {@code unknownKey}: Lista de claves de configuración desconocidas (opcional).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     KeyValueType[] configKeys = {new KeyValueType("key1", "value1"), new KeyValueType("key2", "value2")};
 *     String[] unknownKeys = {"unknownKey1", "unknownKey2"};
 *     GetConfigurationConfirmation confirmation = new GetConfigurationConfirmation();
 *     confirmation.setConfigurationKey(configKeys);
 *     confirmation.setUnknownKey(unknownKeys);
 *
 *     if (confirmation.validate()) {
 *         System.out.println("Confirmación válida: " + confirmation);
 *         // Procesar la confirmación
 *     } else {
 *         System.out.println("Confirmación inválida");
 *     }
 * </pre>
 */
@XmlRootElement(name = "getConfigurationResponse")
@XmlType(propOrder = {"configurationKey", "unknownKey"})
public class GetConfigurationConfirmation extends Confirmation {

    private static final String ERROR_MESSAGE = "Excede el límite de %s caracteres";

    @JsonProperty("configurationKey")
    private KeyValueType[] configurationKey;

    @JsonProperty("unknownKey")
    @Size(max = 50, message = "Cada clave desconocida no debe exceder los 50 caracteres")
    private String[] unknownKey;

    /**
     * Constructor por defecto.
     * Requerido para la deserialización JSON y XML.
     */
    public GetConfigurationConfirmation() {}

    /**
     * Obtiene la lista de claves y valores de configuración conocidos.
     *
     * @return Array de {@link KeyValueType}.
     */
    public KeyValueType[] getConfigurationKey() {
        return configurationKey;
    }

    /**
     * Establece la lista de claves y valores de configuración conocidos.
     *
     * @param configurationKey Array de {@link KeyValueType}.
     */
    @XmlElement
    public void setConfigurationKey(KeyValueType[] configurationKey) {
        this.configurationKey = configurationKey;
    }

    /**
     * Obtiene la lista de claves de configuración desconocidas.
     *
     * @return Array de nombres de claves desconocidas.
     */
    public String[] getUnknownKey() {
        return unknownKey;
    }

    /**
     * Establece la lista de claves de configuración desconocidas.
     *
     * @param unknownKey Array de String, máximo 50 caracteres cada uno, insensible a mayúsculas/minúsculas.
     * @throws IllegalArgumentException si alguna clave excede los 50 caracteres.
     */
    @XmlElement
    public void setUnknownKey(String[] unknownKey) {
        if (unknownKey != null) {
            for (String key : unknownKey) {
                if (key != null && key.length() > 50) {
                    throw new IllegalArgumentException(String.format(ERROR_MESSAGE, 50));
                }
            }
        }
        this.unknownKey = unknownKey;
    }

    /**
     * Valida que todas las claves de configuración sean válidas y que las claves desconocidas no excedan los 50 caracteres.
     *
     * @return true si la confirmación es válida, false en caso contrario.
     */
    @Override
    public boolean validate() {
        if (configurationKey != null) {
            for (KeyValueType kvt : configurationKey) {
                if (!kvt.validate()) {
                    return false;
                }
            }
        }
        if (unknownKey != null) {
            for (String key : unknownKey) {
                if (key != null && key.length() > 50) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetConfigurationConfirmation that = (GetConfigurationConfirmation) o;
        return Arrays.equals(configurationKey, that.configurationKey) &&
                Arrays.equals(unknownKey, that.unknownKey);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(GetConfigurationConfirmation.class);
        result = 31 * result + Arrays.hashCode(configurationKey);
        result = 31 * result + Arrays.hashCode(unknownKey);
        return result;
    }

    @Override
    public String toString() {
        return "GetConfigurationConfirmation{" +
                "configurationKey=" + Arrays.toString(configurationKey) +
                ", unknownKey=" + Arrays.toString(unknownKey) +
                ", isValid=" + validate() +
                '}';
    }
}