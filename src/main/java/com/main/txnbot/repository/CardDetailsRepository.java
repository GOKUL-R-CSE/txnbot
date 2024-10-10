package com.main.txnbot.repository;

import com.main.txnbot.entity.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardDetailsRepository extends JpaRepository<Long, CardDetails> {
}
