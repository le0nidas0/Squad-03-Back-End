package com.example.squad03.exception;

public class ErroNegocioException extends RuntimeException {
    public ErroNegocioException(String mensagem) {
        super(mensagem);
    }
}
