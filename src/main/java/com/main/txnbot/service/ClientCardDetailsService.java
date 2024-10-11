package com.main.txnbot.service;

import com.main.txnbot.entity.CardDetails;

public interface ClientCardDetailsService {
    CardDetails addCard(CardDetails cardDetails, String email);

    void deleteCard(Long pan, String email);
}
