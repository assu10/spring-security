package com.assu.study.chap1102.config;

import com.assu.study.chap1102.authtication.provider.OtpAuthenticationProvider;
import com.assu.study.chap1102.authtication.provider.UsernamePasswordAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;

import java.util.Arrays;

@Configuration
public class ProviderConfig {

  // AuthenticationProvider 주입
  private final UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;
  private final OtpAuthenticationProvider otpAuthenticationProvider;

  public ProviderConfig(UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider, OtpAuthenticationProvider otpAuthenticationProvider) {
    this.usernamePasswordAuthenticationProvider = usernamePasswordAuthenticationProvider;
    this.otpAuthenticationProvider = otpAuthenticationProvider;
  }

  /**
   * 맞춤 구성한 AuthenticationProviderService 구현 연결
   */
  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    return new ProviderManager(Arrays.asList(this.usernamePasswordAuthenticationProvider, this.otpAuthenticationProvider));
  }
}