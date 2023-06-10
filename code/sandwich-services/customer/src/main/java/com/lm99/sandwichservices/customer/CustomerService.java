package com.lm99.sandwichservices.customer;

public interface CustomerService {

    Customer getCustomerById(Long id);

    Customer getCustomerByEmail(String email);

    Customer registerCustomer(Customer customer);

//    Customer updateCustomer(Long id, CustomerUpdateRequest request);

    void activateCustomer(Long id);

    void deactivateCustomer(Long id);

}
