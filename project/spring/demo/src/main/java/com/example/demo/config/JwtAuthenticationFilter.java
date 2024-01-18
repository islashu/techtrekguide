package com.example.demo.config;

import com.example.demo.repository.TokenRepository;
import com.example.demo.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


// To run everything we receive a request from the client
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserDetailsService userDetailsService;

    // We can intercept every request and response like in express.js, this will be at the front of the backend system
    // In this step, the authentication of the JWT token is done here

    // The filter only triggers if this is a request to an api end point that is not part of the white_list (aka part of the request that explicitly requires authentication)
    // There are two possibilities, user has a token or user does not have a token
    // If user has no token but is trying to access a protected endpoint, we will reject the request
    // If user has a token, we will check if the token is valid, if it is valid, we will authenticate the user and allow the request to go through
    // This filter checks if the token is valid.
    // Remember a token can be invalid even if the user has signed in, e.g. a tempered token.
    // Every request will go through this authentication process again and again, so we need to make sure that the token is valid every time.
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
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            System.out.println("context");
            System.out.println(SecurityContextHolder.getContext());
        }
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // When you use loadUserByUsername, you pass in any property but you must configure the userDetailsService to get the user information so that the userDetailService can convert the property to a UserDetails object
            System.out.println("userEmail: " + userEmail);
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            System.out.println("userDetails username: " + userDetails.getUsername());
            System.out.println("userDetails password: " + userDetails.getPassword());
            System.out.println("userDetails authorities: " + userDetails.getAuthorities());
            System.out.println("userDetails accountNonExpired: " + userDetails.isAccountNonExpired());
            System.out.println("userDetails accountNonLocked: " + userDetails.isAccountNonLocked());
            System.out.println("userDetails credentialsNonExpired: " + userDetails.isCredentialsNonExpired());
            System.out.println("userDetails enabled: " + userDetails.isEnabled());
            // Check if the token exist, if exist, check if the token is not expired and not revoked, what if there are multiple tokens?
            var isTokenValid = tokenRepository.findByToken(jwt)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);

            System.out.println("isTokenValid: " + isTokenValid);

            // For a token to be valid, we need to check 2 things, 1) the token is not expired or revoked and 2) the details in the token is correct
            if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
                // Create a spring security specific token that contains the user details and the authorities of the user and set it to the security context holder
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                System.out.println("confirm authentication");
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
