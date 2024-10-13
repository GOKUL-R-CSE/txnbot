package com.main.txnbot.dto;

import com.main.txnbot.entity.Clients;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthResponse {

    private Clients client;
    private String token;
}
