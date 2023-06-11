package com.lm99.sandwichservices.user;

public class UserUpdateWithEmptyRoleException extends UserException {

    public UserUpdateWithEmptyRoleException() {
        super("Cannot update user with empty role");
    }

}
