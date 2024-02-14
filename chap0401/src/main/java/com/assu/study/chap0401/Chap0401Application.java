package com.assu.study.chap0401;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

@SpringBootApplication
public class Chap0401Application {

  public static void main(String[] args) {
    SpringApplication.run(Chap0401Application.class, args);

    String salt = KeyGenerators.string().generateKey();
    String password = "password";
    String valueToEncrypt = "HELLO";

    TextEncryptor encryptor = Encryptors.text(password, salt);
    String encrypted = encryptor.encrypt(valueToEncrypt);
    String decrypted = encryptor.decrypt(encrypted);

    System.out.println(encrypted);
    System.out.println(decrypted);
  }

}
