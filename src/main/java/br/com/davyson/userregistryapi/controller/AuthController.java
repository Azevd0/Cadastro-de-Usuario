package br.com.davyson.userregistryapi.controller;

import br.com.davyson.userregistryapi.config.security.auth.TokenConfig;
import br.com.davyson.userregistryapi.domain.dto.request.LoginRequestDto;
import br.com.davyson.userregistryapi.domain.dto.response.LoginResponseDto;
import br.com.davyson.userregistryapi.domain.user.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final TokenConfig tokenService;

    private final AuthenticationManager authenticationManager;

    public AuthController(TokenConfig tokenService,AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto request) {
        try {
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
            Authentication authenticate = authenticationManager.authenticate(userAndPass);

            User user = (User) authenticate.getPrincipal();

            String token = tokenService.generateToken(user);

            return ResponseEntity.ok(new LoginResponseDto(token));
        } catch (RuntimeException e) {
            throw new RuntimeException("Usuário ou senha inválidos.");
        }
    }

}
