package com.main.txnbot.controller;

import com.main.txnbot.entity.CardDetails;
import com.main.txnbot.service.ClientCardDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientCardDetailsController {

    @Autowired
    private ClientCardDetailsService service;

    @PostMapping("/addCard/{email}")
    public ResponseEntity<CardDetails> addCard(
            @Validated
            @RequestBody CardDetails cardDetails,
            @PathVariable(name = "email") String email
    ){
        CardDetails card = service.addCard(cardDetails, email);
        return new ResponseEntity<CardDetails>(cardDetails, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCard/{email}/{pan}")
    public ResponseEntity<String> deleteCard(
            @PathVariable(name = "pan") Long pan,
            @PathVariable(name = "email") String email
    ){
        service.deleteCard(pan, email);
        return new ResponseEntity<String>("Card deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/{email}/getCardDetails")
    public ResponseEntity<List<CardDetails>> getCardDetails(
            @PathVariable(name = "email") String email
    ){
        List<CardDetails> cardDetails = service.getCardDetails(email);
        return new ResponseEntity<List<CardDetails>>(cardDetails, HttpStatus.FOUND);
    }

    @GetMapping("/{email}/{pan}/getCardDetail")
    public ResponseEntity<CardDetails> getCardDetail(
            @PathVariable(name = "email") String email,
            @PathVariable(name = "pan") Long pan
    ){
        CardDetails cardDetail = service.getCardDetail(email, pan);
        return new ResponseEntity<CardDetails>(cardDetail, HttpStatus.FOUND);
    }
}
