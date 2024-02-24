package com.assu.study.chap1102.authtication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * 사용자 이름/OTP를 이용한 인증을 구현하는 클래스
 * Authentication 는 간접적으로 확장됨
 */
public class OtpAuthentication extends UsernamePasswordAuthenticationToken {
  public OtpAuthentication(Object principal, Object credentials) {
    super(principal, credentials);
  }

//  public OtpAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
//    super(principal, credentials, authorities);
//  }
}
