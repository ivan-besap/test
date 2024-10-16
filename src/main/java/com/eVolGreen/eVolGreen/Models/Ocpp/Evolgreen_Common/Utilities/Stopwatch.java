package com.eVolGreen.eVolGreen.Models.Ocpp.Evolgreen_Common.Utilities;

import static java.util.concurrent.TimeUnit.*;

import java.time.Duration;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * La clase {@code Stopwatch} proporciona una forma de medir el tiempo transcurrido
 * con alta precisión en diferentes unidades de tiempo. Esta implementación es una
 * versión optimizada y simplificada para evitar dependencias adicionales innecesarias
 * (como Guava), proporcionando solo las funcionalidades esenciales.
 * <p>
 * Se puede crear un cronómetro sin iniciar ({@link #createUnstarted()}) o iniciarlo
 * directamente al crear la instancia ({@link #createStarted()}). El tiempo transcurrido
 * se puede consultar en diferentes unidades de tiempo, y es posible reiniciar o detener
 * el cronómetro en cualquier momento.
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     Stopwatch stopwatch = Stopwatch.createStarted();
 *     // Hacer alguna operación
 *     stopwatch.stop();
 *     System.out.println("Tiempo transcurrido: " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms");
 * </pre>
 */
public final class Stopwatch {
    private boolean isRunning;
    private long elapsedNanos;
    private long startTick;
    private Ticker ticker;

    /**
     * Constructor privado que utiliza {@link System#nanoTime} como fuente de tiempo por defecto.
     * Utilizado en métodos estáticos para crear instancias de la clase.
     */
    private Stopwatch() {
        this(System::nanoTime);
    }

    /**
     * Constructor que permite definir una fuente de tiempo personalizada mediante la interfaz {@link Ticker}.
     *
     * @param ticker la implementación personalizada de la fuente de tiempo.
     */
    public Stopwatch(Ticker ticker) {
        this.ticker = ticker;
    }

    /**
     * Interfaz para implementar una fuente de tiempo personalizada que puede ser utilizada
     * por el cronómetro. El método {@code read()} debe devolver la cantidad de nanosegundos
     * transcurridos desde una referencia fija.
     */
    public interface Ticker {
        long read();
    }

    /**
     * Crea una instancia de {@code Stopwatch} sin iniciar el cronómetro. El cronómetro debe ser
     * iniciado manualmente con {@link #start()}.
     *
     * @return una instancia de {@code Stopwatch} sin iniciar.
     */
    public static Stopwatch createUnstarted() {
        return new Stopwatch();
    }

    /**
     * Crea e inicia una instancia de {@code Stopwatch}. El cronómetro comienza a contar inmediatamente.
     *
     * @return una instancia de {@code Stopwatch} iniciada.
     */
    public static Stopwatch createStarted() {
        return new Stopwatch().start();
    }

    /**
     * Verifica si el cronómetro está corriendo en ese momento.
     *
     * @return {@code true} si el cronómetro está en funcionamiento, {@code false} en caso contrario.
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Inicia el cronómetro. Si el cronómetro ya está corriendo, se lanzará una excepción.
     *
     * @return esta misma instancia de {@code Stopwatch} para permitir llamadas encadenadas.
     * @throws IllegalStateException si el cronómetro ya está en funcionamiento.
     */
    public Stopwatch start() {
        checkState(false);
        isRunning = true;
        startTick = ticker.read();
        return this;
    }

    /**
     * Detiene el cronómetro y guarda el tiempo transcurrido hasta ese momento.
     *
     * @return esta misma instancia de {@code Stopwatch} para permitir llamadas encadenadas.
     * @throws IllegalStateException si el cronómetro ya estaba detenido.
     */
    public Stopwatch stop() {
        checkState(true);
        long tick = ticker.read();
        isRunning = false;
        elapsedNanos += tick - startTick;
        return this;
    }

    /**
     * Restablece el cronómetro a cero y lo deja en estado detenido.
     * Se debe volver a llamar a {@link #start()} para comenzar a contar de nuevo.
     *
     * @return esta misma instancia de {@code Stopwatch} para permitir llamadas encadenadas.
     */
    public Stopwatch reset() {
        elapsedNanos = 0;
        isRunning = false;
        return this;
    }

    // Método elapsedNanos
    private long elapsedNanos() {
        // Si está corriendo, calculamos el tiempo transcurrido más el acumulado
        return isRunning ? ticker.read() - startTick + elapsedNanos : elapsedNanos;
    }

    // Método elapsed
    public long elapsed(TimeUnit desiredUnit) {
        // Verifica que el TimeUnit pasado no sea nulo
        if (desiredUnit == null) {
            throw new IllegalArgumentException("Unidad de tiempo no puede ser nula");
        }
        // Convierte el tiempo transcurrido a la unidad deseada
        return desiredUnit.convert(elapsedNanos(), NANOSECONDS);
    }

    /**
     * Devuelve el tiempo transcurrido actual como un objeto {@link Duration}, sin perder precisión.
     *
     * @return la duración del tiempo transcurrido.
     */
    public Duration elapsed() {
        return Duration.ofNanos(elapsedNanos());
    }

    /**
     * Devuelve una representación en cadena del tiempo transcurrido actual, redondeando a 4 dígitos significativos.
     *
     * @return una cadena que representa el tiempo transcurrido.
     */
    @Override
    public String toString() {
        long nanos = elapsedNanos();
        TimeUnit unit = chooseUnit(nanos);
        double value = (double) nanos / NANOSECONDS.convert(1, unit);
        return formatCompact4Digits(value) + " " + abbreviate(unit);
    }

    // Métodos privados para gestionar el estado y conversión de tiempo

    private void checkState(boolean stateExpectation) {
        if (isRunning != stateExpectation) {
            throw new IllegalStateException("Este cronómetro ya está " + (isRunning ? "corriendo" : "detenido"));
        }
    }

    private static TimeUnit chooseUnit(long nanos) {
        if (DAYS.convert(nanos, NANOSECONDS) > 0) return DAYS;
        if (HOURS.convert(nanos, NANOSECONDS) > 0) return HOURS;
        if (MINUTES.convert(nanos, NANOSECONDS) > 0) return MINUTES;
        if (SECONDS.convert(nanos, NANOSECONDS) > 0) return SECONDS;
        if (MILLISECONDS.convert(nanos, NANOSECONDS) > 0) return MILLISECONDS;
        if (MICROSECONDS.convert(nanos, NANOSECONDS) > 0) return MICROSECONDS;
        return NANOSECONDS;
    }

    private static String abbreviate(TimeUnit unit) {
        switch (unit) {
            case NANOSECONDS: return "ns";
            case MICROSECONDS: return "\u03bcs"; // μs
            case MILLISECONDS: return "ms";
            case SECONDS: return "s";
            case MINUTES: return "min";
            case HOURS: return "h";
            case DAYS: return "d";
            default: throw new AssertionError();
        }
    }

    private static String formatCompact4Digits(double value) {
        return String.format(Locale.ROOT, "%.4g", value);
    }
}
