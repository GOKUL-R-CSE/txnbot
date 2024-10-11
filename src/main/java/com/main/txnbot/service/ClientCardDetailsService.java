package com.main.txnbot.service;

import com.main.txnbot.entity.CardDetails;

import java.util.List;

public interface ClientCardDetailsService {
    CardDetails addCard(CardDetails cardDetails, String email);

    void deleteCard(Long pan, String email);

    List<CardDetails> getCardDetails(String email);

    CardDetails getCardDetail(String email, Long pan);
}
