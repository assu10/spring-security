package com.assu.study.chap1301.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * 사용자 관리 구성 클래스
 */
@Configuration
public class WebSecurityConfig {

  @Bean
  public UserDetailsService customUserDetailsService() {
    // UserDetailsService 로써 InMemoryUserDetailsManager 선언
    InMemoryUserDetailsManager uds = new InMemoryUserDetailsManager();

    UserDetails user = User.withUsername("assu")
        .password("1111")
        .authorities("read")
        .build();

    uds.createUser(user);

    return uds;
  }

  // UserDetailsService 를 재정의하면 PasswordEncoder 도 재정의해야함
  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  // AuthenticationManager 를 스프링 컨텍스트에 빈으로 노출시킨 후 AuthServerConfig 에서 사용
  @Bean
  public AuthenticationManager authenticationManager() {
    return new ProviderManager();
  }
}
