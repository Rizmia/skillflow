// src/main/java/com/example/cookingapp/config/SecurityConfig.java
package com.example.cookingapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // enable CORS support
                .cors().and()
                // disable CSRF for simplicity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // allow all API calls
                        .requestMatchers("/api/courses", "/api/courses/**").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }

    // define a global CORS policy
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        // allow your React app’s origin (or use "*")
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        // allow all standard methods
        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","PATCH","OPTIONS"));
        // allow any header
        config.setAllowedHeaders(List.of("*"));
        // allow credentials if you ever need them
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // apply this CORS config to all /api/** endpoints
        source.registerCorsConfiguration("/api/**", config);
        return source;
    }
}
