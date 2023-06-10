package com.lm99.sandwichservices.customer;

public class CustomerEmailAlreadyInUseException extends CustomerException {

    public CustomerEmailAlreadyInUseException(String email) {
        super("Email '" + email + "' is already in use");
    }

}
