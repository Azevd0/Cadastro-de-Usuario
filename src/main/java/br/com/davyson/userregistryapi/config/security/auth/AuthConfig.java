package br.com.davyson.userregistryapi.config.security.auth;

import br.com.davyson.userregistryapi.repository.UserRepoitory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthConfig implements UserDetailsService {

    private final UserRepoitory userRepoitory;

    public AuthConfig(UserRepoitory userRepoitory) {
        this.userRepoitory = userRepoitory;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepoitory.findUserByEmailIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuário não encontrado com este E-mail"
                ));
    }
}
