package com.lm99.sandwichservices.user;

public class UserEmailAlreadyInUseException extends UserException {

    public UserEmailAlreadyInUseException(String email) {
        super("User email '" + email + "' is already in use");
    }

}
