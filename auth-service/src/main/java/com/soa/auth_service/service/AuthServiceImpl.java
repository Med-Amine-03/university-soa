package com.soa.auth_service.service;

import com.soa.auth_service.dto.LoginRequest;
import com.soa.auth_service.dto.RegisterRequest;
import com.soa.auth_service.entity.Role;
import com.soa.auth_service.entity.User;
import com.soa.auth_service.repository.UserRepository;
import com.soa.auth_service.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        Role role = Role.ROLE_STUDENT;
        if (req.getRole() != null) {
            String r = req.getRole().trim().toUpperCase();
            if (r.equals("ADMIN")) role = Role.ROLE_ADMIN;
            else if (r.equals("TEACHER")) role = Role.ROLE_TEACHER;
            else if (r.equals("STUDENT")) role = Role.ROLE_STUDENT;
        }

        User u = User.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .roles(Set.of(role))
                .createdAt(Instant.now())
                .build();

        return userRepository.save(u);
    }

    @Override
    public String login(LoginRequest req) {
        var userOpt = userRepository.findByEmail(req.getEmail());
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        User user = userOpt.get();
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        Set<String> roles = user.getRoles().stream().map(Enum::name).collect(Collectors.toSet());
        return jwtUtil.generateToken(user.getId(), user.getEmail(), roles);
    }
}
