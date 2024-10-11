package com.main.txnbot.serviceImpl;

import com.main.txnbot.entity.CardDetails;
import com.main.txnbot.entity.Clients;
import com.main.txnbot.exception.ResourceAlreadyExistsException;
import com.main.txnbot.exception.ResourceNotFoundException;
import com.main.txnbot.repository.CardDetailsRepository;
import com.main.txnbot.repository.ClientsRepository;
import com.main.txnbot.service.ClientCardDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientCardDetailsServiceImpl implements ClientCardDetailsService {

    @Autowired
    private CardDetailsRepository cardDetailsRepository;

    @Autowired
    private ClientsRepository clientsRepository;

    @Override
    public CardDetails addCard(CardDetails cardDetails, String email) {
        Clients client = clientsRepository.findByEmail(email).orElseThrow(() -> new ResourceAlreadyExistsException("Client", "this email"));
        CardDetails card = cardDetailsRepository.findByCardPanReference(cardDetails.getCardPanReference()).orElseThrow(() -> new ResourceAlreadyExistsException("Card", "number"));
        client.getCardDetails().add(cardDetails);
        return cardDetailsRepository.save(cardDetails);
    }

    @Override
    public void deleteCard(Long pan, String email) {
        Clients client = clientsRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Client", "this email"));
        CardDetails card = cardDetailsRepository.findByCardPanReference(pan).orElseThrow(() -> new ResourceNotFoundException("Card", "number"));
        if (!client.getCardDetails().contains(card)) {
            throw new ResourceNotFoundException("Card", "client");
        }
        client.getCardDetails().remove(card);
        cardDetailsRepository.delete(card);
    }
}
