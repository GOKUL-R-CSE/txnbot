package com.main.txnbot.serviceImpl;

import com.main.txnbot.entity.Transactions;
import com.main.txnbot.exception.ResourceAlreadyExistsException;
import com.main.txnbot.repository.TransactionsRepository;
import com.main.txnbot.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    @Autowired
    private TransactionsRepository repository;
    @Override
    public Transactions addTransaction(Transactions transactions) {
        Optional<Transactions> transaction = repository.findByTransactionReference(transactions.getTransactionReference());
        if (transaction.isPresent()) {
            throw new ResourceAlreadyExistsException("Transaction", "reference");
        }
        return repository.save(transactions);
    }
}
