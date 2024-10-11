package com.main.txnbot.service;

import com.main.txnbot.entity.Transactions;

import java.util.List;

public interface ClientTransactionService {
    Transactions addTransaction(Transactions transactions, String email);

    List<Transactions> getTransactions(String email);

    Transactions getTransaction(String email, Long reference);
}
