package com.main.txnbot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class CardDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardPanReference;
    private LocalDate cardExpiryDate;
    private String cardScheme;
    private String cardHolderName;
    private int cvv;
    private String cardType;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "client_card_details",
            joinColumns = @JoinColumn( name = "cardPanReference" ),
            inverseJoinColumns = @JoinColumn( name = "client_id" )
    )
    List<Clients> clients;
}
