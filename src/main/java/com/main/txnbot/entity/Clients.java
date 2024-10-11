package com.main.txnbot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @ManyToMany(mappedBy = "clients", fetch = FetchType.EAGER)
    private Set<Transactions> transactions = new HashSet<>();

    @ManyToMany(mappedBy = "clients", fetch = FetchType.EAGER)
    private Set<CardDetails> cardDetails = new HashSet<>();
}