package br.com.davyson.userregistryapi.config.security.auth;

import br.com.davyson.userregistryapi.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Instant;
import java.util.Optional;


@Component
public class TokenConfig {
    private String secret = "secret";

    public String generateToken(User user){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withClaim("userId", user.getId())
                .withClaim("countType", user.getCountType().name())
                .withSubject(user.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(84600))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public Optional<JWTUserData> validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);

            return Optional.of(JWTUserData.builder()
                    .userId(decodedJWT.getClaim("userId").asLong())
                    .email(decodedJWT.getSubject())
                    .countType(decodedJWT.getClaim("countType").asString())
                    .build());
        }catch (JWTVerificationException jwtEx){
            return Optional.empty();
        }
    }
}
