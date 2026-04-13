package com.example.banking.repository;

import com.example.banking.entity.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<BankTransaction, Long> {
    List<BankTransaction> findTop10ByAccountIdOrderByTransactionTimeDesc(Long accountId);
}
