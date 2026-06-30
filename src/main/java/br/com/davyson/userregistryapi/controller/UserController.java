package br.com.davyson.userregistryapi.controller;

import br.com.davyson.userregistryapi.domain.dto.request.UserRegisterDto;
import br.com.davyson.userregistryapi.domain.dto.response.UserResponseDto;
import br.com.davyson.userregistryapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserResponseDto> findByEmail(@RequestParam String email){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserByEmail(email));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletar usuário")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
