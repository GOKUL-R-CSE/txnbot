package com.main.txnbot.serviceImpl;

import com.main.txnbot.entity.CardDetails;
import com.main.txnbot.exception.ResourceAlreadyExistsException;
import com.main.txnbot.exception.ResourceNotFoundException;
import com.main.txnbot.repository.CardDetailsRepository;
import com.main.txnbot.service.CardDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardDetailsServiceImpl implements CardDetailsService {

    @Autowired
    private CardDetailsRepository repository;

    @Override
    public CardDetails addCard(CardDetails cardDetails) {
        CardDetails card = repository.findByCardPanReference(cardDetails.getCardPanReference()).orElseThrow(() -> new ResourceAlreadyExistsException("Card", "number"));
        return repository.save(cardDetails);
    }

    @Override
    public void deleteCard(Long pan) {
        CardDetails card = repository.findByCardPanReference(pan).orElseThrow(() -> new ResourceNotFoundException("Card", "number"));
        repository.deleteByPan(pan);
    }
}
