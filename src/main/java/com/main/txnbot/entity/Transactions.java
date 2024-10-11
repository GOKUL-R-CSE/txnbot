package com.main.txnbot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionReference;

    private String transactionCategory;
    private String terminalCategory;
    private Long transactionAmount;
    private LocalDateTime transactionDate;
    private LocalDateTime settlementDate;
    private String transactionCurrency;
    private String settlementCurrency;
    private Long interChangeFee;
    private String transactionStatus;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "client_transactions",
            joinColumns = @JoinColumn( name = "transactionReference" ),
            inverseJoinColumns = @JoinColumn( name = "client_id" )
    )
    private Clients clients;
}
