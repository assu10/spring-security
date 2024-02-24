package com.assu.study.chap1102.config;

import com.assu.study.chap1102.authtication.provider.OtpAuthenticationProvider;
import com.assu.study.chap1102.authtication.provider.UsernamePasswordAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
public class ProviderConfig {

  private final AuthenticationConfiguration authenticationConfiguration;
  private final UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;
  private final OtpAuthenticationProvider otpAuthenticationProvider;

  public ProviderConfig(AuthenticationConfiguration authenticationConfiguration, UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider, OtpAuthenticationProvider otpAuthenticationProvider) {
    this.authenticationConfiguration = authenticationConfiguration;
    this.usernamePasswordAuthenticationProvider = usernamePasswordAuthenticationProvider;
    this.otpAuthenticationProvider = otpAuthenticationProvider;
  }

  /**
   * 맞춤 구성한 AuthenticationProviderService 구현 연결
   */
  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    ProviderManager providerManager = (ProviderManager) authenticationConfiguration.getAuthenticationManager();
    providerManager.getProviders().add(this.usernamePasswordAuthenticationProvider);
    return authenticationConfiguration.getAuthenticationManager();

//    ProviderManager providerManager = (ProviderManager) authenticationConfiguration.getAuthenticationManager();
//    providerManager.getProviders().add(this.otpAuthenticationProvider);
//    providerManager.getProviders().add(this.usernamePasswordAuthenticationProvider);
//    //.addAll(List.of(this.usernamePasswordAuthenticationProvider, this.otpAuthenticationProvider));
//    return authenticationConfiguration.getAuthenticationManager();
  }
}


//@Bean
//public AuthenticationManager authenticationManager() throws Exception {
//  ProviderManager providerManager = (ProviderManager) authenticationConfiguration.getAuthenticationManager();
//  providerManager.getProviders().add(this.authenticationProviderService);
//  return authenticationConfiguration.getAuthenticationManager();
//}