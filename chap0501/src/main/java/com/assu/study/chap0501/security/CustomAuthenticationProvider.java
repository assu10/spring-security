package com.assu.study.chap0501.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * `AuthenticationProvider` 계약을 구현하는 클래스
 */
@Component  // 이 형식의 인스턴스를 컨텍스트에 포함시킴
public class CustomAuthenticationProvider implements AuthenticationProvider {
  
  private final UserDetailsService userDetailsService;
  private final PasswordEncoder passwordEncoder;

  public CustomAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
    this.userDetailsService = userDetailsService;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * 인증 논리 구현
   */
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();

    // UserDetails 를 가져오기 위해 UserDetailsService 구현 이용
    // 사용자가 존재하지 않으면
    //     loadUserByUsername() 는 AuthenticationException 예외 발생시킴
    //     인증 프로세스가 중단되고 HTTP 필터는 401 리턴
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

    // 사용자가 존재하면 matches() 로 암호 확인
    if (passwordEncoder.matches(password, userDetails.getPassword())) {
      // 암호가 일치하면 AuthenticationProvider 는
      // 필요한 세부 정보가 담긴 Authentication 계약의 구현을 '인증됨' 으로 표시한 후 반환함
      return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    } else {
      // 암호가 일치하지 않으면 AuthenticationException 형식의 예외 발생
      throw new BadCredentialsException("BadCredentialsException...!!");
    }
  }

  /**
   * AuthenticationProvider 가 어떤 종류의 Authentication 인터페이스를 지원할 지 결정
   * 이는 authenticate() 메서드의 매개 변수로 어떤 형식이 제공될지에 따라서 달라짐
   * <p>
   * AuthenticationFilter 수준에서 아무것도 맞춤 구성하지 않으면 UsernamePasswordAuthenticationToken 클래스가 형식을 정의함
   */
  @Override
  public boolean supports(Class<?> authentication) {
    // UsernamePasswordAuthenticationToken 는 Authentication 인터페이스의 한 구현이며,
    // 사용자 이름과 암호를 이용하는 표준 인증 요청을 나타냄
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
