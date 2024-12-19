package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Core.Requests.Utils.EVSE;
import org.springframework.lang.Nullable;

import java.util.Objects;


/**
 * Representa un componente físico o lógico del sistema, que puede incluir un EVSE y otros datos
 * relacionados.
 *
 * <p>Se utiliza para la configuración y monitoreo dentro del estándar OCPP 2.0.1.
 */
public class Component {

    /** Datos personalizados asociados al componente. */
    @Nullable
    private CustomData customData;

    /** EVSE (Electric Vehicle Supply Equipment) asociado al componente. */
    @Nullable private EVSE evse;

    /**
     * Nombre del componente. Se recomienda usar nombres estandarizados siempre que sea posible.
     * Insensible a mayúsculas/minúsculas y preferiblemente en Camel Case.
     */
    private String name;

    /**
     * Nombre de la instancia si el componente tiene múltiples instancias. Insensible a
     * mayúsculas/minúsculas y preferiblemente en Camel Case.
     */
    @Nullable private String instance;

    /**
     * Constructor principal para la clase.
     *
     * @param name Nombre del componente.
     */
    public Component(String name) {
        setName(name);
    }

    /**
     * Obtiene los datos personalizados asociados al componente.
     *
     * @return Datos personalizados o {@code null} si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados asociados al componente.
     *
     * @param customData Datos personalizados.
     * @throws PropertyConstraintException si los datos no son válidos.
     */
    public void setCustomData(@Nullable CustomData customData) {
        if (!isValidCustomData(customData)) {
            throw new PropertyConstraintException(customData, "Datos personalizados no válidos");
        }
        this.customData = customData;
    }

    /**
     * Verifica si los datos personalizados son válidos.
     *
     * @param customData Datos personalizados a validar.
     * @return {@code true} si son válidos o {@code false} en caso contrario.
     */
    private boolean isValidCustomData(@Nullable CustomData customData) {
        return customData == null || customData.validate();
    }

    /**
     * Añade datos personalizados al componente.
     *
     * @param customData Datos personalizados.
     * @return La instancia actualizada.
     */
    public Component withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el EVSE asociado al componente.
     *
     * @return EVSE o {@code null} si no está definido.
     */
    @Nullable
    public EVSE getEvse() {
        return evse;
    }

    /**
     * Establece el EVSE asociado al componente.
     *
     * @param evse EVSE asociado.
     * @throws PropertyConstraintException si el EVSE no es válido.
     */
    public void setEvse(@Nullable EVSE evse) {
        if (!isValidEvse(evse)) {
            throw new PropertyConstraintException(evse, "EVSE no válido");
        }
        this.evse = evse;
    }

    /**
     * Verifica si el EVSE es válido.
     *
     * @param evse EVSE a validar.
     * @return {@code true} si es válido o {@code false} en caso contrario.
     */
    private boolean isValidEvse(@Nullable EVSE evse) {
        return evse == null || evse.validate();
    }

    /**
     * Añade un EVSE al componente.
     *
     * @param evse EVSE asociado.
     * @return La instancia actualizada.
     */
    public Component withEvse(@Nullable EVSE evse) {
        setEvse(evse);
        return this;
    }

    /**
     * Obtiene el nombre del componente.
     *
     * @return Nombre del componente.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del componente.
     *
     * @param name Nombre del componente.
     * @throws PropertyConstraintException si el nombre no es válido.
     */
    public void setName(String name) {
        if (!isValidName(name)) {
            throw new PropertyConstraintException(name, "Nombre no válido");
        }
        this.name = name;
    }

    /**
     * Verifica si el nombre del componente es válido.
     *
     * @param name Nombre a validar.
     * @return {@code true} si es válido o {@code false} en caso contrario.
     */
    private boolean isValidName(String name) {
        return name != null && name.length() <= 50;
    }

    /**
     * Obtiene el nombre de la instancia del componente.
     *
     * @return Nombre de la instancia o {@code null} si no está definido.
     */
    @Nullable
    public String getInstance() {
        return instance;
    }

    /**
     * Establece el nombre de la instancia del componente.
     *
     * @param instance Nombre de la instancia.
     * @throws PropertyConstraintException si el nombre no es válido.
     */
    public void setInstance(@Nullable String instance) {
        if (!isValidInstance(instance)) {
            throw new PropertyConstraintException(instance, "Instancia no válida");
        }
        this.instance = instance;
    }

    /**
     * Verifica si el nombre de la instancia es válido.
     *
     * @param instance Nombre a validar.
     * @return {@code true} si es válido o {@code false} en caso contrario.
     */
    private boolean isValidInstance(@Nullable String instance) {
        return instance == null || instance.length() <= 50;
    }

    /**
     * Añade el nombre de la instancia al componente.
     *
     * @param instance Nombre de la instancia.
     * @return La instancia actualizada.
     */
    public Component withInstance(@Nullable String instance) {
        setInstance(instance);
        return this;
    }

    /**
     * Valida la configuración completa del componente.
     *
     * @return {@code true} si todos los campos son válidos o {@code false} en caso contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData)
                && isValidEvse(evse)
                && isValidName(name)
                && isValidInstance(instance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Component that = (Component) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(evse, that.evse)
                && Objects.equals(name, that.name)
                && Objects.equals(instance, that.instance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, evse, name, instance);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("evse", evse)
                .add("name", name)
                .add("instance", instance)
                .add("isValid", validate())
                .toString();
    }
}
