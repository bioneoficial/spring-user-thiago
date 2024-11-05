package com.example.users.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "User entity representing a registered user")
public class User {
    @Schema(description = "Unique identifier of the user", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    @Schema(description = "Age of the user", example = "25", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer age;

    @Schema(description = "Name of the user", example = "João Bione", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "Email address of the user", example = "joao.bione@dev.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
}
