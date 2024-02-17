package com.assu.study.chap0901.config;

import com.assu.study.chap0901.filter.AuthenticationLoggingFilter;
import com.assu.study.chap0901.filter.RequestValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ProjectConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class)  // 인증 필터 앞에 맞춤형 필터 추가
        .addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class) // 인증 필터 뒤에 맞춤형 필터 추가
        .authorizeHttpRequests(authz -> authz.anyRequest().permitAll()) // 인증없이 모든 요청 접근 가능
        .httpBasic(Customizer.withDefaults());
    return http.build();
  }
}