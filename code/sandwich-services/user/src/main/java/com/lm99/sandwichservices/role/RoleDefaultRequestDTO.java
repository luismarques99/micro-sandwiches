package com.lm99.sandwichservices.role;

import jakarta.validation.constraints.NotBlank;

public record RoleDefaultRequestDTO(

        @NotBlank(message = "Name is required")
        String name,

        String description

) {
}
