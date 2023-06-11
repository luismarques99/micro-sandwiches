package com.lm99.sandwichservices.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public record UserController(

        UserService userService,

        UserMapper userMapper

) {

    @PostMapping
    public ResponseEntity<UserDefaultResponseDTO> createUser(@Valid @RequestBody UserRegistrationRequestDTO request) {
        User user = this.userMapper.userRegistrationRequestDTOToUser(request);
        user = this.userService.createUser(user);
        String path = String.format("/%d", user.getId());
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path(path).toUriString());
        UserDefaultResponseDTO response = this.userMapper.userToUserDefaultResponseDTO(user);
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping(params = {"roleId"})
    public ResponseEntity<List<UserDefaultResponseDTO>> getAllUsersByRoleId(@RequestParam Long roleId) {
        List<User> users = this.userService.getAllUsersByRoleId(roleId);
        List<UserDefaultResponseDTO> response = users.stream()
                .map(this.userMapper::userToUserDefaultResponseDTO).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDefaultResponseDTO> getUserById(@PathVariable Long id) {
        User user = this.userService.getUserById(id);
        UserDefaultResponseDTO response = this.userMapper.userToUserDefaultResponseDTO(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping(params = {"email"})
    public ResponseEntity<UserDefaultResponseDTO> getUserByEmail(@RequestParam String email) {
        User user = this.userService.getUserByEmail(email);
        UserDefaultResponseDTO response = this.userMapper.userToUserDefaultResponseDTO(user);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDefaultResponseDTO> updateUser(@PathVariable Long id,
                                                             @Valid @RequestBody UserUpdateRequestDTO request) {
        User updatedUser = this.userMapper.userUpdateRequestDTOToUser(request);
        User user = this.userService.updateUser(id, updatedUser);
        UserDefaultResponseDTO response = this.userMapper.userToUserDefaultResponseDTO(user);
        return ResponseEntity.ok(response);
    }

}
