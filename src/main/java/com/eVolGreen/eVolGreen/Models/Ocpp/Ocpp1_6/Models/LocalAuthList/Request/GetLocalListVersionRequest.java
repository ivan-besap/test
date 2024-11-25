package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.LocalAuthList.Request;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.util.UUID;

/**
 * Representa una solicitud enviada desde el Sistema Central al Punto de Carga para obtener la versión actual de la lista local de autorizaciones.
 * Esta clase extiende {@link RequestWithId} para incluir un identificador único de solicitud.
 * <p>
 * Este mensaje no contiene propiedades específicas adicionales.
 * </p>
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     GetLocalListVersionRequest solicitud = new GetLocalListVersionRequest();
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
public class GetLocalListVersionRequest extends RequestWithId {

    /**
     * Constructor por defecto.
     * Requerido para la deserialización JSON y XML.
     */
    public GetLocalListVersionRequest() {}

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
        return Objects.hash(GetLocalListVersionRequest.class);
    }

    @Override
    public String toString() {
        return "GetLocalListVersionRequest{isValid=" + validate() + "}";
    }
}