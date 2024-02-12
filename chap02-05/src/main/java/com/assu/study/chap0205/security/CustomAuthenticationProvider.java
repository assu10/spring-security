package com.assu.study.chap0205.security;


import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * AuthenticationProvider 인터페이스 구현
 */
@Component  // 구성 클래스 표시
public class CustomAuthenticationProvider implements AuthenticationProvider {
  // 인증 논리 구현
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    // Principal 인터페이스의 getName() 메서드를 Authentication 에서 상속받음
    String username = authentication.getName();
    String password = String.valueOf(authentication.getCredentials());

    // 이 조건은 일반적으로 UserDetailsService, PasswordEncoder 를 호출해서 사용자 이름과 암호를 테스트함
    // 즉, 이 조건절이 UserDetailsService, PasswordEncoder 의 책임을 대체함
    if ("assu".equals(username) && "1234".equals(password)) {
      return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList());
    } else {
      throw new AuthenticationCredentialsNotFoundException("AuthenticationCredentialsNotFoundException Error...!!");
    }
  }

  // Authentication 형식의 구현 추가
  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
