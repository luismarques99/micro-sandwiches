package com.lm99.sandwichservices.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record CustomerDefaultService(CustomerRepository customerRepository,
                                     CustomerMapper customerMapper) implements CustomerService {
    // FIXME: CustomerMapper is not able to be injected

    @Override
    public CustomerDefaultResponse getCustomerById(Long id) {
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        log.info("Fetching customer with id: {}", id);
        if (!customer.getIsActive()) {
            throw new CustomerInactiveException(id);
        }
        return this.customerMapper.customerToDefaultCustomerResponse(customer);
    }

    @Override
    public CustomerDefaultResponse getCustomerByEmail(String email) {
        Customer customer = this.customerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(email));
        if (!customer.getIsActive()) {
            throw new CustomerInactiveException(email);
        }
        return this.customerMapper.customerToDefaultCustomerResponse(customer);
    }

    @Override
    public CustomerDefaultResponse registerCustomer(CustomerRegistrationRequest request) {
        // TODO: Check if email is already in use
        Customer customer = this.customerMapper.customerRegistrationRequestToCustomer(request);
        this.customerRepository.save(customer);
        log.info("Saving customer: {}", customer);
        return this.customerMapper.customerToDefaultCustomerResponse(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        this.customerRepository.deleteById(id);
        log.info("Deleting customer with id: {}", id);
    }

    @Override
    public void activateCustomer(Long id) {
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        customer.setIsActive(true);
        this.customerRepository.save(customer);
        log.info("Activating customer with id: {}", id);
    }

    @Override
    public void deactivateCustomer(Long id) {
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        customer.setIsActive(false);
        this.customerRepository.save(customer);
        log.info("Deactivating customer with id: {}", id);
    }

}
