package com.lm99.sandwichservices.user;

import jakarta.validation.constraints.Email;

public record UserUpdateRequestDTO(

        String firstName,

        String lastName,

        @Email
        String email,

        String password,

        Boolean active,

        UserRoleUpdateRequestDTO role

) {
}
