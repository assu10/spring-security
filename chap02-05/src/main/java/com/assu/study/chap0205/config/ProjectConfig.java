package com.assu.study.chap0205.config;

import com.assu.study.chap0205.security.CustomAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component  // 구성 클래스 표시
public class ProjectConfig {
  private final AuthenticationConfiguration authenticationConfiguration;
  private final CustomAuthenticationProvider customAuthenticationProvider;

  public ProjectConfig(AuthenticationConfiguration authenticationConfiguration, CustomAuthenticationProvider customAuthenticationProvider) {
    this.authenticationConfiguration = authenticationConfiguration;
    this.customAuthenticationProvider = customAuthenticationProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    ProviderManager providerManager = (ProviderManager) authenticationConfiguration.getAuthenticationManager();
    providerManager.getProviders().add(this.customAuthenticationProvider);
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // 모든 요청에 인증이 필요
    http.authorizeHttpRequests(authz -> authz.anyRequest().authenticated()).httpBasic(Customizer.withDefaults());

    // 모든 요청에 인증 없이 요청 가능
    //http.authorizeHttpRequests(authz -> authz.anyRequest().permitAll()).httpBasic(Customizer.withDefaults());

    return http.build();
  }
}
