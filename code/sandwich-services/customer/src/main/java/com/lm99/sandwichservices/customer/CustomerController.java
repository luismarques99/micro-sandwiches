package com.lm99.sandwichservices.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public record CustomerController(CustomerService customerService) {

    @GetMapping("{customerId}")
    public ResponseEntity<CustomerDefaultResponse> getCustomerById(@PathVariable Long customerId) {
        return ResponseEntity.ok(this.customerService.getCustomerById(customerId));
    }

    @GetMapping
    public ResponseEntity<CustomerDefaultResponse> getCustomerByEmail(@RequestParam String email) {
        return ResponseEntity.ok(this.customerService.getCustomerByEmail(email));
    }

}
