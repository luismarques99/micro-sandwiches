package com.lm99.sandwichservices.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record CustomerDefaultService(CustomerRepository customerRepository) implements CustomerService {

    @Override
    public Customer getCustomerById(Long id) {
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        log.info("Fetching customer with id: {}", id);
        return customer;
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        Customer customer = this.customerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(email));
        log.info("Fetching customer with email: {}", email);
        return customer;
    }

    @Override
    public Customer registerCustomer(Customer customer) {
        this.validateEmail(customer.getEmail());
        // TODO: Encrypt password
        this.customerRepository.save(customer);
        log.info("Registering customer: {}", customer);
        return customer;
    }

    @Override
    public void activateCustomer(Long id) {
        Customer customer = this.getCustomerById(id);
        customer.setActive(true);
        this.customerRepository.save(customer);
        log.info("Activating customer with id: {}", id);
    }

    @Override
    public void deactivateCustomer(Long id) {
        Customer customer = this.getCustomerById(id);
        customer.setActive(false);
        this.customerRepository.save(customer);
        log.info("Deactivating customer with id: {}", id);
    }

    private void validateEmail(String email) {
        // This is not validating if the email is a fraud
        if (this.emailAlreadyInUse(email)){
            throw new CustomerEmailAlreadyInUseException(email);
        }
    }

    private boolean emailAlreadyInUse(String email) {
        return this.customerRepository.findByEmail(email).isPresent();
    }

}
