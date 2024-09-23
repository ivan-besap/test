package com.eVolGreen.eVolGreen.Models.Ocpp.Models.Utils;

/**
 * Utilidades para las clases de modelo. Usado para validar valores.
 */
public abstract class ModelUtil {

    /**
     * Verifica si un valor está en una lista de valores.
     *
     * @param needle valor que queremos buscar.
     * @param hayStack lista de valores en la que buscamos.
     * @return true si el valor fue encontrado en la lista.
     */
    public static boolean isAmong(String needle, String... hayStack) {
        boolean found = false;
        if (hayStack != null) {
            for (String straw : hayStack) {
                if (found = isNullOrEqual(straw, needle)) {
                    break;
                }
            }
        }
        return found;
    }

    /**
     * Compara dos valores.
     *
     * @param object1 valor derecho a comparar.
     * @param object2 valor izquierdo a comparar.
     * @return Ambos valores son nulos o iguales.
     */
    private static boolean isNullOrEqual(Object object1, Object object2) {
        if (object1 == null && object2 == null) {
            return true;
        } else return object1 != null && object1.equals(object2);
    }

    /**
     * Verifica si una cadena de texto excede un largo máximo.
     *
     * @param input la cadena de texto a verificar.
     * @param maxLength el largo máximo permitido.
     * @return true si el largo de la cadena no excede el límite.
     */
    public static boolean validate(String input, int maxLength) {
        return input != null && input.length() <= maxLength;
    }
}