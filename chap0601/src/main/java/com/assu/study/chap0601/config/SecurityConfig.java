package com.assu.study.chap0601.config;

import com.assu.study.chap0601.service.AuthenticationProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  private final AuthenticationProviderService authenticationProviderService;
  private final AuthenticationConfiguration authenticationConfiguration;

  /**
   * 맞춤 구성한 AuthenticationProviderService 구현 연결
   */
  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    ProviderManager providerManager = (ProviderManager) authenticationConfiguration.getAuthenticationManager();
    providerManager.getProviders().add(this.authenticationProviderService);
    return authenticationConfiguration.getAuthenticationManager();
  }
}
