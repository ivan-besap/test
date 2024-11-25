package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Confirmations.Utils.IdTokenInfo;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Transactions.Requests.Utils.IdToken;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa los datos de autorización utilizados en el sistema de carga.
 *
 * Contiene información sobre el identificador de autorización y detalles relacionados
 * para validar el acceso a las estaciones de carga.
 */
public class AuthorizationData {

    /** Datos personalizados opcionales para esta autorización. */
    @Nullable
    private CustomData customData;

    /**
     * Identificador utilizado para la autorización.
     * Es un identificador insensible a mayúsculas/minúsculas que soporta múltiples formas de identificación.
     */
    private IdToken idToken;

    /**
     * Información adicional sobre el estado del identificador (ID Token).
     * Incluye detalles como la fecha de expiración para fines de almacenamiento en caché.
     */
    @Nullable private IdTokenInfo idTokenInfo;

    /**
     * Constructor para inicializar los datos de autorización con un identificador.
     *
     * @param idToken Identificador utilizado para la autorización.
     */
    public AuthorizationData(IdToken idToken) {
        setIdToken(idToken);
    }

    /**
     * Obtiene los datos personalizados asociados a la autorización.
     *
     * @return Datos personalizados o null si no se especificaron.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Asigna datos personalizados a la autorización.
     *
     * @param customData Datos personalizados a asignar.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Los datos personalizados no son válidos");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados proporcionados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return true si son válidos, false en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Agrega datos personalizados a la autorización.
     *
     * @param customData Datos personalizados.
     * @return La instancia actualizada de la clase.
     */
    public AuthorizationData withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el identificador utilizado para la autorización.
     *
     * @return Identificador utilizado para la autorización.
     */
    public IdToken getIdToken() {
        return idToken;
    }

    /**
     * Asigna un identificador para la autorización.
     *
     * @param idToken Identificador utilizado para la autorización.
     */
    public void setIdToken(IdToken idToken) {
        if (!isValidIdToken(idToken)) {
            throw new PropertyConstraintException(idToken, "El identificador no es válido");
        }
        this.idToken = idToken;
    }

    /**
     * Verifica si el identificador proporcionado es válido.
     *
     * @param idToken Identificador a validar.
     * @return true si es válido, false en caso contrario.
     */
    private boolean isValidIdToken(IdToken idToken) {
        return idToken != null && idToken.validate();
    }

    /**
     * Obtiene información adicional sobre el estado del identificador.
     *
     * @return Información del identificador o null si no se especificó.
     */
    @Nullable
    public IdTokenInfo getIdTokenInfo() {
        return idTokenInfo;
    }

    /**
     * Asigna información adicional sobre el estado del identificador.
     *
     * @param idTokenInfo Información sobre el identificador.
     */
    public void setIdTokenInfo(@Nullable IdTokenInfo idTokenInfo) {
        if (!isValidIdTokenInfo(idTokenInfo)) {
            throw new PropertyConstraintException(idTokenInfo, "La información del identificador no es válida");
        }
        this.idTokenInfo = idTokenInfo;
    }

    /**
     * Verifica si la información del identificador es válida.
     *
     * @param idTokenInfo Información del identificador a validar.
     * @return true si es válida, false en caso contrario.
     */
    private boolean isValidIdTokenInfo(@Nullable IdTokenInfo idTokenInfo) {
        return idTokenInfo == null || idTokenInfo.validate();
    }

    /**
     * Agrega información adicional sobre el estado del identificador.
     *
     * @param idTokenInfo Información sobre el identificador.
     * @return La instancia actualizada de la clase.
     */
    public AuthorizationData withIdTokenInfo(@Nullable IdTokenInfo idTokenInfo) {
        setIdTokenInfo(idTokenInfo);
        return this;
    }

    /**
     * Valida todos los campos de la clase.
     *
     * @return true si todos los campos son válidos, false en caso contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidIdToken(idToken)
                && isValidIdTokenInfo(idTokenInfo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthorizationData that = (AuthorizationData) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(idToken, that.idToken)
                && Objects.equals(idTokenInfo, that.idTokenInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, idToken, idTokenInfo);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("idToken", idToken)
                .add("idTokenInfo", idTokenInfo)
                .add("isValid", validate())
                .toString();
    }
}
