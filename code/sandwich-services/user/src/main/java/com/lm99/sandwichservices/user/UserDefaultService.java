package com.lm99.sandwichservices.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public record UserDefaultService(

        UserRepository userRepository

) implements UserService {

    @Override
    public User createUser(User user) {
        this.validateUserEmail(user.getEmail());
        log.info("Creating user {}", user);
        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUsersByRoleId(Long roleId) {
        log.info("Fetching all users with role id {}", roleId);
        return this.userRepository.findAllByRoleId(roleId);
    }

    @Override
    public User getUserById(Long id) {
        log.info("Fetching user with id {}", id);
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("Fetching user with email {}", email);
        return this.userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        User user = this.getUserById(id);
        if (updatedUser.getFirstName() != null) {
            user.setFirstName(updatedUser.getFirstName());
        }
        if (updatedUser.getLastName() != null) {
            user.setLastName(updatedUser.getLastName());
        }
        if (updatedUser.getEmail() != null) {
            this.validateUserEmail(updatedUser.getEmail());
            user.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPassword() != null) {
            user.setPassword(updatedUser.getPassword());
        }
        if (updatedUser.getActive() != null) {
            user.setActive(updatedUser.getActive());
        }
        if (updatedUser.getRole() != null) {
            user.setRole(updatedUser.getRole());
        }
        log.info("Updating user {}", user);
        return this.userRepository.save(user);
    }

    private void validateUserEmail(String email) {
        // ATTENTION: This is only validating if the email is not already in use
        if (this.emailAlreadyInUse(email)) {
            throw new UserEmailAlreadyInUseException(email);
        }
    }

    private boolean emailAlreadyInUse(String email) {
        return this.userRepository.existsByEmail(email);
    }

}
