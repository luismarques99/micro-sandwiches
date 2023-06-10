package com.lm99.sandwichservices.customer;

public abstract class CustomerException extends RuntimeException {

    protected CustomerException(String message) {
        super(message);
    }

}
