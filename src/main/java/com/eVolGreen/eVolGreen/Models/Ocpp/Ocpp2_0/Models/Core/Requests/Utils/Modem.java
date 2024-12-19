package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa el módulo de comunicación inalámbrica.
 *
 * <p>Incluye los parámetros necesarios para iniciar y mantener la comunicación inalámbrica con
 * otros dispositivos, como el ICCID e IMSI de la tarjeta SIM del módem.
 */
public class Modem {

    /** Datos personalizados asociados al módulo. */
    @Nullable
    private CustomData customData;

    /**
     * ICCID de la tarjeta SIM del módem.
     *
     * <p>El identificador único de la tarjeta SIM utilizada en el módem.
     */
    @Nullable private String iccid;

    /**
     * IMSI de la tarjeta SIM del módem.
     *
     * <p>El identificador internacional del abonado móvil utilizado por la tarjeta SIM.
     */
    @Nullable private String imsi;

    /** Constructor de la clase Modem. */
    public Modem() {}

    /** @return Datos personalizados asociados al módulo. */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece datos personalizados asociados al módulo.
     *
     * @param customData Datos personalizados.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos");
        }
        this.customData = customData;
    }

    /** Verifica si los datos personalizados son válidos. */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados al módulo.
     *
     * @param customData Datos personalizados.
     * @return La instancia actualizada de Modem.
     */
    public Modem withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /** @return ICCID de la tarjeta SIM del módem. */
    @Nullable
    public String getIccid() {
        return iccid;
    }

    /**
     * Establece el ICCID de la tarjeta SIM del módem.
     *
     * @param iccid ICCID de la tarjeta SIM.
     */
    public void setIccid(@Nullable String iccid) {
        if (!isValidIccid(iccid)) {
            throw new PropertyConstraintException(iccid, "El ICCID no es válido");
        }
        this.iccid = iccid;
    }

    /** Verifica si el ICCID es válido. */
    private boolean isValidIccid(@Nullable String iccid) {
        return iccid == null || iccid.length() <= 20;
    }

    /**
     * Agrega el ICCID de la tarjeta SIM al módulo.
     *
     * @param iccid ICCID de la tarjeta SIM.
     * @return La instancia actualizada de Modem.
     */
    public Modem withIccid(@Nullable String iccid) {
        setIccid(iccid);
        return this;
    }

    /** @return IMSI de la tarjeta SIM del módem. */
    @Nullable
    public String getImsi() {
        return imsi;
    }

    /**
     * Establece el IMSI de la tarjeta SIM del módem.
     *
     * @param imsi IMSI de la tarjeta SIM.
     */
    public void setImsi(@Nullable String imsi) {
        if (!isValidImsi(imsi)) {
            throw new PropertyConstraintException(imsi, "El IMSI no es válido");
        }
        this.imsi = imsi;
    }

    /** Verifica si el IMSI es válido. */
    private boolean isValidImsi(@Nullable String imsi) {
        return imsi == null || imsi.length() <= 20;
    }

    /**
     * Agrega el IMSI de la tarjeta SIM al módulo.
     *
     * @param imsi IMSI de la tarjeta SIM.
     * @return La instancia actualizada de Modem.
     */
    public Modem withImsi(@Nullable String imsi) {
        setImsi(imsi);
        return this;
    }

    /**
     * Valida si los datos del módulo son correctos.
     *
     * @return {@code true} si los datos son válidos, {@code false} en caso contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData) && isValidIccid(iccid) && isValidImsi(imsi);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Modem that = (Modem) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(iccid, that.iccid)
                && Objects.equals(imsi, that.imsi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, iccid, imsi);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("iccid", iccid)
                .add("imsi", imsi)
                .add("isValid", validate())
                .toString();
    }
}
