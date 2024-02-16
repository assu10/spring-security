package com.assu.study.chap0802.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {
  @Bean   // 이 메서드가 반환하는 UserDetailsService 가 스프링 컨텍스트에 추가됨
  public UserDetailsService userDetailsService() {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

    // UserDetailsService 가 관리하도록 사용자 추가
    UserDetails user1 = User.withUsername("assu")
        .password("1111")
        .roles("ADMIN")
        .build();

    UserDetails user2 = User.withUsername("silby")
        .password("2222")
        .roles("MANAGER")
        .build();

    manager.createUser(user1);
    manager.createUser(user2);

    return manager;
  }

  // UserDetailsService 를 재정의하면 PasswordEncoder 도 재정의해야 함
  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .csrf(AbstractHttpConfigurer::disable)  // cstf 비활성화
        .authorizeHttpRequests(authz -> {
          authz.requestMatchers("/product/{code:^[0-9]*$}").permitAll(); // 숫자로 구성된 엔드포인트는 모두 요청 허용
          authz.anyRequest().denyAll(); // 그 외의 엔드포인트는 모두 요청 거부
        })
        .httpBasic(Customizer.withDefaults());


//    http
//        .csrf(AbstractHttpConfigurer::disable)  // cstf 비활성화
//        .authorizeHttpRequests(authz -> {
//          authz.requestMatchers("/aa/bb/**").authenticated(); // /aa/bb 로 시작하는 모든 경로는 인증 필요
//          authz.anyRequest().permitAll(); // 그 외의 엔드포인트는 모두 인증 필요없이 접근 가능
//        })
//        .httpBasic(Customizer.withDefaults());

//    http
//        .csrf(AbstractHttpConfigurer::disable)  // cstf 비활성화
//        .authorizeHttpRequests(authz -> {
//          authz.requestMatchers(HttpMethod.GET, "/aa").authenticated(); // GET /aa 인증 필요
//          authz.requestMatchers(HttpMethod.POST, "/aa").permitAll(); // POST /aa 는 인증 필요없이 모두 접근 허용
//          authz.anyRequest().denyAll(); // 그 외의 엔드포인트는 모두 요청 거부
//        })
//        .httpBasic(Customizer.withDefaults());


//    http.authorizeHttpRequests(authz -> {
//          authz.requestMatchers("/hello").hasRole("ADMIN"); // /hello 는 ADMIN 역할만 접근 가능
//          authz.requestMatchers("/bye").hasRole("MANAGER"); // /bye 는 MANAGER 역할만 접근 가능
//          authz.anyRequest().permitAll(); // 그 외의 엔드포인트는 인증없이 접근 가능
//          //authz.anyRequest().authenticated(); // 그 외의 엔드포인트는 인증만 성공하면 접근 가능
//          //authz.anyRequest().denyAll(); // 그 외의 엔드포인트는 접근 불가
//        })
//        .httpBasic(Customizer.withDefaults());

    return http.build();
  }
}
