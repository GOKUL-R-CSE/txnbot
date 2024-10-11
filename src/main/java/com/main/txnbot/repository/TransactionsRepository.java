package com.main.txnbot.repository;

import com.main.txnbot.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
    Optional<Transactions> findByTransactionReference(Long transactionReference);

}
