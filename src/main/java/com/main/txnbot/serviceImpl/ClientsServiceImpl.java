package com.main.txnbot.serviceImpl;

import com.main.txnbot.entity.Clients;
import com.main.txnbot.exception.ResourceAlreadyExistsException;
import com.main.txnbot.exception.ResourceNotFoundException;
import com.main.txnbot.repository.ClientsRepository;
import com.main.txnbot.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ClientsServiceImpl implements ClientsService {

    @Autowired
    private ClientsRepository repository;

    @Override
    public Clients addClient(Clients clients) {
        Optional<Clients> client = repository.findByEmail(clients.getEmail());
        if (client.isPresent()){
            throw new ResourceAlreadyExistsException("Client", "this email");
        }
        return repository.save(clients);
    }

    @Override
    public void deleteClient(String email) {
        Optional<Clients> client = repository.findByEmail(email);
        if (client.isPresent()){
            repository.deleteByEmail(email);
        }
        else {
            throw new ResourceNotFoundException("Client", "specified email");
        }
    }

    @Override
    public Clients getClient(String email, String password) {
        Optional<Clients> client = repository.findByEmail(email);
        if (client.isPresent() && client.get().getPassword().equals(password)) {
            return client.get();
        }
        throw new RuntimeException("Authentication error");
    }

    @Override
    public Clients updateClient(String email, Clients client) {
        Clients cli = repository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Client", "this email"));
        if(Objects.nonNull(client.getClientName()) && !"".equals(client.getClientName())){
            cli.setClientName(client.getClientName());
        }
        if(Objects.nonNull(client.getEmail()) && !"".equals(client.getEmail())){
            cli.setEmail(client.getEmail());
        }
        if(Objects.nonNull(client.getPassword()) && !"".equals(client.getPassword())){
            cli.setPassword(client.getPassword());
        }
        if(Objects.nonNull(client.getPhoneNumber()) && !"".equals(client.getPhoneNumber())){
            cli.setPhoneNumber(client.getPhoneNumber());
        }
        if(Objects.nonNull(client.getAccountNumber()) && !"".equals(client.getAccountNumber())){
            cli.setAccountNumber(client.getAccountNumber());
        }
        if(Objects.nonNull(client.getIfsc()) && !"".equals(client.getIfsc())){
            cli.setIfsc(client.getIfsc());
        }
        repository.save(cli);
        return cli;
    }
}
