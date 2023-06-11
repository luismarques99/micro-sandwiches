package com.lm99.sandwichservices.role;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public record RoleController(

        RoleService roleService,

        RoleMapper roleMapper

) {

    @PostMapping
    public ResponseEntity<RoleDefaultResponseDTO> createRole(@Valid @RequestBody RoleDefaultRequestDTO request) {
        Role role = this.roleMapper.roleDefaultRequestDTOToRole(request);
        role = this.roleService.createRole(role);
        String path = String.format("/%d", role.getId());
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path(path).toUriString());
        RoleDefaultResponseDTO response = this.roleMapper.roleToRoleDefaultResponseDTO(role);
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<List<RoleDefaultResponseDTO>> getAllRoles() {
        List<Role> roles = this.roleService.getAllRoles();
        List<RoleDefaultResponseDTO> response = roles.stream()
                .map(this.roleMapper::roleToRoleDefaultResponseDTO).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDefaultResponseDTO> getRoleById(@PathVariable Long id) {
        Role role = this.roleService.getRoleById(id);
        RoleDefaultResponseDTO response = this.roleMapper.roleToRoleDefaultResponseDTO(role);
        return ResponseEntity.ok(response);
    }

    @GetMapping(params = {"name"})
    public ResponseEntity<RoleDefaultResponseDTO> getRoleByName(@RequestParam String name) {
        Role role = this.roleService.getRoleByName(name);
        RoleDefaultResponseDTO response = this.roleMapper.roleToRoleDefaultResponseDTO(role);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDefaultResponseDTO> updateRole(@PathVariable Long id,
                                                             @Valid @RequestBody RoleDefaultRequestDTO request) {
        Role updatedRole = this.roleMapper.roleDefaultRequestDTOToRole(request);
        Role role = this.roleService.updateRole(id, updatedRole);
        RoleDefaultResponseDTO response = this.roleMapper.roleToRoleDefaultResponseDTO(role);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        this.roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

}
