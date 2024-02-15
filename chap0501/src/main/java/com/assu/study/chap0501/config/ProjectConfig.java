package com.assu.study.chap0501.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class ProjectConfig {
  @Bean
  public UserDetailsService userDetailsService(DataSource dataSource) {
    //return new JdbcUserDetailsManager(dataSource);

    String usersByUsernameQuery = "select username, password, enabled from security.users where username = ?";
    String authsByUserQuery = "select username, authority from security.authorities where username = ?";

    JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
    userDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
    userDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);

    return userDetailsManager;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

}
