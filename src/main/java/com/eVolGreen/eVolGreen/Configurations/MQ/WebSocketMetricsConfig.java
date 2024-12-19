package com.eVolGreen.eVolGreen.Configurations.MQ;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class WebSocketMetricsConfig {

    @Autowired
    private MeterRegistry meterRegistry;
    private final AtomicInteger activeConnections = new AtomicInteger(0);
    private Counter messagesSentCounter;
    private Counter messagesReceivedCounter;

    public WebSocketMetricsConfig(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    private void init() {
        // Registrar métrica de conexiones activas
        Gauge.builder("websocket.connections.active", activeConnections, AtomicInteger::get)
                .description("Number of active WebSocket connections")
                .register(meterRegistry);

        // Registrar contador de mensajes enviados
        messagesSentCounter = Counter.builder("websocket.messages.sent.total")
                .description("Total number of WebSocket messages sent")
                .register(meterRegistry);

        // Registrar contador de mensajes recibidos
        messagesReceivedCounter = Counter.builder("websocket.messages.received.total")
                .description("Total number of WebSocket messages received")
                .register(meterRegistry);
    }

    public void incrementActiveConnections() {
        activeConnections.incrementAndGet();
    }

    public void decrementActiveConnections() {
        activeConnections.decrementAndGet();
    }

    public void incrementMessagesSent() {
        messagesSentCounter.increment();
    }

    public void incrementMessagesReceived() {
        messagesReceivedCounter.increment();
    }
}
