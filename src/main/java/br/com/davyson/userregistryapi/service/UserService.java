package br.com.davyson.userregistryapi.service;

import br.com.davyson.userregistryapi.domain.dto.request.UserRegisterDto;
import br.com.davyson.userregistryapi.domain.dto.response.UserResponseDto;
import br.com.davyson.userregistryapi.domain.exceptions.globalexceptions.ObjectNotFoundException;
import br.com.davyson.userregistryapi.domain.user.User;
import br.com.davyson.userregistryapi.repository.UserRepoitory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepoitory userRepoitory;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepoitory userRepoitory, PasswordEncoder passwordEncoder) {
        this.userRepoitory = userRepoitory;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(UserRegisterDto request){
        if (userRepoitory.existsByEmailIgnoreCase(request.email())){
            throw new DataIntegrityViolationException("Este E-mail já existe");
        }
        User newUser = new User();
        newUser.setName(request.name());
        newUser.setDocument(request.document());
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));

        userRepoitory.save(newUser);
    }
    @Cacheable(value = "user", key = "'useremail' + #email")
    public UserResponseDto findUserByEmail(String email){
       User userEmail = userRepoitory.findUserByEmailIgnoreCase(email)
               .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado!"));
       return new UserResponseDto(userEmail);
    }
}
