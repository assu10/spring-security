package com.assu.study.chap1102.authtication.provider;

import com.assu.study.chap1102.authtication.OtpAuthentication;
import com.assu.study.chap1102.authtication.proxy.AuthenticationServerProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * 사용자 이름/OTP의 인증 논리 구현 클래스
 */
@RequiredArgsConstructor
@Component  // 이 형식의 인스턴스를 컨텍스트에 포함시킴
public class OtpAuthenticationProvider implements AuthenticationProvider {

  private final AuthenticationServerProxy authenticationServerProxy;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String code = String.valueOf(authentication.getCredentials());

    boolean result = authenticationServerProxy.sendOtp(username, code);

    // OTP 가 맞으면 Authentication 인스턴스를 반환하고, 필터가 HTTP 응답에 토큰을 보냄
    if (result) {
      // 아직 `Authentication` 객체가 인증된 상태가 아니므로
      // 매개 변수가 2개인
      // `OtpAuthentication(Object principal, Object credentials)` 생성자를 이용하여
      // `Authentication` 객체 생성
      return new OtpAuthentication(username, code);
    } else {
      // OTP 가 틀리면 예외 발생
      throw new BadCredentialsException("bad credentials");
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
    return OtpAuthentication.class.isAssignableFrom(authentication);
  }
}
