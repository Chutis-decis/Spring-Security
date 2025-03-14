package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    //Servira cuando quieramos autenticar todo a un usuario
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest()
                        .authenticated() // Todas las solicitudes deben estar autenticadas
                )
                .csrf(csrf -> csrf.disable()) //Deshabilita CSRF
                .httpBasic(Customizer.withDefaults()); // Habilita la autenticación básica (usuario y contraseña)
        return http.build();
    }
}

/* CSRF (Cross-Site Request Forgery) -> es un ataque que aprovecha una vulnerabilidad
y falsifica una peticion web haciendose pasar por un user autorizado y a la hora
de cambiar (PUT) un registro este no marque un status 401 */
