package com.main.txnbot.serviceImpl;

import com.main.txnbot.config.UserPrincipal;
import com.main.txnbot.entity.Clients;
import com.main.txnbot.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientsRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Clients> user = userRepo.findByEmail(email);
        if (user.isEmpty()) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found");
        }

        return new UserPrincipal(user);
    }

}
