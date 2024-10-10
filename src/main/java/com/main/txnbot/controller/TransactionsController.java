package com.main.txnbot.controller;

import com.main.txnbot.entity.Transactions;
import com.main.txnbot.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionsController {

    @Autowired
    private TransactionsService service;

    @PostMapping("/addTransaction")
    public ResponseEntity<Transactions> addTransaction(
            @Validated
            @RequestBody Transactions transactions
    ){
        Transactions transaction = service.addTransaction(transactions);
        return new ResponseEntity<Transactions>(transaction, HttpStatus.CREATED);
    }
}
