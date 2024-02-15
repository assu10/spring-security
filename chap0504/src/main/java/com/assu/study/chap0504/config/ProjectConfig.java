package com.assu.study.chap0504.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {
  // HTTP Basic 방식을 명시적으로 설정
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // 모든 요청에 인증이 필요
    //http.authorizeHttpRequests(authz -> authz.anyRequest().authenticated()).httpBasic(Customizer.withDefaults());

    // 모든 요청에 인증 없이 요청 가능
    //http.authorizeHttpRequests(authz -> authz.anyRequest().permitAll()).httpBasic(Customizer.withDefaults());

    http.authorizeHttpRequests(authz -> authz.anyRequest().authenticated()).httpBasic(c -> {
      c.realmName("OTHER"); // 인증 방식과 관련한 일부 구성 중 영역 이름을 설정
      c.authenticationEntryPoint(new CustomEntryPoint()); // 맞춤형 진입점 등록
    });

    return http.build();
  }
}
