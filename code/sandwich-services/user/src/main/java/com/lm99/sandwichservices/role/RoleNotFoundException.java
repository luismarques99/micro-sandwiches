package com.lm99.sandwichservices.role;

public class RoleNotFoundException extends RoleException {

    public RoleNotFoundException(Long id) {
        super("Role with id '" + id + "' not found");
    }

    public RoleNotFoundException(String name) {
        super("Role with name '" + name + "' not found");
    }

}
