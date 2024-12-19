package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.Securitext.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;

import java.util.Objects;

/**
 * Confirmación para la notificación de estado del firmware firmado en OCPP.
 * <p>
 * Esta confirmación se utiliza como respuesta a la notificación de estado de la actualización del firmware firmado
 * enviada desde la estación de carga.
 * </p>
 *
 * <p><b>Ejemplo de uso:</b></p>
 * <pre>
 *     {@code
 *     SignedFirmwareStatusNotificationConfirmation confirmation = new SignedFirmwareStatusNotificationConfirmation();
 *     System.out.println(confirmation);
 *     }
 * </pre>
 */
public class SignedFirmwareStatusNotificationConfirmation extends Confirmation {

    /**
     * Valida la confirmación.
     * En este caso, no hay validaciones específicas ya que siempre se considera válida.
     *
     * @return {@code true}, siempre válida.
     */
    @Override
    public boolean validate() {
        return true;
    }

    /**
     * Compara si dos confirmaciones de estado de firmware firmado son equivalentes.
     *
     * @param o el objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        return this == o || (o != null && getClass() == o.getClass());
    }

    /**
     * Genera un código hash único basado en la clase {@code SignedFirmwareStatusNotificationConfirmation}.
     *
     * @return el valor hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(SignedFirmwareStatusNotificationConfirmation.class);
    }

    /**
     * Devuelve una representación en cadena de la confirmación de estado de firmware firmado.
     *
     * @return una cadena que representa la confirmación y su estado de validación.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("isValid", validate())
                .toString();
    }
}
