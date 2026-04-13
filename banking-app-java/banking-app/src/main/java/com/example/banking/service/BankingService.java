package com.example.banking.service;

import com.example.banking.dto.CreateAccountRequest;
import com.example.banking.dto.CreateCustomerRequest;
import com.example.banking.dto.TransactionRequest;
import com.example.banking.entity.*;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.CustomerRepository;
import com.example.banking.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
public class BankingService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public BankingService(CustomerRepository customerRepository, AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public List<BankTransaction> getRecentTransactions(Long accountId) {
        return transactionRepository.findTop10ByAccountIdOrderByTransactionTimeDesc(accountId);
    }

    public Customer createCustomer(CreateCustomerRequest request) {
        Customer customer = new Customer(request.getFullName(), request.getEmail(), request.getPhone());
        return customerRepository.save(customer);
    }

    @Transactional
    public Account createAccount(CreateAccountRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        Account account = new Account(generateAccountNumber(), request.getAccountType(), request.getInitialDeposit(), customer);
        Account saved = accountRepository.save(account);

        if (request.getInitialDeposit().compareTo(BigDecimal.ZERO) > 0) {
            transactionRepository.save(new BankTransaction(
                    TransactionType.DEPOSIT,
                    request.getInitialDeposit(),
                    "Initial deposit",
                    saved
            ));
        }
        return saved;
    }

    @Transactional
    public Account deposit(Long accountId, TransactionRequest request) {
        Account account = findAccount(accountId);
        account.setBalance(account.getBalance().add(request.getAmount()));
        transactionRepository.save(new BankTransaction(TransactionType.DEPOSIT, request.getAmount(), request.getDescription(), account));
        return account;
    }

    @Transactional
    public Account withdraw(Long accountId, TransactionRequest request) {
        Account account = findAccount(accountId);
        if (account.getBalance().compareTo(request.getAmount()) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        account.setBalance(account.getBalance().subtract(request.getAmount()));
        transactionRepository.save(new BankTransaction(TransactionType.WITHDRAW, request.getAmount(), request.getDescription(), account));
        return account;
    }

    private Account findAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));
    }

    private String generateAccountNumber() {
        return "BA" + (10000000 + new Random().nextInt(90000000));
    }
}
