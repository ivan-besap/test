package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Confirmations.Types.TriggerMessageStatusEnumType;

import java.util.Objects;

/**
 * Representa la confirmación extendida de TriggerMessage en OCPP 2.0.
 * Indica si el punto de carga enviará la notificación solicitada o no.
 *
 * <b>Ejemplo de uso:</b>
 * <pre>
 *     {@code
 *     TriggerMessageStatusEnumType status = TriggerMessageStatusEnumType.Accepted;
 *     ExtendedTriggerMessageConfirmation confirmation = new ExtendedTriggerMessageConfirmation(status);
 *     System.out.println(confirmation);
 *     }
 * </pre>
 */
public class ExtendedTriggerMessageConfirmation extends Confirmation {

    private TriggerMessageStatusEnumType status;

    /**
     * Constructor privado por defecto para fines de serialización.
     */
    private ExtendedTriggerMessageConfirmation() {
    }

    /**
     * Constructor que inicializa la confirmación con el estado proporcionado.
     *
     * @param status Estado de la confirmación, de tipo {@link TriggerMessageStatusEnumType}.
     */
    public ExtendedTriggerMessageConfirmation(TriggerMessageStatusEnumType status) {
        setStatus(status);
    }

    /**
     * Obtiene el estado que indica si el punto de carga enviará la notificación solicitada.
     *
     * @return {@link TriggerMessageStatusEnumType} indicando el estado de la solicitud.
     */
    public TriggerMessageStatusEnumType getStatus() {
        return status;
    }

    /**
     * Establece el estado de la solicitud de TriggerMessage.
     * Este campo es obligatorio.
     *
     * @param status {@link TriggerMessageStatusEnumType} indicando si la notificación será enviada.
     */
    public void setStatus(TriggerMessageStatusEnumType status) {
        this.status = status;
    }

    /**
     * Valida que la confirmación sea válida.
     * Comprueba que el campo {@code status} no sea nulo.
     *
     * @return {@code true} si la confirmación es válida, {@code false} de lo contrario.
     */
    @Override
    public boolean validate() {
        return status != null;
    }

    /**
     * Compara si dos confirmaciones de TriggerMessage son equivalentes.
     *
     * @param o el objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtendedTriggerMessageConfirmation that = (ExtendedTriggerMessageConfirmation) o;
        return Objects.equals(status, that.status);
    }

    /**
     * Genera un código hash único basado en el estado de la confirmación.
     *
     * @return el valor hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    /**
     * Devuelve una representación en cadena de la confirmación de TriggerMessage.
     *
     * @return una cadena que representa la confirmación, incluyendo el estado y su validez.
     */
    @Override
    public String toString() {
        return "ExtendedTriggerMessageConfirmation{" +
                "status=" + status +
                ", isValid=" + validate() +
                '}';
    }
}
