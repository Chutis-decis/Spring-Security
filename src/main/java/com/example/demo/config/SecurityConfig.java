package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    //Servira cuando quieramos mandar una peticion sin security
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {http
            .authorizeHttpRequests(auth -> auth
                    .anyRequest()
                    .permitAll()
            );
        return http.build();
    }
}
