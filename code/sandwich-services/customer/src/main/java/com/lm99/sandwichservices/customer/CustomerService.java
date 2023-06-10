package com.lm99.sandwichservices.customer;

public interface CustomerService {

    CustomerDefaultResponse getCustomerById(Long id);

    CustomerDefaultResponse getCustomerByEmail(String email);

    CustomerDefaultResponse registerCustomer(CustomerRegistrationRequest request);

//    Customer updateCustomer(Long id, CustomerUpdateRequest request);

    void deleteCustomer(Long id);

    void activateCustomer(Long id);

    void deactivateCustomer(Long id);

}
