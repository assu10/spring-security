package com.assu.study.chap0601.service;

import com.assu.study.chap0601.entity.User;
import com.assu.study.chap0601.model.CustomUserDetails;
import com.assu.study.chap0601.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Supplier<UsernameNotFoundException> supplier =
        () -> new UsernameNotFoundException("Username not Found..!");

    User user = userRepository.findByUsername(username).orElseThrow(supplier);

    System.out.println("user: " + user);
    // User 인스턴스를 CustomUserDetails 로 래핑하여 반환
    return new CustomUserDetails(user);
  }
}
