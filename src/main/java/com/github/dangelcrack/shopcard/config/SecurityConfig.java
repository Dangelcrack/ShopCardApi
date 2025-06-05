package com.github.dangelcrack.shopcard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración de seguridad para la aplicación.
 * Define las reglas de autenticación y autorización, así como la protección contra CSRF.
 */
@Configuration
@EnableWebSecurity // Habilita la seguridad web de Spring Security
public class SecurityConfig {

    /**
     * Configura la cadena de filtros de seguridad para las solicitudes HTTP.
     *
     * @param http Objeto HttpSecurity para configurar las reglas de seguridad
     * @return SecurityFilterChain configurado
     * @throws Exception si ocurre un error durante la configuración
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Permite todas las solicitudes sin requerir autenticación
                        .anyRequest().permitAll()
                )
                // Deshabilita la protección CSRF (útil para APIs stateless, pero requiere consideraciones de seguridad)
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}