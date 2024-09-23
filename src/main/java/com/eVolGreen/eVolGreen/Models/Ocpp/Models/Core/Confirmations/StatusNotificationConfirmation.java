package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Confirmation;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Representa la confirmación de la solicitud de notificación de estado, enviada desde el sistema central al punto de carga.
 * Esta clase extiende {@link Confirmation} para mantener la consistencia con otras confirmaciones en el sistema.
 *
 * <p>Esta confirmación no contiene campos adicionales, ya que simplemente confirma la recepción de la notificación de estado.</p>
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     StatusNotificationConfirmation confirmation = new StatusNotificationConfirmation();
 *
 *     if (confirmation.validate()) {
 *         System.out.println("Confirmación válida: " + confirmation);
 *         // Procesar la confirmación
 *     } else {
 *         System.out.println("Confirmación inválida");
 *     }
 * </pre>
 */
@XmlRootElement(name = "statusNotificationResponse")
public class StatusNotificationConfirmation extends Confirmation {

    /**
     * Constructor por defecto.
     */
    public StatusNotificationConfirmation() {}

    /**
     * Valida si la confirmación es válida.
     * En este caso, siempre retorna true ya que no hay campos que validar.
     *
     * @return true, ya que la confirmación siempre es válida.
     */
    @Override
    public boolean validate() {
        return true;
    }

    /**
     * Compara esta confirmación con otro objeto para determinar si son iguales.
     *
     * @param o el objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true; // Todas las instancias son iguales ya que no tienen campos
    }

    /**
     * Genera un código hash para esta confirmación.
     *
     * @return el código hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(StatusNotificationConfirmation.class);
    }

    /**
     * Retorna una representación en cadena de esta confirmación.
     *
     * @return una cadena que representa esta confirmación.
     */
    @Override
    public String toString() {
        return "StatusNotificationConfirmation{" +
                "isValid=" + validate() +
                '}';
    }
}