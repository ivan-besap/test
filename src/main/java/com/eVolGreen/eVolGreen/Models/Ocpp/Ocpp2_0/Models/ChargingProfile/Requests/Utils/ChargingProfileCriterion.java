package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Enums.ChargingLimitSourceEnum;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.ChargingProfile.Requests.Enums.ChargingProfilePurposeEnum;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Objects;

/**
 * Criterio para el perfil de carga.
 *
 * <p>Un perfil de carga incluye un horario de carga que describe la cantidad de energía o corriente
 * que puede suministrarse en intervalos de tiempo específicos.
 */
public class ChargingProfileCriterion {

    /** Datos personalizados específicos de la implementación. */
    @Nullable
    private CustomData customData;

    /**
     * Propósito del perfil de carga.
     *
     * <p>Define el objetivo del perfil, como gestionar la carga para el vehículo eléctrico o el
     * suministro desde la estación de carga.
     */
    @Nullable
    private ChargingProfilePurposeEnum chargingProfilePurpose;

    /**
     * Nivel en la jerarquía de perfiles.
     *
     * <p>Valores más altos tienen prioridad sobre valores más bajos. El nivel más bajo es 0.
     */
    @Nullable
    private Integer stackLevel;

    /**
     * Lista de identificadores de perfiles de carga solicitados.
     *
     * <p>Se informará cualquier perfil de carga que coincida con uno de estos identificadores. Si
     * se omite, la estación de carga no filtrará los resultados por identificador.
     */
    @Nullable
    private Integer[] chargingProfileId;

    /**
     * Fuentes de límite de carga.
     *
     * <p>Define las fuentes para las que se deben informar los perfiles de carga. Si se omite, la
     * estación de carga no filtrará los resultados por fuente de límite de carga.
     */
    @Nullable
    private ChargingLimitSourceEnum[] chargingLimitSource;

    /** Constructor por defecto para la clase ChargingProfileCriterion. */
    public ChargingProfileCriterion() {}

    /**
     * Obtiene los datos personalizados específicos de la implementación.
     *
     * @return Datos personalizados o {@code null} si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados específicos de la implementación.
     *
     * @param customData Datos personalizados a configurar.
     * @throws PropertyConstraintException Si los datos personalizados no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "customData is invalid");
        }
        this.customData = customData;
    }

    /**
     * Comprueba si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si los datos son válidos; de lo contrario, {@code false}.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Configura y retorna la instancia con los datos personalizados establecidos.
     *
     * @param customData Datos personalizados.
     * @return Esta instancia.
     */
    public ChargingProfileCriterion withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el propósito del perfil de carga.
     *
     * @return Propósito del perfil de carga o {@code null} si no está definido.
     */
    @Nullable
    public ChargingProfilePurposeEnum getChargingProfilePurpose() {
        return chargingProfilePurpose;
    }

    /**
     * Establece el propósito del perfil de carga.
     *
     * @param chargingProfilePurpose Propósito del perfil de carga.
     */
    public void setChargingProfilePurpose(@Nullable ChargingProfilePurposeEnum chargingProfilePurpose) {
        this.chargingProfilePurpose = chargingProfilePurpose;
    }

    /**
     * Configura y retorna la instancia con el propósito del perfil de carga establecido.
     *
     * @param chargingProfilePurpose Propósito del perfil de carga.
     * @return Esta instancia.
     */
    public ChargingProfileCriterion withChargingProfilePurpose(@Nullable ChargingProfilePurposeEnum chargingProfilePurpose) {
        setChargingProfilePurpose(chargingProfilePurpose);
        return this;
    }

    /**
     * Obtiene el nivel en la jerarquía de perfiles.
     *
     * @return Nivel en la jerarquía de perfiles o {@code null} si no está definido.
     */
    @Nullable
    public Integer getStackLevel() {
        return stackLevel;
    }

    /**
     * Establece el nivel en la jerarquía de perfiles.
     *
     * @param stackLevel Nivel en la jerarquía de perfiles.
     */
    public void setStackLevel(@Nullable Integer stackLevel) {
        this.stackLevel = stackLevel;
    }

    /**
     * Configura y retorna la instancia con el nivel en la jerarquía de perfiles establecido.
     *
     * @param stackLevel Nivel en la jerarquía de perfiles.
     * @return Esta instancia.
     */
    public ChargingProfileCriterion withStackLevel(@Nullable Integer stackLevel) {
        setStackLevel(stackLevel);
        return this;
    }

    /**
     * Obtiene la lista de identificadores de perfiles de carga solicitados.
     *
     * @return Lista de identificadores de perfiles de carga o {@code null} si no están definidos.
     */
    @Nullable
    public Integer[] getChargingProfileId() {
        return chargingProfileId;
    }

    /**
     * Establece la lista de identificadores de perfiles de carga solicitados.
     *
     * @param chargingProfileId Lista de identificadores de perfiles de carga.
     * @throws PropertyConstraintException Si la lista de identificadores no es válida.
     */
    public void setChargingProfileId(@Nullable Integer[] chargingProfileId) {
        if (!isValidChargingProfileId(chargingProfileId)) {
            throw new PropertyConstraintException(chargingProfileId, "chargingProfileId is invalid");
        }
        this.chargingProfileId = chargingProfileId;
    }

    /**
     * Comprueba si la lista de identificadores de perfiles de carga es válida.
     *
     * @param chargingProfileId Lista de identificadores a validar.
     * @return {@code true} si la lista es válida; de lo contrario, {@code false}.
     */
    private boolean isValidChargingProfileId(@Nullable Integer[] chargingProfileId) {
        return chargingProfileId == null || chargingProfileId.length >= 1;
    }

    /**
     * Configura y retorna la instancia con la lista de identificadores de perfiles de carga
     * establecidos.
     *
     * @param chargingProfileId Lista de identificadores de perfiles de carga.
     * @return Esta instancia.
     */
    public ChargingProfileCriterion withChargingProfileId(@Nullable Integer[] chargingProfileId) {
        setChargingProfileId(chargingProfileId);
        return this;
    }

    /**
     * Obtiene las fuentes de límite de carga.
     *
     * @return Fuentes de límite de carga o {@code null} si no están definidas.
     */
    @Nullable
    public ChargingLimitSourceEnum[] getChargingLimitSource() {
        return chargingLimitSource;
    }

    /**
     * Establece las fuentes de límite de carga.
     *
     * @param chargingLimitSource Fuentes de límite de carga.
     * @throws PropertyConstraintException Si las fuentes no son válidas.
     */
    public void setChargingLimitSource(@Nullable ChargingLimitSourceEnum[] chargingLimitSource) {
        if (!isValidChargingLimitSource(chargingLimitSource)) {
            throw new PropertyConstraintException(chargingLimitSource, "chargingLimitSource is invalid");
        }
        this.chargingLimitSource = chargingLimitSource;
    }

    /**
     * Comprueba si las fuentes de límite de carga son válidas.
     *
     * @param chargingLimitSource Fuentes a validar.
     * @return {@code true} si las fuentes son válidas; de lo contrario, {@code false}.
     */
    private boolean isValidChargingLimitSource(@Nullable ChargingLimitSourceEnum[] chargingLimitSource) {
        return chargingLimitSource == null || (chargingLimitSource.length >= 1 && chargingLimitSource.length <= 4);
    }

    /**
     * Configura y retorna la instancia con las fuentes de límite de carga establecidas.
     *
     * @param chargingLimitSource Fuentes de límite de carga.
     * @return Esta instancia.
     */
    public ChargingProfileCriterion withChargingLimitSource(@Nullable ChargingLimitSourceEnum[] chargingLimitSource) {
        setChargingLimitSource(chargingLimitSource);
        return this;
    }

    public boolean validate() {
        return isValidCustomData(customData)
                && isValidChargingProfileId(chargingProfileId)
                && isValidChargingLimitSource(chargingLimitSource);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChargingProfileCriterion that = (ChargingProfileCriterion) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(chargingProfilePurpose, that.chargingProfilePurpose)
                && Objects.equals(stackLevel, that.stackLevel)
                && Arrays.equals(chargingProfileId, that.chargingProfileId)
                && Arrays.equals(chargingLimitSource, that.chargingLimitSource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                customData,
                chargingProfilePurpose,
                stackLevel,
                Arrays.hashCode(chargingProfileId),
                Arrays.hashCode(chargingLimitSource));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("chargingProfilePurpose", chargingProfilePurpose)
                .add("stackLevel", stackLevel)
                .add("chargingProfileId", chargingProfileId)
                .add("chargingLimitSource", chargingLimitSource)
                .add("isValid", validate())
                .toString();
    }
}
