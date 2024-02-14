package com.assu.study.chap0401;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

public class CustomEncryptors {
//  public void textEncryptor() {
//    // StringKeyGenerator
//    String salt = KeyGenerators.string().generateKey();
//
//    String password = "password";
//    String valueToEncrypt = "HELLO";
//
//    BytesEncryptor encryptor = Encryptors.standard(password, salt);
//    byte[] encrypted = encryptor.encrypt(valueToEncrypt.getBytes());
//    byte[] decrypted = encryptor.decrypt(encrypted);
//  }


  public void customTextEncryptor() {
    // StringKeyGenerator
    String salt = KeyGenerators.string().generateKey();
    String password = "password";
    String valueToEncrypt = "HELLO";

    TextEncryptor encryptor = Encryptors.text(password, salt);
    String encrypted = encryptor.encrypt(valueToEncrypt); // fe8ecccfc913766c869fc41f2bb111046007029487bec447e92f36ce1d528406
    String decrypted = encryptor.decrypt(encrypted);  // HELLO
  }
}
