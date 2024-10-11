package com.main.txnbot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Clients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    private String clientName;
    private String email;
    private String password;
    private Long accountNumber;
    private String ifsc;
    private Long phoneNumber;

    @OneToMany(mappedBy = "clients", fetch = FetchType.EAGER)
    private Set<Transactions> transactions = new HashSet<>();

    @OneToMany(mappedBy = "clients", fetch = FetchType.EAGER)
    private Set<CardDetails> cardDetails = new HashSet<>();
}
