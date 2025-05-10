package com.example.squad03.util;

public class ValidadorDocumentoUtil {

    private static String apenasNumeros(String documento) {
        return documento.replaceAll("\\D", ""); // Remove tudo que não for número
    }

    public static boolean isCpfValido(String cpf) {
        cpf = apenasNumeros(cpf); // <--- adiciona isso

        if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int soma1 = 0;
            for (int i = 0; i < 9; i++) soma1 += (cpf.charAt(i) - '0') * (10 - i);
            int dig1 = soma1 % 11;
            dig1 = (dig1 < 2) ? 0 : 11 - dig1;

            int soma2 = 0;
            for (int i = 0; i < 10; i++) soma2 += (cpf.charAt(i) - '0') * (11 - i);
            int dig2 = soma2 % 11;
            dig2 = (dig2 < 2) ? 0 : 11 - dig2;

            return cpf.charAt(9) - '0' == dig1 && cpf.charAt(10) - '0' == dig2;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isCnpjValido(String cnpj) {
        cnpj = apenasNumeros(cnpj); // <--- adiciona isso

        if (cnpj == null || cnpj.length() != 14 || cnpj.matches("(\\d)\\1{13}")) return false;

        try {
            int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

            int soma1 = 0;
            for (int i = 0; i < 12; i++) soma1 += (cnpj.charAt(i) - '0') * pesos1[i];
            int dig1 = soma1 % 11;
            dig1 = (dig1 < 2) ? 0 : 11 - dig1;

            int soma2 = 0;
            for (int i = 0; i < 13; i++) soma2 += (cnpj.charAt(i) - '0') * pesos2[i];
            int dig2 = soma2 % 11;
            dig2 = (dig2 < 2) ? 0 : 11 - dig2;

            return cnpj.charAt(12) - '0' == dig1 && cnpj.charAt(13) - '0' == dig2;
        } catch (Exception e) {
            return false;
        }
    }
}
