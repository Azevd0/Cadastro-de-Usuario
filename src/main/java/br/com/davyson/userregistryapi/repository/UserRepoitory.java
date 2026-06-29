package br.com.davyson.userregistryapi.repository;

import br.com.davyson.userregistryapi.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepoitory extends JpaRepository<User, Long> {

    Optional<User> findUserByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);
}
