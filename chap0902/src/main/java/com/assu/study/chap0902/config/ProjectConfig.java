package com.assu.study.chap0902.config;

import com.assu.study.chap0902.filter.StaticKeyAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class ProjectConfig {

  private final StaticKeyAuthenticationFilter filter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .addFilterAt(filter, BasicAuthenticationFilter.class)
        .authorizeHttpRequests(authz -> authz.anyRequest().permitAll()); // 인증없이 모든 요청 접근 가능
    //.httpBasic(Customizer.withDefaults());  // HTTP Basic 인증 대신 정적키 필터를 사용하므로 호출하지 않음
    return http.build();
  }
}
