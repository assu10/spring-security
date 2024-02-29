package com.assu.study.chap1601.controller;

import com.assu.study.chap1601.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HelloController {

  private final HelloService helloService;

  @GetMapping("/hello")
  public String hello() {
    // 사전 권한 부여 규칙을 적용한 메서드 호출
    return "Hello, " + helloService.getName();
  }
}
