package com.main.txnbot.controller;

import com.main.txnbot.dto.AuthResponse;
import com.main.txnbot.entity.Clients;
import com.main.txnbot.service.ClientsService;
import com.main.txnbot.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientsController {

    @Autowired
    private ClientsService service;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<Clients> addClient(
            @Validated
            @RequestBody Clients clients
    ){
        Clients client = userDetailsService.register(clients);
        return new ResponseEntity<Clients>(client, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> enterClient(
            @Validated
            @RequestBody Clients clients
    ){
        String token = userDetailsService.verify(clients);
        AuthResponse response = new AuthResponse(clients, token);
        return new ResponseEntity<AuthResponse>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteClient/{email}")
    public ResponseEntity<String> deleteClient(
            @PathVariable(name = "email") String email
    ){
        service.deleteClient(email);
        return new ResponseEntity<String>("Client deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/getClient/{email}/{password}")
    public ResponseEntity<Clients> getClient(
            @PathVariable(name = "email") String email,
            @PathVariable(name = "password") String password
    ){
        Clients client = service.getClient(email, password);
        return new ResponseEntity<Clients>(client, HttpStatus.FOUND);
    }

    @PutMapping("/updateClient/{email}")
    public ResponseEntity<Clients> updateClient(
            @PathVariable(name = "email") String email,
            @RequestBody Clients client
    ){
        Clients cli = service.updateClient(email, client);
        return new ResponseEntity<Clients>(cli, HttpStatus.ACCEPTED);
    }

}
