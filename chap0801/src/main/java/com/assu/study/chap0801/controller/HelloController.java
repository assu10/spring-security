package com.assu.study.chap0801.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  // ADMIN 만 접근 가능
  @GetMapping("/hello")
  public String hello() {
    return "Hello!";
  }

  // MANAGER 만 접근 가능
  @GetMapping("/bye")
  public String bye() {
    return "Bye!";
  }

  // 별도의 접근 제한 없음
  @GetMapping("/see")
  public String see() {
    return "See!";
  }

  // 별도의 접근 제한 없음
  @PostMapping("/see")
  public String see2() {
    return "See!";
  }
}
