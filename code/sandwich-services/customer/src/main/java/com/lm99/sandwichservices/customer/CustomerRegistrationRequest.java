package com.lm99.sandwichservices.customer;

import jakarta.validation.constraints.NotBlank;

public record CustomerRegistrationRequest(

        String firstName,

        String lastName,

        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Password is required")
        String password

) {
}
