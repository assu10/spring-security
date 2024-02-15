package com.assu.study.chap0501.config;

import com.assu.study.chap0501.security.CustomAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
public class SecurityConfig {
  private final AuthenticationConfiguration authenticationConfiguration;
  private final CustomAuthenticationProvider customAuthenticationProvider;

  public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, CustomAuthenticationProvider customAuthenticationProvider) {
    this.authenticationConfiguration = authenticationConfiguration;
    this.customAuthenticationProvider = customAuthenticationProvider;
  }

  /**
   * 맞춤 구성한 CustomAuthenticationProvider 구현 연결
   */
  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    ProviderManager providerManager = (ProviderManager) authenticationConfiguration.getAuthenticationManager();
    providerManager.getProviders().add(this.customAuthenticationProvider);
    return authenticationConfiguration.getAuthenticationManager();
  }
}
