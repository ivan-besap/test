//package com.eVolGreen.eVolGreen.Configurations;
//
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.WebAttributes;
//import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
//
//
//@EnableWebSecurity
//@Configuration
//public class WebAuthorization {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.
//                authorizeRequests()
//                .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
//
//                .requestMatchers("/web/**").permitAll()
//
//
//                .requestMatchers(HttpMethod.GET, "/api/products", "/api/products", "/api/products/{category}","/api/comments/product").permitAll()
//
//                .requestMatchers(HttpMethod.POST, "/api/comments/add", "/api/answers/add", "/api/punctuations/add","/api/adress/add","/api/purchase/purchaseOrder").hasAuthority("CLIENT")
//
//                .requestMatchers(HttpMethod.GET, "/api/person/all").hasAuthority("ADMIN")
//
//                .requestMatchers(HttpMethod.GET, "/api/person/{id}","/api/purchase/history","/api/purchase/{id}","/api/ticket","/api/person/current").hasAuthority("CLIENT")
//
//                .requestMatchers(HttpMethod.PATCH, "/api/comments/update", "/api/answers/update","/api/adress/delete").hasAuthority("CLIENT")
//
//                .requestMatchers(HttpMethod.DELETE, "/api/comments/delete", "/api/answers/delete").hasAuthority("CLIENT")
//
//                .requestMatchers(HttpMethod.POST, "/api/products/add").hasAuthority("ADMIN")
//
//                .requestMatchers(HttpMethod.GET, "/api/person/all").hasAuthority("ADMIN")
//
//                .requestMatchers(HttpMethod.PATCH, "/api/products/stock", "/api/products/discount", "/api/products/price").hasAuthority("ADMIN")
//
//                .requestMatchers(HttpMethod.DELETE, "/api/products/{id}").hasAuthority("ADMIN")
//                .requestMatchers("/web/**","/script/**","/assets/**").permitAll();
//
//
//        http.formLogin()
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .loginPage("/api/login");
//
//        http.logout().logoutUrl("/api/logout");
//
//        // turn off checking for CSRF tokens
//        http.csrf().disable();
//
//        //disabling frameOptions so h2-console can be accessed
//        http.headers().frameOptions().disable();
//
//        // if user is not authenticated, just send an authentication failure response
//        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
//
//        // if login is successful, just clear the flags asking for authentication
//        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes((HttpServletRequest) req));
//
//        // if login fails, just send an authentication failure response
//        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
//
//        // if logout is successful, just send a success response
//        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
//
//        return http.build();
//    }
//
//    private void clearAuthenticationAttributes(HttpServletRequest request) {
//
//        HttpSession session = request.getSession(false);
//
//        if (session != null) {
//
//            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
//
//        }
//    }
//}
