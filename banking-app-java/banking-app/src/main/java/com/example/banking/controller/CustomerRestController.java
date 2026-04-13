package com.example.banking.controller;

import com.example.banking.dto.CreateCustomerRequest;
import com.example.banking.entity.Customer;
import com.example.banking.service.BankingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerRestController {

    private final BankingService bankingService;

    public CustomerRestController(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return bankingService.getAllCustomers();
    }

    @PostMapping
    public Customer createCustomer(@Valid @RequestBody CreateCustomerRequest request) {
        return bankingService.createCustomer(request);
    }
}
