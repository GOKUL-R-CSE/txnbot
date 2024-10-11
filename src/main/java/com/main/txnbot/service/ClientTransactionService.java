package com.main.txnbot.service;

import com.main.txnbot.entity.Transactions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ClientTransactionService {
    Transactions addTransaction(Transactions transactions, String email);

    List<Transactions> getTransactions(String email);

    Transactions getTransaction(String email, UUID reference);

    List<LocalDateTime> getDate(String email, UUID reference);

    List<String> getCurrency(String email, UUID reference);

    String getStatus(String email, UUID reference);

    List<String> getCategory(String email, UUID reference);
}
