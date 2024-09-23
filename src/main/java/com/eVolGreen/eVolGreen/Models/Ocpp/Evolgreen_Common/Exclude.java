package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * La anotación {@code @Exclude} se utiliza para marcar campos de una clase que deben ser
 * excluidos de ciertos procesos automáticos, como la serialización, clonación, o el logueo
 * de datos sensibles.
 * <p>
 * Esta anotación es útil en situaciones donde no deseamos que ciertos atributos de un objeto
 * sean considerados por librerías externas como Gson o Jackson durante la serialización,
 * o para evitar que se registren datos privados en los logs.
 * </p>
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * public class Usuario {
 *     private String nombre;
 *     private String correo;
 *
 *     @Exclude
 *     private String contrasena;
 * }
 * }
 * </pre>
 * <p>
 * En este ejemplo, el campo {@code contrasena} será excluido del proceso de serialización.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME) // La anotación estará disponible en tiempo de ejecución
@Target(ElementType.FIELD) // Solo se puede aplicar a campos (atributos) de una clase
public @interface Exclude {
    // No se requieren parámetros, ya que simplemente marca el campo a excluir
}
