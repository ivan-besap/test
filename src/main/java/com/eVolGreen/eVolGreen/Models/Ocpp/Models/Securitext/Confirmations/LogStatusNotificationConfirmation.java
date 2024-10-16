package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;


import java.util.Objects;

/**
 * Confirmación para la notificación de estado del registro.
 * Esta clase se utiliza para confirmar que el estado del registro ha sido recibido por el sistema central.
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     {@code
 *     LogStatusNotificationConfirmation confirmation = new LogStatusNotificationConfirmation();
 *     System.out.println(confirmation);
 *     }
 * </pre>
 */
public class LogStatusNotificationConfirmation extends Confirmation {

    /**
     * Valida la confirmación.
     * Como esta confirmación no tiene campos obligatorios, siempre será válida.
     *
     * @return {@code true}, indicando que la confirmación es válida.
     */
    @Override
    public boolean validate() {
        return true;
    }

    /**
     * Compara si dos confirmaciones de notificación de estado del registro son equivalentes.
     *
     * @param o el objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass();
    }

    /**
     * Genera un código hash único para esta confirmación.
     *
     * @return el valor hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(LogStatusNotificationConfirmation.class);
    }

    /**
     * Devuelve una representación en cadena de la confirmación de notificación de estado del registro.
     *
     * @return una cadena que representa la confirmación.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("isValid", validate()).toString();
    }
}
