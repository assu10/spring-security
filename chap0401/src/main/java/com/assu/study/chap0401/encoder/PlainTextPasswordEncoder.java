package com.assu.study.chap0401.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 암호를 plain text 취급
 */
public class PlainTextPasswordEncoder implements PasswordEncoder {
  @Override
  public String encode(CharSequence rawPassword) {
    // 암호를 변경하지 않고 그대로 반환
    return rawPassword.toString();
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    return rawPassword.equals(encodedPassword);
  }
}
