package com.main.txnbot.controller;

import com.main.txnbot.service.ClientTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientTransactionController {

    @Autowired
    private ClientTransactionService service;
}
