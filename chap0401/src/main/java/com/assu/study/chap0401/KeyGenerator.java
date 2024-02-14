package com.assu.study.chap0401;

import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;

public class KeyGenerator {
  // 8바이트 키를 생성하고 이를 16진수 문자열로 인코딩하여 문자열로 반환
//  public String StringKeyGenerator() {
//    StringKeyGenerator keyGenerator = KeyGenerators.string();
//    String salt = keyGenerator.generateKey(); // 72710d5c28db1f92
//
//    return salt;
//  }

  public String BytesKeyGenerator() {
    BytesKeyGenerator keyGenerator = KeyGenerators.secureRandom();
    byte[] key = keyGenerator.generateKey();  // [B@66223d94

    // 기본 BytesKeyGenerator 는 8바이트 길이의 키를 생성함
    int keyLength = keyGenerator.getKeyLength();  // 8

    BytesKeyGenerator keyGenerator2 = KeyGenerators.secureRandom(16);
    byte[] key2 = keyGenerator.generateKey();  // [B@66223d94

    // 16바이트 길이의 키를 생성함
    int keyLength2 = keyGenerator.getKeyLength();  // 16

    BytesKeyGenerator keyGenerator3 = KeyGenerators.shared(16);
    byte[] key3 = keyGenerator3.generateKey();
    byte[] key4 = keyGenerator3.generateKey();

    return null;

  }
}
