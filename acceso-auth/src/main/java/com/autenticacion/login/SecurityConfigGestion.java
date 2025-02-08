package com.autenticacion.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigGestion {

    @Bean
    @Order(1) // Mayor prioridad
    public SecurityFilterChain gestionSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests()
                .antMatchers("/usuarios/**").authenticated()
                .anyRequest().permitAll()
            .and()
            .httpBasic();

        return http.build();
    }
}