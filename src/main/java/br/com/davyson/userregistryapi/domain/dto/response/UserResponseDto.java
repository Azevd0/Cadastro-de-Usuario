package br.com.davyson.userregistryapi.domain.dto.response;

import br.com.davyson.userregistryapi.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String name;
    private String email;
    private String countType;

    public UserResponseDto(){}
    public UserResponseDto(User user) {
        this.name = name;
        this.email = email;
        this.countType = countType;
    }
}
