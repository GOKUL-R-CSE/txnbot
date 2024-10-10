package com.main.txnbot.service;

import com.main.txnbot.entity.CardDetails;

public interface CardDetailsService {
    CardDetails addCard(CardDetails cardDetails);

    void deleteCard(Long pan);
}
