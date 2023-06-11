package com.lm99.sandwichservices.user;

import com.lm99.sandwichservices.role.Role;
import com.lm99.sandwichservices.role.RoleMapper;
import com.lm99.sandwichservices.role.RoleService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public record UserMapper(

        RoleService roleService,

        RoleMapper roleMapper,

        PasswordEncoder passwordEncoder

) {

    public User userRegistrationRequestDTOToUser(UserRegistrationRequestDTO userRegistrationRequest) {
        if (userRegistrationRequest == null) {
            return null;
        }
        return User.builder()
                .firstName(userRegistrationRequest.firstName())
                .lastName(userRegistrationRequest.lastName())
                .email(userRegistrationRequest.email())
                .password(passwordEncoder.encode(userRegistrationRequest.password()))
                .active(false)
                .build();
    }

    public User userUpdateRequestDTOToUser(UserUpdateRequestDTO userUpdateRequest) {
        if (userUpdateRequest == null) {
            return null;
        }
        Role role;
        if (userUpdateRequest.role().id() != null) {
            role = this.roleService.getRoleById(userUpdateRequest.role().id());
        } else if (userUpdateRequest.role().name() != null) {
            role = this.roleService.getRoleByName(userUpdateRequest.role().name());
        } else {
            throw new UserUpdateWithEmptyRoleException();
        }
        return User.builder()
                .firstName(userUpdateRequest.firstName())
                .lastName(userUpdateRequest.lastName())
                .email(userUpdateRequest.email())
                .password(userUpdateRequest.password() == null ? null :
                        passwordEncoder.encode(userUpdateRequest.password()))
                .active(userUpdateRequest.active())
                .role(role)
                .build();
    }

    public UserDefaultResponseDTO userToUserDefaultResponseDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserDefaultResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getActive(),
                this.roleMapper.roleToRoleDefaultResponseDTO(user.getRole())
        );
    }

}
