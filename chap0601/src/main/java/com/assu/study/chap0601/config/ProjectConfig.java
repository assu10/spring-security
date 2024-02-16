package com.assu.study.chap0601.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class ProjectConfig {

  // PasswordEncoder 선언
//  @Bean
//  public BCryptPasswordEncoder bcryptPasswordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
//  // PasswordEncoder 선언
//  @Bean
//  public SCryptPasswordEncoder sCryptPasswordEncoder() {
//    return new SCryptPasswordEncoder(16384, 8, 1, 32, 64);
//  }

  // delegatingPasswordEncoding 적용 시
  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  // 양식 기반 인증 방식
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authz -> authz.anyRequest()
            .authenticated())
        .httpBasic(Customizer.withDefaults()) // HTTP Basic 인증도 사용
        .formLogin(f -> f.defaultSuccessUrl("/main", true));  // 인증 성공 시 /main 으로 이동

//    http.authorizeHttpRequests(authz -> authz.anyRequest()
//            .authenticated())
//        .httpBasic(Customizer.withDefaults())
//        .formLogin(f -> {
//          f.successHandler(customAuthenticationSuccessHandler);
//          f.failureHandler(customAuthenticationFailureHandler);
//        });

    return http.build();
  }
}
