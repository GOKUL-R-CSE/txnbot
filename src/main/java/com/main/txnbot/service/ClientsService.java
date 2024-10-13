package com.main.txnbot.service;

import com.main.txnbot.entity.Clients;

import java.util.Optional;

public interface ClientsService {
    Clients addClient(Clients clients);

    void deleteClient(String email);

    Clients getClient(String email, String password);

    Clients updateClient(String email, Clients client);

}
