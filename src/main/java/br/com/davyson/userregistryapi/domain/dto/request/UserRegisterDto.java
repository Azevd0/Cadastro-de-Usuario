package br.com.davyson.userregistryapi.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record UserRegisterDto(String name,
                              @CPF(message = "Formato de cpf inválido!")
                              String document,
                              @Email(message = "Formato de email inválido!")
                              String email,
                              @Size(min = 5, max = 15, message = "Senha deve ter entre 5 e 15 caracteres")
                              String password) {
}
