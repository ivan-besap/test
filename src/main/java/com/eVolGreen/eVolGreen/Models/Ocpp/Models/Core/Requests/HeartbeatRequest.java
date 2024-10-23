package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud de Heartbeat enviada por el Punto de Carga al Sistema Central en OCPP 1.6.
 * Esta clase extiende {@link RequestWithId} para incluir un identificador único de solicitud.
 * <p>
 * La solicitud de Heartbeat no contiene campos adicionales y siempre es válida.
 * Se utiliza para mantener la conexión activa y verificar el estado del Punto de Carga.
 * </p>
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     HeartbeatRequest solicitud = new HeartbeatRequest();
 *
 *     if (solicitud.validate()) {
 *         System.out.println("Solicitud válida: " + solicitud);
 *         // Enviar la solicitud al Sistema Central
 *     } else {
 *         System.out.println("Solicitud inválida");
 *     }
 * </pre>
 */
@XmlRootElement
public class HeartbeatRequest extends RequestWithId {

    /**
     * Constructor por defecto.
     * Requerido para la deserialización JSON y XML.
     */
    public HeartbeatRequest() {}

    /**
     * Valida la solicitud. Siempre devuelve true ya que no hay campos específicos para validar.
     *
     * @return true, indicando que la solicitud siempre es válida.
     */
    @Override
    public boolean validate() {
        return true;
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     *
     * @return false, ya que esta solicitud no está relacionada con una transacción específica.
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
        return true; // Todas las instancias son iguales ya que no tienen propiedades específicas
    }

    @Override
    public int hashCode() {
        return Objects.hash(HeartbeatRequest.class);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("isValid", validate()).toString();
    }
}