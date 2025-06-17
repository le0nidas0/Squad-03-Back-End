package com.example.squad03.service;

public interface EmailService {
    void enviarAvisoContrato(String destino, String nomeResponsavel, String tituloContrato);
    void enviarNotificacaoResponsavelContrato(String email, String nomeResponsavel, Long idContrato);
    void enviarEmailBoasVindas(String email, String nome);
}
