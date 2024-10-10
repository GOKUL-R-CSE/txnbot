package com.main.txnbot.controller;

import com.main.txnbot.entity.CardDetails;
import com.main.txnbot.service.CardDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CardDetailsController {

    @Autowired
    private CardDetailsService service;

    @PostMapping("/addCard")
    public ResponseEntity<CardDetails> addCard(
            @Validated
            @RequestBody CardDetails cardDetails
    ){
        CardDetails card = service.addCard(cardDetails);
        return new ResponseEntity<CardDetails>(cardDetails, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCard/{pan}")
    public ResponseEntity<String> deleteCard(
            @PathVariable(name = "pan") Long pan
    ){
        service.deleteCard(pan);
        return new ResponseEntity<String>("Card deleted successfully", HttpStatus.OK);
    }
}
