package com.assu.study.chap1601.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

  @PreAuthorize("hasAuthority('write')")  // 권한 부여 규칙 정의
  public String getName() {
    return "testName";
  }
}
