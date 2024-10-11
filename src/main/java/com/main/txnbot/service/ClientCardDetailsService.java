package com.main.txnbot.service;

import com.main.txnbot.entity.CardDetails;

import java.util.List;
import java.util.UUID;

public interface ClientCardDetailsService {
    CardDetails addCard(CardDetails cardDetails, String email);

    void deleteCard(UUID pan, String email);

    List<CardDetails> getCardDetails(String email);

    CardDetails getCardDetail(String email, UUID pan);
}
