package com.eVolGreen.eVolGreen.Configurations.Web;

import com.eVolGreen.eVolGreen.Auth.Jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuración de seguridad de la aplicación eVolGreen.
 *
 * Esta clase define la política de seguridad, incluyendo autorización, autenticación,
 * y configuración de políticas para las sesiones. Permite accesos específicos para
 * ciertos endpoints y asegura que los demás recursos requieran autenticación.
 */
@Configuration
@EnableWebSecurity
public class WebAuthorization {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private AuthenticationProvider authProvider;

    /**
     * Configura la cadena de filtros de seguridad.
     *
     * @param http la configuración de HttpSecurity para la aplicación
     * @return una instancia de SecurityFilterChain configurada
     * @throws Exception si hay algún error de configuración
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Deshabilita CSRF ya que usaremos JWTs y no mantenemos sesión
                .csrf(AbstractHttpConfigurer::disable)
                // Configura las políticas de autorización para diferentes endpoints
                .authorizeHttpRequests(authorize -> authorize
                        // Permite el acceso a ciertos endpoints de registro y login sin autenticación
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/registerCompany").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/adminCompany/registerAdminCompany").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/adminCompany/updateAdminCompany&CompanyUser").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/verify-account").permitAll()
                        // Permite acceso a rutas de OCPP y de error sin autenticación
                        .requestMatchers("/ocpp/**").permitAll()
                        .requestMatchers("/error/**").permitAll()
                        // Requiere autenticación para cualquier otro endpoint
                        .anyRequest().authenticated()
                )
                // Configura el manejo de sesión como stateless, debido al uso de JWT
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Configura el proveedor de autenticación
                .authenticationProvider(authProvider)
                // Añade el filtro JWT antes del filtro de autenticación de usuario y contraseña
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
