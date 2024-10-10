package com.main.txnbot.repository;

import com.main.txnbot.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Long, Transactions> {
}
