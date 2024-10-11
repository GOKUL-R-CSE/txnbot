package com.main.txnbot.repository;

import com.main.txnbot.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, UUID> {
    Optional<Transactions> findByTransactionReference(UUID transactionReference);

    boolean existsByTransactionReference(UUID transactionReference);
}
