package com.assu.study.chap0803.config;

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
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
public class ProjectConfig {
  @Bean   // 이 메서드가 반환하는 UserDetailsService 가 스프링 컨텍스트에 추가됨
  public UserDetailsService userDetailsService() {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

    // UserDetailsService 가 관리하도록 사용자 추가
    UserDetails user1 = User.withUsername("assu")
        .password("1111")
        .roles("NORMAL")
        .build();

    UserDetails user2 = User.withUsername("silby")
        .password("2222")
        .roles("NORMAL", "SUPER")
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

    http
        .authorizeHttpRequests(authz -> {
          authz.requestMatchers(RegexRequestMatcher.regexMatcher(".*/(us|uk|ca)+/(en|fr).*")).authenticated();  // 미국,영국,캐나다이면서 영어권인 경우 인증 후 접근 가능
          authz.anyRequest().hasRole("SUPER"); // 그 외 엔드포인트는 SUPER 권한이 있으면 모두 다 접근 가능
        })
        .httpBasic(Customizer.withDefaults());
    return http.build();
  }
}
