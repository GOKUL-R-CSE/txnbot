package com.main.txnbot.serviceImpl;

import com.main.txnbot.entity.Clients;
import com.main.txnbot.exception.ResourceAlreadyExistsException;
import com.main.txnbot.exception.ResourceNotFoundException;
import com.main.txnbot.repository.ClientsRepository;
import com.main.txnbot.service.JWTService;
import com.main.txnbot.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private ClientsRepository repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    @Override
    public Clients register(Clients user) {
        Optional<Clients> client = repo.findByEmail(user.getEmail());
        if (client.isPresent()){
            throw new ResourceAlreadyExistsException("Client", "this email");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return user;
    }

    @Override
    public String verify(Clients user) {
        Optional<Clients> existingClient = repo.findByEmail(user.getEmail());
        if (existingClient.isEmpty()) {
            throw new ResourceNotFoundException("Client", "email");
        }
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getEmail());
        } else {
            return "fail";
        }
    }
}
