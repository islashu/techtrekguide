package com.example.complete.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter JwtAuthenticationFilter;

    private static final String[] WHITE_LIST_URL = {
             "/api/v1/auth/test",
            "/api/v1/auth/register",
            "/api/v1/auth/authenticate",
    };

    // API Authentication Configuration

    /* Once connections are allowed by CORS, modify this to change which api endpoints are allowed without authentication
     * Do note that the endpoints that are allowed without authentication will be accessible by anyone
     * Add to WHITE_LIST_URL to allow access to the endpoint without authentication
     * */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // This will control if the endpoints are protected or not
        return http
                .csrf()
                .disable()
                .authorizeHttpRequests(
                        // All req that match the white list will be allowed
                        req -> req.requestMatchers(WHITE_LIST_URL).permitAll()
                                .requestMatchers("/api/v1/auth/protected").authenticated()
                                // Remaining request that do not match the white list or is filtered above by other requestMatchers will be need to be authenticated
                                .anyRequest().authenticated()
                )
                // This lets the application know to make the authentication request every time after logging out
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(JwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(Customizer.withDefaults())
                .build();
    }

    // CORS configuration
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
