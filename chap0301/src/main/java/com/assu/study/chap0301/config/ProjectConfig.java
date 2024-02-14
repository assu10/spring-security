package com.assu.study.chap0301.config;

import com.assu.study.chap0301.model.User;
import com.assu.study.chap0301.service.InMemoryUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class ProjectConfig {
  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails user = new User("assu", "1234", "read");
    List<UserDetails> users = List.of(user);
    return new InMemoryUserDetailsService(users);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
