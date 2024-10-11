package com.main.txnbot.repository;

import com.main.txnbot.entity.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardDetailsRepository extends JpaRepository<CardDetails, UUID> {
    Optional<CardDetails> findByCardPanReference(UUID cardPanReference);

    void deleteByCardPanReference(UUID pan);

    boolean existsByCardPanReference(UUID cardPanReference);
}
