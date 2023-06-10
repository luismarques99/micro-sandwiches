package com.lm99.sandwichservices.customer;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/customers")
public record CustomerController(CustomerService customerService,
                                 CustomerMapper customerMapper) {

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDefaultResponse> getCustomerById(@PathVariable Long customerId) {
        Customer customer = this.customerService.getCustomerById(customerId);
        return ResponseEntity.ok(this.customerMapper.customerToDefaultCustomerResponse(customer));
    }

    @GetMapping
    public ResponseEntity<CustomerDefaultResponse> getCustomerByEmail(@RequestParam String email) {
        Customer customer = this.customerService.getCustomerByEmail(email);
        return ResponseEntity.ok(this.customerMapper.customerToDefaultCustomerResponse(customer));
    }

    @PostMapping
    public ResponseEntity<CustomerDefaultResponse> createCustomer(@Valid @RequestBody
                                                                  CustomerRegistrationRequest request) {
        Customer customer = this.customerMapper.customerRegistrationRequestToCustomer(request);
        customer = this.customerService.registerCustomer(customer);
        String path = String.format("/%d", customer.getId());
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path(path).toUriString());
        CustomerDefaultResponse response = this.customerMapper.customerToDefaultCustomerResponse(customer);
        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/{customerId}/activate")
    public ResponseEntity<Void> activateCustomer(@PathVariable Long customerId) {
        this.customerService.activateCustomer(customerId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{customerId}/deactivate")
    public ResponseEntity<Void> deactivateCustomer(@PathVariable Long customerId) {
        this.customerService.deactivateCustomer(customerId);
        return ResponseEntity.noContent().build();
    }

}
