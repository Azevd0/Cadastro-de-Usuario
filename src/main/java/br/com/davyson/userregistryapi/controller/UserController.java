package br.com.davyson.userregistryapi.controller;

import br.com.davyson.userregistryapi.domain.dto.request.UserRegisterDto;
import br.com.davyson.userregistryapi.domain.dto.response.UserResponseDto;
import br.com.davyson.userregistryapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    @Operation(summary = "Criar usuário")
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserRegisterDto request){
        userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping
    @Operation(summary = "Buscar por E-mail")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponseDto> findByEmail(@RequestParam String email){
        UserResponseDto userEmail = userService.findUserByEmail(email);
        return ResponseEntity.ok().body(userEmail);
    }

}
