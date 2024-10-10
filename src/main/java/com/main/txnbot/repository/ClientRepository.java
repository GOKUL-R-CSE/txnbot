package com.main.txnbot.repository;

import com.main.txnbot.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Long, Clients> {
}
