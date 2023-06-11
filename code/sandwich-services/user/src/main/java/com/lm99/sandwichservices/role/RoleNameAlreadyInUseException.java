package com.lm99.sandwichservices.role;

public class RoleNameAlreadyInUseException extends RoleException {

    public RoleNameAlreadyInUseException(String name) {
        super("Role name '" + name + "' is already in use");
    }

}
