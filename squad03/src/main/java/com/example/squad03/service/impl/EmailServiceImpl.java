package com.example.squad03.service.impl;

import com.example.squad03.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void enviarAvisoContrato(String destino, String nomeResponsavel, String tituloContrato) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(destino);
        mensagem.setSubject("Aviso: Contrato próximo do vencimento");
        mensagem.setText("Olá " + nomeResponsavel + ",\n\n" +
                "O contrato \"" + tituloContrato + "\" está próximo do vencimento. Por favor, tome as providências necessárias.\n\n" +
                "Atenciosamente,\nEquipe GetInfo");

        mailSender.send(mensagem);
    }

    @Override
    public void enviarNotificacaoResponsavelContrato(String email, String nomeResponsavel, Long idContrato) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(email);
        mensagem.setSubject("Você foi atribuído como responsável por um contrato");
        mensagem.setText("Olá " + nomeResponsavel + ",\n\n" +
                "Você foi atribuído como responsável pelo contrato de ID: " + idContrato + ".\n" +
                "Acesse o sistema ContractManager para visualizar os detalhes do contrato.\n\n" +
                "Atenciosamente,\nEquipe GetInfo");

        mailSender.send(mensagem);
    }

    @Override
    public void enviarEmailBoasVindas(String email, String nome) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(email);
        mensagem.setSubject("Bem-vindo ao Gerenciador de Cotnratos - GetInfo");
        mensagem.setText("Olá " + nome + ",\n\n" +
                "Você foi adicionado ao nosso sistema! Estamos felizes em tê-lo conosco.\n" +
                "Explore nossos recursos e comece a gerenciar seus contratos de forma eficiente.\n" +
                "Faça login agora mesmo! https://squad-03-front-end.onrender.com/\n\n" +
                "Atenciosamente,\nEquipe GetInfo");

        mailSender.send(mensagem);
    }
}
