package br.com.davyson.userregistryapi.config.security.auth;

import br.com.davyson.userregistryapi.repository.UserRepoitory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthConfig {

    private final UserRepoitory userRepoitory;

    public AuthConfig(UserRepoitory userRepoitory) {
        this.userRepoitory = userRepoitory;
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        return userRepoitory.findUserByEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuário não encontrado com este E-mail"
                ));
    }
}
