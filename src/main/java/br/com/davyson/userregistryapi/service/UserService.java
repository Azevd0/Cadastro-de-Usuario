package br.com.davyson.userregistryapi.service;

import br.com.davyson.userregistryapi.domain.dto.request.UserRegisterDto;
import br.com.davyson.userregistryapi.domain.dto.response.UserResponseDto;
import br.com.davyson.userregistryapi.domain.user.User;
import br.com.davyson.userregistryapi.repository.UserRepoitory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepoitory userRepoitory;

    public UserService(UserRepoitory userRepoitory) {
        this.userRepoitory = userRepoitory;
    }

    public void createUser(UserRegisterDto request){
        User newUser = new User(
                request.name(),
                request.password(),
                request.email()
        );
        userRepoitory.save(newUser);
    }

    public UserResponseDto findUserByEmail(String email) throws UsernameNotFoundException{
        User userEmail = userRepoitory.findUserByEmailIgnoreCase(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado"));
        return new UserResponseDto(userEmail);
    }

    public void deleteUser(Long id){
        userRepoitory.deleteById(id);
    }
}
