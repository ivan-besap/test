package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Enums.ChargingProfilePurposeEnum;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Perfil de carga a limpiar.
 *
 * Representa los criterios utilizados para identificar los perfiles de carga que deben eliminarse
 * en una estación de carga o en su conjunto.
 * <p>OCPP 2.0.1 FINAL</p>
 */
public class ClearChargingProfile {

    /** Datos personalizados adicionales */
    @Nullable
    private CustomData customData;

    /**
     * Identificador de EVSE.
     * Especifica el ID del EVSE para el cual se deben eliminar los perfiles de carga.
     * Un valor de 0 indica el perfil de carga general para la estación de carga.
     * Si este parámetro no está presente, se aplicará a todos los perfiles de carga que coincidan
     * con los demás criterios.
     */
    @Nullable
    private Integer evseId;

    /**
     * Propósito del perfil de carga.
     * Define el propósito de los perfiles de carga que se eliminarán si cumplen con los demás criterios.
     */
    @Nullable
    private ChargingProfilePurposeEnum chargingProfilePurpose;

    /**
     * Nivel de pila del perfil de carga.
     * Define el nivel de pila para el cual se eliminarán los perfiles de carga, si cumplen con los
     * demás criterios.
     */
    @Nullable
    private Integer stackLevel;

    /** Constructor vacío para la clase ClearChargingProfile. */
    public ClearChargingProfile() {}

    /**
     * Obtiene los datos personalizados adicionales.
     *
     * @return Datos personalizados adicionales.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados adicionales.
     *
     * @param customData Datos personalizados adicionales.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados son inválidos.");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados adicionales.
     * @return Verdadero si los datos son válidos, falso de lo contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Establece los datos personalizados de forma fluida.
     *
     * @param customData Datos personalizados adicionales.
     * @return Esta instancia con los datos personalizados establecidos.
     */
    public ClearChargingProfile withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el identificador del EVSE para el cual se deben eliminar los perfiles de carga.
     *
     * @return Identificador del EVSE.
     */
    @Nullable
    public Integer getEvseId() {
        return evseId;
    }

    /**
     * Establece el identificador del EVSE para el cual se deben eliminar los perfiles de carga.
     *
     * @param evseId Identificador del EVSE.
     */
    public void setEvseId(@Nullable Integer evseId) {
        this.evseId = evseId;
    }

    /**
     * Establece el identificador del EVSE de forma fluida.
     *
     * @param evseId Identificador del EVSE.
     * @return Esta instancia con el identificador del EVSE establecido.
     */
    public ClearChargingProfile withEvseId(@Nullable Integer evseId) {
        setEvseId(evseId);
        return this;
    }

    /**
     * Obtiene el propósito de los perfiles de carga a eliminar.
     *
     * @return Propósito de los perfiles de carga.
     */
    @Nullable
    public ChargingProfilePurposeEnum getChargingProfilePurpose() {
        return chargingProfilePurpose;
    }

    /**
     * Establece el propósito de los perfiles de carga a eliminar.
     *
     * @param chargingProfilePurpose Propósito de los perfiles de carga.
     */
    public void setChargingProfilePurpose(@Nullable ChargingProfilePurposeEnum chargingProfilePurpose) {
        this.chargingProfilePurpose = chargingProfilePurpose;
    }

    /**
     * Establece el propósito de los perfiles de carga de forma fluida.
     *
     * @param chargingProfilePurpose Propósito de los perfiles de carga.
     * @return Esta instancia con el propósito establecido.
     */
    public ClearChargingProfile withChargingProfilePurpose(@Nullable ChargingProfilePurposeEnum chargingProfilePurpose) {
        setChargingProfilePurpose(chargingProfilePurpose);
        return this;
    }

    /**
     * Obtiene el nivel de pila para los perfiles de carga a eliminar.
     *
     * @return Nivel de pila.
     */
    @Nullable
    public Integer getStackLevel() {
        return stackLevel;
    }

    /**
     * Establece el nivel de pila para los perfiles de carga a eliminar.
     *
     * @param stackLevel Nivel de pila.
     */
    public void setStackLevel(@Nullable Integer stackLevel) {
        this.stackLevel = stackLevel;
    }

    /**
     * Establece el nivel de pila de forma fluida.
     *
     * @param stackLevel Nivel de pila.
     * @return Esta instancia con el nivel de pila establecido.
     */
    public ClearChargingProfile withStackLevel(@Nullable Integer stackLevel) {
        setStackLevel(stackLevel);
        return this;
    }

    /**
     * Valida esta instancia.
     *
     * @return Verdadero si la instancia es válida, falso de lo contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClearChargingProfile that = (ClearChargingProfile) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(evseId, that.evseId)
                && Objects.equals(chargingProfilePurpose, that.chargingProfilePurpose)
                && Objects.equals(stackLevel, that.stackLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, evseId, chargingProfilePurpose, stackLevel);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("evseId", evseId)
                .add("chargingProfilePurpose", chargingProfilePurpose)
                .add("stackLevel", stackLevel)
                .add("isValid", validate())
                .toString();
    }
}
