package com.main.txnbot.controller;

import com.main.txnbot.entity.Transactions;
import com.main.txnbot.service.ClientTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientTransactionController {

    @Autowired
    private ClientTransactionService service;

    @PostMapping("/{email}/addTransaction")
    public ResponseEntity<Transactions> addTransaction(
            @Validated
            @RequestBody Transactions transactions,
            @PathVariable(name = "email") String email
    ){
        Transactions transaction = service.addTransaction(transactions, email);
        return new ResponseEntity<Transactions>(transaction, HttpStatus.CREATED);
    }

    @GetMapping("/{email}/getTransactions")
    public ResponseEntity<List<Transactions>> getTransactions(
            @PathVariable(name = "email") String email
    ){
        List<Transactions> transactions = service.getTransactions(email);
        return new ResponseEntity<List<Transactions>>(transactions, HttpStatus.FOUND);
    }

    @GetMapping("/{email}/{reference}/getTransaction")
    public ResponseEntity<Transactions> getTransaction(
            @PathVariable(name = "email") String email,
            @PathVariable(name = "reference") Long reference
    ){
        Transactions transactions = service.getTransaction(email, reference);
        return new ResponseEntity<Transactions>(transactions, HttpStatus.FOUND);
    }
}
