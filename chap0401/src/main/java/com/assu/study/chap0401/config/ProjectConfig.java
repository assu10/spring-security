package com.assu.study.chap0401.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProjectConfig {
  // DelegatingPasswordEncoder 정의
  @Bean
  public PasswordEncoder passwordEncoder() {
    // 기본 인코더는 bcrypt
    //PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    Map<String, PasswordEncoder> encoders = new HashMap<>();

    encoders.put("noop", NoOpPasswordEncoder.getInstance());
    encoders.put("bcrypt", new BCryptPasswordEncoder(4));
    encoders.put("scrypt", new SCryptPasswordEncoder(16384, 8, 1, 32, 64));

    // 만일 접두사가 없으면 BCryptPasswordEncoder 구현에 작업 위임
    return new DelegatingPasswordEncoder("bcrypt", encoders);
  }
}
