package com.lm99.sandwichservices.user;

import com.lm99.sandwichservices.role.RoleDefaultResponseDTO;

public record UserDefaultResponseDTO(

        Long id,

        String firstName,

        String lastName,

        String email,

        Boolean active,

        RoleDefaultResponseDTO role

) {
}
