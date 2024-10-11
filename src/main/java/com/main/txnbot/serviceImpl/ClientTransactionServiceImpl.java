package com.main.txnbot.serviceImpl;

import com.main.txnbot.repository.ClientsRepository;
import com.main.txnbot.repository.TransactionsRepository;
import com.main.txnbot.service.ClientTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientTransactionServiceImpl implements ClientTransactionService {

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;


}
