package com.assu.study.chap1702.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableMethodSecurity(prePostEnabled = true)
@Configuration
public class ProjectConfig {
  @Bean
  public UserDetailsService userDetailsService() {
    // UserDetailsService 로써 InMemoryUserDetailsManager 선언
    InMemoryUserDetailsManager uds = new InMemoryUserDetailsManager();

    UserDetails user1 = User.withUsername("assu")
        .password("1111")
        .roles("manager")
        .build();

    UserDetails user2 = User.withUsername("silby")
        .password("1111")
        .roles("admin")
        .build();

    uds.createUser(user1);
    uds.createUser(user2);

    return uds;
  }

  // UserDetailsService 를 재정의하면 PasswordEncoder 도 재정의해야함
  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
