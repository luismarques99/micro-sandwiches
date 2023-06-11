package com.lm99.sandwichservices.role;

import org.springframework.stereotype.Component;

@Component
public record RoleMapper() {

    public Role roleDefaultRequestDTOToRole(RoleDefaultRequestDTO roleDefaultRequest) {
        if (roleDefaultRequest == null) {
            return null;
        }
        return Role.builder()
                .name(roleDefaultRequest.name())
                .description(roleDefaultRequest.description())
                .build();
    }

    public RoleDefaultResponseDTO roleToRoleDefaultResponseDTO(Role role) {
        if (role == null) {
            return null;
        }
        return new RoleDefaultResponseDTO(
                role.getId(),
                role.getName(),
                role.getDescription()
        );
    }

}
