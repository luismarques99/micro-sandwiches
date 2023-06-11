package com.lm99.sandwichservices.user;

public abstract class UserException extends RuntimeException {

    protected UserException(String message) {
        super(message);
    }

}
