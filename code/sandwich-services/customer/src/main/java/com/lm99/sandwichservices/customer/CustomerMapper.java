package com.lm99.sandwichservices.customer;

public record CustomerMapper() {

    public Customer customerRegistrationRequestToCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        return Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .password(customerRegistrationRequest.password())
                .build();
    }

    public CustomerDefaultResponse customerToDefaultCustomerResponse(Customer customer) {
        return new CustomerDefaultResponse(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPassword()
        );
    }

}
