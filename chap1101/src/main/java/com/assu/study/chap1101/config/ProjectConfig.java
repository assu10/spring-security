package com.assu.study.chap1101.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {
  // DB 에 저장된 암호를 해싱하는 암호 인코더 정의
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(c -> c.disable()) // POST 를 포함한 모든 엔드포인트를 호출할 수 있게 csrf 비활성화
        .authorizeHttpRequests(authz -> authz.anyRequest().permitAll());  // 인증없이 모든 호출 허용

    return http.build();
  }
}
