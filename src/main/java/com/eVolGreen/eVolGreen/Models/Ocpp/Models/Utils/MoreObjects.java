package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Utils;

import java.time.ZonedDateTime;
import java.util.*;

/**
 * La clase MoreObjects proporciona utilidades estáticas para operar sobre objetos, permitiendo
 * comparaciones seguras ante posibles valores nulos, generación de cadenas de texto, y otros
 * métodos comunes como el cálculo de hash. Esta es una versión modificada y reducida del paquete
 * original de Guava, optimizada para reducir dependencias y agregar nuevas funcionalidades.
 * <p>
 * Incluye la opción de generar representaciones de cadenas de texto para objetos con controles adicionales
 * como mostrar valores sensibles en forma enmascarada o solo mostrar tamaños para colecciones y arrays.
 */
public final class MoreObjects {

    /** Constructor privado para prevenir instanciación de la clase utilitaria. */
    private MoreObjects() {
        throw new AssertionError("No se pueden crear instancias de esta clase");
    }

    /**
     * Verifica si dos objetos son iguales, gestionando casos donde ambos o uno de ellos sean nulos.
     *
     * @param a el primer objeto.
     * @param b el segundo objeto a comparar.
     * @return {@code true} si los objetos son iguales o ambos son nulos, de lo contrario {@code false}.
     */
    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }

    /**
     * Verifica si dos objetos son profundamente iguales, es decir, compara arrays de forma detallada.
     *
     * @param a el primer objeto.
     * @param b el segundo objeto a comparar.
     * @return {@code true} si ambos objetos o arrays son profundamente iguales, de lo contrario {@code false}.
     */
    public static boolean deepEquals(Object a, Object b) {
        if (a == b) {
            return true;
        } else if (a == null || b == null) {
            return false;
        } else {
            return Arrays.deepEquals(new Object[] {a}, new Object[] {b});
        }
    }

    /**
     * Calcula el hash de un objeto, devolviendo 0 si el objeto es nulo.
     *
     * @param o el objeto del cual se quiere obtener el hash.
     * @return el hash del objeto o 0 si es nulo.
     */
    public static int hashCode(Object o) {
        return o != null ? o.hashCode() : 0;
    }

    /**
     * Genera un hash code basado en una secuencia de valores de entrada.
     *
     * @param values los valores a ser utilizados para el cálculo del hash.
     * @return el hash calculado.
     */
    public static int hash(Object... values) {
        return Arrays.hashCode(values);
    }

    // Métodos adicionales para clonación de arrays

    /**
     * Crea una copia superficial de un array, devolviendo null si el array original es nulo.
     *
     * @param array el array a copiar.
     * @param <T> el tipo de elementos en el array.
     * @return una copia del array o null si el array original es nulo.
     */
    public static <T> T[] clone(T[] array) {
        return array == null ? null : Arrays.copyOf(array, array.length);
    }

    // Ejemplo de clonación de arrays de bytes, utilizado para arrays primitivos.
    public static byte[] clone(byte[] array) {
        return array == null ? null : Arrays.copyOf(array, array.length);
    }

    /**
     * Utilidad para generar representaciones de texto para objetos.
     *
     * @param self el objeto del cual se va a generar la cadena de texto.
     * @return una instancia de {@link ToStringHelper} para crear la representación de cadena.
     */
    public static ToStringHelper toStringHelper(Object self) {
        return new ToStringHelper(self);
    }

    /**
     * Clase interna para ayudar en la generación de representaciones de cadena de texto (toString) para objetos.
     * Ofrece la posibilidad de incluir o excluir valores nulos y manejar colecciones o arrays de manera detallada o resumida.
     */
    public static final class ToStringHelper {

        public static final int MAXIMUM_ARRAY_SIZE_TO_OUTPUT_DETAILS = 32;
        public static final String FIELD_NAME_LENGTH_POSTFIX = ".length";
        public static final String FIELD_NAME_SIZE_POSTFIX = ".size";
        public static final String SECURE_FIELD_VALUE_REPLACEMENT = "********";

        private final boolean outputFullDetails;
        private final ToStringHelperImpl helperImplementation;

        private ToStringHelper(ToStringHelperImpl helperImplementation, boolean outputFullDetails) {
            this.helperImplementation = helperImplementation;
            this.outputFullDetails = outputFullDetails;
        }

        // Constructor que recibe un objeto para inicializar el ayudante
        private ToStringHelper(Object self) {
            this(toStringHelper(self), false);
        }

        // Constructor para inicializar con el nombre de la clase
        private ToStringHelper(Class<?> clazz) {
            this(toStringHelper(clazz), false);
        }

        private ToStringHelper(String className) {
            this(toStringHelper(className), false);
        }

        // Constructor que permite especificar si se desea una salida detallada o no
        private ToStringHelper(Object self, boolean outputFullDetails) {
            this(toStringHelper(self), outputFullDetails);
        }

        // Métodos para añadir valores de campos al helper, con diferentes tipos

        public ToStringHelper add(String name, Object value) {
            helperImplementation.add(name, value);
            return this;
        }

        public ToStringHelper add(String name, boolean value) {
            helperImplementation.add(name, String.valueOf(value));
            return this;
        }

        public ToStringHelper add(String name, char value) {
            helperImplementation.add(name, String.valueOf(value));
            return this;
        }

        public ToStringHelper add(String name, double value) {
            helperImplementation.add(name, String.valueOf(value));
            return this;
        }

        public ToStringHelper add(String name, ZonedDateTime value) {
            helperImplementation.add(name, value);
            return this;
        }

        // Otros métodos para colecciones, arrays y manejo de valores seguros

        public ToStringHelper addSecure(String name, String value) {
            value = SECURE_FIELD_VALUE_REPLACEMENT;
            helperImplementation.add(name, value);
            return this;
        }

        public ToStringHelper addValue(Object value) {
            helperImplementation.addValue(value);
            return this;
        }

        // Método para obtener la representación final en cadena de texto
        @Override
        public String toString() {
            return helperImplementation.toString();
        }

        // Método de ayuda para crear instancias de ToStringHelperImpl
        static ToStringHelperImpl toStringHelper(Object self) {
            return new ToStringHelperImpl(self.getClass().getSimpleName());
        }

        static ToStringHelperImpl toStringHelper(Class<?> clazz) {
            return new ToStringHelperImpl(clazz.getSimpleName());
        }

        static ToStringHelperImpl toStringHelper(String className) {
            return new ToStringHelperImpl(className);
        }
    }
    /**
     * Clase que soporta la implementación de {@link MoreObjects#toStringHelper}.
     * Es responsable de construir la cadena de texto final, asegurando que se omitan valores nulos y manejando arrays y colecciones.
     */
    public static final class ToStringHelperImpl {

        private final String className;
        private final ValueHolder holderHead = new ValueHolder();
        private ValueHolder holderTail = holderHead;
        private boolean omitNullValues = false;

        /**
         * Constructor que recibe el nombre de la clase para representar en la cadena de texto.
         *
         * @param className nombre de la clase que se representará.
         */
        private ToStringHelperImpl(String className) {
            this.className = className;
        }

        /**
         * Configura la instancia para que ignore valores nulos al generar la cadena de texto.
         *
         * @return la instancia actual de ToStringHelperImpl.
         */
        public ToStringHelperImpl omitNullValues() {
            omitNullValues = true;
            return this;
        }

        /**
         * Añade un par nombre/valor al formato de salida en {@code nombre=valor}.
         *
         * @param name el nombre del campo.
         * @param value el valor del campo.
         * @return la instancia actual de ToStringHelperImpl.
         */
        public ToStringHelperImpl add(String name, Object value) {
            return addHolder(name, value);
        }

        public ToStringHelperImpl add(String name, boolean value) {
            return addHolder(name, String.valueOf(value));
        }

        public ToStringHelperImpl add(String name, char value) {
            return addHolder(name, String.valueOf(value));
        }

        public ToStringHelperImpl add(String name, double value) {
            return addHolder(name, String.valueOf(value));
        }

        public ToStringHelperImpl add(String name, ZonedDateTime value) {
            return addHolder(name, value);
        }

        /**
         * Añade un valor sin nombre a la salida.
         *
         * @param value el valor que se añadirá a la salida.
         * @return la instancia actual de ToStringHelperImpl.
         */
        public ToStringHelperImpl addValue(Object value) {
            return addHolder(value);
        }

        @Override
        public String toString() {
            boolean omitNullValuesSnapshot = omitNullValues;
            String nextSeparator = "";
            StringBuilder builder = new StringBuilder(32).append(className).append('{');
            for (ValueHolder valueHolder = holderHead.next; valueHolder != null; valueHolder = valueHolder.next) {
                Object value = valueHolder.value;
                if (!omitNullValuesSnapshot || value != null) {
                    builder.append(nextSeparator);
                    nextSeparator = ", ";

                    if (valueHolder.name != null) {
                        builder.append(valueHolder.name).append('=');
                    }
                    if (value != null && value.getClass().isArray()) {
                        Object[] objectArray = {value};
                        String arrayString = Arrays.deepToString(objectArray);
                        builder.append(arrayString, 1, arrayString.length() - 1);
                    } else {
                        builder.append(value);
                    }
                }
            }
            return builder.append('}').toString();
        }

        // Métodos privados para añadir valores de campos al holder interno.

        private ValueHolder addHolder() {
            ValueHolder valueHolder = new ValueHolder();
            holderTail = holderTail.next = valueHolder;
            return valueHolder;
        }

        private ToStringHelperImpl addHolder(Object value) {
            ValueHolder valueHolder = addHolder();
            valueHolder.value = value;
            return this;
        }

        private ToStringHelperImpl addHolder(String name, Object value) {
            ValueHolder valueHolder = addHolder();
            valueHolder.value = value;
            valueHolder.name = name;
            return this;
        }

        private ToStringHelperImpl addHolder(String name, ZonedDateTime value) {
            ValueHolder valueHolder = addHolder();
            valueHolder.value = "\"" + SugarUtil.zonedDateTimeToString(value) + "\"";
            valueHolder.name = name;
            return this;
        }

        /**
         * Clase interna para mantener pares nombre/valor.
         */
        private static final class ValueHolder {
            String name;
            Object value;
            ValueHolder next;
        }
    }
}
