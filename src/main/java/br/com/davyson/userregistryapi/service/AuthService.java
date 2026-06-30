package br.com.davyson.userregistryapi.service;

import br.com.davyson.userregistryapi.config.security.auth.TokenConfig;
import br.com.davyson.userregistryapi.repository.UserRepoitory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepoitory userRepoitory;
    private final TokenConfig tokenConfig;
    private final AuthenticationManager authManager;

    public AuthService(UserRepoitory userRepoitory, TokenConfig tokenConfig, AuthenticationManager authManager) {
        this.userRepoitory = userRepoitory;
        this.tokenConfig = tokenConfig;
        this.authManager = authManager;
    }
}
