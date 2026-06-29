package br.com.davyson.userregistryapi.domain.dto.request;

public record UserRegisterDto(String name, String document, String email, String password) {
}
