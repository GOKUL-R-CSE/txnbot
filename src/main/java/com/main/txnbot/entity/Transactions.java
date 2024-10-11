package com.main.txnbot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID transactionReference;

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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "client_transactions",
            joinColumns = @JoinColumn( name = "transaction_id" ),
            inverseJoinColumns = @JoinColumn( name = "client_id" )
    )
    private List<Clients> clients = new ArrayList<>();
}
