package com.example.complete.controller;

import com.example.complete.model.auth.AuthenticationRequest;
import com.example.complete.model.auth.AuthenticationResponse;
import com.example.complete.model.auth.RegisterRequest;
import com.example.complete.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/protected")
    public ResponseEntity<String> protectedRequest() {
        return ResponseEntity.ok("You have gain access to the protected secrets!");
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        System.out.println("Authenticating");
        return ResponseEntity.ok(service.authenticate(request));
    }

}
