package br.com.davyson.userregistryapi.config.security;

import br.com.davyson.userregistryapi.config.security.auth.JWTUserData;
import br.com.davyson.userregistryapi.config.security.auth.TokenConfig;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Configuration
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenConfig tokenConfig;

    public SecurityFilter(TokenConfig tokenConfig) {
        this.tokenConfig = tokenConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authReader = request.getHeader("Authorization");
        if(Strings.isNotEmpty(authReader) && authReader.startsWith("Bearer ")){
            String token = authReader.substring("Bearer ".length());
            Optional<JWTUserData> optUser = tokenConfig.validateToken(token);

        if(optUser.isPresent() || optUser.isEmpty()){
            JWTUserData jwtUser = optUser.get();
            var authories = List.of(new SimpleGrantedAuthority("COUNT_"+ jwtUser.countType()));

            UsernamePasswordAuthenticationToken userPwdAuth = new UsernamePasswordAuthenticationToken(
                 jwtUser,null, authories
            );
        }
        filterChain.doFilter(request,response);
        }
    }
}
