package com.assu.study.chap1102.config;

import com.assu.study.chap1102.authtication.filter.InitialAuthenticationFilter;
import com.assu.study.chap1102.authtication.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {
  // filter 주입
  private final InitialAuthenticationFilter initialAuthenticationFilter;
  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  public SecurityConfig(InitialAuthenticationFilter initialAuthenticationFilter, JwtAuthenticationFilter jwtAuthenticationFilter) {
    this.initialAuthenticationFilter = initialAuthenticationFilter;
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(c -> c.disable()) // POST 를 포함한 모든 엔드포인트를 호출할 수 있게 csrf 비활성화
        .addFilterAt(initialAuthenticationFilter, BasicAuthenticationFilter.class)  // 필터 체인에 필터 추가
        .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
        .authorizeHttpRequests(authz -> authz.anyRequest().authenticated());  // 모든 요청이 인증되게 함

    return http.build();
  }
}