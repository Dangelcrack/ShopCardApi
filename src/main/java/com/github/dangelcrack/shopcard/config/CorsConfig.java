package com.github.dangelcrack.shopcard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración de CORS (Cross-Origin Resource Sharing) para la aplicación.
 * Define los orígenes, métodos y cabeceras permitidos para las solicitudes entre dominios.
 */
@Configuration
public class CorsConfig {

    /**
     * Configura y retorna un WebMvcConfigurer personalizado para manejar CORS.
     *
     * @return WebMvcConfigurer con la configuración CORS aplicada
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * Configura los mapeos CORS para la aplicación.
             *
             * @param registry el registro donde se añaden las configuraciones CORS
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Aplica a todos los endpoints
                        .allowedOriginPatterns(  // Patrones de origen permitidos
                                "https://shopcardapplication.web.app",  // Frontend en producción
                                "http://localhost:*",  // Desarrollo local con cualquier puerto
                                "https://*.ngrok-free.app"  // Túneles ngrok para pruebas
                        )
                        .allowedMethods("*")  // Todos los métodos HTTP permitidos
                        .allowedHeaders("*")  // Todas las cabeceras permitidas
                        .exposedHeaders("Authorization", "Content-Disposition")  // Cabeceras expuestas al cliente
                        .allowCredentials(true)  // Permite credenciales (cookies, auth)
                        .maxAge(3600);  // Cache de opciones preflight por 1 hora
            }
        };
    }
}