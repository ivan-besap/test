package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.RequestWithId;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la solicitud para reiniciar un punto de carga en OCPP 1.6.
 * Esta solicitud es enviada desde el Sistema Central al Punto de Carga.
 * El tipo de reinicio puede ser "Hard" (reinicio completo) o "Soft" (reinicio ligero).
 * Esta clase extiende {@link RequestWithId} para incluir un identificador único de solicitud.
 *
 * <b>Campos:</b>
 * - {@code type}: El tipo de reinicio a realizar (obligatorio).
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     ResetRequest solicitud = new ResetRequest(ResetType.HARD);
 *
 *     if (solicitud.validate()) {
 *         System.out.println("Solicitud válida: " + solicitud);
 *         // Enviar la solicitud al Punto de Carga
 *     } else {
 *         System.out.println("Solicitud inválida");
 *     }
 * </pre>
 */
@XmlRootElement
public class ResetRequest extends RequestWithId {

    @NotNull(message = "El tipo de reinicio es obligatorio")
    @JsonProperty("type")
    private ResetType type;

    /**
     * Constructor por defecto.
     * @deprecated Use {@link #ResetRequest(ResetType)} para asegurar que se establezcan los campos requeridos.
     */
    @Deprecated
    public ResetRequest() {}

    /**
     * Construye una nueva instancia de ResetRequest con el tipo de reinicio especificado.
     *
     * @param type El tipo de reinicio que el Punto de Carga debe realizar.
     */
    public ResetRequest(ResetType type) {
        setType(type);
    }

    /**
     * Obtiene el tipo de reinicio que el Punto de Carga debe realizar.
     *
     * @return ResetType, el tipo de reinicio.
     */
    public ResetType getType() {
        return type;
    }

    /**
     * Establece el tipo de reinicio que el Punto de Carga debe realizar.
     *
     * @param type ResetType, el tipo de reinicio.
     */
    @XmlElement
    public void setType(ResetType type) {
        this.type = type;
    }

    /**
     * Valida si los datos de la solicitud son correctos.
     *
     * @return true si los datos son válidos, false en caso contrario.
     */
    @Override
    public boolean validate() {
        return type != null;
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     *
     * @return false, ya que esta solicitud no está directamente relacionada con una transacción.
     */
    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResetRequest that = (ResetRequest) o;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "ResetRequest{" +
                "type=" + type +
                ", isValid=" + validate() +
                '}';
    }
}