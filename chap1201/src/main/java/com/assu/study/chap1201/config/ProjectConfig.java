package com.assu.study.chap1201.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

  @Bean // ClientRegistrationRepository 형식의 빈을 스프링 컨텍스트에 추가, 이 빈에 ClientRegistration 에 대한 참조가 있음
  public ClientRegistrationRepository clientRegistrationRepository() {
    ClientRegistration cr = clientRegistration();

    // ClientRegistration 인스턴스는 매개변수로 하여 생성
    return new InMemoryClientRegistrationRepository(cr);
  }

  // ClientRegistration 객체를 반환하는 메서드를 구성 클래스에 추가
  private ClientRegistration clientRegistration() {
    ClientRegistration cr = CommonOAuth2Provider.GITHUB
        .getBuilder("github") // 클라이언트 등록을 위한 ID 제공
        .clientId("be4d2d061d05e9aed30a")
        .clientSecret("3a3b00faf240377a9630d085ac742e3efc8293e6")
        .build(); // ClientRegistration 인스턴스 생성
    return cr;
  }

  // 복잡해서 사용하지 않을 예정인 코드
//  private ClientRegistration clientRegistration() {
//    ClientRegistration cr = ClientRegistration.withRegistrationId("github")
//        .clientId("be4d2d061d05e9aed30a")
//        .clientSecret("3a3b00faf240377a9630d085ac742e3efc8293e6")
//        .scope("read:user")
//        .authorizationUri("https://github.com/login/oauth/authorize") // 클라이언트가 인증을 위해 사용자를 리디렉션하는 URI
//        .tokenUri("https://github.com/login/oauth/access_token")  // 액세스 토큰과 갱신 토큰을 얻기 위해 호출하는 URI
//        .userInfoUri("https://api.github.com/user") // 클라이언트가 액세스 토큰을 얻은 후 사용자의 세부 정보를 얻기 위해 호출하는 URI
//        .userNameAttributeName("id")
//        .clientName("GitHub")
//        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//        .redirectUri("{baseUrl}/{action}/oauth2/code/{registrationId}")
//        .build();
//    return cr;
//  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .oauth2Login(Customizer.withDefaults()) // 인증 메서드 추가
        .authorizeHttpRequests(authz -> authz.anyRequest().authenticated());  // 모든 요청이 인증되게 함

    return http.build();
  }

//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http
//        .oauth2Login(c -> c.clientRegistrationRepository(clientRegistrationRepository())) // 인증 메서드 추가
//        .authorizeHttpRequests(authz -> authz.anyRequest().authenticated());  // 모든 요청이 인증되게 함
//
//    return http.build();
//  }
}
