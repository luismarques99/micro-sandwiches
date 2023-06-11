package com.lm99.sandwichservices.role;

import java.util.List;

public interface RoleService {

    Role createRole(Role role);

    List<Role> getAllRoles();

    Role getRoleById(Long id);

    Role getRoleByName(String name);

    Role updateRole(Long id, Role updatedRole);

    void deleteRole(Long id);

}
