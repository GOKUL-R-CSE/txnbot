package com.main.txnbot.controller;

import com.main.txnbot.entity.Transactions;
import com.main.txnbot.service.ClientTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
            @PathVariable(name = "reference") UUID reference
    ){
        Transactions transactions = service.getTransaction(email, reference);
        return new ResponseEntity<Transactions>(transactions, HttpStatus.FOUND);
    }

    @GetMapping("/{email}/{reference}/getDate")
    public ResponseEntity<List<LocalDateTime>> getDate(
            @PathVariable(name = "email") String email,
            @PathVariable(name = "reference") UUID reference
    ){
        List<LocalDateTime> date = service.getDate(email, reference);
        return new ResponseEntity<List<LocalDateTime>>(date, HttpStatus.FOUND);
    }

    @GetMapping("/{email}/{reference}/getCurrency")
    public ResponseEntity<List<String>> getCurrency(
            @PathVariable(name = "email") String email,
            @PathVariable(name = "reference") UUID reference
    ){
        List<String> currency = service.getCurrency(email, reference);
        return new ResponseEntity<List<String>>(currency, HttpStatus.FOUND);
    }

    @GetMapping("/{email}/{reference}/getStatus")
    public String getStatus(
            @PathVariable(name = "email") String email,
            @PathVariable(name = "reference") UUID reference
    ){
        return service.getStatus(email, reference);
    }

    @GetMapping("/{email}/{reference}/getcategory")
    public ResponseEntity<List<String>> getCategoey(
            @PathVariable(name = "email") String email,
            @PathVariable(name = "reference") UUID reference
    ){
        List<String> category = service.getCategory(email, reference);
        return new ResponseEntity<List<String>>(category, HttpStatus.FOUND);
    }

}
