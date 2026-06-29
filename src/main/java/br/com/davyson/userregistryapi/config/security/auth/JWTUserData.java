package br.com.davyson.userregistryapi.config.security.auth;

import lombok.Builder;

@Builder
public record JWTUserData(Long userId, String email, String countType) {
}
