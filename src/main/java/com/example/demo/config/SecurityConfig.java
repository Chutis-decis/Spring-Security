package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    //Servira cuando quieramos autenticar todo a un usuario
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/*").hasAnyRole("ADMIN", "CUSTOMER") // tanto el cliente como el admin pueden ver los productos
                        .requestMatchers(HttpMethod.GET, "/api/**").hasRole("ADMIN") //El admin podra vizualizar api/**
                        .requestMatchers(HttpMethod.POST, "/**").hasRole("ADMIN") //solo el ADMIN podra poner nuevos registros
                        .requestMatchers(HttpMethod.PUT).hasRole("ADMIN") //solamente el admin podrá modificar el menu
                        .anyRequest().authenticated() // Todas las solicitudes deben estar autenticadas
                )
                .csrf(csrf -> csrf.disable()) // Deshabilita CSRF
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Habilita CORS
                .httpBasic(Customizer.withDefaults()); // Habilita la autenticación básica (usuario y contraseña)

        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder(){ //metodo que ayuda a que el password no este "muy visible"
        return new BCryptPasswordEncoder();
    }

    // Configuración de CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // Permite solicitudes desde este origen
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Métodos permitidos
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Cabeceras permitidas
        configuration.setAllowCredentials(true); // Permite el envío de credenciales (cookies, tokens)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Aplica la configuración a todas las rutas
        return source;
    }
}

/* CSRF (Cross-Site Request Forgery) -> es un ataque que aprovecha una vulnerabilidad
y falsifica una peticion web haciendose pasar por un user autorizado y a la hora
de cambiar (PUT) un registro este no marque un status 401 */
