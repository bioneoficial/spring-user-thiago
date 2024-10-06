package com.example.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserDto(
        @NotBlank(message = "Nome com pelo menos 1 caracter") String name,
        @NotBlank(message = "Email não pode ser vazio") @Email(message = "Email tem que ser válido") String email,
        @NotNull(message = "Idade não pode ser nulo") @Min(value = 1, message = "Idade tem que ser maior que 0") Integer age
) {
}
