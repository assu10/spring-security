package com.assu.study.chap0401.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 해싱 알고리즘 SHA-512 를 이용
 */
public class Sha512PasswordEncoder implements PasswordEncoder {
  @Override
  public String encode(CharSequence rawPassword) {
    return hashedSha512(rawPassword.toString());
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    String hashedPassword = encode(rawPassword);
    return encodedPassword.equals(hashedPassword);
  }

  private String hashedSha512(String input) {
    StringBuilder result = new StringBuilder();
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-512");
      byte[] digested = md.digest(input.getBytes());
      for (int i = 0; i < digested.length; i++) {
        result.append(Integer.toHexString(0xFF & digested[i]));
      }
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
    return result.toString();
  }

  private void otherEncoding() {
    //PasswordEncoder p1 = new Pbkdf2PasswordEncoder("secret", 18500, 267, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512);

//    PasswordEncoder p1 = new BCryptPasswordEncoder();
//    // 로그 라운드를 나타내는 강도 계수 지정
//    PasswordEncoder p2 = new BCryptPasswordEncoder(4);
//
//    // 인코딩에 이용되는 SecureRandom 인스턴스를 변경
//    SecureRandom s = SecureRandom.getInstanceStrong();
//    PasswordEncoder p3 = new BCryptPasswordEncoder(4, s);
//    PasswordEncoder p4 = new BCryptPasswordEncoder();

    PasswordEncoder p1 = new SCryptPasswordEncoder(16384, 8, 1, 32, 64);
  }
}
