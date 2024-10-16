package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Confirmations;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.Confirmation;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Confirmations.Types.UpdateFirmwareStatusEnumType;

import java.util.Objects;

/**
 * Confirmación de la solicitud de actualización de firmware firmado en OCPP.
 * <p>
 * Esta confirmación indica si la estación de carga pudo aceptar la solicitud
 * de actualización de firmware firmado.
 * </p>
 *
 * <p><b>Ejemplo de uso:</b></p>
 * <pre>
 *     {@code
 *     SignedUpdateFirmwareConfirmation confirmation = new SignedUpdateFirmwareConfirmation(UpdateFirmwareStatusEnumType.Accepted);
 *     System.out.println(confirmation);
 *     }
 * </pre>
 */
public class SignedUpdateFirmwareConfirmation extends Confirmation {

    private UpdateFirmwareStatusEnumType status;

    /**
     * Constructor privado para propósitos de serialización.
     */
    private SignedUpdateFirmwareConfirmation() {
    }

    /**
     * Maneja los campos obligatorios.
     *
     * @param status El estado de la actualización del firmware. Ver {@link #setStatus(UpdateFirmwareStatusEnumType)}
     */
    public SignedUpdateFirmwareConfirmation(UpdateFirmwareStatusEnumType status) {
        setStatus(status);
    }

    /**
     * Este campo indica si la estación de carga pudo aceptar la solicitud.
     *
     * @return {@link UpdateFirmwareStatusEnumType}
     */
    public UpdateFirmwareStatusEnumType getStatus() {
        return status;
    }

    /**
     * Requerido. Este campo indica si la estación de carga pudo aceptar la solicitud.
     *
     * @param status {@link UpdateFirmwareStatusEnumType}
     */
    public void setStatus(UpdateFirmwareStatusEnumType status) {
        this.status = status;
    }

    /**
     * Valida que la confirmación contenga los datos obligatorios.
     *
     * @return {@code true} si el estado no es nulo, {@code false} de lo contrario.
     */
    @Override
    public boolean validate() {
        return status != null;
    }

    /**
     * Compara si dos confirmaciones de actualización de firmware firmado son equivalentes.
     *
     * @param o el objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignedUpdateFirmwareConfirmation that = (SignedUpdateFirmwareConfirmation) o;
        return Objects.equals(status, that.status);
    }

    /**
     * Genera un código hash único basado en el estado de la actualización del firmware.
     *
     * @return el valor hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    /**
     * Devuelve una representación en cadena de la confirmación de actualización de firmware firmado.
     *
     * @return una cadena que representa la confirmación, incluyendo el estado y la validación.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("status", status)
                .add("isValid", validate())
                .toString();
    }
}
