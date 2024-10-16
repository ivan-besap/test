package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;


import java.util.Objects;

/**
 * Confirmación para la notificación de eventos de seguridad en OCPP.
 *
 * <p>Esta clase se utiliza para confirmar que la notificación de un evento de seguridad ha sido recibida correctamente
 * por el sistema central.</p>
 *
 * <p><b>Ejemplo de uso:</b></p>
 * <pre>
 *     {@code
 *     SecurityEventNotificationConfirmation confirmation = new SecurityEventNotificationConfirmation();
 *     System.out.println(confirmation);
 *     }
 * </pre>
 */
public class SecurityEventNotificationConfirmation extends Confirmation {

    /**
     * Valida la confirmación de la notificación de eventos de seguridad.
     *
     * @return {@code true} siempre, ya que esta confirmación no requiere validaciones adicionales.
     */
    @Override
    public boolean validate() {
        return true;
    }

    /**
     * Compara si dos confirmaciones de notificación de eventos de seguridad son equivalentes.
     *
     * @param o El objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        return this == o || (o != null && getClass() == o.getClass());
    }

    /**
     * Genera un código hash único para esta confirmación.
     *
     * @return El valor hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(SecurityEventNotificationConfirmation.class);
    }

    /**
     * Devuelve una representación en cadena de la confirmación de notificación de eventos de seguridad.
     *
     * @return Una cadena que representa la confirmación, indicando si es válida.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("isValid", validate()).toString();
    }
}
