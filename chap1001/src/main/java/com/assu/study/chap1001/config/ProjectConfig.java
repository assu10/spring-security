package com.assu.study.chap1001.config;

import com.assu.study.chap1001.filter.CsrfTokenLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
public class ProjectConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .addFilterAfter(new CsrfTokenLogger(), CsrfFilter.class)
        .authorizeHttpRequests(authz -> authz.anyRequest().permitAll()); // 인증없이 모든 요청 접근 가능
    //.httpBasic(Customizer.withDefaults());  // HTTP Basic 인증 대신 정적키 필터를 사용하므로 호출하지 않음
    // .csrf(AbstractHttpConfigurer::disable)  // cstf 비활성화를 하지 않음
    return http.build();
  }
}
