package com.main.txnbot.service;

import com.main.txnbot.entity.Clients;

public interface UserDetailsService {

    Clients register(Clients user);

    String verify(Clients user);
}
