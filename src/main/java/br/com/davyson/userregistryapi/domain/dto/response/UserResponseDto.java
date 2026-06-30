package br.com.davyson.userregistryapi.domain.dto.response;

import br.com.davyson.userregistryapi.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String countType;

    public UserResponseDto(){}
    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.countType = user.getCountType().name();
    }

}
