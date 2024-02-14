package com.assu.study.chap0301.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 사용자 기술
 */
public class User implements UserDetails {
  private final String username;
  private final String password;
  private final String authority; // 간단히 하기 위해 한 사용자에게 하나의 권한만 적용

  public User(String username, String password, String authority) {
    this.username = username;
    this.password = password;
    this.authority = authority;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // 인스턴스 생성 시 지정한 이름의 GrantedAuthority 객체만 포함하는 목록 반환
    return List.of(() -> authority);
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    // 계정이 만료되지 않음
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // 계정이 잠기지 않음
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // 자격 증명이 만료되지 않음
    return true;
  }

  @Override
  public boolean isEnabled() {
    // 계정이 비활성화되지 않음
    return true;
  }
}
