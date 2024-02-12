package com.assu.study.chap0206.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 권한 부여 관리를 위한 구성 클래스
 */
@Configuration
public class WebAuthorizationConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // 모든 요청에 인증이 필요
    http.authorizeHttpRequests(authz -> authz.anyRequest().authenticated()).httpBasic(Customizer.withDefaults());

    // 모든 요청에 인증 없이 요청 가능
    //http.authorizeHttpRequests(authz -> authz.anyRequest().permitAll()).httpBasic(Customizer.withDefaults());

    return http.build();
  }
}
