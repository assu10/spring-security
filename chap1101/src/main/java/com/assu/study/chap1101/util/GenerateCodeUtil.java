package com.assu.study.chap1101.util;

import lombok.NoArgsConstructor;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@NoArgsConstructor
public final class GenerateCodeUtil {

  public static String generateCode() {
    String code;

    try {
      // 임의의 int 값을 생성하는 SecureRandom 인스턴스 생성
      SecureRandom random = SecureRandom.getInstanceStrong();

      // 0~8,999 사이의 값을 생성하고 1,000을 더해서 1,000~9,999 (4자리 코드) 사이의 값 얻음
      int c = random.nextInt(9000) + 1000;

      // int 를 String 응로 변환하여 반환
      code = String.valueOf(c);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Error when generating the random code.");
    }

    return code;
  }
}
