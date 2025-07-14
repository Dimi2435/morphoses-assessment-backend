package com.morphoses.assessment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf()
        .disable() // Disable CSRF protection
        .authorizeHttpRequests(
            auth ->
                auth.anyRequest()
                    .permitAll() // Allow access to all endpoints without authentication
            )
        .headers()
        .frameOptions()
        .disable(); // Allow H2 console to be accessed in a frame

    return http.build();
  }
}
