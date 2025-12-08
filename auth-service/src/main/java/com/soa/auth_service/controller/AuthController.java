package com.soa.auth_service.controller;

import com.soa.auth_service.dto.AuthResponse;
import com.soa.auth_service.dto.LoginRequest;
import com.soa.auth_service.dto.RegisterRequest;
import com.soa.auth_service.entity.User;
import com.soa.auth_service.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
        try {
            User saved = service.register(req);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        try {
            String token = service.login(req);
            AuthResponse resp = AuthResponse.builder()
                    .accessToken(token)
                    .tokenType("Bearer")
                    .email(req.getEmail())
                    .build();
            return ResponseEntity.ok(resp);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(401).body(ex.getMessage());
        }
    }
}
