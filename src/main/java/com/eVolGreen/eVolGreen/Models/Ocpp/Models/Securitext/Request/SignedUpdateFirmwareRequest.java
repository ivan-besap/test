package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Models.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Securitext.Request.Types.FirmwareType;

import java.util.Objects;
import java.util.UUID;

/**
 * Solicitud para actualizar el firmware firmado en un Charge Point.
 * <p>
 * Esta solicitud indica los detalles necesarios para que el Charge Point
 * descargue e instale un firmware firmado.
 * </p>
 *
 * <p><b>Ejemplo de uso:</b></p>
 * <pre>
 *     {@code
 *     SignedUpdateFirmwareRequest request = new SignedUpdateFirmwareRequest(12345, firmware);
 *     request.setRetries(3);
 *     request.setRetryInterval(120);
 *     System.out.println(request);
 *     }
 * </pre>
 */
public class SignedUpdateFirmwareRequest extends RequestWithId {

    private Integer retries;
    private Integer retryInterval;
    private Integer requestId;
    private FirmwareType firmware;

    /**
     * Constructor privado para propósitos de serialización.
     */
    private SignedUpdateFirmwareRequest() {
    }

    /**
     * Maneja los campos obligatorios.
     *
     * @param requestId El identificador de la solicitud. Ver {@link #setRequestId(Integer)}
     * @param firmware  El firmware a instalar en el Charge Point. Ver {@link #setFirmware(FirmwareType)}
     */
    public SignedUpdateFirmwareRequest(Integer requestId, FirmwareType firmware) {
        setRequestId(requestId);
        setFirmware(firmware);
    }

    /**
     * Especifica cuántas veces el Charge Point debe intentar descargar el firmware
     * antes de rendirse. Si este campo no está presente, queda a criterio del Charge Point.
     *
     * @return Integer
     */
    public Integer getRetries() {
        return retries;
    }

    /**
     * Opcional. Especifica cuántas veces el Charge Point debe intentar descargar el firmware
     * antes de rendirse. Si este campo no está presente, queda a criterio del Charge Point.
     *
     * @param retries Integer
     */
    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    /**
     * El intervalo en segundos entre cada intento de descarga.
     * Si este campo no está presente, queda a criterio del Charge Point.
     *
     * @return Integer
     */
    public Integer getRetryInterval() {
        return retryInterval;
    }

    /**
     * Opcional. El intervalo en segundos entre cada intento de descarga.
     * Si este campo no está presente, queda a criterio del Charge Point.
     *
     * @param retryInterval Integer
     */
    public void setRetryInterval(Integer retryInterval) {
        this.retryInterval = retryInterval;
    }

    /**
     * El identificador de esta solicitud.
     *
     * @return Integer
     */
    public Integer getRequestId() {
        return requestId;
    }

    /**
     * Requerido. El identificador de esta solicitud.
     *
     * @param requestId Integer
     */
    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    /**
     * Especifica el firmware que se actualizará en el Charge Point.
     *
     * @return {@link FirmwareType}
     */
    public FirmwareType getFirmware() {
        return firmware;
    }

    /**
     * Requerido. Especifica el firmware que se actualizará en el Charge Point.
     *
     * @param firmware {@link FirmwareType}
     */
    public void setFirmware(FirmwareType firmware) {
        this.firmware = firmware;
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     *
     * @return {@code false} ya que no está relacionada con una transacción.
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

    /**
     * Valida que la solicitud contenga los datos obligatorios.
     *
     * @return {@code true} si los campos requeridos están presentes y son válidos, {@code false} de lo contrario.
     */
    @Override
    public boolean validate() {
        return requestId != null && firmware != null && firmware.validate();
    }

    /**
     * Compara si dos solicitudes de actualización de firmware son equivalentes.
     *
     * @param o el objeto a comparar.
     * @return {@code true} si ambos objetos son iguales, {@code false} de lo contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignedUpdateFirmwareRequest that = (SignedUpdateFirmwareRequest) o;
        return Objects.equals(retries, that.retries)
                && Objects.equals(retryInterval, that.retryInterval)
                && Objects.equals(requestId, that.requestId)
                && Objects.equals(firmware, that.firmware);
    }

    /**
     * Genera un código hash único basado en los campos de la solicitud.
     *
     * @return el valor hash generado.
     */
    @Override
    public int hashCode() {
        return Objects.hash(retries, retryInterval, requestId, firmware);
    }

    /**
     * Devuelve una representación en cadena de la solicitud de actualización de firmware firmado.
     *
     * @return una cadena que representa la solicitud, incluyendo los campos de reintentos, intervalos y firmware.
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("retries", retries)
                .add("retryInterval", retryInterval)
                .add("requestId", requestId)
                .add("firmware", firmware)
                .add("isValid", validate())
                .toString();
    }
}
