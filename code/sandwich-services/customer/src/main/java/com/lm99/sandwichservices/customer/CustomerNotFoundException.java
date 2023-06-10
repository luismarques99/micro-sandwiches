package com.lm99.sandwichservices.customer;

public class CustomerNotFoundException extends CustomerException {

    public CustomerNotFoundException(Long id) {
        super("Customer with id " + id + " not found");
    }

    public CustomerNotFoundException(String email) {
        super("Customer with email " + email + " not found");
    }

}
