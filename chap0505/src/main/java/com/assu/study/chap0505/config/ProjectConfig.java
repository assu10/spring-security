package com.assu.study.chap0505.config;

import com.assu.study.chap0505.handler.CustomAuthenticationFailureHandler;
import com.assu.study.chap0505.handler.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {
  private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
  private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

  public ProjectConfig(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler, CustomAuthenticationFailureHandler customAuthenticationFailureHandler) {
    this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
  }

  // 양식 기반 인증 방식
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http.authorizeHttpRequests(authz -> authz.anyRequest()
//            .authenticated())
//        .formLogin(f -> f.defaultSuccessUrl("/home", true));  // 인증 성공 시 /home 으로 이동

    http.authorizeHttpRequests(authz -> authz.anyRequest()
            .authenticated())
        .httpBasic(Customizer.withDefaults())
        .formLogin(f -> {
          f.successHandler(customAuthenticationSuccessHandler);
          f.failureHandler(customAuthenticationFailureHandler);
        });

    return http.build();
  }
}
