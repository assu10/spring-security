package com.assu.study.chap0701.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {
  @Bean // 이 메서드가 반환하는 UserDetailsService 가 스프링 컨텍스트에 추가됨
  public UserDetailsService userDetailsService() {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

    // UserDetailsService 에서 관리하도록 사용자 추가
    UserDetails user1 = User.withUsername("assu")
        .password("1111")
        .authorities("READ")
        .build();

    UserDetails user2 = User.withUsername("silby")
        .password("2222")
        .authorities("WRITE")
        .build();

    manager.createUser(user1);
    manager.createUser(user2);

    return manager;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  // HTTP Basic 방식을 명시적으로 설정
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//    http.authorizeHttpRequests(authz ->
//            authz.anyRequest()  // 이용된 URL 이나 HTTP 방식과 관계없이 모든 요청에 대해 규칙 적용
//                .authenticated()) // 인증된 유저만 접근 허용
//        .httpBasic(Customizer.withDefaults());

//    http.authorizeHttpRequests(authz ->
//            authz.anyRequest()    // 이용된 URL 이나 HTTP 방식과 관계없이 모든 요청에 대해 규칙 적용
//                .hasAuthority("WRITE"))   // WRITE 권한이 있는 유저만 접근 허용
//        .httpBasic(Customizer.withDefaults());


    http.authorizeHttpRequests(authz ->
            authz.anyRequest()    // 이용된 URL 이나 HTTP 방식과 관계없이 모든 요청에 대해 규칙 적용
                .hasAnyAuthority("WRITE", "READ"))   // WRITE, READ 권한 중 하나라도 있는 유저만 접근 허용
        .httpBasic(Customizer.withDefaults());


//    http.authorizeHttpRequests(authz ->
//            authz.anyRequest()    // 이용된 URL 이나 HTTP 방식과 관계없이 모든 요청에 대해 규칙 적용
//            .permitAll())   // 인증 여부과 관계없이 모든 요청에 인증 없이 요청 가능
//        .httpBasic(Customizer.withDefaults());

    return http.build();
  }
}
