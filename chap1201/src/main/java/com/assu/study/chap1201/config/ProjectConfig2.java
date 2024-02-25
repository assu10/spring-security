package com.assu.study.chap1201.config;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class ProjectConfig2 {

  //@Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .oauth2Login(Customizer.withDefaults()) // 인증 메서드 추가
        .authorizeHttpRequests(authz -> authz.anyRequest().authenticated());  // 모든 요청이 인증되게 함

    return http.build();
  }
}
