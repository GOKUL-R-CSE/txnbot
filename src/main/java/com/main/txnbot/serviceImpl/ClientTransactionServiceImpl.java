package com.main.txnbot.serviceImpl;

import com.main.txnbot.entity.Clients;
import com.main.txnbot.entity.Transactions;
import com.main.txnbot.exception.ResourceAlreadyExistsException;
import com.main.txnbot.exception.ResourceNotFoundException;
import com.main.txnbot.repository.ClientsRepository;
import com.main.txnbot.repository.TransactionsRepository;
import com.main.txnbot.service.ClientTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientTransactionServiceImpl implements ClientTransactionService {

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;


    @Override
    public Transactions addTransaction(Transactions transactions, String email) {
        Clients client = clientsRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Client", "this email"));
        Transactions transaction = transactionsRepository.findByTransactionReference(transactions.getTransactionReference()).orElseThrow(() -> new ResourceAlreadyExistsException("Transaction", "reference"));
        client.getTransactions().add(transaction);
        return transactionsRepository.save(transactions);
    }

    @Override
    public List<Transactions> getTransactions(String email) {
        Clients client = clientsRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Client", "this email"));
        List<Transactions> transactions = new ArrayList<>(client.getTransactions());
        if (transactions.isEmpty()) {
            throw new ResourceNotFoundException("Transactions", "for this client");
        }
        return transactions;
    }

    @Override
    public Transactions getTransaction(String email, Long reference) {
        Clients client = clientsRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Client", "this email"));
        Transactions transaction = transactionsRepository.findByTransactionReference(reference).orElseThrow(() -> new ResourceAlreadyExistsException("Transaction", "reference"));
        if (!client.getTransactions().contains(transaction)) {
            throw new ResourceNotFoundException("Transaction", "specified reference");
        }
        return transaction;
    }
}
