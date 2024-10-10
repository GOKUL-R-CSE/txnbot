package com.main.txnbot.repository;

import com.main.txnbot.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientsRepository extends JpaRepository<Clients, Long> {

    Optional<Clients> findByEmail(String email);

    void deleteByEmail(String email);
}
