package com.eVolGreen.eVolGreen.Models.Ocpp.Models.LocalAuthList;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la respuesta enviada desde el Punto de Carga al Sistema Central en respuesta a una {@link GetLocalListVersionRequest}.
 * Esta clase extiende {@link Confirmation} para mantener la consistencia con otras confirmaciones en el sistema.
 *
 * <b>Campos:</b>
 * - {@code listVersion}: Número de versión actual de la lista local de autorizaciones (obligatorio).
 *   Un valor de 0 indica que la lista está vacía, y un valor de -1 indica que el Punto de Carga no soporta listas de autorizaciones locales.
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     GetLocalListVersionConfirmation confirmacion = new GetLocalListVersionConfirmation(1);
 *
 *     if (confirmacion.validate()) {
 *         System.out.println("Confirmación válida: " + confirmacion);
 *         // Procesar la confirmación
 *     } else {
 *         System.out.println("Confirmación inválida");
 *     }
 * </pre>
 */
@XmlRootElement
public class GetLocalListVersionConfirmation extends Confirmation {

    @NotNull(message = "El campo listVersion es obligatorio")
    @Min(value = -1, message = "listVersion debe ser mayor o igual a -1")
    @JsonProperty("listVersion")
    private Integer listVersion;

    /**
     * Constructor por defecto.
     * @deprecated Use {@link #GetLocalListVersionConfirmation(Integer)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public GetLocalListVersionConfirmation() {}

    /**
     * Construye una nueva instancia de GetLocalListVersionConfirmation con el número de versión especificado.
     *
     * @param listVersion el número de versión de la lista local de autorizaciones.
     */
    public GetLocalListVersionConfirmation(Integer listVersion) {
        setListVersion(listVersion);
    }

    /**
     * Obtiene el número de versión actual de la lista local de autorizaciones.
     *
     * @return Integer, el número de versión de la lista.
     */
    public Integer getListVersion() {
        return listVersion;
    }

    /**
     * Establece el número de versión actual de la lista local de autorizaciones.
     *
     * @param listVersion Integer, el número de versión de la lista. Debe ser mayor o igual a -1.
     * @throws IllegalArgumentException si listVersion es menor que -1.
     */
    @XmlElement
    public void setListVersion(Integer listVersion) {
        if (listVersion != null && listVersion < -1) {
            throw new IllegalArgumentException("listVersion debe ser mayor o igual a -1");
        }
        this.listVersion = listVersion;
    }

    /**
     * Valida que el campo listVersion no sea nulo y sea mayor o igual a -1.
     *
     * @return true si la confirmación es válida, false en caso contrario.
     */
    @Override
    public boolean validate() {
        return listVersion != null && listVersion >= -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetLocalListVersionConfirmation that = (GetLocalListVersionConfirmation) o;
        return Objects.equals(listVersion, that.listVersion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listVersion);
    }

    @Override
    public String toString() {
        return "GetLocalListVersionConfirmation{" +
                "listVersion=" + listVersion +
                ", isValid=" + validate() +
                '}';
    }
}