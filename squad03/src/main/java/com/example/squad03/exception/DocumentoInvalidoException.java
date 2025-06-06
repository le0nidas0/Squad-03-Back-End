package com.example.squad03.exception;

public class DocumentoInvalidoException extends RuntimeException {
    public DocumentoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
