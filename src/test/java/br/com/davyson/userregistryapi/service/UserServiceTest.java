package br.com.davyson.userregistryapi.service;

import br.com.davyson.userregistryapi.domain.dto.response.UserResponseDto;
import br.com.davyson.userregistryapi.domain.user.User;
import br.com.davyson.userregistryapi.repository.UserRepoitory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserServiceTest {

private UserRepoitory userRepoitory;
private UserService userService;
private PasswordEncoder passwordEncoder;

@BeforeEach
    void setUp(){
    userRepoitory = Mockito.mock(UserRepoitory.class);
    passwordEncoder = Mockito.mock(PasswordEncoder.class);
    userService = new UserService(userRepoitory,passwordEncoder);
}
@Test
@DisplayName("Deve retornar um usuário pelo email")
void deveRetornarUmUsuarioPeloEmail(){
    User authenticatedUser = new User();
    authenticatedUser.setName("Wayne");
    Mockito.when(userRepoitory
            .findUserByEmailIgnoreCase(authenticatedUser.getName()))
            .thenReturn(Optional.of(authenticatedUser));
    UserResponseDto result = userService.findUserByEmail(authenticatedUser.getName());
    assertNotNull(result);
    assertEquals("Wayne", result.getName());
    Mockito.verify(userRepoitory, Mockito.times(1)).findUserByEmailIgnoreCase(authenticatedUser.getName());
}

}