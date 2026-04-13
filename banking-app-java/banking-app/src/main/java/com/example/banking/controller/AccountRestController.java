package com.example.banking.controller;

import com.example.banking.dto.CreateAccountRequest;
import com.example.banking.dto.TransactionRequest;
import com.example.banking.entity.Account;
import com.example.banking.entity.BankTransaction;
import com.example.banking.service.BankingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
public class AccountRestController {

    private final BankingService bankingService;

    public AccountRestController(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    @GetMapping
    public List<Account> getAccounts() {
        return bankingService.getAllAccounts();
    }

    @PostMapping
    public Account createAccount(@Valid @RequestBody CreateAccountRequest request) {
        return bankingService.createAccount(request);
    }

    @PostMapping("/{accountId}/deposit")
    public Account deposit(@PathVariable Long accountId, @Valid @RequestBody TransactionRequest request) {
        return bankingService.deposit(accountId, request);
    }

    @PostMapping("/{accountId}/withdraw")
    public Account withdraw(@PathVariable Long accountId, @Valid @RequestBody TransactionRequest request) {
        return bankingService.withdraw(accountId, request);
    }

    @GetMapping("/{accountId}/transactions")
    public List<BankTransaction> getTransactions(@PathVariable Long accountId) {
        return bankingService.getRecentTransactions(accountId);
    }
}
