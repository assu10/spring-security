//package com.assu.study.chap1301.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//
///**
// * 인증 구성 클래스
// */
//@Configuration
////@EnableAuthorizationServer  // 스프링 부트에 OAuth 2 권한 부여 서버에 관한 구성을 활성화하도록 지시
//public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
//
//  private final AuthenticationManager authenticationManager;
//
//  public AuthServerConfig(AuthenticationManager authenticationManager) {
//    this.authenticationManager = authenticationManager;
//  }
//
//  // 권한 부여 서버에 AuthenticationManager 를 등록
//  @Override
//  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//    endpoints.authenticationManager(authenticationManager);
//  }
//
//  // 클라이언트 구성을 정의하고, InMemoryClientDetailsService 를 이용해 설정
//  // ClientDetailsService 인스턴스를 설정하도록 configurer() 메서드 재정의
//  @Override
//  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//    clients.inMemory()  // ClientDetailsService 구현을 이용하여 메모리에 저장된 ClientDetails 관리
//        .withClient("client")
//        .secret("secret")
//        .authorizedGrantTypes("password", "refresh_token")
//        .scopes("read");
//  }
//
//  // 클라이언트 구성을 정의하고, InMemoryClientDetailsService 를 이용해 설정
//  // ClientDetailsService 인스턴스를 설정하도록 configurer() 메서드 재정의
////  @Override
////  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
////    // ClientDetailsService 구현을 이용하여 인스턴스 생성
////    InMemoryClientDetailsService service = new InMemoryClientDetailsService();
////
////    // ClientDetails 의 인스턴스를 만든 후 클라이언트에 대한 필수 세부 정보 설정
////    BaseClientDetails cd = new BaseClientDetails();
////    cd.setClientId("client");
////    cd.setClientSecret("secret");
////    cd.setScope(List.of("read"));
////    cd.setAuthorizedGrantTypes(List.of("password"));
////
////    // InMemoryClientDetailsService 에 ClientDetails 인스턴스 추가
////    service.setClientDetailsStore(Map.of("client", cd));
////
////    // 권한 부여 서버에서 사용할 수 있게 ClientDetailsService 구성
////    clients.withClientDetails(service);
////  }
//}
