package com.assu.study.chap1002.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {
  @Bean
  public UserDetailsService customUserDetailsService() {
    InMemoryUserDetailsManager uds = new InMemoryUserDetailsManager();

    UserDetails user = User.withUsername("assu")
        .password("1111")
        .roles("ADMIN")
        .build();

    uds.createUser(user);

    return uds;
  }

  // UserDetailsService 를 재정의하면 PasswordEncoder 도 재정의해야함
  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authz -> authz.anyRequest().authenticated()) // 인증된 사용자만 엔드포인트에 접근 가능
        .formLogin(f -> f.defaultSuccessUrl("/main", true));  // 양식 기반 로그인 인증 방식을 사용하고, 인증 성공 시 /main 으로 이동

    return http.build();
  }
}