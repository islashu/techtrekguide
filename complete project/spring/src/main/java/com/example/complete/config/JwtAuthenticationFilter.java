package com.example.complete.config;

import com.example.complete.repository.TokenRepository;
import com.example.complete.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Returns 401, 403 immediately if user is trying to access a protected endpoint without a token
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Authorization header = "Bearer eyJhbGciOiJIUzI1NiJ9", JWT token is everything after "Bearer "
        jwt = authorizationHeader.substring(7);

        // Using the service, we get the username from jwt token
        userEmail = jwtService.extractUsername(jwt);

        /* FROM HERE ONWARDS, THE IMPLEMENTATION DETAILS OF CHECKING IF A TOKEN IS VALID IS UP TO YOU*/

        // Get the user details using the username, if there is no email reject the request.
        // Security Context Holder is another abstract class/concept, it stores the authentication details of the current user.
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // When you use loadUserByUsername, you pass in any property but you must configure the userDetailsService to get the user information so that the userDetailService can convert the property to a UserDetails object
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            // Check if the token exist, if exist, check if the token is not expired and not revoked, what if there are multiple tokens?
            var isTokenValid = tokenRepository.findByToken(jwt)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);

            // For a token to be valid, we need to check 2 things, 1) the token is not expired or revoked and 2) the details in the token is correct
            if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
                // Create a spring security specific token that contains the user details and the authorities of the user and set it to the security context holder
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                // Without an authentication token, spring security will reject the request, after travelling to the end of the filter
                // Which i believe spring security will just carry out the same dao authentication steps again using the auth token
                // This step is exactly the same as the authenticationManager.authenticate() step
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
