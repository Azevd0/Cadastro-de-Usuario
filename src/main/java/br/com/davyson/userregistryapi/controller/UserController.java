package br.com.davyson.userregistryapi.controller;

import br.com.davyson.userregistryapi.domain.dto.request.UserRegisterDto;
import br.com.davyson.userregistryapi.domain.dto.response.UserResponseDto;
import br.com.davyson.userregistryapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    @Operation(summary = "Criar usuário")
    public ResponseEntity<Void> createUser(UserRegisterDto request){
        userService.createUser(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Buscar usuário pelo E-mail")
    public ResponseEntity<UserResponseDto> findByEmail(String email){
        return ResponseEntity.ok().body(userService.findUserByEmail(email));
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Deletar usuário")
    public ResponseEntity<Void> deleteUser(Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
