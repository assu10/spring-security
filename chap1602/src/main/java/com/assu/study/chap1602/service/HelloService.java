package com.assu.study.chap1602.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HelloService {

  private final Map<String, List<String>> secretNames = Map.of(
      "assu", List.of("assu1", "assu2"),
      "silby", List.of("silby1", "silby2")
  );

  // name 매개변수값을 #name 으로 참조하여 인증 개체에 직접 접근해서 현재 인증된 사용자를 참조
  // 인증된 사용자의 이름이 매서드의 매개 변수로 지정된 값과 같아야 메서드 호출 가능
  // 즉, 사용자는 자신의 비밀 이름만 검색 가능
  @PreAuthorize("#name == authentication.principal.username")
  public List<String> getSecretNames(String name) {
    return secretNames.get(name);
  }
}
