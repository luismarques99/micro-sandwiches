package com.lm99.sandwichservices.role;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public record RoleDefaultService(

        RoleRepository roleRepository

) implements RoleService {

    @Override
    public Role createRole(Role role) {
        this.validateRoleName(role.getName());
        log.info("Creating role: {}", role);
        return this.roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        log.info("Fetching all roles");
        return this.roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        log.info("Fetching role with id: {}", id);
        return this.roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
    }

    @Override
    public Role getRoleByName(String name) {
        log.info("Fetching role with name: {}", name);
        return this.roleRepository.findByName(name).orElseThrow(() -> new RoleNotFoundException(name));
    }

    @Override
    public Role updateRole(Long id, Role updatedRole) {
        Role role = this.getRoleById(id);
        if (updatedRole.getName() != null) {
            role.setName(updatedRole.getName());
        }
        if (updatedRole.getDescription() != null) {
            role.setDescription(updatedRole.getDescription());
        }
        log.info("Updating role: {}", role);
        return this.roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long id) {
        log.info("Deleting role with id: {}", id);
        this.roleRepository.deleteById(id);
    }

    private void validateRoleName(String roleName) {
        // ATTENTION: This method iw only validating if there are no repeated role names
        if (this.roleNameAlreadyInUse(roleName)) {
            throw new RoleNameAlreadyInUseException(roleName);
        }
    }

    private boolean roleNameAlreadyInUse(String roleName) {
        return this.roleRepository.existsByName(roleName);
    }

}
