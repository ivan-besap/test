package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.RequestWithId;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

/**
 * Representa una solicitud enviada por el Sistema Central al Punto de Carga para obtener una o más configuraciones.
 * Esta clase extiende {@link RequestWithId} para incluir un identificador único de solicitud.
 *
 * <b>Campos:</b>
 * - {@code key}: Array de claves de configuración (opcional).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     String[] keys = {"key1", "key2", "key3"};
 *     GetConfigurationRequest request = new GetConfigurationRequest(keys);
 *
 *     if (request.validate()) {
 *         System.out.println("Solicitud válida: " + request);
 *         // Enviar la solicitud al Punto de Carga
 *     } else {
 *         System.out.println("Solicitud inválida");
 *     }
 * </pre>
 */
@XmlRootElement
public class GetConfigurationRequest extends RequestWithId {

    @JsonProperty("key")
    @Size(max = 50, message = "Cada clave no debe exceder los 50 caracteres")
    private String[] key;

    /**
     * Constructor por defecto.
     * Requerido para la deserialización JSON y XML.
     */
    public GetConfigurationRequest() {}

    /**
     * Constructor con el array de claves.
     *
     * @param key Array de claves de configuración.
     */
    public GetConfigurationRequest(String[] key) {
        setKey(key);
    }

    /**
     * Obtiene el array de claves de configuración.
     *
     * @return Array de claves de configuración.
     */
    public String[] getKey() {
        return key;
    }

    /**
     * Establece el array de claves de configuración.
     *
     * @param key Array de claves de configuración. Cada clave no debe exceder los 50 caracteres.
     * @throws IllegalArgumentException si alguna clave excede los 50 caracteres.
     */
    @XmlElement
    public void setKey(String[] key) {
        if (key != null) {
            for (String k : key) {
                if (k != null && k.length() > 50) {
                    throw new IllegalArgumentException("La clave '" + k + "' excede el límite de 50 caracteres");
                }
            }
        }
        this.key = key;
    }

    /**
     * Valida que todas las claves (si existen) no excedan los 50 caracteres.
     *
     * @return true si la solicitud es válida, false en caso contrario.
     */
    @Override
    public boolean validate() {
        if (key == null) {
            return true;
        }
        for (String k : key) {
            if (k != null && k.length() > 50) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetConfigurationRequest that = (GetConfigurationRequest) o;
        return Arrays.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(key);
    }

    @Override
    public String toString() {
        return "GetConfigurationRequest{" +
                "key=" + (key == null ? "null" : String.join(",", key)) +
                ", isValid=" + validate() +
                '}';
    }
}