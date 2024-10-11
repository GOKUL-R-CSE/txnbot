package com.main.txnbot.service;

import com.main.txnbot.entity.Transactions;

import java.time.LocalDateTime;
import java.util.List;

public interface ClientTransactionService {
    Transactions addTransaction(Transactions transactions, String email);

    List<Transactions> getTransactions(String email);

    Transactions getTransaction(String email, Long reference);

    List<LocalDateTime> getDate(String email, Long reference);

    List<String> getCurrency(String email, Long reference);

    String getStatus(String email, Long reference);

    List<String> getCategory(String email, Long reference);
}
