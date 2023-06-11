package com.lm99.sandwichservices.user;

public class UserNotFoundException extends UserException {

    public UserNotFoundException(Long id) {
        super("User with id '" + id + "' not found");
    }

    public UserNotFoundException(String email) {
        super("User with email '" + email + "' not found");
    }

}
