package com.main.txnbot.repository;

import com.main.txnbot.entity.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardDetailsRepository extends JpaRepository<CardDetails, Long> {
    Optional<CardDetails> findByCardPanReference(Long cardPanReference);

    void deleteByPan(Long pan);
}
