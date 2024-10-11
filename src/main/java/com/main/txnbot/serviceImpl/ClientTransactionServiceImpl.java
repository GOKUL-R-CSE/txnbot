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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClientTransactionServiceImpl implements ClientTransactionService {

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;


    @Override
    public Transactions addTransaction(Transactions transactions, String email) {
        Clients client = clientsRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Client", "this email"));
        if (transactionsRepository.existsByTransactionReference(transactions.getTransactionReference())) {
            throw new ResourceAlreadyExistsException("Transaction", "reference");
        }
//        client.getTransactions().add(transactions);
        transactions.getClients().add(client);
        Transactions savedTransaction = transactionsRepository.save(transactions);
//        clientsRepository.save(client);
        return savedTransaction;
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
    public Transactions getTransaction(String email, UUID reference) {
        Clients client = clientsRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Client", "this email"));
        Transactions transaction = transactionsRepository.findByTransactionReference(reference).orElseThrow(() -> new ResourceNotFoundException("Transaction", "reference"));
        if (!client.getTransactions().contains(transaction)) {
            throw new ResourceNotFoundException("Transaction", "specified reference");
        }
        return transaction;
    }

    @Override
    public List<LocalDateTime> getDate(String email, UUID reference) {
        Clients client = clientsRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Client", "this email"));
        Transactions transaction = transactionsRepository.findByTransactionReference(reference).orElseThrow(() -> new ResourceNotFoundException("Transaction", "reference"));
        List<LocalDateTime> date = new ArrayList<>();
        if (client.getTransactions().contains(transaction)) {
            date.add(transaction.getTransactionDate());
            date.add(transaction.getSettlementDate());
            return date;
        }
        throw new ResourceNotFoundException("Transaction", "specified reference");
    }

    @Override
    public List<String> getCurrency(String email, UUID reference) {
        Clients client = clientsRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Client", "this email"));
        Transactions transaction = transactionsRepository.findByTransactionReference(reference).orElseThrow(() -> new ResourceNotFoundException("Transaction", "reference"));
        List<String> currency = new ArrayList<>();
        if (client.getTransactions().contains(transaction)) {
            currency.add(transaction.getTransactionCurrency());
            currency.add(transaction.getSettlementCurrency());
            return currency;
        }
        throw new ResourceNotFoundException("Transaction", "specified reference");
    }

    @Override
    public String getStatus(String email, UUID reference) {
        Clients client = clientsRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Client", "this email"));
        Transactions transaction = transactionsRepository.findByTransactionReference(reference).orElseThrow(() -> new ResourceNotFoundException("Transaction", "reference"));
        String status = "";
        if (client.getTransactions().contains(transaction)) {
            status = transaction.getTransactionStatus();
            return status;
        }
        throw new ResourceNotFoundException("Transaction", "specified reference");
    }

    @Override
    public List<String> getCategory(String email, UUID reference) {
        Clients client = clientsRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Client", "this email"));
        Transactions transaction = transactionsRepository.findByTransactionReference(reference).orElseThrow(() -> new ResourceNotFoundException("Transaction", "reference"));
        List<String> category = new ArrayList<>();
        if (client.getTransactions().contains(transaction)) {
            category.add(transaction.getTransactionCategory());
            category.add(transaction.getTerminalCategory());
            return category;
        }
        throw new ResourceNotFoundException("Transaction", "specified reference");
    }
}
