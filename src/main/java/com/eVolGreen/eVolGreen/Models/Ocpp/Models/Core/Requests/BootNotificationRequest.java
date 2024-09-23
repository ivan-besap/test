package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Core.Requests;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.RequestWithId;
import com.eVolGreen.eVolGreen.Models.Ocpp.Models.Utils.ModelUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

/**
 * Representa una solicitud de BootNotification en OCPP 1.6.
 * Esta solicitud es enviada por el punto de carga al sistema central para notificar su inicio
 * y proporcionar información sobre sus características.
 */
@XmlRootElement
@XmlType(propOrder = {
        "chargePointVendor", "chargePointModel", "chargePointSerialNumber",
        "chargeBoxSerialNumber", "firmwareVersion", "iccid", "imsi",
        "meterType", "meterSerialNumber"
})
public class BootNotificationRequest extends RequestWithId {

    private static final int STRING_20_CHAR_MAX_LENGTH = 20;
    private static final int STRING_25_CHAR_MAX_LENGTH = 25;
    private static final int STRING_50_CHAR_MAX_LENGTH = 50;
    private static final String ERROR_MESSAGE = "Excedido el límite de %s caracteres";

    @NotNull(message = "chargePointVendor es obligatorio")
    @Size(max = STRING_20_CHAR_MAX_LENGTH, message = "chargePointVendor no puede superar los 20 caracteres")
    @JsonProperty("chargePointVendor")
    private String chargePointVendor;

    @NotNull(message = "chargePointModel es obligatorio")
    @Size(max = STRING_20_CHAR_MAX_LENGTH, message = "chargePointModel no puede superar los 20 caracteres")
    @JsonProperty("chargePointModel")
    private String chargePointModel;

    @Size(max = STRING_25_CHAR_MAX_LENGTH, message = "chargePointSerialNumber no puede superar los 25 caracteres")
    private String chargePointSerialNumber;

    @Size(max = STRING_25_CHAR_MAX_LENGTH, message = "chargeBoxSerialNumber no puede superar los 25 caracteres")
    private String chargeBoxSerialNumber;

    @Size(max = STRING_50_CHAR_MAX_LENGTH, message = "firmwareVersion no puede superar los 50 caracteres")
    private String firmwareVersion;

    @Size(max = STRING_20_CHAR_MAX_LENGTH, message = "iccid no puede superar los 20 caracteres")
    private String iccid;

    @Size(max = STRING_20_CHAR_MAX_LENGTH, message = "imsi no puede superar los 20 caracteres")
    private String imsi;

    @Size(max = STRING_25_CHAR_MAX_LENGTH, message = "meterType no puede superar los 25 caracteres")
    private String meterType;

    @Size(max = STRING_25_CHAR_MAX_LENGTH, message = "meterSerialNumber no puede superar los 25 caracteres")
    private String meterSerialNumber;

    /**
     * Constructor por defecto para la deserialización.
     */
    public BootNotificationRequest() {}

    /**
     * Constructor que inicializa los campos obligatorios.
     *
     * @param chargePointVendor Proveedor del punto de carga.
     * @param chargePointModel Modelo del punto de carga.
     */
    public BootNotificationRequest(String chargePointVendor, String chargePointModel) {
        setChargePointVendor(chargePointVendor);
        setChargePointModel(chargePointModel);
    }

    /**
     * Obtiene el nombre del proveedor del punto de carga.
     *
     * @return Nombre del proveedor.
     */
    public String getChargePointVendor() {
        return chargePointVendor;
    }

    /**
     * Establece el nombre del proveedor del punto de carga. Requerido.
     *
     * @param chargePointVendor Nombre del proveedor (máximo 20 caracteres).
     */
    @XmlElement
    public void setChargePointVendor(String chargePointVendor) {
        if (!ModelUtil.validate(chargePointVendor, STRING_20_CHAR_MAX_LENGTH)) {
            throw new IllegalArgumentException(String.format(ERROR_MESSAGE, STRING_20_CHAR_MAX_LENGTH));
        }
        this.chargePointVendor = chargePointVendor;
    }

    /**
     * Obtiene el modelo del punto de carga.
     *
     * @return Modelo del punto de carga.
     */
    public String getChargePointModel() {
        return chargePointModel;
    }

    /**
     * Establece el modelo del punto de carga. Requerido.
     *
     * @param chargePointModel Modelo del punto de carga (máximo 20 caracteres).
     */
    @XmlElement
    public void setChargePointModel(String chargePointModel) {
        if (!ModelUtil.validate(chargePointModel, STRING_20_CHAR_MAX_LENGTH)) {
            throw new IllegalArgumentException(String.format(ERROR_MESSAGE, STRING_20_CHAR_MAX_LENGTH));
        }
        this.chargePointModel = chargePointModel;
    }

    /**
     * Obtiene el número de serie del punto de carga.
     *
     * @return Número de serie del punto de carga.
     */
    public String getChargePointSerialNumber() {
        return chargePointSerialNumber;
    }

    /**
     * Establece el número de serie del punto de carga. Opcional.
     *
     * @param chargePointSerialNumber Número de serie del punto de carga (máximo 25 caracteres).
     */
    @XmlElement
    public void setChargePointSerialNumber(String chargePointSerialNumber) {
        if (chargePointSerialNumber != null && !ModelUtil.validate(chargePointSerialNumber, STRING_25_CHAR_MAX_LENGTH)) {
            throw new IllegalArgumentException(String.format(ERROR_MESSAGE, STRING_25_CHAR_MAX_LENGTH));
        }
        this.chargePointSerialNumber = chargePointSerialNumber;
    }

    /**
     * Obtiene el número de serie del ChargeBox.
     *
     * @return Número de serie del ChargeBox.
     * @deprecated Será eliminado en futuras versiones. Use {@link #getChargePointSerialNumber()} en su lugar.
     */
    @Deprecated
    public String getChargeBoxSerialNumber() {
        return chargeBoxSerialNumber;
    }

    /**
     * Establece el número de serie del ChargeBox. Opcional.
     *
     * @param chargeBoxSerialNumber Número de serie del ChargeBox (máximo 25 caracteres).
     * @deprecated Será eliminado en futuras versiones. Use {@link #setChargePointSerialNumber(String)} en su lugar.
     */
    @Deprecated
    @XmlElement
    public void setChargeBoxSerialNumber(String chargeBoxSerialNumber) {
        if (chargeBoxSerialNumber != null && !ModelUtil.validate(chargeBoxSerialNumber, STRING_25_CHAR_MAX_LENGTH)) {
            throw new IllegalArgumentException(String.format(ERROR_MESSAGE, STRING_25_CHAR_MAX_LENGTH));
        }
        this.chargeBoxSerialNumber = chargeBoxSerialNumber;
    }

    /**
     * Obtiene la versión del firmware del punto de carga.
     *
     * @return Versión del firmware del punto de carga.
     */
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    /**
     * Establece la versión del firmware del punto de carga. Opcional.
     *
     * @param firmwareVersion Versión del firmware (máximo 50 caracteres).
     */
    @XmlElement
    public void setFirmwareVersion(String firmwareVersion) {
        if (firmwareVersion != null && !ModelUtil.validate(firmwareVersion, STRING_50_CHAR_MAX_LENGTH)) {
            throw new IllegalArgumentException(String.format(ERROR_MESSAGE, STRING_50_CHAR_MAX_LENGTH));
        }
        this.firmwareVersion = firmwareVersion;
    }

    /**
     * Obtiene el ICCID de la tarjeta SIM del módem.
     *
     * @return ICCID de la tarjeta SIM.
     */
    public String getIccid() {
        return iccid;
    }

    /**
     * Establece el ICCID de la tarjeta SIM del módem. Opcional.
     *
     * @param iccid ICCID de la tarjeta SIM (máximo 20 caracteres).
     */
    @XmlElement
    public void setIccid(String iccid) {
        if (iccid != null && !ModelUtil.validate(iccid, STRING_20_CHAR_MAX_LENGTH)) {
            throw new IllegalArgumentException(String.format(ERROR_MESSAGE, STRING_20_CHAR_MAX_LENGTH));
        }
        this.iccid = iccid;
    }

    /**
     * Obtiene el IMSI de la tarjeta SIM del módem.
     *
     * @return IMSI de la tarjeta SIM.
     */
    public String getImsi() {
        return imsi;
    }

    /**
     * Establece el IMSI de la tarjeta SIM del módem. Opcional.
     *
     * @param imsi IMSI de la tarjeta SIM (máximo 20 caracteres).
     */
    @XmlElement
    public void setImsi(String imsi) {
        if (imsi != null && !ModelUtil.validate(imsi, STRING_20_CHAR_MAX_LENGTH)) {
            throw new IllegalArgumentException(String.format(ERROR_MESSAGE, STRING_20_CHAR_MAX_LENGTH));
        }
        this.imsi = imsi;
    }

    /**
     * Obtiene el tipo del medidor principal del punto de carga.
     *
     * @return Tipo del medidor principal.
     */
    public String getMeterType() {
        return meterType;
    }

    /**
     * Establece el tipo del medidor principal del punto de carga. Opcional.
     *
     * @param meterType Tipo del medidor principal (máximo 25 caracteres).
     */
    @XmlElement
    public void setMeterType(String meterType) {
        if (meterType != null && !ModelUtil.validate(meterType, STRING_25_CHAR_MAX_LENGTH)) {
            throw new IllegalArgumentException(String.format(ERROR_MESSAGE, STRING_25_CHAR_MAX_LENGTH));
        }
        this.meterType = meterType;
    }

    /**
     * Obtiene el número de serie del medidor principal del punto de carga.
     *
     * @return Número de serie del medidor.
     */
    public String getMeterSerialNumber() {
        return meterSerialNumber;
    }

    /**
     * Establece el número de serie del medidor principal del punto de carga. Opcional.
     *
     * @param meterSerialNumber Número de serie del medidor principal (máximo 25 caracteres).
     */
    @XmlElement
    public void setMeterSerialNumber(String meterSerialNumber) {
        if (meterSerialNumber != null && !ModelUtil.validate(meterSerialNumber, STRING_25_CHAR_MAX_LENGTH)) {
            throw new IllegalArgumentException(String.format(ERROR_MESSAGE, STRING_25_CHAR_MAX_LENGTH));
        }
        this.meterSerialNumber = meterSerialNumber;
    }

    /**
     * Valida que los campos obligatorios de la solicitud cumplan con las reglas establecidas.
     *
     * @return true si la solicitud es válida, false en caso contrario.
     */
    @Override
    public boolean validate() {
        return ModelUtil.validate(chargePointVendor, STRING_20_CHAR_MAX_LENGTH)
                && ModelUtil.validate(chargePointModel, STRING_20_CHAR_MAX_LENGTH);
    }

    /**
     * Indica si esta solicitud está relacionada con una transacción.
     *
     * @return false, ya que BootNotification no está relacionada con una transacción específica.
     */
    @Override
    public boolean transactionRelated() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BootNotificationRequest that = (BootNotificationRequest) o;
        return Objects.equals(chargePointVendor, that.chargePointVendor) &&
                Objects.equals(chargePointModel, that.chargePointModel) &&
                Objects.equals(chargeBoxSerialNumber, that.chargeBoxSerialNumber) &&
                Objects.equals(chargePointSerialNumber, that.chargePointSerialNumber) &&
                Objects.equals(firmwareVersion, that.firmwareVersion) &&
                Objects.equals(iccid, that.iccid) &&
                Objects.equals(imsi, that.imsi) &&
                Objects.equals(meterSerialNumber, that.meterSerialNumber) &&
                Objects.equals(meterType, that.meterType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chargePointVendor, chargePointModel, chargeBoxSerialNumber,
                chargePointSerialNumber, firmwareVersion, iccid, imsi,
                meterSerialNumber, meterType);
    }

    @Override
    public String toString() {
        return "BootNotificationRequest{" +
                "chargePointVendor='" + chargePointVendor + '\'' +
                ", chargePointModel='" + chargePointModel + '\'' +
                ", chargePointSerialNumber='" + chargePointSerialNumber + '\'' +
                ", chargeBoxSerialNumber='" + chargeBoxSerialNumber + '\'' +
                ", firmwareVersion='" + firmwareVersion + '\'' +
                ", iccid='" + iccid + '\'' +
                ", imsi='" + imsi + '\'' +
                ", meterType='" + meterType + '\'' +
                ", meterSerialNumber='" + meterSerialNumber + '\'' +
                ", isValid=" + validate() +
                '}';
    }
}