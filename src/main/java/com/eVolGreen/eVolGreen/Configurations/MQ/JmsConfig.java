//package com.eVolGreen.eVolGreen.Configurations.MQ;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.annotation.EnableJms;
//import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
//import org.springframework.jms.config.JmsListenerContainerFactory;
//import org.springframework.jms.connection.CachingConnectionFactory;
//import org.springframework.jms.core.JmsTemplate;
//
///**
// * Configuración de JMS (Java Message Service) utilizando ActiveMQ.
// * Esta clase configura la conexión a un broker de Amazon MQ (ActiveMQ) para el envío y recepción de mensajes.
// * <p>
// * Incluye la creación de las conexiones, la configuración del manejo de concurrencia,
// * y el uso de un template (JmsTemplate) para enviar mensajes de manera programática.
// * </p>
// *
// * <b>Ejemplo de uso:</b>
// * <pre>
// *     {@code
// *     // Envío de un mensaje utilizando el JmsTemplate configurado
// *     jmsTemplate.convertAndSend("mi_cola", "Mensaje de prueba");
// *     }
// * </pre>
// *
// * <b>Configuración en application.properties:</b>
// * <pre>
// *     spring.activemq.broker-url=tcp://localhost:61616
// *     spring.activemq.user=admin
// *     spring.activemq.password=admin
// * </pre>
// */
//@Configuration
//@EnableJms
//public class JmsConfig {
//
//    /**
//     * URL del broker ActiveMQ.
//     * Esta propiedad se define en el archivo de configuración `application.properties`
//     * o en variables de entorno.
//     */
//    @Value("${spring.activemq.broker-url}")
//    private String brokerUrl;
//
//    /**
//     * Nombre de usuario para conectarse al broker ActiveMQ.
//     * Esta propiedad se inyecta desde el archivo de configuración o variables de entorno.
//     */
//    @Value("${spring.activemq.user}")
//    private String userName;
//
//    /**
//     * Contraseña para conectarse al broker ActiveMQ.
//     * Se inyecta desde el archivo de configuración o variables de entorno.
//     */
//    @Value("${spring.activemq.password}")
//    private String password;
//
//    /**
//     * Crea una instancia de `ActiveMQConnectionFactory` con los detalles del broker, nombre de usuario y contraseña.
//     * Este método configura la conexión a ActiveMQ para permitir el envío y recepción de mensajes JMS.
//     *
//     * @return una instancia configurada de `ActiveMQConnectionFactory`.
//     *
//     * <b>Ejemplo de uso:</b>
//     * <pre>
//     *     {@code
//     *     ActiveMQConnectionFactory factory = connectionFactory();
//     *     }
//     * </pre>
//     */
//    @Bean
//    public ActiveMQConnectionFactory connectionFactory() {
//        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
//        factory.setBrokerURL(brokerUrl);
//        factory.setUserName(userName);
//        factory.setPassword(password);
//        return factory;
//    }
//
//    /**
//     * Crea una `CachingConnectionFactory` para mejorar el rendimiento.
//     * Esta fábrica de conexiones con caché envuelve la `ActiveMQConnectionFactory` para
//     * evitar la creación repetida de conexiones y mejorar la eficiencia.
//     *
//     * @return una instancia de `CachingConnectionFactory`.
//     */
//    @Bean
//    public CachingConnectionFactory cachingConnectionFactory() {
//        return new CachingConnectionFactory(connectionFactory());
//    }
//
//    /**
//     * Crea un `JmsTemplate` para enviar mensajes a través de ActiveMQ.
//     * El `JmsTemplate` simplifica el envío de mensajes JMS de manera programática.
//     * <p>
//     * Es útil cuando deseas enviar mensajes de forma sencilla desde tu aplicación sin necesidad de gestionar directamente la conexión.
//     * </p>
//     *
//     * @return una instancia configurada de `JmsTemplate`.
//     *
//     * <b>Ejemplo de uso:</b>
//     * <pre>
//     *     {@code
//     *     jmsTemplate.convertAndSend("mi_cola", "Mensaje de prueba");
//     *     }
//     * </pre>
//     */
//    @Bean
//    public JmsTemplate jmsTemplate() {
//        return new JmsTemplate(cachingConnectionFactory());
//    }
//
//    /**
//     * Crea una fábrica de contenedores para listeners JMS.
//     * Esta fábrica gestiona el manejo de los mensajes entrantes de manera asíncrona
//     * y puede configurarse para manejar mensajes con varios hilos simultáneos.
//     *
//     * @return una instancia configurada de `JmsListenerContainerFactory`.
//     *
//     * <b>Uso en un Listener:</b>
//     * <pre>
//     *     {@code
//     *     @JmsListener(destination = "mi_cola")
//     *     public void escuchaMensajes(String mensaje) {
//     *         System.out.println("Mensaje recibido: " + mensaje);
//     *     }
//     *     }
//     * </pre>
//     */
//    @Bean
//    public JmsListenerContainerFactory<?> jmsListenerContainerFactory() {
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        factory.setConnectionFactory(cachingConnectionFactory());
//        factory.setConcurrency("1-1"); // Establece la concurrencia para manejar un solo mensaje a la vez
//        return factory;
//    }
//}
