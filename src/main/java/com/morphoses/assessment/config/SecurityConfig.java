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
    http.authorizeHttpRequests(
            auth ->
                auth.requestMatchers("/h2-console/**")
                    .permitAll() // Allow access to H2 console
                    .requestMatchers("/v3/api-docs/**")
                    .permitAll() // Allow access to OpenAPI docs
                    .requestMatchers("/swagger-ui/**")
                    .permitAll() // Allow access to Swagger UI
                    .anyRequest()
                    .authenticated())
        .csrf()
        .disable() // Disable CSRF for H2 console
        .headers()
        .frameOptions()
        .disable(); // Allow H2 console to be accessed in a frame
    return http.build();
  }
}
