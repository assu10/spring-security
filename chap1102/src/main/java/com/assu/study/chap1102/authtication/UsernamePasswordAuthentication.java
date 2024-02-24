package com.assu.study.chap1102.authtication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 사용자 이름/암호를 이용한 인증을 구현하는 클래스
 * Authentication 는 간접적으로 확장됨
 */
public class UsernamePasswordAuthentication extends UsernamePasswordAuthenticationToken {

  /**
   * 인증 인스턴스가 인증되지 않은 상태로 유지됨
   * <p>
   * Authentication 객체가 인증 상태로 설정되지 않고, 프로세스 중 예외로 발생하지 않았다면
   * AuthenticationManager 는 요청을 인증할 AuthenticationProvider 객체를 찾음
   */
  public UsernamePasswordAuthentication(Object principal, Object credentials) {
    super(principal, credentials);
  }

  /**
   * Authentication 객체가 인증됨 (= 인증 프로세스가 완료됨)
   * <p>
   * AuthenticationProvider 객체가 요청을 인증할 때는 이 생성자를 호출하여 Authentication 인스턴스를 만들고,
   * 이 때는 인증된 객체가 됨
   *
   * @param authorities 허가된 권한의 컬렉션이며, 완료된 인증 프로세스에서는 필수임
   */
  public UsernamePasswordAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
    super(principal, credentials, authorities);
  }
}
