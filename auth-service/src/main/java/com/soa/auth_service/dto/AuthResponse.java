package com.soa.auth_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String accessToken;
    private String tokenType;
    private Long userId;
    private String email;
}
