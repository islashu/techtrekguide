package com.example.demo.config;

import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository repository;

    @Bean
    // Another abstract class/concept that must be implemented to be part of the spring security framework,
    public UserDetailsService userDetailsService() {
        return username -> repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    // Another abstract class/concept that must be implemented to be part of the spring security framework, this contains the logic to authenticate the user. Different authentication providers have different ways of authenticating the user. DaoAuthenticationProvider is one of those ways.
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    // Another abstract class/concept that must be implemented to be part of the spring security framework, a manager that decides which authentication provider (aka the logic to authenticate a token) to use to authenticate the user.
    // An authentication manager can contain multiple authentication providers, and it will try to authenticate the user with each authentication provider until one of them returns a successful authentication.
    // No need to pass in the authenticationConfiguration as it will default to provider manager
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    // Another abstract class/concept that must be implemented to be part of the spring security framework, a password encoder must be supplied to an authentication provider to encode the password before comparing it with the password in the database.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
