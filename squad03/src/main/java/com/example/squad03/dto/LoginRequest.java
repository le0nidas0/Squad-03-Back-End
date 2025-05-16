package com.example.squad03.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String senha;
}