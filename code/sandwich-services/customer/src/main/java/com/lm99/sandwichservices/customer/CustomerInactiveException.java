package com.lm99.sandwichservices.customer;

public class CustomerInactiveException extends CustomerException {

    public CustomerInactiveException(Long id) {
        super("Customer with id '" + id + "' is inactive");
    }

    public CustomerInactiveException(String email) {
        super("Customer with email '" + email + "' is inactive");
    }

}
