package com.assu.study.chap0801.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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
  @Bean   // 이 메서드가 반환하는 UserDetailsService 가 스프링 컨텍스트에 추가됨
  public UserDetailsService userDetailsService() {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

    // UserDetailsService 가 관리하도록 사용자 추가
    UserDetails user1 = User.withUsername("assu")
        .password("1111")
        .roles("ADMIN")
        .build();

    UserDetails user2 = User.withUsername("silby")
        .password("2222")
        .roles("MANAGER")
        .build();

    manager.createUser(user1);
    manager.createUser(user2);

    return manager;
  }

  // UserDetailsService 를 재정의하면 PasswordEncoder 도 재정의해야 함
  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authz -> {
          authz.requestMatchers("/hello").hasRole("ADMIN"); // /hello 는 ADMIN 역할만 접근 가능
          authz.requestMatchers("/bye").hasRole("MANAGER"); // /bye 는 MANAGER 역할만 접근 가능
          authz.anyRequest().permitAll(); // 그 외의 엔드포인트는 인증없이 접근 가능
          //authz.anyRequest().authenticated(); // 그 외의 엔드포인트는 인증만 성공하면 접근 가능
          //authz.anyRequest().denyAll(); // 그 외의 엔드포인트는 접근 불가
        })
        .httpBasic(Customizer.withDefaults());

    return http.build();
  }
}
