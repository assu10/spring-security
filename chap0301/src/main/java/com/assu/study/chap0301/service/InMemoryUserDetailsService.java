package com.assu.study.chap0301.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * 사용자 이름으로 찾은 사용자 세부 정보 반환
 */
public class InMemoryUserDetailsService implements UserDetailsService {
  // UserDetailsService 는 메모리 내의 사용자 목록을 관리
  private final List<UserDetails> users;

  public InMemoryUserDetailsService(List<UserDetails> users) {
    this.users = users;
  }

  /**
   * 주어진 사용자 이름을 목록에서 검색하여 UserDetails 인스턴스 반환
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return users.stream()
        .filter(u -> u.getUsername().equals(username))  // 사용자 목록에서 요청된 사용자 이름과 일치하는 항목 필터링
        .findFirst()  // 일치하는 사용자 반환
        .orElseThrow(() -> new UsernameNotFoundException("User not found...!"));  // 사용자 이름이 존재하지 않으면 예외 발생
  }
}
