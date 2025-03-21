package br.com.calculo_de_impostos.infra.security;

import java.security.SecureRandom;
import java.util.Base64;

public class KeyGenerator {
    public void generateKeys() {
        byte[] key = new byte[32];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(key);

        String base64Key = Base64.getEncoder().encodeToString(key);
        System.out.println("Chave Base64 segura: " + base64Key);
    }
}