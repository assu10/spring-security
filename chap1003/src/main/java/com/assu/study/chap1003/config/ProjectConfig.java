package com.assu.study.chap1003.config;

import com.assu.study.chap1003.csrf.CustomCsrfTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ProjectConfig {

  // CsrfTokenRepository 를 컨텍스트 빈으로 정의
  @Bean
  public CsrfTokenRepository customTokenRepository() {
    return new CustomCsrfTokenRepository();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //XorCsrfTokenRequestAttributeHandler requestHandler = new XorCsrfTokenRequestAttributeHandler();
    // POST /world 는 CSRF 보호 제외
    http
        .csrf(c -> {
          c.csrfTokenRepository(customTokenRepository()); // 맞춤 구성한 CsrfTokenRepository 연결
          c.ignoringRequestMatchers("/world");  // /world 경로는 CSRF 보호 제외
        })
        .authorizeHttpRequests(authz -> authz.anyRequest().permitAll());  // 그 외의 경로는 인증없이 모두 허용


    return http.build();
  }
}