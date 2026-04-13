package com.example.banking.config;

import com.example.banking.entity.*;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.CustomerRepository;
import com.example.banking.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initData(CustomerRepository customerRepository,
                               AccountRepository accountRepository,
                               TransactionRepository transactionRepository) {
        return args -> {
            if (customerRepository.count() > 0) {
                return;
            }

            Customer c1 = customerRepository.save(new Customer("Aarav Kumar", "aarav@example.com", "717-555-1001"));
            Customer c2 = customerRepository.save(new Customer("Priya Sharma", "priya@example.com", "717-555-1002"));

            Account a1 = accountRepository.save(new Account("BA10000001", AccountType.SAVINGS, new BigDecimal("5400.00"), c1));
            Account a2 = accountRepository.save(new Account("BA10000002", AccountType.CHECKING, new BigDecimal("2500.00"), c2));

            transactionRepository.save(new BankTransaction(TransactionType.DEPOSIT, new BigDecimal("5400.00"), "Salary credit", a1));
            transactionRepository.save(new BankTransaction(TransactionType.DEPOSIT, new BigDecimal("2500.00"), "Opening balance", a2));
            transactionRepository.save(new BankTransaction(TransactionType.WITHDRAW, new BigDecimal("250.00"), "ATM withdrawal", a2));
        };
    }
}
