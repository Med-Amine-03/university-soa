package com.soa.auth_service.service;

import com.soa.auth_service.dto.LoginRequest;
import com.soa.auth_service.dto.RegisterRequest;
import com.soa.auth_service.entity.User;

public interface AuthService {
    User register(RegisterRequest req);
    String login(LoginRequest req);
}
