package com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp2_0.Models.Diagnostics.Requests.Utils;

import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Exceptions.PropertyConstraintException;
import com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities.MoreObjects;
import com.eVolGreen.eVolGreen.Models.Ocpp.Ocpp1_6.Models.CustomData;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * Representa una variable referenciada en un componente, incluyendo su nombre e instancia.
 *
 * <p>Esta clase se utiliza en el contexto de OCPP 2.0.1 para identificar atributos específicos de
 * los componentes del sistema.
 */
public class Variable {

    /** Datos personalizados asociados a la variable. */
    @Nullable
    private CustomData customData;

    /**
     * Nombre de la variable. Se recomienda usar nombres estandarizados siempre que sea posible.
     * Insensible a mayúsculas/minúsculas y preferiblemente en Camel Case.
     */
    private String name;

    /**
     * Nombre de la instancia de la variable en caso de que exista en múltiples instancias. Insensible
     * a mayúsculas/minúsculas y preferiblemente en Camel Case.
     */
    @Nullable
    private String instance;

    /**
     * Constructor principal para la clase.
     *
     * @param name Nombre de la variable.
     */
    public Variable(String name) {
        setName(name);
    }

    /**
     * Obtiene los datos personalizados asociados a la variable.
     *
     * @return Datos personalizados o {@code null} si no están definidos.
     */
    @Nullable
    public CustomData getCustomData() {
        return customData;
    }

    /**
     * Establece los datos personalizados asociados a la variable.
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
     * Añade datos personalizados a la variable.
     *
     * @param customData Datos personalizados.
     * @return La instancia actualizada.
     */
    public Variable withCustomData(@Nullable CustomData customData) {
        setCustomData(customData);
        return this;
    }

    /**
     * Obtiene el nombre de la variable.
     *
     * @return Nombre de la variable.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de la variable.
     *
     * @param name Nombre de la variable.
     * @throws PropertyConstraintException si el nombre no es válido.
     */
    public void setName(String name) {
        if (!isValidName(name)) {
            throw new PropertyConstraintException(name, "Nombre no válido");
        }
        this.name = name;
    }

    /**
     * Verifica si el nombre de la variable es válido.
     *
     * @param name Nombre a validar.
     * @return {@code true} si es válido o {@code false} en caso contrario.
     */
    private boolean isValidName(String name) {
        return name != null && name.length() <= 50;
    }

    /**
     * Obtiene el nombre de la instancia de la variable.
     *
     * @return Nombre de la instancia o {@code null} si no está definido.
     */
    @Nullable
    public String getInstance() {
        return instance;
    }

    /**
     * Establece el nombre de la instancia de la variable.
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
     * Añade el nombre de la instancia de la variable.
     *
     * @param instance Nombre de la instancia.
     * @return La instancia actualizada.
     */
    public Variable withInstance(@Nullable String instance) {
        setInstance(instance);
        return this;
    }

    /**
     * Valida la configuración completa de la variable.
     *
     * @return {@code true} si todos los campos son válidos o {@code false} en caso contrario.
     */
    public boolean validate() {
        return isValidCustomData(customData) && isValidName(name) && isValidInstance(instance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Variable that = (Variable) o;
        return Objects.equals(customData, that.customData)
                && Objects.equals(name, that.name)
                && Objects.equals(instance, that.instance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customData, name, instance);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customData", customData)
                .add("name", name)
                .add("instance", instance)
                .add("isValid", validate())
                .toString();
    }
}
